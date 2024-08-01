package com.oim.stores.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

/**
 * json输出工具类
 * 
 * @author chenzhaowen
 */
public class JsonUtil {

	/**
	 * 获得输出的消息
	 * 
	 * @param ret
	 *            是否成功
	 * @param errinfo
	 *            错误信息
	 * @return 输出的消息
	 */
	public static String ctrRetMsg(boolean ret, String errinfo) {
		String msg = "";
		msg = "{success: " + ret + ",errors: " + "{msg:'" + errinfo + "'}}";
		return msg;
	}

	/**
	 * 输出字符串
	 * 
	 * @param response
	 * @param outinfo
	 * @param bret
	 *            是否成功
	 */
	public static void outInfo(HttpServletResponse response, String outinfo, boolean bret) {
		try {
			if (outinfo.indexOf(":") > -1) {
				outinfo = outinfo.replace(":", "");
			}
			if (outinfo.indexOf("\n") > -1) {
				outinfo = outinfo.replaceAll("\n", "");
			}
			String retInfo = JsonUtil.ctrRetMsg(bret, outinfo);
			System.out.println(retInfo);
			response.getWriter().write(retInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出json格式的字符串
	 * 
	 * @param response
	 * @param jsonout
	 *            json对象
	 */
	public static void outJson(HttpServletResponse response, JSONObject jsonout) {
		try {
			if (jsonout != null) {
				response.getWriter().write(jsonout.toString());
			} else {
				response.getWriter().write("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输入json
	 * @param list
	 * @param map
	 */
	public static void writeJson(List<?> list, Map<String, Object> map) {
		JSONArray jsonusers = new JSONArray();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				try {
					JSONObject jb = JSONUtils.toJSONObject(list.get(i));
					jsonusers.add(jb);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			JSONObject jsonout = new JSONObject();
			try {
				if (map != null) {
					Set<String> set = map.keySet();
					for (String key : set) {
						jsonout.put(key, map.get(key));
					}
				}
				jsonout.put("rows", jsonusers);
				try {
					PubFun.getResponseWriter().write(jsonout.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
