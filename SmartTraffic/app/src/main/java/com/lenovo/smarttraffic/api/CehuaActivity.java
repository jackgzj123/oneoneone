package com.lenovo.smarttraffic.api;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;

public class CehuaActivity extends AppCompatActivity {

    private SwipeMenuListView listView;
    private List<String> strings = new ArrayList<>();
    private AppAdapter appAdapter;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cehua);

        strings.add("111");
        strings.add("222");
        strings.add("333");
        strings.add("444");
        listView = findViewById(R.id.listView);
        // Left
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        appAdapter = new AppAdapter(CehuaActivity.this,strings);
        listView.setAdapter(appAdapter);
        //创建侧滑菜单
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setTitle("删除");
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitleSize(18);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);


        //侧滑菜单监听
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(CehuaActivity.this,"open",Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        // delete

                        strings.remove(position);
                        listView.setAdapter(new AppAdapter(CehuaActivity.this,strings));

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });



    }
}
