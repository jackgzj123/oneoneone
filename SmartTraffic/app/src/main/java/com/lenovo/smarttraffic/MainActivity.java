package com.lenovo.smarttraffic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lenovo.smarttraffic.ui.activity.BaseActivity;
import com.lenovo.smarttraffic.ui.activity.Item10Activity;
import com.lenovo.smarttraffic.ui.activity.Item11Activity;
import com.lenovo.smarttraffic.ui.activity.Item11aActivity;
import com.lenovo.smarttraffic.ui.activity.Item12Activity;
import com.lenovo.smarttraffic.ui.activity.Item13Activity;
import com.lenovo.smarttraffic.ui.activity.Item13aActivity;
import com.lenovo.smarttraffic.ui.activity.Item14Activity;
import com.lenovo.smarttraffic.ui.activity.Item15Activity;
import com.lenovo.smarttraffic.ui.activity.Item16Activity;
import com.lenovo.smarttraffic.ui.activity.Item17Activity;
import com.lenovo.smarttraffic.ui.activity.Item18Activity;
import com.lenovo.smarttraffic.ui.activity.Item19Activity;
import com.lenovo.smarttraffic.ui.activity.Item1Activity;
import com.lenovo.smarttraffic.ui.activity.Item20Activity;
import com.lenovo.smarttraffic.ui.activity.Item22Activity;
import com.lenovo.smarttraffic.ui.activity.Item23Activity;
import com.lenovo.smarttraffic.ui.activity.Item24Activity;
import com.lenovo.smarttraffic.ui.activity.Item25Activity;
import com.lenovo.smarttraffic.ui.activity.Item26Activity;
import com.lenovo.smarttraffic.ui.activity.Item27Activity;
import com.lenovo.smarttraffic.ui.activity.Item28Activity;
import com.lenovo.smarttraffic.ui.activity.Item31Activity;
import com.lenovo.smarttraffic.ui.activity.Item34Activity;
import com.lenovo.smarttraffic.ui.activity.Item35Activity;
import com.lenovo.smarttraffic.ui.activity.Item36Activity;
import com.lenovo.smarttraffic.ui.activity.Item38Activity;
import com.lenovo.smarttraffic.ui.activity.Item39Activity;
import com.lenovo.smarttraffic.ui.activity.Item40Activity;
import com.lenovo.smarttraffic.ui.activity.Item41Activity;
import com.lenovo.smarttraffic.ui.activity.Item42Activity;
import com.lenovo.smarttraffic.ui.activity.Item43Activity;
import com.lenovo.smarttraffic.ui.activity.Item44Activity;
import com.lenovo.smarttraffic.ui.activity.Item45Activity;
import com.lenovo.smarttraffic.ui.activity.Item47Activity;
import com.lenovo.smarttraffic.ui.activity.Item48Activity;
import com.lenovo.smarttraffic.ui.activity.Item50Activity;
import com.lenovo.smarttraffic.ui.activity.Item51Activity;
import com.lenovo.smarttraffic.ui.activity.Item52Activity;
import com.lenovo.smarttraffic.ui.activity.T56.GZJt5Activity;
import com.lenovo.smarttraffic.ui.activity.item29activity.Item29Activity;
import com.lenovo.smarttraffic.ui.activity.Item2Activity1;
import com.lenovo.smarttraffic.ui.activity.Item3Activity;
import com.lenovo.smarttraffic.ui.activity.Item4Activity;
import com.lenovo.smarttraffic.ui.activity.Item5Activity;
import com.lenovo.smarttraffic.ui.activity.Item6Activity;
import com.lenovo.smarttraffic.ui.activity.Item7Activity;
import com.lenovo.smarttraffic.ui.activity.Item8Activity;
import com.lenovo.smarttraffic.ui.activity.Item9Activity;
import com.lenovo.smarttraffic.ui.activity.LoginActivity;
import com.lenovo.smarttraffic.ui.activity.item30activity.Item30Activity;
import com.lenovo.smarttraffic.ui.activity.item32activity.Item32Activity;
import com.lenovo.smarttraffic.ui.activity.item33activity.Item33Activity;
import com.lenovo.smarttraffic.ui.activity.item37activity.Item37Activity;
import com.lenovo.smarttraffic.ui.activity.item46activity.Item46Activity;
import com.lenovo.smarttraffic.ui.activity.item49activity.Item49Activity;
import com.lenovo.smarttraffic.ui.activity.item53activity.Item53Activity;
import com.lenovo.smarttraffic.ui.activity.item54activity.Item54Activity;
import com.lenovo.smarttraffic.ui.broadcastreceiver.IntentReceiver;
import com.lenovo.smarttraffic.ui.fragment.DesignFragment;
import com.lenovo.smarttraffic.ui.fragment.MainContentFragment;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private BroadcastReceiver receiver = new IntentReceiver();
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private MainContentFragment mMainContent;
    private DesignFragment mDesignFragment;
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomItem";
    private static final int FRAGMENT_MAIN = 0;
    private static final int FRAGMENT_DESIGN = 1;
    private BottomNavigationView bottom_navigation;
    //    private int position;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(receiver,filter);

        initView();
        initToolBar();
        if (savedInstanceState != null) {
//            loadMultipleRootFragment(R.id.container,0,mMainContent, mDesignFragment);   //使用fragmentation加载根组件
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            bottom_navigation.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_MAIN);
        }

    }

    private void showFragment(int index) {
//        position = index;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_MAIN:
                mToolbar.setTitle(R.string.title_main);
                if (mMainContent == null){
                    mMainContent = MainContentFragment.getInstance();
                    ft.add(R.id.container,mMainContent,MainContentFragment.class.getName());
                }else {
                    ft.show(mMainContent);
                }
                break;
            case FRAGMENT_DESIGN:
                mToolbar.setTitle(R.string.creative_design);
                if (mDesignFragment == null){
                    mDesignFragment = DesignFragment.getInstance();
                    ft.add(R.id.container,mDesignFragment,DesignFragment.class.getName());
                }else {
                    ft.show(mDesignFragment);
                }
                break;
        }
        ft.commit();

    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mMainContent != null) {
            ft.hide(mMainContent);
        }
        if (mDesignFragment != null) {
            ft.hide(mDesignFragment);
        }
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        CircleImageView imageView = navigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
        setSupportActionBar(mToolbar);
        imageView.setOnClickListener(this);
        /*设置选择item监听*/
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initToolBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawer.addDrawerListener(toggle);
        bottom_navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.action_main:
                    showFragment(FRAGMENT_MAIN);
                    break;
                case R.id.action_creative:
                    showFragment(FRAGMENT_DESIGN);
                    break;
            }
            return true;
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {    /*打开或关闭左边的菜单*/
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressedSupport();
            showExitDialog();
        }
    }


    /*是否退出项目*/
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", (dialogInterface, i) -> InitApp.getInstance().exitApp());
        builder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        /*设置选中item事件*/
        int id = item.getItemId();
        String string = null;
        switch (id){
            case R.id.nav_account:
                string = "个人";
                break;
            case R.id.item_1:
                string = "item1";
                startActivity(new Intent(this, Item1Activity.class));
                break;
            case R.id.item_2:
                string = "item2";
                startActivity(new Intent(this, Item2Activity1.class));
                break;
            case R.id.item_3:
                string = "item3";
                startActivity(new Intent(this, Item3Activity.class));
                break;
            case R.id.item_4:
                string = "我的账户";
                startActivity(new Intent(this, Item4Activity.class));
                break;
            case R.id.item_5:
                string = "账单管理";
                startActivity(new Intent(this, Item5Activity.class));
                break;
            case R.id.nav_setting:
                string = "设置";
                break;
            case R.id.nav_about:
                string = "关于";
                break;
            case R.id.item_6:
                string = "红绿灯管理";
                startActivity(new Intent(this, Item6Activity.class));
                break;
            case R.id.item_7:
                string = "车辆违章";
                startActivity(new Intent(this, Item7Activity.class));
                break;
            case R.id.item_8:
                string = "环境指标";
                startActivity(new Intent(this, GZJt5Activity.class));
                break;
            case R.id.item_9:
                string = "阀值设置";
                startActivity(new Intent(this, Item9Activity.class));
                break;
            case R.id.item_10:
                string = "出行管理";
                startActivity(new Intent(this, Item10Activity.class));
                break;
            case R.id.item_11:
                string = "账户管理";
                startActivity(new Intent(this, Item11Activity.class));
                break;
            case R.id.item_12:
                string = "公交查询";
                startActivity(new Intent(this, Item12Activity.class));
                break;
            case R.id.item_13:
                string = "红路灯管理系统";
                startActivity(new Intent(this, Item13aActivity.class));
                break;
            case R.id.item_14:
                string = "车辆违章系统";
                startActivity(new Intent(this, Item14Activity.class));
                break;
            case R.id.item_15:
                string = "路况查询";
                startActivity(new Intent(this, Item15Activity.class));
                break;
            case R.id.item_16:
                string = "生活助手";
                startActivity(new Intent(this, Item16Activity.class));
                break;
            case R.id.item_17:
                string = "数据分析";
                startActivity(new Intent(this, Item17Activity.class));
                break;
            case R.id.item_18:
                string = "生活指数";
                startActivity(new Intent(this, Item18Activity.class));
                break;
            case R.id.item_19:
                string = "我的消息";
                startActivity(new Intent(this, Item19Activity.class));
                break;
            case R.id.item_11a:
                string = "账户管理";
                startActivity(new Intent(this, Item11aActivity.class));
                break;
            case R.id.item_20:
                string = "账户设置";
                startActivity(new Intent(this, Item20Activity.class));
                break;
            case R.id.item_22:
                string = "路况查询";
                startActivity(new Intent(this, Item22Activity.class));
                break;
            case R.id.item_23:
                string = "生活助手1";
                startActivity(new Intent(this, Item23Activity.class));
                break;
            case R.id.item_24:
                string = "公交查询1";
                startActivity(new Intent(this, Item24Activity.class));
                break;
            case R.id.item_25:
                string = "实时环境";
                startActivity(new Intent(this, Item25Activity.class));
                break;
            case R.id.item_26:
                string = "违章视频";
                startActivity(new Intent(this, Item26Activity.class));
                break;
            case R.id.item_27:
                string = "地铁查询";
                startActivity(new Intent(this, Item27Activity.class));
                break;
            case R.id.item_28:
                string = "高速路况";
                startActivity(new Intent(this, Item28Activity.class));
                break;
            case R.id.item_29:
                string = "高速ETC";
                startActivity(new Intent(this, Item29Activity.class));
                break;
            case R.id.item_30:
                string = "旅行信息";
                startActivity(new Intent(this, Item30Activity.class));
                break;
            case R.id.item_31:
                string = "天气信息";
                startActivity(new Intent(this, Item31Activity.class));
                break;
            case R.id.item_32:
                string = "二维码支付";
                startActivity(new Intent(this, Item32Activity.class));
                break;
            case R.id.item_33:
                string = "定制班车";
                startActivity(new Intent(this, Item33Activity.class));
                break;

            case R.id.item_34:
                string = "新闻媒体";
                startActivity(new Intent(this, Item34Activity.class));
                break;
            case R.id.item_35:
                string = "IC卡充值";
                startActivity(new Intent(this, Item35Activity.class));
                break;
            case R.id.item_36:
                string = "车辆收费查询";
                startActivity(new Intent(this, Item36Activity.class));
                break;
            case R.id.item_37:
                string = "小车充值";
                startActivity(new Intent(this, Item37Activity.class));
                break;
            case R.id.item_38:
                string = "我的座驾";
                startActivity(new Intent(this, Item38Activity.class));
                break;
            case R.id.item_39:
                string = "我的交通";
                startActivity(new Intent(this, Item39Activity.class));
                break;
            case R.id.item_40:
                string = "WEBVIEW";
                startActivity(new Intent(this, Item40Activity.class));
                break;
            case R.id.item_41:
                string = "路况分析";
                startActivity(new Intent(this, Item41Activity.class));
                break;
            case R.id.item_42:
                string = "离线地图";
                startActivity(new Intent(this, Item42Activity.class));
                break;
            case R.id.item_43:
                string = "日志查询";
                startActivity(new Intent(this, Item43Activity.class));
                break;
            case R.id.item_44:
                string = "用户管理";
                startActivity(new Intent(this, Item44Activity.class));
                break;
            case R.id.item_45:
                string = "公交查询";
                startActivity(new Intent(this, Item45Activity.class));
                break;
            case R.id.item_46:
                string = "实时交通";
                startActivity(new Intent(this, Item46Activity.class));
                break;
            case R.id.item_47:
                string = "环境监测";
                startActivity(new Intent(this, Item47Activity.class));
                break;
            case R.id.item_48:
                string = "违章分析";
                startActivity(new Intent(this, Item48Activity.class));
                break;
            case R.id.item_49:
                string = "车辆违章1";
                startActivity(new Intent(this, Item49Activity.class));
                break;
            case R.id.item_50:
                string = "添加订阅";
                startActivity(new Intent(this, Item50Activity.class));
                break;
            case R.id.item_51:
                string = "天气信息1";
                startActivity(new Intent(this, Item51Activity.class));
                break;
            case R.id.item_52:
                string = "违章类型分析";
                startActivity(new Intent(this, Item52Activity.class));
                break;
            case R.id.item_53:
                string = "生活助手";
                startActivity(new Intent(this, Item53Activity.class));
                break;
            case R.id.item_54:
                string = "高速ETC 1";
                startActivity(new Intent(this, Item54Activity.class));
                break;
        }
        if (!TextUtils.isEmpty(string))
        Toast.makeText(InitApp.getInstance(), "你点击了"+"\""+string+"\"", Toast.LENGTH_SHORT).show();
//        mDrawer.closeDrawer(GravityCompat.START);
        mDrawer.closeDrawers();
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.ivAvatar){    /*点击头像跳转登录界面*/
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        mDrawer.closeDrawer(GravityCompat.START);
    }


}
