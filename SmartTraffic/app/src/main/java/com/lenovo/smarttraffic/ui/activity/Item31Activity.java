package com.lenovo.smarttraffic.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.ETCData;
import com.lenovo.smarttraffic.database.data.WeatherData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item31Activity extends AppCompatActivity {

    private ImageView mWeatherImg;
    private TextView mDate;
    private TextView mDay;
    private TextView mtemperature;
    private TextView mJintian;
    private ImageView mJinWeatherImg;
    private TextView mJinWeather;
    private TextView mJintemperature;
    private TextView mMintian;
    private ImageView mMinWeatherImg;
    private TextView mMinWeather;
    private TextView mMintemperature;
    private TextView mHoutian;
    private ImageView mHouWeatherImg;
    private TextView mHouWeather;
    private TextView mHoutemperature;
    private TextView mDahoutian;
    private ImageView mDahouWeatherImg;
    private TextView mDahouWeather;
    private TextView mDahoutemperature;
    private TextView mFivetian;
    private ImageView mFiveWeatherImg;
    private TextView mFiveWeather;
    private TextView mFivetemperature;
    private ImageView mRefresh;

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item31);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        Time time = new Time();
        time.setToNow();
        final String time_show_string = "" + time.year + "年" + (time.month+1) + "月" + time.monthDay + "日";
        mDate.setText(time_show_string);

        getWeather();

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeather();
            }
        });


    }

    private void initview() {
        mWeatherImg = findViewById(R.id.item31_weather);
        mDate = findViewById(R.id.item31_date);
        mDay = findViewById(R.id.item31_day);
        mtemperature = findViewById(R.id.item31_temperature);

        mJintian = findViewById(R.id.item31_jintian);
        mJinWeatherImg = findViewById(R.id.item31_jintian_weatherImg);
        mJinWeather = findViewById(R.id.item31_jintian_weather);
        mJintemperature = findViewById(R.id.item31_jintian_weatherTem);

        mMintian = findViewById(R.id.item31_mintian);
        mMinWeatherImg = findViewById(R.id.item31_mintian_weatherImg);
        mMinWeather = findViewById(R.id.item31_mintian_weather);
        mMintemperature = findViewById(R.id.item31_mintian_weatherTem);

        mHoutian = findViewById(R.id.item31_houtian);
        mHouWeatherImg = findViewById(R.id.item31_houtian_weatherImg);
        mHouWeather = findViewById(R.id.item31_houtian_weather);
        mHoutemperature = findViewById(R.id.item31_houtian_weatherTem);

        mDahoutian = findViewById(R.id.item31_dahoutian);
        mDahouWeatherImg = findViewById(R.id.item31_dahoutian_weatherImg);
        mDahouWeather = findViewById(R.id.item31_dahoutian_weather);
        mDahoutemperature = findViewById(R.id.item31_dahoutian_weatherTem);

        mFivetian = findViewById(R.id.item31_fivetian);
        mFiveWeatherImg = findViewById(R.id.item31_fivetian_weatherImg);
        mFiveWeather = findViewById(R.id.item31_fivetian_weather);
        mFivetemperature = findViewById(R.id.item31_fivetian_weatherTem);

        mRefresh = findViewById(R.id.item31_shuaxin);

    }

    private void getWeather() {

        //todo get请求
        mOkHttpUtil.newCall(new Request.Builder().url("https://www.tianqiapi.com/api/?version=v1&city=%E8%B1%A1%E5%B1%B1&appid=35163593&appsecret=T9t7XBPa").build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                WeatherData weatherData = gson.fromJson(jsonData, WeatherData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mDay.setText(weatherData.getData().get(0).getWeek());
                        mtemperature.setText(weatherData.getData().get(0).getTem());


                        mJintian.setText(weatherData.getData().get(0).getDay());
                        mJintemperature.setText(weatherData.getData().get(0).getTem1()+"/"+weatherData.getData().get(0).getTem2());
                        mJinWeather.setText(weatherData.getData().get(0).getWea());
                        if (weatherData.getData().get(0).getWea_img().equals("yu")){
                            mJinWeatherImg.setImageResource(R.mipmap.w003);
                            mWeatherImg.setImageResource(R.mipmap.rain);
                        }else if (weatherData.getData().get(0).getWea_img().equals("yun")){
                            mJinWeatherImg.setImageResource(R.mipmap.w002);
                            mWeatherImg.setImageResource(R.mipmap.cloudy);
                        }else {
                            mJinWeatherImg.setImageResource(R.mipmap.w001);
                            mWeatherImg.setImageResource(R.mipmap.sun);
                        }

                        mMintian.setText(weatherData.getData().get(1).getDay());
                        mMintemperature.setText(weatherData.getData().get(1).getTem1()+"/"+weatherData.getData().get(1).getTem2());
                        mMinWeather.setText(weatherData.getData().get(1).getWea());
                        if (weatherData.getData().get(1).getWea_img().equals("yu")){
                            mMinWeatherImg.setImageResource(R.mipmap.w003);
                        }else if (weatherData.getData().get(1).getWea_img().equals("yun")){
                            mMinWeatherImg.setImageResource(R.mipmap.w002);
                        }else {
                            mMinWeatherImg.setImageResource(R.mipmap.w001);
                        }

                        mHoutian.setText(weatherData.getData().get(2).getDay());
                        mHoutemperature.setText(weatherData.getData().get(2).getTem1()+"/"+weatherData.getData().get(2).getTem2());
                        mHouWeather.setText(weatherData.getData().get(2).getWea());
                        if (weatherData.getData().get(2).getWea_img().equals("yu")){
                            mHouWeatherImg.setImageResource(R.mipmap.w003);
                        }else if (weatherData.getData().get(2).getWea_img().equals("yun")){
                            mHouWeatherImg.setImageResource(R.mipmap.w002);
                        }else {
                            mHouWeatherImg.setImageResource(R.mipmap.w001);
                        }

                        mDahoutian.setText(weatherData.getData().get(3).getDay());
                        mDahoutemperature.setText(weatherData.getData().get(3).getTem1()+"/"+weatherData.getData().get(3).getTem2());
                        mDahouWeather.setText(weatherData.getData().get(3).getWea());
                        if (weatherData.getData().get(3).getWea_img().equals("yu")){
                            mDahouWeatherImg.setImageResource(R.mipmap.w003);
                        }else if (weatherData.getData().get(3).getWea_img().equals("yun")){
                            mDahouWeatherImg.setImageResource(R.mipmap.w002);
                        }else {
                            mDahouWeatherImg.setImageResource(R.mipmap.w001);
                        }

                        mFivetian.setText(weatherData.getData().get(4).getDay());
                        mFivetemperature.setText(weatherData.getData().get(4).getTem1()+"/"+weatherData.getData().get(4).getTem2());
                        mFiveWeather.setText(weatherData.getData().get(4).getWea());
                        if (weatherData.getData().get(4).getWea_img().equals("yu")){
                            mFiveWeatherImg.setImageResource(R.mipmap.w003);
                        }else if (weatherData.getData().get(4).getWea_img().equals("yun")){
                            mFiveWeatherImg.setImageResource(R.mipmap.w002);
                        }else {
                            mFiveWeatherImg.setImageResource(R.mipmap.w001);
                        }


                    }
                });
            }
        });

    }
}
