package com.shivam.foodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.shivam.foodle.DatabaseHandler.DatabaseHandler;
import com.shivam.foodle.DatabaseHandler.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<RecipeModel> recipeFavourite;
    private DatabaseHandler db;
    private RecyclerView  recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        toolbar=(Toolbar)findViewById(R.id.favouritesActivityToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_black);
            getSupportActionBar().setTitle("Favourites");
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }

        db=new DatabaseHandler(this);
        recipeFavourite=new ArrayList<RecipeModel>();
        recipeFavourite=db.getAllRecipeByFavouriteBoolean();

        if(recipeFavourite.size()!=0){
            setContentView(R.layout.activity_favourite_if_not_empty);
            toolbar=(Toolbar)findViewById(R.id.activityFavouriteIfNotEmptyToolbar);
            setSupportActionBar(toolbar);
            if(getSupportActionBar()!=null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
                getSupportActionBar().setTitle("Favourites");
                toolbar.setTitleTextColor(0xFFFFFFFF);
            }
            recyclerView=(RecyclerView)findViewById(R.id.activityFavouriteIfNotEmptyRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new RecyclerViewAdapter(recipeFavourite));
        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        private List<RecipeModel> recipe;

        public RecyclerViewAdapter(List<RecipeModel> recipe){
            this.recipe=recipe;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getBaseContext()).inflate(R.layout.favourites_card_view,parent,false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            if(recipe!=null) {
                RecipeModel recipeModel = recipe.get(position);
                holder.recipeName.setText(recipeModel.getRecipeName());
                holder.cookTime.setText(recipeModel.getCookTime()+" min");
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
        private TextView cookTime;
        private CircularImageView recipeImage;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeImage=(CircularImageView)itemView.findViewById(R.id.favouriteActivityRecipeImageView);
            recipeName=(TextView)itemView.findViewById(R.id.favouriteActivityRecipeNameTextView);
            cookTime=(TextView)itemView.findViewById(R.id.favouriteActivityCookTimeTextView);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getBaseContext(),DetailActivity.class);
            intent.putExtra("RECIPE_NAME",recipeName.getText().toString());
            startActivity(intent);
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
