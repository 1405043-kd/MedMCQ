package com.example.sakib.myapplication.models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakib.myapplication.R;
import com.example.sakib.myapplication.models.Questions;

import java.util.List;


/**
 * Created by norman on 12/26/16.
 */

public class QuestionAdapter extends ArrayAdapter<Questions> {

    private Context context;
    private List<Questions> values;
    private String[] answers;
    public int selectedId=0;
    RadioButton selectedAnswer;

    public QuestionAdapter(Context context, List<Questions> values,String[] answers) {
        super(context, R.layout.question_pagination_item, values);

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
            row = inflater.inflate(R.layout.question_pagination_item, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.question_item_pagination_text);
        RadioGroup r = (RadioGroup) row.findViewById(R.id.radio_group);
        RadioButton ro1 = (RadioButton) row.findViewById(R.id.option1radio);
        RadioButton ro2 = (RadioButton) row.findViewById(R.id.option2radio);
        RadioButton ro3 = (RadioButton) row.findViewById(R.id.option3radio);
        RadioButton ro4 = (RadioButton) row.findViewById(R.id.option4radio);

        Questions item = values.get(position);

        String question = Integer.toString(position) + ". " + item.getQuestion();
        String option1 = "(A) "+ item.getOption1();
        String option2 = "(B) " +item.getOption2();
        String option3 = "(C) "+ item.getOption3();
        String option4 = "(D)" + item.getOption4();
        String correctAns = item.getCorrectAns();

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
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                int pos = (int) radioGroup.getTag();
//                data[pos].selectedId = radioGroup.getCheckedRadioButtonId();
                selectedId = radioGroup.getCheckedRadioButtonId();
                String s = Integer.toString(selectedId);
                if(selectedId!=0) {
                    selectedAnswer = finalRow.findViewById(selectedId);

                    String answera= (String) selectedAnswer.getText();
                    System.out.println(answera + "fooch");
                    answers[pos] = selectedAnswer.getText().toString();
                }

//                Toast.makeText(QuestionAdapter.this, s, Toast.LENGTH_SHORT).show();
            }
        });
//
//





        // find the radiobutton by returned id


//        Toast.makeText(QuestionAdapter.this,
//                selectedAnswer.getText(), Toast.LENGTH_SHORT).show();



        return row;
    }
}