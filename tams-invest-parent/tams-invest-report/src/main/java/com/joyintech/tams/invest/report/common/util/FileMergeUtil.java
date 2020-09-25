/*
 * 文件名：ExcelCopyUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *  文件合并
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月11日
 * @see ExcelCopyUtil
 * @since 1.0
 */
public class FileMergeUtil {

    public void handle(File file,XSSFWorkbook newExcelCreat) throws Exception{
    	InputStream in = null;
    	XSSFWorkbook fromExcel= null;
        try {
			in = new FileInputStream(file);  
			fromExcel = new XSSFWorkbook(in);
			int length = fromExcel.getNumberOfSheets();
			if(length<=1){       //长度为1时
			    XSSFSheet oldSheet = fromExcel.getSheetAt(0);
			    XSSFSheet newSheet = newExcelCreat.createSheet(oldSheet.getSheetName());
			    copySheet(newExcelCreat, oldSheet, newSheet);
			}else{
			    for (int i = 0; i < length; i++) {// 遍历每个sheet
			        XSSFSheet oldSheet = fromExcel.getSheetAt(i);
			        XSSFSheet newSheet = newExcelCreat.createSheet(oldSheet.getSheetName());
			        copySheet(newExcelCreat, oldSheet, newSheet);
			    }
			}
		} finally {
        	try {
            	if(in !=null)
            		in.close();
            	if(fromExcel !=null)
            		fromExcel.close();
            } catch (Exception e) {
            	e.printStackTrace();
             }
        }
    }
    
    public static void copyCellStyle(XSSFCellStyle fromStyle, XSSFCellStyle toStyle) {
        toStyle.cloneStyleFrom(fromStyle);// 此一行代码搞定
    }
    
    /**
     * 合并单元格
     * @param fromSheet
     * @param toSheet
     */
    public static void mergeSheetAllRegion(XSSFSheet fromSheet, XSSFSheet toSheet) {
        int num = fromSheet.getNumMergedRegions();
        CellRangeAddress cellR = null;
        for (int i = 0; i < num; i++) {
            cellR = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cellR);
        }
    }
    
    /**
     * 复制单元格 
     * @param wb
     * @param fromCell
     * @param toCell
     */
    public static void copyCell(XSSFWorkbook wb, XSSFCell fromCell, XSSFCell toCell) {
        XSSFCellStyle newstyle = wb.createCellStyle();
        copyCellStyle(fromCell.getCellStyle(), newstyle);
      //  toCell.setEncoding(fromCell.getStringCelllValue());
        // 样式
        toCell.setCellStyle(newstyle);
        if (fromCell.getCellComment() != null) {
            toCell.setCellComment(fromCell.getCellComment());
        }
        // 不同数据类型处理
        CellType fromCellType = fromCell.getCellType();
        toCell.setCellType(fromCellType);
        if (CellType.NUMERIC.equals(fromCellType)) {
            if (XSSFDateUtil.isCellDateFormatted(fromCell)) {
                toCell.setCellValue(fromCell.getDateCellValue());
            } else {
                toCell.setCellValue(fromCell.getNumericCellValue());
            }
        } else if (CellType.STRING.equals(fromCellType)) {
            toCell.setCellValue(fromCell.getRichStringCellValue());
        } else if (CellType.BLANK.equals(fromCellType)) {
            // nothing21
        } else if (CellType.BOOLEAN.equals(fromCellType)) {
            toCell.setCellValue(fromCell.getBooleanCellValue());
        } else if (CellType.ERROR.equals(fromCellType)) {
            toCell.setCellErrorValue(fromCell.getErrorCellValue());
        } else if (CellType.FORMULA.equals(fromCellType)) {
            toCell.setCellFormula(fromCell.getCellFormula());
        } else { // nothing29
        }
 
    }
    
    /**
     * 行复制功能
     * @param wb
     * @param oldRow
     * @param toRow
     */
    public static void copyRow(XSSFWorkbook wb, XSSFRow oldRow, XSSFRow toRow) {
        toRow.setHeight(oldRow.getHeight());
        for (Iterator cellIt = oldRow.cellIterator(); cellIt.hasNext();) {
            XSSFCell tmpCell = (XSSFCell) cellIt.next();
            XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb, tmpCell, newCell);
        }
    }
 
    /**
     * Sheet复制 
     * @param wb
     * @param fromSheet
     * @param toSheet
     */
    public static void copySheet(XSSFWorkbook wb, XSSFSheet fromSheet, XSSFSheet toSheet) {
        mergeSheetAllRegion(fromSheet, toSheet);
        // 设置列宽
        int length = fromSheet.getRow(fromSheet.getFirstRowNum()).getLastCellNum();
        for (int i = 0; i <= length; i++) {
            toSheet.setColumnWidth(i, fromSheet.getColumnWidth(i));
        }
        for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
            XSSFRow oldRow = (XSSFRow) rowIt.next();
            XSSFRow newRow = toSheet.createRow(oldRow.getRowNum());
            copyRow(wb, oldRow, newRow);
        }
    }
    
    public class XSSFDateUtil extends org.apache.poi.ss.usermodel.DateUtil {
        
    }
}
