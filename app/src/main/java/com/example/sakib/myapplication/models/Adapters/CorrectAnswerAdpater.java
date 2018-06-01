package com.example.sakib.myapplication.models.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sakib.myapplication.R;
import com.example.sakib.myapplication.models.Questions;

import java.util.List;

public class CorrectAnswerAdpater extends ArrayAdapter<Questions>{
    private Context context;
    private List<Questions> values;
    private String[] answers;
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

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.correct_answer_item, parent, false);


            TextView textView = (TextView) row.findViewById(R.id.answer_item_pagination_text);
            // RadioGroup r = (RadioGroup) row.findViewById(R.id.radio_grou);
            TextView ro1 = (TextView) row.findViewById(R.id.option1);
            TextView ro2 = (TextView) row.findViewById(R.id.option2);
            TextView ro3 = (TextView) row.findViewById(R.id.option3);
            TextView ro4 = (TextView) row.findViewById(R.id.option4);

            Questions item = values.get(position);

            String question = Integer.toString(position) + ". " + item.getQuestion();
            String option1 = "(A) " + item.getOption1();
            String option2 = "(B) " + item.getOption2();
            String option3 = "(C) " + item.getOption3();
            String option4 = "(D)" + item.getOption4();
            String correctAns = item.getCorrectAns();
            String hisAns = answers[position];
            if (hisAns == null)
                hisAns = "E";

            textView.setText(question);
            ro1.setText(option1);
            ro2.setText(option2);
            ro3.setText(option3);
            ro4.setText(option4);


//        Button button =(Button) row.findViewById(R.id.childButton);
//        button.setText(message);
//        button.setFocusable(false);
//        button.setClickable(false);

//
            final View finalRow = row;
            final int pos = position;


//        if(answers[position]!=null) {
//            if (answers[position].contains("A")) {
//                ro1.setBackgroundColor(0xfbf4e10e);
//            } else if (answers[position].contains("B")) {
//                ro2.setBackgroundColor(0xfbf4e10e);
//            } else if (answers[position].contains("C")) {
//                ro3.setBackgroundColor(0xfbf4e10e);
//            } else if (answers[position].contains("D")) {
//                ro4.setBackgroundColor(0xfbf4e10e);
//            }
//        }


            if(correctAns.contains("A")){
                ro1.setBackgroundColor(0xaa06bb00);
            }else if(correctAns.contains("B")){
                ro2.setBackgroundColor(0xaa06bb00);
            }
            else if(correctAns.contains("C")){
                ro3.setBackgroundColor(0xaa06bb00);
            }
            else if(correctAns.contains("D")){
                ro4.setBackgroundColor(0xaa06bb00);
            }


            if (hisAns.contains("A")) {
                ro1.setTextColor(Color.RED);
            } else if (hisAns.contains("B")) {
                ro2.setTextColor(Color.RED);
                ;
            } else if (hisAns.contains("C")) {
                ro3.setTextColor(Color.RED);
            } else if (hisAns.contains("D")) {
                ro4.setTextColor(Color.RED);
            }
//        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
////                int pos = (int) radioGroup.getTag();
////                data[pos].selectedId = radioGroup.getCheckedRadioButtonId();
//                selectedId = radioGroup.getCheckedRadioButtonId();
//                String s = Integer.toString(selectedId);
//                if(selectedId!=0) {
//                    selectedAnswer = finalRow.findViewById(selectedId);
//
//                    String answera= (String) selectedAnswer.getText();
//                    System.out.println(answera + "fooch");
//                    answers[pos] = selectedAnswer.getText().toString();
//                }
//
////                Toast.makeText(QuestionAdapter.this, s, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//


            // find the radiobutton by returned id


//        Toast.makeText(QuestionAdapter.this,
//                selectedAnswer.getText(), Toast.LENGTH_SHORT).show();


        }
        return row;

    }
}
