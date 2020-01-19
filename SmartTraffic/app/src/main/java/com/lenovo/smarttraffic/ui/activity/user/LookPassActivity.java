package com.lenovo.smarttraffic.ui.activity.user;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.UserDao;

public class LookPassActivity extends AppCompatActivity {

    private ImageView mBack;
    private EditText mName;
    private TextView mPass;
    private EditText mEmail;
    private Button mBtn;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_pass);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userDao = new UserDao(this);

        initview();


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LookPassActivity.this,Login1Activity.class));
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = userDao.selectMessage();
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    if (name.equals(mName.getText().toString())){
                        String password = cursor.getString(cursor.getColumnIndex("password"));
                        mPass.setText(password);
                    }
                }
            }
        });

    }

    private void initview() {
        mBack = findViewById(R.id.lookpass_imgleft);
        mName = findViewById(R.id.lookpass_name);
        mPass = findViewById(R.id.lookpass_pass);
        mEmail = findViewById(R.id.lookpass_email);
        mBtn = findViewById(R.id.lookpass_btn);

    }
}
