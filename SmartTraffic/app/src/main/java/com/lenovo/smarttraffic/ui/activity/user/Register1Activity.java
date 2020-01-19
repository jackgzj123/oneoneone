package com.lenovo.smarttraffic.ui.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.UserDao;

public class Register1Activity extends AppCompatActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mRepassword;
    private Button mBtn;
    private ImageView mBack;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userDao = new UserDao(this);

        initview();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mName.getText().toString().length()<4){
                    Toast.makeText(Register1Activity.this,"用户名不少于4位",Toast.LENGTH_SHORT).show();
                }else if (mPassword.getText().toString().length()<6){
                    Toast.makeText(Register1Activity.this,"密码不少于4位",Toast.LENGTH_SHORT).show();
                }else if (!mPassword.getText().toString().equals(mRepassword.getText().toString())){
                    Toast.makeText(Register1Activity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                }else {
                    userDao.addMessage(mName.getText().toString(),mPassword.getText().toString(),mEmail.getText().toString());
                    Toast.makeText(Register1Activity.this,"提交成功",Toast.LENGTH_SHORT).show();
                }
            }
        });


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register1Activity.this,Login1Activity.class));
            }
        });


    }

    private void initview() {
        mName = findViewById(R.id.register1_name);
        mEmail = findViewById(R.id.register1_email);
        mPassword = findViewById(R.id.register1_password);
        mRepassword = findViewById(R.id.register1_repassword);
        mBtn = findViewById(R.id.register1_btn);
        mBack = findViewById(R.id.register1_imgleft);


    }
}
