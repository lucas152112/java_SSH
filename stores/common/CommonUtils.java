package com.oim.stores.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.oim.stores.framework.utils.Cryto;

/**
 * 通用的工具类
 * 
 * @author chenzhaowen
 */
@SuppressWarnings("unchecked")
public class CommonUtils {
	public CommonUtils() {
	}

	// 日期时间的输出样式字符串
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String TIME_PATTERN = "HH:mm:ss";

	// 时间日期输出是样的初始化对象
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_PATTERN);
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_PATTERN);

	/**
	 * 获取当前时间指定偏移类的时间对象
	 * 
	 * @param field
	 *            偏移的域， 以Calendar常量取值， 如 Calendar.DATE
	 * @param offset
	 *            正负数偏移量
	 * @return
	 */
	public static Date getOffsetDate(int field, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(field, offset);
		return cal.getTime();
	}

	/**
	 * 获取日期对象的指定域值
	 * 
	 * @param dt
	 *            日期对象，如果为空将使用当前时间
	 * @param field
	 *            指定域， 以Calendar常量取值
	 * @param offset
	 *            正负数偏移量，不偏移取0
	 * @return
	 */
	public static int getDateField(Date dt, int field, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt == null ? new Date() : dt);
		if (offset != 0)
			;
		cal.add(field, offset);
		return cal.get(field);
	}
	/**
	 * 获取日期对象的指定域值
	 * @param dt
	 * @param field
	 * @return
	 */
	public static int getDateField(Date dt, int field) {
		return getDateField(dt, field, 0);
	}
	/**
	 * 获取日期对象的指定域值
	 * @param field
	 * @return
	 */
	public static int getDateField(int field) {
		return getDateField(null, field, 0);
	}
	/**
	 * 获取日期对象的指定域值
	 * @param field
	 * @param offset
	 * @return
	 */
	public static int getDateField(int field, int offset) {
		return getDateField(null, field, offset);
	}

	/**
	 * 格式化SQL中的字串参数(将'号改为'')
	 * 
	 * @param str
	 * @return
	 */
	public static String formatSQLString(String str) {
		if (str == null)
			return "";
		return str.replaceAll("'", "''");
	}

	/**
	 * 格式化为默认格式(yyyy-MM-dd HH:mm:ss)的日期+时间字符串
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String 指定的格式字符串
	 * @return String date或pattern为空,返回空串
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null || pattern == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 格式化为默认格式(yyyy-MM-dd)的日期字符串
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return "";
		return DATE_FORMAT.format(date);
	}

	/**
	 * 格式化为默认格式(yyyy-MM-dd HH:mm:ss)的日期+时间字符串
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDateTime(Date date) {
		if (date == null)
			return "";
		return DATE_TIME_FORMAT.format(date);
	}

	/**
	 * 格式化为默认格式(HH:mm:ss)的时间字符串
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatTime(Date date) {
		if (date == null)
			return "";
		return TIME_FORMAT.format(date);
	}

	/**
	 * 将字符串转化为日期对象,应用格式 yyyy-MM-dd
	 * 
	 * @param dateStr
	 *            String
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		try {
			return DATE_FORMAT.parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 将字符串转化为日期对象,应用指定的格式
	 * 
	 * @param dateStr
	 *            String 日期字符串
	 * @param pattern
	 *            String 格式字符串
	 * @return Date
	 */
	public static Date parseDate(String dateStr, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(dateStr);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将字符串转化为日期对象,应用格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString
	 *            String
	 * @return Date
	 */
	public static Date parseDateTime(String dateStr) {
		try {
			return DATE_TIME_FORMAT.parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 将字符串转化为日期对象,应用格式 HH:mm:ss
	 * 
	 * @param dateStr
	 *            String
	 * @return Date
	 */
	public static Date parseTime(String dateStr) {
		try {
			return TIME_FORMAT.parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 检验字串是否为空，或为空白串(包含全为空白字符情况)
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0)
			return true;
		else
			return false;
	}

	/**
	 * 解析宏表达式，
	 * 
	 * @param exp
	 *            String 包含有${key}的宏表达式
	 * @param String
	 *            key 宏名
	 * @param String
	 *            value 宏值
	 * @return String
	 */
	public static String parseMacroExp(String exp, String key, String value) {
		Map map = new HashMap();
		map.put(key, value);
		return parseMacroExp(exp, map);
	}

	/**
	 * 解析宏表达式，
	 * 
	 * @param exp
	 *            String 包含有${key}的宏表达式
	 * @param values
	 *            Map 对应于宏的值列表
	 * @return String
	 */
	public static String parseMacroExp(String exp, Map values) {
		if (CommonUtils.isEmpty(exp))
			return "";
		if (values == null || values.size() == 0)
			return exp;

		Pattern p = Pattern.compile("\\$\\{(\\w+)\\}");
		Matcher m = p.matcher(exp);
		StringBuffer sbRet = new StringBuffer();
		Object obj = null;
		String key = null;
		String strTmp = null;
		while (m.find()) {
			key = m.group(1);
			if (values.containsKey(key)) {
				obj = values.get(key);
				strTmp = (obj == null ? "" : obj.toString());
			} else {
				strTmp = "${" + key + "}";
			}
			m.appendReplacement(sbRet, strTmp);
		}
		m.appendTail(sbRet);
		return sbRet.toString();
	}

	/**
	 * 读取配置文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getConf(String key, String configFileName) {
		Properties prop = new Properties();
		try {
			InputStream is = CommonUtils.class.getResourceAsStream("/" + configFileName);
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println("配置文件找不到");
		}
		return prop.getProperty(key);
	}

	public static Properties getProFile(String configFileName) {
		Properties prop = new Properties();
		try {
			InputStream is = CommonUtils.class.getResourceAsStream("/" + configFileName);
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println("配置文件找不到");
		}
		return prop;
	}

	// 钱保留两位
	@SuppressWarnings("static-access")
	public static Double getMoney(String money) {
		if (null != money) {
			BigDecimal bd = new BigDecimal(money);
			BigDecimal bd1 = bd.setScale(2, bd.ROUND_HALF_UP);
			Double pDouble = bd1.doubleValue();
			@SuppressWarnings("unused")
			long ll = Double.doubleToLongBits(pDouble);
			return pDouble;
		}
		return 0.00;
	}

	/**
	 * 判断是否自然数
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNaturalNumber(String val) {
		boolean ret = false;
		if (null != val) {
			String regex = "^\\d+$";
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(val);
			if (matcher.find())
				ret = true;
		}
		return ret;

	}
	
	/**
	 * 发送短信
	 * @param content 内容
	 * @param mobiles 手机号码，以逗号分隔
	 * @return
	 * @throws IOException 
	 */
	public static String sendShortMsg(String content,String mobiles) throws IOException{
		URL url = new URL("http://221.179.216.74/providermt");//urlstring请求地址
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    conn.setUseCaches(false);
	    conn.setInstanceFollowRedirects(true);
	    conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
	    conn.connect();
	    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
	    bw.write(xmlCon(content,mobiles));
	    bw.flush();
	    bw.close();
	    BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//设置编码,否则中文乱码 
	    String line="";
	    String ret="";
	    while ((line=reader.readLine())!=null) {
			ret=ret+line;
		}
	    reader.close();
	    conn.disconnect();
		return getResult(ret);
	}
	
	public static String getResult(String ret){
		StringReader read=new StringReader(ret);
		//创建输入源解析器
		InputSource source=new InputSource(read);
		SAXBuilder sax=new SAXBuilder();
		String result=null;
		try {
			//通过一个输入源创建一个doc
			Document doc=sax.build(source);
			Element el=doc.getRootElement();
			result=el.getChildText("result");
			//System.out.println(el.getChildText("result"));
			if("0".equals(result)){
				result="发送成功";
			}else if("1001".equals(result)){
				result="重要字段有空值";
			}else if("1002".equals(result)){
				result="帐号密码验证错误";
			}else if("1003".equals(result)){
				result="IP限制访问";
			}else if("1004".equals(result)){
				result="下行内容超长，超出180个字";
			}else if("1005".equals(result)){
				result="群发号码过多，超出100个";
			}else if("1006".equals(result)){
				result="子端口不合法";
			}else if("1007".equals(result)){
				result="信息内容非法";
			}else if("1008".equals(result)){
				result="非法CPID";
			}else if("1009".equals(result)){
				result="子端口不合法";
			}else if("9999".equals(result)){
				result="未知错误";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String xmlCon(String content,String mobiles) throws UnsupportedEncodingException{
		String xml;
		String date=getDate();
		byte[] ms=content.getBytes("utf-8");
		String msg=new String(ms);
		String pwd2=Cryto.cryptMD5ToHEX("e72575e59058bf40e1dc07ed359735e7"+date, "utf-8");
		xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><MtPacket><cpid>030000001626"+
				"</cpid><mid>0</mid>" +
				"<cpmid></cpmid><mobile>"+mobiles+"</mobile><port>0097</port><msg>"+msg+"</msg>" +
				"<signature>"+pwd2+"</signature><timestamp>" +date+
				"</timestamp><validtime></validtime></MtPacket>";
		return xml;
	}
	private static String getDate(){
		Calendar dar=Calendar.getInstance();
		String ye=String.valueOf(dar.get(Calendar.YEAR)).substring(2, 4);
		int mon=dar.get(Calendar.MONTH);
		String month;
		if(mon<9){
			month="0"+String.valueOf(dar.get(Calendar.MONTH)+1);
		}else{
			month=String.valueOf(dar.get(Calendar.MONTH)+1);
		}
		String day;
		int dd=dar.get(Calendar.DATE);
		if(dd<10){
			day="0"+String.valueOf(dar.get(Calendar.DATE));
		}else{
			day=String.valueOf(dar.get(Calendar.DATE));
		}
		int hh=dar.get(Calendar.HOUR);
		String hour;
		if(hh<10){
			hour="0"+String.valueOf(dar.get(Calendar.HOUR));
		}else{
			hour=String.valueOf(dar.get(Calendar.HOUR));
		}
		int mm=dar.get(Calendar.MINUTE);
		String min;
		if(mm<10){
			min="0"+String.valueOf(dar.get(Calendar.MINUTE));
		}else{
			min=String.valueOf(dar.get(Calendar.MINUTE));
		}
		String dateStr=ye+month+day+hour+min;
		return dateStr;
	}
}
