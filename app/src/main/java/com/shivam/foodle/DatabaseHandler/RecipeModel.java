package com.shivam.foodle.DatabaseHandler;

/**
 * Created by Shivam on 03-03-2017.
 */

public class RecipeModel {

    int serailNumber;
    String recipeName;
    String imageUrl;
    float rating;
    int favourites;
    int cookTime;
    int calories;
    String ingredients;
    String instructions;
    String category;
    String favouriteBoolean;

    public RecipeModel(){
    }

    public RecipeModel(int serailNumber,String recipeName,String imageUrl,float rating,int favourites,int cookTime,int calories,String ingredients,String instructions,String category,String favouriteBoolean)
    {
        this.serailNumber=serailNumber;
        this.recipeName=recipeName;
        this.imageUrl=imageUrl;
        this.rating=rating;
        this.favourites=favourites;
        this.cookTime=cookTime;
        this.calories=calories;
        this.ingredients=ingredients;
        this.instructions=instructions;
        this.category=category;
        this.favouriteBoolean=favouriteBoolean;
    }


    //getter methods
    public int getSerailNumber(){
        return serailNumber;
    }
    public String getRecipeName(){
        return recipeName;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public float getRating(){
        return rating;
    }
    public int getFavourites(){
        return favourites;
    }
    public int getCookTime(){
        return cookTime;
    }
    public int getCalories(){
        return calories;
    }
    public String getIngredients(){
        return ingredients;
    }
    public String getInstructions(){
        return instructions;
    }
    public String getCategory(){
        return category;
    }
    public String getFavouriteBoolean(){
        return favouriteBoolean;
    }

    //setter methods
    public void setSerailNumber(int serailNumber){
        this.serailNumber=serailNumber;
    }
    public void setRecipeName(String recipeName){
        this.recipeName=recipeName;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }
    public void setRating(float rating){
        this.rating=rating;
    }
    public void setFavourites(int favourites){
        this.favourites=favourites;
    }
    public void setCookTime(int cookTime){
        this.cookTime=cookTime;
    }
    public void setCalories(int calories){
        this.calories=calories;
    }
    public void setIngredients(String ingredients){
        this.ingredients=ingredients;
    }
    public void setInstructions(String instructions){
        this.instructions=instructions;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setFavouriteBoolean(String favouriteBoolean){
        this.favouriteBoolean=favouriteBoolean;
    }
}
