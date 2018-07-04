package com.example.sakib.myapplication;

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


    Button examButton;
    Button rankButton;

    LinearLayout linearLayoutPopup;
    LinearLayout linearLayoutMain;
    PopupWindow popupWindow;

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









    }

    public void buttonAction(final Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) DailyExamActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_daily_exam,null);
                examButton = (Button) customView.findViewById(R.id.buttonPopupDailyExam);
                rankButton = (Button) customView.findViewById(R.id.buttonPopupDailyExamRankShow);

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
                        Intent intent = new Intent(DailyExamActivity.this, ChapterQuestionActivity.class);
                        intent.putExtra("apiStr",button.getText());
                        startActivity(intent);

                    }
                });

                rankButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DailyExamActivity.this, RankActivity.class);
                        intent.putExtra("apiStr",button.getText());
                        startActivity(intent);
                    }
                });

            }
        });


    }
}