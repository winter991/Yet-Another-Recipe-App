package com.android.yet_another_recipe_app;

/**
 * Created by Jason on 5/5/2014.
 */
public class Ingredient {
    public Ingredient(int id, String name, String desc) {
        _ingredientID=id;
        _name=name;
        _description=desc;
    }
    public Ingredient()
    {}

    public int get_ingredientID() {
        return _ingredientID;
    }

    public void set_ingredientID(int _ingredientID) {
        this._ingredientID = _ingredientID;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    private int _ingredientID;
    private String _name;
    private String _description;

}
