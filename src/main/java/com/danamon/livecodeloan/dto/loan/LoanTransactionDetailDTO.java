package com.danamon.livecodeloan.dto.loan;

import com.danamon.livecodeloan.entity.loan_entity.LoanStatus;
import com.danamon.livecodeloan.entity.loan_entity.LoanTransactionDetail;


import javax.persistence.*;

public class LoanTransactionDetailDTO {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private String guaranteePicture;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus; // enum
    private Long createdAt;
    private Long updatedAt;

    public LoanTransactionDetailDTO(LoanTransactionDetail loanTransactionDetail) {
        this.id = loanTransactionDetail.getId();
        this.transactionDate = loanTransactionDetail.getTransactionDate();
        this.nominal = loanTransactionDetail.getNominal();
        this.guaranteePicture = loanTransactionDetail.getGuaranteePicture().getPath();
        this.loanStatus = loanTransactionDetail.getLoanStatus();
        this.createdAt = loanTransactionDetail.getCreatedAt();
        this.updatedAt = loanTransactionDetail.getUpdatedAt();
    }

    public LoanTransactionDetailDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public String getGuaranteePicture() {
        return guaranteePicture;
    }

    public void setGuaranteePicture(String guaranteePicture) {
        this.guaranteePicture = guaranteePicture;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
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
