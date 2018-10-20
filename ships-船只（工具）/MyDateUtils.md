package com.egova.viewport.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: ldy
 * @Desctrption:自定义日期工具类
 * @Date:Create at 2018/1/4 15:42
 * @Modified By:
 */
public class MyDateUtils {
    private static Log log = LogFactory.getLog(MyDateUtils.class);

    /**
     * 获取当前时间，格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     * @author yue
     * @date 2016年9月29日 下午4:38:10
     */
    public static String getTimeNow() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式.24小时制
        return df.format(new Date());
    }

    /**
     * 将日期字符串转化为需要格式的日期
     *
     * @param date 日期字符串
     * @param format 格式字符串
     * @return 转换后的日期对象
     */
    public static Date strToFormatDate(String date, String format) {
        if (date == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date, new ParsePosition(0));
    }

    /**
     * 将字符串转换为yyyy-MM-dd格式的日期
     *
     * @param date
     * @return 转换后的日期对象
     */
    public static Date strToDate(String date) {
        return strToFormatDate(date, "yyyy-MM-dd");
    }

    public static Date str2Date(String date) {
        return strToFormatDate(date, "yyyyMMddHHmmss");
    }

    /**
     * 将字符串转换为yyyy-MM-dd HH:mm:ss格式的日期
     *
     * @param date
     * @return 转换后的日期对象
     */
    public static Date strToDateTime(String date) {
        return strToFormatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将date型日期转换成特定格式的时间字符串
     *
     * @param date
     * @param format
     * @return 转换后的日期对象
     */
    public static String dateToFormatStr(Date date, String format) {
        if (date == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将date型日期转换成yyyy-MM-dd HH:mm:ss格式的时间字符串
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd HH:mm格式的时间字符串
     */
    public static String dateTimeToStr(Date date) {
        return dateToFormatStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将date型日期转换成yyyy-MM-dd格式的日期字符串
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式的日期字符串
     */
    public static String dateToStr(Date date) {
        return dateToFormatStr(date, "yyyy-MM-dd");
    }

    /**
     * 时间之间不同格式转换
     *
     * @param strOriDate 20120101121212
     * @param strOriDateFormater "yyyyMMddHHmmss"
     * @param strNewDateFormater "yyyy-MM-dd HH:mm:ss"
     * @return 返回按时间格式转换后的时间字符串
     */
    public static String changeDateOriToNew(String strOriDate, String strOriDateFormater, String strNewDateFormater) {
        if (strOriDate == null){
            return null;
        }
        String formatDate = "";
        SimpleDateFormat oriDateFormater = new SimpleDateFormat(strOriDateFormater);

        SimpleDateFormat dateFormater = new SimpleDateFormat(strNewDateFormater);
        try {
            formatDate = dateFormater.format(oriDateFormater.parse(strOriDate));
            return formatDate;
        }
        catch (ParseException e) {
            log.error("日期解析出错！");
            return formatDate;
        }
    }

    /**
     * 计算出date day天之前或之后的日期
     *
     * @param date {@link Date} 日期
     * @param days int 天数，正数为向后几天，负数为向前几天
     * @return 返回Date日期类型
     */
    public static Date getDateBeforeOrAfterDays(Date date, int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        return now.getTime();
    }

    /**
     * 计算出date monthes月之前或之后的日期
     *
     * @param date 日期
     * @param monthes 月数，正数为向后几天，负数为向前几天
     * @return 返回Date日期类型
     */
    public static Date getDateBeforeOrAfterMonthes(Date date, int monthes) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + monthes);
        return now.getTime();
    }

    /**
     * 计算出date years年之前或之后的日期
     *
     * @param date 日期
     * @param years 年数，正数为向后几年，负数为向前几年
     * @return 返回Date日期类型
     */
    public static Date getDateBeforeOrAfterYears(Date date, int years) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) + years);
        return now.getTime();
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param beginDate
     * @param endDate
     * @return 如果beginDate 在 endDate之后返回负数 ，反之返回正数
     */
    public static int daysOfTwoDate(Date beginDate, Date endDate) {

        Calendar beginCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        beginCalendar.setTime(beginDate);
        endCalendar.setTime(endDate);

        return daysOfTwoDate(beginCalendar, endCalendar);

    }

    /**
     * 计算两个日期之间的天数
     *
     * @param d1
     * @param d2
     * @return 如果d1 在 d2 之后返回负数 ，反之返回正数
     */
    public static int daysOfTwoDate(Calendar d1, Calendar d2) {
        int days = 0;
        int years = d1.get(Calendar.YEAR) - d2.get(Calendar.YEAR);
        if (years == 0) {// 同一年中
            days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
            return days;
        }
        else if (years > 0) {// 不同一年
            for (int i = 0; i < years; i++) {
                d2.add(Calendar.YEAR, 1);
                days += -d2.getActualMaximum(Calendar.DAY_OF_YEAR);
                if (d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR)) {
                    days += d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
                    return days;
                }
            }
        }
        else {
            for (int i = 0; i < -years; i++) {
                d1.add(Calendar.YEAR, 1);
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                if (d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR)) {
                    days += d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
                    return days;
                }
            }
        }

        return days;
    }

    /**
     * 获得当前时间当天的开始时间，即当前给出的时间那一天的00:00:00的时间
     *
     * @param date 当前给出的时间
     * @return 当前给出的时间那一天的00:00:00的时间的日期对象
     */
    public static Date getDateBegin(Date date) {
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                return DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.CHINA).parse(ymdFormat.format(date));
            }
            catch (ParseException e) {
                log.error("日期解析出错！");
            }
        }
        return null;
    }

    public static Date getDateBegin(Date date, int days) {
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                date = getDateBeforeOrAfterDays(date, days);
                return DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.CHINA).parse(ymdFormat.format(date));
            }
            catch (ParseException e) {
                log.error("日期解析出错！");
            }
        }
        return null;
    }

    /**
     * 获得当前时间当天的结束时间，即当前给出的时间那一天的23:59:59的时间
     *
     * @param date 当前给出的时间
     * @return 当前给出的时间前一天的23:59:59的时间的日期对象
     */
    public static Date getDateEnd(Date date) {
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                date = getDateBeforeOrAfterDays(date, 0);
                date = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.CHINA).parse(ymdFormat.format(date));
                Date endDate = new Date();
                endDate.setTime(date.getTime() - 1000);
                return endDate;
            }
            catch (ParseException e) {
                log.error("日期解析出错！");
            }
        }
        return null;
    }

    /**
     * @description: 获得当前给出的时间偏移days天的23:59:59的时间
     * @param:  * @param null
     * @return:
     * @date: 2017/11/18 12:12
     * @author: ldy
     */
    public static Date getDateEnd(Date date, int days) {
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                date = getDateBeforeOrAfterDays(date, days);
                date = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.CHINA).parse(ymdFormat.format(date));
                Date endDate = new Date();
                endDate.setTime(date.getTime() - 1000);
                return endDate;
            }
            catch (ParseException e) {
                log.error("日期解析出错！");
            }
        }
        return null;
    }

    /**
     * @description: 获取本周的第一天开始日期     20171116000000
     * @param:  * @param null
     * @return:
     * @date: 2017/11/16 9:46
     * @author: ldy
     */
    public static Date getFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (day_of_week == -1) {
            day_of_week = 6;
        }
        calendar.add(Calendar.DATE, 0 - day_of_week);
//            return DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.CHINA).parse(ymdFormat.format(calendar.getTime()));
        return getDateBegin(calendar.getTime());
    }

    /**
     * @description: 获取本月第一天的时间格式
     * @param:  * @param null
     * @return:
     * @date: 2017/11/16 12:13
     * @author: ldy
     */
    public static Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int day_of_month = calendar.get(Calendar.DAY_OF_MONTH) - 1;
        calendar.add(Calendar.DATE, 0 - day_of_month);
        return getDateBegin(calendar.getTime());
    }

    public static String getStringDate(Date date, String yyyymmddhhmmss) {
        SimpleDateFormat df = new SimpleDateFormat(yyyymmddhhmmss);// 设置日期格式.24小时制
        return df.format(date);
    }
}
