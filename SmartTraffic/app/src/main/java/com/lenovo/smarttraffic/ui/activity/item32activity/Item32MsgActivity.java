package com.lenovo.smarttraffic.ui.activity.item32activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;

import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class Item32MsgActivity extends AppCompatActivity {

    private ImageView imageView;
    private String carid;
    private String paymoney;
    private String refresh;
    private TextView mTv;
    private String str;
    private Dialog dialog;
    private ImageView image;
    private Bitmap bitmap;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item32_msg);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = findViewById(R.id.item32_msg_img);
        mTv = findViewById(R.id.item32_msg_tv);

        carid = getIntent().getStringExtra("carid");
        paymoney = getIntent().getStringExtra("paymoney");
        refresh = getIntent().getStringExtra("refresh");

        int i = Integer.valueOf(refresh)*1000;
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        },0,1000);

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


        //长按显示文字
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mTv.setText(str);
                return true;
            }
        });

        //小图的点击事件（弹出大图）
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


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

    //动态的ImageView
    private ImageView getImageView(){
        ImageView imageView = new ImageView(this);

        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //imageView设置图片
        imageView.setImageBitmap(bitmap);

        return imageView;
    }


}
