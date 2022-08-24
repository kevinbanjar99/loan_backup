package com.danamon.livecodeloan.service.auth;

import com.danamon.livecodeloan.dto.auth.LoginDTO;
import com.danamon.livecodeloan.dto.auth.UserDTO;
import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.danamon.livecodeloan.entity.auth_entity.ERole;
import com.danamon.livecodeloan.entity.auth_entity.Role;

public interface AuthService {
    UserDTO registerAdmin(AppUser appUser);
    UserDTO registerStaff(AppUser appUser);
    UserDTO registerCustomer(AppUser appUser);
    LoginDTO login(AppUser appUser);
}
