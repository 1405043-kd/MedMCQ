package com.example.sakib.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.QuestionClient;
import com.example.sakib.myapplication.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    TextView Balance;
    TextView Name;
    TextView ExamAppeared;
    TextView Email;
    Users userReceived= new Users();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        Balance = (TextView) findViewById(R.id.balance_text);
        Balance.setText("Connect internet to see balance");
        Name =(TextView) findViewById(R.id.name_text);
        ExamAppeared=(TextView) findViewById(R.id.exam_appeared_text);
        Email=(TextView) findViewById(R.id.email_text);

        Email.setText(user.getEmail());
        Name.setText(user.getDisplayName());



        Retrofit.Builder builder2 = new Retrofit.Builder()
                .baseUrl("http://missiondmc.ml/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit2 = builder2.build();
        QuestionClient eClient = retrofit2.create(QuestionClient.class);
        Call<Users> call= eClient.get_user(user.getUid());

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(ProfileActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
              //  System.out.print("FUFU"+ response.body());
                userReceived=response.body();
              //  System.out.println(response.body().getName()+"makulu");
               //  System.out.println(userReceived.getName()+"uhuhmaumu");
                //Toast.makeText(ProfileActivity.this, userReceived.getProviderID(), Toast.LENGTH_SHORT).show();
                Balance.setText(Float.toString(userReceived.getBalance()));


            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Already Exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
