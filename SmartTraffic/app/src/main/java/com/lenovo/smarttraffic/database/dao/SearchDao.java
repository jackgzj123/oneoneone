package com.lenovo.smarttraffic.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.smarttraffic.database.data.Car;

public class SearchDao {
    private SQLiteDatabase db;

    public SearchDao(final Context context){
        String path = Environment.getExternalStorageDirectory()+"/search.db";
        Log.e("GZJ", "CarDao: "+path);
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                //创建方法
                Toast.makeText(context,"数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法
                String sql = "create table car_tbb (_id integer primary key autoincrement,"
                        +"text varchar(30))";
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

    public void addCarMessage(String text){
        String sql = "insert into car_tbb  (text) values(?)";
        db.execSQL(sql,new Object[]{text});
    }
    public Cursor selectCarMessage(){
        String sql = "select * from car_tbb ";

        Log.e("GZjJJJ", "sql: "+sql);
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
    public void deleteCarMessage(){
        String sql = "delete from car_tbb ";
        db.execSQL(sql);
    }
    public void updateCarMessage(Car car){
        String sql = "update car_tbb set chong=? where name=?";
        db.execSQL(sql,new Object[]{car.getChong(),car.getName()});
    }


}
