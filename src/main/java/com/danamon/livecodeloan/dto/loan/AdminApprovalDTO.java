package com.danamon.livecodeloan.dto.loan;

public class AdminApprovalDTO {
    private String loanTransactionId;
    private Integer interestRates;

    public AdminApprovalDTO(String loanTransactionId, Integer interestRates) {
        this.loanTransactionId = loanTransactionId;
        this.interestRates = interestRates;
    }

    public AdminApprovalDTO() {
    }

    public String getLoanTransactionId() {
        return loanTransactionId;
    }

    public void setLoanTransactionId(String loanTransactionId) {
        this.loanTransactionId = loanTransactionId;
    }

    public Integer getInterestRates() {
        return interestRates;
    }

    public void setInterestRates(Integer interestRates) {
        this.interestRates = interestRates;
    }
}
