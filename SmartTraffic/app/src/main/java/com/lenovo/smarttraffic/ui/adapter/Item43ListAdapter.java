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

public class Item43ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> carnumberlist = new ArrayList<>();
    private List<String> xuhaolist = new ArrayList<>();

    public Item43ListAdapter(Context mContext, List<Integer> carnumberlist) {
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

            view = LayoutInflater.from(mContext).inflate(R.layout.item43_adapter_view,null);

            //使viewholder属性与之对应
            viewHolder.mCo2 = view.findViewById(R.id.item43adapter_co2);
            viewHolder.mCo2 = view.findViewById(R.id.item43adapter_PM2);
            viewHolder.mCo2 = view.findViewById(R.id.item43adapter_shi);
            viewHolder.mCo2 = view.findViewById(R.id.item43adapter_sun);
            viewHolder.mCo2 = view.findViewById(R.id.item43adapter_wen);
            viewHolder.mCo2 = view.findViewById(R.id.item43adapter_time);

            //使view 与 viewholder 形成对应关系
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder) view.getTag();

        }


//        viewHolder.mtextview.setText(xuhaolist.get(i));
//        viewHolder.mtextview1.setText(xuhaolist.get(i));
//        viewHolder.mtextview2.setText(""+carnumberlist.get(i));



        return view;
    }

    //把每一条数据缓存起来
    public static class ViewHolder{
        public TextView mCo2;
        public TextView mPm;
        public TextView mSun;
        public TextView mShi;
        public TextView mWen;
        public TextView mTime;
    }
}
