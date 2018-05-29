package com.example.sakib.myapplication.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.sakib.myapplication.ChapterActivity;
import com.example.sakib.myapplication.R;

import java.util.List;

public class ChapterAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> chapters;


    public ChapterAdapter(ChapterActivity chapterActivity, List<String> chapterList) {
        super(chapterActivity, R.layout.chapter_list_item_pagination, chapterList);
        context=chapterActivity;
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
