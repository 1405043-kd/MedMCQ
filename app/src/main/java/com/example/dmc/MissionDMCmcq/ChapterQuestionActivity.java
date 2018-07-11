package com.example.dmc.MissionDMCmcq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmc.MissionDMCmcq.models.Adapters.QuestionAdapter;
import com.example.dmc.MissionDMCmcq.models.ExamHistory;
import com.example.dmc.MissionDMCmcq.models.QuestionClient;
import com.example.dmc.MissionDMCmcq.models.Questions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Build.VERSION_CODES.M;
import static com.example.dmc.MissionDMCmcq.R.color.black;
import static com.example.dmc.MissionDMCmcq.R.color.red;

public class ChapterQuestionActivity extends AppCompatActivity {
    private static final long WARN_TIME_IN_MILLIS = 10000;
    private static final long JUST_TIME_IN_MILLIS = 60000;
    private TextView mTextViewCountDown;
    private static CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis=JUST_TIME_IN_MILLIS;
    private long mWarningTime = WARN_TIME_IN_MILLIS;
    ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 400);
    private ProgressBar spinner;
    private String part1="";
    private String part2="";
    private String tabuName="";


    String apiStr="";
    ListView questionList;
    Button buttonSubmit;
    String []answers;
    List<Questions> questions=new ArrayList<Questions>();

    Button buttonPopupYes;
    Button buttonPopupNo;
    LinearLayout linearLayout1;
    LinearLayout linearPopup;
    TextView textViewPopup;
    PopupWindow popupWindow;

    Button buttonPopupSubmitYes;
    Button buttonPopupSubmitNo;
    LinearLayout linearLayout11;
    LinearLayout linearPopup11;
    TextView textViewPopup11;
    PopupWindow popupWindow2;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        LayoutInflater layoutInflater = (LayoutInflater) ChapterQuestionActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView =layoutInflater.inflate(R.layout.popup_backpress_exam,null);
        buttonPopupYes = (Button) customView.findViewById(R.id.buttonPopupBackpressYes);
        buttonPopupNo = (Button) customView.findViewById(R.id.buttonPopupBackpressNo);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayoutQuestionBack);
        textViewPopup = (TextView) customView.findViewById(R.id.textViewPopupBack);
        linearPopup =(LinearLayout) customView.findViewById(R.id.linearPopupBack);
        linearPopup.setBackgroundColor(Color.parseColor("#2D2419"));
        //textViewPopup.setText(apiStr);
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
        popupWindow.update();

        buttonPopupYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                mCountDownTimer.cancel();
                mCountDownTimer=null;
                ChapterQuestionActivity.super.onBackPressed();
            }
        });
        buttonPopupNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_question);

        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apiStr = extras.getString("apiStr");
        }

 //       Toast.makeText(ChapterQuestionActivity.this,apiStr, Toast.LENGTH_SHORT).show();

        questionList = (ListView) findViewById(R.id.question_pagination_list);
        mTextViewCountDown = (TextView) findViewById(R.id.text_view_countdown);
        mTextViewCountDown.setBackgroundColor(Color.parseColor("#000000"));
        mTextViewCountDown.setVisibility(View.GONE);

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient questionClient = retrofit.create(QuestionClient.class);

        Call<List<Questions>> call_cq;

        if(apiStr.contains("DAILY")){

            call_cq = questionClient.cqSubChap("daily",apiStr);
            tabuName="C";
        }
        else {

            if (apiStr.contains("med") || apiStr.contains("den")) {
                apiStr = returnUrlX(apiStr);
                if(apiStr.contains("med"))
                    tabuName="XM";
                else
                    tabuName="XD";
            }
            String[] parts = apiStr.split("/");
            part1 = parts[0]; // 004
            part2 = parts[1]; // 034556

            if (part1.contains("Bio1") || part1.contains("Bio2") || part1.contains("Ph1") || part1.contains("Ph2") || part1.contains("Ch1")
                    || part1.contains("Ch2") || part1.contains("gKnow") || part1.contains("English")) {
                tabuName="C";
                call_cq = questionClient.cqSubChap(part1, part2);
            } else {
                call_cq = questionClient.xqYearMVD(part1, part2);
            }
        }
        buttonSubmit = (Button) findViewById(R.id.subBut);
        buttonSubmit.setVisibility(View.GONE);

        call_cq.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
               // System.out.println("hcudFUFUFUF"+response.body().size());


                try {
                    questions = response.body();
                    answers = new String[questions.size()];


                    //chapter name
                    try{
                        String chapterName = new String(questions.get(0).getChapterName()) ;
                        chapterName = "পরীক্ষাঃ " + chapterName;
                        System.out.println(chapterName);
                        getSupportActionBar().setTitle(chapterName);
                    }catch (Exception e) {
                        //Intent intent = new Intent(ChapterQuestionActivity.this, AboutUsActivity.class);
                        System.out.println("kochu is my favourite");
                        String chapterName ;
                        if(apiStr.contains("M"))
                        {
                            chapterName = "Medical Question: " ;
                            chapterName = chapterName + part1;
                            getSupportActionBar().setTitle(chapterName);
                        }
                        else if(apiStr.contains("D"))
                        {
                            chapterName = "Dental Question: ";
                            chapterName = chapterName + part1;
                            getSupportActionBar().setTitle(chapterName);
                        }

                    }


                    /////////////////////////////
                    questionList.setAdapter(new QuestionAdapter(ChapterQuestionActivity.this, questions, answers));

                    questionList.setOnItemClickListener(new ListView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Questions questions=(Questions) adapterView.getItemAtPosition(i);


                            //      Toast.makeText(ChapterQuestionActivity.this, questions.getQuestion(), Toast.LENGTH_SHORT).show();

                        }
                    });

                    spinner.setVisibility(View.GONE);
                    buttonSubmit.setVisibility(View.VISIBLE);
                    mTextViewCountDown.setVisibility(View.VISIBLE);
                    int nx= questions.size();

                    startTimer(nx);

                }
                catch (Exception e){
                    //Intent intent = new Intent(ChapterQuestionActivity.this, AboutUsActivity.class);
                    Intent intent = new Intent(ChapterQuestionActivity.this, NOTREADYActivity.class);
                    String string = "এই ডেইলি এক্সামটি এখনো অনুষ্ঠিত হয়নি। পরীক্ষার সময়সূচী নোটিস বোর্ডে জানিয়ে দেয়া হবে।";

                    intent.putExtra("apiStr", string);
                    startActivity(intent);

//                    Intent intent = new Intent(ChapterQuestionActivity.this, NOTREADYActivity.class);
//                    startActivity(intent);
                }





                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /////////////////////////////popup

                        LayoutInflater layoutInflater = (LayoutInflater) ChapterQuestionActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View customView =layoutInflater.inflate(R.layout.popup_submit,null);
                        buttonPopupSubmitYes = (Button) customView.findViewById(R.id.buttonPopupSubmitYes);
                        buttonPopupSubmitNo = (Button) customView.findViewById(R.id.buttonPopupSubmitNo);
                        linearLayout11 = (LinearLayout) findViewById(R.id.linearLayoutQuestionBack);
                        textViewPopup11 = (TextView) customView.findViewById(R.id.textViewPopupSubmit);
                        linearPopup11 =(LinearLayout) customView.findViewById(R.id.linearPopupSubmit);
                        linearPopup11.setBackgroundColor(Color.parseColor("#2D2419"));
                        //textViewPopup.setText(apiStr);
                        popupWindow2 = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                        popupWindow2.showAtLocation(linearLayout11, Gravity.CENTER, 0, 0);
                        popupWindow2.update();

                        buttonPopupSubmitYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow2.dismiss();
                                mCountDownTimer.cancel();
                                mCountDownTimer=null;
                                float total=0;

//                        Toast.makeText(ChapterQuestionActivity.this, answers[0]+answers[1]+answers[2],Toast.LENGTH_SHORT).show();
                                for(int ii=0; ii<questions.size();ii++)
                                {

                                    System.out.println(questions.size()+" ybo "+answers.length);
                                    String ans= answers[ii];
                                    System.out.println("wohowo"+Integer.toString(ii+1)+ans+"wohowo"+questions.get(ii).getCorrectAns());
//                            answers[ii]=answers[ii]+"( haha )"+ "lol(DE)";
                                    if(ans!=null) {
                                        //ans = answers[ii].substring(answers[ii].indexOf("(") + 1, answers[ii].indexOf(")"));
                                        //answers[ii]=ans;
                                    ////    Toast.makeText(ChapterQuestionActivity.this, ans, Toast.LENGTH_SHORT).show();
                                        if (questions.get(ii).getCorrectAns().contains(ans))
                                            total += 1;
                                        else
                                            total -= 0.25;
                                        System.out.println(ans + "yo" + Float.toString(total) + " " + questions.get(ii).getCorrectAns() + " yobabes");
                                    }
                                }

                                FirebaseAuth mAuth;
                                mAuth = FirebaseAuth.getInstance();
                                FirebaseUser user = mAuth.getCurrentUser();
                                ExamHistory examHistory;

                                if(apiStr.contains("DAILY")){
                                    examHistory = new ExamHistory(user.getUid(), user.getDisplayName(), questions.get(0).getQuestionId(), apiStr, "C", total);
                                }
                                else {
                                    if (tabuName.contains("C"))
                                        examHistory = new ExamHistory(user.getUid(), user.getDisplayName(), questions.get(0).getQuestionId(), part2, "C", total);
                                    else
                                        examHistory = new ExamHistory(user.getUid(), user.getDisplayName(), questions.get(0).getQuestionId(), part1, "X", total);
                                }
                                sendExamHistory(examHistory);

                            ////    Toast.makeText(ChapterQuestionActivity.this, Float.toString(total), Toast.LENGTH_SHORT).show();
                                Bundle bundle=new Bundle();
                                String listSerializedToJson = new Gson().toJson(questions);
                                //    bundle.putString("questions",listSerializedToJson);
                                List<Questions> ques=questions;
                                //  bundle.putParcelableArrayList("que", (ArrayList<? extends Parcelable>) ques);
                                bundle.putString("que",listSerializedToJson);
                                bundle.putStringArray("ans",answers);
                                bundle.putFloat("res",total);

//                                launchActivityResult(bundle);

                                if(apiStr.contains("DAILY")){
                                    Intent intent = new Intent(ChapterQuestionActivity.this, NOTREADYActivity.class);
                                    String string = "আপনার পরীক্ষা দেয়া সম্পন্ন হয়েছে। রাত ১২ টায় আর্কাইভে গিয়ে এই ডেইলি এক্সামের নাম এর বাটনে ক্লিক করলে উত্তরপত্র " +
                                            "দেখতে পাবেন। আর আপনার প্রাপ্ত নাম্বার ও বর্তমান র‍্যাংক দেখতে চাইলে র‍্যাংক  দেখুন বাটনে ক্লিক করুন। আপনাকে দেখানো র‍্যাংক সময়ের" +
                                            "সাথে সাথে আরও পরীক্ষার্থী অংশগ্রহণ ও তাদের প্রাপ্ত নাম্বারের ভিত্তিতে পরিবর্তিত হবে।";

                                    intent.putExtra("apiStr", string);
                                    startActivity(intent);
//                                    Intent intent = new Intent( ChapterQuestionActivity.this, NOTREADYActivity.class);
//                                    startActivity(intent);
                                }
                                else launchActivityResult(bundle);
                            }
                        });
                        buttonPopupSubmitNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupWindow2.dismiss();
                            }
                        });


                        /////////////////////////


                    }
                });

            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(ChapterQuestionActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sendExamHistory(ExamHistory ehisotry){
        Retrofit.Builder builder2 = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit2 = builder2.build();
        QuestionClient eClient = retrofit2.create(QuestionClient.class);
        Call<ExamHistory> call= eClient.post_e_history(ehisotry);

        call.enqueue(new Callback<ExamHistory>() {
            @Override
            public void onResponse(Call<ExamHistory> call, Response<ExamHistory> response) {
            //    Toast.makeText(ChapterQuestionActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                //System.out.print("FUFU"+ response.body());
            }

            @Override
            public void onFailure(Call<ExamHistory> call, Throwable t) {
            //    Toast.makeText(ChapterQuestionActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void launchActivityResult(Bundle bundle) {
        Intent intent = new Intent(this, ResultActivity.class);
       // intent.putExtra("Result",valueA);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private String returnUrlX(String s) {
        if (s.contains("med")) {
            if (s.contains("২০১৬-২০১৭"))
                return "2016M/M";
            else if (s.contains("২০১৭-২০১৮")) {
                return "2017M/M";
            } else if (s.contains("২০১৫-২০১৬")) {
                return "2015M/M";
            } else if (s.contains("২০১৪-২০১৫")) {
                return "2014M/M";
            } else if (s.contains("২০১৩-২০১৪")) {
                return "2013M/M";
            } else if (s.contains("২০১২-২০১৩")) {
                return "2012M/M";
            } else if (s.contains("২০১১-২০১২")) {
                return "2011M/M";
            } else if (s.contains("২০১০-২০১১")) {
                return "2010M/M";
            } else if (s.contains("২০০৯-২০১০")) {
                return "2009M/M";
            } else if (s.contains("২০০৮-২০০৯")) {
                return "2008M/M";
            } else if (s.contains("২০০৭-২০০৮")) {
                return "2007M/M";
            } else if (s.contains("২০০৬-২০০৭")) {
                return "2006M/M";
            } else if (s.contains("২০০৫-২০০৬")) {
                return "2005M/M";
            } else if (s.contains("২০০৪-২০০৫")) {
                return "2004M/M";
            } else if (s.contains("২০০৩-২০০৪")) {
                return "2003M/M";
            } else if (s.contains("২০০২-২০০৩")) {
                return "2002M/M";
            } else if (s.contains("২০০১-২০০২")) {
                return "2001M/M";
            } else if (s.contains("২০০০-২০০১")) {
                return "2000M/M";
            } else if (s.contains("১৯৯৯-২০০০")) {
                return "1999M/M";
            } else if (s.contains("১৯৯৮-১৯৯৯")) {
                return "1998M/M";
            } else if (s.contains("১৯৯৭-১৯৯৮")) {
                return "1997M/M";
            } else if (s.contains("১৯৯৬-১৯৯৭")) {
                return "1996M/M";
            } else if (s.contains("১৯৯৫-১৯৯৬")) {
                return "1995M/M";
            } else if (s.contains("১৯৯৪-১৯৯৫")) {
                return "1994M/M";
            } else if (s.contains("১৯৯৩-১৯৯৪")) {
                return "1993M/M";
            } else if (s.contains("১৯৯২-১৯৯৩")) {
                return "1992M/M";
            } else if (s.contains("১৯৯১-১৯৯২")) {
                return "1991M/M";
            } else if (s.contains("১৯৯০-১৯৯১")) {
                return "1990M/M";
            } else if (s.contains("১৯৯০-১৯৯১(১)")) {
                return "1990-1M/M";
            } else if (s.contains("১৯৮৯-১৯৯০")) {
                return "1989M/M";
            } else if (s.contains("১৯৮৮-১৯৮৯")) {
                return "1988M/M";
            }

        } else if (s.contains("den")) {
            if (s.contains("২০১৬-২০১৭"))
                return "2016D/D";
            else if (s.contains("২০১০-২০১১")) {
                return "2010D/D";
            } else if (s.contains("২০০৯-২০১০")) {
                return "2009D/D";
            } else if (s.contains("২০০৮-২০০৯")) {
                return "2008D/D";
            } else if (s.contains("২০০৭-২০০৮")) {
                return "2007D/D";
            } else if (s.contains("২০০৬-২০০৭")) {
                return "2006D/D";
            } else if (s.contains("২০০৫-২০০৬")) {
                return "2005D/D";
            } else if (s.contains("২০০৪-২০০৫")) {
                return "2004D/D";
            } else if (s.contains("২০০৩-২০০৪")) {
                return "2003D/D";
            } else if (s.contains("২০০২-২০০৩")) {
                return "2002D/D";
            } else if (s.contains("২০০১-২০০২")) {
                return "2001D/D";
            } else if (s.contains("২০০০-২০০১")) {
                return "2000D/D";
            } else if (s.contains("১৯৯৯-২০০০")) {
                return "1999D/D";
            } else if (s.contains("১৯৯৮-১৯৯৯")) {
                return "1998D/D";
            } else if (s.contains("১৯৯৭-১৯৯৮")) {
                return "1997D/D";
            } else if (s.contains("১৯৯৬-১৯৯৭")) {
                return "1996D/D";
            } else if (s.contains("১৯৯৫-১৯৯৬")) {
                return "1995D/D";
            }


        }
        return "";
    }

    private void startTimer(int n) {
        int x=30000*n;

        mCountDownTimer = new CountDownTimer(x,1000) {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
             //   Toast.makeText(ChapterQuestionActivity.this, "start"+mTimeLeftInMillis, Toast.LENGTH_SHORT).show();
                updateCountDownText();
                if (millisUntilFinished<=WARN_TIME_IN_MILLIS) {
                    toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    mTextViewCountDown.setBackgroundColor(Color.parseColor("#FF0000"));
                }
            }

            @Override
            public void onFinish() {
                toneGen1.stopTone();
                mCountDownTimer.cancel();
                mCountDownTimer=null;
                float total=0;

//                        Toast.makeText(ChapterQuestionActivity.this, answers[0]+answers[1]+answers[2],Toast.LENGTH_SHORT).show();
                for(int ii=0; ii<questions.size();ii++)
                {

                    System.out.println(questions.size()+" ybo "+answers.length);
                    String ans= answers[ii];
                    System.out.println("wohowo"+Integer.toString(ii+1)+ans+"wohowo"+questions.get(ii).getCorrectAns());
//                            answers[ii]=answers[ii]+"( haha )"+ "lol(DE)";
                    if(ans!=null) {
                        //ans = answers[ii].substring(answers[ii].indexOf("(") + 1, answers[ii].indexOf(")"));
                        //answers[ii]=ans;
                    //    Toast.makeText(ChapterQuestionActivity.this, ans, Toast.LENGTH_SHORT).show();
                        if (questions.get(ii).getCorrectAns().contains(ans))
                            total += 1;
                        else
                            total -= 0.25;
                        System.out.println(ans + "yo" + Float.toString(total) + " " + questions.get(ii).getCorrectAns() + " yobabes");
                    }
                }

                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                ExamHistory examHistory;


                if(apiStr.contains("DAILY")){
                    examHistory = new ExamHistory(user.getUid(), user.getDisplayName(), questions.get(0).getQuestionId(), apiStr, "C", total);
                }
                else {
                    if (tabuName.contains("C"))
                        examHistory = new ExamHistory(user.getUid(), user.getDisplayName(), questions.get(0).getQuestionId(), part2, "C", total);
                    else
                        examHistory = new ExamHistory(user.getUid(), user.getDisplayName(), questions.get(0).getQuestionId(), part1, "X", total);
                }
                sendExamHistory(examHistory);

           ////     Toast.makeText(ChapterQuestionActivity.this, Float.toString(total), Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                String listSerializedToJson = new Gson().toJson(questions);
                //    bundle.putString("questions",listSerializedToJson);
                List<Questions> ques=questions;
                //  bundle.putParcelableArrayList("que", (ArrayList<? extends Parcelable>) ques);
                bundle.putString("que",listSerializedToJson);
                bundle.putStringArray("ans",answers);
                bundle.putFloat("res",total);

                launchActivityResult(bundle);
                //buttonSubmit.performClick();
            }
        }.start();

    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
     //   Toast.makeText(ChapterQuestionActivity.this, "asf"+timeLeftFormatted, Toast.LENGTH_SHORT).show();
        mTextViewCountDown.setText(timeLeftFormatted);
     //   toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
    }




}
