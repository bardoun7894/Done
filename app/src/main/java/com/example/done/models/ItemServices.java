package com.example.done.models;

public class ItemServices {

    private String name ;
  private  int ImageUrl;

    public ItemServices(String name, int imageUrl) {
        this.name = name;
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getImageUrl() {
        return ImageUrl;
    }
}
