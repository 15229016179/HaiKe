package com.haike.web.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BaseTools {

	/**
	 * 默认的用户ID，用于在没有登录的情况下生成静态页面，主要是在安装的时候使用
	 */
	public static String DEFAULTADMINID = "20100721001";
	/**
	 * 默认的用户名称，用于在没有登录的情况下生成静态页面，主要是在安装的时候使用
	 */
	public static final String DEFAULTADMINNAME = "sasasa";
	/**
	 * 默认shopid=0 0表示官方平台发布
	 */
	public static final String DEFAULTSHOPID="0";
	/**
	 * 默认的店铺名称 
	 */
	public static final String DEFAULTSHOPNAME="";

	// 默认时间
	public static String DEFAULTTIME = "2010-06-25 12:48:21";
	
	//日期格式化yyyyMMdd
	public static final String DATEFORMATEYMD = "yyyyMMdd";
	
	//日期格式化yyyyMMddHHmmss
	public static final String DATEFORMATEYMDHMS = "yyyyMMddHHmmss";

	/**
	 * 设置日期格式
	 * 
	 * @return
	 */
	public static String tagdate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String time = formatter.format(cal.getTime()).toString();
		return time;
	}

	public static Date defaulttime() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
			Date date = sdf.parse(DEFAULTTIME);
			return date;
		} catch (ParseException e) {

		}
		return null;

	}
	/**
	 * 转换用户填写的发货时间
	 * @param memberdelivertime
	 * @return
	 */
	public static Date getMemberDeliverTime(String memberdelivertime){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Date date = null;
		try {
			date = formatter.parse(memberdelivertime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取系统时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date systemtime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		String dateString = formatter.format(date);
		ParsePosition pos = new ParsePosition(0);
		Date currenttime = formatter.parse(dateString, pos);
		return currenttime;
	}

	
	/**
	 * 获取系统时间long
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static long systemtimeLong() {
		return new Date().getTime();
	}

	/**
	 * 转换数据库日期格式
	 * 
	 * @param object
	 * @return
	 */
	public static String formateDbDate(Date object) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		String dateString = formatter.format(object);
		return dateString;
	}
	/**
	 * 时间精确到毫秒，并且转成字符串
	 * @return String
	 */
	public static String formateDbDateToString(Date object) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		String dateString = formatter.format(object);
		return dateString;
	}
	/**
	 * 时间精确到毫秒加上1W以内的随机数
	 * @return String
	 */
	public static String randomTimer() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.CHINA);
		String dateString = formatter.format(new Date());
		int randNum = (int)(Math.random()*10);
		dateString+=randNum;
		return dateString;
	}
	/**
	 * 转换数据库long型日期至字符串类型
	 * @param millsec
	 * @return
	 */
	public static String formateMillsecDateToStr(long millsec){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		Date date= new Date(millsec);
		return formatter.format(date);
	}
	
	public static Date formateMillsecToDate(long millsec){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		Date date= new Date(millsec);
		return date;
	}
	
	public static Date string2Time(String dateString) throws java.text.ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public static Long StringToLong(String timeString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Date date = null;
		Long long1=null;
		try {
			date = formatter.parse(timeString);
			long1=date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return long1;
	}
	/**
	 * 取月份
	 * @param dateString
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date string2Month(String dateString) throws java.text.ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date StringToDate(String timeString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Date date = null;
		try {
			date = formatter.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	
	
	/**
	 * 获取week
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}
	
	public static List<String> getThisMonthDay(int year, int month){
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal = Calendar.getInstance();
//		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		cal.set(year, month, 1);
		cal.add(Calendar.MONTH, 0);
		cal.add(Calendar.DATE, -1);
		String lastDay = sf.format(cal.getTime());
		String aDay = "";
		int i = 1;
		while (!lastDay.equals(aDay)) {
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), i);
			aDay = sf.format(cal.getTime());
			list.add(sf.format(cal.getTime()));
			i++;
		}
		return list;
	}
	
	
	
	
	/**
	 * 查询从今天起N天(暂为14天)
	 * @return
	 */
	public static List<String> getFromTodayNDays() {
		List<String> list = new ArrayList<String>();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("E:yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		cal.set(Calendar.DAY_OF_WEEK, 2);
		for (int i = 0; i < 14; i++) {
			if (sdf.format(cal.getTime()).equals(sdf.format(date))) {
				cal.add(Calendar.DATE, 0);
			}
			list.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	/**
	 * 查询从今天起 到 结束时间endTime前三天  的数据
	 * @param endTime
	 * @return
	 */
	public static List<String> getbeforeTodayNDays(String endTime) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long day = (sd.parse(endTime).getTime() - sd.parse(formateDbDate(new Date())).getTime()) / (24 * 60 * 60 * 1000);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (day > 3) {
				Date date = new Date();
				for (int i = 0; i < day - 2; i++) {
					if (sdf.format(cal.getTime()).equals(sdf.format(date))) {
						cal.add(Calendar.DATE, 0);
					}
					list.add(sdf.format(cal.getTime()));
					cal.add(Calendar.DATE, 1);
				}
			} else {
				list.add(sdf.format(cal.getTime()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获得明天的凌晨的时间
	 */
	public static Date getTommerDate(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) +1);
		Date endDate = null;
		try { 
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;		
	}
	/**
	 * 获得后天的凌晨的时间
	 */
	public static Date getDayAfterTomorrowDate(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) +2);
		Date endDate = null;
		try { 
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;		
	}
	/**
	 * 获得今天凌晨的时间
	 */
	public static Date getTodayDate(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) );
		Date endDate = null;
		try { 
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;		
	}
	/**
	 * 获得n小时之前的时间
	 */
	public static Long getBeforHourDate(Date datetime,int hourNum){
		Calendar date = Calendar.getInstance();
		date.setTime(datetime);
		date.set(Calendar.HOUR, date.get(Calendar.HOUR) -hourNum);
		return date.getTimeInMillis();
	}
	/**
	 * 获得n小时之前的时间
	 */
	public static Date getBeforHourToDate(Date datetime,int hourNum){
		Calendar date = Calendar.getInstance();
		date.setTime(datetime);
		date.set(Calendar.HOUR, date.get(Calendar.HOUR) -hourNum);
		return date.getTime();
	}
	/**
	 * N小时之后的时间
	 * @param datetime
	 * @param hourNum
	 * @return
	 */
	public static Date getAfterHourToDate(Date datetime,int hourNum){
		Calendar date = Calendar.getInstance();
		date.setTime(datetime);
		date.set(Calendar.HOUR, date.get(Calendar.HOUR) +hourNum);
		return date.getTime();
	}
	/**
	 * 获得N分钟之后的时间
	 */
	public static Long getAfterHourDate(Date datetime,int minuteNum){
		Calendar date = Calendar.getInstance();
		date.setTime(datetime);
		date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) +minuteNum);
		return date.getTimeInMillis();
	}
	/**
	 * 获得下一个月
	 */
	public static Date getTommerMonth(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.MONTH, date.get(Calendar.MONTH) +1);
		Date endDate = null;
		try { 
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;		
	}
	/**
	 * 获得这个月
	 */
	public static Date getTodayMonth(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.MONTH, date.get(Calendar.MONTH));
		Date endDate = null;
		try { 
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;		
	}
	/**
	 * 获得这个月月初日
	 */
	public static Date getTodayDay(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DAY_OF_MONTH,GlobalParams.BILLSDATE);
		Date endDate = null;
		try { 
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;		
	}
	/**
	 * 获得这上个月月初日
	 */
	public static Date getAfterMonth(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DAY_OF_MONTH,GlobalParams.BILLSDATE);
		date.set(Calendar.MONTH,date.get(Calendar.MONTH)-1);
		Date endDate = null;
		try { 
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endDate;		
	}
	/**
	 * 获得这个日期中的日（在这个月是几号）
	 */
	public static int getDay(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		int day = 0;
		day = date.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * 获得
	 * 例如 : 周四 5月5号 20:08
	 */
	public static String getOrderBody(Date beginDate){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		String bodyStr = "";
		String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		int month = cal.get(Calendar.MONTH)+1;
		String  monthstr="";
		if(month<10){
			monthstr = "0"+month;
		}else{
			monthstr=""+month;
		}
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String daystr="";
		if(day<10){
			daystr="0"+day;
		}else{
			daystr=""+day;
		}
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String hourStr = "";
		if(hour<10){
			hourStr="0"+hour;
		}else{
			hourStr=""+hour;
		}
		int min = cal.get(Calendar.MINUTE);
		String minStr ="";
		if(min<10){
			minStr="0"+min;
		}else {
			minStr=""+min;
		}
		return weeks[week_index]+" "+monthstr+"月"+daystr+"日 "+hourStr+":"+minStr;	
	}
	/**
	 * 获取电话号码的后6位,并且加密
	 */
	public static String getPhonelastsix(String phone){
		String str = phone.substring(phone.length()-6,phone.length());
		return SHA1.getDigestOfString(str);
	}
}
