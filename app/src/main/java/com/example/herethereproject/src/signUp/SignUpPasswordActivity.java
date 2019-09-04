package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.signUp.SignUpEmail.SignUpEmailActivity;
import com.example.herethereproject.src.signUp.SignUpPrivacy.SignUpPrivacyActivity;

public class SignUpPasswordActivity extends BaseActivity {
    EditText mSignUpPasswordEditText;
    EditText mSignUpPasswordCheckEditText;
    TextView mSignUpPasswordCheckTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_password);


        mSignUpPasswordEditText = findViewById(R.id.et_sign_up_password_input);
        mSignUpPasswordCheckEditText = findViewById(R.id.et_sign_up_password_input_check);
        mSignUpPasswordCheckTextView = findViewById(R.id.tv_sign_up_password_check);


    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(getApplicationContext(), SignUpEmailActivity.class);
        startActivity(backIntent);
        finish();
    }

    public void signUpPasswordOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_password_back:
                onBackPressed();
                break;

            case R.id.btn_sign_up_password_complete:
                if(!checkPassword(mSignUpPasswordEditText.getText().toString())){
                    if(mSignUpPasswordCheckEditText.getText().toString().length() != 0){
                        mSignUpPasswordCheckTextView.setText("비밀번호 형식이 잘못되었습니다.");
                        break;
                    } else{
                        mSignUpPasswordCheckTextView.setText("비밀번호 확인을 입력해주세요.");
                        break;
                    }

                }
                if(!mSignUpPasswordEditText.getText().toString().equals(mSignUpPasswordCheckEditText.getText().toString())){
                    mSignUpPasswordCheckTextView.setText("비밀번호가 일치하지 않습니다.");
                    break;
                }
                if(checkPassword(mSignUpPasswordEditText.getText().toString()) && mSignUpPasswordEditText.getText().toString().equals(mSignUpPasswordCheckEditText.getText().toString())){
                    Intent email_intent = getIntent();
                    Intent start_privacy_intent = new Intent(getApplicationContext(), SignUpPrivacyActivity.class);
                    start_privacy_intent.putExtra("email", email_intent.getStringExtra("email"));
                    start_privacy_intent.putExtra("password", mSignUpPasswordEditText.getText().toString());
                    startActivity(start_privacy_intent);
                    finish();
                    break;
                }
            default:
                break;
        }
    }
}
