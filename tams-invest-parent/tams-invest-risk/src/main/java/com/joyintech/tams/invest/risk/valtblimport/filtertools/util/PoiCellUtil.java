package com.joyintech.tams.invest.risk.valtblimport.filtertools.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;

public class PoiCellUtil {

	private PoiCellUtil() {}
	
	private static final SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 
	 * @param cell POI cell
	 * @return string value of cell
	 */
	public static String getCellValue(Cell cell) {
		if(cell == null) return null;
		
		String contents = null;
		boolean succ = false;
		if(!succ) {
			try {
				contents = cell.getStringCellValue();
				succ = true;
			} catch(Exception e) { }
		}
		if(!succ) {
			try {
				double v = cell.getNumericCellValue();
//				contents = new BigDecimal(v).toString();
				contents =  String.valueOf(v);
				if(contents != null && contents.endsWith(".0")) {//2202.0这种特殊处理
					contents = contents.substring(0,contents.length()-1);
				}
				//contents = new BigDecimal(v).setScale(6, RoundingMode.HALF_EVEN).toString();
				succ = true;
			} catch(Exception e) { }
		}
		if(!succ) {
			try {
				boolean v = cell.getBooleanCellValue();
				contents = new Boolean(v).toString();
				succ = true;
			} catch(Exception e) { }
		}
		if(!succ) {
			try {
				Date v = cell.getDateCellValue();
				contents = dateFmt.format(v);
				succ = true;
			} catch(Exception e) { }
		}
		if(!succ) {
			try {
				RichTextString v = cell.getRichStringCellValue();
				contents = v.getString();
				succ = true;
			} catch(Exception e) { }
		}
		return contents;
	}
	
	/**
	 * 
	 * @param cell
	 * @return column sequence, 'A' based, e.g.: A,B,...,Z,AA,AB,...,AZ,...
	 */
	public static String getColumnCode(Cell cell) {
		int colSeq = cell.getColumnIndex() + 1;
		return XlsUtil.convertColumnSeqToCode(colSeq);
	}
}
