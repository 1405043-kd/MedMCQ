package com.dmcadmson.dmc.MissionDMCmcq.models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.dmcadmson.dmc.MissionDMCmcq.ChapterActivity;
import com.dmcadmson.dmc.MissionDMCmcq.MediDentalPreviousActivity;
import com.dmcadmson.dmc.MissionDMCmcq.R;

import java.util.List;

public class MediDentalPreviousAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> years;


    public MediDentalPreviousAdapter(MediDentalPreviousActivity mediDentalPreviousActivity, List<String> yearList) {
        super(mediDentalPreviousActivity, R.layout.chapter_list_item_pagination, yearList);
        context=mediDentalPreviousActivity;
        years=yearList;
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

        String itemT = years.get(position);
        //     textView.setText(itemT);

        Button button =(Button) row.findViewById(R.id.childButton);
        button.setText(itemT);
        button.setFocusable(false);
        button.setClickable(false);

        return row;
    }
}
