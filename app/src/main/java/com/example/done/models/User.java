package com.example.done.models;

import java.io.Serializable;
import java.util.List;



public class User  implements Serializable {
    String username;
    String Certified;
    String balad_koliya;
    String Certified_year;
    String certificate;
    String degree_science;
    String degree_year;
    String koliya_name;
    String skills;
    List<String> classification;
    String rating;
    String email;
    String password;
    String PhotoProfile ;
    String desc_experience ;
    String first_name;
    String last_name;
    String type_of_user;
    List<String> languageExperience ;



    public String getCertified() {
        return Certified;
    }

    public void setCertified(String certified) {
        Certified = certified;
    }

    public String getBalad_koliya() {
        return balad_koliya;
    }

    public void setBalad_koliya(String balad_koliya) {
        this.balad_koliya = balad_koliya;
    }

    public String getCertified_year() {
        return Certified_year;
    }

    public void setCertified_year(String certified_year) {
        Certified_year = certified_year;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDegree_science() {
        return degree_science;
    }

    public void setDegree_science(String degree_science) {
        this.degree_science = degree_science;
    }

    public String getDegree_year() {
        return degree_year;
    }

    public void setDegree_year(String degree_year) {
        this.degree_year = degree_year;
    }

    public String getKoliya_name() {
        return koliya_name;
    }

    public void setKoliya_name(String koliya_name) {
        this.koliya_name = koliya_name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public List<String> getClassification() {
        return classification;
    }

    public void setClassification(List<String> classification) {
        this.classification = classification;
    }



    public User(String username, String certified, String balad_koliya, String certified_year, String certificate, String degree_science, String degree_year, String koliya_name, String skills, List<String> classification, String rating, String email, String password, String photoProfile, String desc_experience, String first_name, String last_name, String type_of_user, List<String> languageExperience) {
        this.username = username;
        Certified = certified;
        this.balad_koliya = balad_koliya;
        Certified_year = certified_year;
        this.certificate = certificate;
        this.degree_science = degree_science;
        this.degree_year = degree_year;
        this.koliya_name = koliya_name;
        this.skills = skills;
        this.classification = classification;
        this.rating = rating;
        this.email = email;
        this.password = password;
        PhotoProfile = photoProfile;
        this.desc_experience = desc_experience;
        this.first_name = first_name;
        this.last_name = last_name;
        this.type_of_user = type_of_user;
        this.languageExperience = languageExperience;
    }

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

    public List<String> getLanguageExperience() {
        return languageExperience;
    }

    public void setLanguageExperience(List<String>  languageExperience) {
        this.languageExperience = languageExperience;
    }

    public User(String username, String rating, String email, String password, String photoProfile, String desc_experience, String first_name, String last_name, List<String>  languageExperience) {
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