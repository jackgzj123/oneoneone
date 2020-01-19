package com.lenovo.smarttraffic.ui.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;

public class Item6Activity extends AppCompatActivity {

    private Spinner mSpinner;
    private Button mSelectBtn;
    private ListView mList;
    private String s;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ImageView mRedImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item6);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        createSQL();


        initview();

        initevent();
    }

    private void insertSQL() {
        ContentValues values = new ContentValues();
        //insert into 表名 (列1，列2) values（值1，值2）
        values.put("hong","1");
        values.put("huang","1");
        values.put("lv","1");
        db.insert("trafficc_tb",null,values);

        ContentValues values1 = new ContentValues();
        //insert into 表名 (列1，列2) values（值1，值2）
        values.put("hong","12");
        values.put("huang","7");
        values.put("lv","19");
        db.insert("trafficc_tb",null,values);

        ContentValues values2 = new ContentValues();
        //insert into 表名 (列1，列2) values（值1，值2）
        values.put("hong","10");
        values.put("huang","10");
        values.put("lv","10");
        db.insert("trafficc_tb",null,values);

        ContentValues values3 = new ContentValues();
        //insert into 表名 (列1，列2) values（值1，值2）
        values.put("hong","13");
        values.put("huang","15");
        values.put("lv","14");
        db.insert("trafficc_tb",null,values);

        ContentValues values4 = new ContentValues();
        //insert into 表名 (列1，列2) values（值1，值2）
        values.put("hong","16");
        values.put("huang","18");
        values.put("lv","17");
        db.insert("trafficc_tb",null,values);
    }

    private void createSQL() {
        String path = Environment.getExternalStorageDirectory()+"/traffic2.db";
        SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper(this,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                //创建方法
                Toast.makeText(Item6Activity.this,"数据库创建",Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法，那么我们可以将表的创建工作放在这里面完成
                String sql = "create table trafficc_tb (_id integer primary key autoincrement,"
                        + "hong varchar(30),"
                        +"huang varchat(20),"
                        +"lv varchat(20))";
                sqLiteDatabase.execSQL(sql);
                insertSQL();
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };

        db = sqLiteOpenHelper.getReadableDatabase();

    }

    private void initevent() {

        mRedImg.setBackgroundResource(R.drawable.traffic_animate);
        AnimationDrawable animaition = (AnimationDrawable)mRedImg.getBackground();
        animaition.setOneShot(false);   //设置是否只播放一次，和上面xml配置效果一致
        animaition.start();


        final ArrayList<String> arrayList = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
        //添加要显示的内容
        arrayList.add("路口升序");
        arrayList.add("路口降序");
        arrayList.add("红灯升序");
        arrayList.add("红灯降序");
        arrayList.add("绿灯升序");
        arrayList.add("绿灯降序");
        arrayList.add("黄灯升序");
        arrayList.add("黄灯降序");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);//创建适配器  this--上下文  android.R.layout.simple_spinner_item--显示的模板   arrayList--显示的内容
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置下拉之后的布局的样式 这里采用的是系统的一个布局
        mSpinner.setAdapter(arrayAdapter);//将适配器给下拉框
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//当改变下拉框的时候会触发
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//改变内容的时候

                //todo
                Log.e("position", "onItemSelected: "+position );
                switch (position){
                    case 0:
                        s = "_id asc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                    case 1:
                        s = "_id desc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        break;
                    case 2:
                        s = "hong asc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        break;
                    case 3:
                        s = "hong desc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        break;
                    case 4:
                        s = "lv asc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        break;
                    case 5:
                        s = "lv desc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        break;
                    case 6:
                        s = "huang asc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        break;
                    case 7:
                        s = "huang desc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

            }
        });




        //查询操作
        mSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示查询结果
                mList.setAdapter(adapter);
            }
        });

        //deletemag();

    }

    private void deletemag() {
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder =new AlertDialog.Builder(Item6Activity.this);
                //2、设置对话框样式
                builder.setTitle("提示");//设置标题
                builder.setMessage("您确定删除这一行吗？");//设置文本
                //3、按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.delete("trafficc_tb", "_id=?", new String[]{cursor.getString(cursor.getColumnIndex("_id"))});

                        getAdapter();
                        mList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }
                });
                builder.setNegativeButton("取消",null);
                //4、展示
                builder.show();

                return false;
            }
        });
    }

    private void initview() {
        mSpinner = findViewById(R.id.item6_spinner);
        mSelectBtn = findViewById(R.id.item6_btn);
        mList = findViewById(R.id.item6_list);
        mRedImg = findViewById(R.id.item6_red);
    }

    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                this,R.layout.item1, cursor,
                new String[]{"_id","hong","huang","lv"},//表头
                new int[]{R.id.lukou_item,R.id.hong_item,R.id.huang_item,R.id.lv_item},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

}
