package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.Adapters.ArchieveAnsAdapter;
import com.example.sakib.myapplication.models.Adapters.ExamHistoryAdapter;
import com.example.sakib.myapplication.models.ExamHistory;
import com.example.sakib.myapplication.models.QuestionClient;
import com.example.sakib.myapplication.models.QuestionSet;
import com.example.sakib.myapplication.models.Questions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArchiveActivity extends AppCompatActivity {

    List<ExamHistory> examHistories=new ArrayList<ExamHistory>();
    List<QuestionSet> questionSet=new ArrayList<QuestionSet>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        listView=(ListView) findViewById(R.id.archive_pagination_list);




        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        final QuestionClient questionClient = retrofit.create(QuestionClient.class);

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

                        for(int i=0;i<questionSet.size();i++){
                            for(int j=0;j<examHistories.size();j++){
                                if(questionSet.get(i).getId()==examHistories.get(j).getQuestionId()){
                                    if(questionSet.get(i).getQuestionName().contains("20")||questionSet.get(i).getQuestionName().contains("19"))
                                        examHistories.remove(j);
                                }
                            }
                        }



                        listView.setAdapter(new ExamHistoryAdapter(ArchiveActivity.this, examHistories, questionSet));

                        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                  ExamHistory eHist=(ExamHistory) adapterView.getItemAtPosition(i);

                                  Toast.makeText(ArchiveActivity.this, Integer.toString(eHist.getQuestionId()), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ArchiveActivity.this, ArchieveQuestionActivity.class);

                                intent.putExtra("apiQN",eHist.getQuestionId());
                                startActivity(intent);



                            }
                        });

                    }
                    @Override
                    public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                        Toast.makeText(ArchiveActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            @Override
            public void onFailure(Call<List<QuestionSet>> call, Throwable t) {
                Toast.makeText(ArchiveActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
