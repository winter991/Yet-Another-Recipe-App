package database;

/**
 * Created by Jason on 5/4/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.yet_another_recipe_app.Ingredient;
import com.android.yet_another_recipe_app.Measurement;
import com.android.yet_another_recipe_app.RecipeType;
import com.android.yet_another_recipe_app.Steps;

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
        if (cursor != null) {
            cursor.moveToFirst();
        }
            return  new Ingredient(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                        ,cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                        , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                        );

    }
    //returns all saved Ingredients
    public List<Ingredient> selectAllIngredient()
    {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT ingredientsID,ingredientName,ingredientDescription FROM ingredients" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
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
        if (cursor != null) {
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
        values.put(COLUMN_NAME,measurement.get_description());
        values.put(COLUMN_ID,measurement.get_measurementID());

        return  db.update(MeasurementsTable.TABLE_MEASUREMENTS,values,COLUMN_ID+ " = ?", new String[] { String.valueOf(measurement.get_measurementID()) });

    }

    public int deleteMeasurement(int measurementID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(MeasurementsTable.TABLE_MEASUREMENTS, COLUMN_ID + " =?", new String[]{String.valueOf(measurementID)});
    }

    public Measurement selectMeasurement(int measurementID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  measurementID,measurementName FROM measurements WHERE measurementID ="+measurementID;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return  new Measurement(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
        );

    }
    //returns all saved Ingredients
    public List<Measurement> selectAllMeasurements()
    {
        List<Measurement> measurements = new ArrayList<Measurement>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  measurementID,measurementName FROM measurements" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            measurements.add( new Measurement(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    )
            );
        }while (cursor.moveToNext());
        return measurements;
    }
    //returns   Ingredients by  specified ID
    public List<Measurement> selectMeasurements(int[] IDs)
    {
        List<Measurement> measurements = new ArrayList<Measurement>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT ingredientsID,ingredientName,ingredientDescription FROM ingredients WHERE ingredientsID IN ("+Arrays.toString(IDs)+")" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            measurements.add( new Measurement(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    )
            );
        }while (cursor.moveToNext());
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
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return  new Steps(cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_ID))
                , cursor.getString(cursor.getColumnIndex(StepsTable.COLUMN_DESCRIPTION))
                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_RECIPE_ID))
                , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_DURATION))
        );
    }
    //returns all saved Ingredients
    public List<Steps> selectAllSteps()    {
        List<Steps> steps = new ArrayList<Steps>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="\"SELECT  StepID,StepDescription,duration,recipeID FROM StepID" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            steps.add(new Steps(cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_ID))
                    , cursor.getString(cursor.getColumnIndex(StepsTable.COLUMN_DESCRIPTION))
                    , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_RECIPE_ID))
                    , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_DURATION))
                    )
            );
        }while (cursor.moveToNext());
        return steps;
    }
    //returns   Ingredients by  specified ID
    //returns all saved Ingredients
    public List<Steps> selectAllSteps( int[] IDs)    {
        List<Steps> steps = new ArrayList<Steps>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  StepID,StepDescription,duration,recipeID FROM StepID where StepID IN ("+Arrays.toString(IDs)+")" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            steps.add(new Steps(cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_ID))
                            , cursor.getString(cursor.getColumnIndex(StepsTable.COLUMN_DESCRIPTION))
                            , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_RECIPE_ID))
                            , cursor.getInt(cursor.getColumnIndex(StepsTable.COLUMN_DURATION))
                    )
            );
        }while (cursor.moveToNext());
        return steps;
    }
    //endregion

    //region RecipeTypes
    public long insertRecipeType(RecipeType type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecipeTypeTable.COLUMN_NAME,type.get_description());
        return db.insert(RecipeTypeTable.COLUMN_NAME,null,values);
    }
    public int deleteRecipeType(int id)    {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(RecipeTypeTable.TABLE_RECIPE_TYPE,COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
    public RecipeType selectRecipeType(int id)    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  recipeTypeID,recipeTypeName FROM recipeType WHERE recipeTypeID ="+id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return  new RecipeType(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
        );

    }
    //returns all saved Ingredients
    public List<RecipeType> selectAllRecipeTypes()    {
        List<RecipeType> recipeTypes = new ArrayList<RecipeType>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  recipeTypeID,recipeTypeName FROM recipeType" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            recipeTypes.add( new RecipeType(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    )
            );
        }while (cursor.moveToNext());
        return recipeTypes;
    }
    //returns   Ingredients by  specified ID
    public List<RecipeType> selectRecipeTypes(int[] IDs)    {
        List<RecipeType> recipeTypes = new ArrayList<RecipeType>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT  recipeTypeID,recipeTypeName FROM recipeType WHERE recipeTypeID IN ("+Arrays.toString(IDs)+")" ;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            recipeTypes.add( new RecipeType(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    )
            );
        }while (cursor.moveToNext());
        return recipeTypes;
    }
    //endregion



}
