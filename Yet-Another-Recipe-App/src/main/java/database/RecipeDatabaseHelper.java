package database;

/**
 * Created by Jason on 5/4/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.yet_another_recipe_app.objects.Ingredient;
import com.android.yet_another_recipe_app.objects.Measurement;
import com.android.yet_another_recipe_app.objects.Recipe;
import com.android.yet_another_recipe_app.objects.RecipeIngredients;
import com.android.yet_another_recipe_app.objects.RecipeType;
import com.android.yet_another_recipe_app.objects.Steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static database.IngredientTable.COLUMN_DESCRIPTION;
import static database.IngredientTable.COLUMN_ID;
import static database.IngredientTable.COLUMN_NAME;
import static database.IngredientTable.TABLE_INGREDIENTS;

//Handles DB stuff
public class RecipeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyRecipes.db";

    private static final int DATABASE_VERSION = 1;

    public RecipeDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    // Method is called during creation of the database

    @Override
    public void onCreate(SQLiteDatabase database) {
        RecipeTypeTable.onCreate(database);
        RecipeTable.onCreate(database);
        MeasurementsTable.onCreate(database);
        IngredientTable.onCreate(database);
        StepsTable.onCreate(database);
        RecipeIngredientsTable.onCreate(database);

    }
    // Method is called during an upgrade of the database,

    // e.g. if you increase the database version

    @Override

    public void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {

        RecipeTypeTable.onUpgrade(database, oldVersion, newVersion);
        RecipeTable.onUpgrade(database, oldVersion, newVersion);
        MeasurementsTable.onUpgrade(database, oldVersion, newVersion);
        IngredientTable.onUpgrade(database, oldVersion, newVersion);
        StepsTable.onUpgrade(database, oldVersion, newVersion);

    }

    //region Ingredients
    /**** Ingredients  *****/

    public long insertIngredient(Ingredient ingredient)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,ingredient.get_name());
        values.put(COLUMN_DESCRIPTION,ingredient.get_description());
        long ingredientID= db.insert(TABLE_INGREDIENTS,null,values);
        return ingredientID;
    }
    public int updateIngredient(Ingredient ingredient)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,ingredient.get_name());
        values.put(COLUMN_ID,ingredient.get_ingredientID());
        values.put(COLUMN_DESCRIPTION,ingredient.get_description());
        return  db.update(TABLE_INGREDIENTS,values,COLUMN_ID+ " = ?", new String[] { String.valueOf(ingredient.get_ingredientID()) });

    }

    public int deleteIngredient(int ingredientID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_INGREDIENTS, COLUMN_ID + " =?", new String[]{String.valueOf(ingredientID)});
    }

    public Ingredient selectIngredient(int ingredientID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT ingredientsID,ingredientName,ingredientDescription FROM ingredients WHERE ingredientsID ="+ingredientID;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            return new Ingredient(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    , cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
            );
        }
        else
            return null;

    }
    //returns all saved Ingredients
    public List<Ingredient> selectAllIngredient()
    {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT ingredientsID,ingredientName,ingredientDescription FROM ingredients" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
        }
        do {
            ingredients.add(new Ingredient(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                            )
                        );
        }while (cursor.moveToNext());
        return ingredients;
    }
    //returns   Ingredients by  specified ID
    public List<Ingredient> selectAllIngredient(int[] ingredientIDs)
    {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT ingredientsID,ingredientName,ingredientDescription FROM ingredients WHERE ingredientsID IN ("+Arrays.toString(ingredientIDs)+")" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
        }
        do {
            ingredients.add(new Ingredient(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    )
            );
        }while (cursor.moveToNext());
        return ingredients;
    }
    //endregion

    //region Measurements
    /**** Measurements  *****/

    public long insertMeasurement(Measurement measurement)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MeasurementsTable.COLUMN_NAME,measurement.get_description());
        return db.insert(MeasurementsTable.TABLE_MEASUREMENTS, null, values);

    }
    public int updateMeasurement(Measurement measurement)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MeasurementsTable.COLUMN_NAME,measurement.get_description());
        values.put(MeasurementsTable.COLUMN_ID,measurement.get_measurementID());

        return  db.update(MeasurementsTable.TABLE_MEASUREMENTS,values,MeasurementsTable.COLUMN_ID+ " = ?", new String[] { String.valueOf(measurement.get_measurementID()) });

    }

    public int deleteMeasurement(int measurementID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MeasurementsTable.TABLE_MEASUREMENTS, MeasurementsTable.COLUMN_ID + " = ?", new String[]{String.valueOf(measurementID)});
    }

    public Measurement selectMeasurement(int measurementID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  measurementID,measurementName FROM measurements WHERE measurementID ="+measurementID;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

        return  new Measurement(cursor.getInt(cursor.getColumnIndex(MeasurementsTable.COLUMN_ID))
                , cursor.getString(cursor.getColumnIndex(MeasurementsTable.COLUMN_NAME))
        );
        }
        else{
            return null;
        }
    }
    //returns all saved Ingredients
    public List<Measurement> selectAllMeasurements() {
        List<Measurement> measurements = new ArrayList<Measurement>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT  measurementID,measurementName FROM measurements";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                measurements.add(new Measurement(cursor.getInt(cursor.getColumnIndex(MeasurementsTable.COLUMN_ID))
                                , cursor.getString(cursor.getColumnIndex(MeasurementsTable.COLUMN_NAME))
                        )
                );
            } while (cursor.moveToNext());
        }
        return measurements;
    }
    //returns   Ingredients by  specified ID
    public List<Measurement> selectMeasurements(int[] IDs)
    {
        List<Measurement> measurements = new ArrayList<Measurement>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT ingredientsID,ingredientName,ingredientDescription FROM ingredients WHERE ingredientsID IN ("+Arrays.toString(IDs)+")" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            do {
                measurements.add(new Measurement(cursor.getInt(cursor.getColumnIndex(MeasurementsTable.COLUMN_ID))
                                , cursor.getString(cursor.getColumnIndex(MeasurementsTable.COLUMN_NAME))
                        )
                );
            } while (cursor.moveToNext());
        }
        return measurements;
    }
    //endregion

    //region Steps
    public long insertStep(Steps step)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StepsTable.COLUMN_DESCRIPTION,step.get_description());
        values.put(StepsTable.COLUMN_DURATION,step.get_duration());
        values.put(StepsTable.COLUMN_RECIPE_ID,step.get_recipeID());
        return db.insert(StepsTable.TABLE_STEPS,null,values);
    }

    public int updateSteps(Steps step)    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StepsTable.COLUMN_DESCRIPTION,step.get_description());
        values.put(StepsTable.COLUMN_DURATION,step.get_duration());
        values.put(StepsTable.COLUMN_RECIPE_ID,step.get_recipeID());

        return  db.update(StepsTable.TABLE_STEPS,values,StepsTable.COLUMN_ID+ " = ?", new String[] { String.valueOf(step.get_stepID()) });
    }

    public int deleteStep(int id)    {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(StepsTable.TABLE_STEPS, StepsTable.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
    public Steps selectStep(int id)    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  StepID,StepDescription,duration,recipeID FROM Steps WHERE StepID ="+id;
        Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount()>0) {
                cursor.moveToFirst();

            return  new Steps(cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_ID))
                    , cursor.getString(cursor.getColumnIndex(StepsTable.COLUMN_DESCRIPTION))
                    , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_RECIPE_ID))
                    , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_DURATION))
            );
        }
        else
            {
                return null;
            }
    }
    //returns all saved Ingredients
    public List<Steps> selectAllSteps()    {
        List<Steps> steps = new ArrayList<Steps>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  StepID,StepDescription,duration,recipeID FROM StepID" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                steps.add(new Steps(cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_ID))
                                , cursor.getString(cursor.getColumnIndex(StepsTable.COLUMN_DESCRIPTION))
                                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_RECIPE_ID))
                                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_DURATION))
                        )
                );
            } while (cursor.moveToNext());
        }
        return steps;
    }
    //returns   Ingredients by  specified ID
    //returns all saved Ingredients
    public List<Steps> selectAllSteps( int[] IDs)    {
        List<Steps> steps = new ArrayList<Steps>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  StepID,StepDescription,duration,recipeID FROM StepID where StepID IN ("+Arrays.toString(IDs)+")" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            do {
                steps.add(new Steps(cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_ID))
                                , cursor.getString(cursor.getColumnIndex(StepsTable.COLUMN_DESCRIPTION))
                                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_RECIPE_ID))
                                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_DURATION))
                        )
                );
            } while (cursor.moveToNext());
        }
        return steps;
    }

    private List<Steps> selectRecipeSteps(int RecipeID)
    {
        List<Steps> steps = new ArrayList<Steps>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  * FROM steps WHERE recipeID ="+RecipeID;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                steps.add(new Steps(cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_ID))
                                , cursor.getString(cursor.getColumnIndex(StepsTable.COLUMN_DESCRIPTION))
                                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_RECIPE_ID))
                                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_DURATION))
                        )
                );
            }while(cursor.moveToNext());
            return steps;
        }
        else {
            return null;
        }
    }
    //endregion

    //region RecipeTypes
    public long insertRecipeType(RecipeType type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecipeTypeTable.COLUMN_NAME,type.get_description());
        return db.insert(RecipeTypeTable.TABLE_RECIPE_TYPE,null,values);
    }
    public int deleteRecipeType(int id)    {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(RecipeTypeTable.TABLE_RECIPE_TYPE,RecipeTypeTable.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
    public RecipeType selectRecipeType(int id)    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  recipeTypeID,recipeTypeName FROM recipeType WHERE recipeTypeID ="+id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            return new RecipeType(cursor.getInt(cursor.getColumnIndex(RecipeTypeTable.COLUMN_ID))
                    , cursor.getString(cursor.getColumnIndex(RecipeTypeTable.COLUMN_NAME))
            );
        }
        else {
            return null;
        }
    }
    //returns all saved Ingredients
    public List<RecipeType> selectAllRecipeTypes()    {
        List<RecipeType> recipeTypes = new ArrayList<RecipeType>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  recipeTypeID,recipeTypeName FROM recipeType" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            do {
                recipeTypes.add(new RecipeType(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                                , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                        )
                );
            } while (cursor.moveToNext());
        }
        return recipeTypes;
    }
    //returns   Ingredients by  specified ID
    public List<RecipeType> selectRecipeTypes(int[] IDs)    {
        List<RecipeType> recipeTypes = new ArrayList<RecipeType>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  recipeTypeID,recipeTypeName FROM recipeType WHERE recipeTypeID IN ("+Arrays.toString(IDs)+")" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            do {
                recipeTypes.add(new RecipeType(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                                , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                        )
                );
            } while (cursor.moveToNext());
        }
        return recipeTypes;
    }
    //endregion

    //region Recipe
    // returns 1 on failure

    public long insertRecipe(Recipe recipe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecipeTable.COLUMN_NAME, recipe.get_recipeName());
        values.put(RecipeTable.COLUMN_DESCRIPTION,recipe.get_description());
        values.put(RecipeTable.COLUMN_TYPE,recipe.get_recipetypeID());
        try {
            int z=0;
            long recipeID = db.insert(RecipeTable.TABLE_RECIPE, null, values);
            // ASSUMPTION: The rows will be contiguous and there are no gaps. IE SET IDENTITY_INSERT is OFF!!!
            for (RecipeIngredients i : recipe.get_recipeIngredients()) {
                z= insertRecipeIngredient(i,recipeID);
            }
            for (Steps s : recipe.get_steps()) {
                insertStep(s);
            }
        }
        catch(Exception e)
        {
            Log.d(RecipeDatabaseHelper.class.getName(),"Exception:"+e.getMessage());
            //TODO backout txn
            return 1;
        }
        return 0;
    }
    public long updateRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecipeTable.COLUMN_NAME, recipe.get_recipeName());
        values.put(RecipeTable.COLUMN_DESCRIPTION,recipe.get_description());
        values.put(RecipeTable.COLUMN_TYPE,recipe.get_recipetypeID());
        try {
            long recipeID = db.update(RecipeTable.TABLE_RECIPE, values, RecipeTable.COLUMN_ID + " = ?", new String[]{String.valueOf(recipe.get_recipeID())});
            // ASSUMPTION: The rows will be contiguous and there are no gaps. IE SET IDENTITY_INSERT is OFF!!!
            for (RecipeIngredients i : recipe.get_recipeIngredients()) {
                updateRecipeIngredient(i);
            }
            for (Steps s : recipe.get_steps()) {
                updateSteps(s);
            }
        }
        catch(Exception e)
        {
            Log.d(RecipeDatabaseHelper.class.getName(),"Exception:"+e.getMessage());
            //TODO backout txn
            return 1;
        }
        return 0;
    }

    public Recipe selectRecipe(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  * FROM recipes WHERE recipeID ="+id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            ArrayList<RecipeIngredients> ingredientsList = selectRecipeIngredients(id);
            ArrayList<Steps> stepList = (ArrayList<Steps>) selectRecipeSteps(id);
            return new Recipe(id
                    , cursor.getString(cursor.getColumnIndex(RecipeTable.COLUMN_NAME))
                    ,ingredientsList
                    ,cursor.getInt(cursor.getColumnIndex(RecipeTable.COLUMN_TYPE))
                    ,stepList
                    , cursor.getString(cursor.getColumnIndex(RecipeTable.COLUMN_DESCRIPTION))
            );
        }
        else {
            return null;
        }
    }
    //endregion
    //region recipeIngredient
    public int insertRecipeIngredient(RecipeIngredients recipeIngredients,long recipeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecipeIngredientsTable.RECIPE_ID, recipeID);
        values.put(RecipeIngredientsTable.IngredientID,recipeIngredients.get_ingredientID());
        values.put(RecipeIngredientsTable.QUANTITY,recipeIngredients.get_quantity());
        values.put(RecipeIngredientsTable.MeasurementID,recipeIngredients.get_measurementID());

      return  (int)db.insert(RecipeIngredientsTable.TABLE_RECIPE, null, values);
    }

    private void updateRecipeIngredient(RecipeIngredients recipeIngredients) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecipeIngredientsTable.RECIPE_ID, recipeIngredients.get_recipeID());
        values.put(RecipeIngredientsTable.IngredientID,recipeIngredients.get_ingredientID());
        values.put(RecipeIngredientsTable.QUANTITY,recipeIngredients.get_quantity());
        values.put(RecipeIngredientsTable.MeasurementID,recipeIngredients.get_measurementID());

        db.update(RecipeIngredientsTable.TABLE_RECIPE, values, RecipeIngredientsTable.COLUMN_ID + " = ?", new String[]{String.valueOf(recipeIngredients.get_recipeIngredientID())});
    }
    private void deleteRecipeIngredient(int recipeIngredientID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        db.delete(RecipeIngredientsTable.TABLE_RECIPE, RecipeIngredientsTable.COLUMN_ID + " = ?", new String[]{String.valueOf(recipeIngredientID)});
    }
    private RecipeIngredients selectRecipeIngredient(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  * FROM recipesIngredients WHERE recipesIngredientsID ="+id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();

            return new RecipeIngredients(cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.COLUMN_ID))
                    , cursor.getLong(cursor.getColumnIndex(RecipeIngredientsTable.RECIPE_ID))
                    ,cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.IngredientID))
                    ,cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.MeasurementID))
                    ,cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.QUANTITY))
            );
        }
        else {
            return null;
        }
    }
    //gets all ingredients by recipe
    private ArrayList<RecipeIngredients> selectRecipeIngredients(int RecipeID)
    {
        ArrayList<RecipeIngredients> ingredientsList = new ArrayList<RecipeIngredients>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  * FROM recipesIngredients WHERE recipeID ="+RecipeID;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                {
                }
                ingredientsList.add( new RecipeIngredients(cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.COLUMN_ID))
                        , cursor.getLong(cursor.getColumnIndex(RecipeIngredientsTable.RECIPE_ID))
                        , cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.IngredientID))
                        , cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.MeasurementID))
                        , cursor.getInt(cursor.getColumnIndex(RecipeIngredientsTable.QUANTITY))
                        )
                );
            }while(cursor.moveToNext());
            return ingredientsList;
        }
        else {
            return null;
        }
    }

    //endregion




}
