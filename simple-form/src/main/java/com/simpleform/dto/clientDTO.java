package com.simpleform.dto;

public class clientDTO {


    private Long id;
    private String firstName;
    private String lastName;

    private String contactNumber;

    public clientDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "clientDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
