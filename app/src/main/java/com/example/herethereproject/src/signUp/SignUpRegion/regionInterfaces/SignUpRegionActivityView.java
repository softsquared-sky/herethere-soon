package com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import java.util.List;

public interface SignUpRegionActivityView {

    void validateSuccessGet(List<SignUpRegionResponse.data> result);

    void validateSuccessPost(boolean success, String message);

    void validateFailure(String message);
}
