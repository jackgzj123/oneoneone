package com.lenovo.smarttraffic.ui.activity;

import android.database.Cursor;
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
import com.lenovo.smarttraffic.database.dao.CarDao;

import java.util.ArrayList;
import java.util.List;

public class Item5Activity extends AppCompatActivity {


    private Spinner mSpinner;
    private Button mSelectBtn;
    private ListView mList;
    private String s;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private CarDao carDao;
    private ImageView mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item5);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

    }

    private void initevent() {

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();//后退
            }
        });

        //todo 选择排序顺序
        final ArrayList<String> arrayList = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
        //添加要显示的内容
        arrayList.add("时间降序");
        arrayList.add("时间升序");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);//创建适配器  this--上下文  android.R.layout.simple_spinner_item--显示的模板   arrayList--显示的内容
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置下拉之后的布局的样式 这里采用的是系统的一个布局
        mSpinner.setAdapter(arrayAdapter);//将适配器给下拉框
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//当改变下拉框的时候会触发
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//改变内容的时候

                carDao = new CarDao(Item5Activity.this);
                switch (position){
                    case 1:
                        s = "time asc";
                        cursor = carDao.selectCarMessage("order by time asc");
                        getAdapter();
                        break;
                    case 0:
                        s = "time desc";
                        cursor = carDao.selectCarMessage("order by time desc");
                        getAdapter();
                        mList.setAdapter(adapter);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

            }
        });


        //todo 查询操作
        mSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mList.setAdapter(adapter);
            }
        });

    }

    private void initview() {
        mSpinner = findViewById(R.id.item5_spinner);
        mSelectBtn = findViewById(R.id.item5_searchbtn);
        mList = findViewById(R.id.item5_list);
        mBack = findViewById(R.id.lgb2_back);
    }

    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                this,R.layout.item3, cursor,
                new String[]{"_id","name","chong","operator","time"},//表头
                new int[]{R.id.xuhao_item,R.id.name_item,R.id.chong_item,R.id.people_item,R.id.time_item},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }
}
