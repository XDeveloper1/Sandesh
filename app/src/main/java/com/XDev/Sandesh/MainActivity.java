package com.XDev.Sandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    SharedPreferences sharedPreferences = getSharedPreferences(login_Activity.PREFS_NAME, 0);
                    boolean hasloggedIn = sharedPreferences.getBoolean("hasloggedIN", false);
                    if (hasloggedIn) {
                        Intent intent = new Intent(MainActivity.this, Home_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(MainActivity.this, login_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }




        }, SPLASH_TIME_OUT);





    }
}