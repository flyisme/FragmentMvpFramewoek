package com.huatugz.flycommen.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by heyf on 2018/4/14 0014.
 */

public class SqlLiteHelper extends SQLiteOpenHelper {
    public final String TABLE_TEMP = "tbl_temp";

    public SqlLiteHelper(Context context) {
        super(context, "fly.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_TEMP + "(num integer primary key autoincrement, cid text null, jsonData text null, cacheTime text null, type text null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
