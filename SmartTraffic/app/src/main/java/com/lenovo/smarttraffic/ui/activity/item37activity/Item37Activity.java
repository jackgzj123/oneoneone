package com.lenovo.smarttraffic.ui.activity.item37activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.Data;
import com.lenovo.smarttraffic.ui.activity.Item11aActivity;
import com.lenovo.smarttraffic.ui.activity.MyDialog4;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item37Activity extends AppCompatActivity implements MyDialog5.SettingListener{

    private TextView mCarYue1;
    private TextView mCarYue2;
    private TextView mCarYue3;
    private TextView mCarYue4;
    private TextView mCarYue5;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private String carname;

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item37);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        //todo 小车余额查询

        getcar1();
        getcar2();
        getcar3();
        getcar4();
        getcar5();


        initevent();

    }

    private void initevent() {
        //todo 点击按钮充值 并更新余额
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carname = "1";
                MyDialog5 dialog = new MyDialog5(Item37Activity.this,R.style.mydialog,carname);
                dialog.setOnSettingListener(Item37Activity.this);
                dialog.show();
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carname = "2";
                MyDialog5 dialog = new MyDialog5(Item37Activity.this,R.style.mydialog,carname);
                dialog.setOnSettingListener(Item37Activity.this);
                dialog.show();
            }
        });
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carname = "3";
                MyDialog5 dialog = new MyDialog5(Item37Activity.this,R.style.mydialog,carname);
                dialog.setOnSettingListener(Item37Activity.this);
                dialog.show();
            }
        });
        mBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carname = "4";
                MyDialog5 dialog = new MyDialog5(Item37Activity.this,R.style.mydialog,carname);
                dialog.setOnSettingListener(Item37Activity.this);
                dialog.show();
            }
        });
        mBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carname = "5";
                MyDialog5 dialog = new MyDialog5(Item37Activity.this,R.style.mydialog,carname);
                dialog.setOnSettingListener(Item37Activity.this);
                dialog.show();
            }
        });
    }

    private void getcar1() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","1");
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
                        mCarYue1.setText(s1);
                    }
                });
            }
        });
    }

    private void getcar2() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","2");
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
                        mCarYue2.setText(s1);
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
                        mCarYue3.setText(s1);
                    }
                });
            }
        });
    }

    private void getcar4() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","4");
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
                        mCarYue4.setText(s1);
                    }
                });
            }
        });
    }

    private void getcar5() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","5");
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
                        mCarYue5.setText(s1);
                    }
                });
            }
        });
    }

    private void setMoney(final String s,String str) {

        //todo post请求
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("carId",s);
        builder.add("money",str);
        FormBody formBody = builder.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        if (s.equals("1")){
                            getcar1();
                        }else if (s.equals("2")){
                            getcar2();
                        }else if (s.equals("3")){
                            getcar3();
                        }else if (s.equals("4")){
                            getcar4();
                        }else {
                            getcar5();
                        }

            }
        });
    }

    private void initview() {

        mCarYue1 = findViewById(R.id.item37_car1yue);
        mCarYue2 = findViewById(R.id.item37_car2yue);
        mCarYue3 = findViewById(R.id.item37_car3yue);
        mCarYue4 = findViewById(R.id.item37_car4yue);
        mCarYue5 = findViewById(R.id.item37_car5yue);

        mBtn1 = findViewById(R.id.item37_btn1);
        mBtn2 = findViewById(R.id.item37_btn2);
        mBtn3 = findViewById(R.id.item37_btn3);
        mBtn4 = findViewById(R.id.item37_btn4);
        mBtn5 = findViewById(R.id.item37_btn5);
    }

    @Override
    public void onSetting(String name, final int i) {
        Log.e("set", "name: "+name+"  i: "+i );
        setMoney(""+i,name);
    }
}
