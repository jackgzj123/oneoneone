package com.lenovo.smarttraffic.ui.activity.item32activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.lenovo.smarttraffic.R;

public class Item32Activity extends AppCompatActivity {

    private EditText mCarid;
    private EditText mPaymoney;
    private EditText mRefresh;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item32);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item32Activity.this,Item32MsgActivity.class);
                intent.putExtra("carid",mCarid.getText().toString());
                intent.putExtra("paymoney",mPaymoney.getText().toString());
                intent.putExtra("refresh",mRefresh.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initview() {

        mCarid = findViewById(R.id.item32_caoid);
        mPaymoney = findViewById(R.id.item32_moneypay);
        mRefresh = findViewById(R.id.item32_refresh);
        mBtn = findViewById(R.id.item32_btn);
    }
}
