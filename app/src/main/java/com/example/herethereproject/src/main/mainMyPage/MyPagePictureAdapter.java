package com.example.herethereproject.src.main.mainMyPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.herethereproject.R;
import com.example.herethereproject.src.main.mainHome.MainHomeFragment;
import com.example.herethereproject.src.main.mainHome.MainHomePictureItem;

import java.util.ArrayList;

public class MyPagePictureAdapter extends RecyclerView.Adapter<MyPagePictureAdapter.HomeViewHolder> {

    public ArrayList<MainHomePictureItem> mPictureList;


    private MainHomeFragment mMainHomeFragment = new MainHomeFragment();

    public MyPagePictureAdapter(ArrayList<MainHomePictureItem> pictureList){
        this.mPictureList = pictureList;
    }



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_mypage_picture, parent, false);



        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {



        holder.onBind(mPictureList.get(position));
    }


    @Override
    public int getItemCount() {
        return mPictureList.size();
    }

    void addItem(MainHomePictureItem mainHomePictureItem){
        mPictureList.add(mainHomePictureItem);
    }





    class HomeViewHolder extends RecyclerView.ViewHolder{
        private ImageButton myPictureImageButton;


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);


            myPictureImageButton =itemView.findViewById(R.id.btn_main_mypage_picture);


        }

        void onBind(MainHomePictureItem mainHomePictureItem) {
            Glide.with(itemView)
                    .load(mainHomePictureItem.getProfilePicture())
                    .into(myPictureImageButton);
        }

    }
}