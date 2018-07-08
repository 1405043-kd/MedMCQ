package com.example.sakib.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakib.myapplication.models.QuestionClient;
import com.example.sakib.myapplication.models.Users;
import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.sakib.myapplication.R.drawable.doctor;

public class ProfileActivity extends AppCompatActivity {
    TextView Balance;
    TextView Name;
    TextView ExamAppeared;
    TextView Email;
    Users userReceived= new Users();

    Button rechargeButton;
    ImageView propic2;

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
        propic2=(ImageView)findViewById(R.id.propic);
        Email.setText(user.getEmail());
        Name.setText(user.getDisplayName());
//        if(user.getPhotoUrl()!=null) {
//            Toast.makeText(ProfileActivity.this, "sdfdsg :(", Toast.LENGTH_SHORT).show();
//            propic2.setImageURI(Uri.parse(String.valueOf(user.getPhotoUrl())));
//        }
//        else {
            Toast.makeText(ProfileActivity.this, "error sdfdsg :(", Toast.LENGTH_SHORT).show();
            propic2.setImageResource(doctor);
//        }

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
            //    Toast.makeText(ProfileActivity.this, "yes! :)", Toast.LENGTH_SHORT).show();
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
