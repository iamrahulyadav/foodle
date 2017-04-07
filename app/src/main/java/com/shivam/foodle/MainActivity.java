package com.shivam.foodle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shivam.foodle.DatabaseHandler.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private ImageView facebookSignInButton;
    private ImageView googleSignInButton;

    public SharedPreferences sharedPreferences;
    public static final String MYPREFERENCES="myPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getApplicationContext().getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);

        facebookSignInButton=(ImageView) findViewById(R.id.mainActivityFacebookSignInButton);
        googleSignInButton=(ImageView)findViewById(R.id.mainActivityGoogleSignInButton);

        facebookSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("SharedPreferencesValue","true");
                editor.commit();
                Intent intent=new Intent(getBaseContext(),HomeStreamActivity.class);
                startActivity(intent);

            }
        });

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


    }
}
