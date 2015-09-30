package com.matrix.mithil.vsm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    public static int session;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    static {
        session = 0;
    }

    public Intent i;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(MainActivity.this, VSM.class);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                getInitialDialog();
                // close this activity
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
                        } else if (passcode.equals(getString(R.string.session_one_passcode))) {
                            session = 1;
                            startActivity(i);
                        } else if (passcode.equals(getString(R.string.session_two_passcode))) {
                            session = 2;
                            startActivity(i);
                        } else {
                            Toaster("Sorry! Wrong passcode.");
                            getInitialDialog();
                        }
                    }
                }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
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

}
