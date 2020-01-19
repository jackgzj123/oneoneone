package com.lenovo.smarttraffic.ui.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.ChangeDao;
import com.lenovo.smarttraffic.database.data.CarData;
import com.lenovo.smarttraffic.database.data.EnvironmentData;
import com.lenovo.smarttraffic.database.data.LightData;
import com.lenovo.smarttraffic.ui.adapter.MySimpleCursorAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item13Activity extends AppCompatActivity  implements MyDialog3.SettingListener{

    private ListView mList;
    private Button mXqBtn;
    private Button mSelectBtn;
    private Spinner mSpinner;

    private String s;
    private Cursor cursor;
    private MySimpleCursorAdapter adapter;
    private SQLiteDatabase db;

    private ChangeDao changeDao;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private List<LightData> lightDatalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item13);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        createSQL();
        db.delete("trafficc_tb",null,null);
        getNet();

        changeDao = new ChangeDao(this);
        Cursor cursor = changeDao.selectCarMessage();
        if (cursor.moveToLast()){
            String hong1 = cursor.getString(cursor.getColumnIndex("hong"));
            Log.e("hong1", "onCreate: "+hong1 );
        }

        initview();

        initevent();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        getNet();
                    }
                });

            }
        },0,3000);
    }

    private void createSQL() {
        String path = Environment.getExternalStorageDirectory()+"/traffic3.db";
        SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper(this,path,null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                //创建方法
                Toast.makeText(Item13Activity.this,"数据库创建",Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法，那么我们可以将表的创建工作放在这里面完成
                String sql = "create table trafficc_tb (_id integer primary key autoincrement,"
                        + "roadname varchar(30),"
                        + "hong integer,"
                        +"huang integer,"
                        +"lv integer)";
                sqLiteDatabase.execSQL(sql);

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };

        db = sqLiteOpenHelper.getReadableDatabase();
    }

    private void initevent() {
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
                        mList.setAdapter(adapter);
                        break;
                    case 2:
                        s = "hong asc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                    case 3:
                        s = "hong desc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                    case 4:
                        s = "lv asc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                    case 5:
                        s = "lv desc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                    case 6:
                        s = "huang asc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                    case 7:
                        s = "huang desc";
                        cursor = db.query("trafficc_tb",null,null,null,null,null,s);
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

            }
        });
        mSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示查询结果
                mList.setAdapter(adapter);
            }
        });



    }

    private void initview() {
        mSpinner = findViewById(R.id.item13_spinner);
        mSelectBtn = findViewById(R.id.item13_btn);
        mXqBtn = findViewById(R.id.item13_plsz);
        mList = findViewById(R.id.item13_listview);


    }

    private void  getAdapter(){
        cursor = db.query("trafficc_tb",null,null,null,null,null,s);

        adapter = new MySimpleCursorAdapter(
                this,R.layout.item5, cursor,
                new String[]{"roadname","hong","huang","lv"},//表头
                new int[]{R.id.lukou_item,R.id.hong_item,R.id.huang_item,R.id.lv_item},
                mXqBtn//表头结果所对应的输出对象
               );

        adapter.notifyDataSetChanged();
        mList.setAdapter(adapter);
    }

    private void getNet() {

        //todo get请求
        mOkhttp.newCall(new Request.Builder().url(UrlClass.LIGHT_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                lightDatalist = gson.fromJson(jsonData, new TypeToken<List<LightData>>() {}.getType());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        db.delete("trafficc_tb",null,null);
                        for (int i = 0; i < lightDatalist.size(); i++) {
                            insertData(lightDatalist.get(i).getRoadName(),
                                    lightDatalist.get(i).getRedTime(),
                                    lightDatalist.get(i).getYellowTime(),
                                    lightDatalist.get(i).getGreenTime());
                        }

                    }
                });
            }
        });

    }

    private void insertData(String id,int hong,int huang,int lv) {
        ContentValues values = new ContentValues();
        //insert into 表名 (列1，列2) values（值1，值2）
        values.put("roadname",id);
        values.put("hong",hong);
        values.put("huang",huang);
        values.put("lv",lv);
        db.insert("trafficc_tb",null,values);
        getAdapter();
    }


    @Override
    public void onSetting(int i) {
    }
}
