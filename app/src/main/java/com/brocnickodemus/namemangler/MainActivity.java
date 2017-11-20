package com.brocnickodemus.namemangler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mMangleNicelyButton;
    private Button mMangleRudelyButton;
    private EditText mEditText;

    public static final String EXTRA_MESSAGE = "com.brocnickodemus.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);

        mMangleNicelyButton = (Button) findViewById(R.id.mangle_nicely_button);
        mMangleNicelyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "you must enter a name",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, MangledName.class);
                    String name = mEditText.getText().toString();

                    boolean isNice = true;
                    intent.putExtra("name", name);
                    intent.putExtra("nice", isNice);
                    startActivity(intent);
                }
            }
        });

        mMangleRudelyButton = (Button) findViewById(R.id.mangle_rudely_button);
        mMangleRudelyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "you must enter a name",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, MangledName.class);
                    String name = mEditText.getText().toString();

                    boolean isNice = false;
                    intent.putExtra("name", name);
                    intent.putExtra("nice", isNice);
                    startActivity(intent);
                }
            }
        });
    }
}
