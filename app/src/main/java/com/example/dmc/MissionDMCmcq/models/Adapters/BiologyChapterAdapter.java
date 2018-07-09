package com.example.dmc.MissionDMCmcq.models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.dmc.MissionDMCmcq.BiologyChapterActivity;
import com.example.dmc.MissionDMCmcq.ChapterActivity;
import com.example.dmc.MissionDMCmcq.R;

import java.util.List;

public class BiologyChapterAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> chapters;


    public BiologyChapterAdapter(BiologyChapterActivity biologyChapterActivity, List<String> chapterList) {
        super(biologyChapterActivity, R.layout.chapter_list_item_pagination, chapterList);
        context=biologyChapterActivity;
        chapters=chapterList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chapter_list_item_pagination, parent, false);
        }

        // TextView textView = (TextView) row.findViewById(R.id.chapter_item_pagination_text);

        String itemT = chapters.get(position);
        //     textView.setText(itemT);

        Button button =(Button) row.findViewById(R.id.childButton);
        button.setText(itemT);
        button.setFocusable(false);
        button.setClickable(false);

        return row;
    }
}
