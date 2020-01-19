package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item22Activity extends AppCompatActivity {

    private TextView mPm;
    private TextView mWen;
    private TextView mShi;
    private TextView mOne;
    private TextView mSecond;
    private TextView mThree;
    private View mOneV;
    private View mSecondV;
    private View mThreeV;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item22);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }


    private void initevent() {

        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                getNet();
            }
        },0,3000);

    }

    private void initview() {
        mPm = findViewById(R.id.item22_pm);
        mWen = findViewById(R.id.item22_wen);
        mShi = findViewById(R.id.item22_shi);

        mOne = findViewById(R.id.item22_one);
        mSecond = findViewById(R.id.item22_second);
        mThree = findViewById(R.id.item22_three);

        mOneV = findViewById(R.id.item22_onecolor);
        mSecondV = findViewById(R.id.item22_secondcolor);
        mThreeV = findViewById(R.id.item22_threecolor);

    }

    private void getNet() {

        //todo get请求
        mOkhttp.newCall(new Request.Builder().url(UrlClass.ENVIRONMENT_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                EnvironmentData environmentData = gson.fromJson(jsonData, EnvironmentData.class);
                final String pm = environmentData.getPm();
                final String shi = environmentData.getHumidity();
                final String wen = environmentData.getTemperature();
                final int dao = environmentData.getRoad().getStatus();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPm.setText("PM2.5:"+pm);
                        mShi.setText("湿   度："+shi);
                        mWen.setText("温   度："+wen);
                        if (dao >= 5){
                            mOne.setText("1号道路：爆表");
                            mOneV.setBackgroundColor(Color.parseColor("#4c060e"));
                            mSecond.setText("2号道路：拥挤");
                            mSecondV.setBackgroundColor(Color.parseColor("#ffff01"));
                            mThree.setText("3号道路：通畅");
                            mThreeV.setBackgroundColor(Color.parseColor("#0ebd12"));
                        }else if (dao == 4){
                            mOne.setText("1号道路：堵塞");
                            mOneV.setBackgroundColor(Color.parseColor("#ff0103"));
                            mSecond.setText("2号道路：较通畅");
                            mSecondV.setBackgroundColor(Color.parseColor("#98ed1f"));
                            mThree.setText("3号道路：爆表");
                            mThreeV.setBackgroundColor(Color.parseColor("#4c060e"));
                        }else if (dao == 3){
                            mOne.setText("1号道路：拥挤");
                            mOneV.setBackgroundColor(Color.parseColor("#ffff01"));
                            mSecond.setText("2号道路：堵塞");
                            mSecondV.setBackgroundColor(Color.parseColor("#ff0103"));
                            mThree.setText("3号道路：较通畅");
                            mThreeV.setBackgroundColor(Color.parseColor("#98ed1f"));
                        }else if (dao == 2){
                            mOne.setText("1号道路：较通畅");
                            mOneV.setBackgroundColor(Color.parseColor("#98ed1f"));
                            mSecond.setText("2号道路：通畅");
                            mSecondV.setBackgroundColor(Color.parseColor("#0ebd12"));
                            mThree.setText("3号道路：堵塞");
                            mThreeV.setBackgroundColor(Color.parseColor("#ff0103"));
                        }else if (dao == 1){
                            mOne.setText("1号道路：通畅");
                            mOneV.setBackgroundColor(Color.parseColor("#0ebd12"));
                            mSecond.setText("2号道路：爆表");
                            mSecondV.setBackgroundColor(Color.parseColor("#4c060e"));
                            mThree.setText("3号道路：拥挤");
                            mThreeV.setBackgroundColor(Color.parseColor("#ffff01"));
                        }

                    }
                });
            }
        });

    }
}
