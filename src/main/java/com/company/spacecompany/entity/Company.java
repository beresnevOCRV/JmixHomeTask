package com.company.spacecompany.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "SC_COMPANY")
@JmixEntity
@Entity(name = "sc_Company")
@PrimaryKeyJoinColumn(name = "company_id", referencedColumnName = "ID")
public class Company extends Customer {
    @InstanceName
    @Column(name = "REGISTRATION_ID", length = 100)
    private String registrationId;

    @Column(name = "COMPANY_TYPE", length = 100)
    private String companyType;

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}