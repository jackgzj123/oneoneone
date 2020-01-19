package com.lenovo.smarttraffic.ui.fragment.item17Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lenovo.smarttraffic.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Fragment3 extends Fragment {

    private HorizontalBarChart horizontalBarChart;
    private String[] strings = {"0.00%","10.00%","20.00","30.00","40.00","50.00","60.00","70.00"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3,null);

        horizontalBarChart = view.findViewById(R.id.mHorizentalChart);

        horizontalBarChart.setDescription(null);

        Legend legend = horizontalBarChart.getLegend();
        legend.setEnabled(false);

        horizontalBarChart.setMaxVisibleValueCount(10);
        // 左侧Y轴
        YAxis axisLeft = horizontalBarChart.getAxisRight();
        axisLeft.setTypeface(Typeface.DEFAULT_BOLD);
        axisLeft.setLabelCount(strings.length,true);
        axisLeft.setTextSize(10f);
        axisLeft.setAxisMinimum(0); // 最小值为0
        axisLeft.setAxisMaximum(70); // 最大值为1200
        axisLeft.setTypeface(Typeface.DEFAULT_BOLD); // 加粗字体
        axisLeft.setValueFormatter(new IAxisValueFormatter() { // 自定义值的格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return (int) value + ".00%";
            }
        });
        // 右侧Y轴
        horizontalBarChart.getAxisLeft().setEnabled(false); // 不启用


        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 位于底部
        xAxis.setDrawGridLines(false); // 不绘制X轴网格线
        xAxis.setAxisMinimum(-0.3f); // 最小值-0.3f，为了使左侧留出点空间
        xAxis.setGranularity(1f); // 间隔尺寸1
        xAxis.setTextSize(10f); // 文本大小14
        xAxis.setTypeface(Typeface.DEFAULT_BOLD); // 加粗字体
        xAxis.setValueFormatter(new IAxisValueFormatter() { // 自定义值格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value == 0){
                    return "1-2条违章";
                }else if (value ==1){
                    return "3-5条违章";
                }else {
                    return "5条以上违章";
                }


            }
        });

        setChartData();
        return view;

    }

    private void setChartData() {
        final List<BarEntry> yVals1 = new ArrayList<>();
        yVals1.add(new BarEntry(2f, (float) 13.20));
        yVals1.add(new BarEntry(1f, (float) 26.28));
        yVals1.add(new BarEntry(0f, (float) 60.51));





        BarDataSet barDataSet1 = new BarDataSet(yVals1, "");
        barDataSet1.setValueTextColor(Color.RED);
        barDataSet1.setColors(new int[]{Color.rgb(255,0,0),Color.rgb(0,0,139),Color.rgb(50,205,30)});
        barDataSet1.setValueTextSize(14f);
        barDataSet1.setValueFormatter(new IValueFormatter() {

            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("##.0").format(value) + "%";
            }
        });


        BarData bardata = new BarData(barDataSet1);
        bardata.setBarWidth(0.2f);

        horizontalBarChart.setData(bardata);
    }

}
