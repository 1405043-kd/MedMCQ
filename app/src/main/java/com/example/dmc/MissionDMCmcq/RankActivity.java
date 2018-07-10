package com.example.dmc.MissionDMCmcq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmc.MissionDMCmcq.models.Adapters.MeritListAdapter;
import com.example.dmc.MissionDMCmcq.models.ExamHistory;
import com.example.dmc.MissionDMCmcq.models.QuestionClient;
import com.example.dmc.MissionDMCmcq.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RankActivity extends AppCompatActivity {

    Button merit_button;
    String apiStr="";
    TextView userNameTextView;
    TextView examNameTextView;
    TextView marksTextView;
    TextView rankTextView;

    List<ExamHistory> userReceived = new ArrayList<ExamHistory>();

    List<ExamHistory> results=new ArrayList<ExamHistory>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);


        userNameTextView = (TextView) findViewById(R.id.id_user_name);
        examNameTextView = (TextView) findViewById(R.id.id_exam_name);
        marksTextView = (TextView) findViewById(R.id.id_number);
        rankTextView = (TextView) findViewById(R.id.id_rank);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apiStr = extras.getString("apiStr");
        }
        //////////////////
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient questionClient = retrofit.create(QuestionClient.class);

        final Call<List<ExamHistory>> call_eh;

        call_eh = questionClient.get_history_daily(apiStr);

        ///////////////////////
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();


        Retrofit.Builder builder2 = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit2 = builder2.build();
        QuestionClient eClient = retrofit2.create(QuestionClient.class);
        Call<List<ExamHistory>> call = eClient.e_history_user(user.getUid());


        call.enqueue(new Callback<List<ExamHistory>>() {
            @Override
            public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {

                userReceived = response.body();
                for(final ExamHistory currentUser : userReceived)
                {
                    //System.out.println("chaal");
                    System.out.println(currentUser.getUserName() + " " + currentUser.getUserId() + " e"+ currentUser.getQuestionId());

                        call_eh.enqueue(new Callback<List<ExamHistory>>() {
                            @Override
                            public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {
                                //     System.out.println("hcud");

                                results= response.body();
                                Collections.sort(results);
                                int  i=1;

                                for (ExamHistory curInstance: results) {
                                    System.out.println("Exam");
                                    if(curInstance.getUserId().equals(currentUser.getUserId()) && curInstance.getUserName().equals(currentUser.getUserName()))
                                    {
                                        System.out.println("jjiufyuy");
                                        String usrName = curInstance.getUserName();
                                        String exmName = curInstance.getQuestionName();
                                        float marksFloat = curInstance.getMarks();
                                        String marks = Float.toString(marksFloat);
                                        marks = "Marks : "+marks;
                                        String rank = Integer.toString(i);
                                        rank = "Rank : "+rank;

                                        userNameTextView.setText(usrName);
                                        examNameTextView.setText(exmName);
                                        marksTextView.setText(marks);
                                        rankTextView.setText(rank);

                                        System.out.println("dhukcere dhukce!!!!!!!!!!!");

                                    }

//                                    System.out.println("username: "+curInstance.getUserId() + " "+curInstance.getUserName());
//                                    System.out.println("uid:"+position[0]);

                                    i++;
                                }

                            }
                            @Override
                            public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                                Toast.makeText(RankActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    }
//                    System.out.println("position : " + position[0]);
//                    position[0]++;

                }


            @Override
            public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
                Toast.makeText(RankActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println("baire");

        ////////////////



//        ///////////
        merit_button = findViewById(R.id.id_button_merit);

        merit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankActivity.this, MeritListActivity.class);
                intent.putExtra("apiStr",apiStr);
                startActivity(intent);
            }
        });

    }
}





//
//    final Retrofit.Builder builder = new Retrofit.Builder()
//            .baseUrl("http://missiondmc.ml/")
//            //    .baseUrl("https://api.github.com/")
//            .addConverterFactory(GsonConverterFactory.create());
//
//    Retrofit retrofit = builder.build();
//    QuestionClient questionClient = retrofit.create(QuestionClient.class);
//
//    final Call<List<ExamHistory>> call_eh;
//
//        call_eh = questionClient.get_history_daily(apiStr);
//
//                ///////////////////////
//                FirebaseAuth mAuth;
//                mAuth = FirebaseAuth.getInstance();
//final FirebaseUser user = mAuth.getCurrentUser();
//
//
//        Retrofit.Builder builder2 = new Retrofit.Builder()
//        .baseUrl("http://missiondmc.ml/")
//        //    .baseUrl("https://api.github.com/")
//        .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit2 = builder2.build();
//        QuestionClient eClient = retrofit2.create(QuestionClient.class);
//        Call<List<Users>> call = eClient.get_users();
//
//
//final int[] position = {-2};
//        call.enqueue(new Callback<List<Users>>() {
//@Override
//public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//
//        userReceived = response.body();
//        for(Users currentUser : userReceived)
//        {
//        //System.out.println("chaal");
//        System.out.println(currentUser.getName() + " " + currentUser.getEmail());
//        if(currentUser.getUserID().equals(user.getUid()))
//        {
//        System.out.println("Hurrah");
//
//
//        call_eh.enqueue(new Callback<List<ExamHistory>>() {
//@Override
//public void onResponse(Call<List<ExamHistory>> call, Response<List<ExamHistory>> response) {
//        //     System.out.println("hcud");
//
//        results= response.body();
//        //Collections.sort(results);
//        int  i=1;
//
//        for (ExamHistory curInstance: results) {
//        System.out.println("Exam");
//        if(curInstance.getUserId().equals(user.getUid()))
//        {
//        System.out.println("jjiufyuy");
//        String usrName = curInstance.getUserName();
//        String exmName = curInstance.getQuestionName();
//        float marksFloat = curInstance.getMarks();
//        String marks = Float.toString(marksFloat);
//        String rank = Integer.toString(i);
//
//        userNameTextView.setText(usrName);
//        examNameTextView.setText(exmName);
//        marksTextView.setText(marks);
//        rankTextView.setText(rank);
//
//        System.out.println("dhukcere dhukce!!!!!!!!!!!");
//
//        }
//
//        System.out.println("username: "+curInstance.getUserId() + " "+curInstance.getUserName());
//        System.out.println("uid:"+position[0]);
//
//        i++;
//        }
//
//        }
//@Override
//public void onFailure(Call<List<ExamHistory>> call, Throwable t) {
//        Toast.makeText(RankActivity.this, "error :(", Toast.LENGTH_SHORT).show();
//        }
//        });
//
//        break;
//        }
//        System.out.println("position : " + position[0]);
//        position[0]++;
//
//        }
//
//        }
//
//@Override
//public void onFailure(Call<List<Users>> call, Throwable t) {
//        Toast.makeText(RankActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
//        }
//        });
//
//        System.out.println("baire");
//
//////////////////
//
//
//
////        ///////////
//
//
////UserId = models.ForeignKey(ProfileMod, on_delete=models.CASCADE)
