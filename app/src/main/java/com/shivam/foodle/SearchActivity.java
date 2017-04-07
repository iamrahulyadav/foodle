package com.shivam.foodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText searchEditText;
    private Button searchButton;
    private String APP_ID="f6ec54ba";
    private String APP_KEY="621434204f4c6eff7ddc05891f751243";

    private String JSON_URL_PART1="https://api.edamam.com/search?q=";
    private String JSON_URL_PART2="&app_id=f6ec54ba&app_key=621434204f4c6eff7ddc05891f751243&from=0&to=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar=(Toolbar)findViewById(R.id.searchActivityToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        searchEditText=(EditText)findViewById(R.id.activitySearchEditText);
        searchButton=(Button)findViewById(R.id.activitySearchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input=searchEditText.getText().toString();
                String JSON_URL=JSON_URL_PART1+input+JSON_URL_PART2;

                Intent intent=new Intent(getBaseContext(),SearchResultActivity.class);
                intent.putExtra("JSON_URL",JSON_URL);
                startActivity(intent);
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
}
