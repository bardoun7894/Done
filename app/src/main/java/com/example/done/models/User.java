package com.example.done.models;

public class User {
    String username ;
    String imageUrl ;
    double rating ;
    String email ;
    String password ;

  public User(String username, String imageUrl, String email, String password) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.email = email;
        this.password = password;
    }
    public User(String username , String email, String password ,double rating) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rating =rating;
    }
    public User(double rating) {
      this.rating =rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
