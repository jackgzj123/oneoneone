package com.lenovo.smarttraffic.ui.fragment.item19fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.FazhiBaoDao;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AnalysisFragment extends Fragment {

    private PieChart pieChart;
    private FazhiBaoDao fazhiBaoDao;

    private int shi = 0;
    private int wen = 0;
    private int co2 = 0;
    private int pm = 0;
    private int sun = 0;
    private Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fazhiBaoDao = new FazhiBaoDao(getActivity());
        pieChart = view.findViewById(R.id.analysis_piechart);

        getData();

        getchart();
        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
//        timer.schedule(new TimerTask(){
//            @Override
//            public void run(){
//                getData();
//                getchart();
//            }
//
//        },0,3000);

        return view;
    }

    private void getData() {

        cursor = fazhiBaoDao.selectCarMessage(null);
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("name")).equals("湿度")){
                shi+=1;
            }else if (cursor.getString(cursor.getColumnIndex("name")).equals("温度")){
                wen+=1;
            }else if (cursor.getString(cursor.getColumnIndex("name")).equals("光照")){
                sun+=1;
            }else if (cursor.getString(cursor.getColumnIndex("name")).equals("CO2")){
                co2+=1;
            }else if (cursor.getString(cursor.getColumnIndex("name")).equals("PM2.5")){
                pm+=1;
            }
        }
    }


    private void getchart(){

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry((float) shi, "湿度 "+shi));
        entries.add(new PieEntry((float) sun, "光照 "+sun));
        entries.add(new PieEntry((float) pm, "PM2.5 "+pm));
        entries.add(new PieEntry((float) co2, "CO2 "+co2));
        entries.add(new PieEntry((float) wen, "温度  "+wen));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);


        pieChart.setDrawHoleEnabled(false);
        pieChart.setRotationEnabled(false); // 不可以手动旋转

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.rgb(238,130,238));
        colors.add(Color.rgb(255,222,173));
        colors.add(Color.rgb(144,238,144));
        colors.add(Color.rgb(255,0,255));
        colors.add(Color.rgb(0,255,0));

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
    }


}
