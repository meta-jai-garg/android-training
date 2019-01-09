package com.metacube.intermediatefirst.activities;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.metacube.intermediatefirst.R;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button showDialogBtn, showListDialogBtn, showFormDialogBtn;
    private ArrayList<String> spinnerItem;
    private ArrayAdapter adapter;
    private String[] listItems;
    private boolean[] checkedItems;
    private ArrayList<Integer> selectedItems;
    private TextView selectedItemsTv, dialogDataTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        init();
        methodListener();
    }

    private void init() {
        spinner = findViewById(R.id.spinner);
        showDialogBtn = findViewById(R.id.showDialogBtn);
        showListDialogBtn = findViewById(R.id.showListDialogBtn);
        showFormDialogBtn = findViewById(R.id.showFormDialogBtn);
        spinnerItem = new ArrayList<>();
        listItems = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[listItems.length];
        selectedItems = new ArrayList<>();
        spinnerItem.add("Select Animation Type....");
        spinnerItem.add("Slide Left");
        spinnerItem.add("Slide Up");
        spinnerItem.add("Scale");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                spinnerItem);
        spinner.setAdapter(adapter);
        selectedItemsTv = findViewById(R.id.selectedItemsTv);
        dialogDataTv = findViewById(R.id.dialogDataTv);
    }

    private void methodListener() {
        showDialogBtn.setOnClickListener(v -> {
            switch (spinner.getSelectedItemPosition()) {
                case 0: {
                    Snackbar.make(v, "Please choose an animation type", BaseTransientBottomBar
                            .LENGTH_SHORT).show();
                    break;
                }
                case 1: {
                    showDialogAnimation(R.style.DialogAnimationSlideLeft, "Slide Left");
                    break;
                }
                case 2: {
                    showDialogAnimation(R.style.DialogAnimationSlideUp, "Slide Up");
                    break;
                }
                case 3: {
                    showDialogAnimation(R.style.DialogAnimationScale, "Scale");
                    break;
                }
                default:
                    break;
            }
        });
        showListDialogBtn.setOnClickListener(v -> showListDialog());
        showFormDialogBtn.setOnClickListener(v -> showFormDialog());
    }

    private void showDialogAnimation(int type, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.CustomDialogTheme).create();
        alertDialog.setTitle("Dialog Animation");
        alertDialog.setMessage(message);
        alertDialog.getWindow().getAttributes().windowAnimations = type;
        alertDialog.show();
    }

    private void showListDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        alertDialog.setTitle("List Dialog");
        alertDialog.setMultiChoiceItems(listItems, checkedItems, (dialog, which, isChecked) -> {
            if (isChecked) {
                selectedItems.add(which);
            } else {
                selectedItems.remove(Integer.valueOf(which));
            }
        });

        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("ok", (dialog, which) -> {
            String item = "";
            for (int i = 0; i < selectedItems.size(); i++) {
                item = item + listItems[selectedItems.get(i)];
                if (i != selectedItems.size() - 1) {
                    item = item + ", ";
                }
            }
            if (selectedItemsTv.getVisibility() == View.GONE) {
                selectedItemsTv.setVisibility(View.VISIBLE);
                selectedItemsTv.setText(item);
            }
        });

        alertDialog.setNegativeButton("Discard", (dialog, which) -> {
            selectedItems.clear();
            for (int i = 0; i < checkedItems.length; i++) {
                checkedItems[i] = false;
            }
            dialog.dismiss();
        });

        AlertDialog dialog = alertDialog.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationScale;
        dialog.show();

    }

    private void showFormDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        final View dialogView = getLayoutInflater().inflate(R.layout.form_dialog, null);
        final EditText nameEt = dialogView.findViewById(R.id.nameEt);
        final EditText emailEt = dialogView.findViewById(R.id.emailEt);
        final EditText feedbackEt = dialogView.findViewById(R.id.feedbackEt);
        builder.setView(dialogView);

        builder.setTitle("Form Dialog");
        builder.setMessage("Fill Your Details");
        builder.setPositiveButton("Submit", (dialog, which) -> {
            String name = nameEt.getText().toString().trim();
            String email = emailEt.getText().toString().trim();
            String feedback = feedbackEt.getText().toString().trim();

            if (dialogDataTv.getVisibility() == View.GONE) {
                dialogDataTv.setVisibility(View.VISIBLE);
                dialogDataTv.setText(name + ", " + email + ", " + feedback);
            }
        }).setNegativeButton("Discard", (dialog, which) -> dialog.dismiss()).setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationScale;
        dialog.show();
    }
}
