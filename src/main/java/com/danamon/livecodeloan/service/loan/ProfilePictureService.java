package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.ProfilePicture;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePictureService {
    FileResponseDTO uploadAvatar(MultipartFile file, String customerId);
    Resource downloadAvatar(String customerId);
}
