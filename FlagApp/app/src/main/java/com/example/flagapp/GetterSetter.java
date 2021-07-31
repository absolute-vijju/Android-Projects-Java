package com.example.flagapp;

public class GetterSetter {

    int img;
    String name, capital;

    public GetterSetter(int img, String name, String capital) {
        this.img = img;
        this.name = name;
        this.capital = capital;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
