package com.danamon.livecodeloan.entity.loan_entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_loan_type")
public class LoanType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    private String type;
    private Double maxLoan;

    public LoanType(String id, String type, Double maxLoan) {
        this.id = id;
        this.type = type;
        this.maxLoan = maxLoan;
    }

    public LoanType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMaxLoan() {
        return maxLoan;
    }

    public void setMaxLoan(Double maxLoan) {
        this.maxLoan = maxLoan;
    }
}
