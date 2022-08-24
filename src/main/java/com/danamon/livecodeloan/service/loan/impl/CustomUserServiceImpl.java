package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.dto.auth.UserDTO;
import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.danamon.livecodeloan.entity.auth_entity.UserDetailsImpl;
import com.danamon.livecodeloan.repo.auth_repo.AppUserRepository;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public CustomUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmail(username);
        if(!appUser.isPresent()){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserDetailsImpl(appUser.get());
    }

    public AppUser getUserById(String id){
        if(appUserRepository.findById(id).isPresent()){
            return appUserRepository.findById(id).get();
        }
        else throw new DataNotFoundException("Data Not Found");
    }
}
