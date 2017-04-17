package net.xinqushi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 时间工具类。
 * 
 */

public class DateUtils {

	/**
	 * 
	 * 时间 -> 字符串
	 * 
	 */
	public static String format(Date date, String pattern) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(date);

	}

	/**
	 * 
	 * 获取系统当前时间
	 * 
	 */
	public static String nowTime() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		return df.format(new Date());// new Date()为获取当前系统时间

	}

	/**
	 * 
	 * 字符串 -> 时间
	 * 
	 */
	public static Date parse(String source) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		Date date = null;

		try {

			date = df.parse(source);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return date;

	}

	/**
	 * 
	 * 字符串 -> 时间(只有年月日)
	 * 
	 */
	public static Date parseYMD(String source) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

		Date date = null;

		try {

			date = df.parse(source);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return date;

	}

	/**
	 * 
	 * java.sql.Date -> java.util.Date
	 * 
	 */
	public static Date cast(java.sql.Date date) {

		return new Date(date.getTime());

	}

	/**
	 * 
	 * java.util.Date -> java.sql.Date
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 * 
	 */
	public static java.sql.Date cast(Date date) {

		return new java.sql.Date(date.getTime());

	}

	/**
	 * 
	 * 秒转为小时工具类
	 * 
	 */
	public static String millisToString(int millis) {

		String HH = null;

		String mm = null;

		String ss = null;

		HH = Integer.toString(millis / 3600);

		mm = Integer.toString(millis % 3600 / 60);

		ss = Integer.toString(millis % 3600 % 60);

		if ((millis % 3600 / 60) == 0) {

			mm = "00";

		}

		if ((millis / 3600) == 0) {

			HH = "00";

		}

		if ((millis % 3600 % 60) == 0) {

			ss = "00";

		}

		if ((millis % 3600 / 60) < 10 && (millis % 3600 / 60) > 0) {

			mm = null;

			mm = "0" + millis % 3600 / 60;

		}

		if ((millis % 3600 % 60) < 10 && (millis % 3600 % 60) > 0) {

			ss = null;

			ss = "0" + millis % 3600 % 60;

		}

		return HH + ":" + mm + ":" + ss;

	}

}