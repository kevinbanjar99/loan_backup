package com.danamon.livecodeloan.repo.loan_repo;

import com.danamon.livecodeloan.entity.loan_entity.GuaranteePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuaranteePictureRepository extends JpaRepository<GuaranteePicture,String> {
}
