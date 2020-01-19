package com.lenovo.smarttraffic.ui.activity.item33activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class Item33_4Activity extends AppCompatActivity {

    private String date;
    private String name;
    private String phone;
    private String where;
    private String where1;

    private TextView mDate;
    private TextView mName;
    private TextView mPhone;
    private TextView mWhere;
    private Button mBtn;
    private TextView mWhere1;
    private ImageView mImg;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item33_4);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        date = getIntent().getStringExtra("date");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        where = getIntent().getStringExtra("where");
        where1 = getIntent().getStringExtra("where1");

        mDate = findViewById(R.id.item33_4_date);
        mName = findViewById(R.id.item33_4_name);
        mPhone = findViewById(R.id.item33_4_phone);
        mWhere = findViewById(R.id.item33_4_where);
        mWhere1 = findViewById(R.id.item33_4_where1);
        mBtn = findViewById(R.id.item33_4_btn);
        mImg = findViewById(R.id.lgb2_back);
        mTv = findViewById(R.id.item33_4_tv);

        mName.setText("乘客姓名："+name);
        mDate.setText("乘车日期："+date);
        mPhone.setText("手机号码："+phone);
        mWhere.setText("上车地点："+where);
        mWhere1.setText("下车地点："+where1);
        mTv.setText(getIntent().getStringExtra("wherewhere"));

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Item33_4Activity.this,Item33_5Activity.class);
                intent.putExtra("tag",mTv.getText().toString());
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}
