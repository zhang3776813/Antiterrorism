package com.whfp.anti_terrorism.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BaseActivity;
import com.whfp.anti_terrorism.basic.BaseCallback;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.SchoolBusGpsBean;
import com.whfp.anti_terrorism.bean.SchoolKeyBean;
import com.whfp.anti_terrorism.utils.PathSmoothTool;
import com.whfp.anti_terrorism.utils.StatusBarUtils;
import com.whfp.anti_terrorism.utils.TimeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 校车运行路线查看
 * Created by 张明杨 on 2018-10-29-0029.
 */
@ContentView(R.layout.activity_bus_route)
public class SchoolBusRouteActivity extends BaseActivity implements OnDateSetListener {

    //地图控件
    @ViewInject(R.id.map)
    private MapView mMapView;

    //当前查看的校车
    @ViewInject(R.id.tv_school_number)
    private TextView tv_school_number;

    //起始时间
    @ViewInject(R.id.et_start_time)
    private EditText et_start_time;

    //结束时间
    @ViewInject(R.id.et_end_time)
    private EditText et_end_time;

    //查询按钮
    @ViewInject(R.id.btn_search_track)
    private Button btn_search_track;

    //地图操作对象
    private AMap aMap;
    //地图可视区域
    private CameraUpdate cameraUpdate;
    //地图上画线对象
    private Polyline mOriginPolyline, mkalmanPolyline;
    //添加点标记
    private Marker start_point, endpoint;
    //校车Web平台的Key
    private String key = "";
    //1年的时间的毫秒数
    private long oneYears = 1L * 365 * 1000 * 60 * 60 * 24L;
    //起始时间选择器对象
    private TimePickerDialog timePickerDialog_start;
    //截止时间选择器对象
    private TimePickerDialog timePickerDialog_end;
    //格式化时间
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //轨迹坐标点集合
    private List<LatLng> latLngList;

    //传递过来的校车车牌和校车设备ID
    private String cp, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        StatusBarUtils.with(this).init();
//        //获取地图控件引用
//        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();
        cp = getIntent().getStringExtra(Constants.SCHOOL_BUS_NUMBER);
        id = getIntent().getStringExtra(Constants.SCHOOL_DEVICE_ID);
        tv_school_number.setText(tv_school_number.getText().toString().trim() + cp);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //改变可视区域为指定位置
        //北纬39.22，东经116.39，为负则表示相反方向
        LatLng latLng = new LatLng(30.78429, 115.399222);
        //CameraPosition4个参数分别为位置，缩放级别，目标可视区域倾斜度，可视区域指向方向（正北逆时针算起，0-360）
        cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 15, 0, 30));
        //地图移向指定区域
        aMap.moveCamera(cameraUpdate);
        //获取认证KEy
        httpKey();
        //初始化起始时间选择器
        timePickerDialog_start = TimeUtils.initTimePickerDialog(context, "起始时间",this, System.currentTimeMillis() - oneYears, System.currentTimeMillis());
    }

    /**
     * 连接网络  获取校车Web平台的Key
     */
    private void httpKey() {
        startRxLodingDialog();
        httpUtils.doKey(new BaseCallback(context, Constants.SCHOOL_KEY) {
            @Override
            public void showDatas(Object obj) {
                super.showDatas(obj);
                stopRxLodingDialog();
                SchoolKeyBean bean = (SchoolKeyBean) obj;
                if (bean != null && bean.getErrorcode() == 200) {
                    key = bean.getData().getKey();
                } else {
                    showAlertDialog("提示", "认证失败，请检查网络或稍后重试", "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    });
                }
            }
        });
    }

    /**
     * 连接网络   获取GPS信息
     */
    private void httpGetGPS() {
        startRxLodingDialog();
        httpUtils.doGPSDetail(key, id, et_start_time.getText().toString().trim(), et_end_time.getText().toString().trim(), new BaseCallback(context, Constants.SCHOOL_GPS) {
                    @Override
                    public void showDatas(Object obj) {
                        super.showDatas(obj);
                        stopRxLodingDialog();
                        SchoolBusGpsBean busGpsBean = (SchoolBusGpsBean) obj;
                        if (busGpsBean != null && busGpsBean.getErrorcode() == 200 && busGpsBean.getData() != null && busGpsBean.getData().size() > 0) {
                            updateMap(busGpsBean.getData());
                        } else {
                            RxToast.info("未获取到GPS数据");
                        }
                    }
                });
    }



    /**
     * 根据GPS数据源更新校车运行轨迹
     *
     * @param data
     */
    private void updateMap(List<SchoolBusGpsBean.DataBean> data) {
        LatLng latLng;
        if (latLngList != null) {
            if (latLngList.size() > 0) {
                latLngList.clear();
            }
        } else {
            latLngList = new ArrayList<>();
        }
        for (SchoolBusGpsBean.DataBean dataBean : data) {
            latLng = new LatLng(Double.parseDouble(dataBean.getGpslat()), Double.parseDouble(dataBean.getGpslng()));
            latLngList.add(latLng);
        }
        PathSmoothTool pathSmoothTool = new PathSmoothTool();
        //设置平滑处理的等级
        pathSmoothTool.setIntensity(4);
        List<LatLng> pathoptimizeList = pathSmoothTool.pathOptimize(latLngList);
        //清除所有的覆盖物
        aMap.clear();
        //绘制轨迹，移动地图显示
        if (latLngList != null && latLngList.size() > 0) {
            //添加起点
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_start);
            start_point = aMap.addMarker(new MarkerOptions().draggable(false).icon(bitmapDescriptor).position(latLngList.get(0)));
            //添加轨迹
            mOriginPolyline = aMap.addPolyline(new PolylineOptions().addAll(latLngList).color(Color.GREEN));
            aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getBounds(latLngList), 200));
            //添加终点
            BitmapDescriptor bitmapDescriptor1 = BitmapDescriptorFactory.fromResource(R.mipmap.icon_end);
            endpoint = aMap.addMarker(new MarkerOptions().draggable(false).icon(bitmapDescriptor1).position(latLngList.get(latLngList.size() - 1)));
        }
    }


    /**
     * 验证表单
     *
     * @return
     */
    private boolean check() {
        String s = et_start_time.getText().toString().trim();
        String e = et_end_time.getText().toString().trim();
        if ((s.equals("") || s.length() <= 0) || (e.equals("") || e.length() <= 0)) {
            RxToast.info("请填写起始和结束时间");
            return false;
        } else {
            return true;
        }
    }

    @Event(value = {R.id.et_start_time, R.id.et_end_time, R.id.btn_search_track})
    private void OnClick(View v) {
        switch (v.getId()) {
            case R.id.et_start_time://开始时间
                timePickerDialog_start.show(getSupportFragmentManager(), "start");
                break;
            case R.id.et_end_time://结束时间
                if (et_start_time.getText().toString().trim().equals("")) {
                    RxToast.info("请先填写起始时间");
                } else {
                    RxLogTool.i("起始时间：" + et_start_time.getText().toString().trim());
                    timePickerDialog_end = TimeUtils.initTimePickerDialog(context, "结束时间",this, TimeUtils.dataTwo(et_start_time.getText().toString().trim()),
                            System.currentTimeMillis());
                    timePickerDialog_end.show(getSupportFragmentManager(), "end");
                }
                break;
            case R.id.btn_search_track://查询轨迹
                if (check() && TimeUtils.verificationTime(et_start_time.getText().toString().trim(), et_end_time.getText().toString().trim())) {
                    httpGetGPS();
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        switch (timePickerView.getTag()) {
            case "start"://起始时间
                et_start_time.setText(getDateToString(millseconds));
                break;
            case "end"://截止时间
                et_end_time.setText(getDateToString(millseconds));
                break;
        }
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    private LatLngBounds getBounds(List<LatLng> pointlist) {
        LatLngBounds.Builder b = LatLngBounds.builder();
        if (pointlist == null) {
            return b.build();
        }
        for (int i = 0; i < pointlist.size(); i++) {
            b.include(pointlist.get(i));
        }
        return b.build();

    }
}
