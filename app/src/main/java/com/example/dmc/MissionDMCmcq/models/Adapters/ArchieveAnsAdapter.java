package com.example.dmc.MissionDMCmcq.models.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dmc.MissionDMCmcq.R;
import com.example.dmc.MissionDMCmcq.models.Questions;

import java.util.List;

public class ArchieveAnsAdapter extends ArrayAdapter<Questions>{
    private Context context;
    private List<Questions> values;
    private String[] answers;
    private boolean[] checkedList= new boolean[500];
//    public int selectedId=0;
//    RadioButton selectedAnswer;

    public ArchieveAnsAdapter(Context context, List<Questions> values) {
        super(context, R.layout.activity_archive_question, values);

        this.context = context;
        this.values = values;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolderAnswer holder = null;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.correct_answer_item, parent, false);
            holder = new ViewHolderAnswer(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolderAnswer) row.getTag();
        }

        Questions item = values.get(position);

        String question = Integer.toString(position+1) + ". " + item.getQuestion();
        String option1 = "(A) " + item.getOption1();
        String option2 = "(B) " + item.getOption2();
        String option3 = "(C) " + item.getOption3();
        String option4 = "(D)" + item.getOption4();
        String correctAns = item.getCorrectAns();


        holder.t.setText(question);
        holder.ro1.setText(option1);
        holder.ro2.setText(option2);
        holder.ro3.setText(option3);
        holder.ro4.setText(option4);
        holder.co.setText("Correct Ans: "+correctAns);


//            TextView textView = (TextView) row.findViewById(R.id.answer_item_pagination_text);
//            // RadioGroup r = (RadioGroup) row.findViewById(R.id.radio_grou);
//            TextView ro1 = (TextView) row.findViewById(R.id.option1);
//            TextView ro2 = (TextView) row.findViewById(R.id.option2);
//            TextView ro3 = (TextView) row.findViewById(R.id.option3);
//            TextView ro4 = (TextView) row.findViewById(R.id.option4);



        return row;

    }
}

