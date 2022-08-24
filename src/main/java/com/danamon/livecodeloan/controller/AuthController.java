package com.danamon.livecodeloan.controller;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.dto.auth.LoginDTO;
import com.danamon.livecodeloan.dto.auth.UserDTO;
import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.danamon.livecodeloan.service.auth.AuthService;
import com.danamon.livecodeloan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody AppUser appUser){
        UserDTO register = authService.registerCustomer(appUser);

        String message = String.format(ResponseMessage.DATA_REGISTERED, "new user");
        Response<?> response = new Response<>(message,register);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).
                body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody AppUser user){
        LoginDTO loginDTO = authService.login(user);

        Response<?> response = new Response<>(
                "Successfully login", loginDTO
        );

        return ResponseEntity.ok(response);
    }

    //BelowForTestingPurposes
    @PostMapping("/signup-special-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AppUser appUser){
        UserDTO register = authService.registerAdmin(appUser);

        String message = String.format(ResponseMessage.DATA_REGISTERED, ResponseMessage.ADMIN);
        Response<?> response = new Response<>(message,register);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).
                body(response);
    }
    @PostMapping("/signup-special-staff")
    public ResponseEntity<?> registerStaff(@RequestBody AppUser appUser){
        UserDTO register = authService.registerStaff(appUser);

        String message = String.format(ResponseMessage.DATA_REGISTERED, ResponseMessage.STAFF);
        Response<?> response = new Response<>(message,register);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).
                body(response);
    }
}
