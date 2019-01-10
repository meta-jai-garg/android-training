package com.metacube.intermediatesecond;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter
        .MovieRecyclerViewHolder> {
    private List<MoviePojo> moviePojoList;
    private Context context;
    private static ClickListener clickListener;

    public MovieRecyclerViewAdapter(List<MoviePojo> moviePojoList, Context context) {
        this.moviePojoList = moviePojoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieRecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R
                .layout.movie_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewHolder movieRecyclerViewHolder, int i) {
        movieRecyclerViewHolder.movieTv.setText(moviePojoList.get(i).getMovieName());
        movieRecyclerViewHolder.movieImageView.setImageResource(moviePojoList.get(i)
                .getMovieImage());
    }

    @Override
    public int getItemCount() {
        return this.moviePojoList.size();
    }

    public static class MovieRecyclerViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        public TextView movieTv;
        public ImageView movieImageView;

        public MovieRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            movieTv = itemView.findViewById(R.id.movieTv);
            movieImageView = itemView.findViewById(R.id.movieImageView);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnClickListener(ClickListener clickListener) {
        MovieRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
