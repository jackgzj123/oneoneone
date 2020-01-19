package com.lenovo.smarttraffic.bean;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.lenovo.smarttraffic.R;

import java.util.List;

public class ZFM11Dialog extends Dialog {

    private List<String> roadName;
    private EditText mZfm1DialogEtRed;
    private EditText mZfm1DialogEtYellow;
    private EditText mZfm1DialogEtGreen;
    private Button mZfm1DialogBtnOk;
    private Button mZfm1DialogBtnBack;
    private setOk setOk;

    public void setSetOk(ZFM11Dialog.setOk setOk) {
        this.setOk = setOk;
    }

    public ZFM11Dialog(@NonNull Context context, List<String> roadName) {
        super(context);
        this.roadName = roadName;
        setContentView(R.layout.zfm_11_dialog);

        initViews();

        initEvents();

    }

    private void initEvents() {
        mZfm1DialogBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String red = mZfm1DialogEtRed.getText().toString().trim();
                String green = mZfm1DialogEtGreen.getText().toString().trim();
                String yellow = mZfm1DialogEtYellow.getText().toString().trim();
                if (red.length() == 0 || green.length() == 0 || yellow.length() == 0) {
                    Toast.makeText(getContext(), "请输出设置时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                ZFM11SetTime.setTime(roadName, red, green, yellow, new ZFM11SetTime.IZfm1IFOK() {
                    @Override
                    public void success() {
                        setOk.success();
                        Log.e("====>", "success: " );
                        dismiss();
                    }

                    @Override
                    public void failure() {
                        setOk.failure();
                        dismiss();
                    }
                });
            }
        });

        mZfm1DialogBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void initViews() {
        mZfm1DialogEtRed = findViewById(R.id.zfm_1_dialog_et_red);
        mZfm1DialogEtYellow = findViewById(R.id.zfm_1_dialog_et_yellow);
        mZfm1DialogEtGreen = findViewById(R.id.zfm_1_dialog_et_green);
        mZfm1DialogBtnOk = findViewById(R.id.zfm_1_dialog_btn_ok);
        mZfm1DialogBtnBack = findViewById(R.id.zfm_1_dialog_btn_back);
    }

    public interface setOk {
        void success();

        void failure();
    }

}
