package com.joyintech.tams.svreport.pbc.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.enums.PbcReportEnums;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthReportUtil {

    public static <T> List<T> createDataFromExcel(HSSFSheet sheet, String reportType, Class<T> clazz) throws Exception {
        List<T> instanceList = new ArrayList<>();
       //1.获取报表统计的分组信息
        ReportGroup reportGroup = AnnotationUtils.findAnnotation(clazz, ReportGroup.class);
        if(reportGroup != null){
            String group = reportGroup.group();
            Field[] declaredFields = clazz.getDeclaredFields();
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            //get the enum for report ,it is very important,so  you must know what is  PbcReportEnums!
            //if you want change the way to analyse the report excel,giving up is the best choice you can make！
            List<PbcReportEnums> propertiesByType = PbcReportEnums.getPropertiesByType(reportType, group);
            if(CollectionUtil.isNotEmpty(propertiesByType)){
                for (PbcReportEnums pbcReportEnums : propertiesByType) {
                    //行号
                    int rowNumber = pbcReportEnums.getRowNumber();
                    T newInstance = declaredConstructor.newInstance();
                    for (Field declaredField : declaredFields) {
                        ExcelCell excelCell = AnnotationUtils.findAnnotation(declaredField, ExcelCell.class);
                        if(excelCell != null){
                            if(StrUtil.isNotEmpty(excelCell.fixedValue())){
                                setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,excelCell.fixedValue(),clazz);
                            }else if(excelCell.isPropertyCode()){
                                setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,pbcReportEnums.getPropertyCode(),clazz);
                            }else if(excelCell.isReportType()){
                                setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,reportType,clazz);
                            }else{
                                //列号
                                int columnNumber = excelCell.columnNumber();
                                if(columnNumber >= 0 && rowNumber >= 0){
                                    HSSFRow row = sheet.getRow(rowNumber);
                                    HSSFCell cell = row.getCell(columnNumber);
                                    cell.setCellType(CellType.STRING);
                                    String stringCellValue = cell.getStringCellValue();
                                    setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,stringCellValue,clazz);
                                }
                            }

                        }
                    }
                    instanceList.add(newInstance);
                }
            }
        }
        return instanceList;
    }

    private static Object checkValueType(Class<?> reqType, Object value){
        if(value != null && !"".equals(value.toString())){
            if(reqType.equals(String.class)){
                return value;
            }else if(reqType.equals(Long.class)){
                long parseLong = Long.parseLong(value.toString());
                return parseLong * 10000L;
            }else if(reqType.equals(long.class)){
                long parseLong = Long.parseLong(value.toString());
                return parseLong * 10000L;
            }else{
                if(reqType == BigDecimal.class){
                    BigDecimal multiply = new BigDecimal(value.toString()).multiply(new BigDecimal(10000));
                    return multiply;
                }
            }
        }
        return null;
    }

    private static void setEntityValue(String feildName,Class<?> fieldType,Object newInstance,String value,Class<?> tClass) throws Exception {
        if(StrUtil.isNotEmpty(value)){
            //获取请求实体中set方法
            String setMethod = buildSetMethod(feildName);
            Method method = tClass.getMethod(setMethod, fieldType);
            //执行请求实体中set方法设置请求参数值
            method.invoke(newInstance,checkValueType(fieldType,value));
        }
    }

    private static String buildSetMethod(String feild){
        String substring = feild.substring(0, 1);
        String upperCase = substring.toUpperCase();
        return "set".concat(upperCase).concat(feild.substring(1));
    }
}
