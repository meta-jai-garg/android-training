package com.metacube.firstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewGridAdapter extends RecyclerView.Adapter<RecyclerViewGridAdapter
        .RecyclerViewHolder> {

    private Integer[] mThumbIds;
    private String[] mTexts;

    public RecyclerViewGridAdapter(Integer[] mThumbIds, String[] mTexts) {
        this.mThumbIds = mThumbIds;
        this.mTexts = mTexts;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.mImageView.setImageResource(mThumbIds[i]);
        recyclerViewHolder.mTextView.setText(mTexts[i]);
    }

    @Override
    public int getItemCount() {
        return mThumbIds.length;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.android_gridview_image);
            mTextView = itemView.findViewById(R.id.android_gridview_text);
        }
    }
}
