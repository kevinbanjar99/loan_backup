package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.dto.auth.UserDTO;

public interface UserService {
    UserDTO getUserByID(String id);
}
