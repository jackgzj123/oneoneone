package com.lenovo.smarttraffic.ui.fragment.item38fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/21
 */
public class YuanchengFragment extends Fragment {
    private TextView mCar1qi;
    private TextView mCar1ting;
    private TextView mCar2qi;
    private TextView mCar2ting;
    private TextView mCar3qi;
    private TextView mCar3ting;
    private TextView mCar4qi;
    private TextView mCar4ting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item38_chongfragment,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mCar1qi = view.findViewById(R.id.item38_chongfragment_car1qidong);
        mCar1ting = view.findViewById(R.id.item38_chongfragment_car1ting);
        mCar2qi = view.findViewById(R.id.item38_chongfragment_car2qidong);
        mCar2ting = view.findViewById(R.id.item38_chongfragment_car2ting);
        mCar3qi = view.findViewById(R.id.item38_chongfragment_car3qidong);
        mCar3ting = view.findViewById(R.id.item38_chongfragment_car3ting);
        mCar4qi = view.findViewById(R.id.item38_chongfragment_car4qidong);
        mCar4ting = view.findViewById(R.id.item38_chongfragment_car4ting);

        setcar1();
        setcar2();
        setcar3();
        setcar4();


        return view;
    }

    private void setcar1() {
        mCar1qi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar1qi.setBackgroundResource(R.drawable.edtt10);
                mCar1ting.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"1号小车启动成功",Toast.LENGTH_SHORT).show();
            }
        });
        mCar1ting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar1ting.setBackgroundResource(R.drawable.edtt10);
                mCar1qi.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"1号小车停止成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setcar2() {
        mCar2qi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar2qi.setBackgroundResource(R.drawable.edtt10);
                mCar2ting.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"2号小车启动成功",Toast.LENGTH_SHORT).show();
            }
        });
        mCar2ting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar2ting.setBackgroundResource(R.drawable.edtt10);
                mCar2qi.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"2号小车停止成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setcar3() {
        mCar3qi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar3qi.setBackgroundResource(R.drawable.edtt10);
                mCar3ting.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"3号小车启动成功",Toast.LENGTH_SHORT).show();
            }
        });
        mCar3ting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar3ting.setBackgroundResource(R.drawable.edtt10);
                mCar3qi.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"3号小车停止成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setcar4() {
        mCar4qi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar4qi.setBackgroundResource(R.drawable.edtt10);
                mCar4ting.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"4号小车启动成功",Toast.LENGTH_SHORT).show();
            }
        });
        mCar4ting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCar4ting.setBackgroundResource(R.drawable.edtt10);
                mCar4qi.setBackgroundResource(R.drawable.edtt9);
                Toast.makeText(getActivity(),"4号小车停止成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
