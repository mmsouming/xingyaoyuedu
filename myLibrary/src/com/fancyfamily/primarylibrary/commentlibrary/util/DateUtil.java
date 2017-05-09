package com.fancyfamily.primarylibrary.commentlibrary.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类。
 *
 */
public class DateUtil {

	/** 常规日期格式，24小时制格式  **/
	public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 取得表示当天的时间对象
	 * @return
	 */
	public static Date getCurrentDay() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}


	public static Date parseStringToDate(String dateStr, String format) {
		if (TextUtils.isEmpty(dateStr))
			return null;
		try {
			DateFormat simpleDateFormatForMinute = new SimpleDateFormat(format);
			return simpleDateFormatForMinute.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 解析简单格式yyyy-MM-dd HH:mm:ss的日期字符串
	 * @param simpleDateStr
	 * @return
	 */
	public static Date parseDateNormal(String simpleDateStr) {
		DateFormat simpleDateFormat = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		if (TextUtils.isEmpty(simpleDateStr))
			return null;
		try {
			return simpleDateFormat.parse(simpleDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回某个时间的"yyyy-MM-dd HH:mm:ss"字符串
	 * @param time
	 * @return
	 */
	public static String getTimeString(Long time) {
		final DateFormat simpleDateFormat = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		return simpleDateFormat.format(new Date(time));
	}


	/**
	 * 将时间按格式转换为字符串，日期为空时转换为空字符串
	 *
	 * @param date
	 * @param patten
	 * @return
	 */
	public static String formatDateToString(Date date, String patten) {
		if (null == date)
			return "";
		SimpleDateFormat sd = new SimpleDateFormat(patten);
		return sd.format(date);
	}

	/**
	 * 将时间按24小时制格式("yyyy-MM-dd HH:mm:ss")转换为字符串，日期为空时转换为空字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateNormal(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat sd = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		return sd.format(date);
	}

	/**
	 * 将时间按24小时制格式("yyyy-MM-dd HH:mm:ss")转换为字符串，日期为空时转换为空字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateNormal(Long date) {
		if (null == date)
			return "";
		SimpleDateFormat sd = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		return sd.format(new Date(date));
	}

    /**
     * 根据时间戳判断是否是今天
     * @param timestamp
     * @return true表示当前时间戳时间今天，false表示不是今天
     */
    public static boolean isToday(long timestamp){
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();	//今天
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
        today.set( Calendar.HOUR_OF_DAY, 0);
        today.set( Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        long result=timestamp-today.getTimeInMillis();
        if(result>0&&result<24*60*60*1000){
            return true;
        }
        return false;
    }
}
