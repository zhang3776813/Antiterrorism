package com.whfp.anti_terrorism.basic;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
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
 * 下拉刷新上拉加载基类
 * Created by Wantao on 2018/5/8.
 */

public abstract class BasicRecyclerRefreshAndLoadMoreFragment extends BaseFragment implements RefreshCallBack, OnRefreshListener, OnLoadMoreListener {

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

    //当前页码
    protected int currentPage = 1;


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
            RxLogTool.e("BasicRecyclerRefreshAndLoadMoreFragment：列表控件为空，请检查子类是否传入");
            return;
        }
        if (smartRefreshLayout == null) {
            RxLogTool.e("BasicRecyclerRefreshAndLoadMoreFragment：刷新加载控件为空,请检查子类是否传入");
            return;
        }

        //如果子类未设置分割线，就使用默认分割线
        if (recycleViewDivider == null) {
            //横向，高2像素，灰色
            recycleViewDivider = new RecycleViewDividerUtils(mContext, LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.cj_gary));
        }
        //如果子类为设置布局管理器，就使用默认布局管理器
        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        }
        /**
         * 设置RecyclerView
         */
        //设置布局管理器
        rv_recycler.setLayoutManager(linearLayoutManager);
        //添加分割线
        rv_recycler.addItemDecoration(recycleViewDivider);
        /**
         * 设置刷新加载布局
         */
        //设置刷新布局
        smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(mContext));
        //设置加载更多布局
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        //设置Header的主体颜色(Fragment中才需要在这里设置，Activity中直接在XML中设置就好)
        smartRefreshLayout.setPrimaryColorsId(R.color.login_text_blue);
        //刷新时禁止操作内容视图
        smartRefreshLayout.setDisableContentWhenRefresh(false);
        //加载时禁止操作内容视图
        smartRefreshLayout.setDisableContentWhenLoading(false);
        //内容不满一页时禁止上拉加载
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //Header标准高度（显示下拉高度>=标准高度 触发刷新）
        smartRefreshLayout.setHeaderHeight(70);
        //Footer标准高度（显示上拉高度>=标准高度 触发加载）
        smartRefreshLayout.setFooterHeight(70);
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);
        //设置进入自动刷新
//        refreshLayout.autoRefresh();
    }

    /**
     * 获取数据  加载类型
     *
     * @param type
     */
    protected abstract void getDatas(int type);

    /**
     * 初始化设备列表适配器
     */
    private void initAdapter() {
        adapter = (MyBaseRecyclerAdapter) getAdapter();
        //设置空布局
        adapter.setEmptyView(initEmptyView(mContext, R.layout.view_prompt, null));
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
     * 刷新加载处理加载处理
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
                    RxLogTool.i("进入刷新");
                    //如果有数据先清除
                    if (list_data != null && list_data.size() > 0) {
                        list_data.clear();
                    }
                    //判断是否设置了Adapter,如果没有就设置Adapter
                    if (rv_recycler.getAdapter() == null) {
                        initAdapter();
                    }
                    list_data = list;
                    //为Adapter设置新数据
                    adapter.setNewData(list_data);
                    //刷新成功，收回刷新布局
                    smartRefreshLayout.finishRefresh(true);
                    //设置允许上拉加载啦
                    smartRefreshLayout.setEnableLoadMore(true);
                    break;
                case Constants.LOADMORE://加载更多
                    if (list == null || list.size() <= 0) {//数据为空
                        currentPage--;
                        //数据全部加载完毕
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    } else if (list.size() < Constants.PAGE_SIZE) {//数据小于规定的每页数量，则没有更多数据了
                        //添加新数据数据至数据源中
                        list_data.addAll(list);
                        //数据全部加载完毕
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                        //刷新Adapter
                        adapter.notifyDataSetChanged();
                    } else {
                        //添加新数据数据至数据源中
                        list_data.addAll(list);
                        //加载成功，收回加载布局
                        smartRefreshLayout.finishRefresh(true);
                        //刷新Adapter
                        adapter.notifyDataSetChanged();
                    }
                    //设置允许下拉刷新啦
                    smartRefreshLayout.setEnableRefresh(true);
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
                    //设置允许上拉加载啦
                    smartRefreshLayout.setEnableLoadMore(true);
                    //刷新Adapter数据
                    adapter.notifyDataSetChanged();
                    RxToast.error("暂无数据");
                    break;
                case Constants.LOADMORE://加载更多
                    currentPage--;
                    //数据全部加载完毕
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    //设置允许下拉刷新啦
                    smartRefreshLayout.setEnableRefresh(true);
                    break;
            }
        }
    }

    //刷新
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        //页码归为1
        currentPage = 1;
        //刷新时禁止加载更多
        refreshLayout.setEnableLoadMore(false);
        //将没有数据的状态改为有数据
        refreshLayout.setNoMoreData(false);
        refreshLayout.finishRefresh(5000);//延迟5秒后结束刷新
        getDatas(Constants.REFRESH);
    }

    //加载更多
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        currentPage++;
        //加载时禁止刷新
        refreshLayout.setEnableRefresh(false);
        refreshLayout.finishLoadMore(5000);//延迟5秒后结束加载
        getDatas(Constants.LOADMORE);
    }
}
