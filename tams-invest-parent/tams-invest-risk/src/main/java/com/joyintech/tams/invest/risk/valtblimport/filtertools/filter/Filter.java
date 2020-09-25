package com.joyintech.tams.invest.risk.valtblimport.filtertools.filter;

import org.apache.poi.ss.usermodel.Workbook;


public interface Filter {

	/**
	 * 
	 * @param startRow start row sequence for filter, 1-based
	 */
	void setStartRow(int startRow);
	
	/**
	 * 
	 * @param workbook POI workbook
	 * @param sheetIndex sheet index of workbook, 0-based
	 * @return
	 * @throws Exception
	 */
	Workbook doFilter(Workbook workbook, int sheetIndex) throws Exception;
	
	/**
	 * 
	 * @param workbook POI workbook
	 * @param sheetIndex sheet index of workbook, 0-based
	 * @return
	 * @throws Exception
	 */
	Workbook doFilter(Workbook workbook, int sheetIndex,String param) throws Exception;
}
