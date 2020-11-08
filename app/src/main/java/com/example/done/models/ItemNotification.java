package com.example.done.models;

public class ItemNotification {

    String description ;
    String deliveryTime ;
    String pay_type ;
    String username;
    String classification ;

public ItemNotification(){};
    public ItemNotification(String description, String deliveryTime, String pay_type, String username, String classification) {
        this.description = description;
        this.deliveryTime = deliveryTime;
        this.pay_type = pay_type;
        this.username = username;
        this.classification = classification;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
