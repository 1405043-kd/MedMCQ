package com.dmcadmson.dmc.MissionDMCmcq;

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

import com.dmcadmson.dmc.MissionDMCmcq.models.Adapters.ChapterAdapter;
import com.dmcadmson.dmc.MissionDMCmcq.models.Adapters.MediDentalPreviousAdapter;
import com.dmcadmson.dmc.MissionDMCmcq.models.ExamHistory;
import com.dmcadmson.dmc.MissionDMCmcq.models.QuestionClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private boolean checker=false;
    private List<ExamHistory> e_historys=new ArrayList<ExamHistory>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subName = extras.getString("subName");
        }
        //Toast.makeText(this, subName,Toast.LENGTH_SHORT).show();

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

                final String chapterName=(String) adapterView.getItemAtPosition(i);

                final String apiStr = subName + "/" + chapterName;
               // Toast.makeText(MediDentalPreviousActivity.this, apiStr, Toast.LENGTH_SHORT).show();


                //new code 07-07-2018
                LayoutInflater layoutInflater = (LayoutInflater) MediDentalPreviousActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_medi_dental_taka_vikkha,null);
                buttonPopupExam = (Button) customView.findViewById(R.id.buttonPopupMediDentalExam);
               // buttonPopupSeeQuestion = (Button) customView.findViewById(R.id.buttonPopupMediDentalSeeQstn);
                linearLayout11 = (LinearLayout) findViewById(R.id.linearLayoutMediDentalMain);
                textViewPopup11 = (TextView) customView.findViewById(R.id.textViewPopupMediDental);
                linearPopup11 =(LinearLayout) customView.findViewById(R.id.linearPopupMediDental);
                linearPopup11.setBackgroundColor(Color.parseColor("#2D2419"));
                textViewPopup11.setText(chapterName + " এই পরীক্ষা দেয়ার জন্য আপনার একাউন্ট থেকে শুধুমাত্র প্রথমবার ৩ টাকা কেটে নেয়া হবে।");
                popupWindow2 = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);




                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();


                final Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://missiondmc.ml/")
                        //    .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                QuestionClient questionClient = retrofit.create(QuestionClient.class);




                String toS=" / ";
                if (apiStr.contains("med") || apiStr.contains("den")) {
                    toS = returnUrlX(apiStr);

                }
                String[] parts = toS.split("/");
                final String part1 = parts[0]; // 004
                String part2 = parts[1]; // 034556


                final ExamHistory bought=new ExamHistory(user.getUid(), user.getDisplayName(),0, part1, "X", (float) 0.0);
                Call<List<ExamHistory>> call= questionClient.e_history_user(user.getUid());
                call.enqueue(new Callback<List<ExamHistory>>() {
                    @Override
                    public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {
                        //System.out.println("hcud");
                        checker=false;

                        e_historys = response.body();


                        for (ExamHistory curInstance: e_historys) {
                            String checkerStr=curInstance.getQuestionName();
                            if(checkerStr.equals(part1) || checkerStr.equals("SUPERMED") || checkerStr.equals("SUPERVAR")) {
                                checker = true;
                            }
                        }

                        if(checker==false) {
                            popupWindow2.showAtLocation(linearLayout11, Gravity.CENTER, 0, 0);
                            popupWindow2.update();
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
                                           // Toast.makeText(MediDentalPreviousActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                                            System.out.print("FUFU"+ response.body());

                                            popupWindow2.dismiss();
                                            Intent intent = new Intent(MediDentalPreviousActivity.this, ChapterQuestionActivity.class);
                                            intent.putExtra("apiStr", apiStr);

                                            startActivity(intent);

                                        }

                                        @Override
                                        public void onFailure(Call<ExamHistory> call, Throwable t) {
                                            Toast.makeText( MediDentalPreviousActivity.this, "আপনার একাঊন্টে টাকা নেই। রিচার্জ করুন।", Toast.LENGTH_SHORT).show();

                                        }
                                    });


                                }
                            });

//                            buttonPopupSeeQstn.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    popupWindow.dismiss();
//                                    Intent intent = new Intent(ChapterActivity.this, ChapterQuestionActivity.class);
//
//                                   // Intent intent = new Intent(ChapterActivity.this, ResultActivity.class);
//                                    intent.putExtra("apiStr", toSendString);
//                                    startActivity(intent);
//                                }
//                            });
                        }
                        else {
                            Intent intent = new Intent(MediDentalPreviousActivity.this, ChapterQuestionActivity.class);
                            intent.putExtra("apiStr", apiStr);
                            startActivity(intent);
                        }


                    }

                    @Override
                    public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                        Toast.makeText(MediDentalPreviousActivity.this, "error :(", Toast.LENGTH_SHORT).show();
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


}
