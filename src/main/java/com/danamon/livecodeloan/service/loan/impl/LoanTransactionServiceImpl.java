package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.constant.ResponseMessage;
import com.danamon.livecodeloan.dto.loan.AdminApprovalDTO;
import com.danamon.livecodeloan.dto.loan.FileResponseDTO;
import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.danamon.livecodeloan.entity.loan_entity.ApprovalStatus;
import com.danamon.livecodeloan.entity.loan_entity.LoanStatus;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransaction;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransactionDetail;
import com.danamon.livecodeloan.repo.loan_repo.LoanTransactionRepository;
import com.danamon.livecodeloan.service.loan.*;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LoanTransactionServiceImpl implements LoanTransactionService {
    LoanTransactionRepository loanTransactionRepository;
    CustomerService customerService;
    InstalmentTypeService instalmentTypeService;
    LoanTypeService loanTypeService;
    LoanTransactionDetailService loanTransactionDetailService;
    CustomUserServiceImpl customUserService;

    @Autowired
    public LoanTransactionServiceImpl(LoanTransactionRepository loanTransactionRepository, CustomerService customerService, InstalmentTypeService instalmentTypeService, LoanTypeService loanTypeService, CustomUserServiceImpl customUserService, LoanTransactionDetailService loanTransactionDetailService) {
        this.loanTransactionRepository = loanTransactionRepository;
        this.customerService = customerService;
        this.instalmentTypeService = instalmentTypeService;
        this.loanTypeService = loanTypeService;
        this.customUserService = customUserService;
        this.loanTransactionDetailService = loanTransactionDetailService;
    }

    @Override
    public LoanTransaction requestLoan(LoanTransaction loanTransaction) {
        loanTransaction.setCreatedAt(System.currentTimeMillis());
        return loanTransactionRepository.save(loanTransaction);
    }

    @Override
    public LoanTransaction getTransactionById(String id) {

        if(loanTransactionRepository.findById(id).isPresent()){
            return loanTransactionRepository.findById(id).get();
        }
        else throw new DataNotFoundException(
                String.format(ResponseMessage.NOT_FOUND_MESSAGE,
                        ResponseMessage.LOAN_TRANSACTION,
                        ResponseMessage.ID));
    }

    @Override
    @Transactional
    public LoanTransaction approveTransactionRequestByAdminId(String adminId, AdminApprovalDTO adminApprovalDTO) {
        LoanTransaction loanTransaction = getTransactionById(adminApprovalDTO.getLoanTransactionId());
        AppUser admin = customUserService.getUserById(adminId);

        String eInstalmentType = loanTransaction.getInstalmentType().getInstalmentType().name();
        int iter = 0;
        switch(eInstalmentType) {
            case "ONE_MONTH": iter=1; break;
            case "THREE_MONTH": iter=3; break;
            case "SIXTH_MONTH": iter=6; break;
            case "NINE_MONTH": iter=9; break;
            case "TWELVE_MONTH": iter=12; break;
        }

        List<LoanTransactionDetail> loanTransactionDetails = new ArrayList<>();

        for(int i =1; i<=iter; i++) {
            LoanTransactionDetail loanTransactionDetail = new LoanTransactionDetail();
            loanTransactionDetail.setNominal(loanTransaction.getNominal() +
                    (loanTransaction.getNominal() * 3 / 100));
            loanTransactionDetail.setLoanTransaction(loanTransaction);
            loanTransactionDetail.setLoanStatus(LoanStatus.UNPAID);
            loanTransactionDetail.setCreatedAt(System.currentTimeMillis());
            loanTransactionDetail.setUpdatedAt(System.currentTimeMillis());
            LoanTransactionDetail loanTransactionDetail1 = loanTransactionDetailService.saveLoanTransactionDetail(loanTransactionDetail);
            loanTransactionDetails.add(loanTransactionDetail1);
        }

        loanTransaction.setApprovedAt(System.currentTimeMillis());
        loanTransaction.setApprovedBy(admin.getEmail());
        loanTransaction.setApprovalStatus(ApprovalStatus.APPROVED);
        loanTransaction.setUpdatedAt(System.currentTimeMillis());
        loanTransaction.setLoanTransactionDetails(loanTransactionDetails);
        return loanTransaction;
    }

    @Override
    public FileResponseDTO payInstalment(MultipartFile file) {
        return null;
    }

    @Override
    public Resource downloadGuaranteePicture(String id) {
        return null;
    }
}
