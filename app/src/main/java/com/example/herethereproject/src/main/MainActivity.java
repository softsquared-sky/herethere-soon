package com.example.herethereproject.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.login.LoginActivity;
import com.example.herethereproject.src.main.MainInterfaces.MainActivityView;
import com.example.herethereproject.src.mainHome.MainHomeFragment;
import com.example.herethereproject.src.main.userInterfaces.MainActivityUserView;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;
import com.example.herethereproject.src.write.WriteActivity;

import static com.example.herethereproject.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView, MainActivityUserView {

    private int fragmentCheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent loginIntent = getIntent();
//        tryUserProfileGet(loginIntent.getStringExtra("email"));
        tryUserProfileGet("randy3456@naver.com");
        FragmentManager mainFragment = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mainFragment.beginTransaction();
        fragmentTransaction.add(R.id.main_frame, new MainHomeFragment());
        fragmentTransaction.commit();

    }

    public void MainOnClick(View view) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_main);
        switch (view.getId()) {
            case R.id.btn_main_home:
                fragmentCheck = 0;
                switchFragment();
                break;

            case R.id.btn_main_myPage:
                fragmentCheck = 3;
                switchFragment();
                break;

            case R.id.btn_main_write:
                Intent startWriteIntent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(startWriteIntent);
                break;

            case R.id.btn_main_ham:
                if(!drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                break;

            case R.id.btn_ham_exit:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                break;

            case R.id.constraint_ham_logout:
                Intent startLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.clear();
                startActivity(startLoginIntent);
                break;
        }
    }

    public void switchFragment(){
        Fragment fragment = null;

        switch (fragmentCheck){
            case 0:
                fragment = new MainHomeFragment();
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                fragment = new MainMyPageFragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }



    public void tryUserProfileGet(String email){
        showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getUserProfile(email);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
//MainPosts



    @Override
    public void validateUserProfileSuccess(MainUserProfileResponse.Result result)
    {
        TextView hamNickNameTextView = findViewById(R.id.tv_ham_nickName);
        TextView hamEmailTextView = findViewById(R.id.tv_ham_email);
        TextView hamSchoolTextView = findViewById(R.id.tv_ham_school);
//        TextView hamRegionTextView = findViewById(R.id.tv_ham_region);

        hamNickNameTextView.setText(result.getNickName());
        hamEmailTextView.setText(result.getEmail());
        hamSchoolTextView.setText(result.getSchoolName());
//        hamRegionTextView.setText(result.);
        hideProgressDialog();
    }

    @Override
    public void validateUserProfileFailure(String message) {
        hideProgressDialog();
    }
    //MainUserProfile
}
