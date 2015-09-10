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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VSM extends TabActivity {

    Share arvind,deepika,rohit,salman;
    int asRate,dsRate,rssRate,ssRate;
    int credits, round, profit;
    List<String> roundList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vsm);

        TabHost tabHost = getTabHost();
        this.setNewTab(this, tabHost, "tab1", R.string.tab1, R.id.tab1);
        this.setNewTab(this, tabHost, "tab2", R.string.tab2, R.id.tab2);

        credits=5000;
        profit = 0;
        round = 0;
        newRate();
        arvind=new Share(getApplicationContext(),(TextView)findViewById(R.id.arvindShares),(TextView)findViewById(R.id.arvindRate_textView),(TextView)findViewById(R.id.arvindCredits),asRate);
        deepika=new Share(getApplicationContext(),(TextView)findViewById(R.id.deepikaShares),(TextView)findViewById(R.id.deepikaRate_textView),(TextView)findViewById(R.id.deepikaCredits),dsRate);
        rohit = new Share(getApplicationContext(),(TextView)findViewById(R.id.rohitShares),(TextView)findViewById(R.id.rohitRate_textView),(TextView)findViewById(R.id.rohitCredits),rssRate);
        salman=new Share(getApplicationContext(),(TextView) findViewById(R.id.salmanShares),(TextView)findViewById(R.id.salmanRate_textView),(TextView)findViewById(R.id.salmanCredits),ssRate);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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

            case R.id.arvindBuy_button:
                e = (EditText)findViewById(R.id.arvind_editText);
                credits -= arvind.buyShare(e, credits);
                break;
            case R.id.deepikaBuy_button:
                e = (EditText)findViewById(R.id.deepika_editText);
                credits -= deepika.buyShare(e, credits);
                break;
            case R.id.rohitBuy_button:
                e = (EditText)findViewById(R.id.rohit_editText);
                credits -= rohit.buyShare(e, credits);
                break;
            case R.id.salmanBuy_button:
                e = (EditText)findViewById(R.id.salman_editText);
                credits -= salman.buyShare(e, credits);
                break;

        }
        TextView creditsView = (TextView)findViewById(R.id.availableCredits_textView);
        creditsView.setText("Credits: " + credits);
    }
    public void sell(View v){
        EditText e;
        switch (v.getId()){
            case R.id.arvindSell_button:
                e = (EditText)findViewById(R.id.arvind_editText);
                credits+=arvind.sellShare(e);
                break;
            case R.id.deepikaSell_button:
                e = (EditText)findViewById(R.id.deepika_editText);
                credits+=deepika.sellShare(e);
                break;
            case R.id.rohitSell_button:
                e = (EditText)findViewById(R.id.rohit_editText);
                credits+=rohit.sellShare(e);
                break;
            case R.id.salmanSell_button:
                e = (EditText)findViewById(R.id.salman_editText);
                credits+=salman.sellShare(e);
                break;
        }
        TextView creditsView = (TextView)findViewById(R.id.availableCredits_textView);
        creditsView.setText("Credits: " + credits);
    }

    protected void getDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View promptView = layoutInflater.inflate(R.layout.passcode, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText) promptView.findViewById(R.id.inputPasscode);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String passcode = input.getText().toString();
                        if (passcode.equals("")) {
                            Toaster("Enter paascode!");
                            getDialog();
                        } else if (passcode.equals("1234")) {
                            displayAllNewRate();
                            dialog.cancel();
                        } else {
                            Toaster("Sorry! Wrong passcode.");
                            getDialog();
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
        int profit = arvind.credits + deepika.credits + rohit.credits + salman.credits;
        newRate();
        profit = arvind.credits + deepika.credits + rohit.credits + salman.credits - profit;
        getDialog();
        if (round > 0) {
            roundList.add("Round: " + (round - 1) + " Profit: " + profit);
            Toast.makeText(getApplicationContext(), "Round: " + (round - 1) + " Profit: " + profit, Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roundList);
            ListView roundListView = (ListView) findViewById(R.id.roundList);
            roundListView.setAdapter(adapter);
        }

    }

    private void newRate() {
        this.round++;
        switch (round){
            case 1:
                asRate=10;
                dsRate=10;
                rssRate=10;
                ssRate=10;
                TextView round = (TextView) findViewById(R.id.round_textView);
                round.setText("Round: " + this.round);
                break;
            case 2:
                arvind.setRate(25);
                deepika.setRate(90);
                rohit.setRate(20);
                salman.setRate(70);
                break;
            case 3:
                arvind.setRate(50);
                deepika.setRate(45);
                rohit.setRate(40);
                salman.setRate(35);
                break;
            case 4:
                arvind.setRate();
                deepika.setRate();
                rohit.setRate();
                salman.setRate();
                break;
            case 5:
                arvind.setRate();
                deepika.setRate();
                rohit.setRate();
                salman.setRate();
                break;
        }

    }

    private void displayAllNewRate() {
        TextView round = (TextView) findViewById(R.id.round_textView);
        round.setText("Round: " + this.round);
        arvind.displayNewRate();
        deepika.displayNewRate();
        rohit.displayNewRate();
        salman.displayNewRate();
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(), "Sorry! Cannot go back at this step", Toast.LENGTH_SHORT).show();
    }

}
