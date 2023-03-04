package com.example.taskapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Databasehelper extends SQLiteOpenHelper {
    private static final String TAG = "Databasehelper";
    private static final String TABLE_NAME = "todotask_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "taskname";
    public static final String COL3 = "isckecked";

    public Databasehelper(Context context) {
        super(context,TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtable = "CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT, "+COL3+" INTEGER)";
        sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public Boolean addData(String item, int ischecked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,item);
        contentValues.put(COL3,ischecked);
        Log.d(TAG,"addData: Adding "+ item+ " to "+ TABLE_NAME);
        long result =db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getId(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL1+" FROM "+TABLE_NAME+" WHERE " + COL2+ " = '" +item+ "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public void updatedata( int newischecked, int oldischecked, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COL3+" = "+newischecked+" WHERE "+COL1+" = "+id;
        db.execSQL(query);
    }

    public void deletedata(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME+ " WHERE "+COL1+ " = "+id;
        db.execSQL(query);
    }
}
