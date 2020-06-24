package com.example.csa.sqldatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lord on 3/30/2018.
 */

public class database extends SQLiteOpenHelper {

    private final  static String databae_name="csa.db";
    private final  static int databae_version=1;
    public final  static String table_name="processes";
    public final static String process_id="id";
    public final static String process_name="name";
    public final static String process_bt="bt";
    public final static String process_at="at";
    public final static String process_complete="complete";

    public database(Context context) {
        super(context, databae_name, null, databae_version);
    }
//AUTOINCREMENT
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE IF NOT EXISTS "+ table_name + "("
                + process_id + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + process_name + " TEXT NOT NULL ,"
                + process_bt + " INTEGER NOT NULL ,"
                + process_at + " INTEGER NOT NULL ,"
                + process_complete + " INTEGER NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
}