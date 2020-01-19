package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.JilvDao;
import com.lenovo.smarttraffic.ui.adapter.BasePagerAdapter;
import com.lenovo.smarttraffic.util.CommonUtil;

import butterknife.BindView;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */

public class Item1Activity extends BaseActivity {
    @BindView(R.id.tab_layout_list)
    TabLayout tabLayoutList;
    @BindView(R.id.add_channel_iv)
    ImageView addChannelIv;
    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.item1_submitbtn)
    Button mBtn;
    @BindView(R.id.item1_title)
    EditText mTiltleEd;
    @BindView(R.id.item1_myadvice)
    TextView mMyAdvice;

    private JilvDao jilvDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        InitView();
        InitData();
        initevent();
    }

    private void initevent() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_list_tab;
    }

    private void InitView() {
        jilvDao = new JilvDao(this);
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item1));
        tabLayoutList.setupWithViewPager(viewPager);
        tabLayoutList.setTabMode(TabLayout.MODE_SCROLLABLE);
        headerLayout.setBackgroundColor(CommonUtil.getInstance().getColor());



        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Time time = new Time();
                time.setToNow();
                String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                jilvDao.addMessage(mTiltleEd.getText().toString(),time_show_string);
                Toast.makeText(Item1Activity.this,"提交成功",Toast.LENGTH_SHORT).show();
            }
        });
        mMyAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item1Activity.this,JiluActivity.class));
            }
        });
    }

    private void InitData() {
        BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(basePagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        addChannelIv.setVisibility(View.VISIBLE);
        addChannelIv.setOnClickListener(view -> {
            Intent intent = new Intent(InitApp.getInstance(), ChannelActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        headerLayout.setBackgroundColor(CommonUtil.getInstance().getColor());
    }

}
