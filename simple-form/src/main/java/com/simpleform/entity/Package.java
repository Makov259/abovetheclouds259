package com.simpleform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String recipient;
    private String deliveryAddress;
    private String weight;

    public Package() {
    }

    public Package(String sender, String recipient, String deliveryAddress, String weight) {
        this.sender = sender;
        this.recipient = recipient;
        this.deliveryAddress = deliveryAddress;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getWeight() {
        return weight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", weight=" + weight +
                '}';
    }
}
