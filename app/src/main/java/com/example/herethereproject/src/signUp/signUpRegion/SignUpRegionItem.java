package com.example.herethereproject.src.signUp.signUpRegion;

import android.graphics.drawable.Drawable;

public class SignUpRegionItem {

    public boolean regionCheck = false;
    public String region;
    public Drawable trueDrawable;
    public Drawable falseDrawable;
    public int regionNo;

    public SignUpRegionItem(String region, Drawable trueDrawable, Drawable falseDrawable, int regionNo){
        this.region = region;
        this.trueDrawable = trueDrawable;
        this.falseDrawable = falseDrawable;
        this.regionNo = regionNo;
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

    public int getRegionNo(){
        return regionNo;
    }

}
