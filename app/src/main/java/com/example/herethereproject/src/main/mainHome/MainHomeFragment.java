package com.example.herethereproject.src.main.mainHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.main.MainInterfaces.MainActivityView;
import com.example.herethereproject.src.main.postsInterfaces.MainActivityPostsView;

import java.util.ArrayList;

public class MainHomeFragment extends Fragment implements MainActivityView {

    public MainHomeAdapter homeAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView homeRecyclerView;
    public ArrayList<MainHomeItem> data = new ArrayList<>();

    public MainHomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_home, container, false);
        homeRecyclerView = rootView.findViewById(R.id.list_home);
        layoutManager = new LinearLayoutManager(getActivity());
        homeRecyclerView.setLayoutManager(layoutManager);

        //tryGetPosts();

        int profilePicture = R.drawable.ic_main_profile;
        String nickName = "hsj321";
        String region = "영국";
        String time = "2";
        String like = "10";
        String bookMark = "10";
        String comment = "10";

        MainHomeItem mainHomeItem = new MainHomeItem(profilePicture, nickName, region, time, like, bookMark, comment);

        data.add(mainHomeItem);



        System.out.println(data.get(0).getNickName());

        homeAdapter = new MainHomeAdapter(data);

        System.out.println(homeAdapter.homeList.get(0).getNickName());

        data.add(mainHomeItem);

        homeAdapter.addItem(mainHomeItem);


        homeRecyclerView.setAdapter(homeAdapter);
        System.out.print(homeAdapter.getItemCount());
        return rootView;
    }

    public void getDate(){


    }

    public void tryGetPosts(){
        BaseActivity baseActivity = new BaseActivity();
        baseActivity.showProgressDialog();
        final MainHomeService mainHomeService = new MainHomeService((MainActivityPostsView) this);
        mainHomeService.getPosts();

    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
