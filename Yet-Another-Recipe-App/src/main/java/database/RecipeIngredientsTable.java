package database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Jason on 5/7/2014.
 */
public class RecipeIngredientsTable {

    public static final  String TABLE_RECIPE ="recipesIngredients";
    public static final  String COLUMN_ID ="recipeIngredientID";
    public static final  String RECIPE_ID ="recipeID";
    public static final  String IngredientID ="ingredientID";
    public static final  String MeasurementID ="measurementID";
    public static final  String QUANTITY ="quantity";
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_RECIPE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + RECIPE_ID + " integer not null, "
            +IngredientID +" integer NOT NULL,"
            + MeasurementID +" INTEGER  NOT NULL ,"
            +QUANTITY+" INTEGER NOT NULL ,"
            +" FOREIGN KEY("+RECIPE_ID+") REFERENCES recipes(RecipeID),"
            +" FOREIGN KEY("+IngredientID+") REFERENCES ingredients(IngredientID),"
            +" FOREIGN KEY("+MeasurementID+") REFERENCES Measurements(MeasurementID)"

            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(RecipeIngredientsTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        onCreate(database);
    }


}
