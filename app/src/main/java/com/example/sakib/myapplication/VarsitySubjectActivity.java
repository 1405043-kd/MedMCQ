package com.example.sakib.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class VarsitySubjectActivity extends AppCompatActivity {

    private CardView cardViewBioOne;
    private CardView cardViewBioTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varsity_subject);

        cardViewBioOne = (CardView)findViewById(R.id.card_view_sub1) ;
        cardViewBioTwo = (CardView)findViewById(R.id.card_view_sub2) ;

        cardViewBioOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSubWiseChapterList("bioOne");
            }
        });

        cardViewBioTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSubWiseChapterList("bioTwo");
            }
        });

    }
    private void launchSubWiseChapterList(String string)
    {
        Intent intent = new Intent(this, BiologyChapterActivity.class);
        intent.putExtra("subName",string);
        startActivity(intent);
    }
}
