package com.lenovo.smarttraffic.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment1;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment2;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment3;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment4;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment5;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment6;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment7;

import java.util.ArrayList;
import java.util.List;

public class Item17Activity extends AppCompatActivity {

    private ViewPager mViewpager;
    //private LinearLayout mDotViewGroup;

    private List<TextView> mDotViews = new ArrayList<>();

    final Fragment[] fragments = new Fragment[]{
            new Fragment1(),new Fragment2(),new Fragment3(),new Fragment4(),new Fragment5(),new Fragment6(),new Fragment7()
    };
    private RadioGroup mRadiogroup;
    private RadioButton mRbtn1;
    private RadioButton mRbtn2;
    private RadioButton mRbtn3;
    private RadioButton mRbtn4;
    private RadioButton mRbtn5;
    private RadioButton mRbtn6;
    private RadioButton mRbtn7;
    private int[] ids=new int[]{
            R.id.item17_rb1,
            R.id.item17_rb2,
            R.id.item17_rb3,
            R.id.item17_rb4,
            R.id.item17_rb5,
            R.id.item17_rb6,
            R.id.item17_rb7,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item17);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

//        for (int index = 0; index < fragments.length; index++) {
//
//                //------添加点的视图数据，实现APP引导页
//                TextView dot = new TextView(this);
//                dot.setBackgroundResource(R.drawable.edtt4);//添加点的图片
//                dot.setGravity(View.TEXT_ALIGNMENT_CENTER);
//                dot.setMaxHeight(100);//限制高度
//                dot.setMaxWidth(100);//限制宽度
//
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40,40);//点与点之间相距20，20
//                layoutParams.leftMargin=20;
//                dot.setLayoutParams(layoutParams);
//                dot.setEnabled(false);
//                mDotViewGroup.addView(dot);
//                mDotViews.add(dot);//把点加进来，方便发生页面改变的变化
//
//
//        }

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
                //setDotViews(position);//提取了方法 方便前面使用
                mRadiogroup.check(ids[position]);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.item17_rb1:
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.item17_rb2:
                        mViewpager.setCurrentItem(1);
                        break;
                    case R.id.item17_rb3:
                        mViewpager.setCurrentItem(2);
                        break;
                    case R.id.item17_rb4:
                        mViewpager.setCurrentItem(3);
                        break;
                    case R.id.item17_rb5:
                        mViewpager.setCurrentItem(4);
                        break;
                    case R.id.item17_rb6:
                        mViewpager.setCurrentItem(5);
                        break;
                    case R.id.item17_rb7:
                        mViewpager.setCurrentItem(6);
                        break;
                }
            }
        });
    }
    private void initview() {
        mViewpager = findViewById(R.id.item17_viewpager);
       // mDotViewGroup = findViewById(R.id.dot_layout);
        mRadiogroup = findViewById(R.id.item17_group);
        mRbtn1 = findViewById(R.id.item17_rb1);
        mRbtn2 = findViewById(R.id.item17_rb2);
        mRbtn3 = findViewById(R.id.item17_rb3);
        mRbtn4 = findViewById(R.id.item17_rb4);
        mRbtn5 = findViewById(R.id.item17_rb5);
        mRbtn6 = findViewById(R.id.item17_rb6);
        mRbtn7 = findViewById(R.id.item17_rb7);

    }
}
