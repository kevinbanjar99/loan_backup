package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.dto.loan.AdminApprovalDTO;
import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransaction;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

public interface LoanTransactionService {
    LoanTransaction requestLoan(LoanTransaction loanTransaction);
    LoanTransaction getTransactionById(String id);
    LoanTransaction approveTransactionRequestByAdminId(String adminId, AdminApprovalDTO adminApprovalDTO);
    FileResponseDTO payInstalment(MultipartFile file);
    Resource downloadGuaranteePicture(String id);
}
