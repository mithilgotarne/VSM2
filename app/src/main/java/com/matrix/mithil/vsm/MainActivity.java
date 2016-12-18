package com.matrix.mithil.vsm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static String PREFS = "myPrefs";
    public static int session;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    public Intent i;
    int flag;
    SharedPreferences sharedPreferences;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFS, 0);
        flag = sharedPreferences.getInt("startup", 0);
        i = new Intent(MainActivity.this, VSM.class);

        final Boolean end = sharedPreferences.getBoolean("end", false);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                if (end) {
                    Intent intent = new Intent(MainActivity.this, End.class);
                    startActivity(intent);
                } else if (flag == 0)
                    getTerms();
                // close this activity
                else
                    startActivity(i);
            }
        }, SPLASH_TIME_OUT);

    }

    protected void getInitialDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View promptView = layoutInflater.inflate(R.layout.startup_passcode, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText) promptView.findViewById(R.id.inputPasscode);
        if (Build.VERSION.SDK_INT < 11) {
            alertDialogBuilder.setInverseBackgroundForced(true);
        }

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String passcode = input.getText().toString();
                        if (passcode.equals("")) {
                            Toaster("Enter paascode!");
                            getInitialDialog();
                        } else if (passcode.equals("0000")) {
                            flag = 1;
                            startActivity(i);
                        } else {
                            Toaster("Sorry! Wrong passcode.");
                            getInitialDialog();
                        }
                    }
                });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }

    protected void getTerms() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View promptView = layoutInflater.inflate(R.layout.terms, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);
        if (Build.VERSION.SDK_INT < 11) {
            alertDialogBuilder.setInverseBackgroundForced(true);
        }

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getInitialDialog();
                    }
                }).setNeutralButton("Disagree", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
    }


    private void Toaster(String string) {
        final Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 500);
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt("startup", flag);
        e.commit();
        e.apply();
        super.onPause();
    }
}
