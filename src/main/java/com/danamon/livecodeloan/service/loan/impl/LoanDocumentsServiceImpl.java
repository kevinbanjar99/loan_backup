package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.entity.loan_entity.GuaranteePicture;
import com.danamon.livecodeloan.entity.loan_entity.LoanDocuments;
import com.danamon.livecodeloan.entity.loan_entity.ProfilePicture;
import com.danamon.livecodeloan.repo.loan_repo.LoanDocumentsRepository;
import com.danamon.livecodeloan.service.loan.CustomerService;
import com.danamon.livecodeloan.service.loan.LoanDocumentsService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class LoanDocumentsServiceImpl implements LoanDocumentsService {
    LoanDocumentsRepository loanDocumentsRepository;
    CustomerService customerService;

    @Value("${livecodeloan.file.path}")
    private String root;

    @Autowired
    public LoanDocumentsServiceImpl(LoanDocumentsRepository loanDocumentsRepository,
                                    CustomerService customerService) {
        this.loanDocumentsRepository = loanDocumentsRepository;
        this.customerService = customerService;
    }

    @Override
    public FileResponseDTO uploadDocuments(MultipartFile file, String customerId) {
        try {
            Path path = Paths.get(root);
            Path directories = Files.createDirectories(path);
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path saveFile = directories.resolve(filename);
            Files.copy(file.getInputStream(),saveFile);

            LoanDocuments loanDocuments= loanDocumentsRepository.save(new LoanDocuments(null,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    saveFile.toString(),
                    file.getSize(),
                    customerService.getCustomerByID(customerId))
            );
            return new FileResponseDTO(loanDocuments.getName(),String.format("/products/%s/image",loanDocuments.getId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource downloadDocuments(String documentId) {
        Optional<LoanDocuments> loanDocuments = loanDocumentsRepository.findById(documentId);
        if(!loanDocuments.isPresent()) throw new DataNotFoundException("image not found");
        Path path = Paths.get(loanDocuments.get().getUrl());
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

