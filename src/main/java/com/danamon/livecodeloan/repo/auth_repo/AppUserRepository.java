package com.danamon.livecodeloan.repo.auth_repo;

import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
    Optional<AppUser> findByEmail(String email);
}
