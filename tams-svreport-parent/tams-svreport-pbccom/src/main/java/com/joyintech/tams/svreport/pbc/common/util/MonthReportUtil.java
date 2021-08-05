package com.joyintech.tams.svreport.pbc.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.enums.PbcReportEnums;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthReportUtil {

    public static <T> List<T> createDataFromExcel(Sheet sheet, String reportType,Class<T> clazz) throws Exception {
        List<T> instanceList = new ArrayList<>();
       //1.获取报表统计的分组信息
        ReportGroup reportGroup = AnnotationUtils.findAnnotation(clazz, ReportGroup.class);
        if(reportGroup != null){
            String group = reportGroup.group();
            Field[] declaredFields = clazz.getDeclaredFields();
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            List<PbcReportEnums> propertiesByType = PbcReportEnums.getPropertiesByType(reportType, group);
            if(CollectionUtil.isNotEmpty(propertiesByType)){
                for (PbcReportEnums pbcReportEnums : propertiesByType) {
                    //行号
                    int rowNumber = pbcReportEnums.getRowNumber();
                    String propertyCode = pbcReportEnums.getPropertyCode();
                    T newInstance = declaredConstructor.newInstance();
                    for (Field declaredField : declaredFields) {
                        ExcelCell excelCell = AnnotationUtils.findAnnotation(declaredField, ExcelCell.class);
                        if(excelCell != null){
                            if(excelCell.isPropertyCode()){
                                setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,propertyCode,clazz);
                            }else if(excelCell.isReportType()){
                                //TODO
                                setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,"propertyCode",clazz);
                            }else if(excelCell.isGroupType()){
                                //TODO
                                setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,"propertyCode",clazz);
                            }else{
                                //列号
                                int columnNumber = excelCell.columnNumber();
                                Row row = sheet.getRow(rowNumber);
                                Cell cell = row.getCell(columnNumber);
                                setEntityValue(declaredField.getName(),declaredField.getType(),newInstance,cell.getStringCellValue(),clazz);
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
            }else{
                if(reqType == Double.class){
                    return Double.parseDouble(value.toString());
                }
                if(reqType == double.class){
                    return Double.parseDouble(value.toString());
                }
                if(reqType == Integer.class){
                    return Integer.parseInt(value.toString());
                }
                if(reqType == int.class){
                    return Integer.parseInt(value.toString());
                }
                if(reqType == Long.class){
                    return Long.parseLong(value.toString());
                }
                if(reqType == long.class){
                    return Long.parseLong(value.toString());
                }
                if(reqType == Float.class){
                    return Float.parseFloat(value.toString());
                }
                if(reqType == float.class){
                    return Float.parseFloat(value.toString());
                }
                if(reqType == Date.class){
                    DateTime parse = cn.hutool.core.date.DateUtil.parse(value.toString(), DateUtil.DEFAULT_OTHER_FORMAT);
                    return parse.toJdkDate();
                }
                if(reqType == BigDecimal.class){
                    return new BigDecimal(value.toString());
                }
                if(reqType == Boolean.class){
                    return Boolean.parseBoolean(value.toString());
                }
                if(reqType == boolean.class){
                    return Boolean.parseBoolean(value.toString());
                }
                if(reqType == Short.class){
                    return Short.parseShort(value.toString());
                }
                if(reqType == short.class){
                    return Short.parseShort(value.toString());
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
