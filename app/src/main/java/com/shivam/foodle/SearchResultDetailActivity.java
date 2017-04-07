package com.shivam.foodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SearchResultDetailActivity extends AppCompatActivity {

    private String RECIPE_NAME="";
    private String RECIPE_IMAGE_URL="";
    private String RECIPE_CALORIES="";
    private String RECIPE_TOTAL_WEIGHT="";
    private String RECIPE_INGREDIENTS="";
    private String RECIPE_INSTRUCTIONS="";
    private String RECIPE_HEALTH_LABELS="";

    private TextView recipeName;
    private ImageView recipeImage;
    private TextView totalWeight;
    private TextView calories;
    private TextView ingredients;
    private TextView instructions;
    private TextView healthLabel;

    private Toolbar toolbar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_detail2);

        toolbar=(Toolbar)findViewById(R.id.activitySearchResultToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        recipeName=(TextView)findViewById(R.id.searchResultDetailActivityRecipeNameTextView);
        recipeImage=(ImageView)findViewById(R.id.searchResultDetailActivityRecipeImageView);
        totalWeight=(TextView)findViewById(R.id.searchResultDetailActivityTotalWeightTextView);
        calories=(TextView)findViewById(R.id.searchResultDetailActivityCaloriesTextView);
        ingredients=(TextView)findViewById(R.id.searchResultDetailActivityIngredientsTextView);
        instructions=(TextView)findViewById(R.id.searchResultDetailActivityInstructionsTextView);
        healthLabel=(TextView)findViewById(R.id.searchResultDetailActivityHealthLabelTextView);

        Intent intent=getIntent();
        RECIPE_NAME=intent.getExtras().getString("RECIPE_NAME");
        RECIPE_IMAGE_URL=intent.getExtras().getString("RECIPE_IMAGE_URL");
        RECIPE_CALORIES=intent.getExtras().getString("RECIPE_CALORIES");
        RECIPE_TOTAL_WEIGHT=intent.getExtras().getString("RECIPE_TOTAL_WEIGHT");
        RECIPE_INGREDIENTS=intent.getExtras().getString("RECIPE_INGREDIENTS");
        RECIPE_INSTRUCTIONS=intent.getExtras().getString("RECIPE_INSTRUCTIONS");
        RECIPE_HEALTH_LABELS=intent.getExtras().getString("RECIPE_HEALTH_LABELS");

        recipeName.setText(RECIPE_NAME);
        totalWeight.setText(String.format("%.2f",Float.parseFloat(RECIPE_TOTAL_WEIGHT)) + "gm");
        calories.setText(String.format("%.2f",Float.parseFloat(RECIPE_CALORIES)) + "Cal");
        ingredients.setText(RECIPE_INGREDIENTS);
        instructions.setText(RECIPE_INSTRUCTIONS);
        healthLabel.setText(RECIPE_HEALTH_LABELS);
        Picasso.with(getBaseContext()).load(RECIPE_IMAGE_URL).into(recipeImage);

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.web_view);
                webView=(WebView)findViewById(R.id.webView);
                webView.setWebViewClient(new MyBrowser());
                String url=instructions.getText().toString();
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.loadUrl(url);
            }
        });
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

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
