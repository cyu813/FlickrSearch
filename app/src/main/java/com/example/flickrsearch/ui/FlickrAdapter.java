package com.example.flickrsearch.ui;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import network.Photo;

public class FlickrAdapter extends PagedListAdapter<Photo, FlickrAdapter.FlickrViewHolder> {


    public static final DiffUtil.ItemCallback<Photo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Photo>() {
                @Override
                public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Photo Photo, @NonNull Photo t1) {
                    return Photo.getUrlS().equals(t1.getUrlS());
                }
            };

    public FlickrAdapter() {
        super(DIFF_CALLBACK);
    }

    public static class FlickrViewHolder extends RecyclerView.ViewHolder {
        public ImageView drawable;

        public FlickrViewHolder(View itemView) {
            super(itemView);
            drawable = (ImageView) itemView;
        }
    }

    @NonNull
    @Override
    public FlickrViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ImageView img = new ImageView(viewGroup.getContext());
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return new FlickrViewHolder(img);
    }


    @Override
    public void onBindViewHolder(@NonNull FlickrViewHolder viewHolder, int i) {
        String url = getItem(i).getUrlS();
        Glide.with(viewHolder.itemView).load(url).into(viewHolder.drawable);
    }

}
