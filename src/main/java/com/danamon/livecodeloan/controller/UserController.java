package com.danamon.livecodeloan.controller;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.dto.auth.UserDTO;
import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.danamon.livecodeloan.service.loan.impl.CustomUserServiceImpl;
import com.danamon.livecodeloan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    CustomUserServiceImpl customUserService;

    @Autowired
    public UserController(CustomUserServiceImpl customUserService) {
        this.customUserService = customUserService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        UserDTO userDTO = new UserDTO(customUserService.getUserById(id));
        String message = String.format(ResponseMessage.DATA_FETCHED_BY_ID, ResponseMessage.USER);
        Response<?> response = new Response<>(message,userDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).
                body(response);
    }
}
