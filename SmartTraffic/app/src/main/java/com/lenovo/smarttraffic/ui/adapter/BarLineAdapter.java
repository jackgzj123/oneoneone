package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.github.mikephil.charting.charts.BarChart;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.BarLineFragment;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.Co22Fragment;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.WenduFragment;
import com.lenovo.smarttraffic.ui.fragment.BarFragment.XiangduishiduFragment;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class BarLineAdapter extends BaseAdapter {
    private Context mContext;


    public BarLineAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if (view == null){

            view = LayoutInflater.from(mContext).inflate(R.layout.barline,null);

            //使viewholder属性与之对应
            viewHolder.viewPager = view.findViewById(R.id.viewpager);
            //使view 与 viewholder 形成对应关系
            view.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) view.getTag();

        }






        return view;
    }

    //把每一条数据缓存起来
    public class ViewHolder{
        public ViewPager viewPager;
    }
}
