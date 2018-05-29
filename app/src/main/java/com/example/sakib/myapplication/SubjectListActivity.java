package com.example.sakib.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class SubjectListActivity extends AppCompatActivity {

    CardView bioOne;
    CardView bioTwo;
    CardView chemOne;
    CardView chemTwo;
    CardView phyOne;
    CardView phyTwo;
    CardView english;
    CardView gKnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        bioOne = (CardView) findViewById(R.id.card_view_sub1);
        bioTwo = (CardView) findViewById(R.id.card_view_sub2);

        chemOne = (CardView) findViewById(R.id.card_view_sub3);
        chemTwo = (CardView) findViewById(R.id.card_view_sub4);

        phyOne = (CardView) findViewById(R.id.card_view_sub5);
        phyTwo = (CardView) findViewById(R.id.card_view_sub6);

        gKnow = (CardView) findViewById(R.id.card_view_sub7);
        english = (CardView) findViewById(R.id.card_view_sub8);

        bioOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("bioOne");
            }
        });

        bioTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("bioTwo");
            }
        });

        chemOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("chemOne");
            }
        });

        chemTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("chemTwo");
            }
        });

        phyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("phyOne");
            }
        });
        phyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("phyTwo");
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("english");
            }
        });
        gKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSubWiseChapterList("gKnow");
            }
        });


    }

    private void launchSubWiseChapterList(String string){
        Intent intent = new Intent(this, ChapterActivity.class);
        intent.putExtra("subName",string);
        startActivity(intent);

    }
}
