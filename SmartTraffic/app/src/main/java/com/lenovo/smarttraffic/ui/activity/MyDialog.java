package com.lenovo.smarttraffic.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

//设置自定义对话框样式
public class MyDialog extends Dialog {


    private final EditText mChonged;
    private List<String> carnamelist = new ArrayList<>();
    private final TextView carnametv;
    private String car_name;
    private String s1;
    private SettingListener mSListener = null;

    public MyDialog(Context context, int themeResId, List<String> carnamelist ) {
        super(context, themeResId);
        this.carnamelist = carnamelist;

        //为对话框设置布局
        setContentView(R.layout.diydialog);

        mChonged = findViewById(R.id.diylog_edmoney);
        carnametv = findViewById(R.id.diylog_carname);

        car_name=carnamelist.get(0);

        for (int i = 1; i < carnamelist.size(); i++) {
            car_name=car_name+carnamelist.get(i);
        }
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
                for (int i = 0; i < carnamelist.size(); i++) {
                    if (mSListener != null) {
                        if (carnamelist.get(i).equals("辽A10001")){
                            mSListener.onSetting(mChonged.getText().toString(),1);
                        }else if (carnamelist.get(i).equals("辽A10002")){
                            mSListener.onSetting(mChonged.getText().toString(),2);
                        }else if (carnamelist.get(i).equals("辽A10003")){
                            mSListener.onSetting(mChonged.getText().toString(),3);
                        }else if (carnamelist.get(i).equals("辽A10004")){
                            mSListener.onSetting(mChonged.getText().toString(),4);
                        }else {
                            mSListener.onSetting(mChonged.getText().toString(),5);
                        }

                    }
                }

                Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
    }

    public interface SettingListener {
        public void onSetting(String name,int i);}

    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }
}
