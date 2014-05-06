package database;

/**
 * Created by Jason on 5/4/2014.
 */
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class RecipeTypeTable {
    public static final  String TABLE_RECIPE_TYPE ="recipeType";
    public static final  String COLUMN_ID ="recipeTypeID";
    public static final  String COLUMN_NAME ="recipeTypeName";


    //Create table
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_RECIPE_TYPE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(RecipeTypeTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_TYPE);
        onCreate(database);
    }
}
