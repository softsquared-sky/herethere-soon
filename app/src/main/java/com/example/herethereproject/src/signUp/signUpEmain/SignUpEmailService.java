package com.example.herethereproject.src.signUp.signUpEmain;

        import com.example.herethereproject.src.signUp.signUpInterfaces.SignUpActivityView;
        import com.example.herethereproject.src.signUp.signUpInterfaces.SignUpRetrofitInterface;
        import com.example.herethereproject.src.signUp.signUpModels.SignUpBody;
import com.example.herethereproject.src.signUp.signUpModels.SignUpRegionResponse;



        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        import static com.example.herethereproject.src.ApplicationClass.getRetrofit;


class SignUpEmailService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpEmailService(final SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
    }


    void postUser(int reqType, String email){
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);

        final SignUpBody signUpBody = new SignUpBody(reqType, email, null,null, null, null, null, null, null, null);


        signUpRetrofitInterface.postUser(signUpBody).enqueue(new Callback<SignUpRegionResponse>() {
            @Override
            public void onResponse(Call<SignUpRegionResponse> call, Response<SignUpRegionResponse> response) {
                final SignUpRegionResponse signUpRegionResponse = response.body();
                if (signUpRegionResponse == null) {
                    mSignUpActivityView.validateFailure("null");
                    return;
                }
                mSignUpActivityView.validateSuccessPost(signUpRegionResponse.getIsSuccess(), signUpRegionResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpRegionResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure("fail");
            }
        });
    }
}
