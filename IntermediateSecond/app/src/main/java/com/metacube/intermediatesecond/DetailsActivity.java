package com.metacube.intermediatesecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getWindow().setEnterTransition(null);
    }

//    @Override
//    public void onBackPressed() {
//        supportFinishAfterTransition();
//        finish();
//    }
}
