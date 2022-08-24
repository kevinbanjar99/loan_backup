package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.entity.loan_entity.InstalmentType;
import com.danamon.livecodeloan.entity.loan_entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    LoanType saveLoanType(LoanType loanType);
    List<LoanType> getAllLoanType();
    LoanType getLoanTypeById(String id);
    LoanType updateLoanType(LoanType loanType);
    void deleteLoanType(String id);
}
