package com.example.dmc.MissionDMCmcq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmc.MissionDMCmcq.models.ExamHistory;
import com.example.dmc.MissionDMCmcq.models.QuestionClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PackageBuyStatusActivity extends AppCompatActivity {

    String apiStr="";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_buy_status);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            apiStr = extras.getString("button");
        }



            textView = findViewById(R.id.textViewPackageStatus);
        //ei textView te set kora lagbe j package buy successful hoice kina

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient questionClient = retrofit.create(QuestionClient.class);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();






        if(apiStr.contains("medical")){

            ExamHistory examHistory= new ExamHistory(user.getUid(), user.getDisplayName(),7070809,"SUPERMED" ,"PMA", 666);
            sendExamHistory(examHistory);
        }
        else if(apiStr.contains("varsity")){
            ExamHistory examHistory= new ExamHistory(user.getUid(), user.getDisplayName(), 7070809,"SUPERVAR","PVA", 666);
            sendExamHistory(examHistory);
        }


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
               // Toast.makeText(PackageBuyStatusActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
                //System.out.print("FUFU"+ response.body());
            }

            @Override
            public void onFailure(Call<ExamHistory> call, Throwable t) {
                Toast.makeText(PackageBuyStatusActivity.this, "আপনার একাঊন্টে টাকা নেই। রিচার্জ করুন।", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
