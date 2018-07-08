package com.example.sakib.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.Board;
import com.example.sakib.myapplication.models.QuestionClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicalMainActivity extends AppCompatActivity {

    private Button examButton;
    private Button archiveButton;
    private Button lectureButton;
    private Button subjectListButton;
    private Button previousMediQuesButton;
    private Button previousDentalQuesButton;

    LinearLayout linearLayout11;
    LinearLayout linearPopup11;
    TextView textViewPopup11;
    PopupWindow popupWindow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_main);

        examButton = (Button) findViewById(R.id.button_exam) ;
        subjectListButton = (Button) findViewById(R.id.button_sub);
        previousDentalQuesButton = (Button) findViewById(R.id.button_dentalQstn);
        previousMediQuesButton = (Button) findViewById (R.id.button_medicalQstn);
        archiveButton = (Button) findViewById(R.id.button_archive);
        lectureButton = (Button) findViewById(R.id.button_lecture);
        final TextView nboard=findViewById(R.id.textView4);
        final TextView textViewN=findViewById(R.id.textView_notice);



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

        lectureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = (LayoutInflater) MedicalMainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_lecture_notes,null);

                linearLayout11 = (LinearLayout) findViewById(R.id.linearLayoutMedicalMain);
                textViewPopup11 = (TextView) customView.findViewById(R.id.textViewLectureNotes);
                linearPopup11 =(LinearLayout) customView.findViewById(R.id.linearPopupLectureNotes);
                linearPopup11.setBackgroundColor(Color.parseColor("#2D2419"));
                //textViewPopup.setText(apiStr);
                popupWindow2 = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                popupWindow2.showAtLocation(linearLayout11, Gravity.CENTER, 0, 0);
                popupWindow2.update();

            }
        });




        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient cClient = retrofit.create(QuestionClient.class);

        //Board board=new Board("okay. notice board is working");

        Call<Board> calle= cClient.notices();



        calle.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                Toast.makeText(MedicalMainActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                Board board=response.body();
                textViewN.setText(board.getNotice());

            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Toast.makeText( MedicalMainActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
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
