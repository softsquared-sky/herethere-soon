package com.example.herethereproject.src.main.mainHome.posts.commentInterfaces;

import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentGetResponse;

import java.util.List;

public interface CommentActivityView {

    void validateSuccessCommentPost(String message, boolean isSuccess);

    void validateSuccessCommentGet(String message, List<CommentGetResponse.Result> result, boolean isSuccess);

    void validateFailure(String message);
}
