package com.dmcadmson.dmc.MissionDMCmcq;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dmcadmson.dmc.MissionDMCmcq.models.Adapters.ArchieveAnsAdapter;
import com.dmcadmson.dmc.MissionDMCmcq.models.Adapters.ExamHistoryAdapter;
import com.dmcadmson.dmc.MissionDMCmcq.models.ExamHistory;
import com.dmcadmson.dmc.MissionDMCmcq.models.QuestionClient;
import com.dmcadmson.dmc.MissionDMCmcq.models.QuestionSet;
import com.dmcadmson.dmc.MissionDMCmcq.models.Questions;
import com.dmcadmson.dmc.MissionDMCmcq.models.Users;
import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dmcadmson.dmc.MissionDMCmcq.R.drawable.doctor;

public class ProfileActivity extends AppCompatActivity {
    TextView Balance;
    TextView Name;
    TextView ExamAppeared;
    TextView Email;
    Users userReceived = new Users();

    Button rechargeButton;
    List<Questions> questions=new ArrayList<Questions>();
    ImageView propic2;


    //Exams appeared
    List<ExamHistory> examHistories=new ArrayList<ExamHistory>();
    List<QuestionSet> questionSet=new ArrayList<QuestionSet>();
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        Balance = (TextView) findViewById(R.id.balance_text);
        Balance.setText("Connect internet to see balance");
        Name = (TextView) findViewById(R.id.name_text);
        ExamAppeared = (TextView) findViewById(R.id.exam_appeared_text);
        Email = (TextView) findViewById(R.id.email_text);
        propic2 = (ImageView) findViewById(R.id.propic);
        Email.setText(user.getEmail());
        Name.setText(user.getDisplayName());
        if (user.getPhotoUrl() != null) {
           // Toast.makeText(ProfileActivity.this, "sdfdsg :(", Toast.LENGTH_SHORT).show();
            Glide.with(this)
                    .load(mAuth.getCurrentUser().getPhotoUrl())
                    .into(propic2);
        } else {
            //Toast.makeText(ProfileActivity.this, "error sdfdsg :(", Toast.LENGTH_SHORT).show();
            Glide.with(this)
                    .load(doctor)
                    .into(propic2);
        }



            Retrofit.Builder builder2 = new Retrofit.Builder()
                    .baseUrl("http://missiondmc.ml/")
                    //    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit2 = builder2.build();
            QuestionClient eClient = retrofit2.create(QuestionClient.class);
            Call<Users> call = eClient.get_user(user.getUid());

            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    //    Toast.makeText(ProfileActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                    //  System.out.print("FUFU"+ response.body());
                    userReceived = response.body();
                    //  System.out.println(response.body().getName()+"makulu");
                    //  System.out.println(userReceived.getName()+"uhuhmaumu");
                    //Toast.makeText(ProfileActivity.this, userReceived.getProviderID(), Toast.LENGTH_SHORT).show();
                    Balance.setText(Float.toString(userReceived.getBalance()));
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                   // Toast.makeText(ProfileActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
                }
            });

            ////Exams appeard code
             final QuestionClient questionClient = retrofit2.create(QuestionClient.class);
            Call<List<QuestionSet>> call_qs =questionClient.q_set();
            call_qs.enqueue(new Callback<List<QuestionSet>>() {
            @Override
            public void onResponse(Call<List<QuestionSet>> call, Response<List<QuestionSet>> response) {
                questionSet = response.body();

                System.out.println(response.body().get(0).getId()+response.body().get(0).getQuestionName()+"moreche");
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

                Call<List<ExamHistory>> call_cq =questionClient.e_history_user(user.getUid());


                call_cq.enqueue(new Callback<List<ExamHistory>>() {
                    @Override
                    public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {
                        System.out.println("hcud");

                        examHistories = response.body();

                        System.out.println(examHistories.size() + "bal");
                        //Toast.makeText(ProfileActivity.this, examHistories.size(), Toast.LENGTH_SHORT).show();

                        for(int i=0;i<questionSet.size();i++){
                            for(int j=0;j<examHistories.size();j++){
                                if(questionSet.get(i).getId()==examHistories.get(j).getQuestionId()){
                                    if(questionSet.get(i).getQuestionName().contains("20")||questionSet.get(i).getQuestionName().contains("19"))
                                        examHistories.remove(j);
                                }
                            }
                        }

                        int examCount = examHistories.size();
                        String string = ""+examCount;
                        ExamAppeared.setText(string);

                        //listView.setAdapter(new ExamHistoryAdapter(ArchiveActivity.this, examHistories, questionSet));

//                        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                ExamHistory eHist=(ExamHistory) adapterView.getItemAtPosition(i);
//
//                                // Toast.makeText(ArchiveActivity.this, Integer.toString(eHist.getQuestionId()), Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(ArchiveActivity.this, ArchieveQuestionActivity.class);
//
//                                intent.putExtra("apiQN",eHist.getQuestionId());
//                                startActivity(intent);
//
//
//
//                            }
//                        });

                    }
                    @Override
                    public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                       // Toast.makeText(ProfileActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            @Override
            public void onFailure(Call<List<QuestionSet>> call, Throwable t) {
                //Toast.makeText(ProfileActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

            rechargeButton = (Button) findViewById(R.id.rechargeProfile);
            rechargeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfileActivity.this, RechargeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
