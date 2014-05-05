package com.android.yet_another_recipe_app;

/**
 * Created by Jason on 5/4/2014.
 */
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MeasurementsTable {

    public static final  String TABLE_MEASUREMENTS ="measurements";
    public static final  String COLUMN_ID ="measurementID";
    public static final  String COLUMN_NAME ="measurementName";


    //Create table
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_MEASUREMENTS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(MeasurementsTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASUREMENTS);
        onCreate(database);
    }
}
