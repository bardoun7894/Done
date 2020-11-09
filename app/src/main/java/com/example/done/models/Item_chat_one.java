package com.example.done.models;

public class Item_chat_one {
    String reciever ;
    String sender ;
    String message ;

    public Item_chat_one(String reciever, String sender, String message) {
        this.reciever = reciever;
        this.sender = sender;
        this.message = message;
    }
    public Item_chat_one() {
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
