package com.lenovo.smarttraffic.ui.fragment.item38fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.Car1Dao;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/21
 */
public class ChongFragment extends Fragment {


    private ListView mList;
    private Car1Dao car1Dao;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item38_yuanchengfragment,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mList = view.findViewById(R.id.item38_yuanchengfragment_list);
        car1Dao = new Car1Dao(getActivity());

        cursor = car1Dao.selectCarMessage();

        getAdapter();

        mList.setAdapter(adapter);

        return view;
    }

    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                getActivity(),R.layout.item38_view, cursor,
                new String[]{"_id","name","chong","time"},//表头
                new int[]{R.id.item38_fragment_xuhao,R.id.item38_fragment_carname,R.id.item38_fragment_chong,R.id.item38_fragment_time},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

}
