package com.lenovo.smarttraffic.ui.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.lenovo.smarttraffic.R;

public class Item42Activity extends AppCompatActivity {

    private MapView mMapView ;
    private AMap aMap ;
    private TextView mTV;
    private ImageView mQiehuan;
    private ImageView mCarDing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item42);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //获取地图控件引用
        mMapView = findViewById(R.id.map);
        mTV = findViewById(R.id.item42_tv);
        mQiehuan = findViewById(R.id.item42_qiehuan);
        mCarDing = findViewById(R.id.item42_cardiwei);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        mCarDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dingwei();


                mTV.setText("1、2、3、4号小车标记已完成");
            }
        });
        mQiehuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });

        //标注
        AMap.OnInfoWindowClickListener listener = new AMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {

                arg0.setTitle("infowindow clicked");
            }
        };
        //绑定信息窗点击事件
        aMap.setOnInfoWindowClickListener(listener);
    }

    private void dingwei() {

        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(new LatLng(39.975403,116.318693));
        markerOption.title("1号小车").snippet("北京联想大厦联区");
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.mipmap.marker_one));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果


        MarkerOptions markerOption1 = new MarkerOptions();
        markerOption1.position(new LatLng(39.865948,116.356062));
        markerOption1.title("2号小车").snippet("北京联想大厦联区");
        markerOption1.draggable(true);//设置Marker可拖动
        markerOption1.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.marker_second)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption1.setFlat(true);//设置marker平贴地图效果

        MarkerOptions markerOption2 = new MarkerOptions();
        markerOption2.position(new LatLng(39.790145,116.266376));
        markerOption2.title("3号小车").snippet("北京联想大厦联区");
        markerOption2.draggable(true);//设置Marker可拖动
        markerOption2.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.marker_thrid)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption2.setFlat(true);//设置marker平贴地图效果

        MarkerOptions markerOption3 = new MarkerOptions();
        markerOption3.position(new LatLng(39.86329,116.453223));
        markerOption3.title("4号小车").snippet("北京联想大厦联区");
        markerOption3.draggable(true);//设置Marker可拖动
        markerOption3.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.marker_forth)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption3.setFlat(true);//设置marker平贴地图效果

        aMap.addMarker(markerOption);
        aMap.addMarker(markerOption1);
        aMap.addMarker(markerOption2);
        aMap.addMarker(markerOption3);
    }

    public interface InfoWindowAdapter {
        View getInfoWindow(Marker marker);
        View getInfoContents(Marker marker);
    }
    /**
     * 监听自定义infowindow窗口的infocontents事件回调
     */
    public View getInfoContents(Marker marker) {
        return null;
    //示例没有采用该方法。
    }
    View infoWindow = null;

    /**
     * 监听自定义infowindow窗口的infowindow事件回调
     */
    public View getInfoWindow(Marker marker) {
        if(infoWindow == null) {
            infoWindow = LayoutInflater.from(this).inflate(
                    R.layout.custom_info_window, null);
        }
        render(marker, infoWindow);
        return infoWindow;
    //加载custom_info_window.xml布局文件作为InfoWindow的样式
    //该布局可在官方Demo布局文件夹下找到
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
    //如果想修改自定义Infow中内容，请通过view找到它并修改
    }

    public void showPopupWindow(View v){
        //准备弹窗所需要的视图对象
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow,null);
        //1、实例化PopupWindow对象
        // 参数1：用在弹窗中的view
        // 参数2、3：弹窗的宽高
        //参数4focusable：能否获取焦点
        final PopupWindow window = new PopupWindow(view,400,1000,true);
        //2、设置(背景，动画)
        //设置背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置为透明色的背景,很重要！！！
        //设置能响应外部的点击事件
        window.setOutsideTouchable(true );//很重要！！！
        //设置能响应的点击事件
        window.setTouchable(true);
        //①创建动画资源 ②创建一个style动画应用资源  三 对当前的弹窗风格设置为第二部的资源索引
        //window.setAnimationStyle(R.style.translate_anim);
        //3、显示  参数1：anchor：锚  参数2、3：相对于锚在x、y方向上的偏移量 ,只对左上角的坐标
        window.showAsDropDown(v,-400,0);
        //为弹窗中的文本设置点击事件
        view.findViewById(R.id.putong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV.setText("已切换到普通地图");
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                window.dismiss();
            }
        });
        view.findViewById(R.id.yejing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV.setText("已切换到夜景地图");
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                window.dismiss();
            }
        });
        view.findViewById(R.id.weixing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV.setText("已切换到卫星地图");
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                window.dismiss();
            }
        });
        view.findViewById(R.id.daohang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTV.setText("已切换到导航地图");
                aMap.setMapType(AMap.MAP_TYPE_NAVI);
                window.dismiss();
            }
        });
        view.findViewById(R.id.lukuang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mTV.setText("已切换到路况地图");
                aMap.setMapType(AMap.MAP_TYPE_BUS);
                window.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
