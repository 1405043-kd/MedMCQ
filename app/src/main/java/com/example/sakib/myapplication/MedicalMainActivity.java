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
        subjectListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchactivity_subject();
            }
        });
    }

    private void launchactivity_subject()
    {
        Intent intent = new Intent(this, SubjectListActivity.class);
        startActivity(intent);
    }
}
