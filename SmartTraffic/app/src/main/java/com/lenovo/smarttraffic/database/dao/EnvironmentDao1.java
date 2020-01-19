package com.lenovo.smarttraffic.database.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import com.lenovo.smarttraffic.database.data.EnvirtData;

public class EnvironmentDao1 {
    private SQLiteDatabase db;

    public EnvironmentDao1(final Context context){
        String path = Environment.getExternalStorageDirectory()+"/environment1.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                //创建方法
                Toast.makeText(context,"数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法
                String sql = "create table envir_tbb (_id integer primary key autoincrement,"
                        +"wen varchar(30),"
                        +"shi varchar(30),"
                        +"co2 varchar(30),"
                        +"pm2 varchar(30),"
                        +"time varchar(30),"
                        +"sun varchar(30))";
                sqLiteDatabase.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

                //升级方法
                Toast.makeText(context,"数据库升级", Toast.LENGTH_SHORT).show();
            }
        };

        //用于获取数据库对象
        db = helper.getReadableDatabase();

    }

    public void addCarMessage(String wen,String shi,String co2,String pm2, String time,String sun){
        String sql = "insert into envir_tbb  (wen,shi,co2,pm2,time,sun) values(?,?,?,?,?,?)";
        db.execSQL(sql,new String[]{wen,shi,co2,pm2,time,sun});
    }
    public Cursor selectCarMessage(){
        String sql = "select * from envir_tbb ";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
    public void deleteCarMessage(String...strings){
        String sql = "delete  from envir_tbb ";
        db.execSQL(sql);
    }

}
