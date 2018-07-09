package com.example.dmc.MissionDMCmcq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RankActivity extends AppCompatActivity {

    Button merit_button;
    String apiStr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apiStr = extras.getString("apiStr");
        }

        merit_button = findViewById(R.id.id_button_merit);

        merit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankActivity.this, MeritListActivity.class);
                intent.putExtra("apiStr",apiStr);
                startActivity(intent);
            }
        });

    }
}
