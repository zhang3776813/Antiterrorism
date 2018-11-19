package com.whfp.anti_terrorism.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

/**
 * Created by lake on 2016/11/1.
 */

public class YearMonthDatePickerDialog extends DatePickerDialog {

    public YearMonthDatePickerDialog(Context context, int theme, OnDateSetListener callBack,
                                     int year, int monthOfYear, int dayOfMonth) {
        //主题样式最好是指定的样式,比如这样的样式:DatePickerDialog.THEME_HOLO_LIGHT，因为在小米手机中会显示不同的弹窗日期样式甚至有的会报错崩溃
        super(context, DatePickerDialog.THEME_HOLO_LIGHT, callBack, year, monthOfYear, dayOfMonth);
        setTitle(year + "年" + (monthOfYear + 1) + "月");
        ((ViewGroup) ((ViewGroup) (getDatePicker().getChildAt(0))).getChildAt(0)).getChildAt(2)
                .setVisibility(View.GONE);
    }

    public YearMonthDatePickerDialog(Context context, OnDateSetListener callBack, int year,
                                     int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        setTitle(year + "年" + (month + 1) + "月");
    }
}

