package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PackageActivity extends AppCompatActivity {

    Button medicalPackageButton;
    Button dentalPackageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        medicalPackageButton= (Button) findViewById(R.id.id_medical_packageButton);
        dentalPackageButton = (Button) findViewById(R.id.id_varsity_packageButton);

        medicalPackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PackageActivity.this, PackageActivityMedical.class);
                intent.putExtra("button","medical");
                startActivity(intent);
            }
        });

        dentalPackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PackageActivity.this, PackageActivityVarsity.class);
                intent.putExtra("button","varsity");
                startActivity(intent);
            }
        });
    }
}
