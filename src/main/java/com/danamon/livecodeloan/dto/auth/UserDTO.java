package com.danamon.livecodeloan.dto.auth;


import com.danamon.livecodeloan.entity.auth_entity.AppUser;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private String email;
    private List<String> roles;

    public UserDTO(AppUser user) {
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(role -> role.getRole().name()).collect(Collectors.toList());
    }

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
