package com.example.herethereproject.src.main;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.main.MainInterfaces.MainActivityView;
import com.example.herethereproject.src.main.mainHome.MainHomeFragment;

public class MainActivity extends BaseActivity implements MainActivityView {

    private int fragmentCheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            case R.id.btn_main_bookmark:
                //구현하지 않는다.
                fragmentCheck = 1;
                break;

            case  R.id.btn_main_message:
                //구현하지 않는다.
                fragmentCheck = 2;
                break;

            case R.id.btn_main_myPage:
                fragmentCheck = 3;
                switchFragment();
                break;

            case R.id.btn_main_ham:
                if(!drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                break;

            case R.id.btn_main_filter:
                break;

            case R.id.btn_main_search:
                break;

            case R.id.btn_ham_exit:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
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

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
