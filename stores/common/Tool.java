package com.oim.stores.common;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
/**
 * 工具类
 * @author Administrator
 *
 */
public class Tool {
	public final static String DATE_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";//时间格式

	public final static String YYYY_MM_DD = "yyyy-MM-dd";//日期格式

	public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";//日期分格式

	public final static String YY_MM_DD_HH_MM = "yy-MM-dd HH:mm";//日志分格式
	/**
	 * null校验
	 * @param obj
	 * @return
	 */
	public static String nul(String obj) {
		if (null == obj) {
			return "";
		} else
			return obj;
	}
	/**
	 * 非空校验
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(String obj) {
		if (null == obj || "".equals(obj.trim())) {
			return false;
		} else
			return true;
	}
	/**
	 * 转换数组成字符串
	 * @param ary
	 * @return
	 */
	public static String getArrayToString(String[] ary) {
		String str = "";
		if (null != ary) {
			for (int i = 0; i < ary.length; i++) {
				str += ary[i];
				if (i != (ary.length - 1)) {
					str += ",";
				}
			}
		}
		return str;

	}

	/**
	 * 转编码
	 * 
	 * 
	 * @param str
	 *            字符串
	 * 
	 * @return 字符串
	 */
	public static String encode(String str) {
		try {
			str = java.net.URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 转编码
	 * 
	 * 
	 * @param str
	 *            字符串
	 * 
	 * @return 字符串
	 */
	public static String encodeToUtf8(String str) {
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 取当前系统时间
	 * 
	 * 
	 * @param str
	 *            字符串
	 * 
	 * @return 字符串
	 */
	public static String getSysDateTime(String format) {
		format = null == format ? "yyyy-MM-dd HH:mm:ss" : format;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 时间
	 * 
	 * 
	 * @param str
	 *            字符串
	 * 
	 * @return 字符串
	 */
	public static String getFormatDateTime(Date date, String format) {
		if (null != date) {
			format = null == format ? "yyyy-MM-dd HH:mm:ss" : format;
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else
			return "";
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getSysDateTime(Date date, String format) {
		if (null != date) {
			format = null == format ? "yyyy-MM-dd HH:mm:ss" : format;
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else
			return "";
	}

	/**
	 * 读取配置文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getConf(String str, String configFileName) {
		Properties prop = new Properties();
		try {
			InputStream is = Tool.class.getResourceAsStream("/" + configFileName);
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println("配置文件找不到");
		}
		return prop.getProperty(str);
	}

	/**
	 * 数组转字符串
	 * 
	 * @param str
	 * @param tag
	 * @return
	 */
	public static String arrayToString(String[] str, String tag) {
		String s = "";
		for (String t : str) {
			s += t + tag;
		}
		return s;
	}
	/**
	 * 获取文件类型
	 * @param filename
	 * @return
	 */
	public static String getFileType(String filename) {
		return filename.substring(filename.indexOf("."), filename.length());
	}
	/**
	 * 替换字符串中的单引号
	 * @param str
	 * @return
	 */
	public static String stringTrope(String str) {
		if (null != str) {
			return str.replaceAll("'", "&#039;");
		} else {
			return "";
		}
	}
	/**
	 * 使用’替换字符串中字符
	 * @param str
	 * @return
	 */
	public static String stringGoBackTrope(String str) {
		if (null != str) {
			return str.replaceAll("&#039;", "'");
		} else {
			return "";
		}
	}
	/**
	 * 替换全部符合字符串
	 * @param str
	 * @return
	 */
	public static String stringTropeAll(String str) {
		if (null != str) {
			return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&#039;").replaceAll("\"", "&quot;");
		} else {
			return "";
		}
	}
	/**
	 * 替换部分字符串
	 * @param str
	 * @return
	 */
	public static String stringGoBackTropeAll(String str) {
		if (null != str) {
			return str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&#039;", "'").replaceAll("&quot;", "\"");
		} else {
			return "";
		}
	}
	/**
	 * 过滤br标记
	 * @param str
	 * @return
	 */
	public static String filterBrTag(String str) {
		if (null != str)
			return str.replaceAll("^<br[ ]?[/]?>+|<br[ ]?[/]?>+$", "");
		else
			return null;
	}
}
