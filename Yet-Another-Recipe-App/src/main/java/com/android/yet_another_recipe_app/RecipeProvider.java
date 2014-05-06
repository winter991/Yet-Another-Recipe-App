package com.android.yet_another_recipe_app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import database.RecipeDatabaseHelper;

/**
 * Created by Jason on 5/4/2014.
 */

//Note Since Recipe is kinda useless unless we bring baxk steps and ingredients and such, this provider joins against the others to return a recipe object
public class RecipeProvider extends ContentProvider {
    // database
    private RecipeDatabaseHelper database;
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
