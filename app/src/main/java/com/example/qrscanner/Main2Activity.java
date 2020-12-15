package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
// Splash screen -
public class Main2Activity extends AppCompatActivity {
    private static int SPLASH_TIMER=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(home);
                finish();
            }
        },SPLASH_TIMER);

    }
}



