package com.gofit;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anarasim on 2/12/2017.
 */

public class MyAdapter extends android.support.v7.widget.RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List selectedList =null;
    StorageReference storageRef;
    Context context;
    public void addItem(int position) {

    }



    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }
    Boolean selected = false;
    Boolean deletion = false;

    private int lastPosition = -1;

    OnItemClickListener listener;
    static OnItemClickListener mItemClickListener ;
    public MyAdapter(ArrayList list,Context context) {
        selectedList = list;
        storageRef = FirebaseStorage.getInstance().getReference();
        this.context = context;
    }


    public void setOnItemClickListener(final OnItemClickListener listener, FragmentActivity activity)
    {
        mItemClickListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view =LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HashMap map = (HashMap) selectedList.get(position);

        Picasso.with(context).load((String) map.get("imageurl")).into(holder.image);
        //holder.image.setImageResource(R.drawable.img1);
    }



    @Override
    public int getItemCount() {
       return selectedList.size();
    }
    @Override
    public int getItemViewType ( int position ){
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView description;

        public RatingBar rating;
        public TextView ratingText;
        public ImageView dot;
        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.img1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(image,getPosition());
                }
            });

        }

        private final class MyClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view){
//                int id = (Integer) view.getId();
//                HashMap movie = data.getItem(getAdapterPosition());
//                mListener.onItemSelected(movie, view ,getAdapterPosition());
            }
        }
    }

}
