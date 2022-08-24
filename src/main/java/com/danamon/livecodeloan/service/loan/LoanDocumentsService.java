package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.InstalmentType;
import com.danamon.livecodeloan.entity.loan_entity.LoanDocuments;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LoanDocumentsService {
    FileResponseDTO uploadDocuments(MultipartFile file, String customerId);
    Resource downloadDocuments(String documentId);
}
