package com.metacube.helloworld.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.metacube.helloworld.R;
import com.metacube.helloworld.pojo.MoviePojo;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter
        .RecyclerViewHolder> {
    private List<MoviePojo> movieList;

    public RecyclerViewAdapter(List<MoviePojo> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        MoviePojo moviePojo = movieList.get(i);
        recyclerViewHolder.dummyNameText.setText(moviePojo.getText());
        recyclerViewHolder.staticImageView.setImageResource(moviePojo.getImageId());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView staticImageView;
        private TextView dummyNameText;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            staticImageView = itemView.findViewById(R.id.staticImageView);
            dummyNameText = itemView.findViewById(R.id.dummyNameText);
        }
    }
}
