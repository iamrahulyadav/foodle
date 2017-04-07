package com.shivam.foodle.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivam on 03-03-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="recipeDatabase";
    private static final String TABLE_RECIPE="recipe";

    //recipe table column name
    private static final String SERIAL_NUMBER="serialNumber";
    private static final String RECIPE_NAME="recipeName";
    private static final String IMAGE_URL="imageUrl";
    private static final String RATING="rating";
    private static final String FAVOURITES="favourites";
    private static final String COOK_TIME="cookTime";
    private static final String CALORIES="calories";
    private static final String INGREDIENTS="ingredients";
    private static final String INSTRUCTIONS="instructions";
    private static final String CATEGORY="category";
    private static final String FAVOURITE_BOOLEAN="favouriteBoolean";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating tables
        String CREATE_RECIPE_TABLE="CREATE TABLE " + TABLE_RECIPE + "(" + SERIAL_NUMBER + " INTEGER PRIMARY KEY, " + RECIPE_NAME + " TEXT," + IMAGE_URL + " TEXT," + RATING + " FLOAT," +
                FAVOURITES + " INTEGER," + COOK_TIME + " INTEGER," + CALORIES + " INTEGER," + INGREDIENTS + " TEXT," + INSTRUCTIONS + " TEXT," + CATEGORY + " TEXT," + FAVOURITE_BOOLEAN + " TEXT" + ")";
      sqLiteDatabase.execSQL(CREATE_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Add a new Recipe
    public void addRecipe(RecipeModel recipeModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SERIAL_NUMBER,recipeModel.getSerailNumber());
        values.put(RECIPE_NAME,recipeModel.getRecipeName());
        values.put(IMAGE_URL,recipeModel.getImageUrl());
        //Log.d("Url: ",recipeModel.getImageUrl());
        values.put(RATING,recipeModel.getRating());
        values.put(FAVOURITES,recipeModel.getFavourites());
        values.put(COOK_TIME,recipeModel.getCookTime());
        values.put(CALORIES,recipeModel.getCalories());
        values.put(INGREDIENTS,recipeModel.getIngredients());
        values.put(INSTRUCTIONS,recipeModel.getInstructions());
        values.put(CATEGORY,recipeModel.getCategory());
        values.put(FAVOURITE_BOOLEAN,recipeModel.getFavouriteBoolean());

        //inserting a row
        db.insert(TABLE_RECIPE,null,values);
        db.close();
    }


    //getting all recipe
    public List<RecipeModel> getAllRecipe(){
        List<RecipeModel> recipe=new ArrayList<RecipeModel>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPE+";";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                RecipeModel recipeModel =new RecipeModel();
                recipeModel.setSerailNumber(Integer.parseInt(cursor.getString(0)));
                recipeModel.setRecipeName(cursor.getString(1));
                //Log.d("URL","Hello "+cursor.getString(8));
                recipeModel.setImageUrl(cursor.getString(2));
                recipeModel.setRating(Float.parseFloat(cursor.getString(3)));
                recipeModel.setFavourites(Integer.parseInt(cursor.getString(4)));
                recipeModel.setCookTime(Integer.parseInt(cursor.getString(5)));
                recipeModel.setCalories(Integer.parseInt(cursor.getString(6)));
                recipeModel.setIngredients(cursor.getString(7));
                recipeModel.setInstructions(cursor.getString(8));
                recipeModel.setCategory(cursor.getString(9));
                recipeModel.setFavouriteBoolean(cursor.getString(10));

                recipe.add(recipeModel);
            }while (cursor.moveToNext());
        }
        return  recipe;
    }


    //getting all recipe by category
    public List<RecipeModel> getAllRecipeByCategory(String recipeCategory){
        List<RecipeModel> recipe=new ArrayList<RecipeModel>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPE + " WHERE " + CATEGORY + "='" + recipeCategory + "';";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                RecipeModel recipeModel =new RecipeModel();
                recipeModel.setSerailNumber(Integer.parseInt(cursor.getString(0)));
                recipeModel.setRecipeName(cursor.getString(1));
                recipeModel.setImageUrl(cursor.getString(2));
                recipeModel.setRating(Float.parseFloat(cursor.getString(3)));
                recipeModel.setFavourites(Integer.parseInt(cursor.getString(4)));
                recipeModel.setCookTime(Integer.parseInt(cursor.getString(5)));
                recipeModel.setCalories(Integer.parseInt(cursor.getString(6)));
                recipeModel.setIngredients(cursor.getString(7));
                recipeModel.setInstructions(cursor.getString(8));
                recipeModel.setCategory(cursor.getString(9));
                recipeModel.setFavouriteBoolean(cursor.getString(10));

                recipe.add(recipeModel);
            }while (cursor.moveToNext());
        }
        return  recipe;
    }

    //getting a single recipe from the database depending upon the recipe name
    public List<RecipeModel> getRecipeByRecipeName(String recipeName){
        List<RecipeModel> recipe=new ArrayList<RecipeModel>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPE+ " WHERE " + RECIPE_NAME + "= '" + recipeName + "' ;";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                RecipeModel recipeModel =new RecipeModel();
                recipeModel.setSerailNumber(Integer.parseInt(cursor.getString(0)));
                recipeModel.setRecipeName(cursor.getString(1));
             //   Log.d("URL","Hello "+cursor.getString(7));
                recipeModel.setImageUrl(cursor.getString(2));
                recipeModel.setRating(Float.parseFloat(cursor.getString(3)));
                recipeModel.setFavourites(Integer.parseInt(cursor.getString(4)));
                recipeModel.setCookTime(Integer.parseInt(cursor.getString(5)));
                recipeModel.setCalories(Integer.parseInt(cursor.getString(6)));
                recipeModel.setIngredients(cursor.getString(7));
                recipeModel.setInstructions(cursor.getString(8));
                recipeModel.setCategory(cursor.getString(9));
                recipeModel.setFavouriteBoolean(cursor.getString(10));

                recipe.add(recipeModel);
            }while (cursor.moveToNext());
        }
        return  recipe;
    }

    public int updateRecipeFavouriteBoolean(String recipeName,String value){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FAVOURITE_BOOLEAN,value);
        return db.update(TABLE_RECIPE,values,RECIPE_NAME + " = ?",
                new String[]{ String.valueOf(recipeName)});
    }


    //get all the recipe that are favourite of the user
    public List<RecipeModel> getAllRecipeByFavouriteBoolean(){
        List<RecipeModel> recipe=new ArrayList<RecipeModel>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPE + " WHERE " + FAVOURITE_BOOLEAN + "= 'true' "  + ";";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                RecipeModel recipeModel =new RecipeModel();
                recipeModel.setSerailNumber(Integer.parseInt(cursor.getString(0)));
                recipeModel.setRecipeName(cursor.getString(1));
                recipeModel.setImageUrl(cursor.getString(2));
                recipeModel.setRating(Float.parseFloat(cursor.getString(3)));
                recipeModel.setFavourites(Integer.parseInt(cursor.getString(4)));
                recipeModel.setCookTime(Integer.parseInt(cursor.getString(5)));
                recipeModel.setCalories(Integer.parseInt(cursor.getString(6)));
                recipeModel.setIngredients(cursor.getString(7));
                recipeModel.setInstructions(cursor.getString(8));
                recipeModel.setCategory(cursor.getString(9));
                recipeModel.setFavouriteBoolean(cursor.getString(10));

                recipe.add(recipeModel);
            }while (cursor.moveToNext());
        }
        return  recipe;
    }


    public void addDataInDatabase(Context context){
        DatabaseHandler db=new DatabaseHandler(context);

        //Appetizers
        db.addRecipe(new RecipeModel(1,"Cheese Puffs","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/a1.jpg?alt=media&token=8822a57f-b3a8-47dd-af1b-d35fd32402bc",4.5f,32,25,300,"1 cup all-purpose flour,\n" +
                "1/2 teaspoon salt, \n" +
                "1/2 teaspoon fresh ground black pepper,\n" +
                "1/2 teaspoon dried thyme,\n" +
                "1/2 teaspoon chili powder, \n" +
                "1 pinch cayenne pepper,\n" +
                "1 cup whole milk, \n" +
                "1 stick butter (cut into 1/2 inch cubes), \n" +
                "6 large eggs (at room temperature) ,\n" +
                "1/2 cup grated Parmesan or Romano cheese ,\n" +
                "3/4 cup grated Gruyere cheese ,\n" +
                "1 ounce pepperoni, diced (optional) ,\n" +
                "2 tablespoons milk,\n" +
                "2 tablespoons grated Parmesan cheese","Preheat oven to 425 degrees F (220 degrees C).\n" +
                "Combine the flour with the salt, black pepper, thyme, chili powder, and cayenne pepper in a large bowl.\n" +
                "Put the milk and butter in a large saucepan and bring to a boil. When the butter melts, turn heat to low. Add the seasoned flour all at once. Stir vigorously with a wooden spoon until the dough forms a ball. Remove from heat.\n" +
                "Separate the white and yolk from one egg, reserving the yolk for glazing.\n" +
                "Put the dough into a large mixer bowl. Beat at medium speed for one minute. Then beat in one egg and the extra egg white. Beat until completely absorbed into the dough. Then add the remaining 4 eggs, one at a time, waiting each time until the previous egg is completely absorbed. After all 5 eggs (plus the one egg white) have been incorporated, the dough should be smooth and satiny.\n" +
                "Add the Parmesan and Gruyere cheeses, and pepperoni if you are using it. Incorporate thoroughly into the dough.\n" +
                "Use a pastry bag to pipe dough onto 2 ungreased baking sheets. You can also drop dough from a teaspoon. For tiny puffs, mounds should be about 1/2 inch in diameter. From small appetizers, mounds should be 1 inch in diameter. For puffs large enough for filling, mounds should be 1 1/2 inches in diameter. Keep the size of the puffs uniform so they bake properly. Space puffs about 1 inch apart.\n" +
                "Beat the remaining egg yolk with 2 tablespoons of milk to make a glaze. Lightly brush the tops of the puffs with glaze before baking. Sprinkle tops with your Parmesan cheese.\n" +
                "Bake in pre-heated oven for 10 minutes (5 minutes for tiny puffs). Reduce heat to 300 degrees F (150 degrees C). Bake 10 minutes more for tiny puffs; 15 to 20 minutes longer for medium or large puffs, or until puffs are golden brown. Test by removing a puff from the oven and breaking it open. The inside should be baked through. If it is still doughy or wet, bake another 5 minutes.\n" +
                "Remove pans from oven and leave puffs on pans until cool enough to serve.","Appetizers","false"));

        db.addRecipe(new RecipeModel(2,"Artichoke & Spinach Dip Restaurant Style","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/a2.jpg?alt=media&token=6b245dc4-16a0-49ed-bd4d-33256e40e630",4.5f,35,30,285,"1 (14 ounce) can artichoke hearts, drained and chopped\n" +
                "1 (10 ounce) container Alfredo-style pasta sauce \n" +
                "1 cup shredded mozzarella cheese \n" +
                "1/3 cup grated Parmesan cheese\n" +
                "1/2 (8 ounce) package cream cheese, softened\n","Preheat oven to 350 degrees F (175 degrees C).\n" +
                "Place garlic in a small baking dish. Bake in the preheated oven 20 to 30 minutes, until soft. Remove from heat. When cool enough to touch, squeeze softened garlic from skins.\n" +
                "In an 8x8 inch baking dish, spread the roasted garlic, spinach, artichoke hearts, Alfredo-style pasta sauce, mozzarella cheese, Parmesan cheese, and cream cheese.\n" +
                "Bake in the preheated oven 30 minutes, or until cheeses are melted and bubbly. Serve warm.","Appetizers","false"));

        db.addRecipe(new RecipeModel(3,"Bavarian Style Meatballs","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/a3.jpg?alt=media&token=0b739695-ddf8-4522-9e0f-5433c4b2300f",4.5f,52,240,357," 12 fluid ounces tomato-based chili sauce\n" +
                "1 (16 ounce) can whole cranberry sauce\n" +
                "27 ounces Bavarian-style sauerkraut, undrained\n" +
                "1 cup water\n" +
                "1 cup packed brown sugar \n" +
                "1 (16 ounce) package frozen meatballs\n","In a medium size mixing bowl, combine chili sauce, cranberry sauce, sauerkraut, water, and brown sugar. Mix well. Pour sauce and meatballs in a slow cooker, stir.\n" +
                "Cook, covered, at a medium temperature for 4 hours. Stir occasionally to coat meatballs.","Appetizers","false"));

        db.addRecipe(new RecipeModel(4,"Sausage Stuffed Jalapenos","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/a4.jpg?alt=media&token=50099116-7a46-461a-8f2b-4c80cedec7bd",4.5f,52,20,740," 1 pound ground pork sausage \n" +
                "1 (8 ounce) package cream cheese, softened \n" +
                "1 cup shredded Parmesan cheese \n" +
                "1 pound large fresh jalapeno peppers, halved lengthwise and seeded\n" +
                "1 (8 ounce) bottle Ranch dressing (optional)\n","Preheat oven to 425 degrees F (220 degrees C).\n" +
                "Place sausage in a skillet over medium heat, and cook until evenly brown. Drain grease.\n" +
                "In a bowl, mix the sausage, cream cheese, and Parmesan cheese. Spoon about 1 tablespoon sausage mixture into each jalapeno half. Arrange stuffed halves in baking dishes.\n" +
                "Bake 20 minutes in the preheated oven, until bubbly and lightly browned. Serve with Ranch dressing.","Appetizers","false"));

        db.addRecipe(new RecipeModel(5,"Absolutely Amazing Ahi","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/a5.jpg?alt=media&token=2106066f-d882-4e85-b12b-8730f2fcdcf5",4.5f,52,15,380," 3/4 pound sashimi grade tuna steak,diced\n" +
                "1/2 cup diced cucumber \n" +
                "1 avocados - peeled, pitted and diced\n" +
                "1/4 cup chopped green onion\n" +
                "1 1/2 teaspoons red pepper flakes \n" +
                "1 tablespoon toasted sesame seeds \n" +
                "1 1/2 teaspoons lemon juice\n" +
                "2 teaspoons sesame oil\n" +
                "1/2 cup soy sauce\n","In a medium bowl, combine the tuna, cucumber, avocado, green onion, red pepper flakes and sesame seeds. Pour in the lemon juice, sesame oil and soy sauce, and stir carefully to blend so as not to mash the avocado. Place this bowl into a larger bowl that has been filled with ice. Chill in the refrigerator for 15 minutes, but no longer - the terrific freshness of the fish will be lost.\n" +
                "Once chilled, remove the bowl from the ice, and invert onto a serving plate. Serve with toasted bread or your favorite crackers.\n","Appetizers","false"));

        db.addRecipe(new RecipeModel(6,"Playgroup Granola Bars","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/a6.jpg?alt=media&token=2c6205f0-0f0b-4e89-afb4-5dfd2a2a4275",4.5f,24,35,374," 2 cups rolled oats\n" +
                "3/4 cup packed brown sugar\n" +
                "1/2 cup wheat germ\n" +
                "3/4 teaspoon ground cinnamon \n" +
                "1 cup all-purpose flour\n" +
                "3/4 cup raisins (optional)\n" +
                "3/4 teaspoon salt\n" +
                "1/2 cup honey 1 egg, beaten\n" +
                "1/2 cup vegetable oil\n" +
                "2 teaspoons vanilla extract","Preheat the oven to 350 degrees F (175 degrees C). Generously grease a 9x13 inch baking pan.\n" +
                "In a large bowl, mix together the oats, brown sugar, wheat germ, cinnamon, flour, raisins and salt. Make a well in the center, and pour in the honey, egg, oil and vanilla. Mix well using your hands. Pat the mixture evenly into the prepared pan.\n" +
                "Bake for 30 to 35 minutes in the preheated oven, until the bars begin to turn golden at the edges. Cool for 5 minutes, then cut into bars while still warm. Do not allow the bars to cool completely before cutting, or they will be too hard to cut.\n","Appetizers","false"));


        //Breakfast
        db.addRecipe(new RecipeModel(7,"Quick Quiche","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/b1.jpg?alt=media&token=f75b55f8-0160-4952-b930-e008d360d054",4.5f,42,35,380," 8 slices bacon \n" +
                "4 ounces shredded Swiss cheese \n" +
                "2 tablespoons butter, melted\n" +
                "4 eggs, beaten\n" +
                "1/4 cup finely chopped onion \n" +
                "1 teaspoon salt\n" +
                "1/2 cup all-purpose flour\n" +
                "1 1/2 cups milk","Place bacon in a large, deep skillet. Cook over medium high heat until evenly brown. Drain, crumble and set aside.\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Lightly grease a 9 inch pie pan.\n" +
                "Line bottom of pie plate with cheese and crumbled bacon. Combine eggs, butter, onion, salt, flour and milk; whisk together until smooth; pour into pie pan.\n" +
                "Bake in preheated oven for 35 minutes, until set. Serve hot or cold.","Breakfast","false"));

        db.addRecipe(new RecipeModel(8,"Muffin Morning Makers","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/b2.jpg?alt=media&token=3905664a-d0ae-4cf3-8864-0b4a9e879bf1",4.5f,42,10,345,"1 (12 ounce) package English muffins \n" +
                "6 eggs \n" +
                "1/4 cup chopped green onion\n" +
                "1 cup shredded Cheddar cheese\n" +
                "2 1/2 tablespoons vegetable oil\n" +
                "6 ounces ground breakfast sausage","Split the muffins open with a fork and toast them in a toaster oven.\n" +
                "In a large bowl scramble the eggs, green onions and cheddar cheese together. Heat vegetable oil in a skillet, pour the egg mixture into the skillet and let it fry in one layer as you would an omelet. When the egg is cooked on the underside, flip the egg over and cook the wet side; remove from heat when cooked through.\n" +
                "At the same time that the eggs are frying, form the sausage into small patties. Place the sausage in another skillet. Fry until browned, remove from pan and drain on paper towels.\n" +
                "Make the muffin sandwiches by layering a piece of sausage and a piece of the fried egg between the two muffin pieces. If you intend to freeze the sandwiches, let each part of the sandwich cool before make the sandwiches, then wrap them in plastic wrap and freeze. Reheat in the microwave.","Breakfast","false"));

        db.addRecipe(new RecipeModel(9,"Breakfast Biscuits","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/b3.jpg?alt=media&token=73744663-948b-4c81-96fa-5110210ad7a1",4.2f,52,10,421," 1 (10 ounce) can refrigerated buttermilk biscuit dough \n" +
                "1 pound bacon \n" +
                "5 eggs\n" +
                "1/4 cup milk\n" +
                "3 tablespoons butter, softened\n" +
                "10 slices Cheddar cheese","Preheat oven to 400 degrees F (200 degrees C).\n" +
                "Place biscuits 2 inches apart on an ungreased cookie sheet. Bake in preheated oven for 8 to 11 minutes or until golden brown.\n" +
                "Place bacon in a large, deep skillet. Cook over medium-high heat until evenly brown. Drain and set aside.\n" +
                "In a large bowl, beat together eggs and milk. Heat a lightly oiled skillet over medium heat. Scramble eggs to your liking.\n" +
                "Cut open biscuits, lightly butter, layer with eggs, bacon, and cheese.\n","Breakfast","false"));

        db.addRecipe(new RecipeModel(10,"Eggy Doodle Sandwiches","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/b4.jpg?alt=media&token=10ac70e9-f663-4d27-a78a-65082c369a3e",4.2f,51,10,541,"2 tablespoons butter \n" +
                "4 slices Texas toast thick-sliced bread \n" +
                "4 eggs \n" +
                "8 slices American cheese\n" +
                "4 slices smoked ham salt and pepper to taste","Melt butter in a large skillet over medium heat. Cut a hole in the center of each slice of bread with a round cookie cutter or the rim of a glass. Place the bread into the hot skillet, crack one egg into the hole of each slice of bread, break the yolk and cook until the white is mostly opaque. Flip the slices of bread over. Form sandwich halves by topping each piece of bread with 1 slice of cheese, a slice of ham, then another slice of cheese. Season with salt and pepper and cook until the eggs are fully set; remove from the skillet.\n" +
                "Make two sandwiches by putting together the halves. Place on a microwave-safe plate and microwave on high until the cheese is fully melted, 30 to 45 seconds.","Breakfast","false"));

        db.addRecipe(new RecipeModel(11,"Chef Johns Monte Cristo Benedict","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/b5.jpg?alt=media&token=1c801480-4a27-48ed-915d-9d73e5c5f468",4.5f,42,30,524,"2 large eggs \n" +
                "1/4 cup heavy whipping cream \n" +
                "1 tablespoon white sugar \n" +
                "1 pinch salt\n" +
                "1 pinch cayenne pepper \n" +
                "1/4 teaspoon ground cinnamon \n" +
                "1/8 teaspoon ground allspice\n" +
                "4 thick slices day-old French bread\n" +
                "1 tablespoon butter \n" +
                "8 thin slices cooked ham \n" +
                "4 slices Cheddar cheese \n" +
                "4 slices Havarti cheese\n" +
                "8 poached eggs\n" +
                "2 teaspoons chopped fresh chives, or to taste\n" +
                "1 pinch kosher salt, or to taste\n" +
                "1 pinch cayenne pepper, or to taste","Preheat oven to 375 degrees F (190 degrees C).\n" +
                "Whisk 2 eggs, cream, white sugar, salt, 1 pinch cayenne pepper, cinnamon, and allspice together in a bowl until batter is thoroughly combined.\n" +
                "Lay bread slices into batter, one at a time, and let bread absorb the mixture. Turn bread slices in batter until almost all batter has been absorbed, about 10 minutes.\n" +
                "Heat a large skillet over medium heat, and melt butter in the hot skillet. Cook bread slices in the hot butter until browned, 2 to 3 minutes per side. Transfer French toast slices to a baking sheet.\n" +
                "Lay ham slices into the hot skillet and cook until meat begins to brown, about 1 minute per side.\n" +
                "To assemble, place a Cheddar cheese slice on a slice of French toast, top with 2 slices of ham, and lay a Havarti cheese slice over ham.\n" +
                "Bake in the preheated oven until French toast pieces are no longer wet, the batter is set, and cheese has melted and begun to brown, about 20 minutes.\n" +
                "Place sandwiches on serving plates and top each with 2 poached eggs. Season with kosher salt and a pinch of cayenne pepper.","Breakfast","false"));

        db.addRecipe(new RecipeModel(12,"Grilled Peanut Butter and Banana Sandwich","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/b6.jpg?alt=media&token=e366ad66-c527-4e9e-9e87-c13e2c85ee78",4.7f,48,10,345," cooking spray \n" +
                "2 tablespoons peanut butter\n" +
                "2 slices whole wheat bread \n" +
                "1 banana, sliced\n","Heat a skillet or griddle over medium heat, and coat with cooking spray. Spread 1 tablespoon of peanut butter onto one side of each slice of bread. Place banana slices onto the peanut buttered side of one slice, top with the other slice and press together firmly. Fry the sandwich until golden brown on each side, about 2 minutes per side.\n","Breakfast","false"));

        //Chicken
        db.addRecipe(new RecipeModel(13,"Baked Chicken Schnitzel","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/c1.jpg?alt=media&token=b248c4f5-509e-491e-8adf-5f289cb77c93",4.5f,42,10,421," 1 tablespoon olive oil, or as desired \n" +
                "6 chicken breasts, cut in half lengthwise (butterflied) salt and ground black pepper to taste \n" +
                "3/4 cup all-purpose flour\n" +
                "1 tablespoon paprika\n" +
                "2 eggs, beaten\n" +
                "2 cups seasoned bread crumbs \n" +
                "1 large lemon, zested\n","Preheat oven to 425 degrees F (220 degrees C). Line a large baking sheet with aluminum foil and drizzle olive oil over foil. Place baking sheet in preheated oven.\n" +
                "Flatten chicken breasts so they are all about 1/4-inch thick. Season chicken with salt and pepper.\n" +
                "Mix flour and paprika together on a large plate. Beat eggs with salt and pepper in a shallow bowl. Mix bread crumbs and lemon zest together on a separate large plate. Dredge each chicken piece in flour mixture, then egg, and then bread crumbs mixture and set aside in 1 layer on a clean plate. Repeat with remaining chicken.\n" +
                "Remove baking sheet from oven and arrange chicken in 1 layer on the sheet. Drizzle more olive oil over each piece of coated chicken.\n" +
                "Bake in the preheated oven for 5 to 6 minutes. Flip chicken and continue baking until no longer pink in the center and the breading is lightly browned, 5 to 6 minutes more. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).","Chicken","false"));

        db.addRecipe(new RecipeModel(14,"Simply Lemon Baked Chicken","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/c2.jpg?alt=media&token=60e1dd30-7d95-4702-adb7-54b206bd5ad1",4.2f,51,180,421,"1 (4 pound) whole chicken\n" +
                "2 tablespoons salt \n" +
                "1 lemon, halved \n" +
                "1 tablespoon paprika\n" +
                "1 cup water","Preheat oven to 300 degrees F (150 degrees C).\n" +
                "Rub chicken with salt inside and out. Squeeze lemon juice from lemon halves over outside of chicken, then rub paprika over all. Place squeeze lemon halves inside chicken cavity, then place chicken in a lightly greased 9x13 inch baking dish. Pour a little water over chicken to prevent drying.\n" +
                "Bake at 300 degrees F (150 degrees C) for 3 hours, basting with water as needed.\n","Chicken","false"));

        db.addRecipe(new RecipeModel(15,"Slow Cooker Chicken Taco Soup","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/c3.jpg?alt=media&token=f339f18e-5918-4a94-83a6-b0b413e83724",4.8f,42,420,450,"1 onion, chopped \n" +
                "1 (16 ounce) can chili beans\n" +
                "1 (15 ounce) can black beans\n" +
                "1 (15 ounce) can whole kernel corn, drained\n" +
                "1 (8 ounce) can tomato sauce \n" +
                "1 (12 fluid ounce) can or bottle beer\n" +
                "2 (10 ounce) cans diced tomatoes with green chilies, undrained \n" +
                "1 (1.25 ounce) package taco seasoning \n" +
                "3 whole skinless, boneless chicken breasts \n" +
                "1 (8 ounce) package shredded Cheddar cheese (optional) sour cream (optional) crushed tortilla chips (optional)","Place the onion, chili beans, black beans, corn, tomato sauce, beer, and diced tomatoes in a slow cooker. Add taco seasoning, and stir to blend. Lay chicken breasts on top of the mixture, pressing down slightly until just covered by the other ingredients. Set slow cooker for low heat, cover, and cook for 5 hours.\n" +
                "Remove chicken breasts from the soup, and allow to cool long enough to be handled. Stir the shredded chicken back into the soup, and continue cooking for 2 hours. Serve topped with shredded Cheddar cheese, a dollop of sour cream, and crushed tortilla chips, if desired.","Chicken","false"));

        db.addRecipe(new RecipeModel(16,"Slow Cooker Chicken and Dumplings","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/c4.jpg?alt=media&token=714bae3b-4011-4862-87ce-d51cf4e1042c",4.2f,42,360,520,"4 skinless, boneless chicken breast halves \n" +
                "2 tablespoons butter\n" +
                "2 (10.75 ounce) cans condensed cream of chicken soup \n" +
                "1 onion, finely diced \n" +
                "2 (10 ounce) packages refrigerated biscuit dough, torn into pieces","Place the chicken, butter, soup, and onion in a slow cooker, and fill with enough water to cover.\n" +
                "Cover, and cook for 5 to 6 hours on High. About 30 minutes before serving, place the torn biscuit dough in the slow cooker. Cook until the dough is no longer raw in the center.\n","Chicken","false"));

        //Dessert
        db.addRecipe(new RecipeModel(17,"Real Pistachio Cupcakes","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/d1.jpg?alt=media&token=7cdaa2fa-f554-4565-b971-55025e6f5134",4.2f,42,15,432,"1 cup roasted pistachio nut meats \n" +
                "1 1/2 cups white sugar, divided\n" +
                "3/4 cup all-purpose flour\n" +
                "3/4 cup cake flour\n" +
                "2 teaspoons baking powder \n" +
                "3/4 teaspoon salt \n" +
                "3/4 cup unsalted butter, at room temperature \n" +
                "4 large eggs \n" +
                "2/3 cup milk, at room temperature \n" +
                "2 teaspoons vanilla","Preheat oven to 350 degrees F (175 degrees C). Line 24 muffin cups with paper liners.\n" +
                "Blend pistachios with 1/2 cup sugar in a blender until finely ground.\n" +
                "Sift all-purpose flour, cake flour, baking powder, and salt together in a bowl.\n" +
                "Beat 1 cup sugar and butter together in a large bowl with an electric mixer until light and fluffy. Beat one egg at a time into the creamy butter mixture, thoroughly integrating each egg before adding the next.\n" +
                "Stir milk and vanilla extract together in a separate bowl.\n" +
                "Alternately stir flour mixture and milk mixture in small amounts into the butter mixture beginning and ending with the flour mixture; stir just until it comes together into a batter. Fold the ground pistachios into the batter. Spoon batter into the prepared muffin cups to about 2/3 full.\n" +
                "Bake in the preheated oven until a toothpick inserted into the center comes out clean, 15 to 18 minutes. Cool in the muffin cups for 5 minutes before removing to cool completely on a wire rack.","Dessert","false"));

        db.addRecipe(new RecipeModel(18,"Grandmas Lemon Meringue Pie","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/d2.jpg?alt=media&token=fcf84d8a-4e4d-4999-8c53-8aefa8259d01",4.2f,42,10,380,"1 cup white sugar \n" +
                "2 tablespoons all-purpose flour \n" +
                "3 tablespoons cornstarch \n" +
                "1/4 teaspoon salt\n" +
                "1 1/2 cups water \n" +
                "2 lemons, juiced and zested\n" +
                "2 tablespoons butter\n" +
                "4 egg yolks, beaten \n" +
                "1 (9 inch) pie crust, baked \n" +
                "4 egg whites\n" +
                "6 tablespoons white sugar","Preheat oven to 350 degrees F (175 degrees C).\n" +
                "To Make Lemon Filling: In a medium saucepan, whisk together 1 cup sugar, flour, cornstarch, and salt. Stir in water, lemon juice and lemon zest. Cook over medium-high heat, stirring frequently, until mixture comes to a boil. Stir in butter. Place egg yolks in a small bowl and gradually whisk in 1/2 cup of hot sugar mixture. Whisk egg yolk mixture back into remaining sugar mixture. Bring to a boil and continue to cook while stirring constantly until thick. Remove from heat. Pour filling into baked pastry shell.\n" +
                "To Make Meringue: In a large glass or metal bowl, whip egg whites until foamy. Add sugar gradually, and continue to whip until stiff peaks form. Spread meringue over pie, sealing the edges at the crust.\n" +
                "Bake in preheated oven for 10 minutes, or until meringue is golden brown.","Dessert","false"));

        db.addRecipe(new RecipeModel(19,"Sam Famous Carrot Cake","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/d3.jpg?alt=media&token=1a0db386-603c-41bf-9de2-b0bfe05da665",4.2f,42,60,380,"3 eggs \n" +
                "3/4 cup buttermilk\n" +
                "3/4 cup vegetable oil\n" +
                "1 1/2 cups white sugar \n" +
                "2 teaspoons vanilla extract\n" +
                "2 teaspoons ground cinnamon \n" +
                "1/4 teaspoon salt \n" +
                "2 cups all-purpose flour\n" +
                "2 teaspoons baking soda\n" +
                "2 cups shredded carrots\n" +
                "1 cup flaked coconut\n" +
                "1 cup chopped walnuts\n" +
                "1 (8 ounce) can crushed pineapple with juice\n" +
                "1 cup raisins"," Preheat oven to 350 degrees F (175 degrees C). Grease and flour an 8x12 inch pan.\n" +
                "In a medium bowl, sift together flour, baking soda, salt and cinnamon. Set aside.\n" +
                "In a large bowl, combine eggs, buttermilk, oil, sugar and vanilla. Mix well. Add flour mixture and mix well.\n" +
                "In a medium bowl, combine shredded carrots, coconut, walnuts, pineapple and raisins.\n" +
                "Using a large wooden spoon or a very heavy whisk, add carrot mixture to batter and fold in well.\n" +
                "Pour into prepared 8x12 inch pan, and bake at 350 degrees F (175 degrees C) for 1 hour. Check with toothpick.\n" +
                "Allow to cool for at least 20 minutes before serving.\n","Dessert","false"));

        db.addRecipe(new RecipeModel(20,"Cream Cheese Frosting II","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/d4.jpg?alt=media&token=782594a3-bd78-4f43-8240-a58f06eb93a2",4.2f,42,10,380,"2 (8 ounce) packages cream cheese, softened \n" +
                "1/2 cup butter, softened \n" +
                "2 cups sifted confectioners' sugar\n" +
                "1 teaspoon vanilla extract","In a medium bowl, cream together the cream cheese and butter until creamy. Mix in the vanilla, then gradually stir in the confectioners' sugar. Store in the refrigerator after use.\n","Dessert","false"));

        db.addRecipe(new RecipeModel(21,"Mexican Brownies","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/d5.jpg?alt=media&token=336c9b8d-49a4-482f-a5d9-e333cb9f77f5",4.2f,42,20,380," 1 1/2 cups unsalted butter \n" +
                "3 cups white sugar\n" +
                "6 eggs\n" +
                "1 tablespoon vanilla extract\n" +
                "1 1/4 cups unsweetened cocoa powder \n" +
                "1 1/2 cups all-purpose flour\n" +
                "1 3/4 teaspoons ground Mexican cinnamon (canela) \n" +
                "1/2 teaspoon ground pequin chile pepper \n" +
                "3/4 teaspoon kosher salt \n" +
                "3/4 teaspoon baking powder","Preheat oven to 350 degrees F (175 degrees C). Line a 12x15-inch baking pan with parchment paper, leaving about 3 inches of paper overhanging 2 sides to use as handles.\n" +
                "Place the butter in a microwave-safe bowl, and cook on Medium until the butter is about half melted, about 1 minute. Mash the butter with sugar until well combined, and stir in eggs one at a time, incorporating each one before adding the next. Mix in vanilla extract.\n" +
                "Sift the cocoa, flour, cinnamon, pequin pepper, salt, and baking powder into a bowl. Sprinkle in any salt caught in the sifter. Mix the flour mixture into the butter mixture, stirring to blend well, and pour the batter into the prepared baking pan.\n" +
                "Bake in the preheated oven until a toothpick inserted into the center comes out with moist crumbs, 20 to 25 minutes. Let cool in the pan, and use parchment paper handles to remove the brownies for slicing.\n","Dessert","false"));

        //Healthy
        db.addRecipe(new RecipeModel(22,"Greek Zoodle Salad","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/h1.jpg?alt=media&token=5b8b467c-3b29-4308-b7ca-2f55ab3e65a1",4.2f,42,25,380," 2 zucchini \n" +
                "1/4 English cucumber, chopped \n" +
                "10 cherry tomatoes, halved, or more to taste\n" +
                "10 pitted kalamata olives, halved, or more to taste\n" +
                "1/4 cup thinly sliced red onion\n" +
                "2 ounces crumbled reduced-fat feta cheese \n" +
                "2 tablespoons extra-virgin olive oil\n" +
                "2 tablespoons fresh lemon juice\n" +
                "1 teaspoon dried oregano salt and ground black pepper to taste","Cut zucchini into noodle-shaped strands using a spiralizing tool. Place \"zoodles\" in a large bowl and top with cucumber, tomatoes, olives, red onion, and feta cheese.\n" +
                "Whisk olive oil, lemon juice, oregano, salt, and pepper together in a bowl until dressing is smooth; pour over \"zoodle\" mixture and toss to coat. Marinate salad in refrigerator for 10 to 15 minutes.","Healthy","false"));

        db.addRecipe(new RecipeModel(23,"Annie Fruit Salsa and Cinnamon Chips","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/h2.jpg?alt=media&token=295a6cef-1216-4757-9389-653c852d7e3f",4.2f,42,10,380,"2 kiwis, peeled and diced\n" +
                "2 Golden Delicious apples - peeled, cored and diced\n" +
                "8 ounces raspberries\n" +
                "1 pound strawberries\n" +
                "2 tablespoons white sugar\n" +
                "1 tablespoon brown sugar \n" +
                "3 tablespoons fruit preserves, any flavor \n" +
                "10 (10 inch) flour tortillas butter flavored cooking spray\n" +
                "2 tablespoons cinnamon sugar","In a large bowl, thoroughly mix kiwis, Golden Delicious apples, raspberries, strawberries, white sugar, brown sugar and fruit preserves. Cover and chill in the refrigerator at least 15 minutes.\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "Coat one side of each flour tortilla with butter flavored cooking spray. Cut into wedges and arrange in a single layer on a large baking sheet. Sprinkle wedges with desired amount of cinnamon sugar. Spray again with cooking spray.\n" +
                "Bake in the preheated oven 8 to 10 minutes. Repeat with any remaining tortilla wedges. Allow to cool approximately 15 minutes. Serve with chilled fruit mixture.\n","Healthy","false"));

        db.addRecipe(new RecipeModel(24,"Maple Salmon","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/h3.jpg?alt=media&token=3f261dd7-173c-420f-9de0-0ca0b45fb7bc",4.2f,42,20,380,"1/4 cup maple syrup \n" +
                "2 tablespoons soy sauce\n" +
                "1 clove garlic, minced\n" +
                "1/4 teaspoon garlic salt\n" +
                "1/8 teaspoon ground black pepper \n" +
                "1 pound salmon","In a small bowl, mix the maple syrup, soy sauce, garlic, garlic salt, and pepper.\n" +
                "Place salmon in a shallow glass baking dish, and coat with the maple syrup mixture. Cover the dish, and marinate salmon in the refrigerator 30 minutes, turning once.\n" +
                "Preheat oven to 400 degrees F (200 degrees C).\n" +
                "Place the baking dish in the preheated oven, and bake salmon uncovered 20 minutes, or until easily flaked with a fork.","Healthy","false"));

        db.addRecipe(new RecipeModel(25,"Homemade Black Bean Veggie Burgers","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/h4.jpg?alt=media&token=b4fd81d6-f538-4a01-9b50-e019b9efced2",4.2f,42,20,380,"1 (16 ounce) can black beans, drained and rinsed \n" +
                "1/2 green bell pepper, cut into 2 inch pieces \n" +
                "1/2 onion, cut into wedges\n" +
                "3 cloves garlic, peeled \n" +
                "1 egg \n" +
                "1 tablespoon chili powder\n" +
                "1 tablespoon cumin \n" +
                "1 teaspoon Thai chili sauce or hot sauce\n" +
                "1/2 cup bread crumbs\n","If grilling, preheat an outdoor grill for high heat, and lightly oil a sheet of aluminum foil. If baking, preheat oven to 375 degrees F (190 degrees C), and lightly oil a baking sheet.\n" +
                "In a medium bowl, mash black beans with a fork until thick and pasty.\n" +
                "In a food processor, finely chop bell pepper, onion, and garlic. Then stir into mashed beans.\n" +
                "In a small bowl, stir together egg, chili powder, cumin, and chili sauce.\n" +
                "Stir the egg mixture into the mashed beans. Mix in bread crumbs until the mixture is sticky and holds together. Divide mixture into four patties.\n" +
                "If grilling, place patties on foil, and grill about 8 minutes on each side. If baking, place patties on baking sheet, and bake about 10 minutes on each side.","Healthy","false"));

        db.addRecipe(new RecipeModel(26,"Simple Turkey Chili","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/h5.jpg?alt=media&token=c55f77f4-76f0-4156-a8d0-cd71e855a64c",4.2f,42,45,380,"1 1/2 teaspoons olive oil\n" +
                "1 pound ground turkey\n" +
                "1 onion, chopped\n" +
                "2 cups water\n" +
                "1 (28 ounce) can canned crushed tomatoes\n" +
                "1 (16 ounce) can canned kidney beans - drained, rinsed, and mashed \n" +
                "1 tablespoon garlic, minced\n" +
                "2 tablespoons chili powder\n" +
                "1/2 teaspoon paprika\n" +
                "1/2 teaspoon dried oregano\n" +
                "1/2 teaspoon ground cayenne pepper \n" +
                "1/2 teaspoon ground cumin \n" +
                "1/2 teaspoon salt\n" +
                "1/2 teaspoon ground black pepper","Heat the oil in a large pot over medium heat. Place turkey in the pot, and cook until evenly brown. Stir in onion, and cook until tender.\n" +
                "Pour water into the pot. Mix in tomatoes, kidney beans, and garlic. Season chili powder, paprika, oregano, cayenne pepper, cumin, salt, and pepper. Bring to a boil. Reduce heat to low, cover, and simmer 30 minutes.\n","Healthy","false"));

        //MainDish
        db.addRecipe(new RecipeModel(27,"Spinach Enchiladas","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/m1.jpg?alt=media&token=3eb3587a-8421-46d0-afe1-9b3c571fe0b5",4.2f,42,20,380,"1 tablespoon butter\n" +
                "1/2 cup sliced green onions \n" +
                "2 cloves garlic, minced \n" +
                "1 (10 ounce) package frozen chopped spinach , thawed, drained and squeezed dry\n" +
                "1 cup ricotta cheese\n" +
                "1/2 cup sour cream \n" +
                "2 cups shredded Monterey Jack cheese\n" +
                "10 (6 inch) corn tortillas\n" +
                "1 (19 ounce) can enchilada sauce\n","Preheat the oven to 375 degrees F (190 degrees C).\n" +
                "Melt butter in a saucepan over medium heat. Add garlic and onion; cook for a few minutes until fragrant, but not brown. Stir in spinach, and cook for about 5 more minutes. Remove from the heat, and mix in ricotta cheese, sour cream, and 1 cup of Monterey Jack cheese.\n" +
                "In a skillet over medium heat, warm tortillas one at a time until flexible, about 15 seconds. Spoon about 1/4 cup of the spinach mixture onto the center of each tortilla. Roll up, and place seam side down in a 9x13 inch baking dish. Pour enchilada sauce over the top, and sprinkle with the remaining cup of Monterey Jack.\n" +
                "Bake for 15 to 20 minutes in the preheated oven, until sauce is bubbling and cheese is lightly browned at the edges.","MainDish","false"));

        db.addRecipe(new RecipeModel(28,"Pan Seared Salmon I","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/m2.jpg?alt=media&token=cdfa89cc-3908-4c76-aa83-1563f18ea4ad",4.2f,42,10,380," 4 (6 ounce) fillets salmon \n" +
                "2 tablespoons olive oil\n" +
                "2 tablespoons capers\n" +
                "1/8 teaspoon salt\n" +
                "1/8 teaspoon ground black pepper\n" +
                "4 slices lemon","Preheat a large heavy skillet over medium heat for 3 minutes.\n" +
                "Coat salmon with olive oil. Place in skillet, and increase heat to high. Cook for 3 minutes. Sprinkle with capers, and salt and pepper. Turn salmon over, and cook for 5 minutes, or until browned. Salmon is done when it flakes easily with a fork.\n" +
                "Transfer salmon to individual plates, and garnish with lemon slices.","MainDish","false"));

        db.addRecipe(new RecipeModel(29,"Chicken Breasts with Balsamic Vinegar and Garlic","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/m3.jpg?alt=media&token=98ea867c-50e7-41c1-8429-dddd6bc1fae3",4.2f,42,25,380," 4 skinless, boneless chicken breasts salt and pepper to taste \n" +
                "3/4 pound fresh mushrooms, sliced \n" +
                "2 tablespoons all-purpose flour \n" +
                "2 tablespoons olive oil \n" +
                "6 cloves garlic\n" +
                "1/4 cup balsamic vinegar\n" +
                "3/4 cup chicken broth \n" +
                "1 bay leaf\n" +
                "1/4 teaspoon dried thyme\n" +
                "1 tablespoon butter","Season the chicken with salt and pepper. Rinse the mushrooms and pat dry. Season the flour with salt and pepper and dredge the chicken breasts in the flour mixture. Heat oil in a skillet over medium high heat and saute the chicken until it is nicely browned on one side (about 3 minutes).\n" +
                "Add the garlic. Turn the chicken breasts and scatter the mushrooms over them. Continue frying, shaking the skillet and stirring the mushrooms. Cook for about 3 minutes, then add the vinegar, broth, bay leaf and thyme. Cover tightly and simmer over medium low heat for 10 minutes, turning occasionally.\n" +
                "Transfer the chicken to a warm serving platter and cover with foil. Set aside. Continue simmering the sauce, uncovered, over medium high heat for about 7 minutes. Swirl in the butter or margarine and discard the bay leaf. Pour this mushroom sauce mixture over the chicken and serve.","MainDish","false"));

        db.addRecipe(new RecipeModel(30,"Chef John Stuffed Peppers","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/m4.jpg?alt=media&token=5a7ecf2a-faca-4d81-b847-fcd1888925ea",4.2f,42,80,380," 1 cup uncooked long grain white rice\n" +
                "2 cups water \n" +
                "Sauce: 1 onion, diced \n" +
                "1 tablespoon olive oil\n" +
                "2 cups marinara \n" +
                "1 cup beef broth \n" +
                "1 tablespoon balsamic vinegar\n" +
                "1/4 teaspoon crushed red pepper flakes \n" +
                "1 pound lean ground beef \n" +
                "1/4 pound hot Italian pork sausage, casing removed \n" +
                "1 (10 ounce) can diced tomatoes \n" +
                "1/4 cup chopped fresh Italian parsley\n" +
                "4 cloves garlic, minced \n" +
                "2 teaspoons salt\n" +
                "1 teaspoon freshly ground black pepper \n" +
                "1 pinch ground cayenne pepper \n" +
                "4 large green bell peppers, halved lengthwise and seeded \n" +
                "1 cup finely grated Parmigiano-Reggiano cheese, plus more for topping","Preheat the oven to 375 degrees F (190 degrees C).\n" +
                "Bring rice and water to a boil in a saucepan over high heat. Reduce heat to medium-low, cover, and simmer until the rice is tender, and the liquid has been absorbed, 20 to 25 minutes. Set the cooked rice aside.\n" +
                "Cook onion and olive oil over medium heat until onion begins to soften, about 5 minutes. Transfer half of cooked onion to a large bowl and set aside.\n" +
                "Stir marinara sauce, beef broth, balsamic vinegar, and red pepper flakes into the skillet; cook and stir for 1 minute.\n" +
                "Pour sauce mixture into a 9x13-inch baking dish and set aside.\n" +
                "Combine ground beef, Italian sausage, diced tomatoes, Italian parsley, garlic, salt, black pepper, and cayenne pepper into bowl with reserved onions; mix well. Stir in cooked rice and Parmigiano Reggiano. Stuff green bell peppers with beef and sausage mixture.\n" +
                "Place stuffed green bell pepper halves in the baking dish over tomato sauce; sprinkle with remaining Parmigiano-Reggiano, cover baking dish with aluminum foil, and bake in the preheated oven for 45 minutes.\n" +
                "Remove aluminum foil and bake until the meat is no longer pink, the green peppers are tender and the cheese is browned on top, an addition 20 to 25 minutes.","MainDish","false"));

        db.addRecipe(new RecipeModel(31,"World Best Lasagna","https://firebasestorage.googleapis.com/v0/b/foodle-59684.appspot.com/o/m5.jpg?alt=media&token=3a968c39-57fe-4955-8b5d-05782d93833d",4.2f,42,150,380,"1 pound sweet Italian sausage\n" +
                "3/4 pound lean ground beef\n" +
                "1/2 cup minced onion \n" +
                "2 cloves garlic, crushed \n" +
                "1 (28 ounce) can crushed tomatoes \n" +
                "2 (6 ounce) cans tomato paste \n" +
                "2 (6.5 ounce) cans canned tomato sauce\n" +
                "1/2 cup water \n" +
                "2 tablespoons white sugar \n" +
                "1 1/2 teaspoons dried basil leaves\n" +
                "1/2 teaspoon fennel seeds\n" +
                "1 teaspoon Italian seasoning\n" +
                "1 tablespoon salt \n" +
                "1/4 teaspoon ground black pepper\n" +
                "4 tablespoons chopped fresh parsley\n" +
                "12 lasagna noodles\n" +
                "16 ounces ricotta cheese\n" +
                "1 egg\n" +
                "1/2 teaspoon salt\n" +
                "3/4 pound mozzarella cheese, sliced \n" +
                "3/4 cup grated Parmesan cheese","In a Dutch oven, cook sausage, ground beef, onion, and garlic over medium heat until well browned. Stir in crushed tomatoes, tomato paste, tomato sauce, and water. Season with sugar, basil, fennel seeds, Italian seasoning, 1 tablespoon salt, pepper, and 2 tablespoons parsley. Simmer, covered, for about 1 1/2 hours, stirring occasionally.\n" +
                "Bring a large pot of lightly salted water to a boil. Cook lasagna noodles in boiling water for 8 to 10 minutes. Drain noodles, and rinse with cold water. In a mixing bowl, combine ricotta cheese with egg, remaining parsley, and 1/2 teaspoon salt.\n" +
                "Preheat oven to 375 degrees F (190 degrees C).\n" +
                "To assemble, spread 1 1/2 cups of meat sauce in the bottom of a 9x13 inch baking dish. Arrange 6 noodles lengthwise over meat sauce. Spread with one half of the ricotta cheese mixture. Top with a third of mozzarella cheese slices. Spoon 1 1/2 cups meat sauce over mozzarella, and sprinkle with 1/4 cup Parmesan cheese. Repeat layers, and top with remaining mozzarella and Parmesan cheese. Cover with foil: to prevent sticking, either spray foil with cooking spray, or make sure the foil does not touch the cheese.\n" +
                "Bake in preheated oven for 25 minutes. Remove foil, and bake an additional 25 minutes. Cool for 15 minutes before serving.\n","MainDish","false"));

    }

}
