package com.shivam.foodle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shivam.foodle.DatabaseHandler.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    public static final String MYPREFERENCES="myPrefs";
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getApplicationContext().getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("SharedPreferencesValue","true");
                editor.commit();
                Intent intent=new Intent(getBaseContext(),HomeStreamActivity.class);
                startActivity(intent);
            }
        }, SPLASH_TIME_OUT);

    }
}
