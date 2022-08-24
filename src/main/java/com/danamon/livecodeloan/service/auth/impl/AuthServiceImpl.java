package com.danamon.livecodeloan.service.auth.impl;

import com.danamon.livecodeloan.dto.auth.LoginDTO;
import com.danamon.livecodeloan.dto.auth.UserDTO;
import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.danamon.livecodeloan.entity.auth_entity.ERole;
import com.danamon.livecodeloan.entity.auth_entity.Role;
import com.danamon.livecodeloan.entity.auth_entity.UserDetailsImpl;
import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.repo.auth_repo.AppUserRepository;
import com.danamon.livecodeloan.security.JwtUtils;
import com.danamon.livecodeloan.service.auth.AuthService;
import com.danamon.livecodeloan.service.auth.RoleService;
import com.danamon.livecodeloan.service.loan.CustomerService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RoleService roleService;
    private final CustomerService customerService;

    @Autowired
    public AuthServiceImpl(AppUserRepository appUserRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, RoleService roleService, CustomerService customerService) {
        this.appUserRepository = appUserRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.roleService = roleService;
        this.customerService = customerService;
    }

    @Override
    public UserDTO registerAdmin(AppUser appUser) {
        Role role1 = roleService.getOrSave(ERole.ROLE_ADMIN);
        //Role role2 = roleService.getOrSave(ERole.ROLE_STAFF);
        appUser.setRoles(Arrays.asList(role1));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser appUser1 = appUserRepository.save(appUser);
        return new UserDTO(appUser1);
    }

    @Override
    public UserDTO registerStaff(AppUser appUser) {
        Role role2 = roleService.getOrSave(ERole.ROLE_STAFF);
        appUser.setRoles(Arrays.asList(role2));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser appUser1 = appUserRepository.save(appUser);
        return new UserDTO(appUser1);
    }

    @Override
    @Transactional
    public UserDTO registerCustomer(AppUser appUser) {
        Role role = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        appUser.setRoles(Arrays.asList(role));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        AppUser appUser1 = appUserRepository.save(appUser);

        Customer customer = customerService.saveCustomer(new Customer());
        customer.setUser(appUser);

        return new UserDTO(appUser);
    }

    @Override
    public LoginDTO login(AppUser appUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        appUser.getEmail(),
                        appUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        logger.info("User ({}) was logged",appUser.getEmail());
        return new LoginDTO(userDetails,token);
    }
}
