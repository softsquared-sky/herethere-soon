package com.example.herethereproject.src.main.postsInterfaces;

import com.example.herethereproject.src.main.postsModels.MainPostsResponse;

import java.util.List;

public interface MainActivityPostsView {

    void validateSuccess(String message, List<MainPostsResponse.Data> result, boolean isSuccess);

    void validateSuccessHeart(boolean isSccess);

    void validateFailure(String message);
}
