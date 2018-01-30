package com.personaldev.redditfeed.redditfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.personaldev.redditfeed.redditfeed.AppFunctions.AppGraphics;

public class SplashScreen extends AppCompatActivity {

    // Variables of the activity
    public AppGraphics appGraphics;
    public Animation anim = new AlphaAnimation(0.0f, 1.0f);

    public TextView text_00;
    public LinearLayout linear_00, linear_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initiates the classes needed
        appGraphics = new AppGraphics(this);

        // Coordinate with the UI elements
        text_00 = (TextView) findViewById(R.id.prompt_text);
        linear_00 = (LinearLayout) findViewById(R.id.linear_000);
        linear_01 = (LinearLayout) findViewById(R.id.linear_001);

        // Give the prompting text a blinking effect
        anim.setDuration(500);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        text_00.startAnimation(anim);

    }

    // Proceeds to the Menu Tab Screen activity
    public void onMenuScreenProceed(View v){
        // Heads to the Menu Tab Screen activity
        startActivity(new Intent(getApplicationContext(), MenuScreen.class));
        finish();
    }
}
