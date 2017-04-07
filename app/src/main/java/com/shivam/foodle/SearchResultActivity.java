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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shivam.foodle.DatabaseHandler.RecipeModel;
import com.shivam.foodle.DatabaseHandler.RecipeSearchModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    public String JSON_URL="";
    private String jsonResponse="";
    public List<RecipeSearchModel> recipeSearchList;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        toolbar=(Toolbar)findViewById(R.id.activitySearchResultToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_back_button_white);
            getSupportActionBar().setTitle("Search Results");
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }

        recipeSearchList=new ArrayList<RecipeSearchModel>();

        Intent intent=getIntent();
        JSON_URL=intent.getExtras().getString("JSON_URL");

        recyclerView=(RecyclerView)findViewById(R.id.activitySearchResultRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringRequest stringRequest=new StringRequest(JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonResponse=response;
                try {
                    recipeSearchList=parseJsonResponse(response,recipeSearchList);
                    recyclerView.setAdapter(new RecyclerViewAdapter(recipeSearchList));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);

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

    public List<RecipeSearchModel> parseJsonResponse(String JSON_RESPONSE, List<RecipeSearchModel> recipeSearchList) throws JSONException {

        JSONObject root=new JSONObject(JSON_RESPONSE);
        JSONArray hits=root.getJSONArray("hits");

        for(int i=0;i<hits.length();i++){
            JSONObject object=hits.getJSONObject(i);
            JSONObject recipe=object.getJSONObject("recipe");

            JSONArray healthLabelsArray=recipe.getJSONArray("healthLabels");
            String healthLabels="";
            for(int j=0;j<healthLabelsArray.length();j++)
            {
                healthLabels+=healthLabelsArray.get(j) + "\n ";
            }

            JSONArray ingredientsArray=recipe.getJSONArray("ingredientLines");
            String ingredients="";
            for(int j=0;j<ingredientsArray.length();j++)
            {
                ingredients+=ingredientsArray.get(j) + "\n ";
            }

            String recipeName=recipe.getString("label");
            String imageUrl=recipe.getString("image");
            String preparationsUrl=recipe.getString("url");
            String calories=recipe.getString("calories");
            String totalWeight=recipe.getString("totalWeight");

            RecipeSearchModel recipeSearchModel=new RecipeSearchModel(recipeName,imageUrl,preparationsUrl,ingredients,calories,totalWeight,healthLabels);
            recipeSearchList.add(recipeSearchModel);
        }
        return recipeSearchList;
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        private List<RecipeSearchModel> recipeSearch;

        public RecyclerViewAdapter(List<RecipeSearchModel> recipeSearch){
            this.recipeSearch=recipeSearch;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getBaseContext()).inflate(R.layout.home_stream_card_view,parent,false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            if(recipeSearch!=null) {
                RecipeSearchModel recipeSearchModel = recipeSearch.get(position);
                holder.recipeName.setText(recipeSearchModel.getRecipeName());
                if(recipeSearchModel.getImageUrl().isEmpty()){
                    holder.recipeImage.setImageResource(R.drawable.sandwich_card_view);
                }
                else{
                    Picasso.with(getBaseContext()).load(recipeSearchModel.getImageUrl()).into(holder.recipeImage);
                }
            }
        }

        @Override
        public int getItemCount() {
            return recipeSearch.size();
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView recipeName;
        private ImageView recipeImage;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeName=(TextView)itemView.findViewById(R.id.homeStreamCardViewRecipeName);
            recipeImage=(ImageView)itemView.findViewById(R.id.homeStreamCardViewRecipeImage);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getBaseContext(),SearchResultDetailActivity.class);
            for(int i=0;i<recipeSearchList.size();i++)
            {
                if(recipeSearchList.get(i).getRecipeName().equalsIgnoreCase(recipeName.getText().toString())){
                    intent.putExtra("RECIPE_NAME",recipeName.getText().toString());
                    intent.putExtra("RECIPE_IMAGE_URL",recipeSearchList.get(i).getImageUrl());
                    intent.putExtra("RECIPE_CALORIES",recipeSearchList.get(i).getCalories());
                    intent.putExtra("RECIPE_TOTAL_WEIGHT",recipeSearchList.get(i).getTotalWeight());
                    intent.putExtra("RECIPE_INGREDIENTS",recipeSearchList.get(i).getIngredients());
                    intent.putExtra("RECIPE_INSTRUCTIONS",recipeSearchList.get(i).getPreparationUrl());
                    intent.putExtra("RECIPE_HEALTH_LABELS",recipeSearchList.get(i).getHealthLabels());
                    startActivity(intent);
                }
            }

        }
    }
}
