package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;

public class SignUpPrivacyActivity extends BaseActivity {

    EditText mSignUpNameEditText;
    EditText mSignUpBirthEditText;
    EditText mSignUpNickEditText;

    boolean privacyCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_privacy);

        mSignUpNameEditText = findViewById(R.id.et_sign_up_privacy_name);
        mSignUpBirthEditText = findViewById(R.id.et_sign_up_privacy_birth);
        mSignUpNickEditText = findViewById(R.id.et_sign_up_privacy_nick);

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

    public void signUpPrivacyOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_privacy_back:
                Intent backIntent = new Intent(getApplicationContext(), SignUpPasswordActivity.class);
                startActivity(backIntent);
                break;
            case R.id.btn_sign_up_privacy_complete:
                if(privacyCheck){
                    Intent password_intent = getIntent();
                    Intent start_school_intent = new Intent(getApplicationContext(), SignUpSchoolActivity.class);

                    start_school_intent.putExtra("email", password_intent.getStringExtra("email"));
                    start_school_intent.putExtra("password", password_intent.getStringExtra("password"));
                    start_school_intent.putExtra("name", mSignUpNameEditText.getText().toString());
                    start_school_intent.putExtra("birth", mSignUpBirthEditText.getText().toString());
                    start_school_intent.putExtra("nick", mSignUpNickEditText.getText().toString());

                    startActivity(start_school_intent);
                    finish();
                    break;
                } else {
                    showCustomToast(getString(R.string.toast_wrong_message));
                    break;
                }
            default:
                break;
        }
    }
}
