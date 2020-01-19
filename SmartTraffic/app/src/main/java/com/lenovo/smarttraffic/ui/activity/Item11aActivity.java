package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.CarMessageDao;
import com.lenovo.smarttraffic.database.data.CarData;
import com.lenovo.smarttraffic.database.data.Data;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item11aActivity extends AppCompatActivity implements MyDialog4.SettingListener {

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private TextView mChongzhi;

    private TextView mCaryue1;
    private ImageView mCarok1;
    private Button mCarchong1;

    private TextView mCaryue2;
    private ImageView mCarok2;
    private Button mCarchong2;

    private TextView mCaryue3;
    private ImageView mCarok3;
    private Button mCarchong3;

    private TextView mCaryue4;
    private ImageView mCarok4;
    private Button mCarchong4;

    private LinearLayout mLincar1;
    private LinearLayout mLincar2;
    private LinearLayout mLincar3;
    private LinearLayout mLincar4;

    private TextView mPiliang;
    private List<CarData> carDatalist = new ArrayList<>();
    private List<String> carnamelist = new ArrayList<>();
    private CarMessageDao carMessageDao;

    private int m =100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item11a);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        carMessageDao =new CarMessageDao(this);
        carMessageDao.deleteCarMessage();
        initview();

        initevent();
    }

    private void initevent() {

        //todo post请求
        getcar1();
        getcar2();
        getcar3();
        getcar4();

        mCarchong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add("1,");
                MyDialog4 dialog = new MyDialog4(Item11aActivity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11aActivity.this);
                dialog.show();
            }
        });
        mCarchong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add("2,");
                MyDialog4 dialog = new MyDialog4(Item11aActivity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11aActivity.this);
                dialog.show();
            }
        });
        mCarchong3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add("3,");
                MyDialog4 dialog = new MyDialog4(Item11aActivity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11aActivity.this);
                dialog.show();
            }
        });
        mCarchong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add("4,");
                MyDialog4 dialog = new MyDialog4(Item11aActivity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11aActivity.this);
                dialog.show();
            }
        });


        mPiliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog4 dialog = new MyDialog4(Item11aActivity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11aActivity.this);
                dialog.show();
            }
        });

        mCarok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add("1,");
                mCarok1.setImageResource(R.mipmap.my_car);
            }
        });
        mCarok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add("2,");
                mCarok2.setImageResource(R.mipmap.my_car);
            }
        });
        mCarok3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add("3,");
                mCarok3.setImageResource(R.mipmap.my_car);
            }
        });
        mCarok4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add("4,");
                mCarok4.setImageResource(R.mipmap.my_car);
            }
        });

        mChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item11aActivity.this,Item3Activity.class));
            }
        });
    }

    private void getcar4() {
        FormBody.Builder builder3 = new FormBody.Builder();
        builder3.add("carId","4");
        FormBody formBody3 = builder3.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody3).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCaryue4.setText(s1);
                    }
                });
            }
        });
    }

    private void getcar3() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","3");
        FormBody formBody2 = builder2.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCaryue3.setText(s1);
                    }
                });
            }
        });
    }

    private void getcar2() {
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("carId","2");
        FormBody formBody1 = builder1.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody1).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCaryue2.setText(s1);
                    }
                });
            }
        });
    }

    private void getcar1() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("carId","1");
        FormBody formBody = builder.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCaryue1.setText(s1);
                    }
                });
            }
        });
    }

    private void initview() {

        mChongzhi = findViewById(R.id.item11a_jilv);

        mCaryue1 = findViewById(R.id.item11a_car1yue);
        mCarok1 = findViewById(R.id.item11a_car1ok);
        mCarchong1 = findViewById(R.id.item11a_car1chong);


        mCaryue2 = findViewById(R.id.item11a_car2yue);
        mCarok2 = findViewById(R.id.item11a_car2ok);
        mCarchong2 = findViewById(R.id.item11a_car2chong);


        mCaryue3 = findViewById(R.id.item11a_car3yue);
        mCarok3 = findViewById(R.id.item11a_car3ok);
        mCarchong3 = findViewById(R.id.item11a_car3chong);


        mCaryue4 = findViewById(R.id.item11a_car4yue);
        mCarok4 = findViewById(R.id.item11a_car4ok);
        mCarchong4 = findViewById(R.id.item11a_car4chong);


        mLincar1 = findViewById(R.id.item11a_car1);
        mLincar2 = findViewById(R.id.item11a_car2);
        mLincar3 = findViewById(R.id.item11a_car3);
        mLincar4 = findViewById(R.id.item11a_car4);

        mPiliang = findViewById(R.id.item11a_piliang);

    }

    @Override
    public void onSetting(String name,int i) {

        if (i == 1){
            FormBody.Builder builder2 = new FormBody.Builder();
            builder2.add("carId","1");
            builder2.add("money",name);
            FormBody formBody2 = builder2.build();
            mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    getcar1();
                    Time time = new Time();
                    time.setToNow();
                    String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                    carMessageDao.addCarMessage("1",name, mCaryue1.getText().toString(),"葛张杰",time_show_string);
                }
            });


        }else if (i == 2){
            FormBody.Builder builder2 = new FormBody.Builder();
            builder2.add("carId","2");
            builder2.add("money",name);
            FormBody formBody2 = builder2.build();
            mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    getcar2();
                    Time time = new Time();
                    time.setToNow();
                    String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                    carMessageDao.addCarMessage("2",name, mCaryue2.getText().toString(),"葛张杰",time_show_string);
                }
            });


        }else if (i == 3){
            FormBody.Builder builder2 = new FormBody.Builder();
            builder2.add("carId","3");
            builder2.add("money",name);
            FormBody formBody2 = builder2.build();
            mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    getcar3();
                    Time time = new Time();
                    time.setToNow();
                    String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                    carMessageDao.addCarMessage("3",name, mCaryue3.getText().toString(),"葛张杰",time_show_string);
                }
            });

        }else if (i == 4){
            FormBody.Builder builder2 = new FormBody.Builder();
            builder2.add("carId","4");
            builder2.add("money",name);
            FormBody formBody2 = builder2.build();
            mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    getcar4();
                    Time time = new Time();
                    time.setToNow();
                    String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                    carMessageDao.addCarMessage("4",name, mCaryue4.getText().toString(),"葛张杰",time_show_string);
                }
            });

        }

    }
}
