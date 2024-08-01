/**
 * 
 */
package com.oim.stores.framework.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 日期时间工具类
 * @author zhuangjf
 *
 */
public class DateUtil {
	
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	
	/**
	 * timestamp 
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp,String pattern){
		if (timestamp == null){
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")){
			pattern = PATTERN_STANDARD;;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp.getTime()));
	}
	
	/**
	 * java.util.Date ת String
	 */
	public static String date2String(java.util.Date date,String pattern){
		if (date == null){
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")){
			pattern = PATTERN_STANDARD;;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	/**
	 * timestamp
	 */
	public static Timestamp currentTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * timestampתstr
	 */
	public static String currentTimestamp2String(String pattern){
		return timestamp2String(currentTimestamp(),pattern);
	}
	
	
	public static Timestamp string2Timestamp(String strDateTime,String pattern){
		if (strDateTime == null || strDateTime.equals("")){
			throw new java.lang.IllegalArgumentException("Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")){
			pattern = PATTERN_STANDARD;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try{
			date = sdf.parse(strDateTime);
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}
	
	/**
	 */
	public static Date string2Date(String strDate,String pattern){
		if (strDate == null || strDate.equals("")){
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")){
			pattern = DateUtil.PATTERN_DATE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		
		try{
			date = sdf.parse(strDate);
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * stringToYear
	 * @param strDest
	 * @return
	 */
	public static String stringToYear(String strDest){
		if (strDest == null || strDest.equals("")){
			throw new java.lang.IllegalArgumentException("str dest null");
		}
		
		Date date = string2Date(strDest,DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.valueOf(c.get(Calendar.YEAR));
	}
	
	/**
	 * 
	 * @param strDest
	 * @return
	 */
	public static String stringToMonth(String strDest){
		if (strDest == null || strDest.equals("")){
			throw new java.lang.IllegalArgumentException("str dest null");
		}
		
		Date date = string2Date(strDest,DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//return String.valueOf(c.get(Calendar.MONTH));
		int month = c.get(Calendar.MONTH);
		month = month + 1;
		if (month <10){
			return "0" + month;
		}
		return String.valueOf(month);
	}
	
	/**
	 * 
	 * @param strDest
	 * @return
	 */
	public static String stringToDay(String strDest){
		if (strDest == null || strDest.equals("")){
			throw new java.lang.IllegalArgumentException("str dest null");
		}
		
		Date date = string2Date(strDest,DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 10){
			return "0" + day;
		}
		return "" + day;
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public static Date getFirstDayOfMonth(Calendar c){
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.set(year, month, day,0,0,0);
		return c.getTime();
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public static Date getLastDayOfMonth(Calendar c){
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;
		if (month >11){
			month = 0;
			year = year + 1;
		}
		c.set(year, month, day-1,0,0,0);
		return c.getTime();
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String date2GregorianCalendarString(Date date){
		if (date == null){
			throw new java.lang.IllegalArgumentException("Date is null");
		}
		long tmp  = date.getTime();
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTimeInMillis(tmp);
		try {
			XMLGregorianCalendar t_XMLGregorianCalendar =DatatypeFactory.newInstance().newXMLGregorianCalendar(ca);
			return t_XMLGregorianCalendar.normalize().toString();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new java.lang.IllegalArgumentException("Date is null");
		}

	}
	
	public static boolean compareDate(Date firstDate,Date secondDate){
		if (firstDate == null || secondDate == null){
			throw new java.lang.RuntimeException();
		}
		
		String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
		String strSecondDate = date2String(secondDate,"yyyy-MM-dd");
		if (strFirstDate.equals(strSecondDate)){
			return true;
		}
		return false;
	}
	
	public static Date firstOfQuarter(Date date){
		if(date == null){
			throw new java.lang.RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (calendar.get(Calendar.MONTH)) {
		case 0: case 1: case 2:
			calendar.set(Calendar.MONTH, 0);
			break;
		case 3: case 4: case 5:
			calendar.set(Calendar.MONTH, 3);
			break;
		case 6: case 7: case 8:
			calendar.set(Calendar.MONTH, 6);
			break;
		case 9: case 10: case 11:
			calendar.set(Calendar.MONTH, 9);
			break;
		}
		
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(df.format(calendar.getTime()));
		} catch (ParseException e) {
			throw new java.lang.RuntimeException();
		}
	}
	public static Date lastOfQuarter(Date date){
		Date firstDate = firstOfQuarter(date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 3);
		
		return calendar.getTime();
	}
	
	public static Date getLastDayOfMonth(Date date){
		if(date == null){
			throw new java.lang.RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	public static Date getDateAfterMonth(Date date, int amount){
		if(date == null){
			throw new java.lang.RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}
	
	public static int intervalMinute(Timestamp one,Timestamp two){
	    return (int)(two.getTime()-one.getTime())/(1000*60);//分钟差
	}
	
}