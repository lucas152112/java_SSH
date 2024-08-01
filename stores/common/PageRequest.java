package com.oim.stores.common;

import java.util.HashMap;
import java.util.Map;
/**
 * 分页请求工具类
 * @author Administrator
 *
 */
public class PageRequest {
	private Map<String, String> condition = new HashMap<String, String>();//条件
	private int pageSize = 15;//页大小
	private int pageNumber = 0;//页号

	public Map<String, String> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, String> condition) {
		this.condition = condition;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

}
