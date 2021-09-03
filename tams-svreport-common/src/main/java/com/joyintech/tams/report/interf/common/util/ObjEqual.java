package com.joyintech.tams.report.interf.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjEqual {


    /**
     * 比较两个BEAN对象的值是否相等
     * 如果是BEAN对象比较时MAP中的key值应与BEAN的属性值名称相同且字段数目要一致
     * @param source
     * @param target
     * @return
     */
    public static Map<String,Integer> domainEquals(Object source, Object target) {
        if (source == null || target == null) {
            return null;
        }
        Map<String,Integer> map=new HashMap<>();
        map = classOfSrc(source, target, map);
        return map;
    }


    /**
     * @param source
     * @param target
     * @return
     */
    private static Map<String,Integer> classOfSrc(Object source, Object target, Map<String,Integer> map) {
        Class<?> srcClass = source.getClass();
        Field[] fields = srcClass.getDeclaredFields();
        for (Field field : fields) {
            String nameKey = field.getName();
            String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey)
                    .toString();
            String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey)
                    .toString();
            if (!srcValue.equals(tarValue)) {
                map.put(nameKey,1);
            }else{
                map.put(nameKey,0);
            }
        }
        return map;
    }

    /**
     * 根据字段名称取值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getClassValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        }
        try {
            Class beanClass = obj.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                // 非get方法不取
                if (!ms[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue = null;
                try {
                    objValue = ms[i].invoke(obj, new Object[] {});
                } catch (Exception e) {
                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                        || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                    return objValue;
                } else if (fieldName.toUpperCase().equals("SID")
                        && (ms[i].getName().toUpperCase().equals("ID") || ms[i].getName().substring(3).toUpperCase()
                        .equals("ID"))) {
                    return objValue;
                }
            }
        } catch (Exception e) {
            // logger.info("取方法出错！" + e.toString());
        }
        return null;
    }
}
