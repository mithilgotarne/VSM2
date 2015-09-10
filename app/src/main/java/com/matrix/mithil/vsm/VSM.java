package com.matrix.mithil.vsm;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class VSM extends TabActivity {

    Share arvind,deepika,rohit,salman;
    int asRate,dsRate,rssRate,ssRate;
    int credits,round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vsm);

        TabHost tabHost = getTabHost();
        this.setNewTab(this, tabHost, "tab1", R.string.tab1, R.id.tab1);
        this.setNewTab(this, tabHost, "tab2", R.string.tab2, R.id.tab2);

        credits=5000;
        round=-1;
        setRate();
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
                credits-=arvind.buyShare(e,credits);
                break;
            case R.id.deepikaBuy_button:
                e = (EditText)findViewById(R.id.deepika_editText);
                credits-=deepika.buyShare(e,credits);
                break;
            case R.id.rohitBuy_button:
                e = (EditText)findViewById(R.id.rohit_editText);
                credits-=rohit.buyShare(e,credits);
                break;
            case R.id.salmanBuy_button:
                e = (EditText)findViewById(R.id.salman_editText);
                credits-=salman.buyShare(e,credits);
                break;

        }
        TextView creditsView = (TextView)findViewById(R.id.availableCredits_textView);
        creditsView.setText("Credits: "+credits);
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
    public void transact(View v){

        setRate();
    }
    private void setRate(){
        this.round++;
        switch (round){
            case 0:
                asRate=10;
                dsRate=10;
                rssRate=10;
                ssRate=10;
                TextView round = (TextView) findViewById(R.id.round_textView);
                round.setText("Auction Round");
                return;
            case 1:
                arvind.setRate(25);
                deepika.setRate(90);
                rohit.setRate(20);
                salman.setRate(70);
            case 2:
                arvind.setRate(50);
                deepika.setRate(45);
                rohit.setRate(40);
                salman.setRate(35);
                break;
            default:
                this.round--;
                Toast.makeText(getApplicationContext(), "Event ends", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getBaseContext(), startup.class);
                startActivity(i);
                break;
        }

        TextView round = (TextView)findViewById(R.id.round_textView);
        round.setText("Round: " + this.round);
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(), "Sorry! Cannot go back at this step", Toast.LENGTH_SHORT).show();
    }

}
