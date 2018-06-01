package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.Adapters.QuestionAdapter;
import com.example.sakib.myapplication.models.ExamHistory;
import com.example.sakib.myapplication.models.QuestionClient;
import com.example.sakib.myapplication.models.Questions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Build.VERSION_CODES.M;

public class ChapterQuestionActivity extends AppCompatActivity {
    String apiStr="";
    ListView questionList;
    Button buttonSubmit;
    String []answers;
    List<Questions> questions=new ArrayList<Questions>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_question);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apiStr = extras.getString("apiStr");
        }

 //       Toast.makeText(ChapterQuestionActivity.this,apiStr, Toast.LENGTH_SHORT).show();

        questionList = (ListView) findViewById(R.id.question_pagination_list);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:8000/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient questionClient = retrofit.create(QuestionClient.class);

        Call<List<Questions>> call_cq =questionClient.cqAll();

        buttonSubmit = (Button) findViewById(R.id.subBut);

        call_cq.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                System.out.println("hcud");

                questions = response.body();

                answers=new String[questions.size()];

                questionList.setAdapter(new QuestionAdapter(ChapterQuestionActivity.this, questions, answers));

                questionList.setOnItemClickListener(new ListView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Questions questions=(Questions) adapterView.getItemAtPosition(i);

                  //      Toast.makeText(ChapterQuestionActivity.this, questions.getQuestion(), Toast.LENGTH_SHORT).show();

                    }
                });



                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        float total=0;

//                        Toast.makeText(ChapterQuestionActivity.this, answers[0]+answers[1]+answers[2],Toast.LENGTH_SHORT).show();
                        for(int ii=0; ii<questions.size();ii++)
                        {

                            System.out.println(questions.size()+" ybo "+answers.length);
                            String ans= "";
//                            answers[ii]=answers[ii]+"( haha )"+ "lol(DE)";
                            if(answers[ii]!=null) {
                                ans = answers[ii].substring(answers[ii].indexOf("(") + 1, answers[ii].indexOf(")"));
                                Toast.makeText(ChapterQuestionActivity.this, ans, Toast.LENGTH_SHORT).show();
                                if (questions.get(ii).getCorrectAns().contains(ans))
                                    total += 1;
                                else
                                    total -= 0.25;
                                System.out.println(ans + "yo" + Float.toString(total) + " " + questions.get(ii).getCorrectAns() + " yobabes");
                            }
                        }

                        ExamHistory examHistory= new ExamHistory(1,questions.get(0).getQuestionId(),"C", total);
                        sendExamHistory(examHistory);

                        Toast.makeText(ChapterQuestionActivity.this, Float.toString(total), Toast.LENGTH_SHORT).show();
                        launchactivityResult(total);
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
                .baseUrl("http://192.168.0.104:8000/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit2 = builder2.build();
        QuestionClient eClient = retrofit2.create(QuestionClient.class);
        Call<ExamHistory> call= eClient.post_e_history(ehisotry);

        call.enqueue(new Callback<ExamHistory>() {
            @Override
            public void onResponse(Call<ExamHistory> call, Response<ExamHistory> response) {
                Toast.makeText(ChapterQuestionActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                System.out.print("FUFU"+ response.body());
            }

            @Override
            public void onFailure(Call<ExamHistory> call, Throwable t) {
                Toast.makeText(ChapterQuestionActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void launchactivityResult(Float valueA)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("Result",valueA);
        startActivity(intent);
    }
}
