package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

public class DatabaseHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABALE_NAME = "info";
    public static final int VERSION = 1;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABALE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Password TEXT)";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABALE_NAME, null);

    }

    public Cursor getdatalogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("select *from " + TABALE_NAME + " where " + COL_2 + "=? and " + COL_3 + "=?", new String[]{username, password});
        return result;
    }


    public Cursor viewdata()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select*from "+TABALE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;

    }

















    public boolean insertdata(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, password);
        long result = db.insert(TABALE_NAME, null, contentValues);
        if (result == -1) {

            return false;
        } else {
            return true;
        }

    }

}


