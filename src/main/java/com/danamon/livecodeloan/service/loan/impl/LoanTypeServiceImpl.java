package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.entity.loan_entity.LoanType;
import com.danamon.livecodeloan.repo.loan_repo.LoanTypeRepository;
import com.danamon.livecodeloan.service.loan.LoanTypeService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanTypeServiceImpl implements LoanTypeService {
    LoanTypeRepository loanTypeRepository;

    @Autowired
    public LoanTypeServiceImpl(LoanTypeRepository loanTypeRepository) {
        this.loanTypeRepository = loanTypeRepository;
    }

    @Override
    public LoanType saveLoanType(LoanType loanType) {
        return loanTypeRepository.save(loanType);
    }

    @Override
    public List<LoanType> getAllLoanType() {
        return loanTypeRepository.findAll();
    }

    @Override
    public LoanType getLoanTypeById(String id) {
        if(loanTypeRepository.findById(id).isPresent()){
            return loanTypeRepository.findById(id).get();
        }
        else throw new DataNotFoundException("Not found bud");
    }

    @Override
    public LoanType updateLoanType(LoanType loanType) {
        if(loanTypeRepository.findById(loanType.getId()).isPresent()){
            return loanTypeRepository.save(loanType);
        }
        else throw new DataNotFoundException("not dounf");
    }

    @Override
    public void deleteLoanType(String id) {
        if(loanTypeRepository.findById(id).isPresent()){
            loanTypeRepository.deleteById(id);
        }
        else throw new DataNotFoundException("not found again");
    }
}
