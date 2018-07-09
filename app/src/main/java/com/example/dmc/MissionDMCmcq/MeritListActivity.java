package com.example.dmc.MissionDMCmcq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmc.MissionDMCmcq.models.Adapters.ExamHistoryAdapter;
import com.example.dmc.MissionDMCmcq.models.Adapters.MeritListAdapter;
import com.example.dmc.MissionDMCmcq.models.ExamHistory;
import com.example.dmc.MissionDMCmcq.models.QuestionClient;
import com.example.dmc.MissionDMCmcq.models.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeritListActivity extends AppCompatActivity {

    String apiStr="";
    ProgressBar progressBar;
    List<ExamHistory> results=new ArrayList<ExamHistory>();
    TextView rankView;
    TextView nameView;
    TextView numberView;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merit_list);
        listView = (ListView) findViewById(R.id.rankList);

       // rankView= (TextView) findViewById(R.id.id_rank_merit2);



        progressBar=(ProgressBar) findViewById(R.id.progressBar_meritList);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apiStr = extras.getString("apiStr");
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MeritListActivity.this, apiStr, Toast.LENGTH_SHORT).show();
        }

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient questionClient = retrofit.create(QuestionClient.class);

        Call<List<ExamHistory>> call_eh;

        call_eh = questionClient.get_history_daily(apiStr);


        call_eh.enqueue(new Callback<List<ExamHistory>>() {
            @Override
            public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {
                //     System.out.println("hcud");

                results= response.body();
                Collections.sort(results);

                listView.setAdapter(new MeritListAdapter(MeritListActivity.this, results));



//                String jaDe="";
//                int i=1;
//                for (ExamHistory curInstance: results) {
//                    jaDe+=i+".       "+curInstance.getUserName()+"         "+String.valueOf(curInstance.getMarks())+"\n";
//                    i++;
//                }
//                rankView.setText(jaDe);

                //System.out.print(results.get(0).getUserName()+results.get(0).getMarks()+"coHu");
                //Toast.makeText(MeritListActivity.this, String.valueOf(results.get(0).getUserName())+"ugan", Toast.LENGTH_SHORT).show();


                //        listView.setAdapter(new ExamHistoryAdapter(ArchiveActivity.this, examHistories, questionSet));

            }
            @Override
            public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                Toast.makeText(MeritListActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
