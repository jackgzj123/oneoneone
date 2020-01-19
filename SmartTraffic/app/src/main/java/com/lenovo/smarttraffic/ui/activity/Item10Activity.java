package com.lenovo.smarttraffic.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;

import java.util.Timer;
import java.util.TimerTask;

public class Item10Activity extends AppCompatActivity {

    private TextView mTime1Tv;
    private TextView mTime2Tv;
    private TextView mTime3Tv;
    private TextView mDShTv;
    private TextView mCarTv;
    private TextView mCar1Tv;
    private TextView mCar2Tv;
    private TextView mCar3Tv;
    private ImageView mCar1Img;
    private ImageView mCar2Img;
    private ImageView mCar3Img;
    private ImageView mRedImg;


    private int i = 3;
    private int m_year =2019;
    private int m_day = 30;
    private int m_month =11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item10);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();



    }


    private void initevent() {
        Time time = new Time();
        time.setToNow();
        mTime1Tv.setText("" + time.year);
        mTime2Tv.setText("" + (time.month+1));
        mTime3Tv.setText("" + time.monthDay);
        if (time.monthDay%2 == 1){
            mDShTv.setText("单");
            mCarTv.setText("1、3");
            mCar1Img.setImageResource(R.mipmap.takeon);
            mCar1Tv.setText("开");
            mCar3Img.setImageResource(R.mipmap.takeon);
            mCar3Tv.setText("开");
            mCar2Img.setImageResource(R.mipmap.takeoff);
            mCar2Tv.setText("关");
        }else {
            mDShTv.setText("双");
            mCarTv.setText("2");
            mCar2Img.setImageResource(R.mipmap.takeon);
            mCar2Tv.setText("开");
            mCar1Img.setImageResource(R.mipmap.takeoff);
            mCar1Tv.setText("关");
            mCar3Img.setImageResource(R.mipmap.takeoff);
            mCar3Tv.setText("关");
        }

        mTime1Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateListener=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        m_year = year;
                        m_month = monthOfYear+1;
                        m_day = dayOfMonth;
                    }

                };

                DatePickerDialog date=new DatePickerDialog(Item10Activity.this,
                        dateListener,m_year,m_month,m_day);
                date.setTitle("日期对话框");
                date.show();

                mTime1Tv.setText(""+m_year);
                mTime2Tv.setText(""+m_month);
                mTime3Tv.setText(""+m_day);
                if (m_day%2 == 1){
                    mDShTv.setText("单");
                    mCarTv.setText("1、3");
                    mCar1Img.setImageResource(R.mipmap.takeon);
                    mCar1Tv.setText("开");
                    mCar3Img.setImageResource(R.mipmap.takeon);
                    mCar3Tv.setText("开");
                    mCar2Img.setImageResource(R.mipmap.takeoff);
                    mCar2Tv.setText("关");
                }else {
                    mDShTv.setText("双");
                    mCarTv.setText("2");
                    mCar2Img.setImageResource(R.mipmap.takeon);
                    mCar2Tv.setText("开");
                    mCar1Img.setImageResource(R.mipmap.takeoff);
                    mCar1Tv.setText("关");
                    mCar3Img.setImageResource(R.mipmap.takeoff);
                    mCar3Tv.setText("关");
                }


            }
        });


        mRedImg.setBackgroundResource(R.drawable.traffic_animate);
        AnimationDrawable animaition = (AnimationDrawable)mRedImg.getBackground();
        animaition.setOneShot(false);   //设置是否只播放一次，和上面xml配置效果一致
        animaition.start();             //启动动画

    }

    private void initview() {
        mTime1Tv = findViewById(R.id.item10_time1);
        mTime2Tv = findViewById(R.id.item10_time2);
        mTime3Tv = findViewById(R.id.item10_time3);
        mDShTv = findViewById(R.id.item10_danshuang);
        mCarTv = findViewById(R.id.item10_car);
        mCar1Tv = findViewById(R.id.item10_car1);
        mCar2Tv = findViewById(R.id.item10_car2);
        mCar3Tv = findViewById(R.id.item10_car3);
        mCar1Img = findViewById(R.id.item10_car1img);
        mCar2Img = findViewById(R.id.item10_car2img);
        mCar3Img = findViewById(R.id.item10_car3img);
        mRedImg = findViewById(R.id.item10_red);
    }
}
