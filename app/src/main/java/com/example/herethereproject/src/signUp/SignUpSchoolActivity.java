package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;

public class SignUpSchoolActivity extends BaseActivity {
    EditText mSignUpSchoolEditText;
    boolean mSchoolCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_school);

        mSignUpSchoolEditText = findViewById(R.id.et_sign_up_school_input);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mSignUpSchoolEditText.getText().toString().length() != 0){
                    mSchoolCheck = true;
                } else {
                    mSchoolCheck = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        mSignUpSchoolEditText.addTextChangedListener(textWatcher);


    }

    public void signUpSchoolOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_school_back:
                Intent backIntent = new Intent(getApplicationContext(), SignUpPrivacyActivity.class);
                startActivity(backIntent);
                break;
            case R.id.btn_sign_up_school_complete:
                if(mSchoolCheck){
                    Intent privacyIntent = getIntent();
                    Intent startPictureIntent = new Intent(getApplicationContext(), SignUpPictureActivity.class);
                    startPictureIntent.putExtra("email", privacyIntent.getStringExtra("email"));
                    startPictureIntent.putExtra("password", privacyIntent.getStringExtra("password"));
                    startPictureIntent.putExtra("name", privacyIntent.getStringExtra("name"));
                    startPictureIntent.putExtra("birth", privacyIntent.getStringExtra("birth"));
                    startPictureIntent.putExtra("nick", privacyIntent.getStringExtra("nick"));
                    startPictureIntent.putExtra("school", mSignUpSchoolEditText.getText().toString());
                    startActivity(startPictureIntent);
                    break;
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
