package com.oim.stores.framework.utils;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

public class ORMapUtils {
    public static String getValue(Map map, String key) {
	String result = "";
	result = MapUtils.getString(map, key.toUpperCase(), "");
	return result;
    }
}
