package com.matrix.mithil.vsm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class End extends AppCompatActivity {

    final static String PREFS = "myPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DatabaseHandler db;

    Boolean end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        sharedPreferences = getSharedPreferences(PREFS, 0);
        editor = sharedPreferences.edit();
        db = new DatabaseHandler(this);
        ArrayList<Round> roundsList = db.getAllRound(this);
        int grand = sharedPreferences.getInt("grand", 0);
        end = sharedPreferences.getBoolean("end", true);
        TextView grandText = (TextView) findViewById(R.id.grand);
        if (grand < 0)
            grandText.setText("Total Loss: " + Math.abs(grand));
        else
            grandText.setText("Total Profit: " + grand);
    }

    @Override
    protected void onPause() {
        editor.putBoolean("end", end);
        editor.commit();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Sorry! Cannot go back at this step", Toast.LENGTH_SHORT).show();
    }

    public void end(View view) {
        db.delete();
        editor.clear();
        editor.commit();
        editor.apply();
        finish();
        System.exit(0);
    }
}
