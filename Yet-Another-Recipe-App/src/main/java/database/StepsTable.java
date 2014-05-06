package database;

/**
 * Created by Jason on 5/4/2014.
 */

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StepsTable {
    public static final  String TABLE_STEPS ="steps";
    public static final  String COLUMN_ID ="stepID";
    public static final  String COLUMN_DURATION="duration";
    public static final  String COLUMN_RECIPE_ID ="recipeID";
    public static final  String COLUMN_DESCRIPTION ="description";


    //Create table
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_STEPS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DURATION + " integer not null, "
            +COLUMN_RECIPE_ID +" integer,"
            + COLUMN_DESCRIPTION +" text  null ,"
            +"FOREIGN KEY("+COLUMN_RECIPE_ID+") REFERENCES recipeTable(RecipeID)"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(StepsTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STEPS);
        onCreate(database);
    }
}
