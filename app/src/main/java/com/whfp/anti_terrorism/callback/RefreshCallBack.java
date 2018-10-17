package com.whfp.anti_terrorism.callback;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Recycler下拉刷新上拉加载需要实现的接口
 * Created by 张明杨 on 2018-05-07-0007.
 */

public interface RefreshCallBack {

    /**
     * 获取刷新和上拉控件
     * @return
     */
    SmartRefreshLayout getRefreshLayout();

    /**
     * 获取RecyclerView
     * @return
     */
    RecyclerView getRecycle();

    /**
     * 获取Adapter
     *
     * @return
     */
    BaseQuickAdapter getAdapter();

    /**
     * 获取分割线
     * @return
     */
    RecyclerView.ItemDecoration getItemDecoration();

    /**
     * 获取布局管理器
     * @return
     */
    LinearLayoutManager getLinearLayoutManager();

    /**
     * 分组布局加载头部和尾部，实现此接口
     */
    void addOthers();

}
