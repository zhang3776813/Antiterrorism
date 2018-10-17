package com.whfp.anti_terrorism.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.factory.AdapterFactory;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 加油站主页
 * Created by 张明杨 on 2018-06-13-0013.
 */
@ContentView(R.layout.activity_recycler_universal)
public class GASActivity extends BasicActivity {

    //菜单列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("加油站");
        initMenu();
    }

    /**
     * 初始化菜单列表
     */
    private void initMenu() {
        rv_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rv_recycler.setAdapter(AdapterFactory.getAdapterByType(context, Constants.ADAPTER_GAS_MENU,null,null,null));
    }
}
