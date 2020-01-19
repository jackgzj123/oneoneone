package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.EnvironmentDao;
import com.lenovo.smarttraffic.database.data.EnvironmentData;
import com.lenovo.smarttraffic.database.data.EnvirtData;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item18Activity extends AppCompatActivity {

    private TextView mSun;
    private TextView mSunMsg;
    private TextView mGanmao;
    private TextView mGanmaoMsg;
    private TextView mChuanyi;
    private TextView mChuanyiMsg;
    private TextView mCo2;
    private TextView mCo2Msg;
    private TextView mPm2;
    private TextView mPm2Msg;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private LinearLayout mSunlin;
    private LinearLayout mGanlin;
    private LinearLayout mChuanlin;
    private LinearLayout mCo2lin;
    private LinearLayout mPm2lin;
    private EnvironmentDao environmentDao;

    private int bWen = 20;
    private int bSun =2000;
    private int bCo2 = 1700;
    private int bPm2 = 500;
    private String mpm2;
    private String mshi;
    private String mwen;
    private int mdao;
    private String msun;
    private String mco2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item18);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                getNet();
                setdata();
            }
        },0,100);

        environmentDao = new EnvironmentDao(this);
        environmentDao.deleteCarMessage();

        initview();

        initevent();
    }

    private void initevent() {
        mSunlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item18Activity.this,SensorActivity.class);
                intent.putExtra("co2",0);
                startActivity(intent);

            }
        });
        mChuanlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item18Activity.this,SensorActivity.class);
                intent.putExtra("co2",2);
                startActivity(intent);

            }
        });
        mGanlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item18Activity.this,SensorActivity.class);
                intent.putExtra("co2",2);
                startActivity(intent);

            }
        });
        mCo2lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item18Activity.this,SensorActivity.class);
                intent.putExtra("co2",4);
                startActivity(intent);

            }
        });
        mPm2lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item18Activity.this,SensorActivity.class);
                intent.putExtra("co2",5);
                startActivity(intent);

            }
        });
    }

    private void setdata() {
        EnvirtData envirtData = new EnvirtData();
        envirtData.setmCo2(mco2);
        envirtData.setmPm2(mpm2);
        envirtData.setmShi(mshi);
        envirtData.setmSun(msun);
        envirtData.setmWen(mwen);
        envirtData.setmDao(mdao);
        Time time = new Time();
        time.setToNow();
        String time_show_string = "" +time.minute + ":" + time.second;
        environmentDao.addCarMessage(envirtData,time_show_string);
    }

    private void initview() {
        mSun = findViewById(R.id.item16_sun);
        mSunMsg = findViewById(R.id.item16_sunmsg);
        mGanmao = findViewById(R.id.item16_ganmao);
        mGanmaoMsg = findViewById(R.id.item16_ganmaomsg);
        mChuanyi = findViewById(R.id.item16_chuanyi);
        mChuanyiMsg = findViewById(R.id.item16_chuanyimsg);
        mCo2 = findViewById(R.id.item16_co2);
        mCo2Msg = findViewById(R.id.item16_co2msg);
        mPm2 = findViewById(R.id.item16_pm2);
        mPm2Msg = findViewById(R.id.item16_pm2msg);

        mSunlin = findViewById(R.id.item18_sunlin);
        mGanlin = findViewById(R.id.item18_ganmaolin);
        mChuanlin = findViewById(R.id.item18_chuanyilin);
        mCo2lin = findViewById(R.id.item18_co2lin);
        mPm2lin = findViewById(R.id.item18_pm2lin);

    }

    // 普通事件的处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBusCarrier carrier) {
        int co2 =  carrier.getbCo2();
        int dao =  carrier.getbDao();
        int wen =  carrier.getbWen();
        int sun =  carrier.getbSun();
        int shi =  carrier.getbShi();
        int pm2 =  carrier.getbPm2();
        int jj =  carrier.getJ();

        bSun = sun;
        bCo2 =co2;
        bWen = wen;
        bPm2 = pm2;

        Log.e("wen", "handleEvent: "+bWen );

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
                mpm2 = pm;
                final String shi = environmentData.getHumidity();
                mshi = shi;
                final String wen = environmentData.getTemperature();
                mwen = wen;
                int dao = environmentData.getRoad().getStatus();
                mdao = dao;
                final String sun = environmentData.getLightIntensity();
                msun = sun;
                final String co2 = environmentData.getCo2();
                mco2 = co2;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (Integer.valueOf(sun)>3000){
                            mSun.setText("强("+sun+")");
                            mSunMsg.setText("尽量减少外出，需要涂抹高倍数防晒霜");
                        }else if (Integer.valueOf(sun)<1000){
                            mSun.setText("弱("+sun+")");
                            mSunMsg.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                        }else {
                            mSun.setText("中等("+sun+")");
                            mSunMsg.setText("涂擦SPF大于15、PA+防晒护肤品");
                        }

                        if (Integer.valueOf(wen)<8){
                            mGanmao.setText("较易发("+wen+")");
                            mGanmaoMsg.setText("温度低，风较大，较易发生感冒，注意防护");
                        }else {
                            mGanmao.setText("少发("+wen+")");
                            mGanmaoMsg.setText("无明显降温，感冒机率较低");
                        }

                        if (Integer.valueOf(wen)>21){
                            mChuanyi.setText("热("+wen+")");
                            mChuanyiMsg.setText("适合穿T恤、短薄外套等夏季服装");
                        }else if (Integer.valueOf(wen)<12){
                            mChuanyi.setText("冷("+wen+")");
                            mChuanyiMsg.setText("建议穿长袖衬衫、单裤等服装");
                        }else {
                            mChuanyi.setText("舒适("+wen+")");
                            mChuanyiMsg.setText("建议穿短袖衬衫、单裤等服装");
                        }

                        if (Integer.valueOf(co2)>6000){
                            mCo2.setText("较不宜("+co2+")");
                            mCo2Msg.setText("空气氧气含量低，请在室内进行休闲运动");
                        }else if (Integer.valueOf(co2)<3000){
                            mCo2.setText("适宜("+co2+")");
                            mCo2Msg.setText("气候适宜，推荐您进行户外运动");
                        }else {
                            mCo2.setText("中("+co2+")");
                            mCo2Msg.setText("易感人群应适当减少室外活动");
                        }

                        if (Integer.valueOf(pm)>100){
                            mPm2.setText("污染("+pm+")");
                            mPm2Msg.setText("空气质量差，不适合户外活动");
                        }else if (Integer.valueOf(pm)<30){
                            mPm2.setText("优("+pm+")");
                            mPm2Msg.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                        }else {
                            mPm2.setText("良("+pm+")");
                            mPm2Msg.setText("易感人群应适当减少室外活动");
                        }


                        if (Integer.parseInt(co2)>bCo2){
                            Log.e("GZJJJJ", "bco2:  "+bCo2 );
                            mCo2lin.setBackgroundResource(R.drawable.environmstyle4);
                        }else {
                            mCo2lin.setBackgroundResource(R.drawable.environmstyle3);
                        }
                        if (Integer.parseInt(pm)>bPm2){
                            mPm2lin.setBackgroundResource(R.drawable.environmstyle4);
                        }else {
                            mPm2lin.setBackgroundResource(R.drawable.environmstyle3);
                        }
                        if (Integer.parseInt(sun)>bSun){
                            mSunlin.setBackgroundResource(R.drawable.environmstyle4);
                        }else {
                            mSunlin.setBackgroundResource(R.drawable.environmstyle3);
                        }
                        if (Integer.parseInt(wen)>bWen){
                            mGanlin.setBackgroundResource(R.drawable.environmstyle4);
                            mChuanlin.setBackgroundResource(R.drawable.environmstyle4);
                        }else {
                            mGanlin.setBackgroundResource(R.drawable.environmstyle3);
                            mChuanlin.setBackgroundResource(R.drawable.environmstyle3);
                        }

                    }
                });
            }
        });
    }
}
