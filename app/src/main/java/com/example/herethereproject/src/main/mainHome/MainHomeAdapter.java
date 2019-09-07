package com.example.herethereproject.src.main.mainHome;

import android.content.Intent;
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
import com.example.herethereproject.src.main.mainHome.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsNoResponse;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.mainHome.posts.PostsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainHomeAdapter extends RecyclerView.Adapter<MainHomeAdapter.HomeViewHolder> implements MainActivityPostsView {

    public ArrayList<MainHomeItem> mHomeList;

    private RecyclerView.LayoutManager mLayoutManager;

    private MainHomeFragment mMainHomeFragment = new MainHomeFragment();

    public MainHomeAdapter(ArrayList<MainHomeItem> mHomeList){
        this.mHomeList = mHomeList;


    }



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_home, parent, false);

        mLayoutManager = new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);



        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {
        holder.onBind(mHomeList.get(position));

        final ImageView heartImageView = holder.itemView.findViewById(R.id.iv_main_home_heart);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_main_home_heart:
                        if(!mHomeList.get(position).heartCheck) {
                            mMainHomeFragment.tryPostHeart(mHomeList.get(position).getPostNo());
                            heartImageView.setImageResource(R.drawable.ic_heart_fill);
                            mHomeList.get(position).heartCheck = true;
                            holder.onBindHeart(mHomeList.get(position));
                        }
                        break;

                    case R.id.tv_main_home_line:
                        Intent startPostsIntent = new Intent(view.getContext(), PostsActivity.class);
                        startPostsIntent.putExtra("postNo", mHomeList.get(position).getPostNo());
                        startPostsIntent.putExtra("heartCheck", mHomeList.get(position).heartCheck);
                        view.getContext().startActivity(startPostsIntent);
                        break;
                }
            }
        };

        LinearLayout linearLayout = holder.itemView.findViewById(R.id.btn_main_home_heart);
        TextView commentTextView = holder.itemView.findViewById(R.id.tv_main_home_line);
        linearLayout.setOnClickListener(listener);
        commentTextView.setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return mHomeList.size();
    }

    void addItem(MainHomeItem mainHomeItem){
        mHomeList.add(mainHomeItem);
    }



    @Override
    public void validateSuccess(String message, List<MainPostsResponse.Data> result, boolean isSuccess) {
        
    }

    @Override
    public void validateSuccessPostNo(String message, List<MainPostsNoResponse.Result> result, boolean isSuccess) {

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
            heartTextView.setEnabled(false);
        }

        void onBind(MainHomeItem mainHomeItem){


            if(!mainHomeItem.getPictureList().get(0).getPostPicture().equals("")){
                ArrayList<MainHomePictureItem> pictureList = new ArrayList<>();
                for(MainPostsResponse.Data.Picture postsResponse : mainHomeItem.getPictureList()){
                    MainHomePictureItem mainHomePictureItem = new MainHomePictureItem(postsResponse.getPostPicture());
                    pictureList.add(mainHomePictureItem);
                }
                MainHomePictureAdapter adapter = new MainHomePictureAdapter(pictureList);
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