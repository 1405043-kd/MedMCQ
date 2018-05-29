package com.example.sakib.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ChapterQuestionActivity extends AppCompatActivity {
    String apiStr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_question);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apiStr = extras.getString("apiStr");
        }

        Toast.makeText(ChapterQuestionActivity.this,apiStr, Toast.LENGTH_SHORT).show();
    }
}
