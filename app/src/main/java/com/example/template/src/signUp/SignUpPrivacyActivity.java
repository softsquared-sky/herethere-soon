package com.example.template.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;

public class SignUpPrivacyActivity extends BaseActivity {

    EditText et_name;
    EditText et_birth;
    EditText et_nick;

    boolean privacyCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_privacy);

        et_name = findViewById(R.id.et_sign_up_privacy_name);
        et_birth = findViewById(R.id.et_sign_up_privacy_birth);
        et_nick = findViewById(R.id.et_sign_up_privacy_nick);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_name.getText().toString().length() > 0 && et_birth.getText().toString().length() == 8 && et_nick.getText().toString().length() > 0){
                    privacyCheck = true;
                    System.out.println("privacy success");
                } else {
                    privacyCheck = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        et_name.addTextChangedListener(textWatcher);
        et_birth.addTextChangedListener(textWatcher);
        et_nick.addTextChangedListener(textWatcher);

    }

    public void signUpPrivacyOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_privacy_back:
                finish();
                break;
            case R.id.btn_sign_up_privacy_complete:
                if(privacyCheck){
                    Intent password_intent = getIntent();
                    Intent start_picture_intent = new Intent(getApplicationContext(), SignUpPictureActivity.class);
                    start_picture_intent.putExtra("email", password_intent.getStringExtra("email"));
                    start_picture_intent.putExtra("password", password_intent.getStringExtra("password"));
                    start_picture_intent.putExtra("name", et_name.getText().toString());
                    start_picture_intent.putExtra("birth", et_birth.getText().toString());
                    start_picture_intent.putExtra("nick", et_nick.getText().toString());
                    startActivity(start_picture_intent);
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
