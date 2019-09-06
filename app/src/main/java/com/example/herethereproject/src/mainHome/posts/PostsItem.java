package com.example.herethereproject.src.mainHome.posts;

public class PostsItem {
    private String nickName;
    private String comment;
    private String timeAgo;

    public PostsItem(String nickName, String comment, String timeAgo){
        this.nickName = nickName;
        this.comment = comment;
        this.timeAgo = timeAgo;
    }

    public String getNickName() {
        return nickName;
    }

    public String getComment() {
        return comment;
    }

    public String getTimeAgo() {
        return timeAgo;
    }
}
