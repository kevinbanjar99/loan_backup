package com.danamon.livecodeloan.service.auth;

import com.danamon.livecodeloan.entity.auth_entity.ERole;
import com.danamon.livecodeloan.entity.auth_entity.Role;

public interface RoleService {
    Role getOrSave(ERole eRole);
}
