package com.lenovo.smarttraffic.ui.activity.item33activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;

public class Item33_3Activity extends AppCompatActivity {

    private String date;
    private EditText mName;
    private EditText mPhone;
    private Spinner mSpinner;
    private Spinner mSpinner1;
    private String s;
    private String s1;
    private Button mBtn;
    private TextView mTv;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item33_3);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        date = getIntent().getStringExtra("date");

        mTv = findViewById(R.id.item33_3_tv);

        mTv.setText(getIntent().getStringExtra("wherewhere"));

        initview();

        initevent();


    }

    private void initevent() {

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final ArrayList<String> arrayList = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
        //添加要显示的内容
        arrayList.add("光谷金融街");
        arrayList.add("戎军路南");
        arrayList.add("南湖商厦");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);//创建适配器  this--上下文  android.R.layout.simple_spinner_item--显示的模板   arrayList--显示的内容
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置下拉之后的布局的样式 这里采用的是系统的一个布局
        mSpinner.setAdapter(arrayAdapter);//将适配器给下拉框
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//当改变下拉框的时候会触发

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//改变内容的时候
                s=arrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

            }
        });

        final ArrayList<String> arrayList1 = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
        //添加要显示的内容
        arrayList1.add("光谷金融街");
        arrayList1.add("戎军路南");
        arrayList1.add("南湖商厦");

        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);//创建适配器  this--上下文  android.R.layout.simple_spinner_item--显示的模板   arrayList--显示的内容
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置下拉之后的布局的样式 这里采用的是系统的一个布局
        mSpinner1.setAdapter(arrayAdapter1);//将适配器给下拉框
        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//当改变下拉框的时候会触发

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//改变内容的时候
                s1=arrayList1.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

            }
        });


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (s.equals(s1)){
                    Toast.makeText(Item33_3Activity.this,"上车地点不能与下车地点相同",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Item33_3Activity.this,Item33_4Activity.class);
                    intent.putExtra("date",date);
                    intent.putExtra("name",mName.getText().toString());
                    intent.putExtra("phone",mPhone.getText().toString());
                    intent.putExtra("where",s);
                    intent.putExtra("wherewhere",getIntent().getStringExtra("wherewhere"));
                    intent.putExtra("where1",s1);
                    startActivity(intent);
                }

            }
        });
    }

    private void initview() {
        mName = findViewById(R.id.item33_3_name);
        mPhone = findViewById(R.id.item33_3_phone);
        mSpinner = findViewById(R.id.item33_3_spinner);
        mBtn = findViewById(R.id.item33_3_btn);
        mSpinner1 = findViewById(R.id.item33_3_spinner1);
        mImg = findViewById(R.id.lgb2_back);

    }
}
