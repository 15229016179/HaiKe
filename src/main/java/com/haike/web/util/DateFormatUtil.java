/**
 * @Title: DateFormatUtil.java
 * @Package com.warmdoctor.ows.common.util
 * @author bruce
 * @date 2015年4月27日 上午10:31:01
 * @version V1.0
 */
package com.haike.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间格式化
 *
 * @author bruce
 * @ClassName: DateFormatUtil
 * @date 2015年4月27日 上午10:31:01
 */
public class DateFormatUtil {

    public static final String    DateTimeFormatString = "yyyy-MM-dd HH:mm:ss";

    public static final String[]  WEEK_DAYS_NAME       = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    public static final Integer[] WEEK_DAYS_CODE       = { 0, 1, 2, 3, 4, 5, 6 };

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(DateTimeFormatString);
    }

    public static Date getCurrentDateTime() {
        return new Date();
    }

    public static Date getCurrentDate(String time, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(time);
    }

    /**
     * Parse time quietly
     * 
     * @param time
     * @param pattern
     * @return Date , or null if occurred any error
     */
    public static Date parseQuietly(String time, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(time);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentDate(String format) {
        return getFormatDateTime(new Date(), format);
    }

    public static String getCurrentDate() {
        return getCurrentDate(DateTimeFormatString);
    }

    public static String getFormatDateTime(Date date, String format) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static int getTimeCost(Date reqTime, Date respTime) {
        return Long.valueOf(respTime.getTime() - reqTime.getTime()).intValue();
    }

    public static String getWeixinOrderId() {
        String key = getCurrentDate("yyyyMMddHHmmssSSS");
        return key + NumberRandomUtil.getAuth();
    }

    public static String addTimeFormat(int time, String format) {
        return getFormatDateTime(addDate(time), format);
    }

    public static Date addDate(int time) {
        Date d = getCurrentDateTime();
        return addDate(d, time);
    }

    /**
     * 获取周一的时间
     *
     * @param endTime
     * @param format
     * @return
     */
    public static String getWeekOne(String endTime, String format) {
        int mondayPlus = getMondayPlus(endTime);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus);
        Date monday = currentDate.getTime();
        return getFormatDateTime(monday, format);
    }

    /**
     * 获取周末的时间
     *
     * @param startTime
     * @param format
     * @return
     */
    public static String getWeekLast(String startTime, String format) {
        int mondayPlus = getMondayPlus(startTime);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus + 6);
        Date monday = currentDate.getTime();
        return getFormatDateTime(monday, format);
    }

    private static int getMondayPlus(String time) {
        Calendar cd = Calendar.getInstance();
        if (!StringUtils.isEmpty(time)) {
            try {
                cd.setTime(getDateFormat().parse(time));
            } catch (Exception e) {

            }
        }
        int dayOfWeek = cd.get(7) - 1;
        if (dayOfWeek == 1) {
            return 0;
        }
        return (1 - dayOfWeek);
    }

    public static Date addDate(Date date, int time) {
        return addDate(date, time, "mm");
    }

    public static Date addDate(Date date, int time, String type) {
        if (!StringUtils.isEmpty(type)) { // 增加毫秒
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            if ("yy".equals(type)) {
                calender.add(Calendar.YEAR, time);
            } else if ("MM".equals(type)) {
                calender.add(Calendar.MONTH, time);
            } else if ("dd".equals(type)) {
                calender.add(Calendar.DATE, time);
            } else if ("HH".equals(type)) {
                calender.add(Calendar.HOUR, time);
            } else {  // "mm".equals(type)
                calender.add(Calendar.MINUTE, time);
            }
            return calender.getTime();
        } else {
            return null;
        }
    }

    public static int getDaySpace(String first, String last) throws ParseException {
        // if (StringUtils.isEmpty(first) || StringUtils.isEmpty(last)) {
        // return 0;
        // }
        // Calendar calst = Calendar.getInstance();
        // Calendar caled = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // calst.setTime();
        // caled.setTime(sdf.parse(last));
        // //得到两个日期相差的天数
        // int days = ((int) ((caled.getTime().getTime() / 1000) - (calst.getTime().getTime() / 1000))) / 3600 / 24;
        // return days;
        return getSpace(DateFormatUtil.getFormatDateTime(sdf.parse(first), DateTimeFormatString),
                        DateFormatUtil.getFormatDateTime(sdf.parse(last), DateTimeFormatString), "dd");
    }

    public static int getSpace(String first, String last, String type) throws ParseException {
        if (StringUtils.isEmpty(first) || StringUtils.isEmpty(last)) {
            return 0;
        }
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatUtil.DateTimeFormatString);
        calst.setTime(sdf.parse(first));
        caled.setTime(sdf.parse(last));
        int n = 0;
        while (!calst.after(caled)) {                     // 循环对比，直到相等，n 就是所要的结果
            // list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if ("yy".equals(type)) {
                calst.add(Calendar.YEAR, 1);
            } else if ("MM".equals(type)) {
                calst.add(Calendar.MONTH, 1);
            } else if ("dd".equals(type)) {
                calst.add(Calendar.DATE, 1);
            } else if ("HH".equals(type)) {
                calst.add(Calendar.HOUR, 1);
            } else {  // "mm".equals(type)
                calst.add(Calendar.MINUTE, 1);
            }
        }
        return n - 1;
    }

}
