package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface GuaranteePictureService {
    FileResponseDTO uploadGuaranteePicture(MultipartFile file, String trxId);
    Resource downloadGuaranteePicture(String id);
}
