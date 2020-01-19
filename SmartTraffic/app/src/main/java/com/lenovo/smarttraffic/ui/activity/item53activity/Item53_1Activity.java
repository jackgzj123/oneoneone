package com.lenovo.smarttraffic.ui.activity.item53activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.DialogDatePicker;
import com.lenovo.smarttraffic.ui.activity.DialogDatePicker1;
import com.lenovo.smarttraffic.ui.activity.Item48Activity;

public class Item53_1Activity extends AppCompatActivity implements DialogDatePicker1.OnDateSetListener {

    private TextView mManager;
    private TextView mDeleteTv;
    private TextView mDayTv;
    private TextView mTommorTv;
    private ImageView mBack;
    private ImageView mRight;
    private Button mDeleteBtn;
    private Button mAddBtn;
    private TextView mNum;
    private TextView mTotalPay;
    private TextView mPay;
    private LinearLayout mLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item53_1);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initevent();

        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.valueOf(mNum.getText().toString())-1;
                if (i==0){
                    mNum.setText("0");
                    mDeleteBtn.setEnabled(false);
                    mTotalPay.setText("0");
                }else {
                    mNum.setText(""+i);
                    mTotalPay.setText(""+(i*60));
                }
            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.valueOf(mNum.getText().toString())+1;
                mNum.setText(""+i);
                mTotalPay.setText(""+(i*60));
            }
        });

        mManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteTv.setVisibility(View.VISIBLE);
            }
        });
        mDeleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLin.setVisibility(View.INVISIBLE);
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDatePicker1 dialogDatePicker = new DialogDatePicker1(Item53_1Activity.this,2019,12,27,1);
                dialogDatePicker.setOnSettingListener(Item53_1Activity.this);
                dialogDatePicker.show();
            }
        });

        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item53_1Activity.this,ErweimaActivity.class);
                intent.putExtra("paymoney",mTotalPay.getText().toString());
                startActivity(intent);
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initevent() {
        mManager = findViewById(R.id.item53_1_manager);
        mDeleteTv = findViewById(R.id.item53_1_deletetv);
        mDayTv = findViewById(R.id.item53_1_today);
        mTommorTv = findViewById(R.id.item53_1_tomorrow);
        mBack = findViewById(R.id.lgb2_back);
        mRight = findViewById(R.id.item53_1_right);
        mDeleteBtn = findViewById(R.id.item53_1_delete);
        mAddBtn = findViewById(R.id.item53_1_add);
        mNum = findViewById(R.id.item53_1_num);
        mTotalPay = findViewById(R.id.item53_1_totalpay);
        mPay = findViewById(R.id.item53_1_pay);
        mLin = findViewById(R.id.item53_1_lin);
    }

    @Override
    public void onDateSet(int i, int year, int month, int day) {

    }
}
