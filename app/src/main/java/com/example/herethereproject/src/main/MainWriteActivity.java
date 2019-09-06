package com.example.herethereproject.src.main;

import android.os.Bundle;
import android.view.View;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.main.MainInterfaces.MainActivityView;

public class MainWriteActivity extends BaseActivity implements MainActivityView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
    }

    public void MainWriteOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write_arrow:
                finish();
                break;
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
