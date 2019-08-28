package com.example.template.src;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.herethereproject.R;

import java.util.ArrayList;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    public ProgressDialog mProgressDialog;
    public ArrayList<String> sign_up_list = new ArrayList<>();


    //올바른 비밀번호 입력 확인(대소문자 포함)
    public boolean checkPassword(String password){
        boolean checkNumber = false;
        boolean checkEnglish = false;
        if(!(password.length() >= 6 && password.length() <=10)){
            return false;
        } else {
            for(int i = 0; i < password.length(); i++){
                if(checkNumber && checkEnglish){
                    return true;
                }
                if(((int)password.charAt(i) >= 97 && (int)password.charAt(i) <= 122) || (int)password.charAt(i) >=65 && (int)password.charAt(i) <= 90){
                    //영문이 포함되 있을 때(대소문자)
                    checkEnglish = true;
                } else if((int)password.charAt(i) >=48 && (int)password.charAt(i) <= 57){
                    //숫자가 포함되있을 때
                    checkNumber = true;
                } else {
                    //영어 숫자 외의 문자가 있을 때
                    return false;
                }
            }
            return false;
        }
    }
    //올바른 이메일 입력 확인
    public  boolean checkEmail(String email){
        if(!email.contains("@")){
            return false;
        } else {
            if(!(email.charAt(email.length()-4) == '.')){
                return false;
            } else {
                return true;
            }
        }
    }

    public void showCustomToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}