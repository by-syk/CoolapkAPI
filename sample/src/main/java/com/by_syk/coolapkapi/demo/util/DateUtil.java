package com.by_syk.coolapkapi.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    // 默认时间格式
    public static final String DEFAULT_FOMATE = "yyyyMMdd";
    
    /**
     * 按指定格式转换指定时间为可读的字符串
     * 
     * @param calendar
     * @param format
     *        yyyy-MM-dd HH:mm:ss: 2016-09-15 17:40:05
     *        hh:mm: 05:40
     *        MMM dd, yyyy: Sep 15, 2016
     *        MMMM dd: September 15
     * @return 转换失败返回null
     */
    public static String getDateStr(Calendar calendar, String format) {
        if (calendar == null || format == null) {
            return null;
        }
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }
    
    /**
     * 按默认格式（yyyyMMdd）转换指定时间为可读的字符串
     * 
     * @param calendar
     * @return 转换失败返回null
     */
    public static String getDateStr(Calendar calendar) {
        return getDateStr(calendar, DEFAULT_FOMATE);
    }
    
    /**
     * 按指定格式转换指定时间为可读的字符串
     * 
     * @param date
     * @param format
     * @return 转换失败返回null
     */
    public static String getDateStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return getDateStr(calendar, format);
    }
    
    /**
     * 按默认格式（yyyyMMdd）转换指定时间为可读的字符串
     * 
     * @param date
     * @return 转换失败返回null
     */
    public static String getDateStr(Date date) {
        return getDateStr(date, DEFAULT_FOMATE);
    }
    
    /**
     * 按指定格式转换指定时间为可读的字符串
     * 
     * @param time_millis
     * @param format
     * @return 转换失败返回null
     */
    public static String getDateStr(long time_millis, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_millis);
        
        return getDateStr(calendar, format);
    }
    
    /**
     * 按默认格式（yyyyMMdd）转换指定时间为可读的字符串
     * 
     * @param time_millis
     * @return 转换失败返回null
     */
    public static String getDateStr(long time_millis) {
        return getDateStr(time_millis, DEFAULT_FOMATE);
    }
    
    /**
     * 按指定格式转换当前时间为可读的字符串
     * 
     * @param format
     * @return 转换失败返回null
     */
    public static String getDateStr(String format) {
        return getDateStr(System.currentTimeMillis(), format);
    }
    
    /**
     * 按默认格式（yyyyMMdd）转换当前时间为可读的字符串
     * 
     * @return 转换失败返回null
     */
    public static String getDateStr() {
        return getDateStr(DEFAULT_FOMATE);
    }
    
    /**
     * 按指定格式转换指定时间字符串为日期对象
     * 
     * @param dateStr
     * @param format
     * @return 转换失败返回null
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }
    
    /**
     * 按日加或减指定时间
     * 
     * @param date 为null则取当前时间
     * @param add_days
     * @return
     */
    public static Date addDate(Date date, int add_days) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DAY_OF_MONTH, add_days);
        
        return calendar.getTime();
    }
    
    /**
     * 按日加或减当前时间
     * 
     * @param add_days
     * @return
     */
    public static Date addDate(int add_days) {
        return addDate(null, add_days);
    }

    /**
     * 按日加或减指定时间
     *
     * @param date_millis
     * @param add_days
     * @return
     */
    public static long addDateMillis(long date_millis, int add_days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date_millis);
        calendar.add(Calendar.DAY_OF_MONTH, add_days);

        return calendar.getTimeInMillis();
    }

    /**
     * 按日加或减当前时间
     *
     * @param add_days
     * @return
     */
    public static long addDateMillis(int add_days) {
        return addDateMillis(System.currentTimeMillis(), add_days);
    }

    /**
     * 识别时间字符串并转换为默认格式字符串（yyyyMMdd）
     * 
     * @param unknownDate
     * @return 识别失败返回null
     */
    public static String getValidDate(String unknownDate) {
        if (unknownDate == null || !unknownDate.matches("[\\+|-]?[0-9]+")) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        int len = unknownDate.length();
        if (len < 4) { // 0, +2, -3, -16
            int days = Integer.parseInt(unknownDate);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return getDateStr(calendar);
        } else if (unknownDate.charAt(0) != '+' && unknownDate.charAt(0) != '-') {
            if (len == 8) { // 20160622
                return unknownDate;
            } else if (len == 6) { // 160622
                return (calendar.get(Calendar.YEAR) / 100) +  unknownDate;
            } else if (len == 4) { // 0622
                return calendar.get(Calendar.YEAR) +  unknownDate;
            }
        }
        
        return null;
    }
    
    /**
     * 降低时间精确度到日
     * 
     * @param date
     * @return 处理失败返回null
     */
    public static Date toDayDate(Date date) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0); // !!! NOT HOUR
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * 降低当前时间精确度到日
     * 
     * @return 处理失败返回null
     */
    public static Date toDayDate() {
        return toDayDate(new Date());
    }

    /**
     * 夜间判断（22:00 - 07:00）
     * @param time_millis
     * @return
     */
    public static boolean isNight(long time_millis) {
        // 有时区问题，会相差8小时，坑
        //int hour = (int) ((time_millis / (60 * 60 * 1000)) % 24);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_millis);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        return hour >= 22 || hour < 7;
    }

    /**
     * 夜间判断（22:00 - 07:00）
     * @return
     */
    public static boolean isNight() {
        return isNight(System.currentTimeMillis());
    }
}
