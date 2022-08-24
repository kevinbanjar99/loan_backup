package com.danamon.livecodeloan.repo.auth_repo;

import com.danamon.livecodeloan.entity.auth_entity.ERole;
import com.danamon.livecodeloan.entity.auth_entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByrole(ERole eRole);
}
