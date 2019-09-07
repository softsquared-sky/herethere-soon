package com.example.herethereproject.src.main.mainHome.postsInterfaces;

import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsNoResponse;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;

import java.util.List;

public interface MainActivityPostsView {

    void validateSuccess(String message, List<MainPostsResponse.Data> result, boolean isSuccess);

    void validateSuccessPostNo(String message, List<MainPostsNoResponse.Result> result, boolean isSuccess);

    void validateSuccessHeart(boolean isSccess);

    void validateFailure(String message);
}
