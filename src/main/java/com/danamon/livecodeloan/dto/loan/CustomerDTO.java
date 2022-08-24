package com.danamon.livecodeloan.dto.loan;

import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.entity.loan_entity.LoanDocuments;
import com.danamon.livecodeloan.entity.loan_entity.ProfilePicture;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class CustomerDTO {
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String status;
    @ManyToOne
    @JoinColumn(name="picture_id")
    @JsonIgnoreProperties("customer")
    private ProfilePicture profilePicture;
    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<LoanDocuments> loanDocuments;

    public CustomerDTO(String id, String firstName, String lastName, Date dateOfBirth, String phone, String status, ProfilePicture profilePicture, List<LoanDocuments> loanDocuments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.status = status;
        this.profilePicture = profilePicture;
        this.loanDocuments = loanDocuments;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.dateOfBirth = customer.getDateOfBirth();
        this.phone = customer.getPhone();
        this.status = customer.getStatus();
        this.profilePicture = customer.getProfilePicture();
        this.loanDocuments = customer.getLoanDocuments();
    }

    public CustomerDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<LoanDocuments> getLoanDocuments() {
        return loanDocuments;
    }

    public void setLoanDocuments(List<LoanDocuments> loanDocuments) {
        this.loanDocuments = loanDocuments;
    }
}
