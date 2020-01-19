package com.lenovo.smarttraffic.ui.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
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
import com.lenovo.smarttraffic.database.dao.FazhiBaoDao;
import com.lenovo.smarttraffic.database.data.EnvironmentData;
import com.lenovo.smarttraffic.database.data.EnvirtData;
import com.lenovo.smarttraffic.ui.broadcastreceiver.BroadcastReceiverActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Item8Activity extends AppCompatActivity {

    private TextView mWenText;
    private TextView mShiText;
    private TextView mSunText;
    private TextView mCo2Text;
    private TextView mPm2Text;
    private TextView mDaoText;
    private EnvironmentDao environmentDao;
    private FazhiBaoDao fazhiBaoDao;
    private BroadcastReceiverActivity mbroadcastReceiver;
    private SensorActivity sensorActivity;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;

    private int i = 0;
    public static final String AAAAA = "com.example.broadcastreceiver.Mybroad";
    private String mWen;
    private String mShi;
    private String mSun;
    private String mCo2;
    private String mPm2;
    private int mDao;

    private int bWen = 20;
    private int bShi = 200;
    private int bSun =2000;
    private int bCo2 = 1700;
    private int bPm2 = 500;
    private int bDao = 2;
    private LinearLayout mWenlin;
    private LinearLayout mShilin;
    private LinearLayout mSunlin;
    private LinearLayout mCo2lin;
    private LinearLayout mPm2lin;
    private LinearLayout mDaolin;
    private int j = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item8);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        environmentDao = new EnvironmentDao(this);
        environmentDao.deleteCarMessage();
        fazhiBaoDao = new FazhiBaoDao(this);

        EventBus.getDefault().register(this);  //事件的注册




        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                getNet();
                i+=1;
                if (i<21){
                    setdata();
                }

            }

        },0,3000);


        Timer timer1 = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(j>0){
                    if (Integer.parseInt(mCo2)>bCo2){

                        fazhiBaoDao.addCarMessage("CO2",bCo2,Integer.parseInt(mCo2));
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(Item8Activity.this, "chat")
                                .setAutoCancel(true)
                                .setContentTitle("收到警告消息")
                                .setContentText("CO2报警，阈值"+bCo2+"，当前值"+mCo2)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                //在build()方法之前还可以添加其他方法
                                .build();
                        manager.notify(1, notification);
                    }
                    if (Integer.parseInt(mPm2)>bPm2){
                        fazhiBaoDao.addCarMessage("PM2.5",bPm2,Integer.parseInt(mPm2));
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(Item8Activity.this, "chat")
                                .setAutoCancel(true)
                                .setContentTitle("收到警告消息")
                                .setContentText("PM2报警，阈值"+bPm2+"，当前值"+mPm2)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                //在build()方法之前还可以添加其他方法
                                .build();
                        manager.notify(2, notification);
                    }
                    if (Integer.parseInt(mShi)>bShi){
                        fazhiBaoDao.addCarMessage("湿度",bShi,Integer.parseInt(mShi));
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(Item8Activity.this, "chat")
                                .setAutoCancel(true)
                                .setContentTitle("收到警告消息")
                                .setContentText("湿度报警，阈值"+bShi+"，当前值"+mShi)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                //在build()方法之前还可以添加其他方法
                                .build();
                        manager.notify(3, notification);
                    }
                    if (Integer.parseInt(mSun)>bSun){
                        fazhiBaoDao.addCarMessage("光照",bSun,Integer.parseInt(mSun));
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(Item8Activity.this, "chat")
                                .setAutoCancel(true)
                                .setContentTitle("收到警告消息")
                                .setContentText("光照报警，阈值"+bSun+"，当前值"+mSun)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                //在build()方法之前还可以添加其他方法
                                .build();
                        manager.notify(4, notification);
                    }
                    if (mDao>bDao){
                        fazhiBaoDao.addCarMessage("道路",bDao,mDao);
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(Item8Activity.this, "chat")
                                .setAutoCancel(true)
                                .setContentTitle("收到警告消息")
                                .setContentText("道路状态报警，阈值"+bDao+"，当前值"+mDao)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                //在build()方法之前还可以添加其他方法
                                .build();
                        manager.notify(5, notification);
                    }
                    if (Integer.parseInt(mWen)>bWen){
                        fazhiBaoDao.addCarMessage("温度",bWen,Integer.parseInt(mWen));
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(Item8Activity.this, "chat")
                                .setAutoCancel(true)
                                .setContentTitle("收到警告消息")
                                .setContentText("温度报警，阈值"+bWen+"，当前值"+mWen)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                //在build()方法之前还可以添加其他方法
                                .build();
                        manager.notify(6, notification);
                    }
                }


            }

        },5000,10000);
        initevent();

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
        bShi =shi;
        bCo2 =co2;
        bDao =dao;
        bWen = wen;
        j = jj;
        bPm2 = pm2;

        Log.e("dao", "handleEvent: "+bDao );

    }


    private void setdata() {
        EnvirtData envirtData = new EnvirtData();
        envirtData.setmCo2(mCo2);
        envirtData.setmPm2(mPm2);
        envirtData.setmShi(mShi);
        envirtData.setmSun(mSun);
        envirtData.setmWen(mWen);
        envirtData.setmDao(mDao);
        Time time = new Time();
        time.setToNow();
        String time_show_string = "" +time.minute + ":" + time.second;
        environmentDao.addCarMessage(envirtData,time_show_string);
    }

    private void initevent() {
        mCo2Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item8Activity.this,SensorActivity.class);
                intent.putExtra("co2",4);
                startActivity(intent);

            }
        });
        mPm2Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item8Activity.this,SensorActivity.class);
                intent.putExtra("co2",5);
                startActivity(intent);

            }
        });
        mDaoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item8Activity.this,SensorActivity.class);
                intent.putExtra("co2",3);
                startActivity(intent);

            }
        });
        mShiText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item8Activity.this,SensorActivity.class);
                intent.putExtra("co2",1);
                startActivity(intent);

            }
        });
        mSunText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item8Activity.this,SensorActivity.class);
                intent.putExtra("co2",0);
                startActivity(intent);

            }
        });
        mWenText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item8Activity.this,SensorActivity.class);
                intent.putExtra("co2",2);
                startActivity(intent);

            }
        });
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
                final String co2 = environmentData.getCo2();
                mCo2 = co2;
                final String pm = environmentData.getPm();
                mPm2 = pm;
                final String shi = environmentData.getHumidity();
                mShi =shi;
                final String sun = environmentData.getLightIntensity();
                Log.e("GZJJJJJJJ", "onResponse: "+sun);
                mSun = sun;
                final String wen = environmentData.getTemperature();
                mWen = wen;
                final int dao = environmentData.getRoad().getStatus();
                mDao =dao;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCo2Text.setText(co2);
                        mPm2Text.setText(pm);
                        mShiText.setText(shi);
                        mSunText.setText(sun);
                        mWenText.setText(wen);
                        mDaoText.setText(""+dao);
                        //todo
                        if (j == 1){
                            if (Integer.parseInt(mCo2)>bCo2){
                                Log.e("GZJJJJ", "bco2:  "+bCo2 );
                                mCo2lin.setBackgroundResource(R.drawable.environmstyle1);
                            }else {
                                mCo2lin.setBackgroundResource(R.drawable.environmstyle);
                            }
                            if (Integer.parseInt(mPm2)>bPm2){
                                mPm2lin.setBackgroundResource(R.drawable.environmstyle1);
                            }else {
                                mPm2lin.setBackgroundResource(R.drawable.environmstyle);
                            }
                            if (Integer.parseInt(mShi)>bShi){
                                mShilin.setBackgroundResource(R.drawable.environmstyle1);
                            }else {
                                mShilin.setBackgroundResource(R.drawable.environmstyle);
                            }
                            if (Integer.parseInt(mSun)>bSun){
                                mSunlin.setBackgroundResource(R.drawable.environmstyle1);
                            }else {
                                mSunlin.setBackgroundResource(R.drawable.environmstyle);
                            }
                            if (Integer.parseInt(mWen)>bWen){
                                mWenlin.setBackgroundResource(R.drawable.environmstyle1);
                            }else {
                                mWenlin.setBackgroundResource(R.drawable.environmstyle);
                            }
                            if (mDao>bDao){
                                mDaolin.setBackgroundResource(R.drawable.environmstyle1);
                            }else {
                                mDaolin.setBackgroundResource(R.drawable.environmstyle);
                            }
                        }

                    }
                });
            }
        });

    }

    private void initview() {
        mWenText = findViewById(R.id.item8_wen);
        mShiText = findViewById(R.id.item8_shi);
        mSunText = findViewById(R.id.item8_sun);
        mCo2Text = findViewById(R.id.item8_co2);
        mPm2Text = findViewById(R.id.item8_pm2);
        mDaoText = findViewById(R.id.item8_daolu);
        mWenlin = findViewById(R.id.item8_wenlin);
        mShilin = findViewById(R.id.item8_shilin);
        mSunlin = findViewById(R.id.item8_sunlin);
        mCo2lin = findViewById(R.id.item8_co2lin);
        mPm2lin = findViewById(R.id.item8_pm2lin);
        mDaolin = findViewById(R.id.item8_daolin);

//        //新建广播接收器
//        mbroadcastReceiver = new BroadcastReceiverActivity(bWen,bShi,bSun,bCo2,bPm2,bDao,j);
//
//        IntentFilter intentFilter1 = new IntentFilter();
//        intentFilter1.addAction(AAAAA);
//        registerReceiver(mbroadcastReceiver,intentFilter1);




    }

    private void getdata() {
        if (Integer.parseInt(getIntent().getStringExtra("co2"))!=0){
            bCo2 = Integer.parseInt(getIntent().getStringExtra("co2"));
        }
        if (Integer.parseInt(getIntent().getStringExtra("pm2"))!=0){
            bPm2 = Integer.parseInt(getIntent().getStringExtra("pm2"));
        }
        if (Integer.parseInt(getIntent().getStringExtra("wen"))!=0){
            bWen = Integer.parseInt(getIntent().getStringExtra("wen"));
        }
        if (Integer.parseInt(getIntent().getStringExtra("dao"))!=0){
            bDao = Integer.parseInt(getIntent().getStringExtra("dao"));
        }
        if (Integer.parseInt(getIntent().getStringExtra("shi"))!=0){
            bShi = Integer.parseInt(getIntent().getStringExtra("shi"));
        }
        if (Integer.parseInt(getIntent().getStringExtra("sun"))!=0){
            bSun = Integer.parseInt(getIntent().getStringExtra("sun"));
        }
        if (getIntent().getIntExtra("j",1)!=0){
            j = getIntent().getIntExtra("j",1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mbroadcastReceiver != null){
            //销毁广播接收器
            unregisterReceiver(mbroadcastReceiver);
        }
    }
}
