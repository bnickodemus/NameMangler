package com.brocnickodemus.namemangler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MangledName extends AppCompatActivity {

    private TextView mTextView;
    private Button mReset;
    private Button mReMangle;
    private String mFirstName;
    private final String[] niceLastNames = { "Magnificent", "The Great", "The Awesome", "Superb", "The Fabulous" };
    private final String[] rudeLastNames = { "The Terrible", "The Faulty", "The Mad", "The Poor", "Faint of Heart" };

    private String savedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String firstName = (getIntent()).getStringExtra("name");
        final boolean isNice = (getIntent()).getExtras().getBoolean("nice");

        if (isNice) {
            setContentView(R.layout.activity_mangled_name_nicely);
        } else {
            setContentView(R.layout.activity_mangled_name_rudely);
        }
        mTextView = (TextView) findViewById(R.id.new_name);

        if (firstName != null) {
            mFirstName = firstName;
        }

        if (savedInstanceState != null) {
            savedName = savedInstanceState.getString("saved_name");
        } else {
            int index = new Random().nextInt(5) % 5;
            if (isNice) {
                String lastName = String.valueOf(niceLastNames[index]);
                savedName = mFirstName + " " + lastName;
            }
            else {
                String lastName = String.valueOf(rudeLastNames[index]);
                savedName = mFirstName + " " + lastName;
            }
        }
        mTextView.setText(savedName);

        mReset = (Button) findViewById(R.id.reset_button);
        mReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent  = new Intent (MangledName.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mReMangle = (Button) findViewById(R.id.re_mangle_button);
        mReMangle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int index = new Random().nextInt(5) % 5;
                if (isNice) {
                    String lastName = String.valueOf(niceLastNames[index]);
                    mTextView = (TextView) findViewById(R.id.new_name);
                    savedName = mFirstName + lastName;
                    mTextView.setText(savedName);
                } else {
                    String lastName = String.valueOf(rudeLastNames[index]);
                    mTextView = (TextView) findViewById(R.id.new_name);
                    savedName = mFirstName + lastName;
                    mTextView.setText(savedName);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //savedInstanceState.putString("saved_name", mTextView.getText().toString());
        savedInstanceState.putString("saved_name", savedName);

    }

}
