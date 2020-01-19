package com.lenovo.smarttraffic.ui.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.EnvironmentDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class Pm2Fragment extends Fragment {

    private EnvironmentDao environmentDao ;
    private LineChart mLineChar;
    private LineDataSet set1;

    private List<String> pmlist = new ArrayList<>();
    private List<String> timelist = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pm2,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        environmentDao = new EnvironmentDao(getActivity());
        mLineChar = (LineChart) view.findViewById(R.id.mLineChar);


        //todo 获得数据
        Cursor cursor = environmentDao.selectCarMessage();
        while (cursor.moveToNext()){
            String sun = cursor.getString(cursor.getColumnIndex("pm2"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            pmlist.add(sun);
            timelist.add(time);
        }




        mLineChar.setDescription(null);
        mLineChar.getLegend().setEnabled(false);
        //显示边界
        mLineChar.setDrawBorders(false);

        mLineChar.animateX(3000);

        YAxis leftYAxis = mLineChar.getAxisLeft();

        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(1000f);


        final String[] values = new String[50];
        for(int i=0;i<timelist.size();i++) {
            if (timelist.get(i)==null){
                values[i] = "0";
            }else {
                values[i] = timelist.get(i);
            }
        }
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return values[(int) value+1];
            }

        };

        XAxis xAxis = mLineChar.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        //X轴显示的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLabels(true);
        xAxis.setLabelRotationAngle(90);
        //获取右边的轴线
        YAxis rightAxis=mLineChar.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);


        List<ILineDataSet> list_get=new ArrayList<>();
        ArrayList<Entry> yVals=new ArrayList<Entry>();
        for(int i=0;i<pmlist.size();i++) {
            if (pmlist.get(i)==null){
                yVals.add(new Entry(i,0));
            }else {
                float s = Float.parseFloat(pmlist.get(i));
                yVals.add(new Entry(i,s));
            }
        }

        //一个LineDataSet就是一条线
        LineDataSet set1 = new LineDataSet(yVals, "PM2");
        set1.setColor(Color.rgb(192,192,192));
        set1.setDrawCircleHole(false);
        set1.setCircleColor(Color.rgb(192,192,192));
        list_get.add(set1);
        LineData data = new LineData(list_get);
        mLineChar.setData(data);

        return view;
    }
}
