package com.lenovo.smarttraffic.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.smarttraffic.database.data.Car;

public class CarDao {
    private SQLiteDatabase db;

    public CarDao(final Context context){
        String path = Environment.getExternalStorageDirectory()+"/car.db";
        Log.e("GZJ", "CarDao: "+path);
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                //创建方法
                Toast.makeText(context,"数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法
                String sql = "create table car_tbb (_id integer primary key autoincrement,"
                        +"name varchar(30),"
                        +"chong integer,"
                        +"operator varchar(30),"
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

    public void addCarMessage(Car car,String time){
        String sql = "insert into car_tbb  (name,chong,operator,time) values(?,?,?,?)";
        db.execSQL(sql,new Object[]{car.getName(),car.getChong(),"admin",time});
    }
    public Cursor selectCarMessage(String strings){
        String sql = "select * from car_tbb ";

        if (strings != null){
            sql+=strings;
        }

        Log.e("GZjJJJ", "sql: "+sql);
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
    public void deleteCarMessage(String...strings){
        String sql = "delete from car_tbb where " +strings[0] + "='" +strings[1] + "'";
        db.execSQL(sql);
    }
    public void updateCarMessage(Car car){
        String sql = "update car_tbb set chong=? where name=?";
        db.execSQL(sql,new Object[]{car.getChong(),car.getName()});
    }


}
