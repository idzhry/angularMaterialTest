package com.woodpecker.webframework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.woodpecker.webframework.exception.SystemException;

public class DateUtil {

	/**
	 * 日期格式[yyyy-MM-dd]
	 */
	public static final String DF_YYYYMMDD = "yyyy-MM-dd";

	public static final String DF_YYYYMMDDNUMER = "yyyyMMdd";

	public static final String DF_YYYYMMDDHHmmSS = "yyyy-MM-dd HH:mm:ss";

	public static final String DF_YYYYMMDDHHmm = "yyyy-MM-dd HH:mm";

	public static final String DF_TIMESTAMP = "yyyyMMdd HH:mm:ss.SSS";

	/**
	 * %1d年%2d月%3d日
	 */
	public static final String DATE_CN = "%1d年%2d月%3d日";

	/**
	 * %1d年%2d月%2d日%2d时%2d分%2d秒
	 */
	public static final String DATETIME_CN = "%1d年%2d月%2d日%2d时%2d分%2d秒";
	
	
	/**
	 * 数据库的创建时间和更新时间用此格式
	 * @return
	 */
	public static String getDateStr(){
		return new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS").format(new Date());
	}
	

    /**
     * 日期格式转换
     *
     * @param object
     *            要转换的数据
     * @return codeNameList 格式为yyyy-MM-dd的数据
     */
    public static String getDateYMD(Object object) {

        // 对象为空时
        if (object == null || StringUtils.isEmpty(object.toString())) {
            return StringUtils.EMPTY;
        }

        SimpleDateFormat df = new SimpleDateFormat(DF_YYYYMMDD);
        String strReqult = df.format(object);
        return strReqult;
    }

    /**
     * 日期格式转换
     * 
     * @return 格式为yyyyMMdd的数据
     */
    public static String getDateYMDNUM() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DF_YYYYMMDDNUMER);
        String strReqult = df.format(date);
        return strReqult;
    }

    /**
     * 日期格式转换
     * 
     * @return 格式为yyyyMMdd的数据
     */
    public static String getDateYMDNUM(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DF_YYYYMMDDNUMER);
        String strReqult = df.format(date);
        return strReqult;
    }

    /**
     * 日期格式转换 返回:yyyy-MM-dd HH:mm:ss
     *
     * @param object
     * @return
     */
    public static String getDateYMDHms(Object object) {
        // 对象为空时
        if (object == null || StringUtils.isEmpty(object.toString())) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat df = new SimpleDateFormat(DF_YYYYMMDDHHmmSS);
        String strReqult = df.format(object);
        return strReqult;
    }

    /**
     * 日期格式转换 返回:yyyy-MM-dd HH:mm
     *
     * @param object
     * @return
     */
    public static String getDateYMDHm(Object object) {
        // 对象为空时
        if (object == null || StringUtils.isEmpty(object.toString())) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat df = new SimpleDateFormat(DF_YYYYMMDDHHmm);
        String strReqult = df.format(object);
        return strReqult;
    }

    /**
     * 取得前台日期
     *
     * @param strDate
     *            要转换的数据
     * @return strReqult 格式为yyyy-MM-dd的数据
     * @author k
     */
    public static String getDateYMD(String strDate) {

        String tempDate = StringUtils.trim(strDate);

        if (StringUtils.isEmpty(tempDate)) {
            return StringUtils.EMPTY;
        }
        String strRtn = StringUtils.substringBefore(tempDate, "T");
        return strRtn;
    }

    /**
     * 取得前台日期
     *
     * @param strDate
     *            要转换的数据
     * @return strReqult 格式为yyyy/MM/dd的数据
     * @author kinx.fan
     */
    public static String getDateYMDInfo(String strDate) {

        String tempDate = StringUtils.trim(strDate);

        if (StringUtils.isEmpty(tempDate)) {
            return StringUtils.EMPTY;
        }
        String strRtn = StringUtils.substringBefore(tempDate, "T");
        // 转换日期类型格式
        strRtn = strRtn.replaceAll("-", "/");
        // 返回
        return strRtn;
    }

    /**
     * 根据生日计算年龄
     *
     * @param brithday
     *            格式： EEE MMM dd HH:mm:ss zzz yyyy
     * @return
     */
    public static int getAge(Date brithday) {

        // 年龄
        int age = 0;
        try {
            Date currentDate = new Date(System.currentTimeMillis());
            Calendar bCal = Calendar.getInstance();
            Calendar cCal = Calendar.getInstance();
            bCal.setTime(brithday);
            cCal.setTime(currentDate);
            age = cCal.get(Calendar.YEAR) - bCal.get(Calendar.YEAR);
        } catch (Exception e) {
            return age;
        }
        return age;
    }
    
    /**
     * 计算时间跟当前时间相差多少分钟
     *
     * @param updTime
     * @return
     */
    public static long compareTime(Date updTime) {

    	long expirationTime = 0;
        try {
            Date currentDate = new Date(System.currentTimeMillis());
            expirationTime = Math.abs(currentDate.getTime() - updTime.getTime())/(1000 * 60);
        } catch (Exception e) {
            return expirationTime;
        }
        return expirationTime;
    }

    /**
     * 获取当前系统时间 yyyy-MM-dd
     *
     * @return
     */
    public static final String getDateYMD() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return String.format(DATE_CN, year, month, day);
    }

    /**
     * 获取当前系统时间
     *
     * @return 系统时间 格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateYMDHMS() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前系统时间
     *
     * @return 系统时间 格式 yyyyMMdd HH:mm:ss.SSS
     */
    public static String getSysTimestamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DF_TIMESTAMP);
        return sdf.format(date);
    }

    /**
	 * 将时间字符串转换为Date类型yyyy-MM-dd HH:mm:ss
	 *
	 * @param dateStr
	 * @return Date
	 */
	public static Date toDateYMDHMS(String dateStr) {
		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd HH:mm:ss");
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
		    DateFormat dateFormat;
		    dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
		    dateFormat.setLenient(false);
		    
			try {
			    Date timeDate = dateFormat.parse(dateStr);
			    return timeDate;
            } catch (ParseException e1) {
              throw new SystemException(e, "时间转换错误");
            }
		}
		return date;
	}

    /**
     * 将时间字符串转换为Date类型yyyy-MM-dd
     *
     * @param dateStr
     * @return Date
     */
    public static Date toDateYMD(String dateStr) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(e, "时间转换错误");
        }
        return date;
    }

    /**
     * 取得服务器时间返回到前台+添加时分秒
     *
     * @return
     */
    public static final String getDetailSystemDate() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        int minute = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);
        return String.format(DATETIME_CN, year, month, day, hour, minute, second);
    }

    /**
     * 获取当前系统时间根据指定格式
     *
     * @return 系统时间 格式按照参数指定
     */
    public static String getNowDate(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取当前系统时间,返回值为data类型, 插进数据库为yyyy-MM-dd HH:mm:ss格式
     */
    public static Date getNowSqlDate() {
        return new Date();
    }

    /**
     * 获取当前系统时间,返回值为data类型, 插进数据库为yyyy-MM-dd格式
     */
    public static Date getNowSqlDate2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 接收前台字符串时间转换为date,返回值为data类型, 插进数据库为yyyy-MM-dd格式
     */
    public static Date getNowSqlDate3(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(format);
        } catch (ParseException e) {
        }
        return date;
    }
    
    
    /**
     * 给一个日期增加天
     * 
     * @param date
     *            参考基准天数
     * @param addDays
     *            要增加的天数，负数表示完过去方向推addDays天，正数表示完未来方向推addDays天。
     * @return
     */
    public static Date addDays(Date date, int addDays) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(format.format(date)));
            cal.add(Calendar.DATE, addDays);
            return cal.getTime();
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * UTC时间转换本地时间
     * 
     * @param utcTime
     *            需要格式化的utc时间 "2016-05-01T16:00:00.000Z"
     * @param utcTimePatten
     *            需要格式化utc时间的格式 "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
     * @param localTimePatten
     *            返回的时间格式 "yyyy-MM-dd hh:mm:ss"
     * @return 你需要的本地时间 "yyyy-MM-dd hh:mm:ss"
     */
    public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));// 时区定义并进行时间获取
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime.toString());
        } catch (ParseException e) {
            return utcTime;
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }

}
