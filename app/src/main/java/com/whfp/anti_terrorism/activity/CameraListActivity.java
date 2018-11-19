package com.whfp.anti_terrorism.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hik.apigatephonedemo.ApiGate;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicRecyclerRefreshActivity;
import com.whfp.anti_terrorism.bean.CameraBean;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.HlsBean;
import com.whfp.anti_terrorism.factory.AdapterFactory;
import com.whfp.anti_terrorism.utils.ParserUtils;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 海康监控点列表
 * Created by 张明杨 on 2018-10-16-0016.
 */
@ContentView(R.layout.activity_recycler_universal_refresh_and_loadmore)
public class CameraListActivity extends BasicRecyclerRefreshActivity {

    //海康监控点列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;

    //下拉刷新上拉加载布局
    @ViewInject(R.id.refreshLayout)
    private SmartRefreshLayout refreshLayout;

    //异步分页获取全部的组织树
    public FindCameraPageTask findCameraPageTask;
    //操作类型
    private int operateType;
    //监控点原始数据
    private CameraBean cameraBean;
    //监控点列表数据源
    private List<CameraBean.DataBean> dataBeanListCamer;

    //传递过来的组织区域ID
    private String controlId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.with(this)
                .init();
    }

    @Override
    protected void getDatas(int type) {
        //分页获取监控点列表
        findCameraPageTask = new FindCameraPageTask(controlId, "0", "1000", type);
        findCameraPageTask.execute();
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        setTitleText(intent.getStringExtra(Constants.HK_CONTROL_NAME));
        //获取传递过来的操作类型
        operateType = intent.getIntExtra(Constants.HK_OPERATE_TYPE, 0);
        //获取传递过来的㢟区域ID
        controlId = intent.getStringExtra(Constants.CONTROL_INDEX_CODE);
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
        return AdapterFactory.getAdapterByType(context, Constants.ADAPTER_HK_CAMERA_LIST, list_data, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CameraBean.DataBean dataBean = (CameraBean.DataBean) list_data.get(position);
                RxLogTool.i("这是啥：" + dataBean.getName());
                new HLSByIndexCodeTask().execute(dataBean.getIndexCode());
            }
        }, null, null);
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
     * 获取海康监控点列表异步操作
     */
    public class FindCameraPageTask extends AsyncTask<Void, Void, String> {
        private String indexCode;
        private String start;
        private String size;
        private int loadType;

        public FindCameraPageTask(String indexCode, String start, String size, int loadType) {
            this.indexCode = indexCode;
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
            return ApiGate.getInstance().findCameraInfoPageByTreeNode(indexCode, start, size);
        }

        @Override
        protected void onPostExecute(final String mHandle) {
            super.onPostExecute(mHandle);
            stopRxLodingDialog();

            cameraBean = (CameraBean) new ParserUtils(CameraListActivity.this).parseAllByType(mHandle, Constants.HK_JKD_LIST);
            if (cameraBean != null && cameraBean.getData() != null && cameraBean.getData().size() > 0) {
                dataBeanListCamer = cameraBean.getData();

                dataProcess(dataBeanListCamer, loadType);
            } else {
                dataProcess(new ArrayList(), loadType);
                RxToast.info("暂无数据");
            }
        }
    }

    public class HLSByIndexCodeTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return ApiGate.getInstance().findPreviewHlsUrlByUserAndCamera(strings[0]);
//             return ApiGate.getInstance().getHLSByIndexCode(strings[0]);
        }

        @Override
        protected void onPostExecute(final String mHandle) {
            super.onPostExecute(mHandle);
            stopRxLodingDialog();
            if (mHandle != null && mHandle.length() > 0) {
                HlsBean bean = (HlsBean) new ParserUtils(context).parseAllByType(mHandle, Constants.HK_HLS_PATH);
                if (bean.getCode().equals("200")) {
                    Intent intent = new Intent(context, MonitorActivity.class);
                    intent.putExtra(Constants.HK_HLS, bean.getPlayrealUrl());
                    startActivity(intent);
                } else {
                    RxToast.info(bean.getMsg());
                }
                RxLogTool.i("获取到的地址是：" + mHandle);
            } else {
                RxToast.info("暂无数据");
            }
        }
    }
}
