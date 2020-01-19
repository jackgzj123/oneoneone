package com.lenovo.smarttraffic.database.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import com.lenovo.smarttraffic.database.data.EnvirtData;

public class ChangeDao {
    private SQLiteDatabase db;

    public ChangeDao(final Context context){
        String path = Environment.getExternalStorageDirectory()+"/change.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                //创建方法
                Toast.makeText(context,"数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法
                String sql = "create table envir_tbb (_id integer primary key autoincrement,"
                        +"hong varchar(30),"
                        +"huang varchar(30),"
                        +"lv varchar(30),"
                        +"name varchar(30))";
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

    public void addCarMessage(String hong,String huang,String lv,String name){
        String sql = "insert into envir_tbb  (hong,huang,lv,name) values(?,?,?,?)";
        db.execSQL(sql,new Object[]{hong,huang,lv,name});
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
