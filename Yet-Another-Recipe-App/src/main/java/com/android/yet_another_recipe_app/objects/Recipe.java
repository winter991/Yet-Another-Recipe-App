package com.android.yet_another_recipe_app.objects;


import java.util.ArrayList;

/**
 * Created by Jason on 5/7/2014.
 */
public class Recipe {
    int _recipeID;
    String _recipeName;
    ArrayList<RecipeIngredients> _recipeIngredients;
    int _recipetypeID;
    String _description;
    ArrayList<Steps> _steps;
    public Recipe(String name, ArrayList<RecipeIngredients> recipeIngredients,int recipetypeID, ArrayList<Steps> steps,String desc)
    {
        _recipeName=name;
        _recipeIngredients=recipeIngredients;
        _steps=steps;    
        _recipetypeID=recipetypeID;
        _description=desc;
    }
    public Recipe(int ID,String name, ArrayList<RecipeIngredients> recipeIngredients,int recipetypeID, ArrayList<Steps> steps,String desc)
    {
        _recipeID=ID;
        _recipeName=name;
        _recipeIngredients=recipeIngredients;
        _steps=steps;
        _recipetypeID=recipetypeID;
        _description=desc;
    }

    public String get_recipeName() {
        return _recipeName;
    }

    public void set_recipeName(String _recipeName) {
        this._recipeName = _recipeName;
    }

    public ArrayList<RecipeIngredients> get_recipeIngredients() {
        return _recipeIngredients;
    }

    public void set_recipeIngredients(ArrayList<RecipeIngredients> _recipeIngredients) {
        this._recipeIngredients = _recipeIngredients;
    }

    public int get_recipetypeID() {
        return _recipetypeID;
    }

    public void set_recipetypeID(int _recipetypeID) {
        this._recipetypeID = _recipetypeID;
    }

    public ArrayList<Steps> get_steps() {
        return _steps;
    }

    public void set_steps(ArrayList<Steps> _steps) {
        this._steps = _steps;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public int get_recipeID() {
        return _recipeID;
    }

    public void set_recipeID(int _recipeID) {
        this._recipeID = _recipeID;
    }
}
