package com.oim.stores.framework.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * request工具类
 * @author zhuangjf
 *
 */
public class RequestUtils {

	private static Log logger = LogFactory.getLog(RequestUtils.class);

	/**
	 * get request enctype type
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getExtractType(HttpServletRequest request)
			throws IOException {
		String type = null;
		String type1 = request.getHeader("Content-Type");
		String type2 = request.getContentType();
		if (type1 == null && type2 != null) {
			type = type2;
		} else if (type2 == null && type1 != null) {
			type = type1;
		} else if (type1 != null && type2 != null) {
			type = (type1.length() > type2.length() ? type1 : type2);
		}
		return type;
	}

	// ---------form enctype !=
	// multipart/form-data--------------------------------------------------
	/**
	 * get LinkedHashMap key=lsh ,value=HttpRequestObject form enctype !=
	 * multipart/form-dat
	 */
	public static Map<String, HttpRequestObject> getHttpRequestObject(
			HttpServletRequest request) {
		Map<String, HttpRequestObject> map = new LinkedHashMap<String, HttpRequestObject>();
		Enumeration names = request.getParameterNames();
		int pos = 1;
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] strs = request.getParameterValues(name);
			if (null == strs) {
				HttpRequestObject hro = new HttpRequestObject();
				hro.setParamType("param");
				hro.setName(name);
				hro.setValue("".getBytes());
				map.put(Integer.toString(pos), hro);
				pos++;
			} else {
				for (int i = 0; i < strs.length; i++) {
					HttpRequestObject hro = new HttpRequestObject();
					hro.setParamType("param");
					hro.setName(name);
					hro.setValue(null == strs[i] ? "".getBytes() : strs[i]
							.getBytes());
					map.put(Integer.toString(pos), hro);
					pos++;
				}
			}
		}
		return map;
	}

	/**
	 * put request param to HashMap param must be single String form enctype !=
	 * multipart/form-dat
	 * 
	 * @param request
	 * @return HashMap key=value, name=StringValue
	 */
	public static Map<String, String> getNormalMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			String value = request.getParameter(key);
			value = value == null ? "" : value.trim();
			value = value.equals("") ? null : value;
			map.put(key, value);
		}
		return map;
	}

	// ---------form enctype ==
	// multipart/form-data--------------------------------------------------
	/**
	 * get first params String value by paramsName from enctype must be
	 * multipart/form-data if not find return ""
	 * 
	 * @param map
	 *            LinkedHashMap key=value(lsh=HttpRequestObject)
	 * @param paramName
	 * @return StringValue
	 */
	public static String getParamString(Map<String, HttpRequestObject> map,
			String paramName) {
		Iterator iter = map.keySet().iterator();
		String fieldvalue = "";
		while (iter.hasNext()) {
			HttpRequestObject hobj = map.get(iter.next());
			String name = hobj.getName();
			if (name.equals(paramName)) {
				fieldvalue = new String(hobj.getValue());
				break;
			}
		}
		return fieldvalue;
	}

	/**
	 * params String values by paramsName from enctype must be
	 * multipart/form-data if not find paramsName then return null
	 * 
	 * @param map
	 * @param paramName
	 * @return
	 */
	public static String[] getParamStringValues(
			Map<String, HttpRequestObject> map, String paramName) {
		List<String> list = getParamListValues(map, paramName);
		if (null == list)
			return null;
		else
			return list.toArray(new String[list.size()]);
	}

	public static List<String> getParamListValues(
			Map<String, HttpRequestObject> map, String paramName) {
		List<String> list = new LinkedList<String>();
		Iterator iter = map.keySet().iterator();
		String fieldvalue = "";
		while (iter.hasNext()) {
			Object item = (Object) iter.next();
			HttpRequestObject hobj = map.get(item);
			String name = hobj.getName();
			if (name.equals(paramName)) {
				fieldvalue = new String(hobj.getValue());
				list.add(fieldvalue);
			}
		}
		if (list.size() == 0)
			return null;
		else
			return list;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, List<String>> getGroupByNameParam(
			Map<String, HttpRequestObject> map) {
		Map<String, List<String>> returnMap = new HashMap<String, List<String>>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String serial = (String) it.next();
			HttpRequestObject hro = map.get(serial);
			String keyname = hro.getName();
			if (returnMap.get(keyname) == null) {
				List<String> list = new LinkedList<String>();
				list.add(serial);
				returnMap.put(keyname, list);
			} else {
				returnMap.get(keyname).add(serial);
			}
		}
		logger.debug("\r\n\t name index in request map:" + returnMap);
		return returnMap;
	}

	/**
	 * get request param and value param must be single form
	 * enctype="multipart/form-data"
	 * 
	 * @param sourcemap
	 *            LinkedHashMap key=lsh value=HttpRequestObject
	 * @return LinkedHashMap key=HttpRequestObject.name
	 *         value=HttpRequestObject.value
	 * @throws Exception
	 */

	public static Map<String, String> getNormalMap(
			Map<String, HttpRequestObject> sourcemap) throws Exception {
		Map<String, List<String>> map = getGroupByNameParam(sourcemap);
		Iterator itor = map.keySet().iterator();
		Map<String, String> destmap = new LinkedHashMap<String, String>();
		while (itor.hasNext()) {
			String key = (String) itor.next();
			List list = map.get(key);
			if (list.size() == 1) { 
				HttpRequestObject hobj = sourcemap.get(list.get(0));
				if (hobj == null) {
					continue;
				}
				if (hobj.getParamType().equals("param")) {
					String value = hobj.getValue() == null ? "" : new String(
							hobj.getValue());
					value = value.equals("") ? null : value;
					destmap.put(key, value);
				}
			}
		}
		return destmap;
	}

	public static Map<String, List> getMultiValueMap(
			LinkedHashMap<String, HttpRequestObject> sourcemap)
			throws Exception {
		Map<String, List<String>> map = getGroupByNameParam(sourcemap);
		Iterator itor = map.keySet().iterator();
		Map<String, List> destmap = new LinkedHashMap<String, List>();
		while (itor.hasNext()) {
			String key = (String) itor.next();
			List list = map.get(key);
			if (list.size() > 1) {
				HttpRequestObject hobj = sourcemap.get(list.get(0));
				if (hobj == null) {
					continue;
				}
				destmap.put(key, list);
			}
		}
		return destmap;
	}

	/**
	 * ��ȡkey��value(LinkedList)�ĳ���
	 * 
	 * @param sourceMap
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static int getSizeByKey(Map<String, HttpRequestObject> sourceMap,
			String key) throws Exception {
		int size = 0;
		Map map = getGroupByNameParam(sourceMap);
		LinkedList list = (LinkedList) map.get(key);
		if (list != null) {
			size = list.size();
		}
		return size;
	}

	/**
	 * �������еĲ���ϲ�����
	 * 
	 * @param request
	 * @return
	 */
	public static String getAllParameters(HttpServletRequest request) {
		StringBuffer url = new StringBuffer("");
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = (String) parameterNames.nextElement();
			String[] values = request.getParameterValues(paramName);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					url.append(paramName + "=" + values[i].trim() + "&");
				}
			}
		}
		return url.substring(0, url.length() - 1).toString();
	}
}
