package com.metacube.intermediatesecond;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        methodListener();
    }

    private void init() {
        recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setHasFixedSize(true);
        List<MoviePojo> moviePojos = prepareData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new MovieRecyclerViewAdapter(moviePojos, this);
        recyclerView.setAdapter(adapter);
    }

    private void methodListener() {
        adapter.setOnClickListener(new MovieRecyclerViewAdapter.ClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(int position, View v) {
                if (v != null) {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    ImageView movieImageView = v.findViewById(R.id.movieImageView);
                    TextView movieTv = v.findViewById(R.id.movieTv);
                    Pair<View, String> image = Pair.create((View) movieImageView, movieImageView
                            .getTransitionName());
                    Pair<View, String> title = Pair.create((View) movieTv, movieTv
                            .getTransitionName());
                    ActivityOptionsCompat compat = ActivityOptionsCompat
                            .makeSceneTransitionAnimation
                                    (MainActivity.this, image, title);
                    ActivityCompat.startActivity(MainActivity.this, intent, compat.toBundle());
                } else {
                    Toast.makeText(MainActivity.this, "Something is null", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private List<MoviePojo> prepareData() {
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

