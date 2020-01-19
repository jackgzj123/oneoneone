package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.MyDialog;
import com.lenovo.smarttraffic.ui.activity.MyDialog6;

import java.util.ArrayList;
import java.util.List;

public class ListItem39_fragmentAdapter extends BaseAdapter implements MyDialog6.SettingListener{

    private Context mContext;
    private List<String> honglist = new ArrayList<>();
    private List<String> huanglist = new ArrayList<>();
    private List<String> lvlist = new ArrayList<>();
    private List<String> roadlist = new ArrayList<>();


    public ListItem39_fragmentAdapter(Context mContext,List<String> roadlist, List<String> honglist,List<String> huanglist,List<String> lvlist) {
        this.mContext = mContext;
        this.roadlist = roadlist;
        this.honglist = honglist;
        this.huanglist = huanglist;
        this.lvlist = lvlist;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return honglist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();
        if (view == null){

            view = LayoutInflater.from(mContext).inflate(R.layout.item39_view,null);

            //使viewholder属性与之对应
            viewHolder.mPeizhi = view.findViewById(R.id.item39_view_peizhi);
            viewHolder.mHong = view.findViewById(R.id.item39_view_red);
            viewHolder.mHuang = view.findViewById(R.id.item39_view_yellow);
            viewHolder.mLv = view.findViewById(R.id.item39_view_lv);
            viewHolder.mRoad = view.findViewById(R.id.item39_view_roadname);
            //使view 与 viewholder 形成对应关系
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder) view.getTag();

        }


        viewHolder.mLv.setText("绿灯"+lvlist.get(i)+"秒");
        viewHolder.mHuang.setText("黄灯"+huanglist.get(i)+"秒");
        viewHolder.mHong.setText("红灯"+honglist.get(i)+"秒");
        viewHolder.mRoad.setText("路口"+roadlist.get(i));
        viewHolder.mPeizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDialog6 myDialog6 = new MyDialog6(mContext,R.style.mydialog,""+(i+1));
                myDialog6.show();

            }
        });



        return view;
    }

    @Override
    public void onSetting(String i, String hong, String huang, String lv) {

    }

    //把每一条数据缓存起来
    public static class ViewHolder{
        public TextView mPeizhi;
        public TextView mHong;
        public TextView mHuang;
        public TextView mLv;
        public TextView mRoad;


    }
}
