package com.example.myapplication09;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


      public static final String DATABASE_NAME ="Reservation.db";
    public static final String  TABLE_NAME ="Reservation_table";
    public static final String  COL_1="ID";
    public static final String  COL_2="Name";
    public static final String  COL_3 ="Address";
    public static final String  COL_4 ="Telephone";
    public static final String  COL_5="Holiday_type";
    private Object ContentValues;



    public DatabaseHelper( Context context) {
        super(context,DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Address TEXT, Telephone INTEGER,Holiday_type text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS   "+TABLE_NAME);
        onCreate (db);
    }
    public boolean insertData(String Name, String Address, String Telephone, String Holiday_type ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Address);
        contentValues.put(COL_4,Telephone);
        contentValues.put(COL_5,Holiday_type);
       long result  = db.insert(TABLE_NAME, null , (android.content.ContentValues) ContentValues);
       if(result == -1)
           return false;
       else
           return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null );
        return res;

    }
    public boolean updareDate(){
SQLiteDatabase db = this.getWritableDatabase();
ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Address);
        contentValues.put(COL_4,Telephone);
        contentValues.put(COL_5,Holiday_type);
        db.update(TABLE_NAME, contentValues, "ID = ?" ,new String[] {id} );
         return true;
    }
    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID = ?", new String[] {id});
    }
}


