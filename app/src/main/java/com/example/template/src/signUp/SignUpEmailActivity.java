package com.example.template.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;

public class SignUpEmailActivity extends BaseActivity {
    EditText et_sign_up_email;
    boolean emailCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        et_sign_up_email = findViewById(R.id.et_sign_up_email_input);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(checkEmail(et_sign_up_email.getText().toString())){
                    System.out.println("sign success");
                    emailCheck = true;
                } else {
                    emailCheck = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        et_sign_up_email.addTextChangedListener(textWatcher);


    }

    public void signUpEailOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_email_back:
                finish();
                break;

            case R.id.btn_sign_up_email_complete:
                if(emailCheck){
                    //System.out.println("click success");
                    Intent start_sign_up_password_intent = new Intent(getApplicationContext(), SignUpPasswordActivity.class);
                    start_sign_up_password_intent.putExtra("email", et_sign_up_email.getText().toString());
                    startActivity(start_sign_up_password_intent);
                    finish();
                    break;
                    //비밀번호 입력으로 전환
                } else{
                    showCustomToast(getString(R.string.toast_wrong_message));
                    break;
                }
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
