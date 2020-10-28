package com.example.done.models;

public class ItemServiceSearch {

    int imageService ;
    double rating_number ;
    String description_text ;
    String price ;

    public ItemServiceSearch(int imageService, double rating_number, String description_text, String price) {
        this.imageService = imageService;
        this.rating_number = rating_number;
        this.description_text = description_text;
        this.price = price;
    }
    public int getImageService() {
        return imageService;
    }

    public void setImageService(int imageService) {
        this.imageService = imageService;
    }

    public double getRating_number() {
        return rating_number;
    }

    public void setRating_number(double rating_number) {
        this.rating_number = rating_number;
    }

    public String getDescription_text() {
        return description_text;
    }

    public void setDescription_text(String description_text) {
        this.description_text = description_text;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
