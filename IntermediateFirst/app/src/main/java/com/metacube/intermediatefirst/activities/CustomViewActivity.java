package com.metacube.intermediatefirst.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.metacube.intermediatefirst.R;
import com.metacube.intermediatefirst.custom_views.MyCustomView;

public class CustomViewActivity extends AppCompatActivity {

    private MyCustomView myCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        myCustomView = findViewById(R.id.mcv);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one: {
                myCustomView.customPaddingUp(30);
                break;
            }
            case R.id.two: {
                myCustomView.swapColor();
                break;
            }
            case R.id.three: {
                myCustomView.customPaddingDown(30);
            }
        }
    }
}
