package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.entity.loan_entity.ProfilePicture;
import com.danamon.livecodeloan.repo.loan_repo.ProfilePictureRepository;
import com.danamon.livecodeloan.service.loan.CustomerService;
import com.danamon.livecodeloan.service.loan.ProfilePictureService;
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
import java.util.Optional;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {
    ProfilePictureRepository profilePictureRepository;
    CustomerService customerService;

    @Autowired
    public ProfilePictureServiceImpl(ProfilePictureRepository profilePictureRepository, CustomerService customerService) {
        this.profilePictureRepository = profilePictureRepository;
        this.customerService = customerService;
    }

    @Value("${livecodeloan.file.path}")
    private String root;

    @Override
    @Transactional
    public FileResponseDTO uploadAvatar(MultipartFile file, String customerId) {
        try {
            Path path = Paths.get(root);
            Path directories = Files.createDirectories(path);
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path saveFile = directories.resolve(filename);
            Files.copy(file.getInputStream(),saveFile);

            ProfilePicture profilePicture = profilePictureRepository.save(new ProfilePicture(null,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    saveFile.toString(),
                    file.getSize())
            );
            Customer customer = customerService.getCustomerByID(customerId);
            customer.setProfilePicture(profilePicture);
            customerService.updateCustomer(customer);
            return new FileResponseDTO(profilePicture.getName(),String.format("/products/%s/image",profilePicture.getId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource downloadAvatar(String customerId) {
        Customer customer = customerService.getCustomerByID(customerId);
        Optional<ProfilePicture> profilePicture = profilePictureRepository.findById(customer.getProfilePicture().getId());
        if(!profilePicture.isPresent()) throw new DataNotFoundException("image not found");
        Path path = Paths.get(profilePicture.get().getUrl());
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
