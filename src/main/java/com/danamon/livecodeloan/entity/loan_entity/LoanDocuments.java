package com.danamon.livecodeloan.entity.loan_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="t_loan_document")
public class LoanDocuments{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    private String name;
    private String contentType;
    private String url;
    private long size;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnoreProperties("loanDocuments")
    private Customer customer;

    public LoanDocuments(String id, String name, String contentType, String url, long size, Customer customer) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.url = url;
        this.size = size;
        this.customer = customer;
    }

    public LoanDocuments() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
