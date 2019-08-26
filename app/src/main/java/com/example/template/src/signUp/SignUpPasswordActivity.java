package com.example.template.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;

public class SignUpPasswordActivity extends BaseActivity {
    EditText et_sign_up_password;
    EditText et_sign_up_password_check;

    boolean passwordCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_password);

        et_sign_up_password = findViewById(R.id.et_sign_up_password_input);
        et_sign_up_password_check = findViewById(R.id.et_sign_up_password_input_check);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(checkPassword(et_sign_up_password.getText().toString())){

                    passwordCheck = true;
                } else {
                    passwordCheck = false;
                }
                if(et_sign_up_password.getText().toString().equals(et_sign_up_password_check.getText().toString())){
                    passwordCheck = true;
                } else {
                    passwordCheck = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        et_sign_up_password.addTextChangedListener(textWatcher);
        et_sign_up_password_check.addTextChangedListener(textWatcher);


    }

    public void signUpPasswordOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_password_back:
                finish();
                break;

            case R.id.btn_sign_up_password_complete:
                if(passwordCheck){
                    Intent email_intent = getIntent();
                    Intent start_privacy_intent = new Intent(getApplicationContext(), SignUpPrivacyActivity.class);
                    start_privacy_intent.putExtra("email", email_intent.getStringExtra("email"));
                    start_privacy_intent.putExtra("password", et_sign_up_password.getText().toString());
                    startActivity(start_privacy_intent);
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
