package com.example.herethereproject.src.main.mainHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.herethereproject.R;
import com.example.herethereproject.src.main.postsModels.MainPostsResponse;

import java.util.List;

public class MainHomePictureAdapter extends RecyclerView.Adapter<MainHomePictureAdapter.HomeViewHolder>{

    private MainHomeItem mMainHomeItem;
    private List<MainPostsResponse.Data.Picture> mPictureList;

    public MainHomePictureAdapter(List<MainPostsResponse.Data.Picture> pictureList){
        this.mPictureList = pictureList;
    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_home_picture, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        //System.out.println(mPictureList.get(0).getPostPicture());
        if(mPictureList.get(0).getPostPicture() != "") {
            holder.onBind(mPictureList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPictureList.size();
    }

    /*
    void addItem(MainHomePictureItem mainHomePictureItem){
        mPictureList.add(mainHomePictureItem);
    }
    */

    class HomeViewHolder extends RecyclerView.ViewHolder{

        private ImageButton postPictureImageButton;


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);


            postPictureImageButton =itemView.findViewById(R.id.btn_main_home_picture);


        }

        void onBind(MainPostsResponse.Data.Picture postPicture) {
            Glide.with(itemView)
                    .load("https://firebasestorage.googleapis.com/v0/b/herethere-soon.appspot.com/o/picture%2F20190920_2138.jpg?alt=media&token=c733fbfa-413e-4367-9ab9-e00f1e74364f")
//                    .placeholder()
//                    .error(R.drawable.imagenotfound)
                    .into(postPictureImageButton);
            //postPictureImageButton.setImageURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/herethere-soon.appspot.com/o/picture%2F20190920_2138.jpg?alt=media&token=c733fbfa-413e-4367-9ab9-e00f1e74364f"));
        }
    }
}