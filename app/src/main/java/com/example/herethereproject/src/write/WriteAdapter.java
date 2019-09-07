package com.example.herethereproject.src.write;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;

import java.util.ArrayList;

public class WriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<WriteItem> mWriteItems;

    public WriteAdapter(ArrayList<WriteItem> writeItems){
        this.mWriteItems = writeItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        System.out.println(viewType);
        if(viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_write_plus, parent, false);
            return new PlusViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_write, parent, false);
            return new OtherViewHolder(view);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PlusViewHolder) {
            System.out.println(position);
            ((PlusViewHolder) holder).onBind(mWriteItems.get(position));
        } else if(holder instanceof OtherViewHolder) {
            System.out.println(position);
            ((OtherViewHolder) holder).onBind(mWriteItems.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return mWriteItems.size();
    }

    class PlusViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        ImageView imageView1;


        public PlusViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_write_plus);
            imageView = itemView.findViewById(R.id.iv_write_plus);
            imageView1 = itemView.findViewById(R.id.iv_write_background_plus);

        }


        void onBind(WriteItem writeItem){

        }

    }

    class OtherViewHolder extends RecyclerView.ViewHolder{



        public OtherViewHolder(@NonNull View itemView) {
            super(itemView);


        }


        void onBind(WriteItem writeItem){


        }

    }
}
