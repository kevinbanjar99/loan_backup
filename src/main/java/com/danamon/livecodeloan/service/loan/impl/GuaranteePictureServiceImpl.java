package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.GuaranteePicture;
import com.danamon.livecodeloan.entity.loan_entity.LoanStatus;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransactionDetail;
import com.danamon.livecodeloan.entity.loan_entity.ProfilePicture;
import com.danamon.livecodeloan.repo.loan_repo.GuaranteePictureRepository;
import com.danamon.livecodeloan.service.loan.GuaranteePictureService;
import com.danamon.livecodeloan.service.loan.LoanTransactionDetailService;
import com.danamon.livecodeloan.service.loan.LoanTransactionService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class GuaranteePictureServiceImpl implements GuaranteePictureService {
    GuaranteePictureRepository guaranteePictureRepository;
    LoanTransactionDetailService loanTransactionDetailService;
    LoanTransactionService loanTransactionService;

    @Value("${livecodeloan.file.path}")
    private String root;

    @Autowired
    public GuaranteePictureServiceImpl(GuaranteePictureRepository guaranteePictureRepository, LoanTransactionDetailService loanTransactionDetailService, LoanTransactionService loanTransactionService) {
        this.guaranteePictureRepository = guaranteePictureRepository;
        this.loanTransactionDetailService = loanTransactionDetailService;
        this.loanTransactionService = loanTransactionService;
    }

    @Override
    @Transactional
    public FileResponseDTO uploadGuaranteePicture(MultipartFile file, String trxId) {
        try {
            Path path = Paths.get(root);
            Path directories = Files.createDirectories(path);
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path saveFile = directories.resolve(filename);
            Files.copy(file.getInputStream(),saveFile);

            GuaranteePicture guaranteePicture = guaranteePictureRepository.save(new GuaranteePicture(null,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    saveFile.toString(),
                    file.getSize())
            );
            List<LoanTransactionDetail> loanTransactionDetails = loanTransactionService.getTransactionById(trxId).getLoanTransactionDetails();
            for (LoanTransactionDetail ltd: loanTransactionDetails) {
                if(ltd.getLoanStatus().name().equals("UNPAID")){
                    ltd.setGuaranteePicture(guaranteePicture);
                    ltd.setLoanStatus(LoanStatus.PAID);
                    loanTransactionDetailService.updateLoanTransactionDetailType(ltd);
                    break;
                }
            }
            return new FileResponseDTO(guaranteePicture.getName(),String.format("/products/%s/image",guaranteePicture.getId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource downloadGuaranteePicture(String id) {
        Optional<GuaranteePicture> guaranteePicture = guaranteePictureRepository.findById(id);
        if(!guaranteePicture.isPresent()) throw new DataNotFoundException("image not found");
        Path path = Paths.get(guaranteePicture.get().getPath());
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
