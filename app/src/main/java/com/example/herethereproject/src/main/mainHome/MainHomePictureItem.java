package com.example.herethereproject.src.main.mainHome;

public class MainHomeItem{

    private int profilePicture;
    private String nickName;
    private String region;
    private String time;
    private String like;
    private String bookMark;
    private String comment;

    public MainHomeItem(int profilePicture, String nickName, String region, String time, String like, String bookMark, String comment){
        this.profilePicture = profilePicture;
        this.nickName = nickName;
        this.region = region;
        this.time = time;
        this.like = like;
        this.bookMark = bookMark;
        this.comment = comment;

    }

    public int  getProfilePicture(){
        return profilePicture;
    }

    public String getNickName(){
        return nickName;
    }

    public String getRegion(){
        return region;
    }

    public String getTime(){
        return time;
    }

    public String getLike(){
        return like;
    }

    public String getBookMark(){
        return bookMark;
    }

    public String getComment(){
        return comment;
    }
}
