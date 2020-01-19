package com.lenovo.smarttraffic.ui.fragment.item19fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.FazhiBaoDao;
import com.lenovo.smarttraffic.ui.adapter.MySimpleCursorAdapter;

import java.util.ArrayList;

public class SelectFragment extends Fragment {

    private FazhiBaoDao fazhiBaoDao;
    private Spinner spinner;
    private String s;
    private ListView listView;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = inflater.inflate(R.layout.fragment_select,null);

        fazhiBaoDao = new FazhiBaoDao(getActivity());
        listView = view.findViewById(R.id.listview);

        spinner = view.findViewById(R.id.select_spinner);
        getSpinner();

        cursor = fazhiBaoDao.selectCarMessage();
        getAdapter();
        listView.setAdapter(adapter);


        return view;
    }

    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                getActivity(),R.layout.item7, cursor,
                new String[]{"_id","name","fa","dang"},//表头
                new int[]{R.id.xuhao,R.id.baojingleixing,R.id.fazhi,R.id.dangqianzhi},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    private void getSpinner() {
        final ArrayList<String> arrayList = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
        //添加要显示的内容
        arrayList.add("全部");
        arrayList.add("湿度");
        arrayList.add("温度");
        arrayList.add("CO2");
        arrayList.add("光照");
        arrayList.add("PM2.5");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arrayList);//创建适配器  this--上下文  android.R.layout.simple_spinner_item--显示的模板   arrayList--显示的内容
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置下拉之后的布局的样式 这里采用的是系统的一个布局
        spinner.setAdapter(arrayAdapter);//将适配器给下拉框
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//当改变下拉框的时候会触发

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//改变内容的时候
                s=arrayList.get(position);
                if (s.equals("全部")){
                    cursor = fazhiBaoDao.selectCarMessage();
                    getAdapter();
                    listView.setAdapter(adapter);
                }else {
                    cursor = fazhiBaoDao.selectCarMessage("where name = '"+s+"'");
                    getAdapter();
                    listView.setAdapter(adapter);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

            }
        });
    }
}
