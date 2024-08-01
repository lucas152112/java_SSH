package com.oim.stores.common;

import java.util.List;
/**
 * 分页
 * @author Administrator
 *
 */
public class Pagination {
	private int totalRecord;//总记录数
	private int pageNumber;//页大小
	private List<?> list;//数据

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}
