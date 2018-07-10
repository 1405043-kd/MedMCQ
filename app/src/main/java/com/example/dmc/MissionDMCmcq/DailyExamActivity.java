package com.example.dmc.MissionDMCmcq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmc.MissionDMCmcq.models.ExamHistory;
import com.example.dmc.MissionDMCmcq.models.QuestionClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyExamActivity extends AppCompatActivity {

    Button buttonDailyExam1;
    Button buttonDailyExam2;
    Button buttonDailyExam3;
    Button buttonDailyExam4;
    Button buttonDailyExam5;
    Button buttonDailyExam6;
    Button buttonDailyExam7;
    Button buttonDailyExam8;
    Button buttonDailyExam9;
    Button buttonDailyExam10;
    Button buttonDailyExam11;
    Button buttonDailyExam12;
    Button buttonDailyExam13;
    Button buttonDailyExam14;
    Button buttonDailyExam15;
    Button buttonDailyExam16;
    Button buttonDailyExam17;
    Button buttonDailyExam18;
    Button buttonDailyExam19;
    Button buttonDailyExam20;
    Button buttonDailyExam21;
    Button buttonDailyExam22;
    Button buttonDailyExam23;
    Button buttonDailyExam24;
    Button buttonDailyExam25;
    TextView popUpText;

    Button examButton;
    Button rankButton;
    public boolean checker=false;
    private List<ExamHistory> e_historys=new ArrayList<ExamHistory>();

    LinearLayout linearLayoutPopup;
    LinearLayout linearLayoutMain;
    PopupWindow popupWindow;

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Intent intent= new Intent(DailyExamActivity.this, MedicalMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_exam);



        buttonDailyExam1 = (Button) findViewById(R.id.button_exam_1);
        buttonDailyExam2 = (Button) findViewById(R.id.button_exam_2);
        buttonDailyExam3 = (Button) findViewById(R.id.button_exam_3);
        buttonDailyExam4 = (Button) findViewById(R.id.button_exam_4);
        buttonDailyExam5 = (Button) findViewById(R.id.button_exam_5);
        buttonDailyExam6 = (Button) findViewById(R.id.button_exam_6);
        buttonDailyExam7 = (Button) findViewById(R.id.button_exam_7);
        buttonDailyExam8 = (Button) findViewById(R.id.button_exam_8);
        buttonDailyExam9 = (Button) findViewById(R.id.button_exam_9);
        buttonDailyExam10 = (Button) findViewById(R.id.button_exam_10);
        buttonDailyExam11 = (Button) findViewById(R.id.button_exam_11);
        buttonDailyExam12 = (Button) findViewById(R.id.button_exam_12);
        buttonDailyExam13 = (Button) findViewById(R.id.button_exam_13);
        buttonDailyExam14 = (Button) findViewById(R.id.button_exam_14);
        buttonDailyExam15 = (Button) findViewById(R.id.button_exam_15);
        buttonDailyExam16 = (Button) findViewById(R.id.button_exam_16);
        buttonDailyExam17 = (Button) findViewById(R.id.button_exam_17);
        buttonDailyExam18 = (Button) findViewById(R.id.button_exam_18);
        buttonDailyExam19 = (Button) findViewById(R.id.button_exam_19);
        buttonDailyExam20 = (Button) findViewById(R.id.button_exam_20);
        buttonDailyExam21 = (Button) findViewById(R.id.button_exam_21);
        buttonDailyExam22 = (Button) findViewById(R.id.button_exam_22);
        buttonDailyExam23 = (Button) findViewById(R.id.button_exam_23);
        buttonDailyExam24 = (Button) findViewById(R.id.button_exam_24);
        buttonDailyExam25 = (Button) findViewById(R.id.button_exam_25);
        buttonAction(buttonDailyExam1);
        buttonAction(buttonDailyExam2);
        buttonAction(buttonDailyExam3);
        buttonAction(buttonDailyExam4);
        buttonAction(buttonDailyExam5);
        buttonAction(buttonDailyExam6);
        buttonAction(buttonDailyExam7);
        buttonAction(buttonDailyExam8);
        buttonAction(buttonDailyExam9);
        buttonAction(buttonDailyExam10);
        buttonAction(buttonDailyExam11);
        buttonAction(buttonDailyExam12);
        buttonAction(buttonDailyExam13);
        buttonAction(buttonDailyExam14);
        buttonAction(buttonDailyExam15);
        buttonAction(buttonDailyExam16);
        buttonAction(buttonDailyExam17);
        buttonAction(buttonDailyExam18);
        buttonAction(buttonDailyExam19);
        buttonAction(buttonDailyExam20);
        buttonAction(buttonDailyExam21);
        buttonAction(buttonDailyExam22);
        buttonAction(buttonDailyExam23);
        buttonAction(buttonDailyExam24);
        buttonAction(buttonDailyExam25);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient questionClient = retrofit.create(QuestionClient.class);

    }

    public void buttonAction(final Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) DailyExamActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_daily_exam,null);
                examButton = (Button) customView.findViewById(R.id.buttonPopupDailyExam);
                rankButton = (Button) customView.findViewById(R.id.buttonPopupDailyExamRankShow);
                popUpText=(TextView) findViewById(R.id.textViewPopupDailyExam);
                linearLayoutPopup = (LinearLayout) customView.findViewById(R.id.linearPopupDailyExam);
                linearLayoutPopup.setBackgroundColor(Color.parseColor("#2D2419"));

                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutDailyExamMain);
                popupWindow.showAtLocation(linearLayoutMain, Gravity.CENTER, 0, 0);
                // popupWindow.setOutsideTouchable(true);
                popupWindow.update();



                examButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();

                        final String toSendString= (String) button.getText();
                        FirebaseAuth mAuth;
                        mAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = mAuth.getCurrentUser();


                        final Retrofit.Builder builder = new Retrofit.Builder()
                                .baseUrl("http://missiondmc.ml/")
                                //    .baseUrl("https://api.github.com/")
                                .addConverterFactory(GsonConverterFactory.create());

                        Retrofit retrofit = builder.build();
                        QuestionClient questionClient = retrofit.create(QuestionClient.class);

                        Call<List<ExamHistory>> call= questionClient.e_history_user(user.getUid());
                        final ExamHistory bought=new ExamHistory(user.getUid(), user.getDisplayName(),0, toSendString, "W", (float) 0.0);



                        call.enqueue(new Callback<List<ExamHistory>>() {
                            @Override
                            public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {
                                //System.out.println("hcud");
                                checker=false;

                                e_historys = response.body();


                                for (ExamHistory curInstance: e_historys) {
                                    String checkerStr=curInstance.getQuestionName();
                                    if(checkerStr.equals(toSendString)) {
                                        if(!curInstance.getTableName().equals("W"))
                                            checker = true;
                                    }
                                }

                                if(checker==false) {

                                    final Retrofit.Builder builder = new Retrofit.Builder()
                                            .baseUrl("http://missiondmc.ml/")
                                            //    .baseUrl("https://api.github.com/")
                                            .addConverterFactory(GsonConverterFactory.create());

                                    Retrofit retrofit = builder.build();
                                    QuestionClient cClient = retrofit.create(QuestionClient.class);


                                    Call<ExamHistory> calle= cClient.post_e_history(bought);

                                    calle.enqueue(new Callback<ExamHistory>() {
                                        @Override
                                        public void onResponse(Call<ExamHistory> call, Response<ExamHistory> response) {
                                            Toast.makeText(DailyExamActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                                            //System.out.print("FUFU"+ response.body());

                                            Intent intent = new Intent(DailyExamActivity.this, ChapterQuestionActivity.class);
                                            intent.putExtra("apiStr",button.getText());
                                            startActivity(intent);

                                        }

                                        @Override
                                        public void onFailure(Call<ExamHistory> call, Throwable t) {
                                            Toast.makeText( DailyExamActivity.this, "Check your internet connection!!!", Toast.LENGTH_SHORT).show();
                                        }
                                    });





                                }
                                else {
                                    Intent intent = new Intent(DailyExamActivity.this, NOTREADYActivity.class);
                                    String string = toSendString + " এই পরীক্ষাতে ইতিমধ্যে আপনি অংশগ্রহণ করেছেন অথবা পরীক্ষাটি এখনো অনুষ্ঠিত হয়নি। " +
                                            "একটি ডেইলি এক্সামে একবার মাত্র অংশগ্রহণ করা যাবে। রাত ১২ টার পর আর্কাইভে এই পরীক্ষার " +
                                            "উত্তরপত্র উন্মুক্ত করে দেয়া হবে। পরীক্ষার সময়সূচী জানতে নোটিস বোর্ডে চোখ রাখুন।";

                                    intent.putExtra("apiStr", string);
                                    startActivity(intent);
                                }


                            }

                            @Override
                            public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                                Toast.makeText(DailyExamActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                            }
                        });



                    }
                });

                rankButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent(DailyExamActivity.this, RankActivity.class);
                        intent.putExtra("apiStr",button.getText());
                        startActivity(intent);
                    }
                });

            }
        });


    }
}
