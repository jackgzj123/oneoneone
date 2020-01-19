package com.lenovo.smarttraffic.ui.activity.T56;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lenovo.smarttraffic.R;


import java.util.ArrayList;
import java.util.List;

public class GZJT6Fragment extends Fragment {

    private TextView title;
    private LineChart lineChart;
    private String t;
    private Boolean isFirst = false;
    private List<Entry> entryList = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private LineDataSet lineDataSet;
    private LineData lineData;
    private Handler handler = new Handler(Looper.getMainLooper());

    public void setTitle(String t){
        this.t =t;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gzjt6_fragment,null);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.fragment_title);
        lineChart = view.findViewById(R.id.fragment_linechartt);
        title.setText(t);
        isFirst = true;

        initLinchart();
    }

    public void setdata(List<Integer> entry,List<String> name){

        entryList.clear();
        names.clear();
        for (int i = 0; i < entry.size(); i++) {
            entryList.add(new Entry(i,entry.get(i)));
            names.add(name.get(i));
        }

        if(isFirst){
            lineDataSet.notifyDataSetChanged();
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    lineChart.invalidate();
                }
            });

        }
    }
    private void initLinchart() {
        lineChart.setDescription(null);
        lineChart.getLegend().setEnabled(false);
        lineChart.setDrawGridBackground(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(90);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(R.color.Black);
        xAxis.setAxisLineColor(R.color.Green);
        xAxis.setLabelCount(19,false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return names.get((int) value);
            }
        });

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);

        lineDataSet = new LineDataSet(entryList,"");
        lineDataSet.setCircleColor(Color.GRAY);
        lineDataSet.setCircleColorHole(Color.GRAY);
        lineDataSet.setColor(Color.GRAY);
        lineDataSet.setCircleHoleRadius(5);
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });

        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

    }
}
