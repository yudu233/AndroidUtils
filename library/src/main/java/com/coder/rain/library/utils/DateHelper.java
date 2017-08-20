package com.coder.rain.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Describe : 时间助手类
 * Email:baossrain99@163.com
 * Created by Rain on 17-5-11.
 */
public class DateHelper {

    private static final String TAG = "DateHelper";


    // 返回日期时间格式化对象
    public static final SimpleDateFormat datetimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    // 返回日期时间格式化对象
    public static final SimpleDateFormat noSecondFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    // 从字符串中解析日期时间
    public static final Date datetimeFromString(String s) {
        try {
            return datetimeFormat().parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    // 返回短时间字符串格式yyyy-MM-dd
    public static String getStringDateShort(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    // 将日期时间格式化为字符串
    public static final String datetimeToString(Date date) {
        return date == null ? "" : datetimeFormat().format(date);
    }

    public static final String datetimeToNoSecond(Date date) {
        return date == null ? "" : noSecondFormat().format(date);
    }

    /**
     * 日期格式转换yyyy-MM-dd'T'HH:mm:ss.SSS  (yyyy-MM-dd'T'HH:mm:ss.SSS Z) TO  yyyy-MM-dd HH:mm:ss
     */
    public static final Date dealDateFormat(String oldDateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");       //yyyy-MM-dd'T'HH:mm:ss.SSS
        Date date = null;
        try {
            date = format.parse(oldDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // GMT时间格式转换
    public static final String dateTimeFromGMT() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("CHINESE", "CHINA"));
        formatter.applyPattern("yyyy年MM月dd日 HH:mm");
        return formatter.format(new Date());
    }

    // 将时间间隔转换为可读信息
    public static final String humanInterval(Date date, Date nowTime) {
        if (date == null) return "";
        // long diff = nowTime - date.getTime();
        long diff = nowTime.getTime() - date.getTime();

        diff /= 1000;
        if (diff < 60) return "刚刚";
        if (diff < 3600) return diff / 60 + "分钟前";
        if (diff > 3600 && diff < 3600 * 24) return diff / 3600 + "小时前";
        if (diff < 3600 * 24 * 7 && diff > 3600 * 24) return diff / (3600 * 24) + "天前";
        return getStringDateShort(date);
    }

    public static final String humanInterval(Date date) {
        return humanInterval(date, new Date());
    }

    // 判断是否在同一分钟内
    public static final boolean inSameMinute(Date one, Date tow) {
        if ((one == null) || (tow == null)) return false;
        return one.getTime() / 60000 == tow.getTime() / 60000;
    }

    public static final boolean inSameMinuteByLong(long one, long tow) {
        return one / 60000 == tow / 60000;
    }

    // 最小日期
    public static final Date minDate() {
        Date result = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(1900, 01, 01);
        result.setTime(cal.getTimeInMillis());
        return result;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        return dateFormat.format(new Date());
    }

    /**
     * 以周日开始获取本周开始的时间戳
     *
     * @param calendar
     * @return
     */
    public static String getWeekStartTime(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 本周结束的时间戳
     * @param calendar
     * @return
     */
    public static String getWeekEndTime(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * start
     * 本周开始时间戳 - 以星期一为本周的第一天
     */
    public static String getWeekStartTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy/MM/dd", Locale. getDefault());
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar. DAY_OF_WEEK) - 1;
        if (day_of_week == 0 ) {
            day_of_week = 7 ;
        }
        cal.add(Calendar.DATE , -day_of_week + 1 );
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * end
     * 本周结束时间戳 - 以星期一为本周的第一天
     */
    public static String getWeekEndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy/MM/dd", Locale. getDefault());
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar. DAY_OF_WEEK) - 1;
        if (day_of_week == 0 ) {
            day_of_week = 7 ;
        }
        cal.add(Calendar.DATE , -day_of_week + 7 );
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * 以周日开始获取本周开始的时间戳
     *
     * @param calendar
     * @return
     */
    public static String getWeekStartTimeNoYear(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 本周结束的时间戳
     * @param calendar
     * @return
     */
    public static String getWeekEndTimeNoYear(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return simpleDateFormat.format(calendar.getTime());
    }
}

/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */
