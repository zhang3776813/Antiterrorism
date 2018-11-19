package com.whfp.anti_terrorism.basic;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.adapter.MyBaseRecyclerAdapter;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.callback.RefreshCallBack;
import com.whfp.anti_terrorism.utils.RecycleViewDividerUtils;

import java.util.List;

/**
 * 下拉刷新基类(没有加载更多功能)
 * Created by 张明杨 on 2018-05-07-0007.
 */
public abstract class BasicRecyclerRefreshActivity extends BasicActivity implements RefreshCallBack, OnRefreshListener, OnLoadMoreListener {

    //列表控件
    protected RecyclerView rv_recycler;

    //列表适配器
    private MyBaseRecyclerAdapter adapter;

    //列表数据源
    protected List list_data;

    //下拉刷新上拉加载控件
    protected SmartRefreshLayout smartRefreshLayout;

    //列表分割线
    protected RecycleViewDividerUtils recycleViewDivider;

    //布局管理器
    protected LinearLayoutManager linearLayoutManager;


    @IntDef({Constants.FIRSTLOAD, Constants.REFRESH})
    public @interface Type {
    }


    /**
     * 获取数据
     *
     * @param type 加载类型
     */
    protected abstract void getDatas(@Type int type);


    /**
     * 初始化
     */
    @Override
    protected void init() {
        super.init();
        //获取列表控件
        rv_recycler = getRecycle();
        //获取刷新加载控件
        smartRefreshLayout = getRefreshLayout();
        //获取分割线
        recycleViewDivider = (RecycleViewDividerUtils) getItemDecoration();
        //获取布局管理器
        linearLayoutManager = getLinearLayoutManager();


        if (rv_recycler == null) {
            RxLogTool.e("BasicRecyclerRefreshAndLoadMoreActivity：列表控件为空，请检查子类是否传入");
            return;
        }
        if (smartRefreshLayout == null) {
            RxLogTool.e("BasicRecyclerRefreshAndLoadMoreActivity：刷新加载控件为空,请检查子类是否传入");
            return;
        }

        //如果子类未设置分割线，就使用默认分割线
        if (recycleViewDivider == null) {
            //横向，高2像素，灰色
            recycleViewDivider = new RecycleViewDividerUtils(context, LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.cj_gary));
        }
        //如果子类为设置布局管理器，就使用默认布局管理器
        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        }
        /**
         * 设置recycler
         */
        //设置布局管理器
        rv_recycler.setLayoutManager(linearLayoutManager);
        //添加分割线
        rv_recycler.addItemDecoration(recycleViewDivider);
        /**
         * 设置是刷新控件
         */
        //禁用上拉加载更多
        smartRefreshLayout.setEnableLoadMore(false);
        //设置刷新布局
        smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(context));
        //刷新时禁止操作内容视图
        smartRefreshLayout.setDisableContentWhenRefresh(false);
        //Header标准高度（显示下拉高度>=标准高度 触发刷新）
        smartRefreshLayout.setHeaderHeight(70);
        //Footer标准高度（显示上拉高度>=标准高度 触发加载）
        smartRefreshLayout.setFooterHeight(70);
        //设置刷新回调
        smartRefreshLayout.setOnRefreshListener(this);
        //设置进入自动刷新
//        refreshLayout.autoRefresh();
    }

    /**
     * 初始化设备列表适配器
     */
    private void initAdapter() {
        adapter = (MyBaseRecyclerAdapter) getAdapter();
        //设置空布局
        adapter.setEmptyView(initEmptyView(context, R.layout.view_prompt, null));
        //开启加载动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        rv_recycler.setAdapter(adapter);
    }

    /**
     * 初始化空布局
     *
     * @param context
     * @param layoutId
     * @param listener
     * @return
     */
    private View initEmptyView(Context context, int layoutId, View.OnClickListener listener) {
        View emptyView = View.inflate(context, layoutId, null);
        emptyView.setOnClickListener(listener);
        return emptyView;
    }


    /**
     * 刷新处理
     *
     * @param list
     * @param loadType
     */
    public void dataProcess(List list, int loadType) {
        if (list != null && list.size() > 0) {
            switch (loadType) {
                case Constants.FIRSTLOAD://首次加载
                    list_data = list;
                    initAdapter();
                    break;
                case Constants.REFRESH://刷新
                    logI("进入刷新");
                    //如果有数据先清除
                    if (list_data != null && list_data.size() > 0) {
                        list_data.clear();
                    }
                    //判断是否设置了Adapter,如果没有就设置Adapter
                    if (rv_recycler.getAdapter() == null) {
                        initAdapter();
                    }
                    list_data = list;
                    RxLogTool.i("条数："+list_data.size());
                    //为Adapter设置新数据
                    adapter.setNewData(list_data);
                    //刷新成功，收回刷新布局
                    smartRefreshLayout.finishRefresh(true);
                    break;
            }
        } else {
            switch (loadType) {
                case Constants.FIRSTLOAD://首次加载:
                    list_data = list;
                    initAdapter();
                    break;
                case Constants.REFRESH://刷新
                    list_data = list;
                    //判断是否设置了Adapter
                    if (rv_recycler.getAdapter() == null) {
                        //如果没有就设置Adapter
                        initAdapter();
                    }
                    adapter.setNewData(list_data);
                    //刷新失败，收回刷新布局
                    smartRefreshLayout.finishRefresh(false);
                    //刷新Adapter数据
                    adapter.notifyDataSetChanged();
                    RxToast.error("暂无数据");
                    break;
            }
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

    }

    //刷新
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
//        //将没有数据的状态改为有数据
//        refreshLayout.setNoMoreData(false);
        refreshLayout.finishRefresh(5000);//延迟5秒后结束刷新
        getDatas(Constants.REFRESH);
    }
}
