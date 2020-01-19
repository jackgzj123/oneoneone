package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

public class NetActivity extends AppCompatActivity {

    private EditText fuwuEdt;
    private EditText duanEdt;
    private Button baoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);

        fuwuEdt = findViewById(R.id.net_fuwu);
        duanEdt = findViewById(R.id.net_duankou);
        baoBtn = findViewById(R.id.net_baocun);

        baoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //禁止输入255之外的整数  通过for循环
                int i = Integer.parseInt(duanEdt.getText().toString());
                if (i >255){
                    Toast.makeText(NetActivity.this,"只能输入0-255之间的数字",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(NetActivity.this,"IP地址为"+fuwuEdt.getText().toString()+duanEdt.getText().toString(),Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        duanEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }
}
