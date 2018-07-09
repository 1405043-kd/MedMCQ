package com.example.dmc.MissionDMCmcq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmc.MissionDMCmcq.models.Adapters.ChapterAdapter;
import com.example.dmc.MissionDMCmcq.models.Adapters.MediDentalPreviousAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediDentalPreviousActivity extends AppCompatActivity{

    String subName="";
    ListView chapterListView;
    private List<String> mediYearList=new ArrayList<>();
    private List<String> dentalYearList=new ArrayList<>();

    Button buttonPopupExam;
   // Button buttonPopupSeeQuestion;
    LinearLayout linearLayout11;
    LinearLayout linearPopup11;
    TextView textViewPopup11;
    PopupWindow popupWindow2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subName = extras.getString("subName");
        }
        Toast.makeText(this, subName,Toast.LENGTH_SHORT).show();

        chapterListView = (ListView) findViewById(R.id.chapter_pagination_list);

        mediYearList= Arrays.asList(
                "সেশন ২০১৭-২০১৮",
                "সেশন ২০১৬-২০১৭",
                "সেশন ২০১৫-২০১৬",
                "সেশন ২০১৪-২০১৫",
                "সেশন ২০১৩-২০১৪",
                "সেশন ২০১২-২০১৩",
                "সেশন ২০১১-২০১২",
                "সেশন ২০১০-২০১১",
                "সেশন ২০০৯-২০১০",
                "সেশন ২০০৮-২০০৯",
                "সেশন ২০০৭-২০০৮",
                "সেশন ২০০৬-২০০৭",
                "সেশন ২০০৫-২০০৬",
                "সেশন ২০০৪-২০০৫",
                "সেশন ২০০৩-২০০৪",
                "সেশন ২০০২-২০০৩",
                "সেশন ২০০০-২০০১",
                "সেশন ১৯৯৯-২০০০",
                "সেশন ১৯৯৮-১৯৯৯",
                "সেশন ১৯৯৭-১৯৯৮",
                "সেশন ১৯৯৬-১৯৯৭",
                "সেশন ১৯৯৫-১৯৯৬",
                "সেশন ১৯৯৪-১৯৯৫",
                "সেশন ১৯৯৩-১৯৯৪",
                "সেশন ১৯৯২-১৯৯৩",
                "সেশন ১৯৯১-১৯৯২",
                "সেশন ১৯৯০-১৯৯১",
                "সেশন ১৯৯০-১৯৯১(১)",
                "সেশন ১৯৮৯-১৯৯০",
                "সেশন ১৯৮৮-১৯৮৯"
        );

        dentalYearList = Arrays.asList(
                "সেশন ২০১৬-২০১৭",
                "সেশন ২০১০-২০১১",
                "সেশন ২০০৯-২০১০",
                "সেশন ২০০৮-২০০৯",
                "সেশন ২০০৭-২০০৮",
                "সেশন ২০০৬-২০০৭",
                "সেশন ২০০৫-২০০৬",
                "সেশন ২০০৪-২০০৫",
                "সেশন ২০০৩-২০০৪",
                "সেশন ২০০২-২০০৩",
                "সেশন ২০০১-২০০২",
                "সেশন ২০০০-২০০১",
                "সেশন ১৯৯৯-২০০০",
                "সেশন ১৯৯৮-১৯৯৯",
                "সেশন ১৯৯৭-১৯৯৮",
                "সেশন ১৯৯৬-১৯৯৭",
                "সেশন ১৯৯৫-১৯৯৬"

        );


        if(subName.contains("mediButton")){
            adapterSetter(chapterListView,mediYearList,subName);
        }else if(subName.contains("dentalButton")){
            adapterSetter(chapterListView,dentalYearList,subName);
        }

    }




    private void adapterSetter(ListView listView, List<String > list, final String subName){
        listView.setAdapter(new MediDentalPreviousAdapter(MediDentalPreviousActivity.this, list));

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String chapterName=(String) adapterView.getItemAtPosition(i);

                final String apiStr=subName+"/"+chapterName;
                Toast.makeText(MediDentalPreviousActivity.this, apiStr, Toast.LENGTH_SHORT).show();


                //new code 07-07-2018
                LayoutInflater layoutInflater = (LayoutInflater) MediDentalPreviousActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_medi_dental_taka_vikkha,null);
                buttonPopupExam = (Button) customView.findViewById(R.id.buttonPopupMediDentalExam);
               // buttonPopupSeeQuestion = (Button) customView.findViewById(R.id.buttonPopupMediDentalSeeQstn);
                linearLayout11 = (LinearLayout) findViewById(R.id.linearLayoutMediDentalMain);
                textViewPopup11 = (TextView) customView.findViewById(R.id.textViewPopupMediDental);
                linearPopup11 =(LinearLayout) customView.findViewById(R.id.linearPopupMediDental);
                linearPopup11.setBackgroundColor(Color.parseColor("#2D2419"));
                textViewPopup11.setText(chapterName + " এই পরীক্ষা দেয়ার জন্য আপনার একাউন্ট থেকে ৩ টাকা কেটে নেয়া হবে।");
                popupWindow2 = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                popupWindow2.showAtLocation(linearLayout11, Gravity.CENTER, 0, 0);
                popupWindow2.update();

                buttonPopupExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //taka katar code likhte hbe
//                Toast.makeText(MediDentalPreviousActivity.this,chapterName, Toast.LENGTH_SHORT).show();
                        popupWindow2.dismiss();
                        Intent intent = new Intent(MediDentalPreviousActivity.this, ChapterQuestionActivity.class);
                        intent.putExtra("apiStr",apiStr);
                        startActivity(intent);
                    }
                });

//                buttonPopupSeeQuestion.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //taka katar code likhte hbe
//                        popupWindow2.dismiss();
//                        //code likhte hbe
//                    }
//                });


                /////////////////////////////////


//                String chapterName=(String) adapterView.getItemAtPosition(i);
//
//                String apiStr=subName+"/"+chapterName;
//                Toast.makeText(MediDentalPreviousActivity.this, apiStr, Toast.LENGTH_SHORT).show();
////                Toast.makeText(MediDentalPreviousActivity.this,chapterName, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MediDentalPreviousActivity.this, ChapterQuestionActivity.class);
//                intent.putExtra("apiStr",apiStr);
//                startActivity(intent);
            }
        });

    }


}
