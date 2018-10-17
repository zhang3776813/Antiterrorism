package com.whfp.anti_terrorism.activity;

import android.os.Bundle;

import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;

/**
 * 关于
 * Created by wantao on 2018/6/15.
 */
@ContentView(R.layout.activity_about)
public class AboutActivity extends BasicActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("关于");
    }
}
