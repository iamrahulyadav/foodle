package com.shivam.foodle.DatabaseHandler;

/**
 * Created by Shivam on 10-03-2017.
 */

public class RecipeSearchModel {
    String recipeName;
    String imageUrl;
    String preparationUrl;
    String ingredients;
    String calories;
    String totalWeight;
    String healthLabels;


    public RecipeSearchModel(){}
    public RecipeSearchModel(String recipeName,String imageUrl,String preparationUrl,String ingredients,String calories,String totalWeight,String healthLabels){
        this.recipeName=recipeName;
        this.imageUrl=imageUrl;
        this.preparationUrl=preparationUrl;
        this.ingredients=ingredients;
        this.calories=calories;
        this.totalWeight=totalWeight;
        this.healthLabels=healthLabels;
    }

    //getter methods
    public String getRecipeName(){
        return recipeName;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getPreparationUrl(){
        return preparationUrl;
    }
    public String getIngredients(){
        return ingredients;
    }
    public String getCalories(){
        return calories;
    }
    public String getTotalWeight() {
        return totalWeight;
    }
    public String getHealthLabels(){
        return healthLabels;
    }

    //setter methods
    public void setRecipeName(String recipeName){
        this.recipeName=recipeName;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }
    public void setPreparationUrl(String preparationUrl){
        this.preparationUrl=preparationUrl;
    }
    public void setIngredients(String ingredients){
        this.ingredients=ingredients;
    }
    public void setCalories(String calories){
        this.calories=calories;
    }
    public void setTotalWeight(String totalWeight){
        this.totalWeight=totalWeight;
    }
    public void setHealthLabels(String healthLabels){
        this.healthLabels=healthLabels;
    }
}
