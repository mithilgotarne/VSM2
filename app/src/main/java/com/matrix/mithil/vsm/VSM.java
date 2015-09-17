package com.matrix.mithil.vsm;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VSM extends TabActivity {

    ArrayList<Stock> stocks;
    Round setRound;
    int credits, round, profit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vsm);
        TabHost tabHost = getTabHost();
        this.setNewTab(this, tabHost, "tab1", R.string.tab1, R.id.tab1);
        this.setNewTab(this, tabHost, "tab2", R.string.tab2, R.id.tab2);
        stocks = new ArrayList<Stock>();
        credits=5000;
        profit = 0;
        round = 0;
        setRound = new Round(this);
        stocks.add(new Stock(getApplicationContext(), (TextView) findViewById(R.id.stockOneShares), (TextView) findViewById(R.id.stockOneRate_textView), (TextView) findViewById(R.id.stockOneCredits)));
        stocks.add(new Stock(getApplicationContext(), (TextView) findViewById(R.id.stockTwoShares), (TextView) findViewById(R.id.stockTwoRate_textView), (TextView) findViewById(R.id.stockTwoCredits)));
        stocks.add(new Stock(getApplicationContext(), (TextView) findViewById(R.id.stockThreeShares), (TextView) findViewById(R.id.stockThreeRate_textView), (TextView) findViewById(R.id.stockThreeCredits)));
        stocks.add(new Stock(getApplicationContext(), (TextView) findViewById(R.id.stockFourShares), (TextView) findViewById(R.id.stockFourRate_textView), (TextView) findViewById(R.id.stockFourCredits)));
        stocks.add(new Stock(getApplicationContext(), (TextView) findViewById(R.id.stockFiveShares), (TextView) findViewById(R.id.stockFiveRate_textView), (TextView) findViewById(R.id.stockFiveCredits)));
        stocks.add(new Stock(getApplicationContext(), (TextView) findViewById(R.id.stockSixShares), (TextView) findViewById(R.id.stockSixRate_textView), (TextView) findViewById(R.id.stockSixCredits)));
        stocks.add(new Stock(getApplicationContext(), (TextView) findViewById(R.id.stockSevenShares), (TextView) findViewById(R.id.stockSevenRate_textView), (TextView) findViewById(R.id.stockSevenCredits)));
        initialize(MainActivity.session);
        newRate();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initialize(int session) {
        if (session == 1) {
            stocks.get(0).setName(getString(R.string.rohit), (TextView) findViewById(R.id.stockOne_textView));
            stocks.get(1).setName(getString(R.string.novak), (TextView) findViewById(R.id.stockTwo_textView));
            stocks.get(2).setName(getString(R.string.rooney), (TextView) findViewById(R.id.stockThree_textView));
            stocks.get(3).setName(getString(R.string.salman), (TextView) findViewById(R.id.stockFour_textView));
            stocks.get(4).setName(getString(R.string.jennifer), (TextView) findViewById(R.id.stockFive_textView));
            stocks.get(5).setName(getString(R.string.rahul), (TextView) findViewById(R.id.stockSix_textView));
            stocks.get(6).setName(getString(R.string.tyrion), (TextView) findViewById(R.id.stockSeven_textView));

        } else {
            stocks.get(0).setName(getString(R.string.sreesanth), (TextView) findViewById(R.id.stockOne_textView));
            stocks.get(1).setName(getString(R.string.muray), (TextView) findViewById(R.id.stockTwo_textView));
            stocks.get(2).setName(getString(R.string.bale), (TextView) findViewById(R.id.stockThree_textView));
            stocks.get(3).setName(getString(R.string.deepika), (TextView) findViewById(R.id.stockFour_textView));
            stocks.get(4).setName(getString(R.string.cruise), (TextView) findViewById(R.id.stockFive_textView));
            stocks.get(5).setName(getString(R.string.arvind), (TextView) findViewById(R.id.stockSix_textView));
            stocks.get(6).setName(getString(R.string.walter), (TextView) findViewById(R.id.stockSeven_textView));
        }
    }

    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int contentID ){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title)); // new function to inject our own tab layout
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }

    private View getTabIndicator(Context context, int title) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        return view;
    }

    public void buy(View v){
        EditText e;
        switch (v.getId()){

            case R.id.stockOneBuy_button:
                e = (EditText) findViewById(R.id.stockOne_editText);
                credits -= stocks.get(0).buyShare(e, credits);
                break;
            case R.id.stockTwoBuy_button:
                e = (EditText) findViewById(R.id.stockTwo_editText);
                credits -= stocks.get(1).buyShare(e, credits);
                break;
            case R.id.stockThreeBuy_button:
                e = (EditText) findViewById(R.id.stockThree_editText);
                credits -= stocks.get(2).buyShare(e, credits);
                break;
            case R.id.stockFourBuy_button:
                e = (EditText) findViewById(R.id.stockFour_editText);
                credits -= stocks.get(3).buyShare(e, credits);
                break;
            case R.id.stockFiveBuy_button:
                e = (EditText) findViewById(R.id.stockFive_editText);
                credits -= stocks.get(4).buyShare(e, credits);
                break;
            case R.id.stockSixBuy_button:
                e = (EditText) findViewById(R.id.stockSix_editText);
                credits -= stocks.get(5).buyShare(e, credits);
                break;
            case R.id.stockSevenBuy_button:
                e = (EditText) findViewById(R.id.stockSeven_editText);
                credits -= stocks.get(6).buyShare(e, credits);
                break;


        }
        TextView creditsView = (TextView)findViewById(R.id.availableCredits_textView);
        creditsView.setText("Credits: " + credits);
    }
    public void sell(View v){
        EditText e;
        switch (v.getId()){
            case R.id.stockOneSell_button:
                e = (EditText) findViewById(R.id.stockOne_editText);
                credits += stocks.get(0).sellShare(e);
                break;
            case R.id.stockTwoSell_button:
                e = (EditText) findViewById(R.id.stockTwo_editText);
                credits += stocks.get(1).sellShare(e);
                break;
            case R.id.stockThreeSell_button:
                e = (EditText) findViewById(R.id.stockThree_editText);
                credits += stocks.get(2).sellShare(e);
                break;
            case R.id.stockFourSell_button:
                e = (EditText) findViewById(R.id.stockFour_editText);
                credits += stocks.get(3).sellShare(e);
                break;
            case R.id.stockFiveSell_button:
                e = (EditText) findViewById(R.id.stockFive_editText);
                credits += stocks.get(4).sellShare(e);
                break;
            case R.id.stockSixSell_button:
                e = (EditText) findViewById(R.id.stockSix_editText);
                credits += stocks.get(5).sellShare(e);
                break;
            case R.id.stockSevenSell_button:
                e = (EditText) findViewById(R.id.stockSeven_editText);
                credits += stocks.get(6).sellShare(e);
                break;

        }
        TextView creditsView = (TextView)findViewById(R.id.availableCredits_textView);
        creditsView.setText("Credits: " + credits);
    }

    protected void getDialog(final int evaluation) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View promptView = layoutInflater.inflate(R.layout.passcode, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText) promptView.findViewById(R.id.inputPasscode);
        final TextView summary = (TextView) promptView.findViewById(R.id.summary);
        summary.setText("Summary of Round: " + (round - 1));
        final TextView profit = (TextView) promptView.findViewById(R.id.profit);
        if (evaluation < 0)
            profit.setText("Loss: " + Math.abs(evaluation));
        else
            profit.setText("Profit: " + evaluation);
        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String passcode = input.getText().toString();
                        if (passcode.equals("")) {
                            Toaster("Enter paascode!");
                            getDialog(evaluation);
                        } else if (passcode.equals("1234")) {
                            displayAllNewRate();
                            dialog.cancel();
                        } else {
                            Toaster("Sorry! Wrong passcode.");
                            getDialog(evaluation);
                        }
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

    public void transact(View v){
        int profit = getEvaluation();
        newRate();
        profit = getEvaluation() - profit;
        getDialog(profit);
        if (round > 0) {
            setRound.update(round - 1, getHoldings(), getEvaluation(), profit);
        }
    }

    private int getHoldings() {
        int sum = 0;
        for (Stock s : stocks)
            sum += s.shares;
        return sum;
    }

    private int getEvaluation() {
        int sum = 0;
        for (Stock s : stocks)
            sum += s.credits;
        return sum;
    }

    private void newRate() {
        this.round++;
        switch (round){
            case 1:
                for (Stock s : stocks) {
                    s.setRate();
                    s.displayNewRate();
                }
                TextView round = (TextView) findViewById(R.id.round_textView);
                round.setText("Round: " + this.round);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                for (Stock s : stocks)
                    s.setRate();
                break;

        }

    }

    private void displayAllNewRate() {
        TextView round = (TextView) findViewById(R.id.round_textView);
        round.setText("Round: " + this.round);
        for (Stock s : stocks)
            s.displayNewRate();
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(), "Sorry! Cannot go back at this step", Toast.LENGTH_SHORT).show();
    }

}
