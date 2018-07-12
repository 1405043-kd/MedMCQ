package com.dmcadmson.dmc.MissionDMCmcq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PackageActivity extends AppCompatActivity {

    Button medicalPackageButton;
    Button dentalPackageButton;

    LinearLayout linearLayout11;
    LinearLayout linearPopup11;
    TextView textViewPopup11;
    PopupWindow popupWindow2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        medicalPackageButton= (Button) findViewById(R.id.id_medical_packageButton);
        dentalPackageButton = (Button) findViewById(R.id.id_varsity_packageButton);

        medicalPackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PackageActivity.this, PackageActivityMedical.class);
                intent.putExtra("button","medical");
                startActivity(intent);
            }
        });

        dentalPackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PackageActivity.this, PackageActivityVarsity.class);
//                intent.putExtra("button","varsity");
//                startActivity(intent);

                LayoutInflater layoutInflater = (LayoutInflater) PackageActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_varsity_package_delay_notice,null);

                linearLayout11 = (LinearLayout) findViewById(R.id.linearLayoutPackage);
                textViewPopup11 = (TextView) customView.findViewById(R.id.textViewPackage);
                linearPopup11 =(LinearLayout) customView.findViewById(R.id.linearPopupPackage);
                linearPopup11.setBackgroundColor(Color.parseColor("#2D2419"));
                //textViewPopup.setText(apiStr);
                popupWindow2 = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

                popupWindow2.showAtLocation(linearLayout11, Gravity.CENTER, 0, 0);
                popupWindow2.update();

            }
        });
    }
}
