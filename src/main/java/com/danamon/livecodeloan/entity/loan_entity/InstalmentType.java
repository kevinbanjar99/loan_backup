package com.danamon.livecodeloan.entity.loan_entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_instalment_type")
public class InstalmentType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String id;
    @Enumerated(EnumType.STRING)
    private EInstalmentType instalmentType;

    public InstalmentType(String id, EInstalmentType instalmentType) {
        this.id = id;
        this.instalmentType = instalmentType;
    }

    public InstalmentType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EInstalmentType getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(EInstalmentType instalmentType) {
        this.instalmentType = instalmentType;
    }
}
