package com.example.herethereproject.src.main.mainHome;

import android.app.ProgressDialog;
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
import com.example.herethereproject.src.main.MainInterfaces.MainActivityView;

import java.util.ArrayList;

public class MainHomeFragment extends Fragment implements MainActivityView {

    private ProgressDialog mDialog;

    public ArrayList<MainHomeItem> data = new ArrayList<>();


    public MainHomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_home, container, false);
        RecyclerView homeRecyclerView = rootView.findViewById(R.id.list_home);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        homeRecyclerView.setLayoutManager(layoutManager);

        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage(getString(R.string.loading));
        mDialog.setIndeterminate(true);
        //질문필요

        tryGetPosts();

        int profilePicture = R.drawable.ic_main_profile;
        String nickName = "hsj321";
        String region = "영국";
        String time = "2";
        String like = "10";
        String bookMark = "10";
        String comment = "10";

        MainHomeItem mainHomeItem = new MainHomeItem(profilePicture, nickName, region, time, like, bookMark, comment);

        data.add(mainHomeItem);



        //System.out.println(data.get(0).getNickName());

        MainHomeAdapter homeAdapter = new MainHomeAdapter(data);


        data.add(mainHomeItem);

        homeAdapter.addItem(mainHomeItem);


        homeRecyclerView.setAdapter(homeAdapter);
        return rootView;
    }


    private void tryGetPosts(){
        mDialog.show();
        //BaseActivity baseActivity = new BaseActivity();
         //baseActivity.showFragmentProgressDialog(getActivity().getApplicationContext());
        //base.showProgressDialog();
        final MainHomeService mainHomeService = new MainHomeService(this);
        mainHomeService.getPosts();

    }

    @Override
    public void validateSuccess(String text) {
        mDialog.hide();
        System.out.println(text);
    }

    @Override
    public void validateFailure(String message) {
        mDialog.hide();
        System.out.println(message);

    }
}
