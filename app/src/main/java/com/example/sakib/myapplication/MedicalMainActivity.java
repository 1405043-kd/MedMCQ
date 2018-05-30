package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MedicalMainActivity extends AppCompatActivity {

    private Button examButton;
    private Button archiveButton;
    private Button nextExamButton;
    private Button subjectListButton;
    private Button previousMediQuesButton;
    private Button previousDentalQuesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_main);

        subjectListButton = (Button) findViewById(R.id.button_sub);
        previousMediQuesButton = (Button) findViewById(R.id.button_dentalQstn);
        previousDentalQuesButton = (Button) findViewById (R.id.button_medicalQstn);

        subjectListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchactivity_subject();
            }
        });
        previousDentalQuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchactivity_previousmeden("den");
            }
        });
        previousMediQuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchactivity_previousmeden("med");
            }
        });

    }

    private void launchactivity_subject()
    {
        Intent intent = new Intent(this, SubjectListActivity.class);
        startActivity(intent);
    }

    private void launchactivity_previousmeden(String medden)
    {
        Intent intent = new Intent(this, PrevMedDenQuestionActivity.class);
        intent.putExtra("meode", medden);
        startActivity(intent);
    }
}
