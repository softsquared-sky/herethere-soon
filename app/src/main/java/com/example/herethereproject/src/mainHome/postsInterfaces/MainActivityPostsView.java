package com.example.herethereproject.src.mainHome.postsInterfaces;

import com.example.herethereproject.src.mainHome.postsModels.MainPostsResponse;

import java.util.List;

public interface MainActivityPostsView {

    void validateSuccess(String message, List<MainPostsResponse.Data> result, boolean isSuccess);

    void validateSuccessHeart(boolean isSccess);

    void validateFailure(String message);
}
