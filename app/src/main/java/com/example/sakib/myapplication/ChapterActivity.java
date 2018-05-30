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

        chapterListbio1= Arrays.asList(
                "কোষ ও কোষের গঠণ",
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
                "জীবের পরিবেশ,বিস্তার ও সংরক্ষণ"
        );

        chapterListbio2 = Arrays.asList(
                "প্রাণির বিভিন্নতা ও শ্রেণীবিন্যাস",
                "জীনতত্ত্ব ও বিবর্তন",
                "প্রাণি পরিচিতিঃ হাইড্রা",
                "প্রাণি পরিচিতিঃ ঘাসফড়িং",
                "প্রাণি পরিচিতিঃ রুইমাছ",
                "মানব শারীরতত্ত্বঃ পরিপাক ও শোষণ",
                "বর্জ্য ও নিষ্কাশন",
                "চলন ও অংগচালনা",
                "রক্ত ও সঞ্চালন",
                "শ্বাস ক্রিয়া ও শ্বসন",
                "মানব দেহের প্রতিরক্ষা",
                "সমন্বয় ও নিয়ন্ত্রণ",
                "মানব জীবনের ধারাবাহিকতা"

        );

        chapterListchem1= Arrays.asList(
                "ল্যাবরেটরির নিরাপদ ব্যবহার",
                "গুণগত রসায়ন",
                "মৌলের পর্যায়বৃত্ত ধর্ম",
                "রাসায়নিক বন্ধন",
                "কর্মমূখী রসায়ন"
        );

        chapterListchem2 = Arrays.asList(
                "পরিবেশ গত রসায়ন",
                "জৈব রসায়ন",
                "পরিমাণগত রসায়ন",
                "তড়িৎ রসায়ন",
                "অর্থনৈতিক রসায়ন"
        );

        chapterListphy1 = Arrays.asList(
                "ভৌত জগত ও পরিমাপ",
                " ভেক্টর",
                "গতিবিদ্যা",
                "নিউটনীয় বলবিদ্যা",
                "কাজ, শক্তি ও ক্ষমতা",
                "মহাকর্ষ ও অভিকর্ষ",
                "পদার্থের গাঠনিক ধর্ম",
                "পর্যায়বৃত্ত গতি",
                "তরংগ",
                "আদর্শ গ্যাস ও গ্যাসের গতিতত্ত্ব"
        );

        chapterListphy2 = Arrays.asList(
                "তাপগতিবিদ্যা",
                "স্থির তড়িৎ",
                "চল তড়িৎ",
                "তড়িৎ প্রবাহের চৌম্বক ক্রিয়া ও চুম্বকত্ব",
                "তড়িৎ চৌম্বক আবেশ ও পরিবর্তি প্রবাহ",
                "জ্যামিতিক আলোকবিজ্ঞান",
                "ভৌত আলোকবিজ্ঞান",
                "সেমিকন্ডাক্টর ও ইলেকট্রনিক্স",
                "পরমাণু মডেল ও নিউক্লিয়ার পদার্থবিজ্ঞান",
                "আধুনিক পদার্থবিজ্ঞানের সূচনা জোতির্বিজ্ঞান"
        );

        chapterListgKnow = Arrays.asList(
                "মুক্তিযুদ্ধ ও স্বাধীনতা",
                "খেলাধুলা ও পুরস্কার",
                "সংগঠণ ও সংস্থা",
                "ইতিহাস",
                "সংস্কৃতি",
                "শিক্ষা ও সাহিত্য",
                "সভ্যতা",
                "বিখ্যাত স্থান",
                "বিখ্যাত ব্যক্তিত্ব",
                "নদ-নদী",
                "দিবস",
                "সংবাদ সংস্থা ও বিমান সংস্থা",
                "রাজধানী ও মুদ্রা",
                "সাম্প্রতিক"
        );

        chapterListenglish = Arrays.asList(
                "Prepositions",
                "Phrase and Idioms",
                "Voice",
                "Narration",
                "Transformation",
                "Synonym",
                "Antonym",
                "Proverb and Translation",
                "Right form of verbs",
                "Spelling"
        );

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
