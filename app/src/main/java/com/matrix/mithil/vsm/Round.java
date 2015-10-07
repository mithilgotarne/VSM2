package com.matrix.mithil.vsm;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Mithil on 9/17/2015.
 */
public class Round extends VSM {
    public Activity activity;
    public int number, holdings, evaluation, profit;

    public Round(Activity activity, int x, int h, int e, int p) {
        number = x;
        holdings = h;
        evaluation = e;
        profit = p;
        this.activity = activity;
    }

    public void update() {
        LinearLayout linearLayout;
        switch (number) {
            case 1:
                linearLayout = (LinearLayout) this.activity.findViewById(R.id.round1);
                linearLayout.setBackgroundColor(this.activity.getApplicationContext().getResources().getColor(R.color.primary_light));
                setText((TextView) this.activity.findViewById(R.id.round1_textView), "Round: 1");
                setText((TextView) this.activity.findViewById(R.id.round1_holdings), "Holdings: " + holdings);
                setText((TextView) this.activity.findViewById(R.id.round1_evaluation), "Evaluation: " + evaluation);
                if (profit < 0)
                    setText((TextView) this.activity.findViewById(R.id.round1_profit), "Loss: " + Math.abs(profit));
                else
                    setText((TextView) this.activity.findViewById(R.id.round1_profit), "Profit: " + profit);
                break;
            case 2:
                linearLayout = (LinearLayout) this.activity.findViewById(R.id.round2);
                linearLayout.setBackgroundColor(this.activity.getApplicationContext().getResources().getColor(R.color.primary_light));

                setText((TextView) this.activity.findViewById(R.id.round2_textView), "Round: 2");
                setText((TextView) this.activity.findViewById(R.id.round2_holdings), "Holdings: " + holdings);
                setText((TextView) this.activity.findViewById(R.id.round2_evaluation), "Evaluation: " + evaluation);
                if (profit < 0)
                    setText((TextView) this.activity.findViewById(R.id.round2_profit), "Loss: " + Math.abs(profit));
                else
                    setText((TextView) this.activity.findViewById(R.id.round2_profit), "Profit: " + profit);
                break;
            case 3:
                linearLayout = (LinearLayout) this.activity.findViewById(R.id.round3);
                linearLayout.setBackgroundColor(this.activity.getApplicationContext().getResources().getColor(R.color.primary_light));

                setText((TextView) this.activity.findViewById(R.id.round3_textView), "Round: 3");
                setText((TextView) this.activity.findViewById(R.id.round3_holdings), "Holdings: " + holdings);
                setText((TextView) this.activity.findViewById(R.id.round3_evaluation), "Evaluation: " + evaluation);
                if (profit < 0)
                    setText((TextView) this.activity.findViewById(R.id.round3_profit), "Loss: " + Math.abs(profit));
                else
                    setText((TextView) this.activity.findViewById(R.id.round3_profit), "Profit: " + profit);
                break;
            case 4:
                linearLayout = (LinearLayout) this.activity.findViewById(R.id.round4);
                linearLayout.setBackgroundColor(this.activity.getApplicationContext().getResources().getColor(R.color.primary_light));

                setText((TextView) this.activity.findViewById(R.id.round4_textView), "Round: 4");
                setText((TextView) this.activity.findViewById(R.id.round4_holdings), "Holdings: " + holdings);
                setText((TextView) this.activity.findViewById(R.id.round4_evaluation), "Evaluation: " + evaluation);
                if (profit < 0)
                    setText((TextView) this.activity.findViewById(R.id.round4_profit), "Loss: " + Math.abs(profit));
                else
                    setText((TextView) this.activity.findViewById(R.id.round4_profit), "Profit: " + profit);
                break;
            case 5:
                linearLayout = (LinearLayout) this.activity.findViewById(R.id.round5);
                linearLayout.setBackgroundColor(this.activity.getApplicationContext().getResources().getColor(R.color.primary_light));

                setText((TextView) this.activity.findViewById(R.id.round5_textView), "Round: 5");
                setText((TextView) this.activity.findViewById(R.id.round5_holdings), "Holdings: " + holdings);
                setText((TextView) this.activity.findViewById(R.id.round5_evaluation), "Evaluation: " + evaluation);
                if (profit < 0)
                    setText((TextView) this.activity.findViewById(R.id.round5_profit), "Loss: " + Math.abs(profit));
                else
                    setText((TextView) this.activity.findViewById(R.id.round5_profit), "Profit: " + profit);
                break;
        }
    }

    private void setText(TextView t, String string) {
        t.setText(string);
    }


}
