package com.whfp.anti_terrorism.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dvr.avstream.AudioTrackInterface;
import com.dvr.net.DvrNet;
import com.dvr.net.MultiplaybackInterface;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.StatusBarUtils;
import com.whfp.anti_terrorism.utils.TransTime;

import org.xutils.view.annotation.ContentView;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 校车历史录像回放页面
 */
@ContentView(R.layout.activity_play_back)
public class PlayBackActivity extends BasicActivity implements MultiplaybackInterface, View.OnClickListener {
    private final static String TAG = "PlayBackActivity";
    private DvrNet mDvrNet;
    private Button pb_btn_play;
    private Button pb_btn_stop;
    private Button pb_btn_closevoice;
    private Button pb_btn_faster;
    private Button pb_btn_slower;
    private SeekBar pb_seekbar;
    private SurfaceView surfaceView;
    private AudioTrack trackplayer;
    private TextView tv_plyback_speed;
    private TextView tv_playback_time;
    private TextView tv_playback_time2;
    private TextView pb_channel;
    private int speed = 1;
    private final static int MAX_CHANNEL_COUNT = 32;
    public byte[][] mPixel = new byte[MAX_CHANNEL_COUNT][];
    public ByteBuffer[] bytebuffer = new ByteBuffer[MAX_CHANNEL_COUNT];
    public Bitmap[] VideoBlt = new Bitmap[MAX_CHANNEL_COUNT];
    public int mWidth = 0;
    public int mHeight = 0;
    public boolean[] bMute = new boolean[4];
    private static final int sampleRateInHz = 8000;
    private static final int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
    private static final int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private static final int bufferSize = AudioTrack.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
    private boolean play = false;
    private boolean puse = false;
    private boolean voice = true;
    private String playtime = "20170607090000";
    private String endtime = "20170607095000";
    private int channal = 1;
    private int basetime = 3240;

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
        setTitleText("历史录像回放");
        Intent intent = getIntent();
        String deviceid = intent.getStringExtra("DEVICEID");
        String serverip = intent.getStringExtra("SERVERIP");
        String rport = intent.getStringExtra("RPORT");
        String vport = intent.getStringExtra("VPORT");
        if (intent.getStringExtra("STARTTIME") != null) {
            playtime = intent.getStringExtra("STARTTIME");
        }
        if (intent.getStringExtra("ENDTIME") != null) {
            endtime = intent.getStringExtra("ENDTIME");
        }
        channal = intent.getIntExtra("CHANNAL", 1);


        initView();
        LoginvideoServer(deviceid, serverip, rport, vport);
    }

    public void initView() {
        surfaceView = (SurfaceView) findViewById(R.id.pb_surfaceview1);
        pb_btn_play = (Button) findViewById(R.id.pb_btn_play);
        pb_btn_stop = (Button) findViewById(R.id.pb_btn_stop);
        pb_btn_closevoice = (Button) findViewById(R.id.pb_btn_closevoice);
        pb_btn_faster = (Button) findViewById(R.id.pb_btn_faster);
        pb_btn_slower = (Button) findViewById(R.id.pb_btn_slower);
        pb_seekbar = (SeekBar) findViewById(R.id.pb_seekbar);
        tv_plyback_speed = (TextView) findViewById(R.id.tv_plyback_speed);
        tv_playback_time = (TextView) findViewById(R.id.tv_playback_time);
        tv_playback_time2 = (TextView) findViewById(R.id.tv_playback_time2);
        pb_channel = (TextView) findViewById(R.id.pb_channel);
        pb_btn_play.setOnClickListener(this);
        pb_btn_stop.setOnClickListener(this);
        pb_btn_closevoice.setOnClickListener(this);
        pb_btn_faster.setOnClickListener(this);
        pb_btn_slower.setOnClickListener(this);
        pb_channel.setText(String.valueOf(channal));
        String etime = endtime.substring(8, 14);
        String stime = playtime.substring(8, 14);
        tv_playback_time2.setText(etime.substring(0, 2) + ":" + etime.substring(2, 4) + ":" + etime.substring(4, 6));
        tv_playback_time.setText(stime.substring(0, 2) + ":" + stime.substring(2, 4) + ":" + stime.substring(4, 6));
        pb_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    int time1 = i + basetime;
                    tv_playback_time.setText(TransTime.secToTime(time1));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mDvrNet.MultiPlayPause(true);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int time = seekBar.getProgress();
                mDvrNet.MultiPlayPause(false);
                String yearmonth = playtime.substring(0, 8);
                mDvrNet.MultiPlaySeek(yearmonth + TransTime.secToTime2(time + basetime));
            }
        });
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

    public void playback() {
        if (mDvrNet == null) {
            return;
        }
        if (puse) {
            mDvrNet.MultiPlayPause(false);
            puse = false;
        } else {
            mDvrNet.MultiPlay(channal, DvrNet.MAIN_STREAM, playtime,1);
            mDvrNet.SetMultiplayInterface(this);
        }
        play = true;
        pb_btn_play.setText(getString(R.string.action_puse));
    }

    public void pusepalyback() {
        if (mDvrNet == null) {
            return;
        }
        if (play) {
            mDvrNet.MultiPlayPause(true);
            puse = true;
            play = false;
            pb_btn_play.setText(getString(R.string.action_play));
        }

    }

    public void stop() {
        if (mDvrNet == null) {
            return;
        }
        mDvrNet.MultiPlayStop();
        play = false;
        puse = false;
        pb_btn_play.setText(getString(R.string.action_play));
    }

    public void faster() {
        if (mDvrNet == null) {
            return;
        }
        switch (speed) {
            case 1:
                mDvrNet.MuitiPlaySetSpeed(2);
                speed = 2;
                tv_plyback_speed.setText("x2");
                break;
            case 2:
                mDvrNet.MuitiPlaySetSpeed(4);
                speed = 4;
                tv_plyback_speed.setText("x4");
                break;
            case 4:
                mDvrNet.MuitiPlaySetSpeed(1);
                speed = 1;
                tv_plyback_speed.setText("x1");
                break;
            case -2:
                mDvrNet.MuitiPlaySetSpeed(1);
                speed = 1;
                tv_plyback_speed.setText("x1");
                break;
            case -4:
                mDvrNet.MuitiPlaySetSpeed(-2);
                speed = -2;
                tv_plyback_speed.setText("x1/2");
                break;
        }

    }

    public void slower() {
        if (mDvrNet == null) {
            return;
        }
        switch (speed) {
            case 1:
                mDvrNet.MuitiPlaySetSpeed(-2);
                speed = -2;
                tv_plyback_speed.setText("x1/2");
                break;
            case 2:
                mDvrNet.MuitiPlaySetSpeed(1);
                speed = 1;
                tv_plyback_speed.setText("x1");
                break;
            case 4:
                mDvrNet.MuitiPlaySetSpeed(2);
                speed = 2;
                tv_plyback_speed.setText("x2");
                break;
            case -2:
                mDvrNet.MuitiPlaySetSpeed(-4);
                speed = -4;
                tv_plyback_speed.setText("x1/4");
                break;
            case -4:
                mDvrNet.MuitiPlaySetSpeed(1);
                speed = 1;
                tv_plyback_speed.setText("x1");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pb_btn_play:
                if (!play) {
                    playback();
                    openvoice();
                } else {
                    pusepalyback();
                    closevoice();
                }
                break;
            case R.id.pb_btn_stop:
                stop();
                break;
            case R.id.pb_btn_closevoice:
                //打开声音
                if (!voice) {
                    openvoice();
                    voice = true;
                    pb_btn_closevoice.setText(getString(R.string.action_close_voice));
                } else {
                    voice = false;
                    closevoice();
                    pb_btn_closevoice.setText(getString(R.string.action_open_voice));
                }
                break;
            case R.id.pb_btn_faster:
                faster();
                break;
            case R.id.pb_btn_slower:
                slower();
                break;
            default:
                break;
        }
    }

    public void openvoice() {
        if (trackplayer != null) {
            closevoice();
        }
        trackplayer = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, channelConfig, audioFormat, bufferSize,
                AudioTrack.MODE_STREAM);
        if (mDvrNet != null) {
            mDvrNet.SetAudioInterface(mAudioTrackInterface);
            trackplayer.play();
        }

    }

    public void closevoice() {
        if (trackplayer != null) {
            trackplayer.stop();
            trackplayer.flush();
            mDvrNet.SetAudioInterface(null);
        }

    }

    public AudioTrackInterface mAudioTrackInterface = new AudioTrackInterface() {
        @Override
        public void InputAudioData(int i, byte[] bytes, int len) {
            if (bytes == null || len == 0) {
                return;
            }
            if (trackplayer != null && trackplayer.getState() == AudioTrack.STATE_INITIALIZED) {
                trackplayer.write(bytes, 0, len);
            }
        }
    };

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

    @Override
    public void MultiplayCallback(int nUserData, int nChannel, int nFrameType, byte[] data, int nWidth, int nHeight,
                                  final int nParams,int nFrom) {
        // TODO Auto-generated method stub
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
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        final String time = TransTime.secToTime(nParams);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_playback_time.setText(time);
                String startime = playtime.substring(8, 14);
                String endtime1 = endtime.substring(8, 14);
                basetime = Integer.parseInt(startime.substring(0, 2)) * 3600 + Integer.parseInt(startime.substring(2, 4)) * 60 + Integer.parseInt(startime.substring(4, 6));
                int time2 = Integer.parseInt(endtime1.substring(0, 2)) * 3600 + Integer.parseInt(endtime1.substring(2, 4)) * 60 + Integer.parseInt(endtime1.substring(4, 6));
                //int time=32400;//开始时间
                //int time2=35400;//结束时间
                int timer = time2 - basetime;
                pb_seekbar.setMax(timer);
                pb_seekbar.setProgress(nParams - basetime);
                if (nParams > time2) {
                    mDvrNet.MultiPlayStop();
                    play = false;
                    pb_btn_play.setText(getString(R.string.action_play));
                    pb_seekbar.setProgress(0);
                    tv_playback_time.setText(startime.substring(0, 2) + ":" + startime.substring(2, 4) + ":" + startime.substring(4, 6));
                }

            }
        });

        SimpleDraw(surfaceView, nChannel, nWidth, nHeight, mPixel[nChannel]);

    }

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
            bytebuffer[nChannel].rewind();
            VideoBlt[nChannel].copyPixelsFromBuffer(bytebuffer[nChannel]);
        }

        Matrix matrix = new Matrix();

        float scale = 0;
        float trans = 0;

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

        Canvas canvas = surfaceView.getHolder().lockCanvas();

        if (VideoBlt != null && canvas != null) {
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(VideoBlt[nChannel], matrix, null);
        }

        surfaceView.getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDvrNet != null && play) {
            pusepalyback();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDvrNet != null) {
            mDvrNet.MultiPlayStop();
            mDvrNet.CloseDeviceHandle();
            mDvrNet = null;
        }
    }
}
