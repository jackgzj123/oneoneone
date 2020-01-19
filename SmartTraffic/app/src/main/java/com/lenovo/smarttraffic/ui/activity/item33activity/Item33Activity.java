package com.lenovo.smarttraffic.ui.activity.item33activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.data.Chapter;
import com.lenovo.smarttraffic.database.data.ChapterItem;
import com.lenovo.smarttraffic.ui.adapter.ExpandlistAdapter;
import com.lenovo.smarttraffic.ui.adapter.Item33ExpandlistAdapter;

import java.util.ArrayList;
import java.util.List;

public class Item33Activity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private List<Chapter> chapterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item33);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mExpandableListView = findViewById(R.id.item33_expandlistview);

        setdata();

        mExpandableListView.setAdapter(new Item33ExpandlistAdapter(chapterList, Item33Activity.this));

//设置子选项点击监听事件
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {                         //childPosition代表着要接收的数组的列数

                    Intent intent = new Intent(Item33Activity.this,Item33_1Activity.class);
                    String s = chapterList.get(groupPosition).getChildren().get(childPosition).getName();
                    intent.putExtra("wherewhere",s);
                    startActivity(intent);

                    return true;
            }
        });


    }

    private void setdata() {
        Chapter chapter = new Chapter("1号线");
        ChapterItem chapterItem = new ChapterItem("起点：光谷金融街");
        ChapterItem chapterItem1 = new ChapterItem("戎军路南");
        ChapterItem chapterItem2 = new ChapterItem("终点：南湖商厦");
        chapter.addChild(chapterItem);
        chapter.addChild(chapterItem1);
        chapter.addChild(chapterItem1);
        chapter.addChild(chapterItem1);
        chapter.addChild(chapterItem1);
        chapter.addChild(chapterItem1);
        chapter.addChild(chapterItem2);
        chapterList.add(chapter);

        Chapter chapter2 = new Chapter("2号线");
        ChapterItem chapterItem3 = new ChapterItem("起点：光谷金融街");
        ChapterItem chapterItem4 = new ChapterItem("戎军路南");
        ChapterItem chapterItem5 = new ChapterItem("终点：南湖商厦");
        chapter2.addChild(chapterItem3);
        chapter2.addChild(chapterItem4);
        chapter2.addChild(chapterItem4);
        chapter2.addChild(chapterItem4);
        chapter2.addChild(chapterItem4);
        chapter2.addChild(chapterItem4);
        chapter2.addChild(chapterItem5);
        chapterList.add(chapter2);

        Chapter chapter3 = new Chapter("3号线");
        ChapterItem chapterItem6 = new ChapterItem("起点：光谷金融街");
        ChapterItem chapterItem7 = new ChapterItem("戎军路南");
        ChapterItem chapterItem8 = new ChapterItem("终点：南湖商厦");
        chapter3.addChild(chapterItem6);
        chapter3.addChild(chapterItem7);
        chapter3.addChild(chapterItem7);
        chapter3.addChild(chapterItem7);
        chapter3.addChild(chapterItem7);
        chapter3.addChild(chapterItem7);
        chapter3.addChild(chapterItem8);
        chapterList.add(chapter3);
    }
}
