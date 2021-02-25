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
    public static final int VERSION_NUM = 1;
    public final static String TABLE_NAME = "Chat Messages";
    public final static String COL_ID = "_id";
    public final static String COL_MESSAGES = "Messages";
    public final static String COL_SENDORRECEIVE = "TYPE";
    public final String[] columns = {COL_MESSAGES, COL_SENDORRECEIVE, COL_ID};
    private Object MessageType;

    public DBmessages(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    DBmessages(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase SQLdb){
        SQLdb.execSQL("CREATE TABLE " + TABLE_NAME + "( "
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_MESSAGES + " TEXT,"
                + COL_SENDORRECEIVE + " INTEGER);");
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("Database Upgrade", "Old Version:" + oldVersion + " newVersion:"+newVersion);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("Database downgrade", "Old Version:" + oldVersion + " newVersion:"+newVersion);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    private void printCursor(Cursor c, int version) {
        Log.i("printCursor", "DB version number: " + version
                + "\nNumber of columns: "
                + c.getColumnCount()
                + "\nColumn Names: " + Arrays.toString(c.getColumnNames())
                + "\nNumber of rows: " + c.getCount());
    }
}

