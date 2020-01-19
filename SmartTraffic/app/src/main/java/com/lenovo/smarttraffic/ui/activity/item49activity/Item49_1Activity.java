package com.lenovo.smarttraffic.ui.activity.item49activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.VideoActivity;
import com.lenovo.smarttraffic.ui.activity.item32activity.Item32MsgActivity;

public class Item49_1Activity extends AppCompatActivity {

    private ImageView mBack;
    private ImageView mLeft;
    private ImageView mRight;
    private ImageView mImg;
    private ImageView mVideo;
    private Dialog dialog;
    private ImageView image;
    private Integer[] list = {R.mipmap.weizhang01,R.mipmap.weizhang02,R.mipmap.weizhang03,R.mipmap.weizhang04};
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item49_1);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBack = findViewById(R.id.lgb2_back);
        mLeft = findViewById(R.id.item49_1_left);
        mRight = findViewById(R.id.item49_1_right);
        mImg = findViewById(R.id.item49_1_img);
        mVideo = findViewById(R.id.item49_1_video);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//展示在dialog上面的大图
        dialog = new Dialog(Item49_1Activity.this,R.style.FullActivity);

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
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        mVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item49_1Activity.this, VideoActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (num==0){
                    Toast.makeText(Item49_1Activity.this,"没有了",Toast.LENGTH_SHORT).show();
                }else {
                    num--;
                    mImg.setBackgroundResource(list[num]);
                }
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num==3){
                    Toast.makeText(Item49_1Activity.this,"没有了",Toast.LENGTH_SHORT).show();
                }else {
                    num++;
                    mImg.setBackgroundResource(list[num]);
                }
            }
        });

    }
    //动态的ImageView
    private ImageView getImageView(){
        ImageView imageView = new ImageView(this);

        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //imageView设置图片
        imageView.setImageResource(list[num]);

        return imageView;
    }
}
