package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.Adapters.BiologyChapterAdapter;
import com.example.sakib.myapplication.models.Adapters.ChapterAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BiologyChapterActivity extends AppCompatActivity{

    String subName="";
    ListView chapterListView;
    private List<String> chapterListbio1=new ArrayList<>();
    private List<String> chapterListbio2=new ArrayList<>();




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

        chapterListbio1= Arrays.asList(
                "কোষ ও কোষের গঠন",
                "কোষ বিভাজন",
                "কোষ রসায়ন",
                "অণুজীব",
                "শৈবাল ও ছত্রাক",
                "ব্রায়োফাইটা ও টেরিডোফাইটা",
                "নগ্নবীজী ও আবৃতবীজী উদ্ভিদ",
                "টিস্যু ও টিস্যুতন্ত্র",
                "জীবপ্রযুক্তি",
                "উদ্ভিদের প্রজনন",
                "উদ্ভিদের শারীরতত্ত্ব",
                "জীবের পরিবেশ, বিস্তার ও সংরক্ষণ"
        );

        chapterListbio2 = Arrays.asList(
                "প্রাণির শ্রেণীবিন্যাস",
                "জীনতত্ত্ব ও বিবর্তন",
                "প্রাণীর আচরণ",
                "প্রাণীর পরিচিতি",
                "মানব শারীরতত্ত্বঃ পরিপাক ও শোষণ",
                "বর্জ্য ও নিষ্কাশন",
                "চলন ও অঙ্গচালনা",
                "রক্ত ও সঞ্চালন",
                "শ্বাস ক্রিয়া ও শ্বসন",
                "মানব দেহের প্রতিরক্ষা",
                "সমন্বয় ও নিয়ন্ত্রণ",
                "মানব জীবনের ধারাবাহিকতা"
        );



        if(subName.contains("bioOne")){
            adapterSetter(chapterListView,chapterListbio1,subName);
        }else if(subName.contains("bioTwo")){
            adapterSetter(chapterListView,chapterListbio2,subName);
        }
    }




    private void adapterSetter(ListView listView, List<String > list, final String subName){
        listView.setAdapter(new BiologyChapterAdapter(BiologyChapterActivity.this, list));

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String chapterName=(String) adapterView.getItemAtPosition(i);
                Toast.makeText(BiologyChapterActivity.this,chapterName, Toast.LENGTH_SHORT).show();
                String apiStr=subName+"/"+chapterName;
                Intent intent = new Intent(BiologyChapterActivity.this, ChapterQuestionActivity.class);
                intent.putExtra("apiStr",apiStr);
                startActivity(intent);
            }
        });

    }

}
