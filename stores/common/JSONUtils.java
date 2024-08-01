package com.oim.stores.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSON 工具类
 * 
 * @author chenzhaowen
 */
@SuppressWarnings("unchecked")
public class JSONUtils {
	private static Map primitiveMap = new HashMap();
	static {
		primitiveMap.put(boolean.class, Boolean.class);
		primitiveMap.put(char.class, Character.class);
		primitiveMap.put(byte.class, Byte.class);
		primitiveMap.put(short.class, Short.class);
		primitiveMap.put(int.class, Integer.class);
		primitiveMap.put(long.class, Long.class);
		primitiveMap.put(float.class, Float.class);
		primitiveMap.put(double.class, Double.class);
	}

	public JSONUtils() {
	}
	/**
	 * 转换bean为json对象
	 * @param bean
	 * @return
	 */
	public static JSONObject toJSONObject(Object bean) {
		return toJSONObject(bean, null);
	}

	/**
	 * 将bean转化为JSONObject 对象 注意:仅处理 原始类型, String, Number, Boolean, Date 这几种类型
	 * 
	 * @param bean
	 *            Object 要转换的bean对象
	 * @return JSONObject 返回的JSONObject对象
	 */
	public static JSONObject toJSONObject(Object bean, JSONObject json) {
		if (bean == null)
			return null;
		if (json == null)
			json = new JSONObject();
		Class klass = bean.getClass();
		Method[] methods = klass.getMethods();
		Method method;
		String name, key;
		Object obj = null;
		for (int i = 0; i < methods.length; i += 1) {
			try {
				method = methods[i];
				name = method.getName();
				key = "";
				if (name.startsWith("get")) {
					key = name.substring(3);
				} else if (name.startsWith("is")) {
					key = name.substring(2);
				}
				if (key.length() > 0 && !key.equals("Class")
						&& Character.isUpperCase(key.charAt(0))
						&& method.getParameterTypes().length == 0) {
					key = key.substring(0, 1).toLowerCase() + key.substring(1);
					obj = method.invoke(bean, (Object[]) null);
					// 仅处理 String, Number, Boolean, Date 这几种类型
					if (obj == null) {
						json.put(key, null);
					} else if (Date.class.isAssignableFrom(obj.getClass())) {
						json.put(key, CommonUtils.formatDateTime((Date) obj));
					} else if (obj.getClass().isPrimitive()
							|| obj.getClass().equals(String.class)
							|| obj.getClass().equals(Boolean.class)
							|| Number.class.isAssignableFrom(obj.getClass())) {
						json.put(key, obj);
					}
				}
			} catch (Exception e) {
				/* forget about it */
			}
		}
		return json;
	}

	/**
	 * 用JSON中的对应值填充bean对象的属性 注意:仅处理 原始类型, String, Number, Boolean, Date 这几种类型
	 * 
	 * @param bean
	 *            Object 要填充的bean对象
	 * @param json
	 *            JSONObject 用于填充的json对象
	 * @return int 实际填充的属性计数
	 */
	public static int fillBean(Object bean, JSONObject json) {
		if (bean == null || json == null)
			return 0;
		int ret = 0;
		Class klass = bean.getClass();
		Method[] methods = klass.getMethods();
		Method method;
		String name, key;
		Object obj = null;
		Class[] types = null;
		for (int i = 0; i < methods.length; i += 1) {
			try {
				method = methods[i];
				name = method.getName();
				key = "";
				if (name.startsWith("set")) {
					key = name.substring(3);
					types = method.getParameterTypes();
					if (key.length() > 0 && !key.equals("Class")
							&& Character.isUpperCase(key.charAt(0))
							&& types.length == 1) {
						key = key.substring(0, 1).toLowerCase()
								+ key.substring(1);
						obj = null;
						Class clazz = types[0];
						// 仅处理 String, Number, Boolean, Date 这几种类型
						if (json.has(key)
								&& (clazz.isPrimitive()
										|| clazz.equals(String.class)
										|| clazz.equals(Boolean.class)
										|| Number.class.isAssignableFrom(clazz) || Date.class
										.isAssignableFrom(clazz))) {
							// 处理Date及其子类情况
							if (Date.class.isAssignableFrom(clazz)) {
								String tmp = json.optString(key, null);
								if (tmp != null) {
									if (clazz.equals(java.sql.Date.class))
										obj = java.sql.Date.valueOf(tmp);
									else if (clazz.equals(java.sql.Time.class))
										obj = java.sql.Time.valueOf(tmp);
									else if (clazz
											.equals(java.sql.Timestamp.class))
										obj = java.sql.Timestamp.valueOf(tmp);
									else {
										obj = CommonUtils.parseDateTime(json
												.optString(key));
										if (obj == null)
											obj = CommonUtils.parseDate(json
													.optString(key));
									}
								}
							} else {
								obj = json.opt(key);
								if ("null".equals(obj)) {
									obj = null;
								} else if (!clazz.equals(obj.getClass())) {
									String tmp = json.optString(key, "0");
									if (clazz.isPrimitive()) {
										clazz = (Class) primitiveMap.get(clazz);
									}
									obj = clazz.getConstructor(
											new Class[] { String.class })
											.newInstance(new Object[] { tmp });
								}
							}
							method.invoke(bean, new Object[] { obj });
							ret++;
						}
					}
				}
			} catch (Exception e) {
				/* forget about it */
			}
		}
		return ret;
	}

	/**
	 * 将json对象转化成bean
	 * @param klass
	 * @param json
	 * @return
	 */
	public static Object getBean(Class klass, JSONObject json) {
		try {
			Object obj = klass.newInstance();
			JSONUtils.fillBean(obj, json);
			return obj;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将json数组转化成java对象List
	 * @param klass
	 * @param jary
	 * @return
	 */
	public static List getBeanList(Class klass, JSONArray jary) {
		List list = new ArrayList();
		JSONObject json = null;
		Object obj = null;
		for (int i = 0; i < jary.size(); i++) {
			json = jary.optJSONObject(i);
			if (json != null) {
				obj = getBean(klass, json);
				if (obj != null)
					list.add(obj);
			}
		}
		return list;
	}

	/**
	 * 将java对象List转化成json对象List
	 * @param beanList
	 * @return
	 */
	public static List toJSONList(List beanList) {
		List list = new ArrayList();
		if (beanList != null) {
			for (int i = 0; i < beanList.size(); i++) {
				Object bean = beanList.get(i);
				list.add(toJSONObject(bean));
			}
		}
		return list;
	}

	/**
	 * 
	 * @param bean
	 * @param json
	 * @return
	 */
	public static int fillBeanRaw(Object bean, JSONObject json) {
		if (bean == null || json == null)
			return 0;
		int ret = 0;
		Class klass = bean.getClass();
		Method[] methods = klass.getMethods();
		Method method;
		String name, key;
		Object obj = null;
		Class[] types = null;
		for (int i = 0; i < methods.length; i += 1) {
			try {
				method = methods[i];
				name = method.getName();
				key = "";
				if (name.startsWith("set")) {
					key = name.substring(3);
					types = method.getParameterTypes();
					if (key.length() > 0 && !key.equals("Class")
							&& Character.isUpperCase(key.charAt(0))
							&& types.length == 1) {
						obj = null;
						Class clazz = types[0];
						// 仅处理 String, Number, Boolean, Date 这几种类型
						if (json.has(key)
								&& (clazz.isPrimitive()
										|| clazz.equals(String.class)
										|| clazz.equals(Boolean.class)
										|| Number.class.isAssignableFrom(clazz) || Date.class
										.isAssignableFrom(clazz))) {
							// 处理Date及其子类情况
							if (Date.class.isAssignableFrom(clazz)) {
								String tmp = json.optString(key, null);
								if (tmp != null) {
									if (clazz.equals(java.sql.Date.class))
										obj = java.sql.Date.valueOf(tmp);
									else if (clazz.equals(java.sql.Time.class))
										obj = java.sql.Time.valueOf(tmp);
									else if (clazz
											.equals(java.sql.Timestamp.class))
										obj = java.sql.Timestamp.valueOf(tmp);
									else {
										obj = CommonUtils.parseDateTime(json
												.optString(key));
										if (obj == null)
											obj = CommonUtils.parseDate(json
													.optString(key));
									}
								}
							} else {
								obj = json.opt(key);
								if (!clazz.equals(obj.getClass())) {
									String tmp = json.optString(key, "0");
									if (clazz.isPrimitive()) {
										clazz = (Class) primitiveMap.get(clazz);
									}
									obj = clazz.getConstructor(
											new Class[] { String.class })
											.newInstance(new Object[] { tmp });
								}
							}
							method.invoke(bean, new Object[] { obj });
							ret++;
						}
					}
				}
			} catch (Exception e) {
				/* forget about it */
			}
		}
		return ret;
	}
	/**
	 * get bean raw;
	 * @param klass
	 * @param json
	 * @return
	 */
	public static Object getBeanRaw(Class klass, JSONObject json) {
		try {
			Object obj = klass.newInstance();
			JSONUtils.fillBeanRaw(obj, json);
			return obj;
		} catch (Exception ex) {
			return null;
		}
	}
	/**
	 * get Bean list;
	 * @param klass
	 * @param jary
	 * @return
	 */
	public static List getBeanListRaw(Class klass, JSONArray jary) {
		List list = new ArrayList();
		JSONObject json = null;
		Object obj = null;
		for (int i = 0; i < jary.size(); i++) {
			json = jary.optJSONObject(i);
			if (json != null) {
				obj = getBeanRaw(klass, json);
				if (obj != null)
					list.add(obj);
			}
		}
		return list;
	}
	// public static void main(String[] args) throws Exception {
	// String str = "{\"originalBillId\":123,\"memo\":\"jsut
	// memo\",\"cardType\":null,\"cashier\":0,\"totalQuantity\":10,\"cardNo\":null,\"id\":0,\"totalDiscount\":0,\"totalAmount\":0,\"editor\":0,\"saleTerminal\":null,\"creator\":0,\"editDate\":null,\"storeId\":0,\"salesman\":0,\"createDate\":\"2008-09-11\"}";
	//
	// JSONObject json = new JSONObject(str);
	// System.out.println("json=" + json);
	//
	// SaleBill bill = new SaleBill();
	// int ret = bill.valueOf(json);
	// System.out.println("ret=" + ret);
	// json = bill.toJSONObject();
	// System.out.println("newj=" + json);
	// }
}
