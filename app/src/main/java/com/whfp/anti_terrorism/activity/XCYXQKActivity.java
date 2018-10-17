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
 * 校车运行情况
 * Created by wantao on 2018/6/15.
 */
@ContentView(R.layout.activity_xcyxqk)
public class XCYXQKActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("校车运行情况");
    }

    @Event(value = {R.id.btn_check_route, R.id.btn_check_person})
    private void OnClick(View v){
        switch (v.getId()){
            case R.id.btn_check_route://校车运行路线查看
                RxActivityTool.skipActivity(context, BusRouteActivity.class);
                break;
            case R.id.btn_check_person://乘车人员查看
                RxActivityTool.skipActivity(context, BusPersonActivity.class);
                break;
        }
    }

}
