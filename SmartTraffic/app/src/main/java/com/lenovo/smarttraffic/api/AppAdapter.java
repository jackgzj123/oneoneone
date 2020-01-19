package com.lenovo.smarttraffic.api;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AppAdapter extends BaseAdapter {
    private Context context;
    private List<String> strings = new ArrayList<>();

    public AppAdapter(Context context,List<String> strings) {
        this.context = context;
        this.strings =strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Object getItem(int i) {
        return strings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = View.inflate(context, R.layout.cehuaaa,null);
        TextView textView = view1.findViewById(R.id.cehuatv);
        textView.setText(strings.get(i));
        return view1;
    }
}
