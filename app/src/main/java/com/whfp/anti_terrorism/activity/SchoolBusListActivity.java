package com.whfp.anti_terrorism.activity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ceiba.apis.CeibaAPIs;
import com.ceiba.apis.IRegisterIOTCListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vondear.rxtools.RxLogTool;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.SchoolBusListBean;
import com.whfp.anti_terrorism.factory.AdapterFactory;
import com.whfp.anti_terrorism.utils.ParserUtils;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 校车列表
 * Created by 张明杨 on 2018-10-16-0016.
 */
@ContentView(R.layout.activity_recycler_universal)
public class SchoolBusListActivity extends BasicActivity implements IRegisterIOTCListener {

    //校车设备列表
    @ViewInject(R.id.rv_recycler)
    private RecyclerView rv_recycler;

    //校车设备列表适配器
    private BaseQuickAdapter adapter;

    //布局管理器
    protected LinearLayoutManager linearLayoutManager;

    //校车夫服务登录标识，为0则是未登录
    private int mHandle = 0;
    //现车服务API对象
    private CeibaAPIs apis = null;
    //校车服务登录异步Task对象
    private UserLoginTask mAuthTask = null;
    //json解析工具类
    private ParserUtils parserUtils;
    //校车设备列表数据源
    private List<SchoolBusListBean.DeviceBean> deviceBeans;
    //在线的校车车牌号列表
    private List<String> devOnlines;
    //UI更新状态码
    private final static int LOAD_LIST = 11;//加载列表
    private final static int UPDATE_LIST = 12;//更新列表


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD_LIST://加载列表
                    updateView();
                    break;
                case UPDATE_LIST://更新列表
                    RxLogTool.i("为什么这里没执行");
                    updateList();
                    break;
            }
        }
    };

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
        setTitleText("校车列表");
        //登录校车服务
        login();

    }


    /**
     * 获取在线校车列表
     */
    private void getDevOnline() {
        if (mHandle != 0) {
            apis.StartRecvDevOnline(mHandle);
        } else {
            RxLogTool.e("未登录校车服务");
        }
    }

    /**
     * 校车列表解析
     *
     * @param result
     * @return
     */
    private List<SchoolBusListBean.DeviceBean> parserSchoolBusList(String result) {
        if (parserUtils == null) {
            parserUtils = new ParserUtils(this);
        }
        SchoolBusListBean schoolBusListBean = (SchoolBusListBean) parserUtils.parseAllByType(result, Constants.SCHOOL_BUS_LIST);
        if (schoolBusListBean != null && schoolBusListBean.getDevice().size() > 0) {
            return schoolBusListBean.getDevice();
        } else {
            return null;
        }
    }

    /**
     * 在线校车列表解析
     */
    private List<String> parserSchoolBusListOnline(String result) {
        List<String> list = new ArrayList<>();
        if (parserUtils == null) {
            parserUtils = new ParserUtils(this);
        }
        try {
            JSONObject jObject1 = new JSONObject(result);
            //再转JsonArray 加上数据头
            JSONArray jsonArray = jObject1.getJSONArray("terid");
            RxLogTool.i(jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                String string = jsonArray.getString(i);
                //添加到集合里面去
                list.add(string);
            }
            RxLogTool.i("个数：" + list.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    /**
     * 加载校车列表数据
     */
    private void updateView() {
        RxLogTool.i("执行");
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_recycler.setLayoutManager(linearLayoutManager);
        adapter = AdapterFactory.getAdapterByType(context, Constants.ADAPTER_XCLB, deviceBeans, null, null);
        rv_recycler.setAdapter(adapter);
        getDevOnline();
    }

    /**
     * 更新校车列表
     */
    private void updateList() {
        RxLogTool.i("执行更新");
        if (deviceBeans == null || deviceBeans.size() <= 0) {
            return;
        }
        for (int i = 0; i < deviceBeans.size(); i++) {
            SchoolBusListBean.DeviceBean dbs = deviceBeans.get(i);
            for (int j = 0; j < devOnlines.size(); j++) {
                if (dbs.getDeviceid().equals(devOnlines.get(j))) {
                    dbs.setStatus(1);
                    RxLogTool.i("修改之后：" + dbs.getStatus());
                }
            }
        }
        adapter.notifyDataSetChanged();
        RxLogTool.i("执行完毕");
    }


    /**
     * 校车服务登录
     */
    private void login() {
        String username = "admin";
        String password = "123456";
        String ip = "119.96.239.107";
        int port = 7264;

        if (mAuthTask != null) {
            return;
        }
        mAuthTask = new UserLoginTask(ip, port, username, password);
        mAuthTask.execute((Void) null);
    }

    /**
     * 校车服务登出
     */
    public void logout() {
        if (apis != null) {
            if (mHandle != 0) {
                apis.unregisterIOListener(this);
                apis.LogoutServer(mHandle);
                mHandle = 0;
            }
            apis = null;
        }

    }


    /**
     * 校车服务登录异步操作
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {
        private String ip;
        private String username;
        private String password;
        private int port;

        public UserLoginTask(String ip, int port, String username, String password) {
            this.ip = ip;
            this.port = port;
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startRxLodingDialog();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
//            if (apis != null) {
//                //如果校车服务已登录就退出
//                if (mHandle != 0) {
//                    apis.LogoutServer(mHandle);
//                    mHandle = 0;
//                }
//                apis = null;
//            }
            apis = new CeibaAPIs();
            apis.registerIOListener(SchoolBusListActivity.this);
            String[] response = new String[1];
            mHandle = apis.LoginServer(ip, port, 1, username, password, 10, response);
            return mHandle;
        }

        @Override
        protected void onPostExecute(final Integer mHandle) {
            super.onPostExecute(mHandle);
            if (mHandle != 0) {
                RxLogTool.i("校车服务登录成功！");
                //延时3秒获取校车列表
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        apis.GetDevSiteTree(mHandle);
                        stopRxLodingDialog();
                    }
                }, 3000);
            } else {
                RxLogTool.e("校车服务登录失败！");
                showAlertDialog("提示", "校车服务登录失败，请重试！", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
            }
            if (mAuthTask != null) {
                mAuthTask = null;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        logout();
    }

    /**
     * 校车服务回调接口
     *
     * @param cmd
     * @param data
     * @param len
     */
    @Override
    public void receviceSessionInfo(int cmd, byte[] data, int len) {
        String command = new String(data, 0, len, Charset.forName("utf-8"));
        RxLogTool.i("返回的原始数据：" + command);
        switch (cmd) {
            case CeibaAPIs.CLTDA_MSG_OPERATION_RET://多种信息请求操作反馈
                try {
                    JSONObject ret = new JSONObject(command);
                    String key = ret.getString("key");
                    RxLogTool.i("接收到的Key：" + key);
                    if (key.contentEquals("getbalanceinfo")) {//均衡服务器信息

                    } else if (key.contentEquals("getdevsitetree")) {//车辆设备列表信息
                        if (ret.has("response")) {
                            final JSONObject resp = ret.getJSONObject("response");
                            RxLogTool.i("设备信息" + resp);
                            //解析车辆列表
                            deviceBeans = parserSchoolBusList(resp + "");

                            Message message = new Message();
                            message.what = LOAD_LIST;
                            handler.sendMessage(message);

                        }
                    } else if (key.contentEquals("queryofflinealm")) {//离线报警信息

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case CeibaAPIs.CLTDA_MSG_DEV_OL_STATUS://获取到的车辆在线信息
                RxLogTool.i("hahahhahaha" + command);
                if (command.indexOf("\"terid\":null") == -1) {
                    RxLogTool.i("1111111111111111111111111");
                    devOnlines = parserSchoolBusListOnline(command);
                    RxLogTool.i("22222222222222222222222222222");

                    Message message = new Message();
                    message.what = UPDATE_LIST;
                    handler.sendMessage(message);
                } else {
                    RxLogTool.i("在线校车为空！");
                }
                break;
            case CeibaAPIs.CLTDA_MSG_ALM_MSG://获取到的报警信息
                break;
            case CeibaAPIs.CLTDA_MSG_GPS_MSG://获取到的gps信息
                break;
        }
    }

}
