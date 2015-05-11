package com.qiubai.tool;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {

	public static String getCurrentTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		String currentTime = sdf.format(System.currentTimeMillis());
		return currentTime;
	}

	/**
	 * @return 当前时间 2015-05-06 14:25:00
	 */
	public static String getCurrentTime() {
		String format = "yyyy-MM-dd HH:mm:ss";
		return getCurrentTime(format);
	}
	
	
	/**
	 * @return 2015/05/06
	 */
	public static String getCurrentDayTime(){
		String format = "yyyy/MM/dd";
		return getCurrentTime(format);
	}
	
	
}
