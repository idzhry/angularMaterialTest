package com.woodpecker.webframework.util;

import java.io.Serializable;

/**
 * 分页查询时用此对象
 */
@SuppressWarnings("serial")
public class PageQuery implements Serializable{
	
	/**
	 * 系统默认最大记录数
	 */
	private static int SYSTEM_DEFAULT_MAX_PAGE_SIZE = 10000;
	
	/**
	 * 页面期望每次取回的记录数
	 */
	private int pageSize = SYSTEM_DEFAULT_MAX_PAGE_SIZE;
	
	/**
	 * 页面期望跳到的页码
	 */
	private int pageNum = 1;
	
	public PageQuery() {
		super();
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize >= SYSTEM_DEFAULT_MAX_PAGE_SIZE || pageSize <= 0 ? SYSTEM_DEFAULT_MAX_PAGE_SIZE : pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum <= 0 ? 1 : pageNum;
	}
	
}
