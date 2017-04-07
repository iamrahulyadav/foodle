package com.shivam.foodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CategoryActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private LinearLayout Appetizers;
    private LinearLayout BreakFast;
    private LinearLayout Healthy;
    private LinearLayout Dessert;
    private LinearLayout Chicken;
    private LinearLayout MainDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Appetizers=(LinearLayout) findViewById(R.id.categoryActivityAppetizers);
        BreakFast=(LinearLayout) findViewById(R.id.categoryActivityBreakfast);
        Healthy=(LinearLayout) findViewById(R.id.categoryActivityHealthy);
        Dessert=(LinearLayout) findViewById(R.id.categoryActivityDessert);
        Chicken=(LinearLayout) findViewById(R.id.categoryActivityChicken);
        MainDish=(LinearLayout) findViewById(R.id.categoryActivityMaindish);


        Appetizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),AppetizersCategoryActivity.class);
                startActivity(intent);
            }
        });

        BreakFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),BreakfastCategoryActivity.class);
                startActivity(intent);
            }
        });

        Healthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),HealthyCategoryActivity.class);
                startActivity(intent);
            }
        });

        Chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),ChickenCategoryActivity.class);
                startActivity(intent);
            }
        });

        Dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getBaseContext(),DessertCategoryActivity.class);
                startActivity(intent);
            }
        });

        MainDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getBaseContext(),MaindishCategoryActivity.class);
                startActivity(intent);
            }
        });

        toolbar=(Toolbar)findViewById(R.id.categoryActivityToolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Category");
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
