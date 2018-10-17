package com.whfp.anti_terrorism.activity;

import android.os.Bundle;
import android.view.View;

import com.vondear.rxtools.RxActivityTool;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 排污情况
 * Created by wantao on 2018/6/14.
 */
@ContentView(R.layout.activity_pwqk)
public class PWQKActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("排污情况");
    }


    @Event(value = R.id.btn_submit)
    private void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_submit://历史录像查看
                RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                break;
        }
    }

}
