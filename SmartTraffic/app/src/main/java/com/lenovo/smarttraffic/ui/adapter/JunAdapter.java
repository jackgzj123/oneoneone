package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

public class JunAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> carnumberlist = new ArrayList<>();

    public JunAdapter(Context mContext, List<String> carnumberlist) {
        this.mContext = mContext;
        this.carnumberlist = carnumberlist;
    }

    @Override
    public int getCount() {
        return carnumberlist.size();
    }

    @Override
    public Object getItem(int i) {
        return carnumberlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();
        if (view == null){

            view = LayoutInflater.from(mContext).inflate(R.layout.jun_adapter,null);

            //使viewholder属性与之对应
            viewHolder.mtextview = view.findViewById(R.id.jun_tv);

            //使view 与 viewholder 形成对应关系
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder) view.getTag();

        }


        viewHolder.mtextview.setText(carnumberlist.get(i));




        return view;
    }

    //把每一条数据缓存起来
    public static class ViewHolder{
        public TextView mtextview;

    }
}
