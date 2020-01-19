package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEdtName;
    private EditText mEdtPassword;
    private EditText mEdtPasswordagain;
    private EditText mEdtPhone;
    private Button mQuitBtn;
    private Button mRegBtn;

    private String name;
    private String password;
    private String password1;
    private String phone;
    private String TAG = "GZJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initview();

        initevent();
    }

    private void initevent() {

        mEdtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = editable.toString();
            }
        });

        mEdtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password = editable.toString();
            }
        });

        mEdtPasswordagain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password1 = editable.toString();
            }
        });

        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phone = editable.toString();
            }
        });

        mQuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.equals(password1)&&phone.length() ==11){
                    Toast.makeText(RegisterActivity.this,"注册成功， "+name+"帅哥/姑娘",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }else if (!password.equals(password1)){
                    Toast.makeText(RegisterActivity.this,"两次密码输入不一致，请重新来过",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this,"手机号码输入不正确，请重新来过",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //网络设置
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.network_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this,NetActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initview() {
        mEdtName = findViewById(R.id.register_editTextName);
        mEdtPassword = findViewById(R.id.register_editTextPassword);
        mEdtPasswordagain = findViewById(R.id.register_editTextPasswordagain);
        mEdtPhone = findViewById(R.id.register_phone);
        mQuitBtn = findViewById(R.id.register_quitBtn);
        mRegBtn = findViewById(R.id.register_regBtn);
    }
}
