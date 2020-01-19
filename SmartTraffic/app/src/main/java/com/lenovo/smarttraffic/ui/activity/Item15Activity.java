package com.lenovo.smarttraffic.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item15Activity extends AppCompatActivity {

    private TextView mWen;
    private TextView mShi;
    private TextView mPm;
    private TextView mDate;
    private TextView mDay;
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mShuaxin;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;

    private String mYear;
    private String mMonth;
    private String mDay1;
    private String mWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item15);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay1 = String.valueOf(c.get(Calendar.DAY_OF_MONTH)+1);// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK)+1);
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }

        mDate.setText(mYear + "-" + mMonth + "-" + mDay1);
        mDay.setText("星期"+mWay);


        mShuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNet();
            }
        });

        mImg1.setBackgroundResource(R.drawable.jingchang1_animate);
        AnimationDrawable animaition = (AnimationDrawable)mImg1.getBackground();
        animaition.setOneShot(false);   //设置是否只播放一次，和上面xml配置效果一致
        animaition.start();             //启动动画

        mImg2.setBackgroundResource(R.drawable.jingchang2_animate);
        AnimationDrawable animaition1 = (AnimationDrawable)mImg2.getBackground();
        animaition1.setOneShot(false);   //设置是否只播放一次，和上面xml配置效果一致
        animaition1.start();             //启动动画

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
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPm.setText(pm+"ug/m3");
                        mWen.setText(wen+"°");
                        mShi.setText(shi);
                    }
                });
            }
        });

    }

    private void initview() {
        mWen = findViewById(R.id.item15_wen);
        mShi = findViewById(R.id.item15_shi);
        mPm = findViewById(R.id.item15_pm2);
        mDate = findViewById(R.id.item15_date);
        mDay = findViewById(R.id.item15_day);
        mImg1 = findViewById(R.id.item15_jiaojing1);
        mImg2 = findViewById(R.id.item15_jiaojing2);
        mShuaxin = findViewById(R.id.item15_shuaxin);
    }
}
