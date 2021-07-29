package com.joyintech.tams.svreport.pbc.common.util;

import cn.hutool.core.date.DateTime;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCellNumber;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 导入模板中的行数处理成java实体工具类
 * @Author jinyangyang
 */
public class ExcelListToEntity {

    public static  <T> List<T> convertToEntity(List<List<String>> dataList, Class<T> clazz) throws Exception{
        List<T> tList = new ArrayList<>();
        if(StringUtil.isNotEmpty(dataList)){
            Field[] declaredFields = clazz.getDeclaredFields();
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            for (List<String> rowsList : dataList) {
                T newInstance = declaredConstructor.newInstance();
                for (Field declaredField : declaredFields) {
                    ExcelCellNumber annotation = AnnotationUtils.findAnnotation(declaredField, ExcelCellNumber.class);
                    if(annotation == null){
                        throw new RuntimeException("Annotation @ExcelCellNumber is must be not null");
                    }
                    int cellNumber = annotation.cellNumber();
                    if(cellNumber < 0){
                        throw new RuntimeException("Annotation @ExcelCellNumber number value is must big than 0");
                    }
                    String cellValue = rowsList.get(cellNumber);
                    Class<?> type = declaredField.getType();
                    String fieldName = declaredField.getName();
                    setEntityValue(fieldName,type,newInstance,cellValue,clazz);
                }
                tList.add(newInstance);
            }
        }
        return tList;
    }

    private static Object checkValueType(Field field,Object value){
        if(field != null && value != null && !"".equals(value.toString())){
            Class<?> reqType = field.getType();
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
        //获取请求实体中set方法
        String setMethod = buildSetMethod(feildName);
        Method method = tClass.getMethod(setMethod, fieldType);
        //执行请求实体中set方法设置请求参数值
        method.invoke(newInstance,value);
    }

    private static String buildSetMethod(String feild){
        String substring = feild.substring(0, 1);
        String upperCase = substring.toUpperCase();
        return "set".concat(upperCase).concat(feild.substring(1));
    }


}
