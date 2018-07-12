package com.dmcadmson.dmc.MissionDMCmcq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class NOTREADYActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NOTREADYActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        //super.onBackPressed();
    }

    TextView textNotReady;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notready);

        Bundle bundle = getIntent().getExtras();
        textNotReady = (TextView) findViewById(R.id.textNotReady);

        if(bundle.getString("apiStr")!= null)
        {
            //TODO here get the string stored in the string variable and do
            // setText() on userName
            textNotReady.setText(bundle.getString("apiStr"));

        }
    }
}
