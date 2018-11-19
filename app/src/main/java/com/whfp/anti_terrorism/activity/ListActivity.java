package com.whfp.anti_terrorism.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicRecyclerRefreshAndLoadMoreActivity;
import com.whfp.anti_terrorism.bean.Constants;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 选择对应的列表
 * Created by 张明杨 on 2018-09-05-0005.
 */
@ContentView(R.layout.activity_recycler_universal_refresh_and_loadmore)
public class ListActivity extends BasicRecyclerRefreshAndLoadMoreActivity {

    //历史监控录像列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;

    //下拉刷新上拉加载控件
    @ViewInject(R.id.refreshLayout)
    private SmartRefreshLayout refreshLayout;

    //列表类型
    private int listType = -1;

    @Override
    protected void init() {
        super.init();
        listType = getIntent().getIntExtra(Constants.LIST_TYPE, -1);
        if (listType == -1) {
            finish();
        } else {
            switch (listType) {
                case 1://加油站
                    break;
                case 2://寄递物流
                    break;
                case 3://校园
                    break;
                case 4://水电油气
                    break;
                case 5://党政机关
                    break;
                case 6://化工企业
                    break;
                case 7://重点单位
                    break;
            }
        }
    }

    @Override
    public SmartRefreshLayout getRefreshLayout() {
        return null;
    }

    @Override
    public RecyclerView getRecycle() {
        return null;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return null;
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
    public void addOthers() {

    }

    @Override
    protected void getDatas(int type) {

    }
}
