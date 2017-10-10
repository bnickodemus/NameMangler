package com.brocnickodemus.namemangler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MangledName extends AppCompatActivity {

    private TextView mTextView;
    private Button mReset;
    private Button mReMangle;
    private String mFirstName;
    private final String[] lastNames = { " Magnificent", " The Terrible", " The Smelly", " Superb", " The Great"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangled_name);

        mTextView = (TextView) findViewById(R.id.new_name);

        Intent intent = getIntent();
        String firstName = (getIntent()).getStringExtra("name");
        if (firstName != null) {
            mFirstName = firstName;
        }

        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString("saved_name"));
        }
        else {
            int index = new Random().nextInt(5) % 5;
            String lastName = String.valueOf(lastNames[index]);
            mTextView.setText(mFirstName + lastName);
        }


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
                String lastName = String.valueOf(lastNames[index]);

                mTextView = (TextView) findViewById(R.id.new_name);
                mTextView.setText(mFirstName + lastName);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("saved_name", mTextView.getText().toString());
    }

}
