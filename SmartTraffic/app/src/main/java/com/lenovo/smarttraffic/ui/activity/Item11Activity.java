package com.lenovo.smarttraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.CarMessageDao;
import com.lenovo.smarttraffic.database.data.CarData;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item11Activity extends AppCompatActivity implements MyDialog.SettingListener {

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private TextView mChongzhi;
    private TextView mCarname1;
    private TextView mCaryue1;
    private ImageView mCarok1;
    private Button mCarchong1;
    private TextView mCarname2;
    private TextView mCaryue2;
    private ImageView mCarok2;
    private Button mCarchong2;
    private TextView mCarname3;
    private TextView mCaryue3;
    private ImageView mCarok3;
    private Button mCarchong3;
    private TextView mCarname4;
    private TextView mCaryue4;
    private ImageView mCarok4;
    private Button mCarchong4;
    private TextView mCarname5;
    private TextView mCaryue5;
    private ImageView mCarok5;
    private Button mCarchong5;
    private LinearLayout mLincar1;
    private LinearLayout mLincar2;
    private LinearLayout mLincar3;
    private LinearLayout mLincar4;
    private LinearLayout mLincar5;
    private TextView mPiliang;
    private List<CarData> carDatalist = new ArrayList<>();
    private List<String> carnamelist = new ArrayList<>();
    private CarMessageDao carMessageDao;

    private int m =100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item11);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        carMessageDao =new CarMessageDao(this);
        carMessageDao.deleteCarMessage();
        initview();

        initevent();
    }

    private void initevent() {
        //todo  get请求
        //todo get请求
        mOkhttp.newCall(new Request.Builder().url(UrlClass.ETC_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                carDatalist = gson.fromJson(jsonData, new TypeToken<List<CarData>>() {}.getType());

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mCarname1.setText(carDatalist.get(0).getCarNumber());
                        mCarname2.setText(carDatalist.get(1).getCarNumber());
                        mCarname3.setText(carDatalist.get(2).getCarNumber());
                        mCarname4.setText(carDatalist.get(3).getCarNumber());
                        mCarname5.setText(carDatalist.get(4).getCarNumber());

                        mCaryue1.setText(carDatalist.get(0).getCarMoney());
                        mCaryue2.setText(carDatalist.get(1).getCarMoney());
                        mCaryue3.setText(carDatalist.get(2).getCarMoney());
                        mCaryue4.setText(carDatalist.get(3).getCarMoney());
                        mCaryue5.setText(carDatalist.get(4).getCarMoney());

                    }
                });
            }
        });


        mCarchong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add(mCarname1.getText().toString());
                MyDialog dialog = new MyDialog(Item11Activity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11Activity.this);
                dialog.show();
            }
        });
        mCarchong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add(mCarname2.getText().toString());
                MyDialog dialog = new MyDialog(Item11Activity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11Activity.this);
                dialog.show();
            }
        });
        mCarchong3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add(mCarname3.getText().toString());
                MyDialog dialog = new MyDialog(Item11Activity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11Activity.this);
                dialog.show();
            }
        });
        mCarchong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add(mCarname4.getText().toString());
                MyDialog dialog = new MyDialog(Item11Activity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11Activity.this);
                dialog.show();
            }
        });
        mCarchong5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.clear();
                carnamelist.add(mCarname5.getText().toString());
                MyDialog dialog = new MyDialog(Item11Activity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11Activity.this);
                dialog.show();
            }
        });

        mPiliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog dialog = new MyDialog(Item11Activity.this,R.style.mydialog,carnamelist);
                dialog.setOnSettingListener(Item11Activity.this);
                dialog.show();
            }
        });

        mCarok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add(mCarname1.getText().toString());
                mCarok1.setImageResource(R.mipmap.my_car);
            }
        });
        mCarok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add(mCarname2.getText().toString());
                mCarok2.setImageResource(R.mipmap.my_car);
            }
        });
        mCarok3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add(mCarname3.getText().toString());
                mCarok3.setImageResource(R.mipmap.my_car);
            }
        });
        mCarok4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add(mCarname4.getText().toString());
                mCarok4.setImageResource(R.mipmap.my_car);
            }
        });
        mCarok5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnamelist.add(mCarname5.getText().toString());
                mCarok5.setImageResource(R.mipmap.my_car);
            }
        });

        if (Integer.parseInt(mCaryue5.getText().toString())< m){
            mLincar5.setBackgroundColor(Color.parseColor("#ffcc00"));
        }

        mChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item11Activity.this,Item3Activity.class));
            }
        });
    }

    private void initview() {

        mChongzhi = findViewById(R.id.item11_jilv);

        mCarname1 = findViewById(R.id.item11_car1name);
        mCaryue1 = findViewById(R.id.item11_car1yue);
        mCarok1 = findViewById(R.id.item11_car1ok);
        mCarchong1 = findViewById(R.id.item11_car1chong);

        mCarname2 = findViewById(R.id.item11_car2name);
        mCaryue2 = findViewById(R.id.item11_car2yue);
        mCarok2 = findViewById(R.id.item11_car2ok);
        mCarchong2 = findViewById(R.id.item11_car2chong);

        mCarname3 = findViewById(R.id.item11_car3name);
        mCaryue3 = findViewById(R.id.item11_car3yue);
        mCarok3 = findViewById(R.id.item11_car3ok);
        mCarchong3 = findViewById(R.id.item11_car3chong);

        mCarname4 = findViewById(R.id.item11_car4name);
        mCaryue4 = findViewById(R.id.item11_car4yue);
        mCarok4 = findViewById(R.id.item11_car4ok);
        mCarchong4 = findViewById(R.id.item11_car4chong);

        mCarname5 = findViewById(R.id.item11_car5name);
        mCaryue5 = findViewById(R.id.item11_car5yue);
        mCarok5 = findViewById(R.id.item11_car5ok);
        mCarchong5 = findViewById(R.id.item11_car5chong);

        mLincar1 = findViewById(R.id.item11_car1);
        mLincar2 = findViewById(R.id.item11_car2);
        mLincar3 = findViewById(R.id.item11_car3);
        mLincar4 = findViewById(R.id.item11_car4);
        mLincar5 = findViewById(R.id.item11_car5);

        mPiliang = findViewById(R.id.item11_piliang);

    }

    @Override
    public void onSetting(String name,int i) {

        if (i == 1){
            int j = Integer.parseInt(name)+Integer.parseInt(mCaryue1.getText().toString());
            mCaryue1.setText(""+j);
            Time time = new Time();
            time.setToNow();
            String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
            carMessageDao.addCarMessage(mCarname1.getText().toString(), name, String.valueOf(j),"葛张杰",time_show_string);
        }else if (i == 2){
            int j = Integer.parseInt(name)+Integer.parseInt(mCaryue2.getText().toString());
            mCaryue2.setText(""+j);
            Time time = new Time();
            time.setToNow();
            String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
            carMessageDao.addCarMessage(mCarname2.getText().toString(),name, String.valueOf(j),"葛张杰",time_show_string);

        }else if (i == 3){
            int j = Integer.parseInt(name)+Integer.parseInt(mCaryue3.getText().toString());
            mCaryue3.setText(""+j);
            Time time = new Time();
            time.setToNow();
            String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
            carMessageDao.addCarMessage(mCarname3.getText().toString(),name, String.valueOf(j),"葛张杰",time_show_string);

        }else if (i == 4){
            int j = Integer.parseInt(name)+Integer.parseInt(mCaryue4.getText().toString());
            mCaryue4.setText(""+j);
            Time time = new Time();
            time.setToNow();
            String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
            carMessageDao.addCarMessage(mCarname4.getText().toString(),name, String.valueOf(j),"葛张杰",time_show_string);

        }else{
            int j = Integer.parseInt(name)+Integer.parseInt(mCaryue5.getText().toString());
            mCaryue5.setText(""+j);
            Time time = new Time();
            time.setToNow();
            String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
            carMessageDao.addCarMessage(mCarname5.getText().toString(),name, String.valueOf(j),"葛张杰",time_show_string);
        }

    }
}
