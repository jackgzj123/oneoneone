package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

public class Item14Activity extends AppCompatActivity {

    private Button mSelectbtn;
    private EditText mEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item14);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {
        mSelectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Item14Activity.this,"没有查询到鲁"+mEd.getText().toString()+"车的违章数据!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Item14Activity.this,WeizhangActivity.class);
                intent.putExtra("name",mEd.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initview() {
        mSelectbtn = findViewById(R.id.item14_selectbtn);
        mEd = findViewById(R.id.item14_ed);
    }
}
