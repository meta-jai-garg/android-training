package com.metacube.helloworld;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.metacube.helloworld.pojo.FormPojo;

import java.util.Calendar;
import java.util.TimeZone;

public class FormControlActivity extends AppCompatActivity {

    private EditText nameEditText, mobileEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton genderBtn;
    private CheckBox javaCheckBox, dotNetCheckBox, javaScriptCheckBox, reactNativeCheckBox;
    private RatingBar selfRatingBar;
    private Button birthDateBtn;
    private SwitchCompat challengeSwitch;
    private FloatingActionButton fab;
    private FormPojo formPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_control);
        init();
        methodListener();
    }

    private void init() {
        nameEditText = findViewById(R.id.nameEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        javaCheckBox = findViewById(R.id.javaCheckBox);
        dotNetCheckBox = findViewById(R.id.dotNetCheckBox);
        javaScriptCheckBox = findViewById(R.id.javaScriptCheckBox);
        reactNativeCheckBox = findViewById(R.id.reactNativeCheckBox);
        selfRatingBar = findViewById(R.id.selfRatingBar);
        birthDateBtn = findViewById(R.id.birthDateBtn);
        challengeSwitch = findViewById(R.id.challengeSwitch);
        fab = findViewById(R.id.fab);
        formPojo = new FormPojo();
    }

    private void methodListener() {
        birthDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("IST"));
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormControlActivity
                        .this, new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int
                                    dayOfMonth) {
                                birthDateBtn.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDetailsValid()) {
                    startActivity(new Intent(FormControlActivity.this, SavedDetailsActivity
                            .class).putExtra("formPojo", formPojo));
                    finish();
                }
            }
        });

    }

    private boolean isDetailsValid() {
        String name = nameEditText.getText().toString();
        String mobile = mobileEditText.getText().toString();
        if (genderRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        genderBtn = genderRadioGroup.findViewById(genderRadioGroup
                .getCheckedRadioButtonId());
        String gender = genderBtn.getText().toString();
        String skills = (javaCheckBox.isChecked() ? javaCheckBox.getText().toString() + ", " : "") +
                (dotNetCheckBox.isChecked() ? dotNetCheckBox.getText().toString() + ", " : "") +
                (javaScriptCheckBox.isChecked() ? javaScriptCheckBox.getText().toString() + ", "
                        : "") +
                (reactNativeCheckBox.isChecked() ? reactNativeCheckBox.getText().toString() : "");
        float selfRating = selfRatingBar.getRating();
        boolean challenge = challengeSwitch.isChecked();
        String birthDate = birthDateBtn.getText().toString();

        if ("".equals(name)) {
            nameEditText.setError("Name can't be empty.");
        }
        if ("".equals(mobile)) {
            mobileEditText.setError("Mobile can't be empty.");
        }
        if ("".equals(skills)) {
            Toast.makeText(this, "Please add at least one skill", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (selfRating <= 0.0f) {
            Toast.makeText(this, "Please rate yourself", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ("".equals(birthDate)) {
            Toast.makeText(this, "Please provide your birth-date.", Toast.LENGTH_SHORT).show();
            return false;
        }
        formPojo.setName(name);
        formPojo.setMobile(mobile);
        formPojo.setGender(gender);
        formPojo.setSkills(skills);
        formPojo.setBirthDate(birthDate);
        formPojo.setChallenge(challenge);
        formPojo.setSelfRating(selfRating);
        return true;
    }
}
