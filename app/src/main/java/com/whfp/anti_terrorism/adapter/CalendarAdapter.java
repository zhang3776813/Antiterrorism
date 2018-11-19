package com.whfp.anti_terrorism.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dvr.net.CalendarData;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.utils.DateConstants;
import com.whfp.anti_terrorism.utils.LunarCalendar;
import com.whfp.anti_terrorism.utils.SpecialCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日历gridview中的每一个item显示的textview
 * @author lmw
 *
 */
public class CalendarAdapter extends BaseAdapter {
	private final static String TAG="CalendarAdapter";
	private boolean isLeapyear = false;  //是否为闰年
	private int daysOfMonth = 0;      //某月的天数
	private int dayOfWeek = 0;        //具体某一天是星期几
	private int lastDaysOfMonth = 0;  //上一个月的总天数
	private Context context;
	private int items = 42;
	private CalendarData[] calendarDatas;
	private String[] dayNumber = new String[42];  //一个gridview中的日期存入此数组中
	private SpecialCalendar sc = null;
	private LunarCalendar lc = null;

	private String currentYear = "";
	private String currentMonth = "";
	private String currentDay = "";
	private String mDay;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
	private String showYear = "";   //用于在头部显示的年份
	private String showMonth = "";  //用于在头部显示的月份
	private String animalsYear = "";
	private String leapMonth = "";   //闰哪一个月
	private String cyclical = "";   //天干地支

	//系统当前时间
	private String sysDate = "";
	private String sys_year = "";
	private String sys_month = "";
	private String sys_day = "";

	private Map<Integer,String> mapnormal=new HashMap<>();
	private Map<Integer,String> mapalarm=new HashMap<>();
	private Map<Integer,String> maplock=new HashMap<>();

	
	public CalendarAdapter(){
		Date date = new Date();
		sysDate = sdf.format(date);  //当期日期
		sys_year = sysDate.split("-")[0];
		sys_month = sysDate.split("-")[1];
		sys_day = sysDate.split("-")[2];
		
	}
	public CalendarAdapter(Context context, int year, int month, int day_c, CalendarData[] datas){
		this.context= context;
		sc = new SpecialCalendar();
		lc = new LunarCalendar();
		this.calendarDatas=datas;
		currentYear = String.valueOf(year);  //得到当前的年份
		currentMonth = String.valueOf(month);  //得到本月
		currentDay = String.valueOf(day_c);
		getCalendar(year,month);
		getDayNormal(datas);
	}
	public CalendarAdapter(Context context, Resources rs, int jumpMonth, int jumpYear, int year_c, int month_c, int day_c, CalendarData[] datas){
		this();
		this.context= context;
		sc = new SpecialCalendar();
		lc = new LunarCalendar();
		this.calendarDatas=datas;
		int stepYear = year_c+jumpYear;
		int stepMonth = month_c+jumpMonth ;
		if(stepMonth > 0){
			//往下一个月滑动
			if(stepMonth%12 == 0){
				stepYear = year_c + stepMonth/12 -1;
				stepMonth = 12;
			}else{
				stepYear = year_c + stepMonth/12;
				stepMonth = stepMonth%12;
			}
		}else{
			//往上一个月滑动
			stepYear = year_c - 1 + stepMonth/12;
			stepMonth = stepMonth%12 + 12;
			if(stepMonth%12 == 0){
				
			}
		}
		getDayNormal(datas);
		currentYear = String.valueOf(stepYear);  //得到当前的年份
		currentMonth = String.valueOf(stepMonth);  //得到本月 （jumpMonth为滑动的次数，每滑动一次就增加一月或减一月）
		currentDay = String.valueOf(day_c);  //得到当前日期是哪天
		Log.e("CalendarAdapter","currentMonth="+currentMonth);
		Log.e("CalendarAdapter","currentYear="+currentYear);
		getCalendar(Integer.parseInt(currentYear), Integer.parseInt(currentMonth));
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.calendar_item, null);
		 }
		TextView textView = (TextView) convertView.findViewById(R.id.tvtext);
		mDay = dayNumber[position].split("\\.")[0];
		textView.setText(mDay + "");
		if (position < daysOfMonth + dayOfWeek && position >= dayOfWeek) {
			// 当前月信息显示
			textView.setTextColor(Color.BLACK);// 当月字体设黑
			// 设置 选中的背景
			if (DateConstants.zYear.equals(currentYear) && DateConstants.zMonth.equals(currentMonth) && DateConstants.zDay.equals(mDay)){
				textView.setBackgroundResource(R.drawable.select_day_bg);
				textView.setTextColor(Color.WHITE);
			}else if(mapnormal.containsValue(mDay)){
				textView.setBackgroundResource(R.drawable.normal_day_bg);
				textView.setTextColor(Color.WHITE);
			}else if(mapalarm.containsValue(mDay)){
				textView.setBackgroundResource(R.drawable.alarm_day_bg);
				textView.setTextColor(Color.WHITE);
			}else if(maplock.containsValue(mDay)){
				textView.setBackgroundResource(R.drawable.lock_day_bg);
				textView.setTextColor(Color.WHITE);
			}else{
				textView.setBackgroundResource(R.color.colorWhite);
			}
		}else{
			textView.setTextColor(Color.WHITE);
		}
		return convertView;
	}

	public void getDayNormal(CalendarData[] datas){
		for(int i=0;i<calendarDatas.length;i++){
			CalendarData data=calendarDatas[i];
			if(data.Property==1){//正常
				mapnormal.put(i, String.valueOf(data.nDay));
			}else if(data.Property>=2&&data.Property<64){//报警
				mapalarm.put(i, String.valueOf(data.nDay));
			}else if(data.Property==64){//加锁
				maplock.put(i, String.valueOf(data.nDay));
			}
		}
	}
	public Map<Integer,String> getDayAlarm(CalendarData[] datas){
		Map<Integer,String> map=new HashMap<>();
		for(int i=0;i<calendarDatas.length;i++){
			CalendarData data=calendarDatas[i];
			if(data.Property>=2&&data.Property<64){//报警
				map.put(i, String.valueOf(data.nDay));
			}
		}
		return map;
	}
	public Map<Integer,String> getDayLock(CalendarData[] datas){
		Map<Integer,String> map=new HashMap<>();
		for(int i=0;i<calendarDatas.length;i++){
			CalendarData data=calendarDatas[i];
			if(data.Property==64){//加锁
				map.put(i, String.valueOf(data.nDay));
			}
		}
		return map;
	}
	//得到某年的某月的天数且这月的第一天是星期几
	public void getCalendar(int year, int month){
		isLeapyear = sc.isLeapYear(year);              //是否为闰年
		daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);  //某月的总天数
		dayOfWeek = sc.getWeekdayOfMonth(year, month);      //某月第一天为星期几
		lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month-1);  //上一个月的总天数

		int days = daysOfMonth;
		if (dayOfWeek != 7){
			days = days + dayOfWeek;
		}
		if (days <= 35){
			items = 35;
			DateConstants.scale = 0.25f;
		}else{
			items = 42;
			DateConstants.scale = 0.2f;
		}
		getweek(year,month);
	}
	
	//将一个月中的每一天的值添加入数组dayNuber中
	private void getweek(int year, int month) {
		int j = 1;
		int flag = 0;
		String lunarDay = "";
		
		//得到当前月的所有日程日期(这些日期需要标记)

		for (int i = 0; i < dayNumber.length; i++) {
			// 周一
			 if(i < dayOfWeek){  //前一个月
				int temp = lastDaysOfMonth - dayOfWeek+1;
				dayNumber[i] = (temp + i)+"."+lunarDay;
				
			}else if(i < daysOfMonth + dayOfWeek){   //本月
				String day = String.valueOf(i-dayOfWeek+1);   //得到的日期
				dayNumber[i] = i-dayOfWeek+1+"."+lunarDay;
				setShowYear(String.valueOf(year));
				setShowMonth(String.valueOf(month));
				setAnimalsYear(lc.animalsYear(year));
				setLeapMonth(lc.leapMonth == 0?"": String.valueOf(lc.leapMonth));
				setCyclical(lc.cyclical(year));
			}else{   //下一个月
				dayNumber[i] = j+"."+lunarDay;
				j++;
			}
		}
        String abc = "";
        for(int i = 0; i < dayNumber.length; i++){
        	 abc = abc+dayNumber[i]+":";
        }
        Log.d("DAYNUMBER",abc);
	}
	/**
	 * 点击每一个item时返回item中的日期
	 * @param position
	 * @return
	 */
	public String getDateByClickItem(int position){
		return dayNumber[position];
	}
	
	/**
	 * 在点击gridView时，得到这个月中第一天的位置
	 * @return
	 */
	public int getStartPositon(){
		return dayOfWeek + 7;
	}
	
	/**
	 * 在点击gridView时，得到这个月中最后一天的位置
	 * @return
	 */
	public int getEndPosition(){
		return  (dayOfWeek + daysOfMonth + 7) - 1;
	}
	
	public String getShowYear() {
		return showYear;
	}

	public void setShowYear(String showYear) {
		this.showYear = showYear;
	}

	public String getShowMonth() {
		return showMonth;
	}

	public void setShowMonth(String showMonth) {
		this.showMonth = showMonth;
	}
	
	public String getAnimalsYear() {
		return animalsYear;
	}

	public void setAnimalsYear(String animalsYear) {
		this.animalsYear = animalsYear;
	}
	
	public String getLeapMonth() {
		return leapMonth;
	}

	public void setLeapMonth(String leapMonth) {
		this.leapMonth = leapMonth;
	}
	
	public String getCyclical() {
		return cyclical;
	}

	public void setCyclical(String cyclical) {
		this.cyclical = cyclical;
	}
}
