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
package com.android.yet_another_recipe_app;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Jason on 5/4/2014.
 *
 */
public class RecipeTable {

    public static final  String TABLE_RECIPE ="recipes";
    public static final  String COLUMN_ID ="recipeID";
    public static final  String COLUMN_NAME ="recipeName";
    public static final  String COLUMN_DESCRIPTION ="recipeDescription";
    public static final  String COLUMN_TYPE ="recipeTypeID";


    //Create table
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_RECIPE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            +COLUMN_TYPE +" integer,"
            + COLUMN_DESCRIPTION +" text  null"
            +"FOREIGN KEY("+COLUMN_TYPE+") REFERENCES recipeType(RecipeTypeID)"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(RecipeTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        onCreate(database);
    }
}