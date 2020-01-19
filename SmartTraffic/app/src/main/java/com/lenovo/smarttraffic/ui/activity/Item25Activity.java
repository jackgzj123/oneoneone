package com.lenovo.smarttraffic.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment1;
import com.lenovo.smarttraffic.ui.fragment.item25fragment.Item25SunFragment;
import com.lenovo.smarttraffic.ui.fragment.item25fragment.Item25WenFragment;
import com.lenovo.smarttraffic.ui.fragment.item25fragment.PmFragment;


import java.util.ArrayList;
import java.util.List;

public class Item25Activity extends AppCompatActivity {
    private ViewPager mViewpager;
    private LinearLayout mDotViewGroup;

    private List<TextView> mDotViews = new ArrayList<>();

    final Fragment[] fragments = new Fragment[]{
            new PmFragment(),new Item25SunFragment(),new Item25WenFragment()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item25);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

        for (int index = 0; index < fragments.length; index++) {

            //------添加点的视图数据，实现APP引导页
            TextView dot = new TextView(this);
            dot.setBackgroundResource(R.drawable.edtt4);//添加点的图片
            dot.setGravity(View.TEXT_ALIGNMENT_CENTER);
            dot.setMaxHeight(100);//限制高度
            dot.setMaxWidth(100);//限制宽度

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40,40);//点与点之间相距20，20
            layoutParams.leftMargin=20;
            dot.setLayoutParams(layoutParams);
            dot.setEnabled(false);
            mDotViewGroup.addView(dot);
            mDotViews.add(dot);//把点加进来，方便发生页面改变的变化


        }

    }


    private void setDotViews(int position) {
        for (int index = 0; index <mDotViews.size() ; index++) {
            if (position==index){
                mDotViews.get(index).setBackgroundResource(R.drawable.edtt3);
            }else {
                mDotViews.get(index).setBackgroundResource(R.drawable.edtt4);
            }
        }
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
    private void initview() {
        mViewpager = findViewById(R.id.item25_viewpager);
        mDotViewGroup = findViewById(R.id.dot_layout);

    }
}
