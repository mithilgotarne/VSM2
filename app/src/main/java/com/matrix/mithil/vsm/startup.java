package com.matrix.mithil.vsm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class startup extends AppCompatActivity {

    String sessionOne, sessionTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        sessionOne = getString(R.string.session_one_passcode);
        sessionTwo = getString(R.string.session_two_passcode);

        final Button submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submitPasscode();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void submitPasscode() {
        EditText e = (EditText) findViewById(R.id.startupPasscode_editText);
        String passcode = e.getText().toString();
        if (passcode.equals(""))
            Toast.makeText(getApplicationContext(), "Enter passcode", Toast.LENGTH_SHORT).show();
        else if (passcode.equals(sessionOne)) {
            Intent i = new Intent(this, VSM.class);
            startActivity(i);
        } else if (passcode.equals(sessionTwo)) {
            Intent i = new Intent(this, VSM.class);
            startActivity(i);
        } else
            Toast.makeText(getApplicationContext(), "Sorry! Wrong passcode.", Toast.LENGTH_SHORT).show();
    }


}
