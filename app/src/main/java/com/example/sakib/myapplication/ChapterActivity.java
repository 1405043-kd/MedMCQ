package com.example.sakib.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.sakib.myapplication.models.Adapters.ChapterAdapter;
import com.example.sakib.myapplication.models.Adapters.QuestionAdapter;
import com.example.sakib.myapplication.models.ExamHistory;
import com.example.sakib.myapplication.models.QuestionClient;
import com.example.sakib.myapplication.models.Questions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChapterActivity extends AppCompatActivity{

    private String subName="";
    private ListView chapterListView;
    private List<String> chapterListbio1=new ArrayList<>();
    private List<String> chapterListbio2=new ArrayList<>();
    private List<String> chapterListchem1=new ArrayList<>();
    private List<String> chapterListchem2=new ArrayList<>();
    private List<String> chapterListphy1=new ArrayList<>();
    private List<String> chapterListphy2=new ArrayList<>();
    private List<String> chapterListgKnow=new ArrayList<>();
    private List<String> chapterListenglish=new ArrayList<>();
    private boolean checker=false;
    private List<ExamHistory> e_historys=new ArrayList<ExamHistory>();

    PopupWindow popupWindow;
    Button buttonPopupExam;
    Button buttonPopupSeeQstn;
    LinearLayout linearLayout1;
    LinearLayout linearPopup;
    TextView textViewPopup;
    boolean click=true;


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

        chapterListchem1= Arrays.asList(
                "ল্যাবরেটরির নিরাপদ ব্যবহার",
                "গুণগত রসায়ন",
                "মৌলের পর্যায়বৃত্ত ধর্ম",
                "রাসায়নিক পরিবর্তন",
                "কর্মমূখী রসায়ন"
        );

        chapterListchem2 = Arrays.asList(
                "পরিবেশগত রসায়ন",
                "জৈব রসায়ন",
                "পরিমাণগত রসায়ন",
                "তড়িৎ রসায়ন",
                "অর্থনৈতিক রসায়ন"
        );

        chapterListphy1 = Arrays.asList(
                "ভৌত জগত ও পরিমাপ",
                "ভেক্টর",
                "গতিবিদ্যা",
                "নিউটনীয় বলবিদ্যা",
                "কাজ, শক্তি ও ক্ষমতা",
                "মহাকর্ষ ও অভিকর্ষ",
                "পদার্থের গাঠনিক ধর্ম",
                "পর্যায়বৃত্ত গতি",
                "তরঙ্গ",
                "আদর্শ গ্যাস ও গ্যাসের গতিতত্ত্ব"
        );

        chapterListphy2 = Arrays.asList(
                "তাপ গতিবিদ্যা",
                "চল তড়িৎ",
                "স্থির তড়িৎ",
                "তড়িৎ প্রবাহের চৌম্বকক্রিয়া ",
                "তড়িৎ চৌম্বক আবেশ ও চুম্বকত্ব",
                "জ্যামিতিক আলোকবিজ্ঞান",
                "ভৌত আলোকবিজ্ঞান",
                "আধুনিক পদার্থের সুচনা",
                "সেমিকন্ডাক্টার ও ইলেকট্রনিক্স",
                "নিউক্লিয়ার পদাথবিজ্ঞান",
                "জ্যোতির্বিজ্ঞান"
        );

        chapterListgKnow = Arrays.asList(
                "বাংলাদেশের মুক্তিযুদ্ধ",
                "খেলাধুলা",
                "পুরস্কার ও সম্মাননা",
                "বাংলাদেশের নদ, নদী খাল বিল",
                "বাংলাদেশের বিখ্যাত ব্যক্তিত্ব",
                "বাংলাদেশের সংস্কৃতি, সাহিত্য",
                "বাংলার ইতিহাস",
                "বিখ্যাত ব্যক্তি",
                "বিখ্যাত স্থান ও স্থাপত্য",
                "বিশ্ব ভুগোল",
                "বিভিন্ন দেশের বিমান সংস্থা",
                "সংগঠন ও সংস্থা",
                "সংবাদ সংস্থা",
                "আন্তর্জাতিক মুদ্রা"
        );

        chapterListenglish = Arrays.asList(
                "Preposition",
                "Phrase & Idioms",
                "Voice",
                "Narration",
                "Transformation",
                "Synonyms",
                "Antonym",
                "Translation",
                "Re-writes",
                "Spelling"
        );

        if(subName.contains("Bio1")){
            adapterSetter(chapterListView,chapterListbio1,subName);
        }else if(subName.contains("Bio2")){
            adapterSetter(chapterListView,chapterListbio2,subName);
        }else if(subName.contains("Ch1")){
            adapterSetter(chapterListView,chapterListchem1,subName);
        }else if(subName.contains("Ch2")){
            adapterSetter(chapterListView,chapterListchem2,subName);
        }else if(subName.contains("Ph1")){
            adapterSetter(chapterListView,chapterListphy1,subName);
        }else if(subName.contains("Ph2")){
            adapterSetter(chapterListView,chapterListphy2,subName);
        }else if(subName.contains("gKnow")){
            adapterSetter(chapterListView,chapterListgKnow,subName);
        }else if(subName.contains("English")){
            adapterSetter(chapterListView,chapterListenglish,subName);
        }

    }




    private void adapterSetter(ListView listView, List<String > list, final String subName){
        listView.setAdapter(new ChapterAdapter(ChapterActivity.this, list));

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String chapterName=(String) adapterView.getItemAtPosition(i);
//                Toast.makeText(ChapterActivity.this,chapterName, Toast.LENGTH_SHORT).show();
//                String apiStr=subName+"/"+chapterName;
//                Intent intent = new Intent(ChapterActivity.this, ChapterQuestionActivity.class);
//                intent.putExtra("apiStr",apiStr);
//                startActivity(intent);

                final String chapterName = (String) adapterView.getItemAtPosition(i);
                final String apiStr= chapterName + " অধ্যায় থেকে ৫০ টি প্রশ্ন দিয়ে পরীক্ষা দিন। পরীক্ষাটি দিতে হলে আপনার একাউন্ট থেকে ৫ টাকা কেটে নেয়া " +
                        "হবে।";


                final String toSendString=subName+"/"+chapterName;
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();


                final Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://missiondmc.ml/")
                        //    .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                QuestionClient questionClient = retrofit.create(QuestionClient.class);

////////////////////////////////////////////////////////////////////////////



                Call<List<ExamHistory>> call= questionClient.e_history_user(user.getUid());
     //           instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) ChapterActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_chapter_exam,null);
                buttonPopupExam = (Button) customView.findViewById(R.id.buttonPopupExam);
                buttonPopupSeeQstn = (Button) customView.findViewById(R.id.buttonPopupSeeQstn);
                linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
                textViewPopup = (TextView) customView.findViewById(R.id.textViewPopup);
                linearPopup =(LinearLayout) customView.findViewById(R.id.linearPopup);
                linearPopup.setBackgroundColor(Color.parseColor("#2D2419"));
                textViewPopup.setText(apiStr);
                popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
                // display the popup window

                //initiate popupWindow


                final ExamHistory bought=new ExamHistory(user.getUid(), user.getDisplayName(),0, chapterName, "C", (float) 0.0);

                call.enqueue(new Callback<List<ExamHistory>>() {
                    @Override
                    public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {
                        //System.out.println("hcud");
                        checker=false;

                        e_historys = response.body();


                        for (ExamHistory curInstance: e_historys) {
                            String checkerStr=curInstance.getQuestionName();
                            if(checkerStr.equals(chapterName) || checkerStr.equals("SUPERMED") || checkerStr.equals("SUPERVAR")) {
                                checker = true;

                            }
                        }

                        if(checker==false) {
                        popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                            // popupWindow.setOutsideTouchable(true);
                            popupWindow.update();

                            buttonPopupExam.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

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
                                            Toast.makeText(ChapterActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                                            System.out.print("FUFU"+ response.body());

                                            popupWindow.dismiss();
                                            Intent intent = new Intent(ChapterActivity.this, ChapterQuestionActivity.class);
                                            intent.putExtra("apiStr", toSendString);

                                            startActivity(intent);

                                        }

                                        @Override
                                        public void onFailure(Call<ExamHistory> call, Throwable t) {
                                            Toast.makeText( ChapterActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            });

                            buttonPopupSeeQstn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    popupWindow.dismiss();
                                    Intent intent = new Intent(ChapterActivity.this, ChapterQuestionActivity.class);
                                    intent.putExtra("apiStr", toSendString);
                                    startActivity(intent);
                                }
                            });
                        }
                        else {
                            Intent intent = new Intent(ChapterActivity.this, ChapterQuestionActivity.class);
                            intent.putExtra("apiStr", toSendString);
                            startActivity(intent);
                        }


                    }

                    @Override
                    public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                        Toast.makeText(ChapterActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });


////////////////////////////////////////////////////////////////////////
            }
        });

    }

}




//package com.popupwindow;
//
//import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//
//public class MainActivity extends AppCompatActivity {
//    Button showPopupBtn, closePopupBtn;
//    PopupWindow popupWindow;
//    LinearLayout linearLayout1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        showPopupBtn = (Button) findViewById(R.id.showPopupBtn);
//        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
//
//        showPopupBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.popup,null);
//
//                closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
//
//                //instantiate popup window
//                popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//
//                //display the popup window
//                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
//
//                //close the popup window on button click
//                closePopupBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }
//                });
//
//            }
//        });
//    }
//}
