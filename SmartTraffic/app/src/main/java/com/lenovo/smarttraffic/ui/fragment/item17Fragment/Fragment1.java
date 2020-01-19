package com.lenovo.smarttraffic.ui.fragment.item17Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;


public class Fragment1 extends Fragment {


    private PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1,null);

        pieChart = view.findViewById(R.id.mPieChart);

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry((float) 28.6, "有违章："));
        entries.add(new PieEntry((float) 71.3, "无违章："));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);


        pieChart.setDrawHoleEnabled(false);
        pieChart.setRotationEnabled(false); // 不可以手动旋转

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#4A92FC"));
        colors.add(Color.parseColor("#ee6e55"));

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        dataSet.setValueLineColor(R.color.Blue);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//文本外显示
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//文本外显示
        dataSet.setValueTextColor(R.color.Black);


        pieChart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);//图例显示位置
        pieChart.getLegend().setXEntrySpace(20f);
        pieChart.getLegend().setYEntrySpace(20f);
        pieChart.setDescription(null);
        pieChart.setEntryLabelColor(R.color.Black);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.highlightValues(null);

        //刷新
        pieChart.invalidate();



        return view;
    }
}
