package com.lenovo.smarttraffic.ui.activity.T56;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Sqlhelp extends SQLiteOpenHelper {
    public Sqlhelp(@Nullable Context context) {
        super(context, "gzj1.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table en_tb ("+
                "wen Integer,"+
                "shi Integer,"+
                "sun Integer,"+
                "co2 Integer,"+
                "pm Integer,"+
                "dao Integer,"+
                "time Long)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
