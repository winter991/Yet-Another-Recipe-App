package com.android.yet_another_recipe_app;

/**
 * Created by Jason on 5/5/2014.
 */

public class Steps {
    private int _stepID;
    private int _recipeID;
    private String _description;
    private long _duration;

    public Steps(int id, String desc,int recipe, long stepduration) {
        _stepID=id;
        _description=desc;
        _recipeID=recipe;
        _duration=stepduration;
    }

    public int get_stepID() {
        return _stepID;
    }

    public void set_stepID(int _stepID) {
        this._stepID = _stepID;
    }

    public int get_recipeID() {
        return _recipeID;
    }

    public void set_recipeID(int _recipeID) {
        this._recipeID = _recipeID;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public long get_duration() {
        return _duration;
    }

    public void set_duration(long _duration) {
        this._duration = _duration;
    }
}
