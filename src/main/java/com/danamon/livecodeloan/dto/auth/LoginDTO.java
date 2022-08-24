package com.danamon.livecodeloan.dto.auth;

import com.danamon.livecodeloan.entity.auth_entity.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class LoginDTO {
    private String userEmail;
    private List<String> roles;
    private String token;

    public LoginDTO(UserDetailsImpl userDetails, String token) {
        this.userEmail = userDetails.getUsername();
        this.roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        this.token = token;
    }

    public LoginDTO() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
