package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.broadcastreceiver.BroadcastReceiverActivity;

import org.greenrobot.eventbus.EventBus;

public class Item9Activity extends AppCompatActivity {


    public static final String J = "j";
    private TextView mFaTv;
    private ImageView mTakeImg;
    private EditText mCo2Ed;
    private EditText mPm2Ed;
    private EditText mDaoEd;
    private EditText mWenEd;
    private EditText mSunEd;
    private EditText mShiEd;
    private Button mSaveBtn;

    private int i = 1;
    public static final String AAAAA = "com.example.broadcastreceiver.Mybroad";
    public static final String CO2 = "co2";
    public static final String PM_2 = "pm2";
    public static final String DAO ="dao";
    public static final String SHI ="shi";
    public static final String WEN ="wen";
    public static final String SUN ="sun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item9);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        mTakeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 1){
                    mTakeImg.setImageResource(R.mipmap.takeoff);
                    mFaTv.setText("关");
                    mCo2Ed.setEnabled(false);
                    mPm2Ed.setEnabled(false);
                    mDaoEd.setEnabled(false);
                    mWenEd.setEnabled(false);
                    mSunEd.setEnabled(false);
                    mShiEd.setEnabled(false);
                    i=0;
                }else {
                    mTakeImg.setImageResource(R.mipmap.takeon);
                    mFaTv.setText("开");
                    mCo2Ed.setEnabled(true);
                    mPm2Ed.setEnabled(true);
                    mDaoEd.setEnabled(true);
                    mWenEd.setEnabled(true);
                    mSunEd.setEnabled(true);
                    mShiEd.setEnabled(true);
                    i=1;
                }
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i ==1 ){
//                    Intent intent = new Intent(AAAAA);
//                    intent.putExtra(CO2,mCo2Ed.getText().toString());
//                    intent.putExtra(PM_2,mPm2Ed.getText().toString());
//                    intent.putExtra(DAO,mDaoEd.getText().toString());
//                    intent.putExtra(SHI,mShiEd.getText().toString());
//                    intent.putExtra(WEN,mWenEd.getText().toString());
//                    intent.putExtra(SUN,mSunEd.getText().toString());
//                    intent.putExtra(J,1);

//                    sendBroadcast(intent);

//

                    EventBusCarrier eventBusCarrier = new EventBusCarrier();
                    eventBusCarrier.setEventType("1");
                    eventBusCarrier.setbCo2(Integer.parseInt(mCo2Ed.getText().toString()));
                    eventBusCarrier.setbPm2(Integer.parseInt(mPm2Ed.getText().toString()));
                    eventBusCarrier.setbDao(Integer.parseInt(mDaoEd.getText().toString()));
                    eventBusCarrier.setbShi(Integer.parseInt(mShiEd.getText().toString()));
                    eventBusCarrier.setbSun(Integer.parseInt(mSunEd.getText().toString()));
                    eventBusCarrier.setbWen(Integer.parseInt(mWenEd.getText().toString()));
                    eventBusCarrier.setJ(1);
                    EventBus.getDefault().post(eventBusCarrier); //普通事件发布
                    Toast.makeText(Item9Activity.this,"保存成功",Toast.LENGTH_SHORT).show();

                }else {
                   //Intent intent1 = new Intent(AAAAA);
                    //Intent intent = new Intent(Item9Activity.this,Item8Activity.class);
                    //intent1.putExtra(J,0);
                    //startActivity(intent);
                    EventBusCarrier eventBusCarrier = new EventBusCarrier();
                    eventBusCarrier.setEventType("1");
                    eventBusCarrier.setJ(0);
                    EventBus.getDefault().post(eventBusCarrier);
                    Toast.makeText(Item9Activity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    //sendBroadcast(intent1);

                }

            }
        });
    }

    private void initview() {
        mFaTv = findViewById(R.id.item9_baojingtv);
        mTakeImg = findViewById(R.id.item9_takeon);
        mCo2Ed = findViewById(R.id.item9_co2);
        mPm2Ed = findViewById(R.id.item9_pm2);
        mDaoEd = findViewById(R.id.item9_dao);
        mWenEd = findViewById(R.id.item9_wen);
        mSunEd = findViewById(R.id.item9_sun);
        mShiEd = findViewById(R.id.item9_shi);
        mSaveBtn = findViewById(R.id.item9_save);

    }
}
