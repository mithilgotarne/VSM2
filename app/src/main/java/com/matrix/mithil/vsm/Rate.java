package com.matrix.mithil.vsm;

/**
 * Created by Mithil on 9/19/2015.
 */
class Rate {
    public static int[][] StartupRate(int session) {
        int finalRate[][] = new int[5][7];
        switch (session) {
            case 1:
                int rate[][] = {{10, 20, 30, 40, 50, 60, 70},
                        {60, 80, 90, 100, 50, 40, 30},
                        {},
                        {},
                        {}};
                finalRate = rate;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

        return finalRate;
    }
}
