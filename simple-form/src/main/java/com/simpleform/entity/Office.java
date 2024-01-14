package com.simpleform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private Integer numberOfEmployees;
    private String officeContactNumber;

    public Office() {
    }

    public Office(String location, Integer numberOfEmployees, String officeContactNumber) {
        this.location = location;
        this.numberOfEmployees = numberOfEmployees;
        this.officeContactNumber = officeContactNumber;
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public String getOfficeContactNumber() {
        return officeContactNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public void setOfficeContactNumber(String officeContactNumber) {
        this.officeContactNumber = officeContactNumber;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                ", officeContactNumber='" + officeContactNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Objects.equals(id, office.id) && Objects.equals(location, office.location) && Objects.equals(numberOfEmployees, office.numberOfEmployees) && Objects.equals(officeContactNumber, office.officeContactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, numberOfEmployees, officeContactNumber);
    }
}
