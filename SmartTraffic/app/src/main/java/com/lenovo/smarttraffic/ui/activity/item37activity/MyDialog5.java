package com.lenovo.smarttraffic.ui.activity.item37activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

//设置自定义对话框样式
public class MyDialog5 extends Dialog {


    private final EditText mChonged;
    private final TextView carnametv;
    private String car_name;
    private String s1;
    private SettingListener mSListener = null;

    public MyDialog5(Context context, int themeResId, String carnamelist ) {
        super(context, themeResId);
        this.car_name = carnamelist;

        //为对话框设置布局
        setContentView(R.layout.diydialog);

        mChonged = findViewById(R.id.diylog_edmoney);
        carnametv = findViewById(R.id.diylog_carname);


        carnametv.setText(car_name);


        findViewById(R.id.diylog_quitbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();//让对话框消失
            }
        });

        findViewById(R.id.diylog_chongbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (mSListener != null) {
                        if (car_name.equals("1")){
                            mSListener.onSetting(mChonged.getText().toString(),1);
                        }else if (car_name.equals("2")){
                            mSListener.onSetting(mChonged.getText().toString(),2);
                        }else if (car_name.equals("3")){
                            mSListener.onSetting(mChonged.getText().toString(),3);
                        }else if (car_name.equals("4")){
                            mSListener.onSetting(mChonged.getText().toString(),4);
                        }else {
                            mSListener.onSetting(mChonged.getText().toString(),5);
                        }


                }

                Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
    }

    public interface SettingListener {
        public void onSetting(String name, int i);}

    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }
}
