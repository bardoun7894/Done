package com.example.done.models;

import java.util.List;

public class User {
    String username;
    String rating;
    String email;
    String password;
    String PhotoProfile ;
    String desc_experience ;
    String first_name;
    String last_name;
    String type_of_user;
    List<String> languageExperience ;

    public String getType_of_user() {
        return type_of_user;
    }

    public void setType_of_user(String type_of_user) {
        this.type_of_user = type_of_user;
    }

    public String getPhotoProfile() {
        return PhotoProfile;
    }

    public void setPhotoProfile(String photoProfile) {
        PhotoProfile = photoProfile;
    }

    public String getDesc_experience() {
        return desc_experience;
    }

    public void setDesc_experience(String desc_experience) {
        this.desc_experience = desc_experience;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List getLanguageExperience() {
        return languageExperience;
    }

    public void setLanguageExperience(List languageExperience) {
        this.languageExperience = languageExperience;
    }

    public User(String username, String rating, String email, String password, String photoProfile, String desc_experience, String first_name, String last_name, List languageExperience) {
        this.username = username;
        this.rating = rating;
        this.email = email;
        this.password = password;
        PhotoProfile = photoProfile;
        this.desc_experience = desc_experience;
        this.first_name = first_name;
        this.last_name = last_name;
        this.languageExperience = languageExperience;
    }

    public User() {

    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String rating, String email, String password) {
        this.username = username;
        this.rating = rating;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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
}