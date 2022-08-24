package com.danamon.livecodeloan.entity.loan_entity;

import com.danamon.livecodeloan.entity.auth_entity.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_customer")
public class Customer {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String status;
    @OneToOne
    @JoinColumn(name= "user_id")
    private AppUser user;
    @ManyToOne
    @JoinColumn(name="picture_id")
    @JsonIgnoreProperties("customer")
    private ProfilePicture profilePicture;
    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<LoanDocuments> loanDocuments;

    public Customer(String id, String firstName, String lastName, Date dateOfBirth, String phone, String status, AppUser user, ProfilePicture profilePicture, List<LoanDocuments> loanDocuments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.status = status;
        this.user = user;
        this.profilePicture = profilePicture;
        this.loanDocuments = loanDocuments;
    }

    public Customer() {
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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
