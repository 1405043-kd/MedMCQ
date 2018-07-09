package com.example.dmc.MissionDMCmcq;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by AbhiAndroid
 */

public class SplashActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);
        TextView tv=findViewById(R.id.tv_id);
        ImageView iview=findViewById(R.id.logo_id);
        tv.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, android.R.anim.slide_in_left));
//        iview.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, android.R.anim.slide_out_right));
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}