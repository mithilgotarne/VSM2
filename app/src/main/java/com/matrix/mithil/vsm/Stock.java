package com.matrix.mithil.vsm;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Mithil on 8/23/2015.
 */
public class Stock extends VSM {
    static int idTrack = 0;
    public String name;
    public int id, shares, rate, credits;
    TextView s, r, c, t;  //shares,rate,credits;
    Context context;

    Stock(Context context, TextView s, TextView r, TextView c) {
        this.s = s;
        this.r = r;
        this.c = c;
        id = idTrack;
        idTrack++;
        this.shares = 0;
        this.credits = 0;
        this.context = context;
    }

    public void setName(String name, TextView t) {
        this.t = t;
        this.name = name;
        t.setText(name);
    }

    public int buyShare(EditText e, int totalCredits) {
        int number = 0;
        String string = e.getText().toString();
        if (string.equals("") || string.contains(".") || rate == 0) {
            Toast.makeText(context, "Invalid Input.", Toast.LENGTH_SHORT).show();
            e.setText("");
            return 0;
        } else {
            number = Integer.parseInt(string);

            if (totalCredits - rate * number < 0) {
                Toast.makeText(context, "Not enough credits", Toast.LENGTH_SHORT).show();
                e.setText("");
                return 0;
            } else {
                shares += number;
                credits += rate * number;
                s.setText("Shares: " + shares);
                c.setText("Worth Credits: " + credits);
                e.setText("");
                return rate * number;
            }
        }

    }

    public int sellShare(EditText e) {
        int number = 0;
        String string = e.getText().toString();

        if (string.equals("") || string.contains(".") || rate == 0) {
            Toast.makeText(context, "Invalid Input.", Toast.LENGTH_SHORT).show();
            e.setText("");
            return 0;
        } else {
            number = Integer.parseInt(string);
            if (shares - number >= 0) {
                shares -= number;
                credits -= rate * number;
                s.setText("Shares: " + shares);
                c.setText("Worth Credits: " + credits);
                e.setText("");
                return rate * number;
            } else {
                Toast.makeText(context, "Not enough Shares to Sell", Toast.LENGTH_SHORT).show();
                e.setText("");
                return 0;
            }

        }

    }

    public void setRate(int rate) {
        this.rate = rate;
        credits = rate * shares;
        if (rate == 0)
            shares = 0;
    }

    public void displayNewRate() {
        r.setText("Rate: " + rate);
        c.setText("Worth Credits: " + credits);
        s.setText("Shares: " + shares);
    }

    public void setRate() {
        setRate(new Random().nextInt(101));
    }

}
