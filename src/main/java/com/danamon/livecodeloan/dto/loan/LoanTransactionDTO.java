package com.danamon.livecodeloan.dto.loan;

import com.danamon.livecodeloan.entity.loan_entity.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class LoanTransactionDTO {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerID;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus; // enum
    private List<LoanTransactionDetail> transactionDetailsResponse;
    private Long createdAt;
    private Long updatedAt;

    public LoanTransactionDTO(LoanTransaction loanTransaction) {
        this.id = loanTransaction.getId();
        this.loanTypeId = loanTransaction.getLoanType().getId();
        this.instalmentTypeId = loanTransaction.getInstalmentType().getId();
        this.customerID = loanTransaction.getCustomer().getId();
        this.nominal = loanTransaction.getNominal();
        this.approvedAt = loanTransaction.getApprovedAt();
        this.approvedBy = loanTransaction.getApprovedBy();
        this.approvalStatus = loanTransaction.getApprovalStatus();

/*        if(!(loanTransaction.getLoanTransactionDetails().isEmpty()||
                loanTransaction.getLoanTransactionDetails() == null)){
            List<LoanTransactionDetailDTO> loanTransactionDetailDTOS = new ArrayList<>();
            for (LoanTransactionDetail ltd: loanTransaction.getLoanTransactionDetails()){
                loanTransactionDetailDTOS.add(new LoanTransactionDetailDTO(ltd));
            }
            this.transactionDetailsResponse = loanTransactionDetailDTOS;
        }
        else this.transactionDetailsResponse = null;*/
        this.transactionDetailsResponse = loanTransaction.getLoanTransactionDetails() ;
        this.createdAt = loanTransaction.getCreatedAt();
        this.updatedAt = loanTransaction.getUpdatedAt();
    }

    public LoanTransactionDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getInstalmentTypeId() {
        return instalmentTypeId;
    }

    public void setInstalmentTypeId(String instalmentTypeId) {
        this.instalmentTypeId = instalmentTypeId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Long getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Long approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public List<LoanTransactionDetail> getTransactionDetailsResponse() {
        return transactionDetailsResponse;
    }

    public void setTransactionDetailsResponse(List<LoanTransactionDetail> transactionDetailsResponse) {
        this.transactionDetailsResponse = transactionDetailsResponse;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
