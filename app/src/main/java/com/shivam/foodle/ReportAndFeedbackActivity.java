package com.shivam.foodle;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReportAndFeedbackActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView name;
    private TextView emailAddress;
    private TextView feedback;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_and_feedback);

        toolbar=(Toolbar)findViewById(R.id.reportAndFeedbackToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
            getSupportActionBar().setTitle("Report and feedback");
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }

        name=(EditText)findViewById(R.id.reportAndFeedbackNameEditText);
        emailAddress=(EditText)findViewById(R.id.reportAndFeedbackEmailEditText);
        feedback=(EditText)findViewById(R.id.reportAndFeedbackFeedbackEditText);
        button=(Button)findViewById(R.id.reportAndFeedbackSubmitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body="Hello Team " + feedback.getText().toString() + "\n " + name.getText().toString();
                String[] TO= {"gupta.shivam97@gmail.com"};
                Intent emailIntent=new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback for foodle ");
                emailIntent.putExtra(Intent.EXTRA_TEXT,body);

                startActivity(Intent.createChooser(emailIntent,"Send mail..."));
                finish();
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
