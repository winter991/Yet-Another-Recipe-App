
/*
* Copyright (c) 2014. Jason Esposito
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class IngredientTable {
    // Database Table
    //Stores the ingredients
    public static final  String TABLE_INGREDIENTS ="ingredients";
    public static final  String COLUMN_ID ="ingredientsID";
    public static final  String COLUMN_NAME ="ingredientName";
    public static final  String COLUMN_DESCRIPTION ="ingredientDescription";


    //Create table
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_INGREDIENTS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_DESCRIPTION +" text  null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(IngredientTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(database);
    }
}


