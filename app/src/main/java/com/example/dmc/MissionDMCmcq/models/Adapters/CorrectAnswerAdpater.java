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

public class CorrectAnswerAdpater extends ArrayAdapter<Questions>{
    private Context context;
    private List<Questions> values;
    private String[] answers;
    private boolean[] checkedList= new boolean[500];
//    public int selectedId=0;
//    RadioButton selectedAnswer;

    public CorrectAnswerAdpater(Context context, List<Questions> values,String[] answers) {
        super(context, R.layout.correct_answer_item, values);

        this.context = context;
        this.values = values;
        this.answers = answers;

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
        String hisAns = answers[position];

        if (hisAns == null)
            hisAns = "E";

        holder.t.setText(question);
        holder.ro1.setText(option1);
        holder.ro2.setText(option2);
        holder.ro3.setText(option3);
        holder.ro4.setText(option4);
        holder.co.setText("Your Ans: "+answers[position]+"|| Correct Ans: "+correctAns);
        if(holder.isChecked==false) {
            holder.isChecked=true;

//            if (correctAns.contains("A")) {
//                holder.ro1.setBackgroundColor(0xaa06bb00);
//            } else if (correctAns.contains("B")) {
//                holder.ro2.setBackgroundColor(0xaa06bb00);
//            } else if (correctAns.contains("C")) {
//                holder.ro3.setBackgroundColor(0xaa06bb00);
//            } else if (correctAns.contains("D")) {
//                holder.ro4.setBackgroundColor(0xaa06bb00);
//            }
//
//
//            if (hisAns.contains("A")) {
//                holder.ro1.setTextColor(Color.RED);
//            } else if (hisAns.contains("B")) {
//                holder.ro2.setTextColor(Color.RED);
//                ;
//            } else if (hisAns.contains("C")) {
//                holder.ro3.setTextColor(Color.RED);
//            } else if (hisAns.contains("D")) {
//                holder.ro4.setTextColor(Color.RED);
//            }

        }


//            TextView textView = (TextView) row.findViewById(R.id.answer_item_pagination_text);
//            // RadioGroup r = (RadioGroup) row.findViewById(R.id.radio_grou);
//            TextView ro1 = (TextView) row.findViewById(R.id.option1);
//            TextView ro2 = (TextView) row.findViewById(R.id.option2);
//            TextView ro3 = (TextView) row.findViewById(R.id.option3);
//            TextView ro4 = (TextView) row.findViewById(R.id.option4);



        return row;

    }
}


class ViewHolderAnswer {
    boolean isChecked=false;
    TextView t = null;
    TextView ro1 = null;
    TextView ro2 = null;
    TextView ro3 = null;
    TextView ro4 = null;
    TextView co = null;


    ViewHolderAnswer(View v) {
        t = (TextView) v.findViewById(R.id.answer_item_pagination_text);
        ro1 = (TextView) v.findViewById(R.id.option1);
        ro2 = (TextView) v.findViewById(R.id.option2);
        ro3 = (TextView) v.findViewById(R.id.option3);
        ro4 = (TextView) v.findViewById(R.id.option4);
        co = (TextView) v.findViewById(R.id.correctAnsw);
    }

}