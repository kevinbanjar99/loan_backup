package com.danamon.livecodeloan.service.auth.impl;

import com.danamon.livecodeloan.entity.auth_entity.ERole;
import com.danamon.livecodeloan.entity.auth_entity.Role;
import com.danamon.livecodeloan.repo.auth_repo.RoleRepository;
import com.danamon.livecodeloan.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getOrSave(ERole eRole) {
        return roleRepository.findByrole(eRole).
                orElseGet(() -> roleRepository.save(new Role(null,eRole)));
    }
}
