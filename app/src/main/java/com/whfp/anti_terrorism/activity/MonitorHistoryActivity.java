package com.whfp.anti_terrorism.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vondear.rxtools.RxActivityTool;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicRecyclerRefreshAndLoadMoreActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.factory.AdapterFactory;
import com.whfp.anti_terrorism.factory.MyFactory;
import com.whfp.anti_terrorism.utils.RecycleViewDividerUtils;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 历史监控录像列表
 * Created by 张明杨 on 2018-06-14-0014.
 */
@ContentView(R.layout.activity_recycler_universal_refresh_and_loadmore)
public class MonitorHistoryActivity extends BasicRecyclerRefreshAndLoadMoreActivity {

    //历史监控录像列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;

    //下拉刷新上拉加载控件
    @ViewInject(R.id.refreshLayout)
    private SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.with(this)
                .init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("历史监控录像");
        getDatas(Constants.FIRSTLOAD);
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
        return AdapterFactory.getAdapterByType(context, Constants.ADAPTER_LSJK, list_data,
                new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                    }
                }, null,null);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecycleViewDividerUtils(context, LinearLayoutManager.HORIZONTAL, 0, getResources().getColor(R.color.cj_gary));
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return null;
    }

    @Override
    public void addOthers() {

    }

    @Override
    protected void getDatas(int type) {
        dataProcess(MyFactory.getBaseListDatas(Constants.LSJK_LIST), type);
    }
}
