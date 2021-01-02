/**  
 * @Project     tams-app  
 * @File        ExcelUtil.java  
 * @Package     com.joyintech.tams.invest.report.common.util  
 * @Version     V1.0  
 * @Date        2019年8月8日 下午4:53:58  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.tams.invest.report.common.util;

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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.dto.ChbMonthReportViewModelDto;
import com.joyintech.tools.util.ExcelCellValueFunction;

import static org.apache.poi.ss.usermodel.CellType.*;

/**  
 * @Description 一级商月报表导出专用  
 * @ClassName   ExcelUtil  
 * @Date        2019年8月8日 下午4:53:58  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */

public class ExcelUtil {
    public static void writeExcel(InputStream stream, String tempName, Map<String, Object> paraMap,
            String outputFilepath) {
    	writeExcel(stream, tempName, paraMap, outputFilepath, null);
    }

    @SuppressWarnings("deprecation")
	public static void writeExcel(InputStream stream, String tempName, Map<String, Object> paraMap,
                                  String outputFilepath, ExcelCellValueFunction excuter) {
        // 模板文件后缀名
        String fileType = FilenameUtils.getExtension(tempName).toLowerCase();

        // 工作表对象
        HSSFWorkbook wb = null;
        try {
            // 工作表对象初始化
            if (StringUtil.equals(fileType, "xls")) {
                wb = new HSSFWorkbook(stream);
            }
            else {
                throw new Exception("不是一个有效的Excel文件");
            }

            for (Sheet sheet : wb) {
                List<HSSFRow> listRows = new ArrayList<HSSFRow>();
                // 记录需要进行保留两位小数格式化的单元格
                List<String> numList = new ArrayList<String>();
                int rownum = sheet.getLastRowNum();
                for (int i = 0; i <= rownum; i++ ) {
                    HSSFRow row = (HSSFRow)sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    int cellnum = row.getLastCellNum();

                    for (int j = 0; j < cellnum; j++ ) {
                    	HSSFCell hss = row.getCell(j);
                        CellType cellType = null;
                        if (null != hss) {
                        	cellType = hss.getCellTypeEnum();
                        }
                        if (cellType != STRING) {
                            continue;
                        }
                        String cellvalue = row.getCell(j).getStringCellValue();
                        if (StringUtil.isNotEmpty(cellvalue)) {
                        	if (cellvalue.contains("$[") && cellvalue.contains("]")) {
                        		row.getCell(j).setCellType(BLANK);
                        		cellvalue = cellvalue.replace("$[", "");
                        		cellvalue = cellvalue.replace("]", "");
                        		row.getCell(j).setCellFormula(cellvalue);
                        	}
                        	if (cellvalue.startsWith("${") && cellvalue.endsWith("}") && (cellvalue.length() - cellvalue.replaceAll("}", "").length()) == 1) {
                        		numList.add(i + "," + j);
                        	}
                        	
                            if (cellvalue.contains("${") && cellvalue.contains("}")) {
                                replaceData(row.getCell(j), paraMap, excuter, row, sheet, wb);
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
                        if (cellType != STRING) {
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
                            if (cellType != STRING) {
                                continue;
                            }
                            String cellvalue = newcell.getStringCellValue();
                            if (StringUtil.isNotEmpty(cellvalue)) {
                                if (cellvalue.contains("#{") && cellvalue.contains("}")) {
//                                	newcell.setCellType(HSSFCell.CELL_TYPE_BLANK);
                                    replaceListData(newcell, value, excuter, row, sheet, wb);
                                }
                            }
                        }
                    }

                    removeRow(row);

                    // break;
                }
                sheet.setForceFormulaRecalculation(true);
//                for (String str : numList) {
//                	int rowIndex = Integer.parseInt(str.split(",")[0]);
//                	int cellIndex = Integer.parseInt(str.split(",")[1]);
//                	HSSFRow row = (HSSFRow)sheet.getRow(rowIndex);
//                	HSSFCell hss = row.getCell(cellIndex);
//                	hss.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//                }
            }

            File file = new File(outputFilepath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            wb.write(outputStream);
            outputStream.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                wb.close();
            }
            catch (IOException e) {
                e.printStackTrace();
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
    public static void copyCell(Workbook wb, Cell srcCell, Cell distCell,
                                boolean copyValueFlag) {
//        // 样式
//        distCell.setCellStyle(srcCell.getCellStyle());

        // 评论
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        CellType srcCellType = srcCell.getCellTypeEnum();
        distCell.setCellType(srcCellType);
        if (copyValueFlag) {
            if (srcCellType == NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                }
                else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            }
            else if (srcCellType == STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            }
            else if (srcCellType == BLANK) {
                // nothing21
            }
            else if (srcCellType == BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            }
            else if (srcCellType == ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            }
            else if (srcCellType == FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            }
            else { // nothing29
            }
        }
     // 样式
        distCell.setCellStyle(srcCell.getCellStyle());
    }

    private static void replaceData(Cell cell, Map<String, Object> datas, ExcelCellValueFunction excuter, Row row, Sheet sheet, Workbook workbook) {
        CellType cellType = cell.getCellTypeEnum();
        if (cellType != STRING) {
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
        boolean flag = true;
        Double resultValue = 0.00;
        try {
//        		if (result.contains(".")) {
        			resultValue = Double.valueOf(result); 
//        		} else {
//        			flag = false;
//        		}
            } catch ( NumberFormatException e) {
        	flag = false;
            }
        if (flag) {
        	cell.setCellValue(resultValue);
        } else {
        	cell.setCellValue(result);
        }
        
        if(excuter != null) {
        	excuter.execute(key, result, cell, row, sheet, workbook);
        }

        if (result.contains("${") && result.contains("}")) {
            replaceData(cell, datas, excuter, row, sheet, workbook);
        }

    }

    private static void replaceListData(Cell cell, Map<String, Object> datas, ExcelCellValueFunction excuter, Row row, Sheet sheet, Workbook workbook) {
        String result = cell.getStringCellValue();

        if (!result.contains("#{") || !result.contains("}")) {
            return;
        }
        String cellvalue = result.substring(result.indexOf("#{") + 2, result.indexOf("}"));
        String[] keys = cellvalue.split("\\.");

        Object value = datas.get(keys[1]);
        CellStyle cellStyle = workbook.createCellStyle();
        if (value == null) {
            cell.setCellValue("");
            return;
        }
        cell.setCellType(NUMERIC);
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(cellStyle);
        
//        if (value instanceof Date) {
//            cell.setCellValue((Date)value);
//        }
//        else if (value instanceof String) {
//            cell.setCellValue((String)value);
//        }
//        else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean)value);
//        }
//        else if (value instanceof Calendar) {
//            cell.setCellValue((Calendar)value);
//        }
//        else if (value instanceof RichTextString) {
//            cell.setCellValue((RichTextString)value);
//        }
//        else if (value instanceof Double) {
//            cell.setCellValue((Double)value);
//        }
//        else if (value instanceof BigDecimal) {
            BigDecimal cellValue = (BigDecimal)value;
            //DecimalFormat df = new DecimalFormat("#,##0.00#########");
            cell.setCellValue(cellValue.doubleValue());
//        }
//        else if (value instanceof Integer) {
//            Integer cellValue = (Integer)value;
//            cell.setCellValue(cellValue.doubleValue());
//        }

        if(null != excuter) {
        	excuter.execute(cellvalue, value, cell, row, sheet, workbook);
        }
    }

    private static String replaceString(String key, Map<String, Object> datas) {
        if(key.contains(".")) {
            String subKey = key.substring(0, key.indexOf("."));
            Object object = datas.get(subKey);
            if(object != null) {
                Map<String, Object> map = transBean2MapEx(object);
                return replaceString(key.substring(subKey.length()+1), map);
            }
        }
        if(datas.get(key) != null && datas.get(key) instanceof BigDecimal) {
            return ((BigDecimal)datas.get(key)).toPlainString();
        }
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
            e.printStackTrace();
        }
        return map;
    }
    
    
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2MapEx(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
//            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
//            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//            for (PropertyDescriptor property : propertyDescriptors) {
//                String key = property.getName();
//                // 过滤class属性
//                if (!key.equals("class")) {
//                    // 得到property对应的getter方法
//                    Method getter = property.getReadMethod();
//                    Object value = getter.invoke(obj);
//
//                    map.put(key, value);
//                }
//
//            }
        	@SuppressWarnings("unchecked")
			List<ChbMonthReportViewModelDto> list = (List<ChbMonthReportViewModelDto>)obj;
        	for (ChbMonthReportViewModelDto chbMonthReportViewModelDto : list) {
        		map.put(chbMonthReportViewModelDto.getVcode(), chbMonthReportViewModelDto.getValue());
        	}
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }
    
    
    /**
     * 获取模板文件路径
     * @param templateFile 模板文件名
     * @return 模板文件
     */
    public static InputStream getTplFile(String templateFile) {
    	ClassPathResource resource = new ClassPathResource("tpl" + templateFile);
    	InputStream inputStream = null;
    	try {
			inputStream = resource.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return inputStream;
    }
}
