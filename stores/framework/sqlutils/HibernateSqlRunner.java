package com.oim.stores.framework.sqlutils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.oim.stores.framework.sqlutils.HibernateSqlParameter;
import com.oim.stores.framework.sqlutils.HibernateSqlParameters;
import com.oim.stores.common.Pagination;

public class HibernateSqlRunner {
    private String sql;
    private Session session;
    private HibernateSqlParameters parameters;
    private StringBuffer sb;
    private final static String prefix = "#{";
    private final static String suffix = "}";

    public HibernateSqlRunner() {

    }

    /**
     * HibernateSqlRunner构造函数
     * @param session
     * @param sql
     */
    public HibernateSqlRunner(Session session, String sql) {
	this.sql = sql;
	this.session = session;
	String regExpress = "[" + prefix + "][\\s\\S]+?[" + suffix + "]";
	Pattern pattern = Pattern.compile(regExpress);
	Matcher matcher = pattern.matcher(sql);
	sb = new StringBuffer();
	parameters = new HibernateSqlParameters();
	int nStart = 0;
	while (matcher.find()) {
	    String tag = matcher.group().toString();
	    int start = matcher.start();
	    int end = matcher.end();
	    sb.append(sql.substring(nStart, start));
	    nStart = end;
	    HibernateSqlParameter parameter = new HibernateSqlParameter(tag);
	    parameters.put(parameter.getName(), parameter);
	    sb.append("?");
	}
	sb.append(sql.substring(nStart));
    }

    /**
     * 设置参数值
     * @param parameterName
     * @param value
     * Author zhuangjf
     * Create On 2013-3-14 上午10:48:46
     */
    public void setParameterValue(String parameterName, String value) {
	if (this.parameters.containsKey(parameterName))
	    this.parameters.setValue(parameterName, value);
    }

    /**
     * 批量设置参数值
     * @param parameters
     * Author zhuangjf
     * Create On 2013-3-14 上午10:48:57
     */
    public void setParameterValues(Map<String, String> parameters) {
	Iterator<String> it = this.parameters.keySet().iterator();
	while (it.hasNext()) {
	    String key = it.next();
	    String value = parameters.get(key);
	    setParameterValue(key, value);
	}
    }

    private Map<String, SQLQuery> initQuery() {
	System.out.println("sql=" + sb.toString());
	SQLQuery query = session.createSQLQuery(sb.toString());
	SQLQuery countquery = session.createSQLQuery("select count(1) from ("
		+ sb + ")");
	Map<String, SQLQuery> queryMap = new HashMap<String, SQLQuery>();
	Iterator<String> it = this.parameters.keySet().iterator();
	int index = 0;
	while (it.hasNext()) {
	    String key = it.next();
	    HibernateSqlParameter parameter = this.parameters.get(key);
	    query.setString(index, String.valueOf(parameter.getValue()));
	    countquery.setString(index, String.valueOf(parameter.getValue()));
	    // if (parameter.getJavaType().equals("String")) {
	    // query.setString(index, String.valueOf(parameter.getValue()));
	    // } else if (parameter.getJavaType().equals("Long")) {
	    // query.setLong(index, Long.parseLong(parameter.getValue()));
	    // } else if (parameter.getJavaType().equals("Integer")) {
	    // query.setInteger(index, Integer.parseInt(parameter.getValue()));
	    // }
	    index++;
	}
	queryMap.put("1", query);
	queryMap.put("2", countquery);
	return queryMap;
    }

    public int execute() {
	Map<String, SQLQuery> queryMap = initQuery();
	SQLQuery query = queryMap.get("1");
	return query.executeUpdate();
    }

    public List list() {
	Map<String, SQLQuery> queryMap = initQuery();
	SQLQuery query = queryMap.get("1");
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	return query.list();
    }

    public List list(int count) {
	Map<String, SQLQuery> queryMap = initQuery();
	SQLQuery query = queryMap.get("1");
	query.setFirstResult(0);
	query.setMaxResults(count);
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	return query.list();
    }
    
    public Pagination pagination(int pageNumber, int pageSize) {
	Map<String, SQLQuery> queryMap = initQuery();
	SQLQuery query = queryMap.get("1");
	SQLQuery countquery = queryMap.get("2");
	// String counthql = "select count(1) from (" + sb + ")";
	int totalRecord = ((Number) countquery.uniqueResult()).intValue();
	query.setFirstResult(pageNumber * pageSize);
	query.setMaxResults(pageSize);
	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	Pagination page = new Pagination();
	page.setPageNumber(pageNumber);
	page.setTotalRecord(totalRecord);
	page.setList(query.list());
	return page;
    }
}
