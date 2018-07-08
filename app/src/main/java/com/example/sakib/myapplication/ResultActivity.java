package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sakib.myapplication.models.Adapters.CorrectAnswerAdpater;
import com.example.sakib.myapplication.models.Adapters.QuestionAdapter;
import com.example.sakib.myapplication.models.Questions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity{

    TextView tv1;
    TextView tv2;

    ArrayList<Questions> que= new ArrayList<>();
    String []ans;
    Float res= Float.valueOf(0);
    String q;
    ListView listView;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
//        System.out.println("eikhanei asi france");
//        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
      //      q=extras.getParcelableArrayList("que");
            q = extras.getString("que");
            ans=extras.getStringArray("ans");
            res=extras.getFloat("res");
            Gson gson=new Gson();
            que = gson.fromJson(q, new TypeToken<ArrayList<Questions>>(){}.getType());

        }



    ///    tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.totalText);
//
  //      tv1.setText("Result");
        tv2.setText("You scored: "+Float.toString(res));

        listView = (ListView) findViewById(R.id.answer_pagination_list);
        listView.setAdapter(new CorrectAnswerAdpater(ResultActivity.this, que, ans));

        for (int i=0;i<ans.length;i++){
        //    System.out.println(que.get(i).getQuestion()+"chum");
            System.out.println(que.get(i).getQuestion()+" shat "+ans[i]+" fafua");
//
        }


    }
}
