package com.lenovo.smarttraffic.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class Collectiondao {
    private SQLiteDatabase db;

    public Collectiondao(final Context context){
        String path = Environment.getExternalStorageDirectory()+"/collection.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                //创建方法
                Toast.makeText(context,"数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法
                String sql = "create table envir_tbb (_id integer primary key autoincrement,"
                        +"username varchar(30),"
                        +"name varchar(30),"
                        +"phonenumber varchar(30),"
                        +"time varchar(30))";
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

    public void addCarMessage(String username,String name,String phonenumber, String time){
        String sql = "insert into envir_tbb  (username,name,phonenumber,time) values(?,?,?,?)";
        db.execSQL(sql,new Object[]{username,name,phonenumber,time});
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
