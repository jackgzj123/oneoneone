package com.lenovo.smarttraffic.bean;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.data.CarData;
import com.lenovo.smarttraffic.database.data.Data;
import com.lenovo.smarttraffic.ui.activity.EventBusCarrier;
import com.lenovo.smarttraffic.ui.activity.Item11Activity;
import com.lenovo.smarttraffic.ui.activity.Item3Activity;
import com.lenovo.smarttraffic.ui.activity.MyDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/21
 */
public class TotalClass {

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private String s1;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    //todo 获取当前的日期 和 时间   month要+1
    private void getTime(){
        Time time = new Time();
        time.setToNow();
        String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
    }
//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
//    format.format(new Date(System.currentTimeMillis()));
    //todo 获取星期几
    /*
    final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
    String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
    String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
    String mDay1 = String.valueOf(c.get(Calendar.DAY_OF_MONTH)+1);// 获取当前月份的日期号码
    String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK)+1);
        if("1".equals(mWay)){
        mWay ="天";
    }else if("2".equals(mWay)){
        mWay ="一";
    }else if("3".equals(mWay)){
        mWay ="二";
    }else if("4".equals(mWay)){
        mWay ="三";
    }else if("5".equals(mWay)){
        mWay ="四";
    }else if("6".equals(mWay)){
        mWay ="五";
    }else if("7".equals(mWay)){
        mWay ="六";
    }
     */

    //todo 隐藏状态栏     getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    //todo 状态栏发送消息
    /*
    private void setmessage(){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(Item3Activity.this, "chat")
                .setAutoCancel(true)
                .setContentTitle("收到警告消息")
                .setContentText("车辆号 "+name+" 余额:"+i+" 阈值:"+j1)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                //在build()方法之前还可以添加其他方法
                .build();
        manager.notify(1, notification);
    }
    */

    //todo okhttp请求
    private void postnet(String s){
        //todo post请求
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("carId",s);
        FormBody formBody = builder.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                //s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                       // mYueTv.setText(s1);
                    }
                });
            }
        });

        //todo get请求
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.ETC_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
//                gson = new Gson();
//                carDatalist = gson.fromJson(jsonData, new TypeToken<List<CarData>>() {}.getType());

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {



                    }
                });
            }
        });
    }


    //todo Spinner列表框的控件

    /**
    final ArrayList<String> arrayList = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
    //添加要显示的内容
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");

    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);//创建适配器  this--上下文  android.R.layout.simple_spinner_item--显示的模板   arrayList--显示的内容
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置下拉之后的布局的样式 这里采用的是系统的一个布局
        mSpinner.setAdapter(arrayAdapter);//将适配器给下拉框
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//当改变下拉框的时候会触发

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//改变内容的时候
            s=arrayList.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

        }
    });
     ***/

    //todo 数据库的适配器
    /*
   private void  getAdapter(){

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,R.layout.item3, cursor,
                new String[]{"_id","name","chong","operator","time"},//表头
                new int[]{R.id.xuhao_item,R.id.name_item,R.id.chong_item,R.id.people_item,R.id.time_item},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
 }
 */

    //todo 动画的设置
        /*
         AnimationDrawable animaition = (AnimationDrawable)mRedImg.getBackground();
        animaition.setOneShot(false);   //设置是否只播放一次，和上面xml配置效果一致
        animaition.start();             //启动动画
         */

    //todo 自定义对话框的使用及回调
    /*
   MyDialog dialog = new MyDialog(Item11Activity.this,R.style.mydialog,carnamelist);
        dialog.setOnSettingListener(Item11Activity.this);
        dialog.show();
     */

    //todo LineChart Item16Acitivity
    /*

    // 触摸
    mLineChart.setTouchEnabled(true);

    // 拖拽
    mLineChart.setDragEnabled(true);

    // 缩放
    mLineChart.setScaleEnabled(true);


    //自定义x轴标签数据
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xMap.get((int)value);
            }
        });

        mLinChart.setDrawBorders(false);//取消绘制chart边框的线
        mLinChart.setDrawGridBackground(false);//设置网格背景

        xAxis.setLabelCount(5);//x轴坐标显示的数量 5就是6个数据

        mLinChart.setDescription(null);//描述
        mLinChart.getLegend().setEnabled(false);//取消图例

        '//①
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
        set1.setDrawCircleHole(true);//设置空心
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置成圆滑曲线
        set1.setCubicIntensity(0.2f);//设置曲线的弯曲程度

        //②
        LineData data = new LineData(set1,set2);
        //data.setValueTextColor(Color.RED);
        //设置图标中显示数字的大小
        data.setValueTextSize(9f);
        //③ set data
        mLinChart.setData(data);

     */
    //todo 图表刷新
    /*
        xnames.clear();
        entries.clear();
        然后添加新的数据
        先判断是否是第一次 if（isFirst）{
        lineDataSet.notifyDataSetChange();
        lineData.notifyDataChanged();
        line.notifyDataSetChanged();
        line.invalidate();
        }

     */

    //todo 生成二维码
    /*

        try {
            str = "车辆编号"+carid+",付费金额="+paymoney+"元";
            Map<EncodeHintType, Object> hints = null;
            String encoding = getEncoding(str);//获取字符编码
            if (encoding != null) {
                hints = new EnumMap<>(EncodeHintType.class);
                hints.put(EncodeHintType.CHARACTER_SET, encoding);
            }
            BitMatrix result=new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,350,350,hints);//通过字符串创建二维矩阵
            int width = result.getWidth();
            int height = result.getHeight();
            int[] pixels = new int[width * height];

            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;//根据二维矩阵数据创建数组
                }
            }

            //创建位图
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);//将数组加载到位图中
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);//显示二维码
                }
            });
        } catch (WriterException e) {
            e.printStackTrace();
        }


         private static String getEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
     */

    //todo 图片点击放大缩小
    /*
     //展示在dialog上面的大图
        dialog = new Dialog(Item32MsgActivity.this,R.style.FullActivity);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);

        image = getImageView();
        dialog.setContentView(image);

        //大图的点击事件（点击让他消失）
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //小图的点击事件（弹出大图）
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        //动态的ImageView
    private ImageView getImageView(){
        ImageView imageView = new ImageView(this);

        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //imageView设置图片
        imageView.setImageBitmap(bitmap);

        return imageView;
    }


     */




    //todo barChart条形图
    /*

    同一条条形图

     List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        BarData bardata = new BarData(dataSets);

        分组条形图

        mBarchart.groupBars(0f, 0.4f,0.02f); // perform the "explicit" grouping

     */

    //todo 图片实现双手放大与缩小
    /*
       ImagOnTouch
     */

    //todo EventBus

    /*

    *
    EventBus.getDefault().register(this);  //事件的注册

         // 普通事件的处理
        @Subscribe(threadMode = ThreadMode.MAIN)
        public void handleEvent(EventBusCarrier carrier) {
            int co2 =  carrier.getbCo2();
            int dao =  carrier.getbDao();
            int wen =  carrier.getbWen();
            int sun =  carrier.getbSun();
            int shi =  carrier.getbShi();
            int pm2 =  carrier.getbPm2();
            int jj =  carrier.getJ();

            bSun = sun;
            bShi =shi;
            bCo2 =co2;
            bDao =dao;
            bWen = wen;
            j = jj;
            bPm2 = pm2;

            Log.e("dao", "handleEvent: "+bDao );

        }

     *

     *
     EventBusCarrier eventBusCarrier = new EventBusCarrier();
                    eventBusCarrier.setEventType("1");
                    eventBusCarrier.setbCo2(Integer.parseInt(mCo2Ed.getText().toString()));
                    eventBusCarrier.setbPm2(Integer.parseInt(mPm2Ed.getText().toString()));
                    eventBusCarrier.setbDao(Integer.parseInt(mDaoEd.getText().toString()));
                    eventBusCarrier.setbShi(Integer.parseInt(mShiEd.getText().toString()));
                    eventBusCarrier.setbSun(Integer.parseInt(mSunEd.getText().toString()));
                    eventBusCarrier.setbWen(Integer.parseInt(mWenEd.getText().toString()));
                    eventBusCarrier.setJ(1);
                    EventBus.getDefault().post(eventBusCarrier); //普通事件发布

     *

     */

    //todo 自定义dialog的宽和高
    /*
    WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
                attributes.width=1000;
                attributes.height = 800;
     */

    //todo 数据库
    /*
    public void addCarMessage(EnvirtData envirtData, String time){
        //todo String sql = "insert into envir_tbb  (wen,dao,shi,co2,pm2,time,sun) values(?,?,?,?,?,?,?)";
        db.execSQL(sql,new Object[]{envirtData.getmWen(),envirtData.getmDao(),envirtData.getmShi(),envirtData.getmCo2(),envirtData.getmPm2(),time,envirtData.getmSun()});
    }
    public Cursor selectCarMessage(){
       //todo String sql = "select * from envir_tbb order by _id ";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
    public void deleteCarMessage(String...strings){
      //todo  String sql = "delete from envir_tbb ";
        db.execSQL(sql);
    }
     */

    //todo SharedPreferences本地存储
    /*
    SharedPreferences sp = getSharedPreferences("userInfo", 0);
    String name=sp.getString("USER_NAME", "");
    Boolean pass =sp.getBoolean("PASSWORD", fasle);
    SharedPreferences.Editor editor = sp.edit;
    editor.putString("USER_NAME","...");
    editor.commit();
     */

    //todo 侧滑
    /*

        listView = findViewById(R.id.listView);

        //创建侧滑菜单
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.mipmap.icon_1);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        //侧滑菜单监听
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(CehuaActivity.this,"open",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        // delete
                        Toast.makeText(CehuaActivity.this,"delete",Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        // Left
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        listView.setAdapter(new AppAdapter(CehuaActivity.this));

        //todo 侧滑导包
        implementation 'com.baoyz.swipemenulistview:library:1.3.0'
     */

}
