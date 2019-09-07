package com.example.herethereproject.src.write;

public class WriteItem {

    private String postPicture;
    private boolean plus;

    public WriteItem(String postPicture, boolean plus){
        this.postPicture = postPicture;
        this.plus = plus;

    }

    public String getPostPicture() {
        return postPicture;
    }

    public boolean isPlus() {
        return plus;
    }
}
