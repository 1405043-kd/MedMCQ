package com.example.sakib.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PackageActivityMedical extends AppCompatActivity {

    Button medicalPackageBuyButton;
    Button buttonConfirm;
    Button buttonCancel;
    LinearLayout linearLayout;
    LinearLayout linearLayoutMediPackage;
    PopupWindow popupWindow;
    String apiStr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_medical);


        if(popupWindow!=null && popupWindow.isShowing())
        {
            popupWindow.dismiss();
        }

        medicalPackageBuyButton = findViewById(R.id.button_medicalPackage_Buy);

        medicalPackageBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) PackageActivityMedical.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_medical_package,null);
                buttonConfirm = (Button) customView.findViewById(R.id.buttonPopupMedicalPackageConfirm);
                buttonCancel = (Button) customView.findViewById(R.id.buttonPopupMedicalPackageCancel);

                linearLayout = (LinearLayout) customView.findViewById(R.id.linearPopupMedicalPackage);
                linearLayout.setBackgroundColor(Color.parseColor("#2D2419"));


                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    apiStr = extras.getString("button");
                }


                popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
                linearLayoutMediPackage = (LinearLayout) findViewById(R.id.linearLayoutMedicalPackage);
                popupWindow.showAtLocation(linearLayoutMediPackage, Gravity.CENTER, 0, 0);
                // popupWindow.setOutsideTouchable(true);
                popupWindow.update();

                buttonConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Intent intent = new Intent(PackageActivityMedical.this, PackageBuyStatusActivity.class);
                        intent.putExtra("button",apiStr);
                        startActivity(intent);
                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(popupWindow!=null && popupWindow.isShowing())
                        {
                            popupWindow.dismiss();
                        }
                    }
                });

            }
        });
    }
}
