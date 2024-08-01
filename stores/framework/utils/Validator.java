package com.oim.stores.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * @author iampc
 *
 */
public class Validator {
    public static boolean quickValidate(String express, String value) {
	Pattern pattern = Pattern.compile(express, Pattern.CASE_INSENSITIVE);
	Matcher match = pattern.matcher(value);
	return match.matches();
    }

    /**
     * 判断是否域名
     * 
     * @author zhuangjf
     * @param value
     * @return boolean
     * @throws
     * */
    public static boolean isCommonDomain(String value) {
	String express = "^(www.)?(\\w+\\.){1,3}(org|org.cn|gov.cn|com|cn|net|cc|info)$";
	return quickValidate(express, value);
    }

    /**
     * 判断是否邮件格式
     * 
     * @author zhuangjf
     * @param
     * @return
     * @throws
     * */
    public static boolean isEmail(String value) {
	String express = "^\\w+([-+.]\\w+)*@(\\w+([-.]\\w+)*\\.)+([a-zA-Z]+)+$";
	return quickValidate(express, value);
    }

    /**
     * 判断是否整数
     * 
     * @author zhuangjf
     * @param
     * @return
     * @throws
     * */
    public static boolean isInteger(String value) {
	String express = "^[-]?[1-9]*[0-9]*$";
	return quickValidate(express, value);
    }

    /**
     * 判断是否数字，含小数
     * 
     * @author zhuangjf
     * @param
     * @return
     * @throws
     * */
    public static boolean isNumber(String value) {
	// ^(0|([1-9]+[0-9]*))(.[0-9]+)?$//正数
	String express = "^(0|([-]?[1-9]+[0-9]*))(.[0-9]+)?$";
	return quickValidate(express, value);
    }

    public static boolean isChinese(String value) {
	String express = "^[\u4E00-\u9FA5\uF900-\uFA2D]+$";
	return quickValidate(express, value);
    }

    public static boolean isMobileNo(String value) {
	String express = "^(13|15|18)\\d{9}$";
	return quickValidate(express, value);
    }

    public static boolean isWordAndNum(String value) {
	// String express="^[a-zA-Z0-9_]{3,16}$";
	String express = "^[a-zA-Z0-9_]$";
	return quickValidate(express, value);
    }
    

    public static boolean isNullOrEmpty(String value) {
	boolean flag = true;
	if (value != null && !value.equals("")) {
	    flag = false;
	}
	return flag;
    }

    public static void main(String[] arg) {
	boolean ret = isCommonDomain("sina.com");
	ret = isWordAndNum("abd1_2r3fsdf_fsf");
	System.out.println(ret);
    }
}
