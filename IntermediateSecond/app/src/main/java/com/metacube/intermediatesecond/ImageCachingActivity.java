package com.metacube.intermediatesecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ImageCachingActivity extends AppCompatActivity {
    private ListView listView;
    private ListAdapter listAdapter;
    private List<MovieModelREST> modelRESTS;
    private ImageCache cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_caching);
        listView = findViewById(R.id.list_view);
        cache = ImageCache.getInstance();
        cache.initializeCache();
       final String url = "http://localhost:3000/movies";
    }
}
