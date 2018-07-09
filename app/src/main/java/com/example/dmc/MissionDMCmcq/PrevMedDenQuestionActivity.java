package com.example.dmc.MissionDMCmcq;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.ToneGenerator;

import com.example.dmc.MissionDMCmcq.models.Adapters.QuestionAdapter;
import com.example.dmc.MissionDMCmcq.models.QuestionClient;
import com.example.dmc.MissionDMCmcq.models.Questions;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrevMedDenQuestionActivity extends AppCompatActivity{
    private static final long START_TIME_IN_MILLIS = 600000;
    String apiStr="";
    ListView questionList;
    private TextView mTextViewCountDown;
    private static CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 300);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_question);
        mTextViewCountDown = (TextView) findViewById(R.id.text_view_countdown);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apiStr = extras.getString("apiStr");
        }

        Toast.makeText(PrevMedDenQuestionActivity.this,apiStr, Toast.LENGTH_SHORT).show();
        startTimer();
        questionList = (ListView) findViewById(R.id.question_pagination_list);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:8000/")
                //    .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        QuestionClient questionClient = retrofit.create(QuestionClient.class);

        Call<List<Questions>> call_cq =questionClient.xqAll();


        call_cq.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                System.out.println("hcud");
                List<Questions> questions = response.body();

                String []answers=new String[questions.size()+1];

                questionList.setAdapter(new QuestionAdapter(PrevMedDenQuestionActivity.this, questions, answers));
                questionList.setOnItemClickListener(new ListView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Questions questions=(Questions) adapterView.getItemAtPosition(i);

                        Toast.makeText(PrevMedDenQuestionActivity.this, questions.getQuestion(), Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(PrevMedDenQuestionActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                Toast.makeText(PrevMedDenQuestionActivity.this, "start"+mTimeLeftInMillis, Toast.LENGTH_SHORT).show();
                updateCountDownText();
            }

            @Override
            public void onFinish() {
            }
        }.start();

    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        Toast.makeText(PrevMedDenQuestionActivity.this, "asf"+timeLeftFormatted, Toast.LENGTH_SHORT).show();
        mTextViewCountDown.setText(timeLeftFormatted);
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
    }

}

