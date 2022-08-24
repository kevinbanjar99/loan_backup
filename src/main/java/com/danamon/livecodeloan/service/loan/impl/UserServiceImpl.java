package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.dto.auth.UserDTO;
import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.danamon.livecodeloan.repo.auth_repo.AppUserRepository;
import com.danamon.livecodeloan.repo.loan_repo.CustomerRepository;
import com.danamon.livecodeloan.service.loan.UserService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    AppUserRepository appUserRepository;

    @Autowired
    public UserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDTO getUserByID(String id){
        if(appUserRepository.findById(id).isPresent()){
            return new UserDTO(appUserRepository.findById(id).get());
        }
        else throw new DataNotFoundException("Data Not Found");
    }
}
