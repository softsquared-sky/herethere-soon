package com.example.herethereproject.src.main.mainHome;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.main.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.main.postsModels.MainPostsResponse;

import java.util.ArrayList;
import java.util.List;

public class MainHomeAdapter extends RecyclerView.Adapter<MainHomeAdapter.HomeViewHolder> implements MainActivityPostsView {

    public ArrayList<MainHomeItem> homeList;

    private RecyclerView.LayoutManager mLayoutManager;

    private MainHomeFragment mMainHomeFragment = new MainHomeFragment();

    public MainHomeAdapter(ArrayList<MainHomeItem> homeList){
        this.homeList = homeList;


    }



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_home, parent, false);

        mLayoutManager = new LinearLayoutManager(parent.getContext(), RecyclerView.HORIZONTAL, true);



        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {
        holder.onBind(homeList.get(position));

        final ImageView heartImageView = holder.itemView.findViewById(R.id.iv_main_home_heart);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_main_home_heart:
                        mMainHomeFragment.tryPostHeart(homeList.get(position).getPostNo());
                        heartImageView.setImageResource(R.drawable.ic_heart_fill);
                        holder.onBindHeart(homeList.get(position));
                        break;
                }
            }
        };

        LinearLayout linearLayout = holder.itemView.findViewById(R.id.btn_main_home_heart);
        linearLayout.setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return homeList.size();
    }

    void addItem(MainHomeItem mainHomeItem){
        homeList.add(mainHomeItem);
    }



    @Override
    public void validateSuccess(String message, List<MainPostsResponse.Data> result, boolean isSuccess) {
        
    }

    @Override
    public void validateSuccessHeart(boolean isSccess) {
        System.out.println(isSccess);
    }

    @Override
    public void validateFailure(String message) {

    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        private ImageView userPictureImageView;
        private TextView nickNameTextView;
        private TextView regionTimeTextView;
        private TextView postContentsTextView;
        private TextView heartTextView;
        private TextView commentTextView;
        private RecyclerView pictureView;

        //private LinearLayout heartLinearLayout;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            userPictureImageView = itemView.findViewById(R.id.iv_main_home_profile);
            nickNameTextView = itemView.findViewById(R.id.tv_main_home_profile_name);
            regionTimeTextView = itemView.findViewById(R.id.tv_main_home_region);
            postContentsTextView = itemView.findViewById(R.id.tv_main_home_line);
            heartTextView = itemView.findViewById(R.id.tv_main_home_heart);
            commentTextView = itemView.findViewById(R.id.tv_main_home_comment);
            pictureView = itemView.findViewById(R.id.recyclerview_main_home_picture);

            //heartLinearLayout = itemView.findViewById(R.id.btn_main_home_heart);


            pictureView.setLayoutManager(mLayoutManager);



        }

        void onBindHeart(MainHomeItem mainHomeItem){
            mainHomeItem.setHeart();
            heartTextView.setText(Integer.toString(mainHomeItem.getHeart()));
            heartTextView.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.heartColor));
        }

        void onBind(MainHomeItem mainHomeItem){


            if(!mainHomeItem.getPictureList().get(0).getPostPicture().equals("")){
                MainHomePictureAdapter adapter = new MainHomePictureAdapter(mainHomeItem.getPictureList());
                pictureView.setAdapter(adapter);
                pictureView.setVisibility(View.VISIBLE);
            }


            if(!mainHomeItem.getUserPicture().equals(""))
                userPictureImageView.setImageURI(Uri.parse(mainHomeItem.getUserPicture()));
            nickNameTextView.setText(mainHomeItem.getNickName());
            String regionTime = mainHomeItem.getPostLocation() + "∙" + mainHomeItem.getTimeAgo();
            regionTimeTextView.setText(regionTime);
            postContentsTextView.setText(mainHomeItem.getPostContents());
            heartTextView.setText(Integer.toString(mainHomeItem.getHeart()));
            commentTextView.setText(mainHomeItem.getComment());

        }

    }
}