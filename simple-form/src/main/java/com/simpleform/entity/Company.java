package com.simpleform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private Integer bulstat;
    private Integer numberOfOffices;

    public Company(String companyName, Integer bulstat, Integer numberOfOffices) {
        this.companyName = companyName;
        this.bulstat = bulstat;
        this.numberOfOffices = numberOfOffices;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getBulstat() {
        return bulstat;
    }

    public Integer getNumberOfOffices() {
        return numberOfOffices;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBulstat(Integer bulstat) {
        this.bulstat = bulstat;
    }

    public void setNumberOfOffices(Integer numberOfOffices) {
        this.numberOfOffices = numberOfOffices;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", bulstat=" + bulstat +
                ", numberOfOffices=" + numberOfOffices +
                '}';
    }
}
