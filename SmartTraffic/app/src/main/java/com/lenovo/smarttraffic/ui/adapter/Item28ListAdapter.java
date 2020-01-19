package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

public class Item28ListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] strings = {"荣乌高速","荣乌高速","荣乌高速","荣乌高速","荣乌高速","荣乌高速","荣乌高速"};


    public Item28ListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int i) {
        return strings[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();
        if (view == null){

            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item28,null);

            //使viewholder属性与之对应
            viewHolder.mtextview = view.findViewById(R.id.adapter_item28_lefttv);
            viewHolder.mtextview1 = view.findViewById(R.id.adapter_item28_middletv);

            //使view 与 viewholder 形成对应关系
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder) view.getTag();

        }


        viewHolder.mtextview.setText(strings[i]);
        viewHolder.mtextview1.setText(strings[i]);

        return view;
    }

    //把每一条数据缓存起来
    public static class ViewHolder{
        public TextView mtextview;
        public TextView mtextview1;
    }
}
