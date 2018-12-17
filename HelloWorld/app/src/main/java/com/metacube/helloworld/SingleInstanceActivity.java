package com.metacube.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
    }
}
