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

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> carnumberlist = new ArrayList<>();
    private List<String> xuhaolist = new ArrayList<>();

    public ListAdapter(Context mContext, List<Integer> carnumberlist) {
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
        for (int j = 1; j < 16; j++) {
            xuhaolist.add(""+j);
        }
        ViewHolder viewHolder = new ViewHolder();
        if (view == null){

            view = LayoutInflater.from(mContext).inflate(R.layout.item_app_liest_view,null);

            //使viewholder属性与之对应
            viewHolder.mtextview = view.findViewById(R.id.xuhao_item);
            viewHolder.mtextview1 = view.findViewById(R.id.gongjiaoche_item);
            viewHolder.mtextview2 = view.findViewById(R.id.peoplenum_item);

            //使view 与 viewholder 形成对应关系
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder) view.getTag();

        }


        viewHolder.mtextview.setText(xuhaolist.get(i));
        viewHolder.mtextview1.setText(xuhaolist.get(i));
        viewHolder.mtextview2.setText(""+carnumberlist.get(i));



        return view;
    }

    //把每一条数据缓存起来
    public static class ViewHolder{
        public TextView mtextview;
        public TextView mtextview1;
        public TextView mtextview2;

    }
}
