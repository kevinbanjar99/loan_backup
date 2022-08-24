package com.danamon.livecodeloan.entity.loan_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trx_loan")
public class LoanTransaction {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    @OneToOne
    @JoinColumn(name= "loan_type_id")
    private LoanType loanType;
    @OneToOne
    @JoinColumn(name= "instalment_type_id")
    private InstalmentType instalmentType;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnoreProperties("loanTransaction")
    private Customer customer;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus; // enum
    @OneToMany(mappedBy = "loanTransaction")
    @JsonIgnoreProperties("loanTransaction")
    private List<LoanTransactionDetail> loanTransactionDetails;
    private Long createdAt;
    private Long updatedAt;

    public LoanTransaction(String id, LoanType loanType, InstalmentType instalmentType, Customer customer, Double nominal, Long approvedAt, String approvedBy, ApprovalStatus approvalStatus, List<LoanTransactionDetail> loanTransactionDetails, Long createdAt, Long updatedAt) {
        this.id = id;
        this.loanType = loanType;
        this.instalmentType = instalmentType;
        this.customer = customer;
        this.nominal = nominal;
        this.approvedAt = approvedAt;
        this.approvedBy = approvedBy;
        this.approvalStatus = approvalStatus;
        this.loanTransactionDetails = loanTransactionDetails;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LoanTransaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public InstalmentType getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(InstalmentType instalmentType) {
        this.instalmentType = instalmentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public List<LoanTransactionDetail> getLoanTransactionDetails() {
        return loanTransactionDetails;
    }

    public void setLoanTransactionDetails(List<LoanTransactionDetail> loanTransactionDetails) {
        this.loanTransactionDetails = loanTransactionDetails;
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
