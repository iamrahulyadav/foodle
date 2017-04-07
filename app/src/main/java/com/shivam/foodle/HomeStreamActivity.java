package com.shivam.foodle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shivam.foodle.DatabaseHandler.DatabaseHandler;
import com.shivam.foodle.DatabaseHandler.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeStreamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView searchButton;
    private ImageView homeButton;
    private ImageView categoryButton;

    public SharedPreferences sharedPreferences;

    //Arraylist that contains all the data read from the database
    public List<RecipeModel> recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_stream);

        sharedPreferences=getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
        String value= sharedPreferences.getString("SharedPreferencesValue","false");

        DatabaseHandler db=new DatabaseHandler(this);

        //inserting data into the database for once using the shared preferences
       if(value.equalsIgnoreCase("true")){
            db.addDataInDatabase(getBaseContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("SharedPreferencesValue","false");
            editor.commit();
        }

        //Reading Data from the Database
        recipe=new ArrayList<RecipeModel>();
        recipe=ReadDataFromTheDatabase(db);

        searchButton=(ImageView)findViewById(R.id.activity_home_stream_searchButton);
        homeButton=(ImageView)findViewById(R.id.activity_home_stream_homeButton);
        categoryButton=(ImageView)findViewById(R.id.activity_home_stream_categoryButton);

        recyclerView=(RecyclerView)findViewById(R.id.homeStreamRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(new RecyclerViewAdapter(recipe));

        drawerLayout=(DrawerLayout)findViewById(R.id.activity_home_stream_drawerLayout);
        navigationView=(NavigationView)findViewById(R.id.activity_home_stream_navigationView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SearchActivity.class);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),CategoryActivity.class);
                startActivity(intent);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_menu_home:{
                        drawerLayout.closeDrawers();
                        return true;
                    }
                    case R.id.navigation_menu_explore:{
                        Intent intent=new Intent(getBaseContext(),SearchActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.navigation_menu_favourites:{
                        Intent intent=new Intent(getBaseContext(),FavouritesActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.navigation_menu_category:{
                        Intent intent=new Intent(getBaseContext(),CategoryActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.navigation_menu_report_and_feedback:{
                        Intent intent=new Intent(getBaseContext(),ReportAndFeedbackActivity.class);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        private List<RecipeModel> recipe;

        public RecyclerViewAdapter(List<RecipeModel> recipe){
            this.recipe=recipe;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getBaseContext()).inflate(R.layout.home_stream_card_view,parent,false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
          if(recipe!=null) {
              RecipeModel recipeModel = recipe.get(position);
              holder.recipeName.setText(recipeModel.getRecipeName());
              holder.recipeRating.setText(recipeModel.getRating() + "");
              holder.recipeFavourites.setText(recipeModel.getFavourites() + "");
              if(recipeModel.getImageUrl().isEmpty()){
                  holder.recipeImage.setImageResource(R.drawable.sandwich_card_view);
              }
              else{
              Picasso.with(getBaseContext()).load(recipeModel.getImageUrl()).into(holder.recipeImage);
              }
          }
        }

        @Override
        public int getItemCount() {
            return recipe.size();
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView recipeName;
        private ImageView recipeImage;
        private TextView recipeRating;
        private TextView recipeFavourites;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeName=(TextView)itemView.findViewById(R.id.homeStreamCardViewRecipeName);
            recipeImage=(ImageView)itemView.findViewById(R.id.homeStreamCardViewRecipeImage);
            recipeRating=(TextView)itemView.findViewById(R.id.homeStreamCardViewRatingTextView);
            recipeFavourites=(TextView)itemView.findViewById(R.id.homeStreamCardViewFavourites);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getBaseContext(),DetailActivity.class);
            intent.putExtra("RECIPE_NAME",recipeName.getText().toString());
            startActivity(intent);
        }
    }

    public List<RecipeModel> ReadDataFromTheDatabase(DatabaseHandler db){
        List<RecipeModel> recipe=db.getAllRecipe();
        return recipe;
    }

}
