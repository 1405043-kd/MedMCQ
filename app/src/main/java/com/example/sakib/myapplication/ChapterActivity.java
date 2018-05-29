package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.Adapters.ChapterAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChapterActivity extends AppCompatActivity{

    String subName="";
    ListView chapterListView;
    private List<String> chapterListbio1=new ArrayList<>();
    private List<String> chapterListbio2=new ArrayList<>();
    private List<String> chapterListchem1=new ArrayList<>();
    private List<String> chapterListchem2=new ArrayList<>();
    private List<String> chapterListphy1=new ArrayList<>();
    private List<String> chapterListphy2=new ArrayList<>();
    private List<String> chapterListgKnow=new ArrayList<>();
    private List<String> chapterListenglish=new ArrayList<>();



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

        chapterListbio1= Arrays.asList("One", "Two", "Three");;

        if(subName.contains("bioOne")){
            adapterSetter(chapterListView,chapterListbio1,subName);
        }else if(subName.contains("bioTwo")){
            adapterSetter(chapterListView,chapterListbio2,subName);
        }else if(subName.contains("chemOne")){
            adapterSetter(chapterListView,chapterListchem1,subName);
        }else if(subName.contains("chemTwo")){
            adapterSetter(chapterListView,chapterListchem2,subName);
        }else if(subName.contains("phyOne")){
            adapterSetter(chapterListView,chapterListphy1,subName);
        }else if(subName.contains("phyTwo")){
            adapterSetter(chapterListView,chapterListphy2,subName);
        }else if(subName.contains("gKnow")){
            adapterSetter(chapterListView,chapterListgKnow,subName);
        }else if(subName.contains("english")){
            adapterSetter(chapterListView,chapterListenglish,subName);
        }

    }




    private void adapterSetter(ListView listView, List<String > list, final String subName){
        listView.setAdapter(new ChapterAdapter(ChapterActivity.this, list));

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String chapterName=(String) adapterView.getItemAtPosition(i);
                Toast.makeText(ChapterActivity.this,chapterName, Toast.LENGTH_SHORT).show();
                String apiStr=subName+"/"+chapterName;
                Intent intent = new Intent(ChapterActivity.this, ChapterQuestionActivity.class);
                intent.putExtra("apiStr",apiStr);
                startActivity(intent);
            }
        });

    }

}
