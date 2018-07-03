package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        examButton = (Button) findViewById(R.id.button_exam) ;
        subjectListButton = (Button) findViewById(R.id.button_sub);
        previousDentalQuesButton = (Button) findViewById(R.id.button_dentalQstn);
        previousMediQuesButton = (Button) findViewById (R.id.button_medicalQstn);
        archiveButton = (Button) findViewById(R.id.button_archive);



        examButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalMainActivity.this, DailyExamActivity.class);
                startActivity(intent);
            }
        });

        subjectListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchactivity_subject();
            }
        });
        previousDentalQuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchactivity_previousmeden("dentalButton");
            }
        });
        previousMediQuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchactivity_previousmeden("mediButton");
            }
        });
        archiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalMainActivity.this, ArchiveActivity.class);
                startActivity(intent);
            }
        });

    }

    private void launchactivity_subject()
    {
        Intent intent = new Intent(this, SubjectListActivity.class);
        startActivity(intent);
    }

//    private void launchactivity_previousmeden(String medden)
//    {
//        Intent intent = new Intent(this, PrevMedDenQuestionActivity.class);
//        intent.putExtra("meode", medden);
//        startActivity(intent);
//    }

      private void launchactivity_previousmeden(String medden)
      {
          Intent intent = new Intent(this, MediDentalPreviousActivity.class);
          intent.putExtra("subName",medden);
          startActivity(intent);
      }
}
