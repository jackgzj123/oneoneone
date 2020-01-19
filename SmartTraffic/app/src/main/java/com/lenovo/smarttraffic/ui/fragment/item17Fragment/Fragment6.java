package com.lenovo.smarttraffic.ui.fragment.item17Fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lenovo.smarttraffic.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Fragment6 extends Fragment {

    private BarChart bc;
    private String[] strings = {"0:00:01-2:00:00","2:00:01-4:00:00","4:00:01-6:00:00","6:00:01-8:00:00","8:00:01-10:00:00","10:00:01-12:00:00","12:00:01-14:00:00","14:00:01-16:00:00","16:00:01-18:00:00","18:00:01-20:00:00","20:00:01-22:00:00","22:00:01-24:00:00"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment5,null);
        bc = view.findViewById(R.id.mBarChart);

        initData();


        return view;
    }

    private void initData() {

        bc.setExtraOffsets(24f,48f,24f,24f);
        setDescription("");
        setLegend();
        setYAxis();
        setXAxis();
        setChartData();
    }

    private void setDescription(String descriptionStr) {
        Description description = new Description();
        description.setText(descriptionStr);
        description.setTextSize(18f);
        description.setTextAlign(Paint.Align.CENTER); // 文本居中对齐
        // 计算描述位置
        //WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        //wm.getDefaultDisplay().getMetrics(outMetrics);
        Paint paint = new Paint();
        paint.setTextSize(18f);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        float x = outMetrics.widthPixels / 2;
        float y =  Utils.calcTextHeight(paint, descriptionStr) + Utils.convertDpToPixel(24);
        description.setPosition(x, y);
        bc.setDescription(description);

    }

    private void setLegend() {
        Legend legend = bc.getLegend();
        legend.setEnabled(false);
    }

    private void setYAxis() {
        // 左侧Y轴
        YAxis axisLeft = bc.getAxisLeft();
        axisLeft.setAxisMinimum(0); // 最小值为0
        axisLeft.setAxisMaximum(25); // 最大值为1200
        axisLeft.setValueFormatter(new IAxisValueFormatter() { // 自定义值的格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + ".00%";
            }
        });
        // 右侧Y轴
        bc.getAxisRight().setEnabled(false); // 不启用
    }

    private void setXAxis() {
        // X轴
        XAxis xAxis = bc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 位于底部
        xAxis.setDrawGridLines(false); // 不绘制X轴网格线
        xAxis.setAxisMinimum(-0.3f); // 最小值-0.3f，为了使左侧留出点空间
        xAxis.setGranularity(1f); // 间隔尺寸1
        xAxis.setTextSize(14f); // 文本大小14
        xAxis.setLabelRotationAngle(-60);
        xAxis.setLabelCount(strings.length,true);

        xAxis.setTypeface(Typeface.DEFAULT_BOLD); // 加粗字体
        xAxis.setValueFormatter(new IAxisValueFormatter() { // 自定义值格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return strings[(int) value];

            }
        });
    }

    private void setChartData() {
        final List<BarEntry> yVals1 = new ArrayList<>();
        yVals1.add(new BarEntry(0f, 2));
        yVals1.add(new BarEntry(1f, 1));
        yVals1.add(new BarEntry(2f, 3));
        yVals1.add(new BarEntry(3f, 6));
        yVals1.add(new BarEntry(4f, (float) 18.54));
        yVals1.add(new BarEntry(5f, (float) 21.87));
        yVals1.add(new BarEntry(6f, 12));
        yVals1.add(new BarEntry(7f, (float) 19.28));
        yVals1.add(new BarEntry(8f, 12));
        yVals1.add(new BarEntry(9f, 4));
        yVals1.add(new BarEntry(10f, 2));
        yVals1.add(new BarEntry(11f, 1));




        BarDataSet barDataSet1 = new BarDataSet(yVals1, "");
        barDataSet1.setValueTextColor(Color.RED);
        barDataSet1.setColors(new int[]{Color.rgb(0,191,255),Color.rgb(139,69,19),Color.rgb(0,0,128),Color.rgb(0,100,0),Color.rgb(230,230,250),Color.rgb(34,139,34),Color.rgb(178,34,34),Color.rgb(255,215,0),Color.rgb(100,149,237),Color.rgb(255,165,0),Color.rgb(186,85,211),Color.rgb(139,69,19),});
        barDataSet1.setValueTextSize(14f);
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("##.0").format(value) + "%";
            }
        });


        BarData bardata = new BarData(barDataSet1);
        bardata.setBarWidth(0.2f);

        bc.setData(bardata);
    }



}
