package com.jixu.common.util;

import java.util.List;

/** 
 * @ClassName 		Pages
 * @author  		Pan Wei 
 * @Date 			2017年5月8日 下午5:04:34 	
 * @Description		[]  
 * @return  
 */

public class Pages<T> {
	private int currentPage;
	private int totalPages=0;
	private int totalCount=0;
	
	/**
	 * 每页最多几条数据
	 */
	private int pageMaxRecords=50;
	
	/**
	 * 
	 */
	public Pages() {
		setCurrentPage(1);
	}
	
	/**
	 * 
	 */
	public Pages(int pageNum) {
		setCurrentPage(pageNum);
	}
	
	/**
	 * 
	 */
	public Pages(int pageNum, int limits) {
		setCurrentPage(pageNum);
		if(limits>0)
			setPageMaxRecords(limits);
		else
			setPageMaxRecords(pageMaxRecords);
		
	}
	
	
	private List<T> resultList;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageMaxRecords() {
		return pageMaxRecords;
	}
	public void setPageMaxRecords(int pageMaxRecords) {
		this.pageMaxRecords = pageMaxRecords;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
