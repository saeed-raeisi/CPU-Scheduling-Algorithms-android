package com.example.csa.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.csa.Models.input_process_model;

import java.util.ArrayList;
import java.util.List;

public class database_functions {
    SQLiteOpenHelper msqLiteOpenHelper;
    SQLiteDatabase database;

    public database_functions(Context context) {
        msqLiteOpenHelper=new database(context);
    }

    public void open()
    {
        database=msqLiteOpenHelper.getWritableDatabase();
    }
    public void close()
    {
        database.close();
    }

    public void addprocesses(ArrayList<input_process_model> list)
    {
        open();

        for (int i=0;i<list.size();i++) {
            ContentValues contentValues=new ContentValues();
//            contentValues.put("name",list.get(i).getName());
//            contentValues.put("bt",list.get(i).getCbt());
//            contentValues.put("at",list.get(i).getArrival_time());
//            contentValues.put("complete",list.get(i).getCompleted());
            //contentValues.put("id",String.valueOf(i+14));
            contentValues.put(com.example.csa.sqldatabase.database.process_name,list.get(i).getName());
            contentValues.put(com.example.csa.sqldatabase.database.process_bt,list.get(i).getCbt());
            contentValues.put(com.example.csa.sqldatabase.database.process_at,list.get(i).getArrival_time());
            contentValues.put(com.example.csa.sqldatabase.database.process_complete,list.get(i).getCompleted());
            database.insert(com.example.csa.sqldatabase.database.table_name, null, contentValues);
        }
        close();
    }

    public ArrayList<input_process_model> getprocesses()
    {
        open();
        ArrayList process_list = new ArrayList<>();
        Cursor cursor;
        try {
//+" order by id"
            cursor=database.rawQuery("SELECT * FROM " + com.example.csa.sqldatabase.database.table_name,null);

            if (cursor.moveToFirst()) {
                do {
                    input_process_model process_model = new input_process_model();
                    process_model.setName(cursor.getString(cursor.getColumnIndex("name")));
                    process_model.setCbt(cursor.getInt(cursor.getColumnIndex("bt")));
                    process_model.setArrival_time(cursor.getInt(cursor.getColumnIndex("at")));
                    process_model.setCompleted(cursor.getInt(cursor.getColumnIndex("complete")));
                    process_list.add(process_model);

                } while (cursor.moveToNext());
            }
            cursor.close();
            delete_all();
        }catch (Error e)
        {
            Log.e("Table Error!",e.getMessage());
        }

        return process_list;
    }

    public void delete_all(){
        database.delete(com.example.csa.sqldatabase.database.table_name,null,null);
    }

}
