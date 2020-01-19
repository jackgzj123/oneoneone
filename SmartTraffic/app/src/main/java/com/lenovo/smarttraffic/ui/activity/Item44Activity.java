package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.Collectiondao;
import com.lenovo.smarttraffic.ui.MyRecyclerViewItem;

import java.util.Timer;

public class Item44Activity extends AppCompatActivity {

    private String TAG = "IMListView";
    private RecyclerView recycler;
    private static Collectiondao collectiondao;
    private ImageView mImg;
    private TextView mXiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item44);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        collectiondao = new Collectiondao(this);


        recycler = findViewById(R.id.recycler);
        mImg = findViewById(R.id.lgb2_back);
        mXiang = findViewById(R.id.item44_xiangqing);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mXiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item44Activity.this,CollectionActivity.class));
            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new MyApr());

    }

    public static class VH extends RecyclerView.ViewHolder {

        public TextView show;
        public TextView click;
        public MyRecyclerViewItem recyclerViewItem;
        private final TextView delete;
        private final TextView mUserName;
        private final TextView mName;
        private final TextView mPhoneNumber;

        public VH(View itemView) {
            super(itemView);
            recyclerViewItem = itemView.findViewById(R.id.scroll_item);
            click = itemView.findViewById(R.id.click);
            delete = itemView.findViewById(R.id.delete);
            mUserName = itemView.findViewById(R.id.im_test_item_username);
            mName = itemView.findViewById(R.id.im_test_item_name);
            mPhoneNumber = itemView.findViewById(R.id.im_test_item_phonenumber);
        }
    }

    public static class MyApr extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.im_test_item, parent, false));
        }

        @Override
        public void onBindViewHolder(final VH holder, final int position) {
            //恢复状态
            holder.recyclerViewItem.apply();
            holder.click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(holder.itemView.getContext(), "收藏：" + position, Toast.LENGTH_SHORT).show();
                    Time time = new Time();
                    time.setToNow();
                    String timestring = time.year+"."+(time.month+1)+"."+time.monthDay+" "+time.hour+":"+time.minute;
                    collectiondao.addCarMessage(holder.mUserName.getText().toString(),holder.mName.getText().toString(),holder.mPhoneNumber.getText().toString(),timestring);
                }

            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(holder.itemView.getContext(), "删除：" + position, Toast.LENGTH_SHORT).show();
                    holder.recyclerViewItem.removeAllViews();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 8;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}



