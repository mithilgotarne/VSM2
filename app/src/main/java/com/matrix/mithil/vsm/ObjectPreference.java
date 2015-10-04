package com.matrix.mithil.vsm;

/**
 * Created by Mithil on 10/5/2015.
 */

import android.app.Application;

public class ObjectPreference extends Application {
    private static final String TAG = "ObjectPreference";
    private ComplexPreferences complexPrefenreces = null;

    @Override
    public void onCreate() {
        super.onCreate();
        complexPrefenreces = ComplexPreferences.getComplexPreferences(getBaseContext(), "abhan", MODE_PRIVATE);
        android.util.Log.i(TAG, "Preference Created.");
    }

    public ComplexPreferences getComplexPreference() {
        if (complexPrefenreces != null) {
            return complexPrefenreces;
        }
        return null;
    }
}
