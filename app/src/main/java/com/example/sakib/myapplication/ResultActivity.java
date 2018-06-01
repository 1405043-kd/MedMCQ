package com.example.sakib.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity{

    TextView tv1;
    TextView tv2;
    Float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            result = extras.getFloat("Result");
        }

        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView_notice);
//
        tv1.setText("Result");
        tv2.setText(Float.toString(result));

    }
}
