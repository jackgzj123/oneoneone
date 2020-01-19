package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

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

public class Item41Activity extends AppCompatActivity {

    private BarChart mBarchart;
    private String[] Leftstr = {"畅通","缓行","一般拥堵","中度拥堵","严重拥堵"};
    private String[] strings = {"周一","周二","周三","周四","周五","周六","周日"};
    private int i = 0;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item41);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBarchart = findViewById(R.id.item41_barchart);
        mImg = findViewById(R.id.lgb2_back);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initData();

    }

    private void initData() {
        mBarchart.setExtraOffsets(15f,10f,6f,10f);
        setDescription("");
        setLegend();
        setYAxis();
        setXAxis();
        setChartData();
    }

    private void setChartData() {

        final List<BarEntry> yVals1 = new ArrayList<>();
        yVals1.add(new BarEntry(0f, 5));
        yVals1.add(new BarEntry(1f, 5));
        yVals1.add(new BarEntry(2f, 4));
        yVals1.add(new BarEntry(3f, 4));
        yVals1.add(new BarEntry(4f, 3));
        yVals1.add(new BarEntry(5f, 4));
        yVals1.add(new BarEntry(6f, 3));

        final List<BarEntry> yVals2 = new ArrayList<>();
        yVals2.add(new BarEntry(0f, 4));
        yVals2.add(new BarEntry(1f, 3));
        yVals2.add(new BarEntry(2f, 2));
        yVals2.add(new BarEntry(3f, 5));
        yVals2.add(new BarEntry(4f, 3));
        yVals2.add(new BarEntry(5f, 2));
        yVals2.add(new BarEntry(6f, 4));

        final List<BarEntry> yVals3 = new ArrayList<>();
        yVals3.add(new BarEntry(0f, 3));
        yVals3.add(new BarEntry(1f, 2));
        yVals3.add(new BarEntry(2f, 4));
        yVals3.add(new BarEntry(3f, 3));
        yVals3.add(new BarEntry(4f, 4));
        yVals3.add(new BarEntry(5f, 5));
        yVals3.add(new BarEntry(6f, 3));

        final List<BarEntry> yVals4 = new ArrayList<>();
        yVals4.add(new BarEntry(0f, 2));
        yVals4.add(new BarEntry(1f, 3));
        yVals4.add(new BarEntry(2f, 4));
        yVals4.add(new BarEntry(3f, 4));
        yVals4.add(new BarEntry(4f, 5));
        yVals4.add(new BarEntry(5f, 5));
        yVals4.add(new BarEntry(6f, 3));

        final List<BarEntry> yVals5 = new ArrayList<>();
        yVals5.add(new BarEntry(0f, 3));
        yVals5.add(new BarEntry(1f, 2));
        yVals5.add(new BarEntry(2f, 3));
        yVals5.add(new BarEntry(3f, 2));
        yVals5.add(new BarEntry(4f, 4));
        yVals5.add(new BarEntry(5f, 5));
        yVals5.add(new BarEntry(6f, 3));

        final List<BarEntry> yVals6 = new ArrayList<>();
        yVals6.add(new BarEntry(0f, 2));
        yVals6.add(new BarEntry(1f, 3));
        yVals6.add(new BarEntry(2f, 4));
        yVals6.add(new BarEntry(3f, 3));
        yVals6.add(new BarEntry(4f, 4));
        yVals6.add(new BarEntry(5f, 2));
        yVals6.add(new BarEntry(6f, 2));

        final List<BarEntry> yVals7 = new ArrayList<>();
        yVals7.add(new BarEntry(0f, 3));
        yVals7.add(new BarEntry(1f, 4));
        yVals7.add(new BarEntry(2f, 3));
        yVals7.add(new BarEntry(3f, 2));
        yVals7.add(new BarEntry(4f, 2));
        yVals7.add(new BarEntry(5f, 3));
        yVals7.add(new BarEntry(6f, 2));



        BarDataSet barDataSet1 = new BarDataSet(yVals1, "学院路");
        barDataSet1.setColor(Color.rgb(255,0,0));
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        BarDataSet barDataSet2 = new BarDataSet(yVals2, "联想路");
        barDataSet2.setColor(Color.rgb(0,100,0));
        barDataSet2.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        BarDataSet barDataSet3 = new BarDataSet(yVals3, "医院路");
        barDataSet3.setColor(Color.rgb(127,255,170));
        barDataSet3.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        BarDataSet barDataSet4 = new BarDataSet(yVals4, "幸福路");
        barDataSet4.setColor(Color.rgb(255,165,0));
        barDataSet4.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        BarDataSet barDataSet5 = new BarDataSet(yVals5, "环城快速路");
        barDataSet5.setColor(Color.rgb(0,250,154));
        barDataSet5.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        BarDataSet barDataSet6 = new BarDataSet(yVals6, "环城高速");
        barDataSet6.setColor(Color.rgb(60,179,113));
        barDataSet6.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        BarDataSet barDataSet7 = new BarDataSet(yVals7, "停车场");
        barDataSet7.setColor(Color.rgb(218,165,32));
        barDataSet7.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });


        BarData bardata = new BarData(barDataSet1,barDataSet2,barDataSet3,barDataSet4,barDataSet5,barDataSet6,barDataSet7);
        bardata.setBarWidth(0.05f);

        mBarchart.setData(bardata);
        mBarchart.groupBars(0f, 0.4f,0.02f); // perform the "explicit" grouping
        mBarchart.invalidate(); // refresh
    }

    private void setXAxis() {
        // X轴
        XAxis xAxis = mBarchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 位于底部
        xAxis.setDrawGridLines(false); // 不绘制X轴网格线
        xAxis.setAxisMinimum(-0.3f); // 最小值-0.3f，为了使左侧留出点空间
        xAxis.setGranularity(1f); // 间隔尺寸1
        xAxis.setTextSize(14f); // 文本大小14
        xAxis.setLabelCount(7,false);

        xAxis.setValueFormatter(new IAxisValueFormatter() { // 自定义值格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return strings[(int) value];

            }
        });
    }

    private void setYAxis() {
        // 左侧Y轴
        YAxis axisLeft = mBarchart.getAxisLeft();
        axisLeft.setLabelCount(5, false);
        axisLeft.setValueFormatter(new IAxisValueFormatter() { // 自定义值的格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                    String s = Leftstr[i%5];
                    i++;
                    return s;

            }
        });
        // 右侧Y轴
        YAxis axisRight = mBarchart.getAxisRight();
        axisRight.setAxisMinimum(0f); // 最小值为0
        axisRight.setAxisMaximum(5f); // 最大值为5
        axisRight.setLabelCount(5, false);

    }

    private void setLegend() {


        Legend l = mBarchart.getLegend();
        l.setFormLineWidth(1f);

        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }


    private void setDescription(String s) {
        Description description = new Description();
        description.setText(s);
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
        float y =  Utils.calcTextHeight(paint, s) + Utils.convertDpToPixel(24);
        description.setPosition(x, y);
        mBarchart.setDescription(description);
    }
}
