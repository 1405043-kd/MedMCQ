package com.example.dmc.MissionDMCmcq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dmc.MissionDMCmcq.models.Adapters.ArchieveAnsAdapter;
import com.example.dmc.MissionDMCmcq.models.QuestionClient;
import com.example.dmc.MissionDMCmcq.models.Questions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArchieveQuestionActivity extends AppCompatActivity{

    int QID=0;
    ListView lv;
    ProgressBar progressBar;
    List<Questions> questions=new ArrayList<Questions>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_question);
            try {

                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    QID = extras.getInt("apiQN");
                }
                lv = (ListView) findViewById(R.id.arch_question_pagination_list);
                progressBar = (ProgressBar) findViewById(R.id.progressBar2);


                final Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://missiondmc.ml/")
                        //    .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                final QuestionClient questionClient = retrofit.create(QuestionClient.class);

               // fetchQuestions(QID, questionClient);

                Call<List<Questions>> call_cq =questionClient.cqID(String.valueOf(QID));

                call_cq.enqueue(new Callback<List<Questions>>() {
                    @Override
                    public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {


                        progressBar.setVisibility(View.GONE);
                        //  System.out.println("hcud"+"lllllllllllllllllllllllllll"+response.body().size());

                        questions = response.body();
                        //System.out.print(response.body().get(0).getOption1());
                        //Toast.makeText(ArchieveQuestionActivity.this, response.body().get(0).getOption1(), Toast.LENGTH_SHORT).show();


                        lv.setAdapter(new ArchieveAnsAdapter(ArchieveQuestionActivity.this, questions));


                    }
                    @Override
                    public void onFailure(Call<List<Questions>> call, Throwable t) {

                        Toast.makeText(ArchieveQuestionActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ArchieveQuestionActivity.this, NOTREADYActivity.class);

                        startActivity(intent);
                    }
                });

            }
            catch (Exception e){
                Intent intent = new Intent(ArchieveQuestionActivity.this, NOTREADYActivity.class);

                startActivity(intent);
            }

    }

//    public void fetchQuestions(int id, QuestionClient client){
//
//
//
//    }
}
