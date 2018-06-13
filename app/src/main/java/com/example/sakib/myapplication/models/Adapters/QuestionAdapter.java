package com.example.sakib.myapplication.models.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    TextView textView;
    RadioGroup r;
    RadioButton ro1,ro2,ro3,ro4;

    public QuestionAdapter(Context context, List<Questions> values,String[] answers) {
        super(context, R.layout.question_pagination_item, values);

        this.context = context;
        this.values = values;
        this.answers = answers;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.question_pagination_item, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
            holder.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    Integer pos = (Integer) radioGroup.getTag();
                    Questions item = values.get(pos);

                    switch (i) { // set the Model to hold the
                        // answer the user picked
                        case R.id.option1radio:
                            answers[pos] = "A";
                            break;
                        case R.id.option2radio:
                            answers[pos] = "B";
                            break;
                        case R.id.option3radio:
                            answers[pos] = "C";
                            break;
                        case R.id.option4radio:
                            answers[pos] = "D";
                            break;
                        default:
                            answers[pos] = null;// Something was

                    }
                }
            });
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Questions item = values.get(position);
        holder.group.setTag(new Integer(position)); // I passed the current
        holder.t.setText(Integer.toString(position+1)+". "+item.getQuestion());

        RadioButton r1 = (RadioButton) holder.group.getChildAt(0);
        r1.setText(item.getOption1());
        RadioButton r2 = (RadioButton) holder.group.getChildAt(1);
        r2.setText(item.getOption2());
        RadioButton r3 = (RadioButton) holder.group.getChildAt(2);
        r3.setText(item.getOption3());
        RadioButton r4 = (RadioButton) holder.group.getChildAt(3);
        r4.setText(item.getOption4());

        if(answers[position]!=null) {

            if (answers[position].contains("A")) {
                RadioButton r = (RadioButton) holder.group.getChildAt(0);
                r.setChecked(true);
            } else if (answers[position].contains("B")) {
                RadioButton r = (RadioButton) holder.group.getChildAt(1);
                r.setChecked(true);
            } else if (answers[position].contains("C")) {
                RadioButton r = (RadioButton) holder.group.getChildAt(2);
                r.setChecked(true);
            } else if (answers[position].contains("D")) {
                RadioButton r = (RadioButton) holder.group.getChildAt(3);
                r.setChecked(true);
            }
        }
        else {
            holder.group.clearCheck(); // This is required because although the
            // Model could have the current
            // position to NONE you could be dealing
            // with a previous row where
            // the user already picked an answer.

        }

//        textView = (TextView) row.findViewById(R.id.question_item_pagination_text);
//        r = (RadioGroup) row.findViewById(R.id.radio_group);
//        ro1 = (RadioButton) row.findViewById(R.id.option1radio);
//        ro2 = (RadioButton) row.findViewById(R.id.option2radio);
//        ro3 = (RadioButton) row.findViewById(R.id.option3radio);
//        ro4 = (RadioButton) row.findViewById(R.id.option4radio);
//
//
//        String question = Integer.toString(position+1) + ". " + item.getQuestion();
//        String option1 = "(A) "+ item.getOption1();
//        String option2 = "(B) " +item.getOption2();
//        String option3 = "(C) "+ item.getOption3();
//        String option4 = "(D)" + item.getOption4();
//        String correctAns = item.getCorrectAns();
//
//        textView.setText(question);
//        ro1.setText(option1);
//    //    ro1.setChecked(false);
//        ro2.setText(option2);
//    //    ro2.setChecked(false);
//        ro3.setText(option3);
//    //    ro3.setChecked(false);
//        ro4.setText(option4);
//      //  ro4.setChecked(false);
//
//
//
//
////        Button button =(Button) row.findViewById(R.id.childButton);
////        button.setText(message);
////        button.setFocusable(false);
////        button.setClickable(false);
//
////
////        final View finalRow = row;
////        final int pos = position;
//
//        String ans="";
//        if(answers[position]!=null) {
//            ans = answers[position].substring(answers[position].indexOf("(") + 1, answers[position].indexOf(")"));
//            if (ans.contains("A")) {
////                r.check(ro1.getId());
//                ro1.setChecked(true);
//            }
//            else if (ans.contains("B")) {
////                r.check(ro2.getId());
//                ro2.setChecked(true);
//            }
//            else if (ans.contains("C")) {
////                r.check(ro3.getId());
//                ro3.setChecked(true);
//            }
//            else if (ans.contains("D")) {
////                r.check(ro4.getId());
//                ro4.setChecked(true);
//            }
//        }
//        else {
//            ro1.setChecked(false);
//            ro2.setChecked(false);
//            ro3.setChecked(false);
//            ro4.setChecked(false);
//
//
//            final View finalRow = row;
//            r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup radioGroup, int i) {
////                int pos = (int) radioGroup.getTag();
////                data[pos].selectedId = radioGroup.getCheckedRadioButtonId();
//                    //selectedId = radioGroup.getCheckedRadioButtonId();
//                    if (answers[position] == null) {
//                        selectedId = i;
//                        String s = Integer.toString(selectedId);
//                        if (selectedId != 0) {
//                            selectedAnswer = finalRow.findViewById(selectedId);
//                            selectedAnswer.setChecked(true);
//
//                            String answera = (String) selectedAnswer.getText();
//                            System.out.println(answera + "fooch");
//                            answers[position] = selectedAnswer.getText().toString();
//                        }
//                    }
////                Toast.makeText(QuestionAdapter.this, s, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
////
////
////        String ans = "";
////        // r.setOnCheckedChangeListener(null);
//
//
//
//
//
//        // find the radiobutton by returned id
//
//
////        Toast.makeText(QuestionAdapter.this,
////                selectedAnswer.getText(), Toast.LENGTH_SHORT).show();



        return row;
    }
}
class ViewHolder {
    TextView t = null;
    RadioGroup group;

    ViewHolder(View v) {
        t = (TextView) v.findViewById(R.id.question_item_pagination_text);
        group = (RadioGroup) v.findViewById(R.id.radio_group);
    }

}