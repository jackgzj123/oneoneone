package com.lenovo.smarttraffic.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.RefreshableView;

import java.util.List;

import butterknife.OnTouch;

public class Refreshview extends LinearLayout implements View.OnTouchListener {

    private int pulltorefresh = 0;
    private int releasetorefresh = 1;
    private int refreshing = 2;
    private int finishrefresh = 3;
    private int scrollspeed = -20;
    private long minute = 60*1000;
    private long hour = minute*60;
    private long day = 24*hour;
    private long month = 30*day;
    private long year = 12*month;
    private MarginLayoutParams viewLayoutParams;
    private long lastupdateTime;
    private int mId = -1;
    private int hideviewheight;
    private int currenstatus = finishrefresh;
    private int laststatus = currenstatus;
    private float yDown;
    private int touchslop;
    private boolean loadonce;
    private boolean abletopull;
    private final SharedPreferences sharedPreferences;
    private final ProgressBar progressBar;
    private final ImageView arrow;
    private final TextView description;
    private final TextView updateTv;
    private final View view;
    private ListView listView;
    private PullToRefreshListener mListener;

    public Refreshview(Context context, AttributeSet attrs) {
        super(context,attrs);
        view = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh,null,true);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        progressBar = view.findViewById(R.id.progress_bar);
        arrow = view.findViewById(R.id.arrow);
        description = view.findViewById(R.id.description);
        updateTv = view.findViewById(R.id.updated_at);
        touchslop = ViewConfiguration.get(context).getScaledTouchSlop();
        refresh_update();
        setOrientation(VERTICAL);
        addView(view,0);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed&&!loadonce){
            hideviewheight = -view.getHeight();
            viewLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            viewLayoutParams.topMargin = hideviewheight;
            listView = (ListView) getChildAt(1);
            listView.setOnTouchListener(this);
            loadonce = true;
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        setisabletopull(motionEvent);//判断能否下拉刷新
        if (abletopull){
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    yDown = motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float ymove = motionEvent.getRawY();
                    int distance = (int) (ymove-yDown);
                    if (distance<0&&viewLayoutParams.topMargin<=hideviewheight){
                        return false;
                    }else if (distance<touchslop){
                        return false;
                    }
                    if (currenstatus != refreshing){
                        if (viewLayoutParams.topMargin>0){
                            currenstatus = releasetorefresh;
                        }else {
                            currenstatus = pulltorefresh;
                        }
                        viewLayoutParams.topMargin = (distance/2)+hideviewheight;
                        view.setLayoutParams(viewLayoutParams);

                    }
                    break;
                case MotionEvent.ACTION_UP:
                default:
                    if (currenstatus == releasetorefresh){
                        new RefreshingTask().execute();
                    }else if (currenstatus == pulltorefresh){
                        new HideviewTask().execute();
                    }
                    break;
            }

            if (currenstatus == releasetorefresh||currenstatus == pulltorefresh){
                updateviewView();
                listView.setPressed(false);
                listView.setFocusable(false);
                listView.setFocusableInTouchMode(false);
                laststatus = currenstatus;
                return true;
            }
        }
        return false;
    }

    private void updateviewView() {
        if (laststatus!=currenstatus){
            if (currenstatus == pulltorefresh){
                description.setText("下拉可以刷新");
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            }else if (currenstatus == releasetorefresh){
                description.setText("释放可以刷新");
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            }else if (currenstatus == refreshing){
                description.setText("正在刷新");
                arrow.clearAnimation();
                progressBar.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.GONE);
            }else {
                description.setText("完成刷新");
                progressBar.setVisibility(View.VISIBLE);
                arrow.clearAnimation();
                arrow.setVisibility(View.GONE);
            }
            refresh_update();
        }
    }

    private void rotateArrow() {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if (currenstatus == pulltorefresh) {
            fromDegrees = 180f;
            toDegrees = 360f;
        } else if (currenstatus == releasetorefresh) {
            fromDegrees = 0f;
            toDegrees = 180f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(100);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }

    public void finishRefreshing() {
        currenstatus = finishrefresh;
        sharedPreferences.edit().putLong("updatetime", System.currentTimeMillis()).commit();
        new HideviewTask().execute();
    }

    private void setisabletopull(MotionEvent event) {
        View ChildView = listView.getChildAt(0);
        if (ChildView!=null){
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            if (firstVisiblePosition == 0&&ChildView.getTop()==0){
                if (!abletopull){
                    yDown = event.getRawY();
                }
                abletopull = true;
            }else {
                if (viewLayoutParams.topMargin != hideviewheight) {
                    viewLayoutParams.topMargin = hideviewheight;
                    view.setLayoutParams(viewLayoutParams);
                }
                abletopull = false;
            }
        }else {
            abletopull = true;
        }
    }

    private void refresh_update() {

        lastupdateTime = sharedPreferences.getLong("updatetime",-1);
        long currentTime = System.currentTimeMillis();
        long timepassed = currentTime-lastupdateTime;
        long timeintoformat;
        String updatetext;
        if (lastupdateTime == -1){
            updatetext="暂未更新过";
        }else if (timepassed<0){
            updatetext = "时间有问题";
        }else if(timepassed<minute){
            updatetext = "刚刚更新";
        }else if (timepassed<hour){
            timeintoformat = timepassed/minute;
            updatetext = timeintoformat+"分钟前更新";
        }else if (timepassed<day){
            timeintoformat = timepassed/hour;
            updatetext = timeintoformat+"小时前更新";
        }else if (timepassed<month){
            timeintoformat = timepassed/day;
            updatetext = timeintoformat+"天前更新";
        }else if (timepassed<year){
            timeintoformat = timepassed/month;
            updatetext = timeintoformat+"月前更新";
        }else {
            timeintoformat = timepassed/year;
            updatetext = timeintoformat+"年前更新";
        }
        updateTv.setText(updatetext);
    }

    class RefreshingTask extends AsyncTask<Void, Integer, Void> {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... params) {
            int topMargin = viewLayoutParams.topMargin;
            while (true) {
                topMargin = topMargin + scrollspeed;
                if (topMargin <= 0) {
                    topMargin = 0;
                    break;
                }
                publishProgress(topMargin);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currenstatus = refreshing;
            publishProgress(0);
            if (mListener != null) {
                mListener.onRefresh();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            updateviewView();
            viewLayoutParams.topMargin = topMargin[0];
            view.setLayoutParams(viewLayoutParams);
        }

    }


    class HideviewTask extends AsyncTask<Void,Integer,Integer>{

        @Override
        protected Integer doInBackground(Void... voids) {
            int topMargin = viewLayoutParams.topMargin;
            while (true){
                topMargin = topMargin+scrollspeed;
                if (topMargin<=hideviewheight){
                    topMargin = hideviewheight;
                    break;
                }
                publishProgress(topMargin);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return topMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            viewLayoutParams.topMargin = values[0];
            view.setLayoutParams(viewLayoutParams);
        }

        @Override
        protected void onPostExecute(Integer integer) {

            viewLayoutParams.topMargin = integer;
            view.setLayoutParams(viewLayoutParams);
            currenstatus = finishrefresh;
        }
    }

    public interface PullToRefreshListener {
        void onRefresh();
    }
    public void setOnRefreshListener(PullToRefreshListener listener) {
        mListener = listener;

    }
}
