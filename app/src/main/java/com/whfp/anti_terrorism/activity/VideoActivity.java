package com.whfp.anti_terrorism.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.dvr.avstream.AVStream;
import com.dvr.avstream.AudioTrackInterface;
import com.dvr.avstream.RealPlayInterface;
import com.dvr.net.DvrNet;
import com.vondear.rxtools.RxLogTool;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BaseCallback;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.SchoolKeyBean;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * 校车监控视频页面
 */
@ContentView(R.layout.activity_video)
public class VideoActivity extends BasicActivity {
    private final String TAG = "VideoActivity";
    private DvrNet mDvrNet;
    private SurfaceView[] surfaceView = new SurfaceView[4];
    private FrameLayout[] frameLayoutsbg = new FrameLayout[4];
    private Button btn_openvoice;
    private AudioTrack trackplayer;
    private final static int MAX_CHANNEL_COUNT = 32;
    public byte[][] mPixel = new byte[MAX_CHANNEL_COUNT][];
    public ByteBuffer[] bytebuffer = new ByteBuffer[MAX_CHANNEL_COUNT];
    public Bitmap[] VideoBlt = new Bitmap[MAX_CHANNEL_COUNT];
    public AVStream[] mAVStream = new AVStream[MAX_CHANNEL_COUNT];
    public int mWidth = 0;
    public int mHeight = 0;
    public boolean[] bMute = new boolean[4];
    private static final int sampleRateInHz = 8000;
    private static final int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
    private static final int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private static final int bufferSize = AudioTrack.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);

    private boolean voice = true;
    //传递过来的校车ID
    private String deviceid;

    private boolean isStopView = false;


    //一下为我自己写的
    //地图控件
    @ViewInject(R.id.map)
    private MapView mMapView;

    //地图操作对象
    private AMap aMap;
    //地图可视区域
    private CameraUpdate cameraUpdate;
    //添加点标记
    private Marker bus_point;
    //返回的GPS坐标
    private LatLng latLng;
    //校车Web平台的Key
    private String key = "";

    //Soket连接对象
    private Socket socket;
    //设备ID列表
    private List<String> deviceId_list;


    /**
     * 更新校车位置
     */
    private void updateView() {
        RxLogTool.i("11111111111111111111111111111111111111");
        if (aMap != null) {
            //清除所有的覆盖物
            aMap.clear();
            //添加标记
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_start);//自定义图标
            bus_point = aMap.addMarker(new MarkerOptions().position(latLng).title("校车").icon(bitmapDescriptor).draggable(false));
            //CameraPosition4个参数分别为位置，缩放级别，目标可视区域倾斜度，可视区域指向方向（正北逆时针算起，0-360）
            cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 15, 0, 30));
            //地图移向指定区域
            aMap.moveCamera(cameraUpdate);
            RxLogTool.i("2222222222222222222222222222222222222222");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.with(this)
                .init();
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("校车监控查看");
        Intent intent = getIntent();
        deviceid = intent.getStringExtra("DEVICEID");
        String serverip = intent.getStringExtra("SERVERIP");
        String rport = intent.getStringExtra("RPORT");
        String vport = intent.getStringExtra("VPORT");
        LoginvideoServer(deviceid, serverip, rport, vport);
        initView();

        //以下为我自己写的
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
    }

    public void initView() {
        //关闭声音 按钮
        btn_openvoice = (Button) findViewById(R.id.btn_next);
        btn_openvoice.setText("关闭声音");
        btn_openvoice.setVisibility(View.VISIBLE);

        surfaceView[0] = (SurfaceView) findViewById(R.id.surfaceview1);
        surfaceView[1] = (SurfaceView) findViewById(R.id.surfaceview2);
        surfaceView[2] = (SurfaceView) findViewById(R.id.surfaceview3);
        surfaceView[3] = (SurfaceView) findViewById(R.id.surfaceview4);
        frameLayoutsbg[0] = (FrameLayout) findViewById(R.id.bg_surf1);
        frameLayoutsbg[1] = (FrameLayout) findViewById(R.id.bg_surf2);
        frameLayoutsbg[2] = (FrameLayout) findViewById(R.id.bg_surf3);
        frameLayoutsbg[3] = (FrameLayout) findViewById(R.id.bg_surf4);
        frameLayoutsbg[0].setVisibility(View.VISIBLE);
        frameLayoutsbg[1].setVisibility(View.GONE);
        frameLayoutsbg[2].setVisibility(View.GONE);
        frameLayoutsbg[3].setVisibility(View.GONE);
        bMute[0] = true;
        btn_openvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开声音
                if (!voice) {
                    openvoice();
                    voice = true;
                    btn_openvoice.setText(getString(R.string.action_close_voice));
                } else {
                    voice = false;
                    closevoice();
                    btn_openvoice.setText(getString(R.string.action_open_voice));
                }
            }
        });
        for (int i = 0; i < 4; i++) {
            final int m = i;
            surfaceView[i].getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    Log.e(TAG, "surfaceCreated: " + m);
                    playvideo(m);
                    openvoice();
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                }
            });
            surfaceView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearbg();
                    frameLayoutsbg[m].setVisibility(View.VISIBLE);
                    bMute[m] = true;
                    if (voice) {
                        openvoice();
                    }
                }
            });
        }
    }

    public void openvoice() {
        if (trackplayer != null) {
            closevoice();
        }
        trackplayer = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, channelConfig, audioFormat, bufferSize,
                AudioTrack.MODE_STREAM);
        for (int i = 0; i < 4; i++) {
            if (mAVStream[i] != null) {
                if (bMute[i]) {
                    mAVStream[i].SetAudioInterface(mAudioTrackInterface);
                    if (trackplayer.getState() != AudioTrack.MODE_STATIC) {
                        trackplayer.play();
                    }
                }
            }
        }

    }

    public void closevoice() {
        if (trackplayer != null && trackplayer.getState() != AudioTrack.MODE_STATIC) {
            trackplayer.stop();
            trackplayer.flush();
//            //我加入
            trackplayer = null;
        }

    }

    public void clearbg() {
        for (int i = 0; i < 4; i++) {
            frameLayoutsbg[i].setVisibility(View.GONE);
            bMute[i] = false;
        }
    }

    private void LoginvideoServer(String deviceid, String serverip, String rport, String vport) {
        if (mDvrNet != null) {
            mDvrNet.CloseDeviceHandle();
            mDvrNet = null;
        }
        if (mDvrNet == null) {
            mDvrNet = new DvrNet();
        }
        Map<String, Object> strMap =
                mDvrNet.GetDeviceHandle(serverip, Integer.parseInt(rport),
                        serverip, Integer.parseInt(vport), 124, deviceid, "admin",
                        "admin");
    }

    public void stopvideo() {
        if (mDvrNet == null) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            mDvrNet.SetAVStream(i, null);
            if (mAVStream[i] != null) {
                isStopView = true;
                mAVStream[i].StopPlay();
                mAVStream[i].SetVideoInterface(null);
                mAVStream[i].CloseHandle();
                mAVStream[i] = null;
                if (surfaceView[i] != null) {
                    SurfaceHolder mHolder;
                    mHolder = surfaceView[i].getHolder();
                    Canvas can = mHolder.lockCanvas();
                    can.drawColor(Color.BLACK);
                    mHolder.unlockCanvasAndPost(can);
                }
            }
            mDvrNet.StopRealAv(i);
            mDvrNet.CloseDeviceHandle();
        }
    }

    public void playvideo(int i) {
        if (mDvrNet == null) {
            return;
        }
        if (surfaceView[i] != null && mAVStream[i] == null) {
            isStopView = false;
            SurfaceHolder mHolder;
            mHolder = surfaceView[i].getHolder();
            Canvas can = mHolder.lockCanvas();
            Paint paint = new TextPaint();
            paint.setTextSize(40);
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.WHITE);
            int[] position = new int[2];
            surfaceView[i].getLocationInWindow(position);
            can.drawText("Loading...", (surfaceView[i].getWidth() - paint.measureText("Loading...")) / 2, (surfaceView[i].getHeight()) / 2, paint);
            mHolder.unlockCanvasAndPost(can);
        }
        if (mAVStream[i] == null) {
            mAVStream[i] = new AVStream();
            mAVStream[i].GetHandle(i);
            mAVStream[i].SetVideoInterface(mRealPlayInterface);
            mAVStream[i].StartPlay();
        }
        mDvrNet.SetAVStream(i, mAVStream[i]);
        mDvrNet.StartRealAv(i, DvrNet.SUB_STREAM);

    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public AudioTrackInterface mAudioTrackInterface = new AudioTrackInterface() {
        @Override
        public void InputAudioData(int i, byte[] bytes, int len) {
            if (bytes == null || len == 0) {
                return;
            }
            if (trackplayer != null && trackplayer.getState() == AudioTrack.STATE_INITIALIZED && bMute[i]) {
                trackplayer.write(bytes, 0, len);
            }
        }
    };


    public RealPlayInterface mRealPlayInterface = new RealPlayInterface() {
        @Override
        public void RealPlayRGBFrame(int nChannel, byte[] data, int nWidth, int nHeight, int i3) {
            if (data == null || nWidth == 0 || nHeight == 0) {
                return;
            }
            int nArraySize = nWidth * nHeight * 2;

            if (mPixel[nChannel] == null) {
                mPixel[nChannel] = new byte[nArraySize];
                bytebuffer[nChannel] = ByteBuffer.wrap(mPixel[nChannel]);
            } else if (mPixel[nChannel].length < nArraySize) {
                mPixel[nChannel] = new byte[nArraySize];
                bytebuffer[nChannel] = ByteBuffer.wrap(mPixel[nChannel]);
            }

            if (mWidth != nWidth || mHeight != nHeight) {
                mWidth = nWidth;
                mHeight = nHeight;
            }

            if (data.length == 0)
                return;

            try {
                System.arraycopy(data, 0, mPixel[nChannel], 0, nArraySize);
            } catch (IndexOutOfBoundsException ex) {
            }
            SimpleDraw(surfaceView[nChannel], nChannel, nWidth, nHeight, mPixel[nChannel]);
        }
    };

    private void SimpleDraw(SurfaceView surfaceView, int nChannel, int nWidth, int nHeight, byte[] data) {

        if (VideoBlt[nChannel] == null) {
            VideoBlt[nChannel] = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.RGB_565);
        } else if (VideoBlt[nChannel].getWidth() != mWidth || VideoBlt[nChannel].getHeight() != mHeight) {
            VideoBlt[nChannel] = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.RGB_565);
        }

        if (bytebuffer == null) {
            bytebuffer[nChannel] = ByteBuffer.wrap(data);
        }

        if (VideoBlt != null) {
            if (VideoBlt[nChannel].getByteCount() > bytebuffer[nChannel].capacity()) {
                bytebuffer[nChannel] = ByteBuffer.wrap(data);
            }
            bytebuffer[nChannel].rewind();
            VideoBlt[nChannel].copyPixelsFromBuffer(bytebuffer[nChannel]);
        }

        Matrix matrix = new Matrix();

        float scale = 0;
        float trans = 0;

        if (surfaceView != null) {
            if (surfaceView.getHeight() < surfaceView.getWidth()) {
                scale = surfaceView.getHeight() / (float) mHeight;
                trans = (surfaceView.getWidth() - mWidth * scale) / 2;
            } else {
                scale = surfaceView.getWidth() / (float) mWidth;
                trans = (surfaceView.getHeight() - mHeight * scale) / 2;
            }

            matrix.postScale(scale, scale);
            matrix.postTranslate(trans, 0);

            matrix.postScale(1, 1);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            Canvas canvas = surfaceHolder.lockCanvas();

            if (VideoBlt != null && canvas != null) {
                canvas.drawColor(Color.BLACK);
                canvas.drawBitmap(VideoBlt[nChannel], matrix, null);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
        stopvideo();
        closevoice();
    }

    @Override
    protected void onStop() {
        super.onStop();
        RxLogTool.i("执行销毁11111111111111111111111111111");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxLogTool.i("执行销毁");
        socket.disconnect();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (mDvrNet != null) {
            for (int i = 0; i < 4; i++) {
                mDvrNet.StopRealAv(i);
            }
            mDvrNet.CloseDeviceHandle();
            mDvrNet = null;
        }
        if (trackplayer != null && trackplayer.getState() != AudioTrack.MODE_STATIC) {
            trackplayer.stop();
            trackplayer.release();
            trackplayer = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    //______________________________________________________________以下为我新增的________________________________________________________________\


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
                    conn();
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

//    /**
//     * 获取校车最后一条GPS信息
//     */
//    private void httpGpsLast() {
//        httpUtils.doGPSLAST(key, deviceid, new BaseCallback(context, Constants.SCHOOL_GPS_LAST) {
//            @Override
//            public void showDatas(Object obj) {
//                super.showDatas(obj);
//                RxLogTool.i("我成功执行到了哦");
//                SchoolBusGpsLastBean bean = (SchoolBusGpsLastBean) obj;
//                if (bean != null && bean.getErrorcode() == 200 && bean.getData() != null && bean.getData().size() > 0) {
//                    SchoolBusGpsLastBean.DataBean dataBeans = bean.getData().get(0);
//                    latLng = new LatLng(Double.parseDouble(dataBeans.getGpslat()), Double.parseDouble(dataBeans.getGpslng()));
//                    updateView();
//                } else {
//                    RxLogTool.i("获取最后一条GPS信息失败");
//                }
//            }
//        });
//    }


    private void conn() {
        try {
            socket = IO.socket("http://119.96.239.107:12056");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {//连接成功
                    RxLogTool.i("连接成功");

                    deviceId_list = new ArrayList<>();
                    deviceId_list.add(deviceid);
                    RxLogTool.i("要订阅GPS的校车ID：" + deviceId_list.get(0));
                    Map<String, Object> map = new HashMap<>();
                    map.put("key", key);
                    map.put("didArray", deviceId_list.toArray());

                    //发送
                    socket.emit("sub_gps", map);
//                    socket.disconnect();
                }

            }).on("event", new Emitter.Listener() {//??????

                @Override
                public void call(Object... args) {
                }

            }).on("sub_gps", new Emitter.Listener() {//接收返回的GPS信息

                @Override
                public void call(Object... args) {
                    RxLogTool.i("返回的GPS数据:" + args[0].toString());
                    try {
                        JSONObject jsonObject = new JSONObject(args[0].toString());
                        if (jsonObject.getString("deviceno").equals(deviceid)) {
                            String lat = jsonObject.getString("lat");
                            String lng = jsonObject.getString("lng");
                            RxLogTool.i("返回的经纬度：" + "【" + lat + "，" + lng + "】");
                            latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                            updateView();
                        } else {
                            RxLogTool.i("这个返回的不是我要的数据哦~");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {//断开连接

                @Override
                public void call(Object... args) {
                    RxLogTool.i("断开连接");
                }

            });
            //开始连接
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


//    /**
//     * 建立soket的连接
//     */
//    private void conn() {
//        try {
//            if (socket == null) {
//                socket = IO.socket("http://119.96.239.107:12056");
//            }
//            if (!socket.connected()) {
//                socket.open();
//            }
//            //发送请求
//            send();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 发送请求
//     */
//    private void send() {
//        deviceId_list = new ArrayList<>();
//        deviceId_list.add(deviceid);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("key", key);
//        map.put("didArray", deviceId_list.toArray());
//        //发送
//        socket.emit("sub_gps", map);
//        //接收
//        socket.on("sub_gps", new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                RxLogTool.i("返回的GPS信息："+args[0].toString());
//            }
//        });
//    }
//
//    /**
//     * 取消订阅
//     */
//    private void sendQX(){
//        deviceId_list = new ArrayList<>();
//        deviceId_list.add("");
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("key", key);
//        map.put("didArray",null);
//        //发送
//        socket.emit("sub_gps", map);
//        socket.close();
//        socket.disconnect();
//        socket = null;
////        //接收
////        socket.on("sub_gps", new Emitter.Listener() {
////            @Override
////            public void call(Object... args) {
////                RxLogTool.i("返回的GPS信息："+args[0].toString());
////            }
////        });
//    }
}



