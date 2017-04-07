package com.shivam.foodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.shivam.foodle.DatabaseHandler.DatabaseHandler;
import com.shivam.foodle.DatabaseHandler.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ImageView imageView;
    private List<RecipeModel> recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        imageView=(ImageView)findViewById(R.id.testActivityImageView);

        DatabaseHandler db=new DatabaseHandler(this);
        recipe=new ArrayList<>();
        recipe=db.getAllRecipe();

        String url=recipe.get(12).getImageUrl();
        Toast.makeText(this,url+"",Toast.LENGTH_LONG).show();
        Picasso.with(getBaseContext()).load(url).into(imageView);
    }
}
