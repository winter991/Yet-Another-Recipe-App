package com.android.yet_another_recipe_app.objects;

/**
 * Created by Jason on 5/5/2014.
 */
public class Measurement {
    public Measurement(int id, String desc) {
        _description=desc;
        _measurementID=id;

    }
    //Class for measurements
    // The reason why we have this table is to support easy conversion
    //ex TBSP to C or g to oz

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public int get_measurementID() {
        return _measurementID;
    }

    public void set_measurementID(int _measurementID) {
        this._measurementID = _measurementID;
    }
    private String _description;
    private int _measurementID;
    // Conversion Formulas
}
