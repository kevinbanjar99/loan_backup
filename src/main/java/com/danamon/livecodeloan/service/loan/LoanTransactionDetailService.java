package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.entity.loan_entity.InstalmentType;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransaction;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransactionDetail;

import java.util.List;

public interface LoanTransactionDetailService {
    LoanTransactionDetail saveLoanTransactionDetail(LoanTransactionDetail loanTransactionDetail);
    List<LoanTransactionDetail> getAllLoanTransactionDetail();
    LoanTransactionDetail getLoanTransactionDetailById(String id);
    LoanTransactionDetail updateLoanTransactionDetailType(LoanTransactionDetail loanTransactionDetail);
    void deleteLoanTransactionDetail(String id);
}