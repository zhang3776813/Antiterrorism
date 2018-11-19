package com.whfp.anti_terrorism.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dvr.net.CalendarData;
import com.dvr.net.DvrNet;
import com.dvr.net.RemoteFileInfo;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.adapter.CalendarAdapter;
import com.whfp.anti_terrorism.adapter.RetrievalListAdpater;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.DateConstants;
import com.whfp.anti_terrorism.utils.SpecialCalendar;
import com.whfp.anti_terrorism.utils.StatusBarUtils;
import com.whfp.anti_terrorism.utils.YearMonthDatePickerDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 校车历史录像检索
 */
@ContentView(R.layout.activity_retrieval)
public class RetrievalActivity extends BasicActivity implements OnGestureListener, View.OnClickListener {

    @ViewInject(R.id.ll)
    private LinearLayout ll;

    @ViewInject(R.id.ll2)
    private LinearLayout ll2;

    private final static String TAG = "RetrievalActivity";
    private GestureDetector gestureDetector = null;
    private DvrNet mDvrNet;
    private CalendarAdapter calV = null;
    private GridView gridView = null;
    private TextView topText = null;
    private ListView listView = null;
    private LinearLayout mBtnLeft, mBtnRight, mBtnBack, mBtnSelect;
    private static int jumpMonth = 0;      //每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0;       //滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    private List<RemoteFileInfo> mDataslistfile = new ArrayList<>();
    private RetrievalListAdpater adpater;
    private float location;             // 最终决定的收缩比例值
    private float currentLoction = 1f;  // 记录当天的收缩比例值
    private float selectLoction = 1f;   // 记录选择那一天的收缩比例值
    private String deviceid;
    private String serverip;
    private String rport;
    private String vport;

    GetCalendarData getCalendarData;

    CalendarData[] datas;

    //UI更新状态码
    private final static int LOAD_DATE = 11;//加载日历

    public RetrievalActivity() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);  //当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
        Log.e("RetrievalActivity: ", "" + year_c + "");
    }

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
        setTitleText("历史录像查看");
        //获取传递过来的参数
        Intent intent = getIntent();
        deviceid = intent.getStringExtra("DEVICEID");
        serverip = intent.getStringExtra("SERVERIP");
        rport = intent.getStringExtra("RPORT");
        vport = intent.getStringExtra("VPORT");
        //初始化控件
        initView();
        //登录媒体服务器
        LoginvideoServer(deviceid, serverip, rport, vport);
        //获取日历信息
        getMonth();
    }

    private void getDate(){
        if (datas == null) {
            stopRxLodingDialog();
            finish();
            RxToast.info("该设备没有数据或设备不在线！",Toast.LENGTH_LONG);
            return;
        } else {
            ll.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.VISIBLE);
            calc();
        }
    }

    /**
     * 计算当天的位置和收缩比例
     */
    private void calc() {
        SpecialCalendar calendar = new SpecialCalendar();
        boolean isLeapYear = calendar.isLeapYear(year_c);
        int days = calendar.getDaysOfMonth(isLeapYear, month_c);
        int dayOfWeek = calendar.getWeekdayOfMonth(year_c, month_c);
        int todayPosition = day_c;
        if (dayOfWeek != 7) {
            days = days + dayOfWeek;
            todayPosition += dayOfWeek - 1;
        } else {
            todayPosition -= 1;
        }
        /**
         * 如果 少于或者等于35天显示五行 多余35天显示六行
         * 五行: 收缩比例是：0.25，0.5，0.75，1
         * 六行: 收缩比例是：0.2，0.4，0.6，0.8，1
         */
        if (days <= 35) {
            DateConstants.scale = 0.25f;
            currentLoction = (4 - todayPosition / 7) * DateConstants.scale;
        } else {
            DateConstants.scale = 0.2f;
            currentLoction = (5 - todayPosition / 7) * DateConstants.scale;
        }
        location = currentLoction;
        gestureDetector = new GestureDetector(this);
        calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, datas);
        addGridView();
        gridView.setAdapter(calV);
        topText = (TextView) findViewById(R.id.tv_month);
        addTextToTopTextView(topText);
        //获取日历检索
        setList(String.valueOf(year_c), String.valueOf(month_c), String.valueOf(day_c));
        stopRxLodingDialog();
    }

    public void initView() {
        gridView = (GridView) findViewById(R.id.gridview);
        listView = (ListView) findViewById(R.id.retrieval_listview);
        mBtnSelect = (LinearLayout) findViewById(R.id.btn_yearmonth);
        mBtnBack = (LinearLayout) findViewById(R.id.btn_back_retrieval);
        mBtnLeft = (LinearLayout) findViewById(R.id.btn_prev_month);
        mBtnRight = (LinearLayout) findViewById(R.id.btn_next_month);
        mBtnLeft.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(RetrievalActivity.this, adpater.getItem(i).FileTime, Toast.LENGTH_SHORT).show();
                String starttime = adpater.getItem(i).FileTime.split("-")[0];
                String endtime = adpater.getItem(i).FileTime.split("-")[1];
                int channal = adpater.getItem(i).nChannel + 1;
                Intent intent = new Intent();
                intent.putExtra("DEVICEID", deviceid);
                intent.putExtra("SERVERIP", serverip);
                intent.putExtra("RPORT", rport);
                intent.putExtra("VPORT", vport);
                intent.putExtra("STARTTIME", starttime);
                intent.putExtra("ENDTIME", endtime);
                intent.putExtra("CHANNAL", channal);
                intent.setClass(RetrievalActivity.this,PlayBackActivity.class);
                startActivity(intent);
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
        Map<String, Object> strMap = mDvrNet.GetDeviceHandle(serverip, Integer.parseInt(rport),
                serverip, Integer.parseInt(vport), 124, deviceid, "admin",
                "admin");
    }


    //检索视频日历
    public void getMonth() {
        getCalendarData = new GetCalendarData(year_c, month_c, 1, DvrNet.MAIN_STREAM,
                DvrNet.CALENDAR_FILETYPE_ALARM | DvrNet.CALENDAR_FILETYPE_LOCK | DvrNet.CALENDAR_FILETYPE_NORMAL, 1);
        getCalendarData.execute((Void)null);
    }


    /**
     * 检索录像日志数据
     */
    public class GetCalendarData extends AsyncTask<Void, Void, CalendarData[]> {

        private int nYear1;
        private int nMonth1;
        private int nChannelBits1;
        private int nStreamType1;
        private int nFileType1;
        private int nFrom1;

        public GetCalendarData(int nYear1, int nMonth1, int nChannelBits1, int nStreamType1, int nFileType1, int nFrom1) {
            this.nYear1 = nYear1;
            this.nMonth1 = nMonth1;
            this.nChannelBits1 = nChannelBits1;
            this.nStreamType1 = nStreamType1;
            this.nFileType1 = nFileType1;
            this.nFrom1 = nFrom1;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startRxLodingDialog();
        }

        @Override
        protected CalendarData[] doInBackground(Void... voids) {
            if (mDvrNet == null) {
                return null;
            }else {
                CalendarData[] calendarDatas = mDvrNet.SearchMonth(nYear1, nMonth1, nChannelBits1, nStreamType1,
                        nFileType1, nFrom1);
                return calendarDatas;
            }
        }

        @Override
        protected void onPostExecute(final CalendarData[] mHandle) {
            super.onPostExecute(mHandle);
            datas = mHandle;
            getDate();
        }
    }

    public CalendarData[] getMonth(int year, int month) {
        if (mDvrNet == null) {
            return null;
        }
        CalendarData[] calendarDatas = mDvrNet.SearchMonth(year, month, 1, DvrNet.MAIN_STREAM,
                DvrNet.CALENDAR_FILETYPE_ALARM | DvrNet.CALENDAR_FILETYPE_LOCK | DvrNet.CALENDAR_FILETYPE_NORMAL, 1);
        return calendarDatas;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            //像左滑动
            jumpMonth++;     //下一个月
            upDateView();
            return true;
        } else if (e1.getX() - e2.getX() < -120) {
            //向右滑动
            jumpMonth--;     //上一个月
            upDateView();
            return true;
        }
        return false;
    }

    private void upDateView() {
        DateConstants.zYear = "";
        DateConstants.zMonth = "";
        DateConstants.zDay = "";
        listView.setAdapter(null);
        int stepYear = year_c + jumpYear;
        int stepMonth = month_c + jumpMonth;
        if (stepMonth > 0) {
            //往下一个月滑动
            if (stepMonth % 12 == 0) {
                stepYear = year_c + stepMonth / 12 - 1;
                stepMonth = 12;
            } else {
                stepYear = year_c + stepMonth / 12;
                stepMonth = stepMonth % 12;
            }
        } else {
            //往上一个月滑动
            stepYear = year_c - 1 + stepMonth / 12;
            stepMonth = stepMonth % 12 + 12;
            if (stepMonth % 12 == 0) {

            }
        }
        CalendarData[] dates = getMonth(stepYear, stepMonth);
        addGridView();   //添加一个gridView
        calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, dates);
        gridView.setAdapter(calV);
        addTextToTopTextView(topText);
    }

    private void upDateView(int year, int month) {
        listView.setAdapter(null);
        CalendarData[] dates = getMonth(year, month);
        addGridView();   //添加一个gridView
        calV = new CalendarAdapter(this, year, month, day_c, dates);
        year_c = year;
        month_c = month;
        gridView.setAdapter(calV);
        addTextToTopTextView(topText);
    }

    //添加头部的年份 闰哪月等信息
    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        textDate.append(calV.getShowYear()).append("年").append(
                calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
        view.setTextColor(Color.WHITE);
        view.setTypeface(Typeface.DEFAULT_BOLD);
    }

    //添加gridview
    private void addGridView() {

        // TODO 如果滑动到其他月默认定位到第一行，划回本月定位到当天那行
        if (jumpMonth == 0) {
            location = currentLoction;
        } else {
            location = 1f;
        }
        // TODO 选择的月份 定位到选择的那天
        if (((jumpMonth + month_c) + "").equals(DateConstants.zMonth)) {
            location = selectLoction;
        }
        Log.d("location", "location == " + location + "   currentLoction == " + currentLoction);

        gridView.setOnTouchListener(new View.OnTouchListener() {
            //将gridview中的触摸事件回传给gestureDetector
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return RetrievalActivity.this.gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //gridView中的每一个item的点击事件
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                //点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                String scheduleDay;
                String scheduleYear;
                String scheduleMonth;
                location = (float) ((5 - position / 7) * 0.2);
                if (startPosition <= position + 7 && position <= endPosition - 7) {
                    scheduleDay = calV.getDateByClickItem(position).split("\\.")[0];  //这一天的阳历
                    scheduleYear = calV.getShowYear();
                    scheduleMonth = calV.getShowMonth();
                    DateConstants.zYear = scheduleYear;
                    DateConstants.zMonth = scheduleMonth;
                    DateConstants.zDay = scheduleDay;

                    if (DateConstants.scale == 0.2f) {
                        location = (5 - position / 7) * DateConstants.scale;
                    } else {
                        location = (4 - position / 7) * DateConstants.scale;
                    }
                    selectLoction = location;
                    calV.notifyDataSetChanged();
                    Toast.makeText(RetrievalActivity.this, scheduleYear + "-" + scheduleMonth + "-" + scheduleDay, Toast.LENGTH_SHORT).show();
                    //显示filelist
                    if (Integer.parseInt(scheduleMonth) < 10) {
                        scheduleMonth = "0" + scheduleMonth;
                    } else if (Integer.parseInt(scheduleDay) < 10) {
                        scheduleDay = "0" + scheduleDay;
                    }
                    setList(scheduleYear, scheduleMonth, scheduleDay);
                }
            }

        });
    }

    public void setList(String year, String month, String day) {
        RemoteFileInfo[] fileInfos = mDvrNet.SearchVideoFileList(DvrNet.MAIN_STREAM, -1,
                DvrNet.CALENDAR_FILETYPE_ALARM | DvrNet.CALENDAR_FILETYPE_LOCK | DvrNet.CALENDAR_FILETYPE_NORMAL,
                year + month + day + "000000", year + month + day + "235959", 1);
        mDataslistfile = new ArrayList<>();
        for (int i = 0; i < fileInfos.length; i++) {
            RemoteFileInfo remoteFileInfo = fileInfos[i];
            remoteFileInfo.Print(TAG);
            mDataslistfile.add(remoteFileInfo);
        }
        adpater = new RetrievalListAdpater(RetrievalActivity.this, mDataslistfile);
        listView.setAdapter(adpater);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_prev_month:
                jumpMonth--;     //上一个月
                upDateView();
                break;
            case R.id.btn_next_month:
                jumpMonth++;     //下一个月
                upDateView();
                break;
            case R.id.btn_back_retrieval://返回
                RetrievalActivity.this.finish();
                break;
            case R.id.btn_yearmonth://选择年月日
                Calendar calendar = Calendar.getInstance();
                YearMonthDatePickerDialog dialog = new YearMonthDatePickerDialog(this,
                        DatePickerDialog.THEME_HOLO_LIGHT, dateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
        }
    }

    DatePickerDialog.OnDateSetListener dateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker,
                                      int year, int month, int dayOfMonth) {
                    //选着年月
                    Log.e("TAG", "你选择了" + year + "年" + (month + 1) + "月");
                    upDateView(year, (month + 1));
                }
            };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Re", "onDestroy: ");
        // TODO 页面被销毁时，清空选择的日期数据
        jumpMonth = 0;
        jumpYear = 0;
        DateConstants.zYear = "";
        DateConstants.zMonth = "";
        DateConstants.zDay = "";

    }
}
