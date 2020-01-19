package com.lenovo.smarttraffic.ui.activity.T56;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GZJt6Activity extends AppCompatActivity {

    private ViewPager mViewpager;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private SQLiteDatabase sql;
    private List<GZJT6Fragment> fragments = new ArrayList<>();
    private GZJT6Fragment wenFragment = new GZJT6Fragment();
    private GZJT6Fragment shiFragment = new GZJT6Fragment();
    private GZJT6Fragment sunFragment = new GZJT6Fragment();
    private GZJT6Fragment co2Fragment = new GZJT6Fragment();
    private GZJT6Fragment pmFragment = new GZJT6Fragment();
    private GZJT6Fragment daoFragment = new GZJT6Fragment();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzjt6);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initview();

        initevent();
    }

    private void initevent() {
        wenFragment.setTitle("温度");
        shiFragment.setTitle("湿度");
        sunFragment.setTitle("光照强度");
        co2Fragment.setTitle("Co2");
        pmFragment.setTitle("Pm2.5");
        daoFragment.setTitle("道路状态");
        fragments.add(wenFragment);
        fragments.add(shiFragment);
        fragments.add(sunFragment);
        fragments.add(co2Fragment);
        fragments.add(pmFragment);
        fragments.add(daoFragment);

        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        Intent intent = getIntent();
        if(intent!=null){
            int id = intent.getIntExtra("id", -1);
            if(id!=-1){
                mViewpager.setCurrentItem(id);
                switch (id){
                    case 0:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 1:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 2:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 3:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 4:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 5:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv1);
                        break;
                }
            }
        }

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 1:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 2:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 3:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 4:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv1);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv);
                        break;
                    case 5:
                        mTv1.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv2.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv3.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv4.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv5.setBackgroundResource(R.drawable.gzjt6_tv);
                        mTv6.setBackgroundResource(R.drawable.gzjt6_tv1);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        setData();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private List<Integer> integers1 = new ArrayList<>();
    private List<Integer> integers2 = new ArrayList<>();
    private List<Integer> integers3 = new ArrayList<>();
    private List<Integer> integers4 = new ArrayList<>();
    private List<Integer> integers5 = new ArrayList<>();
    private List<Integer> integers6 = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private SimpleDateFormat format=new SimpleDateFormat("mm:ss");
    private void setData() {
        integers1.clear();
        integers2.clear();
        integers3.clear();
        integers4.clear();
        integers5.clear();
        integers6.clear();
        name.clear();
        String s = "select * from en_tb order by time asc";
        Cursor cursor = sql.rawQuery(s, null);
        while (cursor.moveToNext()){
            integers1.add(cursor.getInt(0));
            integers2.add(cursor.getInt(1));
            integers3.add(cursor.getInt(2));
            integers4.add(cursor.getInt(3));
            integers5.add(cursor.getInt(4));
            integers6.add(cursor.getInt(5));
            name.add(format.format(new Date(cursor.getLong(6))));
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                wenFragment.setdata(integers1,name);
                shiFragment.setdata(integers2,name);
                sunFragment.setdata(integers3,name);
                co2Fragment.setdata(integers4,name);
                pmFragment.setdata(integers5,name);
                daoFragment.setdata(integers6,name);
            }
        });

    }

    private void initview() {
        sql = new Sqlhelp(this).getReadableDatabase();
        mViewpager = findViewById(R.id.gzjt6_viewpager);
        mTv1 = findViewById(R.id.gzjt6_tv1);
        mTv2 = findViewById(R.id.gzjt6_tv2);
        mTv3 = findViewById(R.id.gzjt6_tv3);
        mTv4 = findViewById(R.id.gzjt6_tv4);
        mTv5 = findViewById(R.id.gzjt6_tv5);
        mTv6 = findViewById(R.id.gzjt6_tv6);


    }
}
