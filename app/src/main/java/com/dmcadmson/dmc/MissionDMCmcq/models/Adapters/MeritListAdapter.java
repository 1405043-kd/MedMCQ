package com.dmcadmson.dmc.MissionDMCmcq.models.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dmcadmson.dmc.MissionDMCmcq.R;
import com.dmcadmson.dmc.MissionDMCmcq.models.ExamHistory;
import com.dmcadmson.dmc.MissionDMCmcq.models.Questions;
import com.dmcadmson.dmc.MissionDMCmcq.models.Ranks;

import java.util.List;

public class MeritListAdapter extends ArrayAdapter<ExamHistory>{
    private Context context;
    private List<ExamHistory> values;
    private boolean[] checkedList= new boolean[500];
//    public int selectedId=0;
//    RadioButton selectedAnswer;

    public MeritListAdapter(Context context, List<ExamHistory> values) {
        super(context, R.layout.activity_merit_list, values);

        this.context = context;
        this.values = values;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolderRank holder = null;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_merit_pagination_list, parent, false);
            holder = new ViewHolderRank(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolderRank) row.getTag();
        }

        ExamHistory item = values.get(position);
        int position2=position+1;
        String userRank = Integer.toString(position2);
        String userName = item.getUserName();
        String userNumber = Float.toString(item.getMarks());



        holder.ro1.setText(userRank);
        holder.ro2.setText(userName);
        holder.ro3.setText(userNumber);



//            TextView textView = (TextView) row.findViewById(R.id.answer_item_pagination_text);
//            // RadioGroup r = (RadioGroup) row.findViewById(R.id.radio_grou);
//            TextView ro1 = (TextView) row.findViewById(R.id.option1);
//            TextView ro2 = (TextView) row.findViewById(R.id.option2);
//            TextView ro3 = (TextView) row.findViewById(R.id.option3);
//            TextView ro4 = (TextView) row.findViewById(R.id.option4);



        return row;

    }
}

class ViewHolderRank {
    boolean isChecked=false;
    TextView ro1 = null;
    TextView ro2 = null;
    TextView ro3 = null;



    ViewHolderRank(View v) {
        ro1 = (TextView) v.findViewById(R.id.id_rank_merit);
        ro2 = (TextView) v.findViewById(R.id.id_name_merit);
        ro3 = (TextView) v.findViewById(R.id.id_number_merit);

    }

}

