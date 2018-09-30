package com.example.vietvan.androideatitserver.Model;

/**
 * Created by VietVan on 15/06/2018.
 */

public class Category {
    private String Name, Image;

    public Category() {
    }

    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Name='" + Name + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}
