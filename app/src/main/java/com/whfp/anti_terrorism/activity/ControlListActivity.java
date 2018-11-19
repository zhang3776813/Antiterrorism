package com.whfp.anti_terrorism.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hik.apigatephonedemo.ApiGate;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicRecyclerRefreshActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.ControlUnitBean;
import com.whfp.anti_terrorism.factory.AdapterFactory;
import com.whfp.anti_terrorism.utils.ParserUtils;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 海康组织或区域列表
 * Created by 张明杨 on 2018-10-16-0016.
 */
@ContentView(R.layout.activity_recycler_universal_refresh_and_loadmore)
public class ControlListActivity extends BasicRecyclerRefreshActivity {

    //海康设备列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;

    //下拉刷新上拉加载布局
    @ViewInject(R.id.refreshLayout)
    private SmartRefreshLayout refreshLayout;

    //异步分页获取全部的组织树
    public FindControlUnitPageTask findControlUnitPageTask;
    //操作类型
    private int operateType;
    //组织树原始数据
    private ControlUnitBean controlUnitBean;
    //组织树列表数据源
    private List<ControlUnitBean.DataBean> dataBeanListControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.with(this)
                .init();
    }

    @Override
    protected void getDatas(int type) {
        //分页获取组织树列表
        findControlUnitPageTask = new FindControlUnitPageTask("0", "1000", type);
        findControlUnitPageTask.execute();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("组织/区域列表");
        //获取传递过来的操作类型
        operateType = getIntent().getIntExtra(Constants.HK_OPERATE_TYPE, 0);
        startRxLodingDialog();
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
        return AdapterFactory.getAdapterByType(context, Constants.ADAPTER_HK_CONTROL_LIST, list_data, null, null, operateType);
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


    /**
     * 海康获取组织列表异步操作
     */
    public class FindControlUnitPageTask extends AsyncTask<Void, Void, String> {
        private String start;
        private String size;
        private int loadType;

        public FindControlUnitPageTask(String start, String size, int loadType) {
            this.start = start;
            this.size = size;
            this.loadType = loadType;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return ApiGate.getInstance().findControlUnitPage(start, size);
        }

        @Override
        protected void onPostExecute(final String mHandle) {
            super.onPostExecute(mHandle);
            stopRxLodingDialog();
            //解析
            controlUnitBean = (ControlUnitBean) new ParserUtils(context).parseAllByType(mHandle, Constants.HK_ZZ_LIST);
            if (controlUnitBean != null && controlUnitBean.getData() != null && controlUnitBean.getData().size() > 0) {
//                dataBeanListControl = controlUnitBean.getData();
                dataBeanListControl = new ArrayList<>();
                for (ControlUnitBean.DataBean dataBean : controlUnitBean.getData()) {
                    if (dataBean.getUnitType() == 2) {
                        dataBeanListControl.add(dataBean);
                    }
                }
                dataProcess(dataBeanListControl, loadType);
            } else {
                dataProcess(new ArrayList(), loadType);
                RxToast.info("暂无数据,请检查网络后重试！");
            }
        }
    }
}
