package com.lenovo.smarttraffic.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.Co2Fragment;
import com.lenovo.smarttraffic.ui.fragment.DaoFragment;
import com.lenovo.smarttraffic.ui.fragment.Pm2Fragment;
import com.lenovo.smarttraffic.ui.fragment.ShiFragment;
import com.lenovo.smarttraffic.ui.fragment.SunFragment;
import com.lenovo.smarttraffic.ui.fragment.WenFragment;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity {

    final Fragment[] fragments = new Fragment[]{
        new SunFragment(),new ShiFragment(),new WenFragment(),new DaoFragment(),new Co2Fragment(),new Pm2Fragment()

    };
    private ViewPager viewPager;

    private ViewGroup mDotViewGroup;
    private List<ImageView> mDotViews = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        viewPager = findViewById(R.id.sensor_viewpager);
        mDotViewGroup = findViewById(R.id.dot_layout);



        //用FragmentAdapter适配器，通过适配器连接
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];//第二个实例 选一个返回
            }

            @Override
            public int getCount() {
                return fragments.length;//第二个实例  返回几个Fragment页面
            }
        });


        viewPager.setOffscreenPageLimit(6);//设置预加载页面数量

        viewPager.setCurrentItem(getIntent().getIntExtra("co2",0));//设置第一张显示的是第几个图片
        setDotViews(6);//实现进去的界面引导页的点为第几个
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override//页面滚动
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override//页面选中,选中哪个哪个图标就变化
            public void onPageSelected(int position) {
                setDotViews(position);//提取了方法 方便前面使用
            }

            @Override//页面被改变
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDotViews(int position) {
        for (int index = 0; index <mDotViews.size() ; index++) {
            mDotViews.get(index).setImageResource(position == index ? R.drawable.timg :R.mipmap.ic_launcher);
        }
    }
}
