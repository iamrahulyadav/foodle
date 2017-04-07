package com.shivam.foodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.shivam.foodle.DatabaseHandler.DatabaseHandler;
import com.shivam.foodle.DatabaseHandler.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String RECIPE_NAME="";
    private TextView recipeNameTextView;
    private TextView cookTimeTextView;
    private TextView caloriesTextView;
    private TextView ingredientsTextView;
    private TextView instructionsTextView;
    private TextView ratingTextView;
    private ImageView recipeImageView;

    private DatabaseHandler db;

    private List<RecipeModel> recipe;
    private List<RecipeModel> testing;
    private LikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recipeNameTextView=(TextView)findViewById(R.id.detailActivityRecipeNameTextView);
        cookTimeTextView=(TextView)findViewById(R.id.detailActivityCookTimeTextView);
        caloriesTextView=(TextView)findViewById(R.id.detailActivityCaloriesTextView);
        ingredientsTextView=(TextView)findViewById(R.id.detailActivityIngredientsTextView);
        instructionsTextView=(TextView)findViewById(R.id.detailActivityInstructionsTextView);
        ratingTextView=(TextView)findViewById(R.id.detailActivityRatingTextView);
        recipeImageView=(ImageView)findViewById(R.id.detailActivityRecipeImageView);

        likeButton=(LikeButton) findViewById(R.id.likeButton);

        db=new DatabaseHandler(this);

        toolbar=(Toolbar)findViewById(R.id.detailActivityToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Intent intent=getIntent();
        RECIPE_NAME=intent.getExtras().getString("RECIPE_NAME");
        recipe=new ArrayList<RecipeModel>();
        testing=new ArrayList<RecipeModel>();

        recipe=db.getRecipeByRecipeName(RECIPE_NAME);
        setData();

        // handling the state of liked button depending upon the data present in the database
        if(recipe.get(0).getFavouriteBoolean().equalsIgnoreCase("true")){
            likeButton.setLiked(true);
        }
        else{
            likeButton.setLiked(false);
        }


        //setting the favouriteBoolean field of the recipe in the database
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                String nameOfRecipe=recipeNameTextView.getText().toString();
                testing=db.getRecipeByRecipeName(nameOfRecipe);
                db.updateRecipeFavouriteBoolean(nameOfRecipe,"true");
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                String nameOfRecipe=recipeNameTextView.getText().toString();
                testing=db.getRecipeByRecipeName(nameOfRecipe);
                db.updateRecipeFavouriteBoolean(nameOfRecipe,"false");
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

    public void setData(){
        recipeNameTextView.setText(recipe.get(0).getRecipeName());
        cookTimeTextView.setText(recipe.get(0).getCookTime()+" min");
        caloriesTextView.setText(recipe.get(0).getCalories()+" Kcal");
        ratingTextView.setText(recipe.get(0).getRating()+"");
        ingredientsTextView.setText(recipe.get(0).getIngredients());
        instructionsTextView.setText(recipe.get(0).getInstructions());
        Picasso.with(getBaseContext()).load(recipe.get(0).getImageUrl()).into(recipeImageView);
    }
}
