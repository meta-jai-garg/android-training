package com.metacube.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.metacube.helloworld.pojo.FormPojo;

public class SavedDetailsActivity extends AppCompatActivity {

    private TextView nameText, mobileText, genderText, skillsText, ratingText, challengeText,
            dobText;
    private FormPojo formPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_details);
        init();
        formPojo = getIntent().getParcelableExtra("formPojo");
        setText();
    }


    private void init() {
        nameText = findViewById(R.id.nameText);
        mobileText = findViewById(R.id.mobileText);
        genderText = findViewById(R.id.genderText);
        dobText = findViewById(R.id.dobText);
        skillsText = findViewById(R.id.skillsText);
        ratingText = findViewById(R.id.ratingText);
        challengeText = findViewById(R.id.challengeText);
    }

    private void setText() {
        nameText.setText(formPojo.getName());
        mobileText.setText(formPojo.getMobile());
        genderText.setText(formPojo.getGender());
        dobText.setText(formPojo.getBirthDate());
        skillsText.setText(formPojo.getSkills());
        ratingText.setText(String.valueOf(formPojo.getSelfRating()));
        challengeText.setText(formPojo.isChallenge() ? "Yes" : "No");
    }
}
