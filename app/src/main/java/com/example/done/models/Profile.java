package com.example.done.models;

import java.util.List;

public class Profile {
    User user ;
    String photoUrl ;
    String desc_experience ;
    String first_name;
    String last_name;
    List languageExperience ;
    public Profile(User user, String photoUrl, String desc_experience, String first_name, String last_name, List languageExperience) {
        this.user = user;
        this.photoUrl = photoUrl;
        this.desc_experience = desc_experience;
        this.first_name = first_name;
        this.last_name = last_name;
        this.languageExperience = languageExperience;
    }
    public Profile(String photoUrl, String desc_experience, String first_name, String last_name, List  languageExperience) {
        this.photoUrl = photoUrl;
        this.desc_experience = desc_experience;
        this.first_name = first_name;
        this.last_name = last_name;
        this.languageExperience = languageExperience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
}
