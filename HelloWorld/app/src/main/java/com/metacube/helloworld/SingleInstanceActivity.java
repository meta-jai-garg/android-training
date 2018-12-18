package com.metacube.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleInstanceActivity extends AppCompatActivity {

    private Button singleInstance, singleTask, singleTop, standard;
    private TextView singleInstanceText;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        this.counter++;
        init();
        methodListener();
    }


    private void init() {
        singleInstanceText = findViewById(R.id.singleInstanceText);
        singleInstance = findViewById(R.id.singleInstance);
        singleTask = findViewById(R.id.singleTask);
        singleTop = findViewById(R.id.singleTop);
        standard = findViewById(R.id.standard);
    }

    private void methodListener() {
        singleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleInstanceText.setText(""+counter);
                startActivity(new Intent(getApplicationContext(), SingleInstanceActivity.class));
            }
        });
        singleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SingleTaskActivity.class));
            }
        });
        singleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SingleTopActivity.class));
            }
        });
        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StandardActivity.class));
            }
        });
    }
}
