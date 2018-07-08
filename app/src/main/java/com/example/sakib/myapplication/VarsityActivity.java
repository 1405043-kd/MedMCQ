package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class VarsityActivity extends AppCompatActivity {

//    private Button subjectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varsity);

        //subjectButton = (Button) findViewById(R.id.button_sub) ;

//        subjectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchactivity_subject();
//            }
//        });


    }

//    private void launchactivity_subject()
//    {
//        Intent intent = new Intent(this, VarsitySubjectActivity.class);
//        startActivity(intent);
//    }

}
