package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

public class Item52Activity extends AppCompatActivity {


    private RadarChart mRadrachart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item52);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mRadrachart = findViewById(R.id.item52_radarchart);


        XAxis xAxis = mRadrachart.getXAxis();
        xAxis.setDrawLabels(false);
        YAxis yAxis = mRadrachart.getYAxis();
        yAxis.setAxisMaximum(70f); // 要达到100需要把该值设为80，至于原因可以试着向下调小和向上调大看看效果就
        yAxis.setAxisMinimum(0f);
        // Y坐标值标签个数
        yAxis.setLabelCount(5, false);
        mRadrachart.setDrawAngleCircle(true);
        mRadrachart.setAngleCircleRadius(8f);
        mRadrachart.setAngleCircleColor(new int[] {
                Color.parseColor("#33ff66"),
                Color.parseColor("#ef5aa1"),
                Color.parseColor("#ff0000"),
                Color.parseColor("#6600ff"),
                Color.parseColor("#36a9ce")
        });
        setChartData2_3_1();
        Legend l = mRadrachart.getLegend();
        // 图例位置
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);


    }


    /**
     * 创建虚拟图表数据
     * @param lable 数据标签
     * @return
     */
    private RadarDataSet creatingData(String lable,float a1,float a2,float a3,float a4,float a5) {
        List<RadarEntry> yVals = new ArrayList<>();


            yVals.add(new RadarEntry(a1)); // 生成1-100的随机数
        yVals.add(new RadarEntry(a2)); // 生成1-100的随机数
        yVals.add(new RadarEntry(a3)); // 生成1-100的随机数
        yVals.add(new RadarEntry(a4)); // 生成1-100的随机数
        yVals.add(new RadarEntry(a5)); // 生成1-100的随机数


        RadarDataSet ds = new RadarDataSet(yVals, lable);
        return ds;
    }

    /**
     * 设置图表数据
     */
    private void setChartData2_3_1() {
        List<IRadarDataSet> dataSets = new ArrayList<>();
        dataSets.add(creatingData("驾驶摩托车在车把上悬挂物品的", Color.parseColor("#36a9ce"),80.0f,60.0f,100.0f,60.0f, 100.0f));
        dataSets.add(creatingData("拖拉机驶入大中城市中心城区内道路的", Color.parseColor("#33ff66"),60.0f,100.0f,80.0f,80.0f,60.0f));
        dataSets.add(creatingData("拖拉机违法规定载人的", Color.parseColor("#ef5aa1"),0f,0f,0f,0f,0f));
        dataSets.add(creatingData("拖拉机牵引多辆挂车的", Color.parseColor("#ff0000"),0f,0f,0f,0f,0f));
        dataSets.add(creatingData("学习驾驶人不按指定时间上盗录学习驾驶的", Color.parseColor("#6600ff"),0f,0f,0f,0f,0f));
        RadarData data = new RadarData(dataSets);
        mRadrachart.setData(data);
    }

    /**
     * 创建一组虚拟图表数据
     * @param lable 标签
     * @param color 颜色
     * @return
     */
    private RadarDataSet creatingData(String lable, int color,float a1,float a2,float a3,float a4,float a5) {
        RadarDataSet ds = creatingData(lable,a1,a2,a3,a4,a5);
        ds.setColor(color);
//        ds.setColor(color, 102); // 第二个参数（alpha）设置颜色的透明度
        ds.setDrawFilled(true); // 绘制填充，默认为false
        ds.setFillColor(color); // 填充颜色
        ds.setFillAlpha(90); // 填充内容透明度
        ds.setDrawValues(false);
        ds.setHighlightEnabled(false); // 是否绘制高亮效果，默认为true
        return ds;
    }



}
