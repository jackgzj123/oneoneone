package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.data.WeatherData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item51Activity extends AppCompatActivity {

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private TextView mWen;
    private TextView mQi;
    private TextView mDayName1;
    private TextView mDayName2;
    private TextView mDayName3;
    private TextView mDayName4;
    private TextView mDayName5;
    private TextView mDayName6;
    private TextView mDayTime1;
    private TextView mDayTime2;
    private TextView mDayTime3;
    private TextView mDayTime4;
    private TextView mDayTime5;
    private TextView mDayTime6;
    private ImageView mWeateher1;
    private ImageView mWeateher2;
    private ImageView mWeateher3;
    private ImageView mWeateher4;
    private ImageView mWeateher5;
    private ImageView mWeateher6;
    private ImageView mRefresh;
    private TextView mRefrasTime;
    private LineChart mLinChart;
    private String wentop2;
    private String wenbottom2;
    private String wentop3;
    private String wenbottom3;
    private String wentop4;
    private String wenbottom4;
    private String wentop5;
    private String wenbottom5;
    private String wentop6;
    private String wenbottom6;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item51);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();



        Time time = new Time();
        time.setToNow();
        mRefrasTime.setText(""+time.hour+"："+time.minute+"刷新");
        getWeather();

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time time = new Time();
                time.setToNow();
                mRefrasTime.setText(""+time.hour+"："+time.minute+"刷新");
                getWeather();
            }
        });



    }

    private void getDraw() {

        mLinChart.getXAxis().setEnabled(false);
        mLinChart.getAxisRight().setEnabled(false);
        mLinChart.getLegend().setEnabled(false);
        mLinChart.getAxisLeft().setAxisMaximum(30);
        mLinChart.getAxisLeft().setAxisMinimum(-10);
        mLinChart.getAxisLeft().setEnabled(false);
        mLinChart.getDescription().setEnabled(false);



        ArrayList<Entry> yHigh = new ArrayList<Entry>();
        yHigh.add(new Entry(0,17));
        yHigh.add(new Entry(1,Integer.valueOf(wentop2)));
        yHigh.add(new Entry(2,Integer.valueOf(wentop3)));
        yHigh.add(new Entry(3,Integer.valueOf(wentop4)));
        yHigh.add(new Entry(4,Integer.valueOf(wentop5)));
        yHigh.add(new Entry(5,Integer.valueOf(wentop6)));
        LineDataSet high = new LineDataSet(yHigh, "");
        high.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat decimalFormat = new DecimalFormat();
                String s = decimalFormat.format(value)+"℃";
                return s;
            }
        });
        high.setColor(Color.rgb(30,144,255));
        high.setCircleColor(Color.rgb(30,144,255));
        high.setCircleColorHole(Color.rgb(30,144,255));
        high.setLineWidth(3f);//折线宽度
        high.setCircleRadius(5f);

        ArrayList<Entry> yLow = new ArrayList<Entry>();
        yLow.add(new Entry(0,7));
        yLow.add(new Entry(1,Integer.valueOf(wenbottom2)));
        yLow.add(new Entry(2,Integer.valueOf(wenbottom3)));
        yLow.add(new Entry(3,Integer.valueOf(wenbottom4)));
        yLow.add(new Entry(4,Integer.valueOf(wenbottom5)));
        yLow.add(new Entry(5,Integer.valueOf(wenbottom6)));
        LineDataSet low = new LineDataSet(yLow, "");
        low.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat decimalFormat = new DecimalFormat();
                String s = decimalFormat.format(value)+"℃";
                return s;
            }
        });
        low.setColor(Color.GREEN);//折线颜色
        low.setCircleColor(Color.rgb(30,144,255));//折线圆颜色
        low.setCircleColorHole(Color.rgb(30,144,255));//折线小圆颜色
        low.setLineWidth(3f);//折线宽度
        low.setCircleRadius(5f);


//        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
//        dataSets.add(high);
//        dataSets.add(low);

        LineData data = new LineData(high,low);
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(10f);
        mLinChart.setData(data);

    }

    private void initevent() {

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day = time.monthDay;
        if (time.monthDay==1&&((month+1)==1||(month+1)==3||(month+1)==5||(month+1)==7||(month+1)==8||(month+1)==10||(month+1)==12)){
            mDayName1.setText("30日（昨天）");
        }else if (time.monthDay==1&&((month+1)==2||(month+1)==4||(month+1)==6||(month+1)==9||(month+1)==1)){
            mDayName1.setText("31日（昨天）");
        }else if(time.monthDay<10){
            mDayName1.setText("0"+(time.monthDay-1)+"日（昨天）");
        }else {
            mDayName1.setText(""+(time.monthDay-1)+"日（昨天）");
        }


        if ((month+1)>10){
            if (time.monthDay==1&&((month+1)==1||(month+1)==3||(month+1)==5||(month+1)==7||(month+1)==8||(month+1)==10||(month+1)==12)){
                mDayTime1.setText("2020-"+(month+1)+"-30");
            }else if (time.monthDay==1&&((month+1)==2||(month+1)==4||(month+1)==6||(month+1)==9||(month+1)==1)){
                mDayTime1.setText("2020-"+(month+1)+"-31");
            }else if(time.monthDay<10){
                mDayTime1.setText("2020-"+(month+1)+"-0"+(time.monthDay-1));
            }else {
                mDayTime1.setText("2020-"+(month+1)+"-"+(time.monthDay-1));
            }
        }else if ((month+1)==1){
            if (time.monthDay==1&&((month+1)==1||(month+1)==3||(month+1)==5||(month+1)==7||(month+1)==8||(month+1)==10||(month+1)==12)){
                mDayTime1.setText("2019-12-30");
            }else if (time.monthDay==1&&((month+1)==2||(month+1)==4||(month+1)==6||(month+1)==9||(month+1)==1)){
                mDayTime1.setText("2020-0"+month+"-31");
            }else if(time.monthDay<10){
                mDayTime1.setText("2020-0"+(month+1)+"-0"+(time.monthDay-1));
            }else {
                mDayTime1.setText("2020-0"+(month+1)+"-"+(time.monthDay-1));
            }
        } else {
            if (time.monthDay==1&&((month+1)==1||(month+1)==3||(month+1)==5||(month+1)==7||(month+1)==8||(month+1)==10||(month+1)==12)){
                mDayTime1.setText("2020-0"+month+"-30");
            }else if (time.monthDay==1&&((month+1)==2||(month+1)==4||(month+1)==6||(month+1)==9||(month+1)==1)){
                mDayTime1.setText("2020-0"+month+"-31");
            }else if(time.monthDay<10){
                mDayTime1.setText("2020-0"+(month+1)+"-0"+(time.monthDay-1));
            }else {
                mDayTime1.setText("2020-0"+(month+1)+"-"+(time.monthDay-1));
            }
        }



    }

    private void initview() {
        mBack = findViewById(R.id.lgb2_back);
        mWen = findViewById(R.id.item51_wen);
        mQi = findViewById(R.id.item51_qi);
        mDayName1 = findViewById(R.id.item51_dayname1);
        mDayName2 = findViewById(R.id.item51_dayname2);
        mDayName3 = findViewById(R.id.item51_dayname3);
        mDayName4 = findViewById(R.id.item51_dayname4);
        mDayName5 = findViewById(R.id.item51_dayname5);
        mDayName6 = findViewById(R.id.item51_dayname6);
        mDayTime1 = findViewById(R.id.item51_daytime1);
        mDayTime2 = findViewById(R.id.item51_daytime2);
        mDayTime3 = findViewById(R.id.item51_daytime3);
        mDayTime4 = findViewById(R.id.item51_daytime4);
        mDayTime5 = findViewById(R.id.item51_daytime5);
        mDayTime6 = findViewById(R.id.item51_daytime6);
        mWeateher1 = findViewById(R.id.item51_weather1);
        mWeateher2 = findViewById(R.id.item51_weather2);
        mWeateher3 = findViewById(R.id.item51_weather3);
        mWeateher4 = findViewById(R.id.item51_weather4);
        mWeateher5 = findViewById(R.id.item51_weather5);
        mWeateher6 = findViewById(R.id.item51_weather6);
        mRefresh = findViewById(R.id.item51_refresh);
        mRefrasTime = findViewById(R.id.item51_refreshtime);
        mLinChart = findViewById(R.id.item51_linechart);

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

                        mWen.setText(weatherData.getData().get(0).getTem());
                        mQi.setText(weatherData.getData().get(0).getWea());


                        mDayName2.setText(weatherData.getData().get(0).getDay());
                        mDayName3.setText(weatherData.getData().get(1).getDay());
                        mDayName4.setText(weatherData.getData().get(2).getDay());
                        mDayName5.setText(weatherData.getData().get(3).getDay());
                        mDayName6.setText(weatherData.getData().get(4).getDay());

                        if (weatherData.getData().get(0).getWea_img().equals("yu")){
                            mWeateher2.setImageResource(R.mipmap.rain);
                        }else if (weatherData.getData().get(0).getWea_img().equals("yun")){
                            mWeateher2.setImageResource(R.mipmap.cloudy);
                        }else if(weatherData.getData().get(0).getWea_img().equals("yin")){
                            mWeateher2.setImageResource(R.mipmap.cloudy_);
                        }else {
                            mWeateher2.setImageResource(R.mipmap.sun);
                        }

                        if (weatherData.getData().get(1).getWea_img().equals("yu")){
                            mWeateher3.setImageResource(R.mipmap.rain);
                        }else if (weatherData.getData().get(1).getWea_img().equals("yun")){
                            mWeateher3.setImageResource(R.mipmap.cloudy);
                        }else if(weatherData.getData().get(1).getWea_img().equals("yin")){
                            mWeateher3.setImageResource(R.mipmap.cloudy_);
                        }else {
                            mWeateher3.setImageResource(R.mipmap.sun);
                        }

                        if (weatherData.getData().get(2).getWea_img().equals("yu")){
                            mWeateher4.setImageResource(R.mipmap.rain);
                        }else if (weatherData.getData().get(2).getWea_img().equals("yun")){
                            mWeateher4.setImageResource(R.mipmap.cloudy);
                        }else if(weatherData.getData().get(2).getWea_img().equals("yin")){
                            mWeateher4.setImageResource(R.mipmap.cloudy_);
                        }else {
                            mWeateher4.setImageResource(R.mipmap.sun);
                        }

                        if (weatherData.getData().get(3).getWea_img().equals("yu")){
                            mWeateher5.setImageResource(R.mipmap.rain);
                        }else if (weatherData.getData().get(3).getWea_img().equals("yun")){
                            mWeateher5.setImageResource(R.mipmap.cloudy);
                        }else if(weatherData.getData().get(3).getWea_img().equals("yin")){
                            mWeateher5.setImageResource(R.mipmap.cloudy_);
                        }else {
                            mWeateher5.setImageResource(R.mipmap.sun);
                        }

                        if (weatherData.getData().get(4).getWea_img().equals("yu")){
                            mWeateher6.setImageResource(R.mipmap.rain);
                        }else if (weatherData.getData().get(4).getWea_img().equals("yun")){
                            mWeateher6.setImageResource(R.mipmap.cloudy);
                        }else if(weatherData.getData().get(4).getWea_img().equals("yin")){
                            mWeateher6.setImageResource(R.mipmap.cloudy_);
                        }else {
                            mWeateher6.setImageResource(R.mipmap.sun);
                        }

                        wentop2 = weatherData.getData().get(0).getTem1().replace("℃","");
                        wenbottom2 = weatherData.getData().get(0).getTem2().replace("℃","");

                        wentop3 = weatherData.getData().get(1).getTem1().replace("℃","");
                        wenbottom3 = weatherData.getData().get(1).getTem2().replace("℃","");

                        wentop4 = weatherData.getData().get(2).getTem1().replace("℃","");
                        wenbottom4 = weatherData.getData().get(2).getTem2().replace("℃","");

                        wentop5 = weatherData.getData().get(3).getTem1().replace("℃","");
                        wenbottom5 = weatherData.getData().get(3).getTem2().replace("℃","");

                        wentop6 = weatherData.getData().get(4).getTem1().replace("℃","");
                        wenbottom6 = weatherData.getData().get(4).getTem2().replace("℃","");

                        mDayTime2.setText(weatherData.getData().get(0).getDate());
                        mDayTime3.setText(weatherData.getData().get(1).getDate());
                        mDayTime4.setText(weatherData.getData().get(2).getDate());
                        mDayTime5.setText(weatherData.getData().get(3).getDate());
                        mDayTime6.setText(weatherData.getData().get(4).getDate());

                        getDraw();
                    }
                });
            }
        });

    }

}
