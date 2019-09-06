package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.login.LoginActivity;

public class SignUpFinishActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_finish);

        TextView nictNameTextView = findViewById(R.id.tv_sign_up_finish_please_input_picture);

        Intent regionIntent = getIntent();
        String nickName = regionIntent.getStringExtra("nick");
        String sourceString = "<b>" + nickName + "</b>" + getString(R.string.finish_sign_up_first) + "<br />" + getString(R.string.finish_sign_up_second);
        nictNameTextView.setText(Html.fromHtml(sourceString));
    }

    public void signUpFinishOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_finish_complete:
                Intent startLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(startLoginIntent);
                finish();
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
