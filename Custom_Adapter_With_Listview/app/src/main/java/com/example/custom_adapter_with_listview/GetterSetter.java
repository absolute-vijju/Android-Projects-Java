package com.example.custom_adapter_with_listview;

public class GetterSetter {
    int img;
    String title;

    public GetterSetter(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
