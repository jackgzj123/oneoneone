package com.lenovo.smarttraffic.ui.activity.T56;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GZJt5Activity extends AppCompatActivity {

    private LinearLayout mWenlin;
    private LinearLayout mShilin;
    private LinearLayout mSunlin;
    private LinearLayout mCOlin;
    private LinearLayout mPMlin;
    private LinearLayout mDaolin;
    private TextView mWen;
    private TextView mShi;
    private TextView mSun;
    private TextView mCo;
    private TextView mPm;
    private TextView mDao;
    private SQLiteDatabase sql;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<Integer> number = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzjt5);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        getdata();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    private void getdata() {

        okHttpClient.newCall(new Request.Builder().url(UrlClass.ENVIRONMENT_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();
                EnvironmentData environmentData = new Gson().fromJson(string,EnvironmentData.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        number.clear();
                        number.add(Integer.valueOf(environmentData.getTemperature()));
                        number.add(Integer.valueOf(environmentData.getHumidity()));
                        number.add(Integer.valueOf(environmentData.getLightIntensity()));
                        number.add(Integer.valueOf(environmentData.getCo2()));
                        number.add(Integer.valueOf(environmentData.getPm()));
                        number.add(environmentData.getRoad().getRoadId());
                        mWen.setText(environmentData.getTemperature());
                        mSun.setText(environmentData.getLightIntensity());
                        mShi.setText(environmentData.getHumidity());
                        mCo.setText(environmentData.getCo2());
                        mPm.setText(environmentData.getPm());
                        mDao.setText(""+environmentData.getRoad().getRoadId());
                        setsql(number);
                        if (Integer.valueOf(environmentData.getTemperature())>1000){
                            mWenlin.setBackgroundResource(R.drawable.gzjt5_ed2);
                        }else mWenlin.setBackgroundResource(R.drawable.gzjt5_ed1);
                        if (Integer.valueOf(environmentData.getHumidity())>1000){
                            mShilin.setBackgroundResource(R.drawable.gzjt5_ed2);
                        }else mShilin.setBackgroundResource(R.drawable.gzjt5_ed1);
                        if (Integer.valueOf(environmentData.getLightIntensity())>1000){
                            mSunlin.setBackgroundResource(R.drawable.gzjt5_ed2);
                        }else mSunlin.setBackgroundResource(R.drawable.gzjt5_ed1);
                        if (Integer.valueOf(environmentData.getCo2())>1000){
                            mCOlin.setBackgroundResource(R.drawable.gzjt5_ed2);
                        }else mCOlin.setBackgroundResource(R.drawable.gzjt5_ed1);
                        if (Integer.valueOf(environmentData.getPm())>1000){
                            mPMlin.setBackgroundResource(R.drawable.gzjt5_ed2);
                        }else mPMlin.setBackgroundResource(R.drawable.gzjt5_ed1);
                        if (environmentData.getRoad().getRoadId()>1000){
                            mDaolin.setBackgroundResource(R.drawable.gzjt5_ed2);
                        }else mDaolin.setBackgroundResource(R.drawable.gzjt5_ed1);
                    }
                });

            }
        });

    }

    private void setsql(List<Integer> number) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("wen",number.get(0));
        contentValues.put("shi",number.get(1));
        contentValues.put("sun",number.get(2));
        contentValues.put("co2",number.get(3));
        contentValues.put("pm",number.get(4));
        contentValues.put("dao",number.get(5));
        contentValues.put("time",System.currentTimeMillis());
        String s = "select * from en_tb";
        Cursor cursor = sql.rawQuery(s, null);
        if (cursor.getCount()<20){
            sql.insert("en_tb",null,contentValues);
        }else {
            Cursor query = sql.query("en_tb", new String[]{"time"}, null, null, null, null, "time asc","0,1");
            query.moveToNext();
            long time = query.getLong(0);
            sql.update("en_tb",contentValues,"time=?",new String[]{""+time});

        }

    }


    private void initevent() {
        mPm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GZJt5Activity.this,GZJt6Activity.class);
                intent.putExtra("id",4);
                startActivity(intent);
            }
        });
        mCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GZJt5Activity.this,GZJt6Activity.class);
                intent.putExtra("id",3);
                startActivity(intent);
            }
        });
        mWen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GZJt5Activity.this,GZJt6Activity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
        mShi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GZJt5Activity.this,GZJt6Activity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        mSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GZJt5Activity.this,GZJt6Activity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        mDao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GZJt5Activity.this,GZJt6Activity.class);
                intent.putExtra("id",5);
                startActivity(intent);
            }
        });
    }

    private void initview() {
        mWenlin = findViewById(R.id.gzjt5_wendulin);
        mShilin = findViewById(R.id.gzjt5_shidulin);
        mSunlin = findViewById(R.id.gzjt5_sunlin);
        mCOlin = findViewById(R.id.gzjt5_co2lin);
        mPMlin = findViewById(R.id.gzjt5_pmlin);
        mDaolin = findViewById(R.id.gzjt5_daolin);
        mWen = findViewById(R.id.gzjt5_wentv);
        mShi = findViewById(R.id.gzjt5_shitv);
        mSun = findViewById(R.id.gzjt5_suntv);
        mCo = findViewById(R.id.gzjt5_co2tv);
        mPm = findViewById(R.id.gzjt5_pmtv);
        mDao = findViewById(R.id.gzjt5_daotv);
        sql = new Sqlhelp(GZJt5Activity.this).getReadableDatabase();
    }
}
