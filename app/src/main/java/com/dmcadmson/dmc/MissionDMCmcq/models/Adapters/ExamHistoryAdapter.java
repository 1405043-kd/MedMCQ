package com.dmcadmson.dmc.MissionDMCmcq.models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.dmcadmson.dmc.MissionDMCmcq.ArchiveActivity;
import com.dmcadmson.dmc.MissionDMCmcq.ChapterActivity;
import com.dmcadmson.dmc.MissionDMCmcq.R;
import com.dmcadmson.dmc.MissionDMCmcq.models.ExamHistory;
import com.dmcadmson.dmc.MissionDMCmcq.models.QuestionSet;

import java.util.List;

public class ExamHistoryAdapter extends ArrayAdapter<ExamHistory> {
    private Context context;
    private List<ExamHistory> examHistories;
    private List<QuestionSet> questionSets;


    public ExamHistoryAdapter(ArchiveActivity archiveActivity, List<ExamHistory> examHistories, List<QuestionSet> questionSets) {
        super(archiveActivity, R.layout.archive_pagination_item, examHistories);
        context=archiveActivity;
        this.examHistories=examHistories;
        this.questionSets=questionSets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.archive_pagination_item, parent, false);
        }

        // TextView textView = (TextView) row.findViewById(R.id.chapter_item_pagination_text);
//
        ExamHistory itemT = examHistories.get(position);



        //     textView.setText(itemT);

        String questinoName="";

        Button button =(Button) row.findViewById(R.id.childAcrchButton);

        for(int i=0;i<questionSets.size();i++){
            System.out.println(itemT.getQuestionId()+"fmlForeveR"+questionSets.get(i).getId());

            if(questionSets.get(i).getId()==itemT.getQuestionId())
                questinoName=questionSets.get(i).getQuestionName();

        }

       // if(itemT.getTableName().contains("M")||itemT.getTableName().contains("V")||itemT.getTableName().contains("D"))
         //   button.setText(questinoName+" "+itemT.getTableName());
        //else
        button.setText(questinoName);

        button.setFocusable(false);
        button.setClickable(false);

        return row;
    }
}
