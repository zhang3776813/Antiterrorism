package com.whfp.anti_terrorism.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicRecyclerRefreshAndLoadMoreActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.factory.AdapterFactory;
import com.whfp.anti_terrorism.factory.MyFactory;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 乘车人员
 * Created by wantao on 2018/6/15.
 */
@ContentView(R.layout.activity_recycler_universal_refresh_and_loadmore)
public class BusPersonActivity extends BasicRecyclerRefreshAndLoadMoreActivity {

    @ViewInject(R.id.refreshLayout)
    private SmartRefreshLayout refreshLayout;

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
        setTitleText("乘车人员");
        getDatas(Constants.FIRSTLOAD);
    }

    @Override
    protected void getDatas(int type) {
        dataProcess(MyFactory.getBaseListDatas(Constants.CCRY_LIST),type);
    }

    @Override
    public SmartRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    @Override
    public RecyclerView getRecycle() {
        return rv_recycler;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return AdapterFactory.getAdapterByType(context, Constants.ADAPTER_CCRY, list_data, null,null,null);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return null;
    }

    @Override
    public void addOthers() {}
}
