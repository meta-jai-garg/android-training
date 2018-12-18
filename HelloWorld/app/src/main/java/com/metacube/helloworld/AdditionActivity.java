package com.metacube.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdditionActivity extends AppCompatActivity {

    private EditText firstNumberEditText, secondNumberEditText;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        init();
        methodListener();
    }

    private void init() {
        firstNumberEditText = findViewById(R.id.firstNumberEditText);
        secondNumberEditText = findViewById(R.id.secondNumberEditText);
        addBtn = findViewById(R.id.addBtn);
    }

    private void methodListener() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int firstNumber = Integer.parseInt(String.valueOf(firstNumberEditText.getText()));
                int secondNumber = Integer.parseInt(String.valueOf(secondNumberEditText.getText()));
                setResult(Activity.RESULT_OK, new Intent().putExtra("addition", Integer.toString(firstNumber + secondNumber)));
                finish();
            }
        });
    }
}
