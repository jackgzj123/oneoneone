package com.lenovo.smarttraffic.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.view.TimePickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Item48Activity extends AppCompatActivity implements DialogDatePicker.OnDateSetListener{

    private ImageView mLie1;
    private ImageView mLie2;
    private TextView mTv1;
    private TextView mTv2;
    private ImageView mCalender;
    private LineChart mLinechart;
    private ImageView mBack;
    private Calendar calendar = Calendar.getInstance();
    private List<Integer> yList = new ArrayList<>();
    private String[] xStringList ={"学院路","联想路","医院路","幸福路","环城快速路","环城高速"};
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item48);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

        drawLine();

    }

    private void showDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String time = ""+i+"-"+(i1+1)+"-"+i2;
                Log.e("time", "onDateSet: "+time );
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void drawLine() {
        mLinechart.setDrawBorders(false);//取消绘制chart边框的线
        mLinechart.setDrawGridBackground(false);//设置网格背景
        mLinechart.getAxisRight().setEnabled(false);

        XAxis xAxis = mLinechart.getXAxis();//获得x轴
        xAxis.setTextSize(13f);//设置x轴文字大小
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴显示位置
        xAxis.setLabelCount(5,false);//x轴坐标显示的数量
        xAxis.setDrawAxisLine(true);//显示x轴
        xAxis.setDrawGridLines(false);//是否绘制X轴的网格线
        xAxis.setTextColor(Color.rgb(95,158,160));
        xAxis.setAxisLineColor(Color.rgb(95,158,160));



        //y轴
        YAxis leftAxis = mLinechart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //左侧Y轴最大值
        leftAxis.setAxisMaximum(300f);
        //左侧Y轴最小值
        leftAxis.setAxisMinimum(0f);
        leftAxis.setXOffset(30f);


        mLinechart.setDescription(null);
        mLinechart.getLegend().setEnabled(false);

        setData();

    }
    private void setData() {

        ArrayList<Entry> values = new ArrayList<Entry>();

        yList.add(67);yList.add(254);yList.add(153);yList.add(185);yList.add(91);yList.add(64);
        for (int i = 0; i < 6; i++) {
            float val = yList.get(i);
            values.add(new Entry(i, val));
        }
        mLinechart.getXAxis().setLabelCount(5,false);//x轴坐标显示的数量
        mLinechart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                String s = xStringList[j%6];
                j++;
                return s;
            }
        });

        LineDataSet set1;
        // create a dataset and give it a type
        set1 = new LineDataSet(values, "");
        set1.setDrawIcons(false);
        set1.setColor(Color.RED);
        set1.setValueTextColor(Color.RED);
        set1.setLineWidth(1f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(12f);
        set1.setDrawFilled(false);
        set1.setFormLineWidth(1f);
        set1.setFormSize(15.f);
        set1.setCircleColor(Color.WHITE);
        set1.setDrawCircleHole(true);//设置空心
        set1.setCircleColorHole(Color.RED);
        set1.setCircleRadius(8f);
        set1.setCircleHoleRadius(5f);
        set1.setCubicIntensity(0.2f);//曲线






        // create a data object with the datasets
        LineData data = new LineData(set1);


        mLinechart.setData(data);

    }

    private void initevent() {
        mCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mLie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogDatePicker dialogDatePicker = new DialogDatePicker(Item48Activity.this,2019,12,27,1);
                dialogDatePicker.setOnSettingListener(Item48Activity.this);
                dialogDatePicker.show();
            }
        });
        mLie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogDatePicker dialogDatePicker = new DialogDatePicker(Item48Activity.this,2019,12,27,2);
                dialogDatePicker.setOnSettingListener(Item48Activity.this);
                dialogDatePicker.show();
            }
        });




    }

    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    private void initview() {
        mLie1 = findViewById(R.id.item48_liebiao1);
        mLie2 = findViewById(R.id.item48_liebiao2);
        mTv1 = findViewById(R.id.item48_tv1);
        mTv2 = findViewById(R.id.item48_tv2);
        mCalender = findViewById(R.id.item48_calendar);
        mLinechart = findViewById(R.id.item48_linechart);
        mBack = findViewById(R.id.lgb2_back);
    }

    @Override
    public void onDateSet(int i,int year, int month, int day) {
        if (i ==1 ){
            mTv1.setText(""+year+"-"+month+"-"+day);
        }else {
            mTv2.setText(""+year+"-"+month+"-"+day);
        }
    }
}
