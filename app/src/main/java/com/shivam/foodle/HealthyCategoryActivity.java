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
import android.widget.ImageView;
import android.widget.TextView;

import com.shivam.foodle.DatabaseHandler.DatabaseHandler;
import com.shivam.foodle.DatabaseHandler.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HealthyCategoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    public List<RecipeModel> recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_category);

        toolbar=(Toolbar)findViewById(R.id.healthyCategoryActivityToolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Healthy");
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }

        DatabaseHandler db=new DatabaseHandler(this);
        recipe=new ArrayList<RecipeModel>();
        recipe=db.getAllRecipeByCategory("Healthy");
        //read data category wise and store it in recipe

        recyclerView=(RecyclerView)findViewById(R.id.healthyCategoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(new HealthyCategoryActivity.RecyclerViewAdapter(recipe));

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

    public class RecyclerViewAdapter extends RecyclerView.Adapter<HealthyCategoryActivity.RecyclerViewHolder>{

        private List<RecipeModel> recipe;

        public RecyclerViewAdapter(List<RecipeModel> recipe){
            this.recipe=recipe;
        }

        @Override
        public HealthyCategoryActivity.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getBaseContext()).inflate(R.layout.home_stream_card_view,parent,false);
            return new HealthyCategoryActivity.RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HealthyCategoryActivity.RecyclerViewHolder holder, int position) {
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
}
