package com.example.herethereproject.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.login.LoginActivity;
import com.example.herethereproject.src.main.MainInterfaces.MainActivityView;
import com.example.herethereproject.src.main.mainHome.MainHomeFragment;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.mainMyPage.MyPageFragment;
import com.example.herethereproject.src.main.userInterfaces.MainActivityUserView;
import com.example.herethereproject.src.main.userModels.MainUserPictureResponse;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;
import com.example.herethereproject.src.write.WriteActivity;

import java.util.List;

import static com.example.herethereproject.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView, MainActivityUserView {

    private TextView mTopTextVeiw;
    private ImageButton mTopButton;
    private TextView mHamNickNameTextView;
    private String mMyEmail;

    private int fragmentCheck = 0;

    private FragmentManager mManager;

    private Fragment homeFragment, myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopButton = findViewById(R.id.btn_main_ham);
        mTopTextVeiw = findViewById(R.id.tv_main_top);
        mHamNickNameTextView = findViewById(R.id.tv_ham_nickName);

        Intent loginIntent = getIntent();
        mMyEmail = loginIntent.getStringExtra("email");
//        tryUserProfileGet(loginIntent.getStringExtra("email"));
        tryUserProfileGet("randy3456@naver.com");
        mManager = getSupportFragmentManager();

        homeFragment = new MainHomeFragment();

        mManager.beginTransaction().replace(R.id.main_frame, homeFragment).commit();

    }

    public void MainOnClick(View view) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_main);
        switch (view.getId()) {
            case R.id.btn_main_home:
                fragmentCheck = 0;
                switchFragment();
                break;

            case R.id.btn_main_myPage:
                fragmentCheck = 1;
                switchFragment();
                break;

            case R.id.btn_main_write:
                Intent startWriteIntent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(startWriteIntent);
                break;

            case R.id.btn_main_ham:
                if(fragmentCheck == 1){
                    fragmentCheck = 0;
                    switchFragment();
                } else {
                    if(!drawerLayout.isDrawerOpen(Gravity.LEFT)){
                        drawerLayout.openDrawer(Gravity.LEFT);
                    }
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
                startLoginIntent.putExtra("logout", true);
                startActivity(startLoginIntent);
                break;
        }
    }

    public void switchFragment(){
        Bundle bundle = new Bundle();
        switch (fragmentCheck){
            case 0:
                if(homeFragment == null){
                    homeFragment = new MainHomeFragment();

                    bundle.putString("email", mMyEmail);
                    homeFragment.setArguments(bundle);
                    mManager.beginTransaction().add(R.id.main_frame, homeFragment).commit();
                }

                mTopButton.setImageResource(R.drawable.ic_main_buger);
                mTopTextVeiw.setText(R.string.app_name);

                bundle.putString("email", mMyEmail);
                homeFragment.setArguments(bundle);

                if(homeFragment != null)
                    mManager.beginTransaction().show(homeFragment).commit();
                if(myPageFragment != null)
                    mManager.beginTransaction().hide(myPageFragment).commit();

                break;

            case 1:
                if(myPageFragment == null){
                    myPageFragment = new MyPageFragment();

                    bundle.putString("email", mMyEmail);
                    myPageFragment.setArguments(bundle);
                    mManager.beginTransaction().add(R.id.main_frame, myPageFragment).commit();
                }

                bundle.putString("email", mMyEmail);
                myPageFragment.setArguments(bundle);

                mTopButton.setImageResource(R.drawable.ic_mypage_back);
                mTopTextVeiw.setText(mHamNickNameTextView.getText().toString());

                if(homeFragment != null)
                    mManager.beginTransaction().hide(homeFragment).commit();
                if(myPageFragment != null)
                    mManager.beginTransaction().show(myPageFragment).commit();
                break;
        }


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
        TextView hamEmailTextView = findViewById(R.id.tv_ham_email);
        TextView hamSchoolTextView = findViewById(R.id.tv_ham_school);

        mHamNickNameTextView.setText(result.getNickName());
        hamEmailTextView.setText(result.getEmail());
        hamSchoolTextView.setText(result.getSchoolName());
        hideProgressDialog();
    }

    @Override
    public void validateUserpostsSuccess(String message, List<MainPostsResponse.Data> result, int code) {

    }

    @Override
    public void validateUserPictureSuccess(String message, List<MainUserPictureResponse.Result> result, int code ){

    }


    @Override
    public void validateUserProfileFailure(String message) {
        hideProgressDialog();
    }
    //MainUserProfile
}
