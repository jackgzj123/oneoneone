package com.lenovo.smarttraffic.ui.fragment.item33fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.data.Chapter;
import com.lenovo.smarttraffic.database.data.ChapterItem;
import com.lenovo.smarttraffic.ui.adapter.Item33_5ExpandlistAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/21
 */
public class Daifragment extends Fragment {

    private ExpandableListView expandableListView;
    private String string;
    private List<Chapter> chapterList = new ArrayList<>();
    private String date;

    public static Daifragment newInstance(String str,String date){
        Daifragment daifragment = new Daifragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag",str);
        bundle.putString("tag1",date);
        daifragment.setArguments(bundle);
        return daifragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daifragment_view,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getArguments()!=null){
            string = getArguments().getString("tag");
            date = getArguments().getString("tag1");
        }
        expandableListView = view.findViewById(R.id.daifragment_expandlistview);

        setdata();
        expandableListView.setAdapter(new Item33_5ExpandlistAdapter(chapterList,getContext()));


        return view;
    }

    private void setdata() {
        Chapter chapter = new Chapter(string);
        ChapterItem chapterItem = new ChapterItem(date);
        chapter.addChild(chapterItem);
        chapterList.add(chapter);
    }
}
