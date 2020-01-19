package com.lenovo.smarttraffic.ui.fragment.item25fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
public class Item25WenFragment extends Fragment {

    private LineChart mLineChart;
    private List<Integer> yList = new ArrayList<>();
    private List<String> xStringList = new ArrayList<>();

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pmit25,null);

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mLineChart = view.findViewById(R.id.mLinechart);

        mLineChart.setDrawBorders(false);//取消绘制chart边框的线
        mLineChart.setDrawGridBackground(false);//设置网格背景


        mLineChart.getAxisRight().setEnabled(false);

        for (int i = 0; i < 100; i++) {
            xStringList.add(""+i*3);
        }




//x轴
        XAxis xAxis = mLineChart.getXAxis();//获得x轴
        xAxis.setTextSize(15f);//设置x轴文字大小
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴显示位置
        xAxis.setTextColor(Color.parseColor("#00bcd4"));//设置x轴文字颜色
        xAxis.setLabelCount(5);//x轴坐标显示的数量
        xAxis.setDrawAxisLine(false);//不显示x轴
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);


        //自定义x轴标签数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String s = ""+value*3;
                return s;
            }
        });

        //y轴
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        //左侧Y轴最大值
        leftAxis.setAxisMaximum(40f);
        //左侧Y轴最小值
        leftAxis.setAxisMinimum(-40f);


        mLineChart.setDescription(null);
        mLineChart.getLegend().setEnabled(false);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getNet();

            }
        },0,1000);

        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                setData();
            }
        },3000,3000);


        return view;

    }

    private void setData() {
        ArrayList<Entry> values = new ArrayList<Entry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int i = 0; i < yList.size(); i++) {
            float val = yList.get(i);
            values.add(new Entry(i, val));
        }
        mLineChart.getXAxis().setLabelCount(yList.size(),false);
        mLineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xStringList.get((int) value);
            }
        });
        LineDataSet set1;
        /**************判断图中是否已有LineDataSet，如果有就删除，重新创建加载****************/
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            mLineChart.getData().removeDataSet(0);
        }
        // create a dataset and give it a type
        set1 = new LineDataSet(values, "");

        set1.setDrawIcons(false);

        set1.setColor(Color.BLACK);
        /******************************/
        //循环判断y值，并向颜色集合中添加对应的颜色
        for (int i = 0; i < yList.size(); i++) {
            if (yList.get(i)>30) colors.add(Color.RED);
            else colors.add(Color.GREEN);
        }
        set1.setCircleColors(colors);
        /******************************/
        set1.setLineWidth(1f);
        set1.setCircleRadius(5f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(12f);
        set1.setDrawFilled(false);
        set1.setFormLineWidth(1f);
        set1.setFormSize(15.f);


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets


        // create a data object with the datasets
        LineData data = new LineData(dataSets);

        // set data
        /**************设置数据，并移动到最新点的位置****************/
        mLineChart.setData(data);
        mLineChart.moveViewToX(set1.getEntryCount()-1);
        /******************************/

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
                final String shi = environmentData.getHumidity();
                final String wen = environmentData.getTemperature();
                final String sun = environmentData.getLightIntensity();
                final String co2 = environmentData.getCo2();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        yList.add(Integer.valueOf(wen));
                    }
                });
            }
        });
    }
}
