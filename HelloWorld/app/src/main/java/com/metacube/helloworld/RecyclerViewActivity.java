package com.metacube.helloworld;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.metacube.helloworld.adapter.RecyclerViewAdapter;
import com.metacube.helloworld.pojo.MoviePojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MoviePojo> movies;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);

        movies = prepareMovieData();

        layoutManager = getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_PORTRAIT ? new LinearLayoutManager(this) : new GridLayoutManager
                (this, 2);
        adapter = new RecyclerViewAdapter(movies);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private List<MoviePojo> prepareMovieData() {
        List<MoviePojo> movieList = new ArrayList<>();
        movieList.add(new MoviePojo(R.drawable.antman_and_the_wasp, "Antman and The Wasp"));
        movieList.add(new MoviePojo(R.drawable.aquaman, "Aquaman"));
        movieList.add(new MoviePojo(R.drawable.assasin_creed, "Assasin's Creed"));
        movieList.add(new MoviePojo(R.drawable.avengers, "Avengers"));
        movieList.add(new MoviePojo(R.drawable.black_panther, "Black Panther"));
        movieList.add(new MoviePojo(R.drawable.boss_baby, "Boss Baby"));
        movieList.add(new MoviePojo(R.drawable.deadpool, "Deadpool"));
        movieList.add(new MoviePojo(R.drawable.death_race, "Death Race"));
        movieList.add(new MoviePojo(R.drawable.ff_8, "Fast and Furious 8"));
        movieList.add(new MoviePojo(R.drawable.hitman, "Hitman"));
        movieList.add(new MoviePojo(R.drawable.john_wick, "John Wick"));
        movieList.add(new MoviePojo(R.drawable.sherlock_holmes, "Sherlock Holmes"));
        movieList.add(new MoviePojo(R.drawable.superman, "Superman"));
        movieList.add(new MoviePojo(R.drawable.transporter, "Transporter"));
        return movieList;
    }
}
