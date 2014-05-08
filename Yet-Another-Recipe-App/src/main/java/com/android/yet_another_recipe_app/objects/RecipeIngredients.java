package com.android.yet_another_recipe_app.objects;

/**
 * Created by Jason on 5/7/2014.
 */
public class RecipeIngredients {
    int _recipeIngredientID;
    long _recipeID;
    int _ingredientID;
    int _measurementID;
    int _quantity;
    public RecipeIngredients( long recipeID,int ingredientID,int measurementID,int quantity) {
        _recipeID=recipeID;
        _ingredientID=measurementID;
        _measurementID=measurementID;
        _quantity=quantity;
    }
    public RecipeIngredients( int id,long recipeID,int ingredientID,int measurementID,int quantity) {
        _recipeID=recipeID;
        _ingredientID=ingredientID;
        _measurementID=measurementID;
        _quantity=quantity;
        _recipeIngredientID=id;
    }



    public int get_recipeIngredientID() {
        return _recipeIngredientID;
    }

    public void set_recipeIngredientID(int _recipeIngredientID) {
        this._recipeIngredientID = _recipeIngredientID;
    }

    public long get_recipeID() {
        return _recipeID;
    }

    public void set_recipeID(long _recipeID) {
        this._recipeID = _recipeID;
    }

    public int get_ingredientID() {
        return _ingredientID;
    }

    public void set_ingredientID(int _ingredientID) {
        this._ingredientID = _ingredientID;
    }

    public int get_measurementID() {
        return _measurementID;
    }

    public void set_measurementID(int _measurementID) {
        this._measurementID = _measurementID;
    }

    public int get_quantity() {
        return _quantity;
    }

    public void set_quantity(int _quantity) {
        this._quantity = _quantity;
    }
}
