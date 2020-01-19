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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.text.DecimalFormat;
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
public class BarLineFragment extends Fragment {

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private BarChart barChart;
    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴

    private List<Integer> PmList = new ArrayList<>();
    private int max = 0;
    private TextView mTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barline,null);
        barChart = view.findViewById(R.id.mBarChar);
        mTv = view.findViewById(R.id.barline_text);



        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                getNet();
            }

        },0,100);

        barChart.setDrawGridBackground(false);//不显示图表网格
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        //不显示边框
        barChart.setDrawBorders(false);
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        //不显示X轴网格线
        xAxis.setDrawGridLines(false);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();

        rightAxis.setEnabled(false);
        leftAxis.setAxisMinimum(0f);

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
                        mTv.setText("过去1分钟空气质量最差值："+max);
                        PmList.clear();
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

                final String pm = environmentData.getPm();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        PmList.add(Integer.valueOf(pm));
                        if (Integer.valueOf(pm)>max){
                            max = Integer.valueOf(pm);
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

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < PmList.size(); i++) {

            barEntries.add(new BarEntry(i,PmList.get(i)));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "PM times");
        barDataSet.setColor(R.color.colorGray);
        barDataSet.setDrawValues(false);


        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(true);//是否显示柱子的数值
        barData.setValueTextSize(10f);//柱子上面标注的数值的字体大小
        barData.setBarWidth(0.8f);//每个柱子的宽度
        barData.setValueTextColor(R.color.colorGray);


        barChart.setData(barData);


    }
}
