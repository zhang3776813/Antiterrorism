package com.whfp.anti_terrorism.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;
import com.vondear.rxtools.RxLogTool;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.MediaController;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 实时监控查看界面
 * Created by 张明杨 on 2018-06-14-0014.
 */
@ContentView(R.layout.activity_monitor)
public class MonitorActivity extends BasicActivity implements PLOnPreparedListener, PLOnInfoListener, PLOnCompletionListener, PLOnErrorListener {

    //视频播放器控件
    @ViewInject(R.id.PLVideoTextureView)
    private PLVideoTextureView PLVideoTextureView;

    private String PATH_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

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
        setTitleText("实时监控");
        initVideoView();
    }

    /**
     * 初始化视频播放
     */
    private void initVideoView() {
        //设置屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //关联播放控制器
        MediaController mediaController = new MediaController(this);
        PLVideoTextureView.setMediaController(mediaController);
        //设置相关监听监听
        PLVideoTextureView.setOnPreparedListener(this);//播放准备
        PLVideoTextureView.setOnInfoListener(this);//播放器状态
        PLVideoTextureView.setOnCompletionListener(this);//播放结束
        PLVideoTextureView.setOnErrorListener(this);//播放错误
        //设置画面预览模式
        PLVideoTextureView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);//适应屏幕
        //设置播放地址
        PLVideoTextureView.setVideoPath(PATH_URL);

    }

    /**
     * 判断网络连通性
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * 播放准备
     */
    @Override
    public void onPrepared(int i) {
        PLVideoTextureView.start();
    }

    /**
     * 播放器状态
     */
    @Override
    public void onInfo(int i, int i1) {
        RxLogTool.i("播放器状态，状态代码：" + i + "，附加参数：" + i1);
    }

    /**
     * 该对象用于监听播放结束的消息，关于该回调的时机，有如下定义：
     * 如果是播放文件，则是播放到文件结束后产生回调
     * 如果是在线视频，则会在读取到码流的EOF信息后产生回调，回调前会先播放完已缓冲的数据
     * 如果播放过程中产生onError，并且没有处理的话，最后也会回调本接口
     * 如果播放前设置了 setLooping(true)，则播放结束后会自动重新开始，不会回调本接口
     * 如果同时将 AVOptions.KEY_FAST_OPEN 与 AVOptions.KEY_SEEK_MODE 设置为 1，并且希望在收到本接口后播放同一个视频，需要在 start 后手动调用 seekTo(0)
     */
    @Override
    public void onCompletion() {

    }

    /**
     * 播放错误回调
     *
     * @param i
     * @return
     */
    @Override
    public boolean onError(int i) {
        RxLogTool.e("播放错误，错误代码：" + i);
        switch (i) {
            case ERROR_CODE_IO_ERROR://网络异常
                if (isNetworkAvailable(this)) {
                    //退出播放
                    PLVideoTextureView.stopPlayback();
                }
                return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PLVideoTextureView.stopPlayback();
    }
}
