package com.oim.stores.common;

import java.util.List;
/**
 * 分页工具类
 * @author Administrator
 *
 */
public class PageResponse {
	private boolean success;//成功标志
	private String error;//错误信息
	private int totalRecord;//总记录
	private List<?> list;//数据
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
}
