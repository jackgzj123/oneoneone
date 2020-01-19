package com.lenovo.smarttraffic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.smarttraffic.ui.activity.LoginActivity;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */
public class SplashActivity extends AppCompatActivity {

    private boolean isFirstIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = this.getSharedPreferences("myActivityName", MODE_PRIVATE);
        isFirstIn = pref.getBoolean("isFirstIn", true);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();

        if(isFirstIn == true){
            InitApp.getHandler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            },1000);
        }else {
            InitApp.getHandler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            },1000);
        }

    }

}
