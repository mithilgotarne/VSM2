package com.matrix.mithil.vsm;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Mithil on 10/7/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Manager";

    // table name
    private static final String TABLE_STOCKS = "stocks";

    private static final String TABLE_ROUNDS = "rounds";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SHARES = "shares";
    private static final String KEY_CREDITS = "credits";

    private static final String KEY_NO = "no";
    private static final String KEY_HOLDINGS = "holdings";
    private static final String KEY_EVALUATION = "eval";
    private static final String KEY_PROFIT = "profit";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STOCK_TABLE = "CREATE TABLE " + TABLE_STOCKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SHARES + " INT,"
                + KEY_CREDITS + " INT" + ")";

        String CREATE_ROUND_TABLE = "CREATE TABLE " + TABLE_ROUNDS + "("
                + KEY_NO + " INTEGER PRIMARY KEY," + KEY_HOLDINGS + " INT,"
                + KEY_EVALUATION + " INT," + KEY_PROFIT + " INT" + ")";

        db.execSQL(CREATE_STOCK_TABLE);
        db.execSQL(CREATE_ROUND_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUNDS);
        // Create tables again
        onCreate(db);
    }

    public void addStock(Stock s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SHARES, s.shares);
        values.put(KEY_CREDITS, s.credits);

        // Inserting Row
        db.insert(TABLE_STOCKS, null, values);
        db.close();
    }

    public void addRound(Round r) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOLDINGS, r.holdings);
        values.put(KEY_EVALUATION, r.evaluation);
        values.put(KEY_PROFIT, r.profit);

        // Inserting Row
        db.insert(TABLE_ROUNDS, null, values);
        db.close();
    }


    public ArrayList<Stock> getAllStocks(ArrayList<Stock> stocks) {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STOCKS;
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                stocks.get(i).id = (Integer.parseInt(cursor.getString(0)));
                stocks.get(i).shares = Integer.parseInt(cursor.getString(1));
                stocks.get(i++).credits = Integer.parseInt(cursor.getString(2));

            } while (cursor.moveToNext() && i < 7);
        }
        return stocks;
    }

    public ArrayList<Round> getAllRound(Activity activity) {
        ArrayList<Round> list = new ArrayList<Round>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ROUNDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Round r = new Round(activity, Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                list.add(r);
                r.update();
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }


    public int updateStock(Stock s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SHARES, s.shares);
        values.put(KEY_CREDITS, s.credits);

        // updating row
        return db.update(TABLE_STOCKS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(s.id)});
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_STOCKS);
        db.execSQL("delete from " + TABLE_ROUNDS);
    }
}