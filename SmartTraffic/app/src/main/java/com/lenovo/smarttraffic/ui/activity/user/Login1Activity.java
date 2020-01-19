package com.lenovo.smarttraffic.ui.activity.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.LoginActivity;

public class Login1Activity extends AppCompatActivity {

    private EditText mNameEd;
    private EditText mPassEd;
    private TextView mRegister;
    private TextView mZhaoPass;
    private Button mBtn;

    private SharedPreferences sp;
    private String userNameValue,passwordValue;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

    }

    private void initevent() {

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameValue = mNameEd.getText().toString();
                passwordValue = mPassEd.getText().toString();


                // TODO Auto-generated method stub
                if (userNameValue.equals("user1")
                        && passwordValue.equals("123456")) {
                    Toast.makeText(Login1Activity.this, "登录成功",
                            Toast.LENGTH_SHORT).show();

                    editor = sp.edit();
                    //保存用户名和密码
                    editor.putString("USER_NAME", userNameValue);
                    editor.putString("PASSWORD", passwordValue);

                    editor.commit();

                    //跳转
                    Intent intent =new Intent(Login1Activity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login1Activity.this, "用户名或密码错误，请重新登录!",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login1Activity.this,Register1Activity.class));
            }
        });

        mZhaoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login1Activity.this,LookPassActivity.class));
            }
        });
    }

    private void initview() {
        mNameEd = findViewById(R.id.login1_name);
        mPassEd = findViewById(R.id.login1_password);
        mRegister = findViewById(R.id.login1_zhuce);
        mZhaoPass = findViewById(R.id.login1_lookpass);
        mBtn = findViewById(R.id.login1_denglu);

        sp = getSharedPreferences("userInfo", 0);
        String name=sp.getString("USER_NAME", "");
        String pass =sp.getString("PASSWORD", "");

        mNameEd.setText(name);
        mPassEd.setText(pass);


        boolean choseRemember =sp.getBoolean("remember", false);
        boolean choseAutoLogin =sp.getBoolean("autologin", true);//自动登录

    }
}
