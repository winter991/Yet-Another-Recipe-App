package com.android.yet_another_recipe_app;

/**
 * Created by Jason on 5/5/2014.
 */
public class RecipeType {
    private int _typeID;
    private String _description;

    public RecipeType(int id, String desc) {
        _description=desc;
        _typeID=id;
    }

    public int get_typeID() {
        return _typeID;
    }

    public void set_typeID(int _typeID) {
        this._typeID = _typeID;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
