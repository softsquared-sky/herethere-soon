package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.signUp.SignUpEmail.SignUpEmailActivity;
import com.example.herethereproject.src.signUp.SignUpPrivacy.SignUpPrivacyActivity;

public class SignUpPasswordActivity extends BaseActivity {
    EditText mSignUpPasswordEditText;
    EditText mSignUpPasswordCheckEditText;

    boolean passwordCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_password);


        mSignUpPasswordEditText = findViewById(R.id.et_sign_up_password_input);
        mSignUpPasswordCheckEditText = findViewById(R.id.et_sign_up_password_input_check);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               passwordCheck = checkPassword(mSignUpPasswordEditText.getText().toString());
               passwordCheck = mSignUpPasswordEditText.getText().toString().equals(mSignUpPasswordCheckEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        mSignUpPasswordEditText.addTextChangedListener(textWatcher);
        mSignUpPasswordCheckEditText.addTextChangedListener(textWatcher);


    }

    public void signUpPasswordOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_password_back:
                Intent backIntent = new Intent(getApplicationContext(), SignUpEmailActivity.class);
                startActivity(backIntent);
                break;

            case R.id.btn_sign_up_password_complete:
                if(passwordCheck){
                    Intent email_intent = getIntent();
                    Intent start_privacy_intent = new Intent(getApplicationContext(), SignUpPrivacyActivity.class);
                    start_privacy_intent.putExtra("email", email_intent.getStringExtra("email"));
                    start_privacy_intent.putExtra("password", mSignUpPasswordEditText.getText().toString());
                    startActivity(start_privacy_intent);
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
