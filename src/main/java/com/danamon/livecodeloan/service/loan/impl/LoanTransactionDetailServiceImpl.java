package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransactionDetail;
import com.danamon.livecodeloan.repo.loan_repo.LoanTransactionDetailRepository;
import com.danamon.livecodeloan.service.loan.LoanTransactionDetailService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanTransactionDetailServiceImpl implements LoanTransactionDetailService {
    LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Autowired
    public LoanTransactionDetailServiceImpl(LoanTransactionDetailRepository loanTransactionDetailRepository) {
        this.loanTransactionDetailRepository = loanTransactionDetailRepository;
    }

    @Override
    public LoanTransactionDetail saveLoanTransactionDetail(LoanTransactionDetail loanTransactionDetail) {
        return loanTransactionDetailRepository.save(loanTransactionDetail);
    }

    @Override
    public List<LoanTransactionDetail> getAllLoanTransactionDetail() {
        return loanTransactionDetailRepository.findAll();
    }

    @Override
    public LoanTransactionDetail getLoanTransactionDetailById(String id) {
        if(loanTransactionDetailRepository.findById(id).isPresent()){
            return loanTransactionDetailRepository.findById(id).get();
        }
        else throw new DataNotFoundException(
                String.format(ResponseMessage.NOT_FOUND_MESSAGE,
                        ResponseMessage.LOAN_TRANSACTION,
                        ResponseMessage.ID));
    }

    @Override
    public LoanTransactionDetail updateLoanTransactionDetailType(LoanTransactionDetail loanTransactionDetail) {
        if(loanTransactionDetailRepository.findById(loanTransactionDetail.getId()).isPresent()){
            return loanTransactionDetailRepository.save(loanTransactionDetail);
        }
        else throw new DataNotFoundException("not dounf");
    }

    @Override
    public void deleteLoanTransactionDetail(String id) {
        if(loanTransactionDetailRepository.findById(id).isPresent()){
            loanTransactionDetailRepository.deleteById(id);
        }
        else throw new DataNotFoundException("not found again");
    }
}
