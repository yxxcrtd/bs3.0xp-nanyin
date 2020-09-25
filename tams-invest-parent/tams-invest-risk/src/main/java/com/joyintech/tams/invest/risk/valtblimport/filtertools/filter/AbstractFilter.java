package com.joyintech.tams.invest.risk.valtblimport.filtertools.filter;

import org.apache.poi.ss.usermodel.Workbook;


public abstract class AbstractFilter implements Filter {

	protected int startRow = 1;
	
	@Override
	public void setStartRow(int startRow) {
		this.startRow = startRow < 1 ? 1 : startRow;
	}

	@Override
	public Workbook doFilter(Workbook workbook, int sheetIndex) throws Exception {
		return workbook;
	}

	@Override
	public Workbook doFilter(Workbook workbook, int sheetIndex,String param) throws Exception {
		return workbook;
	}

}
