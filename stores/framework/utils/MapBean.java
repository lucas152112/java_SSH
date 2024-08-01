package com.oim.stores.framework.utils;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * hashmap的扩展子类
 * @author iampc
 *
 */
public class MapBean extends HashMap{
    public MapBean() {
    }
 
    public MapBean(Object... args) {
        put(args);
    }
 
    public int getInt(Object key) {
        return getInt(key, 0);
    }
 
    public int getInt(Object key, int defaultInt) {
        Integer i = (Integer) get(key);
        return i == null ? defaultInt : i;
    }
 
    public String getString(Object key) {
        return (String) get(key);
    }
 
    public String getString(Object key, String defaultValue) {
        String value = (String) get(key);
        return value == null ? defaultValue : value;
    }
 
    public Timestamp getTimestamp(Object key) {
        return (Timestamp) get(key);
    }
 
    public void put(Object... args) {
        for (int i = 1; i < args.length; i += 2) {
            put(String.valueOf(args[i - 1]), args[i]);
        }
    }
}
