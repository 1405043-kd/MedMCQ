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

public class PackageActivityVarsity extends AppCompatActivity {

    Button varsityPackageBuyButton;
    Button buttonVarsityConfirm;
    Button buttonVarsityCancel;
    LinearLayout linearLayoutPopup;
    LinearLayout linearLayoutVarsityPackage;
    PopupWindow popupWindow;
    String apiStr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_varsity);

        if(popupWindow!=null && popupWindow.isShowing())
        {
            popupWindow.dismiss();
        }

        varsityPackageBuyButton = (Button) findViewById(R.id.button_varsityPackage_Buy);

        varsityPackageBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) PackageActivityVarsity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView =layoutInflater.inflate(R.layout.popup_varsity_package,null);
                buttonVarsityConfirm = (Button) customView.findViewById(R.id.buttonPopupVarsityPackageConfirm);
                buttonVarsityCancel = (Button) customView.findViewById(R.id.buttonPopupVarsityPackageCancel);

                linearLayoutPopup = (LinearLayout) customView.findViewById(R.id.linearPopupVarsityPackage);
                linearLayoutPopup.setBackgroundColor(Color.parseColor("#2D2419"));

                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                linearLayoutVarsityPackage = (LinearLayout) findViewById(R.id.linearLayoutVarsityPackage);
                popupWindow.showAtLocation(linearLayoutVarsityPackage, Gravity.CENTER, 0, 0);
                // popupWindow.setOutsideTouchable(true);
                popupWindow.update();

                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    apiStr = extras.getString("button");
                }

                buttonVarsityConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                        Intent intent = new Intent(PackageActivityVarsity.this, PackageBuyStatusActivity.class);
                        intent.putExtra("button",apiStr);
                        startActivity(intent);
                    }
                });

                buttonVarsityCancel.setOnClickListener(new View.OnClickListener() {
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
