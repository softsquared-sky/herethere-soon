package com.example.herethereproject.src.signUp.SignUpRegion;

import android.graphics.drawable.Drawable;

public class SignUpRegionItem {

    public boolean regionCheck = false;
    public String region;
    public Drawable trueDrawable;
    public Drawable falseDrawable;

    public SignUpRegionItem(String region, Drawable trueDrawable, Drawable falseDrawable){
        this.region = region;
        this.trueDrawable = trueDrawable;
        this.falseDrawable = falseDrawable;
    }

    public void setRegionCheck(boolean regionCheck){
        this.regionCheck = regionCheck;
    }

    public boolean getRegionCheck(){
        return regionCheck;
    }

    public String getRegion(){
        return region;
    }

}
