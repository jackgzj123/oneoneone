package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.data.WeatherData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item47Activity extends AppCompatActivity {

    private TextView mTime;
    private TextView mName;
    private TextView mPmTop;
    private TextView mPmBottom;
    private TextView mPmPing;
    private TextView mCo2Top;
    private TextView mCo2Bottom;
    private TextView mCo2Ping;
    private TextView mShiTop;
    private TextView mShiBottom;
    private TextView mShiPing;
    private TextView mSunTop;
    private TextView mSunBottom;
    private TextView mSunPing;
    private TextView mWenTop;
    private TextView mWenBottom;
    private TextView mWenPing;
    private PieChart mPiechart;
    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private float bei = 13;
    private float shen =17;
    private float xiong = 40;
    private float chongqing=20;
    private float shang =10;
    private float total;
    private ImageView mImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item47);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        getTime();

        drawpicture();

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void drawpicture() {

        total = bei+shang+shen+chongqing+xiong;
        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry((float) bei/total*100, "北京"));
        entries.add(new PieEntry((float) shang/total*100, "上海"));
        entries.add(new PieEntry((float)shen/total*100, "深圳"));
        entries.add(new PieEntry((float) chongqing/total*100, "重庆"));
        entries.add(new PieEntry((float) xiong/total*100, "雄安"));

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.rgb(47,79,79));
        colors.add(Color.rgb(0,139,139));
        colors.add(Color.rgb(210,105,30));
        colors.add(Color.rgb(144,238,144));
        colors.add(Color.rgb(178,34,34));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(0);//设置扇形空隙的大小
        dataSet.setSelectionShift(10f);//设置饼块选中时偏离饼图中心的距离
        dataSet.setColors(colors);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//文本外显示
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//文本外显示

        mPiechart.setDrawHoleEnabled(false);
        mPiechart.setRotationAngle(55);
        // 是否使用百分比
        mPiechart.setUsePercentValues(false);
        mPiechart.setRotationEnabled(false); // 不可以手动旋转

        mPiechart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.e(",","entry:"+e);  //打印日志
                if(xiong == e.getY()){
                    mName.setText("雄安");
                    mCo2Bottom.setText("654");
                    mCo2Top.setText("1222");
                    mCo2Ping.setText("784");
                    mPmBottom.setText("654");
                    mPmTop.setText("1222");
                    mPmPing.setText("784");
                    mShiBottom.setText("654");
                    mShiTop.setText("1222");
                    mShiPing.setText("784");
                    mSunBottom.setText("654");
                    mSunTop.setText("1222");
                    mSunPing.setText("784");
                    mWenBottom.setText("654");
                    mWenTop.setText("1222");
                    mWenPing.setText("784");


                }else if (bei==e.getY()){
                    mName.setText("北京");
                    mCo2Bottom.setText("321");
                    mCo2Top.setText("1442");
                    mCo2Ping.setText("897");
                    mPmBottom.setText("321");
                    mPmTop.setText("1442");
                    mPmPing.setText("784");
                    mShiBottom.setText("321");
                    mShiTop.setText("1442");
                    mShiPing.setText("784");
                    mSunBottom.setText("321");
                    mSunTop.setText("1442");
                    mSunPing.setText("784");
                    mWenBottom.setText("321");
                    mWenTop.setText("1442");
                    mWenPing.setText("784");
                }else if (shang==e.getY()){
                    mName.setText("上海");
                    mCo2Bottom.setText("321");
                    mCo2Top.setText("1442");
                    mCo2Ping.setText("897");
                    mPmBottom.setText("321");
                    mPmTop.setText("1442");
                    mPmPing.setText("784");
                    mShiBottom.setText("321");
                    mShiTop.setText("1442");
                    mShiPing.setText("784");
                    mSunBottom.setText("321");
                    mSunTop.setText("1442");
                    mSunPing.setText("784");
                    mWenBottom.setText("321");
                    mWenTop.setText("1442");
                    mWenPing.setText("784");
                }else if (shen==e.getY()){
                    mName.setText("深圳");
                    mCo2Bottom.setText("321");
                    mCo2Top.setText("1442");
                    mCo2Ping.setText("897");
                    mPmBottom.setText("321");
                    mPmTop.setText("1442");
                    mPmPing.setText("784");
                    mShiBottom.setText("321");
                    mShiTop.setText("1442");
                    mShiPing.setText("784");
                    mSunBottom.setText("321");
                    mSunTop.setText("1442");
                    mSunPing.setText("784");
                    mWenBottom.setText("321");
                    mWenTop.setText("1442");
                    mWenPing.setText("784");
                }else if (chongqing==e.getY()){
                    mName.setText("重庆");
                    mCo2Bottom.setText("321");
                    mCo2Top.setText("1442");
                    mCo2Ping.setText("897");
                    mPmBottom.setText("321");
                    mPmTop.setText("1442");
                    mPmPing.setText("784");
                    mShiBottom.setText("321");
                    mShiTop.setText("1442");
                    mShiPing.setText("784");
                    mSunBottom.setText("321");
                    mSunTop.setText("1442");
                    mSunPing.setText("784");
                    mWenBottom.setText("321");
                    mWenTop.setText("1442");
                    mWenPing.setText("784");
                }


            }

            @Override
            public void onNothingSelected() {
                //整体扇形的监听
            }
        });


        mPiechart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//图例显示位置
        mPiechart.getLegend().setXEntrySpace(20f);
        mPiechart.getLegend().setYEntrySpace(20f);
        mPiechart.setDescription(null);
        mPiechart.getLegend().setFormToTextSpace(10f);
        mPiechart.setEntryLabelColor(R.color.Black);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);


        mPiechart.setData(data);
        mPiechart.highlightValues(null);


    }


    private void getTime() {
        Time time = new Time();
        time.setToNow();
        String s = time.year+"年"+(time.month+1)+"月"+time.monthDay+"日"+time.hour+":"+time.minute+" "+"星期";
        final Calendar c = Calendar.getInstance();
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
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
        s+=mWay;
        mTime.setText(s);
    }

    private void initview() {
        mTime = findViewById(R.id.item47_time);
        mName = findViewById(R.id.item47_name);
        mPmTop = findViewById(R.id.item47_pm_top);
        mPmBottom = findViewById(R.id.item47_pm_bottom);
        mPmPing = findViewById(R.id.item47_pm_ping);
        mCo2Top = findViewById(R.id.item47_co2_top);
        mCo2Bottom = findViewById(R.id.item47_co2_bottom);
        mCo2Ping = findViewById(R.id.item47_co2_ping);
        mShiTop = findViewById(R.id.item47_shi_top);
        mShiBottom = findViewById(R.id.item47_shi_bottom);
        mShiPing = findViewById(R.id.item47_shi_ping);
        mSunTop = findViewById(R.id.item47_sun_top);
        mSunBottom = findViewById(R.id.item47_sun_bottom);
        mSunPing = findViewById(R.id.item47_sun_ping);
        mWenTop = findViewById(R.id.item47_wen_top);
        mWenBottom = findViewById(R.id.item47_wen_bottom);
        mWenPing = findViewById(R.id.item47_wen_ping);
        mPiechart = findViewById(R.id.item47_piechart);
        mImg = findViewById(R.id.lgb2_back);

    }
    private void getWeather(String url) {

        //todo get请求
        mOkHttpUtil.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
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


                    }
                });
            }
        });

    }
}
