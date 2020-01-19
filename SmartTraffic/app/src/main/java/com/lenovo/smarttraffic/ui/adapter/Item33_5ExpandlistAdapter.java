package com.lenovo.smarttraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.data.Chapter;
import com.lenovo.smarttraffic.database.data.ChapterItem;

import java.util.List;

public class Item33_5ExpandlistAdapter extends BaseExpandableListAdapter {

    private List<Chapter> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;

    public Item33_5ExpandlistAdapter(List<Chapter> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mDatas.get(i).getChildren().size();
    }

    @Override
    public Object getGroup(int i) {
        return mDatas.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mDatas.get(i).getChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ParentViewHolder parentViewHolder = null;
        if (view == null){
            view = mInflater.inflate(R.layout.item33_5chapter,viewGroup,false);
            parentViewHolder = new ParentViewHolder();
            parentViewHolder.mTvName = view.findViewById(R.id.item33_5_tv);
            parentViewHolder.imageView = view.findViewById(R.id.item33_chapterimg);

            view.setTag(parentViewHolder);
        }else {
            parentViewHolder = (ParentViewHolder) view.getTag();
        }

        Chapter chapter = mDatas.get(i);
        parentViewHolder.mTvName.setText(chapter.getName());
        parentViewHolder.imageView.setImageResource(R.drawable.group_indicator);
        parentViewHolder.imageView.setSelected(b);

        return view ;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder = null;
        if (view == null){
            view = mInflater.inflate(R.layout.item33_child_chapter,viewGroup,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.mTvName = view.findViewById(R.id.tv_id_name);
            view.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }

        ChapterItem chapterItem = mDatas.get(i).getChildren().get(i1);
        childViewHolder.mTvName.setText(chapterItem.getName());

        return view ;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public static class ParentViewHolder{
        TextView mTvName;
        ImageView imageView;

    }
    public static class ChildViewHolder{
        TextView mTvName;

    }




}
