package com.oim.stores.framework.utils;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 对象装换类
 * @author zhuangjf
 *
 */
public class BeanConvert {
    /**
     * 将javaBean转换成Map
     * 
     * @param javaBean
     *            javaBean
     * @return Map对象
     */
    public static Map<String, String> toMap(Object javaBean) {
	Map<String, String> result = new HashMap<String, String>();
	Method[] methods = javaBean.getClass().getDeclaredMethods();

	for (Method method : methods) {
	    try {
		if (method.getName().startsWith("get")) {
		    String field = method.getName();
		    field = field.substring(field.indexOf("get") + 3);
		    field = field.toLowerCase().charAt(0) + field.substring(1);

		    Object value = method.invoke(javaBean, (Object[]) null);
		    result.put(field, null == value ? "" : value.toString());
		}
	    } catch (Exception e) {
	    }
	}

	return result;
    }

    public static <T> T toBean(Class<T> type, Map<String, String> data) throws Exception {
	T bean = null;
	try {
	    bean = type.newInstance();
	} catch (Exception e) {
	    return null;
	}
	Method[] methods = type.getDeclaredMethods();
	for (Method method : methods) {
	    try {
		if (method.getName().startsWith("set")) {
		    if (method.getGenericParameterTypes().length == 1) {
			Class clazz = method.getParameterTypes()[0];
			String field = method.getName();
			field = field.substring(field.indexOf("set") + 3);
			field = field.toLowerCase().charAt(0)
				+ field.substring(1);
			String dataValue = data.get(field) == null ? "" : data
				.get(field).toString();
			// Number.class.isAssignableFrom(clazz)
			if (!dataValue.equals("")) {
			    Object object = clazz.getConstructor(
				    new Class[] { String.class }).newInstance(
				    new Object[] { dataValue });
			    method.invoke(bean, new Object[] { object });
			}
		    }
		}
	    } catch (Exception e) {
		throw e;
	    }
	}
	return bean;
    }

    public static JSONObject toJSONObject(Map<String, Object> map) {
	Iterator<String> it = map.keySet().iterator();
	JSONObject json = new JSONObject();
	while (it.hasNext()) {
	    String key = it.next();
	    // key=StringUtils.replace(key, "_", "").toLowerCase();
	    if (map.get(key) instanceof Timestamp) {
		json.accumulate(key, DateUtil.timestamp2String((Timestamp)map.get(key),DateUtil.PATTERN_STANDARD));
	    } else {
		json.accumulate(key, MapUtils.getString(map, key, ""));
	    }
	}
	return json;
    }

    public static JSONArray toJSONArray(List<Map<String, Object>> list) {
	JSONArray jsonArray = new JSONArray();
	for (int i = 0; i < list.size(); i++) {
	    Map<String, Object> row = list.get(i);
	    jsonArray.add(toJSONObject(row));
	}
	return jsonArray;
    }
}
