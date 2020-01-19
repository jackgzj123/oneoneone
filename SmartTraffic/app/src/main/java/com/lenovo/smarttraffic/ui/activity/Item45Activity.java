package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item45fragment.OneFragment;
import com.lenovo.smarttraffic.ui.fragment.item45fragment.TwoFragment;

public class Item45Activity extends AppCompatActivity implements TabHost.TabContentFactory{

    private TabHost mtabHost;
    private ViewPager viewPager;
    private OneFragment oneFragment = new OneFragment();
    private TwoFragment twoFragment = new TwoFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item45);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();
    }

    private void initview() {
        mtabHost = findViewById(R.id.tab_host);
        mtabHost.setup();//初始化

        //两个Tab 做处理
        //1.初始化Tab的数据
        String[] titleIDs = {"1号站台", "2号站台"};
        int[] drawableIDs = {R.drawable.oneitem, R.drawable.twoitem};

        //2.data与view 结合
        for (int index = 0; index < titleIDs.length; index++) {
            View view = getLayoutInflater().inflate(R.layout.main_tab_layout, null, false);//创建视图

            TextView title = view.findViewById(R.id.main_tab_txt);//设置文本
            View tab = view.findViewById(R.id.tab_bg);
            LinearLayout linearLayout = view.findViewById(R.id.main_content);
            //icon.setImageResource(drawableIDs[index]);
            title.setText(titleIDs[index]);
            tab.setBackgroundResource(drawableIDs[index]);
            mtabHost.addTab(//添加标签页
                    mtabHost.newTabSpec(titleIDs[index])//创建标签
                            .setIndicator(view)//设置标题
                            .setContent(this)//设置当前视图的内容  实现的接口  设置选项卡内容 可以设置Activity, 也可以设置Fragement

            );//
        }

        final Fragment[] fragments = new Fragment[]{oneFragment,twoFragment};
        viewPager = findViewById(R.id.view_pager1);
        //用FragmentAdapter适配器，通过适配器连接
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return fragments[position];//第二个实例 选一个返回
            }

            @Override
            public int getCount() {
                //return a4; //第一个实例
                return fragments.length;//第二个实例  返回几个Fragment页面
            }
        });
//---------------------viewpager连接底部切换导航,实现app导航与图片连接  联动效果
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(mtabHost != null){
                    mtabHost.setCurrentTab(position);//设置当前的tab指向哪个


                    if (position == 0) {//选中
                        TextView textView = mtabHost.getTabWidget().getChildTabViewAt(0).findViewById(R.id.main_tab_txt);
                        textView.setTextColor(Color.rgb(255,255,255));
                        TextView textView1 = mtabHost.getTabWidget().getChildTabViewAt(1).findViewById(R.id.main_tab_txt);
                        textView1.setTextColor(Color.rgb(0,0,0));
                    } else {//不选中
                        TextView textView = mtabHost.getTabWidget().getChildTabViewAt(1).findViewById(R.id.main_tab_txt);
                        textView.setTextColor(Color.rgb(255,255,255));
                        TextView textView1 = mtabHost.getTabWidget().getChildTabViewAt(0).findViewById(R.id.main_tab_txt);
                        textView1.setTextColor(Color.rgb(0,0,0));
                    }

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mtabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (mtabHost != null){
                    int position = mtabHost.getCurrentTab();//获取当前的位置
                    viewPager.setCurrentItem(position);//显示当前位置的视图
                }
            }
        });

    }

    @Override
    public View createTabContent(String s) {
        View view = new View(this);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    }
}
