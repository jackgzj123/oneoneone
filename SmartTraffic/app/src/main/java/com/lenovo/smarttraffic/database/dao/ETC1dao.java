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
public class ETC1dao {
    private SQLiteDatabase db;

    public ETC1dao(final Context context){
        String path = Environment.getExternalStorageDirectory()+"/etc1.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                //创建方法
                Toast.makeText(context,"数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法
                String sql = "create table envir_tbb (_id integer primary key autoincrement,"
                        +"carname varchar(30),"
                        +"timedate varchar(30),"
                        +"paymoney varchar(30),"
                        +"timeday varchar(30),"
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

    public void addCarMessage(String timedate,String timeday,String carname,String paymoney, String time){
        String sql = "insert into envir_tbb  (timedate,carname,paymoney,time,timeday) values(?,?,?,?,?)";
        db.execSQL(sql,new Object[]{timedate,carname,paymoney,time,timeday});
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
