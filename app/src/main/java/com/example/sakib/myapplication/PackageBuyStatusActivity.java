package com.example.sakib.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PackageBuyStatusActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_buy_status);

        textView = findViewById(R.id.textViewPackageStatus);

        //ei textView te set kora lagbe j package buy successful hoice kina
    }
}
