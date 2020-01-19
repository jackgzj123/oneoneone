package com.lenovo.smarttraffic.ui.fragment.item3fragment;

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
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.CarMessageDao;

public class ChongFragment extends Fragment {

    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private ListView mList;
    private CarMessageDao carMessageDao;
    private TextView mTatoltv;
    private int i =0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gerenzhongxin2,null);

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mTatoltv = view.findViewById(R.id.item3_tatolpride);
        mList = view.findViewById(R.id.item3_list);
        carMessageDao = new CarMessageDao(getActivity());
        cursor = carMessageDao.selectCarMessage(null);

        while (cursor.moveToNext()){
            String chong = cursor.getString(cursor.getColumnIndex("chong"));
            i+= Integer.valueOf(chong);
        }

        mTatoltv.setText(""+i);
        cursor = carMessageDao.selectCarMessage("order by time desc");
        getAdapter();

        mList.setAdapter(adapter);


        return view;

    }

    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                getActivity(),R.layout.item6, cursor,
                new String[]{"time","chong","chongyue","peoplename","time","carNumber"},//表头
                new int[]{R.id.item6time1,R.id.item6chong,R.id.item6yue,R.id.item6peoplename,R.id.item6time,R.id.item6carname},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }
}
