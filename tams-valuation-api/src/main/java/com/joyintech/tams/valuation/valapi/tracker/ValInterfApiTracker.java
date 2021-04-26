package com.joyintech.tams.valuation.valapi.tracker;

import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;
import com.joyintech.tams.valuation.valapi.constant.DictCodeValApiList;
import com.joyintech.tams.valuation.valapi.exception.ValInterfApiException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName ValInterfApiTracker
 * @Description: 解析估值api中字段的注解
 * @Author yanya
 * @Date 2021/2/3
 * @Version V1.0
 **/
public class ValInterfApiTracker {

    /**
     * 分析估值api参数
     * @param index 第几个参数
     * @param obj 参数对象
     * @param emptyFlag 是否可以为空，true表示可以，false表示不可以
     */
    public static void trackValApi(int index, Object obj, Boolean emptyFlag) throws Exception {
        if(obj != null) {
            //如果对象是list
            if(obj instanceof List) {
                List<Object> list = (List<Object>) obj;
                if(list.size() <= 0 && !emptyFlag) {
                    throw new ValInterfApiException("第"+index+"个参数长度为0！");
                }
                for(Object singleObj : list) {
                    trackValApi(index, singleObj, emptyFlag);//递归循环判定
                }
            }
            //如果对象为数组
            else if(obj.getClass().isArray()) {
                Object[] arr = (Object[]) obj;
                if(arr.length <= 0 && !emptyFlag) {
                    throw new ValInterfApiException("第"+index+"个参数长度为0！");
                }
                for(Object singleObj : arr) {
                    trackValApi(index, singleObj, emptyFlag);//递归循环判定
                }
            }
            //默认为 单个实体类
            else {
                judgeSingleObj(index, obj, emptyFlag);
            }
        }
        //参数为空
        else {
            if(!emptyFlag) {
                throw new ValInterfApiException("第"+index+"个参数不能为空！");
            }
        }
    }

    /**
     * 判定单个对象 是否符合参数条件
     * @param index 第几个参数
     * @param singleObj 参数对象
     * @param emptyFlag 是否可以为空，true表示可以，false表示不可以
     * @throws Exception
     */
    private static void judgeSingleObj(int index, Object singleObj, Boolean emptyFlag) throws Exception {
        if(singleObj != null) {
            Class<?> cl = singleObj.getClass();
            for (Field f : cl.getDeclaredFields()) {
                ValInterfApiField apiField = f.getAnnotation(ValInterfApiField.class);//估值api字段注解
                if (null != apiField) {
                    //判定类型
                    String type = apiField.type();
                    //指定字段
                    String appointField = apiField.appointField();
                    //指定字段值
                    String appointVal = apiField.appointVal();
                    //分割符
                    String splitStr = apiField.splitStr();
                    //字典key
                    String dictCode = apiField.dictCode();
                    //字段范围值内
                    String fieldVal = apiField.fieldVal();
                    Object value = getGetMethod(singleObj, f.getName());
                    if (StringUtil.equals(DictCodeValApiList.API字段注解类型_条件.getValue(), type)) {
                        if (StringUtil.isNotEmpty(appointField)) {
                            Object appointFieldObj = getGetMethod(singleObj, appointField);
                            if (appointFieldObj != null) {
                                if (StringUtil.isNotEmpty(appointVal)) {
                                    if (judgeRangeVal(appointFieldObj, appointVal, splitStr)) {
                                        judgeIsNull(cl.getName(), f.getName(), value);//判定是否为空
                                        judgeDict(cl.getName(), f.getName(), value, dictCode, splitStr);//判定数据字典
                                        judgeFieldVal(cl.getName(), f.getName(), value, fieldVal, splitStr);//判定指定值
                                    }
                                } else {
                                    judgeIsNull(cl.getName(), f.getName(), value);//判定是否为空
                                    judgeDict(cl.getName(), f.getName(), value, dictCode, splitStr);//判定数据字典
                                    judgeFieldVal(cl.getName(), f.getName(), value, fieldVal, splitStr);//判定指定值
                                }
                            }
                        }
                    } else {
                        judgeIsNull(cl.getName(), f.getName(), value);//判定是否为空
                        judgeDict(cl.getName(), f.getName(), value, dictCode, splitStr);//判定数据字典
                        judgeFieldVal(cl.getName(), f.getName(), value, fieldVal, splitStr);//判定指定值
                    }
                }
            }
        }
        else {
            if(!emptyFlag) {
                throw new ValInterfApiException("第"+index+"参数对象中有为空的数据！");
            }
        }
    }
    /**
     * 判定是否为空
     * @param clName 对象class名称
     * @param fieldName 字段名称
     * @param value 字段值
     * @throws Exception
     */
    private static void judgeIsNull(String clName, String fieldName, Object value) throws Exception {
        if(value == null) {
            throw new ValInterfApiException("参数【"+clName+ "】中，字段【"+fieldName+"】，不能为空");
        }
    }
    /**
     * 根据属性，获取get方法
     * @param obj   对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    private static Object getGetMethod(Object obj, String name) throws Exception {
        PropertyDescriptor pd = new PropertyDescriptor(name, obj.getClass());
        if(pd != null) {
            //获得get方法
            Method getMethod = pd.getReadMethod();
            //执行get方法返回一个Object
            return getMethod.invoke(obj);
        }
        return null;
    }

    /**
     * 判断值是否在指定数据字典内
     * @param clName  对象class名称
     * @param fieldName 字段名称
     * @param value 字段值
     * @param dictCode 字典
     * @param splitStr 分隔符
     * @throws Exception
     */
    private static void judgeDict(String clName, String fieldName, Object value, String dictCode, String splitStr) throws Exception {
        if(StringUtil.isNotEmpty(dictCode)) {
            String[] dictArr = StringUtil.split(dictCode, splitStr);
            boolean flag = false;
            for (String dict : dictArr) {
                //根据字典获取字典值
                if(StringUtil.isNotEmpty(getNameFromDataDict(dict, value.toString()))) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                throw new ValInterfApiException("参数【"+clName+"】中，字段【"+fieldName+"】，必须在数据字典值范围内【"+dictCode+"】");
            }
        }
    }

    /**
     * 判定值是否在指定范围内
     * @param value 字段值
     * @param rangeVal 范围值
     * @param splitStr 分隔符
     * @return
     */
    private static boolean judgeRangeVal(Object value, String rangeVal, String splitStr) {
        String[] arr = StringUtil.split(rangeVal, splitStr);
        for (String str : arr) {
            if (StringUtil.equals(value.toString(), str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判定值是否在指定值内
     * @param clName 对象class名称
     * @param fieldName 字段名称
     * @param value 字段值
     * @param appointVal 指定字段值
     * @param splitStr 分隔符
     * @throws Exception
     */
    private static void judgeFieldVal(String clName, String fieldName, Object value, String fieldVal, String splitStr) throws Exception {
        if(StringUtil.isNotEmpty(fieldVal)) {
            if(!judgeRangeVal(value, fieldVal, splitStr)) {
                throw new ValInterfApiException("参数【"+clName+"】中，字段【"+fieldName+"】，必须在指定字段值内【"+fieldVal+"】");
            }
        }
    }

    /**
     * 校验指定数据字典是否包含字典的code
     * @param dict 字典项
     * @param code 字典code
     * @return
     */
    private static String getNameFromDataDict(String dict, String code) {
        DataDict dataDict = CacheUtil.getDictCode(dict);
        if(dataDict == null) {
            return null;
        }
        List<? extends DataDictItem> itemList = dataDict.getItems();
        return itemList.stream().filter(item -> {
            if (StringUtil.equals(item.getItemCode(), code)) {
                return true;
            }
            return false;
        }).map(DataDictItem::getItemValue).findFirst().orElse(null);
    }

}
