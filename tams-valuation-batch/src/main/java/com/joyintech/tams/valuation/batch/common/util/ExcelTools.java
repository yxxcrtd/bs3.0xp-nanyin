/*
 * 文件名：ExcelTools.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

public class ExcelTools {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ExcelTools.class);
    
    public static void writeExcel(InputStream stream, String tempName, Map<String, Object> paraMap,
                                  String outputFilepath) {
        // 模板文件后缀名
        String fileType = GlobalConstant.POINT + FilenameUtils.getExtension(tempName);

        // 工作表对象
        HSSFWorkbook wb = null;
        try {
            // 工作表对象初始化
            if (StringUtil.equals(fileType, GlobalConstant.FILE_POSTFIX_XLS)) {
                wb = new HSSFWorkbook(stream);
            }
            else {
                throw new Exception("不是一个有效的Excel文件");
            }

            for (Sheet sheet : wb) {
                List<HSSFRow> listRows = new ArrayList<HSSFRow>();
                int rownum = sheet.getLastRowNum();
                for (int i = 0; i <= rownum; i++ ) {
                    HSSFRow row = (HSSFRow)sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    int cellnum = row.getLastCellNum();

                    for (int j = 0; j < cellnum; j++ ) {
                        CellType cellType = row.getCell(j).getCellTypeEnum();
                        if (cellType != CellType.STRING) {
                            continue;
                        }
                        String cellvalue = row.getCell(j).getStringCellValue();
                        if (StringUtil.isNotEmpty(cellvalue)) {
                            if (cellvalue.contains("${") && cellvalue.contains("}")) {
                                replaceData(row.getCell(j), paraMap);
                            }

                            if (cellvalue.contains("#{") && cellvalue.contains("}")) {
                                listRows.add(0, row);
                                break;
                            }
                        }
                    }
                }

                for (HSSFRow row : listRows) {
                    List list = null;
                    for (Cell cell : row) {
                        CellType cellType = cell.getCellTypeEnum();
                        if (cellType != CellType.STRING) {
                            continue;
                        }
                        String cellvalue = cell.getStringCellValue();
                        if (StringUtil.isNotEmpty(cellvalue)) {
                            if (cellvalue.contains("#{") && cellvalue.contains("}")) {
                                String key = cellvalue.substring(cellvalue.indexOf("#{") + 2,
                                    cellvalue.indexOf("."));
                                list = (List)paraMap.get(key);
                                break;
                            }
                        }
                    }

                    // 增加新行
                    if(list == null){
                        return;
                    }
                    insertRow(row, list.size());

                    for (Object obj : list) {
                        Map<String, Object> value = transBean2Map(obj);
                        // // 获取需要设值的行
                        Row newrow = sheet.getRow(row.getRowNum() + list.indexOf(obj) + 1);// sheet.createRow(row.getRowNum()
                                                                                           // +
                                                                                           // list.indexOf(obj)
                                                                                           // + 1);
                        if(newrow == null){
                            newrow = sheet.createRow(row.getRowNum() + list.indexOf(obj) + 1);
                        }
                        for (int i = 0; i < row.getLastCellNum(); i++ ) {
                            HSSFCell cell = (HSSFCell)row.getCell(i);
                            HSSFCell newcell = (HSSFCell)newrow.createCell(i);
                            copyCell((HSSFWorkbook)wb, cell, newcell, true);

                            CellType cellType = newcell.getCellTypeEnum();
                            if (cellType != CellType.STRING) {
                                continue;
                            }
                            String cellvalue = newcell.getStringCellValue();
                            if (StringUtil.isNotEmpty(cellvalue)) {
                                if (cellvalue.contains("#{") && cellvalue.contains("}")) {
                                    replaceListData(newcell, value);
                                }
                            }
                        }
                    }

                    removeRow(row);

                    // break;
                }
            }

            File file = new File(outputFilepath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            try {
                if(wb != null) {
                    wb.close(); 
                }
            }
            catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Remove a row by its index
     * 
     * @param sheet a Excel sheet
     * @param rowIndex a 0 based index of removing row
     */
    public static void removeRow(Row row) {
        Sheet sheet = row.getSheet();
        int rowIndex = row.getRowNum();
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum)
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);// 将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
        // if (rowIndex == lastRowNum) {
        // sheet.removeRow(row);
        // }
    }

    /**
     * Remove a row by its index
     * 
     * @param sheet a Excel sheet
     * @param rowIndex a 0 based index of removing row
     */
    public static void insertRow(Row row, int number) {
        Sheet sheet = row.getSheet();
        int rowIndex = row.getRowNum();
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, number);// 将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
        }
    }

    /**
     * 复制单元格
     * 
     * @param srcCell
     * @param distCell
     * @param copyValueFlag true则连同cell的内容一起复制
     */
    public static void copyCell(HSSFWorkbook wb, HSSFCell srcCell, HSSFCell distCell,
                                boolean copyValueFlag) {
        // 样式
        distCell.setCellStyle(srcCell.getCellStyle());

        // 评论
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        CellType srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (copyValueFlag) {
            if (CellType.NUMERIC.equals(srcCellType)) {
                if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                }
                else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            }
            else if (CellType.STRING.equals(srcCellType)) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            }
            else if (CellType.BLANK.equals(srcCellType)) {
                // nothing21
            }
            else if (CellType.BOOLEAN.equals(srcCellType)) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            }
            else if (CellType.ERROR.equals(srcCellType)) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            }
            else if (CellType.FORMULA.equals(srcCellType)) {
                distCell.setCellFormula(srcCell.getCellFormula());
            }
            else { // nothing29
            }
        }
    }

    private static void replaceData(Cell cell, Map<String, Object> datas) {
        CellType cellType = cell.getCellTypeEnum();
        if (cellType != CellType.STRING) {
            return;
        }
        String result = cell.getStringCellValue();

        if (!result.contains("${") || !result.contains("}")) {
            return;
        }
        String key = result.substring(result.indexOf("${") + 2, result.indexOf("}"));
        String replaceString = replaceString(key, datas);
        if (StringUtil.isNotEmpty(replaceString)) {
            result = result.replace("${" + key + "}", replaceString(key, datas));
        }
        else {
            result = "";
        }

        cell.setCellValue(result);

        if (result.contains("${") && result.contains("}")) {
            replaceData(cell, datas);
        }

    }

    private static void replaceListData(Cell cell, Map<String, Object> datas) {
        String result = cell.getStringCellValue();

        if (!result.contains("#{") || !result.contains("}")) {
            return;
        }
        String cellvalue = result.substring(result.indexOf("#{") + 2, result.indexOf("}"));
        String[] keys = cellvalue.split("\\.");

        Object value = datas.get(keys[1]);
        if (value == null) {
            cell.setCellValue("");
            return;
        }
        if (value instanceof Date) {
            cell.setCellValue((Date)value);
        }
        else if (value instanceof String) {
            cell.setCellValue((String)value);
        }
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean)value);
        }
        else if (value instanceof Calendar) {
            cell.setCellValue((Calendar)value);
        }
        else if (value instanceof RichTextString) {
            cell.setCellValue((RichTextString)value);
        }
        else if (value instanceof Double) {
            cell.setCellValue((Double)value);
        }
        else if (value instanceof BigDecimal) {
            BigDecimal cellValue = (BigDecimal)value;
//            DecimalFormat df = new DecimalFormat("#,##0.00#########");
            cell.setCellValue(cellValue.doubleValue());
        }
        else if (value instanceof Integer) {
            Integer cellValue = (Integer)value;
            cell.setCellValue(cellValue.doubleValue());
        }
    }

    private static String replaceString(String key, Map<String, Object> datas) {
        if(key.contains(".")) {
            String subKey = key.substring(0, key.indexOf("."));
            Object object = datas.get(subKey);
            if(object != null) {
                Map<String, Object> map = transBean2Map(object);
                return replaceString(key.substring(subKey.length()+1), map);
            }
        }
//        if(datas.get(key) != null && datas.get(key) instanceof BigDecimal) {
//            DecimalFormat df = new DecimalFormat("#,##0.00#########");
//            return df.format((BigDecimal)datas.get(key));
//        }
        return (String)datas.get(key);
    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return map;

    }
}

