package com.example.done.models;

public class ItemServices {

    private String name ;
  private  int ImageUrl;

    public ItemServices() {
    }

    public ItemServices(String name, int imageUrl) {
        this.name = name;
        ImageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(int imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getImageUrl() {
        return ImageUrl;
    }

}
