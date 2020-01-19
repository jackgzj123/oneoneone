package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

public class List1Adapter extends BaseAdapter {

    private Context mContext;
    private String[] strings;
    private int selectedPosition = 0;// 选中的位置

    public List1Adapter(Context mContext, String[] strings) {
        this.mContext = mContext;
        this.strings = strings;
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

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();
        if (view == null){

            view = LayoutInflater.from(mContext).inflate(R.layout.item46_1_adapterview,null);

            //使viewholder属性与之对应
            viewHolder.mtextview = view.findViewById(R.id.item46_1_adapter_number);
            viewHolder.mtextview1 = view.findViewById(R.id.item46_1_adapter_name);

            //使view 与 viewholder 形成对应关系
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder) view.getTag();

        }

        if (selectedPosition == i) {
            viewHolder.mtextview.setBackgroundResource(R.drawable.circle6);
            viewHolder.mtextview1.setTextColor(Color.rgb(255,0,0));
        } else {
            viewHolder.mtextview.setBackgroundResource(R.drawable.circle5);
            viewHolder.mtextview1.setTextColor(Color.parseColor("#000000"));
        }

        viewHolder.mtextview.setText(""+(i+1));
        viewHolder.mtextview1.setText(strings[i]);

        return view;
    }

    //把每一条数据缓存起来
    public static class ViewHolder{
        public TextView mtextview;
        public TextView mtextview1;

    }
}
