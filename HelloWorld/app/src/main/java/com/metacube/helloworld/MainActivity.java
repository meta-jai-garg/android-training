package com.metacube.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "1234";
    private static final int PICK_CONTACT_REQUEST = 2;
    private Button singleInstance, singleTask, singleTop, standard, launchMapBtn,
            launchDialerBtn, additionBtn, launchFragmentBtn, formControlActivityBtn,
            recyclerViewActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        init();
        methodListener();
    }


    /**
     * method to initialize different UI components
     */
    private void init() {
        singleInstance = findViewById(R.id.singleInstance);
        singleTask = findViewById(R.id.singleTask);
        singleTop = findViewById(R.id.singleTop);
        standard = findViewById(R.id.standard);
        launchMapBtn = findViewById(R.id.launchMapBtn);
        launchDialerBtn = findViewById(R.id.launchDialerBtn);
        additionBtn = findViewById(R.id.additionBtn);
        launchFragmentBtn = findViewById(R.id.launchFragmentBtn);
        formControlActivityBtn = findViewById(R.id.formControlActivityBtn);
        recyclerViewActivityBtn = findViewById(R.id.recyclerViewActivityBtn);
    }

    /**
     * method to handle click listener
     */
    private void methodListener() {
        singleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        launchMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo:26.7891175,75.8250578?z=10");
                startActivity(new Intent(Intent.ACTION_VIEW, location));
            }
        });

        launchDialerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, Uri.parse
                        ("content://contacts")).setType(ContactsContract.CommonDataKinds.Phone
                        .CONTENT_TYPE), PICK_CONTACT_REQUEST);
            }
        });

        additionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), AdditionActivity
                        .class), 1);
            }
        });

        launchFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FragmentCommunicationActivity
                        .class));
            }
        });

        formControlActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormControlActivity.class));
            }
        });

        recyclerViewActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String addition = data.getStringExtra("addition");
                Toast.makeText(this, addition, Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);
                Toast.makeText(this, number, Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}