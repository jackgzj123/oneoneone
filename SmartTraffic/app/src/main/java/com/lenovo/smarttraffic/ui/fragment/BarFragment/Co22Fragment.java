package com.lenovo.smarttraffic.ui.fragment.BarFragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class Co22Fragment extends Fragment {
    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private LineChart lineChart;
    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴

    private List<Integer> Co2List = new ArrayList<>();
    private int max = 0;
    private int min = 0;
    private TextView mTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_co22,null);
        lineChart = view.findViewById(R.id.mLinerChar);
        mTv = view.findViewById(R.id.co2_text);



        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                getNet();
            }

        },0,100);

        lineChart.setDrawGridBackground(false);//不显示图表网格
        //背景颜色
        lineChart.setBackgroundColor(Color.BLUE);

        //不显示边框
        lineChart.setDrawBorders(false);
        //X轴设置显示位置在底部
        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        //不显示X轴网格线
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(R.color.White);

        leftAxis = lineChart.getAxisLeft();
        rightAxis = lineChart.getAxisRight();

        rightAxis.setEnabled(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaxValue(2000f);
        leftAxis.setTextColor(R.color.White);

        xAxis.setDrawAxisLine(false);
        leftAxis.setDrawAxisLine(false);
        rightAxis.setDrawAxisLine(false);




        Timer timer1 = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer1.schedule(new TimerTask(){
            @Override
            public void run(){
                setData();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTv.setText("过去1分钟最大相对浓度："+max);
                        Co2List.clear();
                        max = 0;
                    }
                });
            }

        },2000,2000);

        return view;
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
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Co2List.add(Integer.valueOf(co2));
                        if (Integer.valueOf(co2)>max){
                            max = Integer.valueOf(co2);
                        }
                    }
                });
            }
        });
    }

    private void setData() {
        final List<String> xValue = new ArrayList<>();
        xValue.add("03");//index = 0 的位置的数据在IndexAxisValueFormatter中时不显示的。
        xValue.add("06");
        xValue.add("09");
        xValue.add("12");
        xValue.add("15");
        xValue.add("18");
        xValue.add("21");
        xValue.add("24");
        xValue.add("27");
        xValue.add("30");
        xValue.add("33");
        xValue.add("36");
        xValue.add("39");
        xValue.add("42");
        xValue.add("45");
        xValue.add("48");
        xValue.add("51");
        xValue.add("54");
        xValue.add("57");
        xValue.add("60");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));//设置x轴标签格式化器

        ArrayList<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < Co2List.size(); i++) {

            lineEntries.add(new BarEntry(i,Co2List.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Co2 times");
        lineDataSet.setColor(Color.rgb(255,255,255));
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setCircleColor(Color.rgb(255,255,255));


        LineData lineData = new LineData(lineDataSet);

        lineChart.setHighlightPerTapEnabled(false);
        lineChart.setHighlightPerDragEnabled(false);
        lineChart.setData(lineData);


    }
}
