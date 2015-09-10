package com.matrix.mithil.vsm;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mithil on 8/23/2015.
 */
public class Share extends VSM {
    TextView s, r, c;  //shares,rate,credits;
    int shares,rate,credits;
    Context context;

    Share(Context context,TextView s,TextView r, TextView c,int rate){
        this.s=s;
        this.r=r;
        this.c=c;
        this.shares=0;
        this.rate=rate;
        this.credits=0;
        this.setRate(rate);
        this.context=context;
    }

    public int buyShare(EditText e, int totalCredits){
        int number = 0;
        String string = e.getText().toString();

        if(string.equals("")||string.contains(".")){
            Toast.makeText(context, "Invalid Input.", Toast.LENGTH_SHORT).show();
            e.setText("");
            return 0;
        }
        else{
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
                return rate*number;
            }
        }

    }
    public int sellShare(EditText e){
        int number = 0;
        String string = e.getText().toString();

        if(string.equals("")||string.contains(".")){
            Toast.makeText(context, "Invalid Input.", Toast.LENGTH_SHORT).show();
            e.setText("");
            return 0;
        }
        else {
            number = Integer.parseInt(string);
            if (shares - number >= 0) {
                shares -= number;
                credits -= rate * number;
                s.setText("Shares: " + shares);
                c.setText("Worth Credits: " + credits);
                e.setText("");
                return rate*number;
            } else{
                Toast.makeText(context, "Not enough Shares to Sell", Toast.LENGTH_SHORT).show();
                e.setText("");
                return 0;
            }

        }

    }
    public void setRate(int rate){
        this.rate=rate;
        credits=rate*shares;
        r.setText("Rate: "+rate);
        c.setText("Worth Credits: "+credits);
    }

}
