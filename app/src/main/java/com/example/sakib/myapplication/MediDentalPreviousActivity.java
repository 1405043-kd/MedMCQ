package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.Adapters.ChapterAdapter;
import com.example.sakib.myapplication.models.Adapters.MediDentalPreviousAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediDentalPreviousActivity extends AppCompatActivity{

    String subName="";
    ListView chapterListView;
    private List<String> mediYearList=new ArrayList<>();
    private List<String> dentalYearList=new ArrayList<>();




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
                "সেশন ২০০১-২০০২",
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

                String apiStr=subName+"/"+chapterName;
                Toast.makeText(MediDentalPreviousActivity.this, apiStr, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MediDentalPreviousActivity.this,chapterName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MediDentalPreviousActivity.this, ChapterQuestionActivity.class);
                intent.putExtra("apiStr",apiStr);
                startActivity(intent);
            }
        });

    }


}
