package com.lenovo.smarttraffic.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
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
import com.lenovo.smarttraffic.Constant;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;


/**
 * @author Amoly
 * @date 2019/4/11.
 * description：设计页面
 */
public class DesignFragment extends BaseFragment {
    private static DesignFragment instance = null;
    private PieChart pieChart;

    public static DesignFragment getInstance() {
        if (instance == null) {
            instance = new DesignFragment();
        }
        return instance;
    }


    @Override
    protected View getSuccessView() {
//        TextView view = new TextView(getActivity());
//        view.setText("创意设计");
//        view.setTextColor(Color.RED);
//        view.setTextSize(50);
        pieChart = new PieChart(getActivity());
        //设置 pieChart 图表基本属性

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);//描述
        pieChart.setExtraOffsets(5,10,5,5);//偏移量
        pieChart.setDragDecelerationFrictionCoef(0.95f);//摩擦系数

        //设置中间文件
        pieChart.setCenterText(geText());
        pieChart.setDrawCenterText(true);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(58f);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setTransparentCircleRadius(61f);

        // 触摸旋转
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setRotationAngle(0);

        //变化监听
       // pieChart.setOnChartValueSelectedListener(getContext().this);

        //设置数据
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40,"优秀"));
        entries.add(new PieEntry(30,"满分"));
        entries.add(new PieEntry(20,"及格"));
        entries.add(new PieEntry(10,"不及格"));

        setData(entries);

        // 获取pieCahrt图列
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(12f);
        return pieChart;
    }

    private CharSequence geText() {

        SpannableString s = new SpannableString("葛某人程序员\n我仿佛听到有人说我帅");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 2);
        return s;
    }

    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet pieDataSet = new PieDataSet(entries,"软件183");
        pieDataSet.setSliceSpace(3f); //item之间的间隙
        pieDataSet.setSelectionShift(5f); //选中变化的距离

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colors);


        PieData data = new PieData(pieDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        //刷新
        pieChart.invalidate();


    }
    @Override
    protected Object requestData() {
        return Constant.STATE_SUCCEED;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        if (instance != null) {
            instance = null;
        }
        super.onDestroy();
    }

}
