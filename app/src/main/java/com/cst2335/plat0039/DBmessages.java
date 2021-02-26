package com.cst2335.plat0039;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class DBmessages extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBmessages";
    public static final int VERSION_NUM = 2;
    public final static String TABLE_NAME = "ChatMessages";
    public final static String COL_ID = "_id";
    public final static String COL_MESSAGES = "Messages";
    public final static String COL_SENDORRECEIVE = "TYPE";
    public final String[] columns = {COL_MESSAGES, COL_SENDORRECEIVE, COL_ID};
    private Object MessageType;


    public DBmessages(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_MESSAGES + " text,"
                + COL_SENDORRECEIVE  + " text);");  // add or remove columns
    }
    /*public void onCreate(SQLiteDatabase SQLdb){
        SQLdb.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_MESSAGES + " text,"
                + COL_SENDORRECEIVE + " text);");
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database Upgrade", "Old Version:" + oldVersion + " newVersion:"+newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database downgrade", "Old Version:" + oldVersion + " newVersion:"+newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

