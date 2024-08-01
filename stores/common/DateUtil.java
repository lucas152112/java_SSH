package com.oim.stores.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {
	
	private DateUtil(){}

	public static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30,
			31, 31, 30, 31, 30, 31 }; // 此数组定义了平年中每个月都有多少天

	public static final SimpleDateFormat sdf = new SimpleDateFormat(); // 定义一个日期格式化类实例

	/**
	 * yyyy-MM-dd HH:mm:ss SSS
	 */
	public static final String MILLI_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

	/**
	 * yyyy-MM-dd HH:mm:ss,SSS
	 */
	public static final String MILLI_PATTERN_COMMA = "yyyy-MM-dd HH:mm:ss,SSS";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String SECOND_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";

	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_DAY_PATTERN = "yyyy-MM-dd";
	
	/**
	 * yyyy-MM
	 */
	public static final String MONTH_PATTERN = "yyyy-MM";

	/**
	 * yyyy-MM-dd_HH-mm-ss
	 */
	public static final String DATE_FILE_PATTERN = "yyyy-MM-dd_HH-mm-ss";

	/**
	 * yyyy-MM-dd_HH-mm-ss_SSS
	 */
	public static final String DATE_FILE_MILLI_PATTERN = "yyyy-MM-dd_HH-mm-ss_SSS";

	/**
	 * MM/dd/yyyy
	 */
	public static final String DATE_FORMAT = "MM/dd/yyyy";

	/**
	 * MM/dd/yyyy HH:mm 标准时间格式
	 */
	public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm";

	/**
	 * MM/dd/yyyy HH:mm:ss 带时分秒的标准时间格式
	 */
	public static final String DATE_TIME_EXTENDED_FORMAT = "MM/dd/yyyy HH:mm:ss";
	
	/**
	 * yy-MM-dd 
	 */
	public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";
	
	/**
	 * yyyy获取年份
	 */
	public static final String DATE_FORMAT_YYYY = "yyyy";

	/**
	 * MM/dd/yyyy HH:mm:ss SSS
	 */
	public static final String DATE_TIME_MILLI_FORMAT = "MM/dd/yyyy HH:mm:ss SSS";

	/**
	 * MM/dd/yyyy HH:mm:ss,SSS
	 */
	public static final String DATE_TIME_MILLI_FORMAT_COMMA = "MM/dd/yyyy HH:mm:ss,SSS";

	/**
	 * yyyyMMdd ORA标准日期格式
	 */
	public static final String ORA_DATE_FORMAT = "yyyyMMdd";

	/**
	 * yyyyMMddHHmm ORA标准时间格式
	 */
	public static final String ORA_DATE_TIME_FORMAT = "yyyyMMddHHmm";

	/**
	 * yyyyMMddHHmmss 带时分秒的ORA标准时间格式
	 */
	public static final String ORA_DATE_TIME_EXTENDED_FORMAT = "yyyyMMddHHmmss";

	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final String ORA_DATE_TIME_MILLI_FORMAT = "yyyyMMddHHmmssSSS";

	/**
	 * yyyyMMddHHmmss,SSS
	 */
	public static final String ORA_DATE_TIME_MILLI_FORMAT_COMMA = "yyyyMMddHHmmss,SSS";

	/**
	 * 星期几
	 */
	public static final String WEEK_DAY = "E";

	public static int YEAR = Calendar.YEAR;

	public static int MONTH = Calendar.MONTH;

	public static int DAY_OF_MONTH = Calendar.DAY_OF_MONTH;

	public static int HOUR_OF_DAY = Calendar.HOUR_OF_DAY;

	public static int MINUTE = Calendar.MINUTE;

	public static int SECOND = Calendar.SECOND;

	public static int MILLISECOND = Calendar.MILLISECOND;

	/**
	 * 获得一个当前系统时间的Calendar对象的实例
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获得一个当前系统时间的GregorianCalendar对象的实例
	 */
	public static GregorianCalendar getGregorianCalendar() {
		return (GregorianCalendar) Calendar.getInstance();
	}

	/**
	 * 获得一个当前系统时间的Date对象的实例
	 */
	public static Date getDate() {
		return new Date();
	}

	
	/**
	 * 获得一个指定时间的Calendar对象的实例
	 */
	public static Calendar getCalendar(Date date) {
		Calendar cal = DateUtil.getCalendar();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 获得一个指定时间的GregorianCalendar对象的实例
	 */
	public static GregorianCalendar getGregorianCalendar(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		return gc;
	}

	/**
	 * 获得一个指定时间的Date对象的实例
	 */
	public static Date getDate(Date date) {
		return DateUtil.getGregorianCalendar(date).getTime();
	}

	/**
	 * 获得一个计算指定日期的Date对象的实例 对日期中的某个部分进行加减计算
	 */
	public static Date getCalculateDate(Date date, int addNumber,
			int addField) {
		if(date != null){
			GregorianCalendar gc = DateUtil.getGregorianCalendar(date);
			gc.add(addField, addNumber);
			return gc.getTime();
		}else{
			return null;
		}
	}

	public static int getDateField(Date date, int getField) {
		if(date != null){
			GregorianCalendar gc = DateUtil.getGregorianCalendar(date);
			int field = gc.get(getField);
			if (getField == DateUtil.MONTH) {
				field = field + 1;
			}
			return field;
		}else{
			return -1;
		}
	}

	/**
	 * 传入一个Date对象实例和格式字符串 获得相应格式的日期字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getStringDateFormat(Date date,
			String pattern) {
		if(date != null){
			synchronized (sdf) {
				String str = null;
				sdf.applyPattern(pattern);
				str = sdf.format(date);
				return str;
			}
		}else{
			return "";
		}
	}

	/**
	 * 传入一个Date对象实例和格式字符串 获得相应格式的日期对象
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date getDateFormat(Date date, String pattern) {
		return DateUtil.parseDateFormat(DateUtil.getStringDateFormat(date,
				pattern), pattern);
	}

	/**
	 * 传入一个日期字符串和格式字符串 获得相应格式的日期对象实例
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date parseDateFormat(String strDate,
			String pattern) {
		if(strDate != null && !strDate.equals("")){
			synchronized (sdf) {
				Date date = null;
				sdf.applyPattern(pattern);
				try {
					date = sdf.parse(strDate);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		}else{
			return null;
		}
	}

	/**
	 * 传入一个日期字符串和格式字符串 获得相应格式的日期对象实例 出错抛出异常
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateFormatFromString(String strDate,String pattern) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date =format.parse(strDate);
		return date;
	}
	
	/**
	 * 传入一个日期字符串和格式字符串 获得相应格式的日期字符串
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static String parseStringDateFormat(String strDate,
			String pattern) {
		return DateUtil.getStringDateFormat(DateUtil.parseDateFormat(strDate,
				pattern), pattern);
	}

	/**
	 * 创建一个标准日期格式
	 * @param pattern
	 * @return 标准日期格式的克隆
	 */
	public static DateFormat getDateFormat(String pattern) {
		SimpleDateFormat theDateFormat = new SimpleDateFormat(pattern);
		return theDateFormat;
	}

	/**
	 * 创建一个标准日期格式的克隆
	 * @param pattern
	 * @return 标准日期格式的克隆
	 */
	public static DateFormat getDateFormatClone(String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat theDateFormat = (SimpleDateFormat) dateFormat.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	/**
	 * 比较两个日期对象之间的大小 firstDate早于secondDate
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return boolean 如果firstDate早于secondDate,返回true
	 */
	public static boolean compareTwoDate(Date firstDate,
			Date secondDate,String pattern) throws Exception {
		if(firstDate != null && secondDate != null){
			if(pattern==null || pattern.equals("")){
				pattern=DateUtil.DATE_DAY_PATTERN;
			}
			firstDate=DateUtil.getDateFormat(firstDate,pattern);
			secondDate=DateUtil.getDateFormat(secondDate,pattern);
			if (firstDate.before(secondDate)) {
				return true;
			} else {
				return false;
			}
		}else{
			throw new Exception("the firstDate or the secondDate null");
		}
		
	}

	public static boolean compareTwoDate(Date firstDate,
			Date secondDate) throws Exception {
		return compareTwoDate(firstDate,secondDate,null);
	}
	
	/**
	 * 计算两个日期对象之间的天数 secondDate-firstDate
	 * @param firstDate
	 * @param secondDate
	 * @return
	 * @throws Exception
	 */
	public static long CalculateBetweenTwoDate(Date firstDate,
			Date secondDate)throws Exception {
		if(firstDate != null && secondDate != null){
			long days = 0;
			Date first = DateUtil.getDateFormat(firstDate,
					DateUtil.DATE_DAY_PATTERN);
			Date second = DateUtil.getDateFormat(secondDate,
					DateUtil.DATE_DAY_PATTERN);
			days = (second.getTime() - first.getTime()) / (24 * 60 * 60 * 1000); // getTime()方法返回的是豪秒数
			return days;
		}else{
			throw new Exception("the firstDate or the secondDate null");
		}
	}
	
	/**
	 * 计算两个日期对象之间的分钟数 secondDate-firstDate
	 * @param firstDate
	 * @param secondDate
	 * @return
	 * @throws Exception
	 */
	public static long CalculateBetweenTwoMin(Date firstDate,
			Date secondDate)throws Exception {
		if(firstDate != null && secondDate != null){
			long min = 0;
			Date first = DateUtil.getDateFormat(firstDate,
					DateUtil.SECOND_PATTERN);
			Date second = DateUtil.getDateFormat(secondDate,
					DateUtil.SECOND_PATTERN);
			min = (second.getTime() - first.getTime()) / (60*1000); // getTime()方法返回的是豪秒数
			return min;
		}else{
			throw new Exception("the firstDate or the secondDate null");
		}
	}

	/**
	 * 取得指定日期的所处月份的第一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getFirstDayOfMonth(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return gc.getTime();
	}

	/**
	 * 传入Date对象实例并取得指定日期的下一个月的第一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getFirstDayOfNextMonth(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 取得指定日期的所处星期的第一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getFirstDayOfWeek(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY  ):
			gc.add(Calendar.DATE, 0);
			break;
		case (Calendar.MONDAY  ):
			gc.add(Calendar.DATE, -1);
			break;
		case (Calendar.TUESDAY	):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.WEDNESDAY  ):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.THURSDAY	 ):
			gc.add(Calendar.DATE, -4);
			break;
		case (Calendar.FRIDAY  ):
			gc.add(Calendar.DATE, -5);
			break;
		case (Calendar.SATURDAY	 ):
			gc.add(Calendar.DATE, -6);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一个星期的第一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getFirstDayOfNextWeek(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getFirstDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 传入实际的月份,得到当前年份指定月份的最后一天
	 * 
	 * @param month
	 * @return int
	 */
	public static int getLastDayOfMonth(int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear()) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	/**
	 * 传入实际的月份和年份,得到指定年份指定月份的最后一天
	 * 
	 * @param month
	 * @return int
	 */
	public static int getLastDayOfMonth(int year, int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear(year)) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	/**
	 * 取得指定日期的所处星期的最后一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getLastDayOfWeek(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY  ):
			gc.add(Calendar.DATE, 6);
			break;
		case (Calendar.MONDAY  ):
			gc.add(Calendar.DATE, 5);
			break;
		case (Calendar.TUESDAY	):
			gc.add(Calendar.DATE, 4);
			break;
		case (Calendar.WEDNESDAY  ):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.THURSDAY	 ):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.FRIDAY  ):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY	 ):
			gc.add(Calendar.DATE, 0);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期的所处月份的最后一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getLastDayOfMonth(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// 检查闰年
		if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY)
				&& (isLeapYear(gc.get(Calendar.YEAR)))) {
			gc.set(Calendar.DAY_OF_MONTH, 29);
		}
		return gc.getTime();
	}

	/**
	 * 传入Date对象实例并取得指定日期的下一个月的最后一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getLastDayOfNextMonth(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextMonth(gc.getTime()));
		gc.setTime(DateUtil.getLastDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 传入Date对象实例并取得指定日期的下一个星期的最后一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getLastDayOfNextWeek(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.setTime(DateUtil.getNextWeek(gc.getTime()));
		gc.setTime(DateUtil.getLastDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	/**
	 * 传入Date对象实例并得到指定日期的前一个工作日
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getPreviousWeekDay(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, -2);
			break;
		default:
			gc.add(Calendar.DATE, -1);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 传入Date对象实例并得到指定日期的下一个工作日
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getNextWeekDay(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, 2);
			break;
		default:
			gc.add(Calendar.DATE, 1);
			break;
		}
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一个月
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getNextMonth(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MONTH, 1);
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getNextDay(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return gc.getTime();
	}
	
	/**
	 * 取得指定日期加几天
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getNextDay(Date date,int day) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, day);
		return gc.getTime();
	}

	/**
	 * 取得指定日期的下一个星期
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getNextWeek(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, 7);
		return gc.getTime();
	}

	/**
	 * 判断当前是否为闰年
	 * 
	 * @return boolean
	 */
	public static boolean isLeapYear() {
		Calendar cal = DateUtil.getCalendar();
		int year = cal.get(Calendar.YEAR);
		return isLeapYear(year);
	}

	/**
	 * 判断指定的年份是否为闰年
	 * 
	 * @return boolean
	 */
	public static boolean isLeapYear(int year) {
		
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 传入Date对象实例并判断是否是闰年
	 * 
	 * @param date
	 * @return boolean
	 */
	public static boolean isLeapYear(Date date) {
		GregorianCalendar gc = DateUtil.getGregorianCalendar();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		return isLeapYear(year);
	}
	
}
