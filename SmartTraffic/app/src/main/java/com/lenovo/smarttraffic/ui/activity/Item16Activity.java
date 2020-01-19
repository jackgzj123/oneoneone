package com.lenovo.smarttraffic.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.EnvironmentData;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.BarLineFragment;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.Co22Fragment;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.WenduFragment;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.XiangduishiduFragment;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item16Activity extends AppCompatActivity {

    private TextView mWen;
    private ImageView mShuaxin;
    private LineChart mLinChart;
    private TextView mSun;
    private TextView mSunMsg;
    private TextView mGanmao;
    private TextView mGanmaoMsg;
    private TextView mChuanyi;
    private TextView mChuanyiMsg;
    private TextView mCo2;
    private TextView mCo2Msg;
    private TextView mPm2;
    private TextView mPm2Msg;

    private ArrayList<Entry> yVals1;
    private ArrayList<Entry> yVals2;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;

    private ViewPager mViewpager;
    private ViewGroup mDotViewGroup;
    private List<TextView> mDotViews = new ArrayList<>();

    final Fragment[] fragments = new Fragment[]{
            new BarLineFragment(),new WenduFragment(),new XiangduishiduFragment(),new Co22Fragment()
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item16);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();
        getNet();
        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                getNet();
            }

        },0,3000);


        setData();
        initevent();

        //画图 天气图
        setLineChart();

        for (int index = 0; index < fragments.length; index++) {

            if (index == 0){
                //------添加点的视图数据，实现APP引导页
                TextView dot = new TextView(this);
                dot.setText("空气质量");//添加点的图片
                dot.setGravity(View.TEXT_ALIGNMENT_CENTER);
//                dot.setMaxHeight(100);//限制高度
//                dot.setMaxWidth(100);//限制宽度

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150,40);//点与点之间相距20，20
                layoutParams.leftMargin=20;
                dot.setLayoutParams(layoutParams);
                dot.setEnabled(false);
                mDotViewGroup.addView(dot);
                mDotViews.add(dot);//把点加进来，方便发生页面改变的变化
            }else if (index == 1){
                TextView dot = new TextView(this);
                dot.setText("温度");//添加点的图片
                dot.setGravity(View.TEXT_ALIGNMENT_CENTER);
                dot.setMaxHeight(100);//限制高度
                dot.setMaxWidth(100);//限制宽度

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150,40);//点与点之间相距20，20
                layoutParams.leftMargin=20;
                dot.setLayoutParams(layoutParams);
                dot.setEnabled(false);
                mDotViewGroup.addView(dot);
                mDotViews.add(dot);//把点加进来，方便发生页面改变的变化
            }else if (index == 2){
                TextView dot = new TextView(this);
                dot.setText("相对湿度");//添加点的图片
                dot.setMaxHeight(100);//限制高度
                dot.setMaxWidth(100);//限制宽度
                dot.setGravity(View.TEXT_ALIGNMENT_CENTER);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150,40);//点与点之间相距20，20
                layoutParams.leftMargin=20;
                dot.setLayoutParams(layoutParams);
                dot.setEnabled(false);
                mDotViewGroup.addView(dot);
                mDotViews.add(dot);//把点加进来，方便发生页面改变的变化
            }else {
                TextView dot = new TextView(this);
                dot.setText("二氧化碳");//添加点的图片
                dot.setMaxHeight(100);//限制高度
                dot.setMaxWidth(100);//限制宽度
                dot.setGravity(View.TEXT_ALIGNMENT_CENTER);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150,40);//点与点之间相距20，20
                layoutParams.leftMargin=20;
                dot.setLayoutParams(layoutParams);
                dot.setEnabled(false);
                mDotViewGroup.addView(dot);
                mDotViews.add(dot);//把点加进来，方便发生页面改变的变化
            }
        }


    }

    private void setLineChart() {

        mLinChart.setDrawBorders(false);//取消绘制chart边框的线
        mLinChart.setDrawGridBackground(false);//设置网格背景

        //x轴
        XAxis xAxis = mLinChart.getXAxis();//获得x轴
        xAxis.setTextSize(15f);//设置x轴文字大小
        xAxis.setPosition(XAxis.XAxisPosition.TOP);//设置x轴显示位置
        xAxis.setTextColor(Color.parseColor("#00bcd4"));//设置x轴文字颜色
        xAxis.setLabelCount(5);//x轴坐标显示的数量
        xAxis.setDrawAxisLine(false);//不显示x轴
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        //设置x轴数据
        final Map<Integer, String> xMap = new HashMap<>();
        final String[] valueArry = {"昨天", "今天", "明天", "周五", "周六","周日"};
        for (int i = 0; i < yVals1.size(); i++) {
            xMap.put((int) yVals1.get(i).getX(), valueArry[i]);
        }
        //自定义x轴标签数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xMap.get((int)value);
            }
        });

        //y轴
        YAxis leftAxis = mLinChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);
        //leftAxis.setEnabled(false);
        // 强制显示Y轴6个坐标
        leftAxis.setLabelCount(5, true);
        //左侧Y轴最大值
        leftAxis.setAxisMaximum(30f);
        //左侧Y轴最小值
        leftAxis.setAxisMinimum(12f);
        //右侧Y轴坐标
        YAxis rightAxis = mLinChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setEnabled(false);

        mLinChart.setDescription(null);
        mLinChart.getLegend().setEnabled(false);

    }

    private void setData(){

        yVals1 = new ArrayList<Entry>();
        yVals1.add(new Entry(0,22));
        yVals1.add(new Entry(1,24));
        yVals1.add(new Entry(2,25));
        yVals1.add(new Entry(3,25));
        yVals1.add(new Entry(4,25));
        yVals1.add(new Entry(5,23));

        yVals2 = new ArrayList<Entry>();
        yVals2.add(new Entry(0,14));
        yVals2.add(new Entry(1,15));
        yVals2.add(new Entry(2,16));
        yVals2.add(new Entry(3,17));
        yVals2.add(new Entry(4,16));
        yVals2.add(new Entry(5,16));

        LineDataSet set1;
        set1 = new LineDataSet(yVals1,"数据1");
        //数据对应的是左边还是右边的Y值
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //线条的颜色
        set1.setColor(Color.rgb(178,34,34));
        //表中数据圆点的颜色
        set1.setCircleColor(Color.rgb(178,34,34));
        //绘制的数据的圆点是否是实心还是空心
        set1.setDrawCircleHole(false);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.2f);//设置曲线的弯曲程度

        LineDataSet set2;
        set2 = new LineDataSet(yVals2,"数据2");

        //线条的颜色
        set2.setColor(Color.rgb(65,105,225));
        //表中数据圆点的颜色
        set2.setCircleColor(Color.rgb(65,105,225));
        //绘制的数据的圆点是否是实心还是空心
        set2.setDrawCircleHole(false);
        set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set2.setCubicIntensity(0.3f);//设置曲线的弯曲程度

        LineData data = new LineData(set1,set2);
        //data.setValueTextColor(Color.RED);
        //设置图标中显示数字的大小
        data.setValueTextSize(9f);
        // set data
        mLinChart.setData(data);

    }

    private void initevent() {

        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setDotViews(position);//提取了方法 方便前面使用
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDotViews(int position) {
        for (int index = 0; index <mDotViews.size() ; index++) {
            if (position==index){
                mDotViews.get(index).setBackgroundResource(R.drawable.edtt);
            }else {
                mDotViews.get(index).setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
    }
    private void getNet() {
        //todo get请求
        mOkhttp.newCall(new Request.Builder().url(UrlClass.ENVIRONMENT_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                EnvironmentData environmentData = gson.fromJson(jsonData, EnvironmentData.class);

                final String pm = environmentData.getPm();
                final String shi = environmentData.getHumidity();
                final String wen = environmentData.getTemperature();

                final String sun = environmentData.getLightIntensity();
                final String co2 = environmentData.getCo2();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWen.setText(wen+"°");
                        if (Integer.valueOf(sun)>3000){
                            mSun.setText("强("+sun+")");
                            mSunMsg.setText("尽量减少外出，需要涂抹高倍数防晒霜");
                        }else if (Integer.valueOf(sun)<1000){
                            mSun.setText("弱("+sun+")");
                            mSunMsg.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                        }else {
                            mSun.setText("中等("+sun+")");
                            mSunMsg.setText("涂擦SPF大于15、PA+防晒护肤品");
                        }

                        if (Integer.valueOf(wen)<8){
                            mGanmao.setText("较易发("+wen+")");
                            mGanmaoMsg.setText("温度低，风较大，较易发生感冒，注意防护");
                        }else {
                            mGanmao.setText("少发("+wen+")");
                            mGanmaoMsg.setText("无明显降温，感冒机率较低");
                        }

                        if (Integer.valueOf(wen)>21){
                            mChuanyi.setText("热("+wen+")");
                            mChuanyiMsg.setText("适合穿T恤、短薄外套等夏季服装");
                        }else if (Integer.valueOf(wen)<12){
                            mChuanyi.setText("冷("+wen+")");
                            mChuanyiMsg.setText("建议穿长袖衬衫、单裤等服装");
                        }else {
                            mChuanyi.setText("舒适("+wen+")");
                            mChuanyiMsg.setText("建议穿短袖衬衫、单裤等服装");
                        }

                        if (Integer.valueOf(co2)>6000){
                            mCo2.setText("较不宜("+co2+")");
                            mCo2Msg.setText("空气氧气含量低，请在室内进行休闲运动");
                        }else if (Integer.valueOf(co2)<3000){
                            mCo2.setText("适宜("+co2+")");
                            mCo2Msg.setText("气候适宜，推荐您进行户外运动");
                        }else {
                            mCo2.setText("中("+co2+")");
                            mCo2Msg.setText("易感人群应适当减少室外活动");
                        }

                        if (Integer.valueOf(pm)>100){
                            mPm2.setText("污染("+pm+")");
                            mPm2Msg.setText("空气质量差，不适合户外活动");
                        }else if (Integer.valueOf(pm)<30){
                            mPm2.setText("优("+pm+")");
                            mPm2Msg.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                        }else {
                            mPm2.setText("良("+pm+")");
                            mPm2Msg.setText("易感人群应适当减少室外活动");
                        }


                    }
                });
            }
        });
    }



    private void initview() {
        mWen = findViewById(R.id.item16_wen);
        mShuaxin = findViewById(R.id.item16_shuaxin);
        mLinChart = findViewById(R.id.item16_linechart);
        mSun = findViewById(R.id.item16_sun);
        mSunMsg = findViewById(R.id.item16_sunmsg);
        mGanmao = findViewById(R.id.item16_ganmao);
        mGanmaoMsg = findViewById(R.id.item16_ganmaomsg);
        mChuanyi = findViewById(R.id.item16_chuanyi);
        mChuanyiMsg = findViewById(R.id.item16_chuanyimsg);
        mCo2 = findViewById(R.id.item16_co2);
        mCo2Msg = findViewById(R.id.item16_co2msg);
        mPm2 = findViewById(R.id.item16_pm2);
        mPm2Msg = findViewById(R.id.item16_pm2msg);
        mViewpager = findViewById(R.id.item16_viewpager);
        mDotViewGroup = findViewById(R.id.dot_layout);
    }
}
