package com.example.done.models;

public class services {


    private String sid ;
    private String date ;

    private String time ;
    private String service_type;
    private String service_desc;
    private String service_price;
    private String service_time;
    private String service_image;
    private String service_username;

public services(){}
    public services(String sid, String date, String time, String service_type, String service_desc, String service_price, String service_time, String service_image, String service_username) {
        this.sid = sid;
        this.date = date;
        this.time = time;
        this.service_type = service_type;
        this.service_desc = service_desc;
        this.service_price = service_price;
        this.service_time = service_time;
        this.service_image = service_image;
        this.service_username = service_username;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_desc() {
        return service_desc;
    }

    public void setService_desc(String service_desc) {
        this.service_desc = service_desc;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public String getService_image() {
        return service_image;
    }

    public void setService_image(String service_image) {
        this.service_image = service_image;
    }

    public String getService_username() {
        return service_username;
    }

    public void setService_username(String service_username) {
        this.service_username = service_username;
    }
}
