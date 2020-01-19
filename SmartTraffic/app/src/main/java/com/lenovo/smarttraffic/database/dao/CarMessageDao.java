package com.lenovo.smarttraffic.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.smarttraffic.database.data.CarData;
import com.lenovo.smarttraffic.database.data.EnvirtData;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class CarMessageDao {
    private SQLiteDatabase db;

    public CarMessageDao(final Context context){
        String path = Environment.getExternalStorageDirectory()+"/carmessage.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

                //创建方法
                Toast.makeText(context,"数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法
                String sql = "create table carmessage_tbb (_id integer primary key autoincrement,"
                        +"carNumber varchar(30),"
                        +"chong varchar(30),"
                        +"chongyue varchar(30),"
                        +"peoplename varchar(30),"
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

    public void addCarMessage(String carnumber,String chong,String chongyue,String peoplename, String time){
        String sql = "insert into carmessage_tbb  (carNumber,chong,chongyue,peoplename,time) values(?,?,?,?,?)";
        //todo
        db.execSQL(sql,new Object[]{carnumber,chong,chongyue,peoplename,time});
    }
    public Cursor selectCarMessage(String strings){
        String sql = "select * from carmessage_tbb ";
        Log.e("GZjJJJ", "sql: "+sql);
        if (strings != null){
            sql+=strings;
        }
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
    public void deleteCarMessage(){
        String sql = "delete  from carmessage_tbb ";
        db.execSQL(sql);
    }
}
