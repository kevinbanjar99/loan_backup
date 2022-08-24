package com.danamon.livecodeloan.entity.loan_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_loan_detail")
public class LoanTransactionDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    private Long transactionDate;
    private Double nominal;
    @ManyToOne
    @JoinColumn(name="loan_transaction_id")
    @JsonIgnoreProperties("loanTransactionDetail")
    private LoanTransaction loanTransaction;
    @ManyToOne
    @JoinColumn(name="guarantee_picture_id")
    @JsonIgnoreProperties("loan_transaction_detail")
    private GuaranteePicture guaranteePicture;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus; // enum
    private Long createdAt;
    private Long updatedAt;

    public LoanTransactionDetail(String id, Long transactionDate, Double nominal, LoanTransaction loanTransaction, GuaranteePicture guaranteePicture, LoanStatus loanStatus, Long createdAt, Long updatedAt) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.nominal = nominal;
        this.loanTransaction = loanTransaction;
        this.guaranteePicture = guaranteePicture;
        this.loanStatus = loanStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LoanTransactionDetail() {
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

    public LoanTransaction getLoanTransaction() {
        return loanTransaction;
    }

    public void setLoanTransaction(LoanTransaction loanTransaction) {
        this.loanTransaction = loanTransaction;
    }

    public GuaranteePicture getGuaranteePicture() {
        return guaranteePicture;
    }

    public void setGuaranteePicture(GuaranteePicture guaranteePicture) {
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
