package com.example.done;

public class ItemServices {

    private String name ;
  private  String ImageUrl;

    public ItemServices(String name, String imageUrl) {
        this.name = name;
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
