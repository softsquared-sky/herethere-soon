package com.example.herethereproject.src.signUp.SignUpPrivacy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.signUp.SignUpInterfaces.SignUpActivityView;
import com.example.herethereproject.src.signUp.SignUpModels.SignUpRegionResponse;
import com.example.herethereproject.src.signUp.SignUpPasswordActivity;
import com.example.herethereproject.src.signUp.SignUpSchoolActivity;

import java.util.List;

public class SignUpPrivacyActivity extends BaseActivity implements SignUpActivityView {

    EditText mSignUpNameEditText;
    EditText mSignUpBirthEditText;
    EditText mSignUpNickEditText;
    TextView mSignUpNickNameCheckTextView;

    boolean privacyCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_privacy);

        mSignUpNameEditText = findViewById(R.id.et_sign_up_privacy_name);
        mSignUpBirthEditText = findViewById(R.id.et_sign_up_privacy_birth);
        mSignUpNickEditText = findViewById(R.id.et_sign_up_privacy_nick);
        mSignUpNickNameCheckTextView = findViewById(R.id.tv_sign_up_privacy_check);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mSignUpNameEditText.getText().toString().length() > 0 && mSignUpBirthEditText.getText().toString().length() == 6 && mSignUpNickEditText.getText().toString().length() > 0){
                    privacyCheck = true;
                    System.out.println("privacy success");
                } else {
                    privacyCheck = false;
                }
                //null이 나올 수 있음
                //@Nullable
                //방어코드
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        mSignUpNameEditText.addTextChangedListener(textWatcher);
        mSignUpBirthEditText.addTextChangedListener(textWatcher);
        mSignUpNickEditText.addTextChangedListener(textWatcher);

    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(getApplicationContext(), SignUpPasswordActivity.class);
        startActivity(backIntent);
        finish();
    }

    public void signUpPrivacyOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_privacy_back:
                onBackPressed();
                break;
            case R.id.btn_sign_up_privacy_complete:
                if(mSignUpNameEditText.getText().toString().length() <= 0) {
                    mSignUpNickNameCheckTextView.setText("이름을 입력해주세요.");

                    break;
                }
                if(mSignUpBirthEditText.getText().toString().length() != 6){
                    mSignUpNickNameCheckTextView.setText("생년월일을 정확히 입력해주세요.");
                    break;
                }

                if(mSignUpNickEditText.getText().toString().length() <= 0){
                    mSignUpNickNameCheckTextView.setText("닉네임을 입력해주세요.");
                    break;
                }

                tryPostUser();

            default:
                break;
        }
    }

    private  void tryPostUser() {

        showProgressDialog();
        final SignUpPrivacyService signUpPrivacyService = new SignUpPrivacyService(this);

        int reqType = 1;
        String nickName = mSignUpNickEditText.getText().toString();
        signUpPrivacyService.postUser(reqType, nickName);
        //signUpRegionService.postUser("randy3456@naver.com", "q1w2e3", "홍순재", 960603, "asdf321", "asdf.jpg", "한국항", locationNo);
    }


    @Override
    public void validateSuccessPost(boolean success, String message) {
        hideProgressDialog();
        if(message != null) {
            mSignUpNickNameCheckTextView.setText(message);
        } else {
            showCustomToast(message);
        }

    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();

        Intent password_intent = getIntent();
        Intent start_school_intent = new Intent(getApplicationContext(), SignUpSchoolActivity.class);

        start_school_intent.putExtra("email", password_intent.getStringExtra("email"));
        start_school_intent.putExtra("password", password_intent.getStringExtra("password"));
        start_school_intent.putExtra("name", mSignUpNameEditText.getText().toString());
        start_school_intent.putExtra("birth", mSignUpBirthEditText.getText().toString());
        start_school_intent.putExtra("nick", mSignUpNickEditText.getText().toString());

        startActivity(start_school_intent);
        finish();

    }

    @Override
    public void validateSuccessGet(List<SignUpRegionResponse.data> result) {

    }
}
