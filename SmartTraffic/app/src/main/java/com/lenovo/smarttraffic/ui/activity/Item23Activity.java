package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Item23Activity extends AppCompatActivity {

    private TextView mPm;
    private TextView mWen;
    private TextView mShi;
    private TextView mSuncontext;
    private TextView mSunmsg;
    private TextView mPmcontext;
    private TextView mPmmsg;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item23);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

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
        mPm = findViewById(R.id.item23_pm);
        mWen = findViewById(R.id.item23_wen);
        mShi = findViewById(R.id.item23_shi);

        mSuncontext = findViewById(R.id.item23_suncontext);
        mSunmsg = findViewById(R.id.item23_sunmsg);
        mPmcontext = findViewById(R.id.item23_pmcontext);
        mPmmsg = findViewById(R.id.item23_pmmsg);
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
                final String sun = environmentData.getLightIntensity();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPm.setText("PM2.5:"+pm);
                        mShi.setText("湿   度："+shi);
                        mWen.setText("温   度："+wen);

                        if (Integer.valueOf(sun)<1000){
                            mSuncontext.setText("非常弱");
                            mSuncontext.setTextColor(Color.parseColor("#000000"));
                            mSunmsg.setText("您无须担心紫外线");
                        }else if (Integer.valueOf(sun)>3000){
                            mSuncontext.setText("强");
                            mSuncontext.setTextColor(Color.rgb(255,0,0));
                            mSunmsg.setText("外出需要涂抹中倍数防晒霜");
                        }else {
                            mSuncontext.setText("弱");
                            mSuncontext.setTextColor(Color.parseColor("#000000"));
                            mSunmsg.setText("外出适当涂抹低倍数防晒霜");
                        }

                        if (Integer.valueOf(pm)>300){
                            mPmcontext.setText("爆表");
                            mPmcontext.setTextColor(Color.rgb(255,0,0));
                            mPmmsg.setText("停止一切外出活动");
                        }else if (Integer.valueOf(pm)<100){
                            mPmcontext.setText("良好");
                            mPmcontext.setTextColor(Color.parseColor("#000000"));
                            mPmmsg.setText("气象条件会对晨练影响不大");
                        }else if (Integer.valueOf(pm)<200){
                            mPmcontext.setText("轻度");
                            mPmcontext.setTextColor(Color.rgb(255,0,0));
                            mPmmsg.setText("受天气影响，较不宜晨练");
                        }else {
                            mPmcontext.setText("重度");
                            mPmcontext.setTextColor(Color.rgb(255,0,0));
                            mPmmsg.setText("减少外出，出行注意戴口罩");
                        }

                    }
                });
            }
        });

    }
}
