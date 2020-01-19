package com.lenovo.smarttraffic.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class DialogDatePicker extends AlertDialog implements
        DialogInterface.OnClickListener, DatePicker.OnDateChangedListener {

    private static final String YEAR = "year";

    private static final String MONTH = "month";

    private static final String DAY = "day";


    private DatePicker picker;
    private OnDateSetListener mSListener;
    private int i;


    /******
     * 日期选择回掉事件
     */
    public interface OnDateSetListener {

        void onDateSet(int i,int year, int month,

                       int day);
    }

    public void setOnSettingListener(OnDateSetListener listener) {
        mSListener = listener;
    }
    public DialogDatePicker(Context context, int year, int month,int day,int i) {
        //        super(context, AlertDialog.THEME_HOLO_DARK);
        //        super(context, AlertDialog.THEME_HOLO_LIGHT);
        //        super(context,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        //       super(context,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        super(context, AlertDialog.THEME_HOLO_LIGHT);
        this.i = i;

        Context themeContext = getContext();
//        setButton(BUTTON_POSITIVE, "ensure", this);
//
//        setButton(BUTTON_NEGATIVE, "cancel", this);

        setIcon(0);

        LayoutInflater inflater =(LayoutInflater)themeContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_datepicker, null);

        setView(view);

        picker = (DatePicker) view.findViewById(R.id.datepick);
        TextView mSure = view.findViewById(R.id.dialog7_sure);
        TextView mQuit = view.findViewById(R.id.dialog7_quit);
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryNotifyDateSet();
                dismiss();
            }
        });
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        picker.init(year, month, day, this);


    }
    @Override
    public void onClick(DialogInterface dialog, int which) {

        if (which == BUTTON_POSITIVE)
            tryNotifyDateSet();

    }

    private void tryNotifyDateSet() {

        if (mSListener != null) {
            picker.clearFocus();
            //月份要加1
            mSListener.onDateSet( i,picker.getYear(), picker.getMonth() + 1, picker.getDayOfMonth());
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int
            monthOfYear, int dayOfMonth) {

        if (view.getId() == R.id.datepick)
            picker.init(year, monthOfYear, dayOfMonth, this);
    }

}
