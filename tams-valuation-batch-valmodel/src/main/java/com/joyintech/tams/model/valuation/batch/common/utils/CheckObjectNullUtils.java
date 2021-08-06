package com.joyintech.tams.model.valuation.batch.common.utils;

import java.lang.reflect.Field;

/**
 * 判断对象属性是否为空
 * @author zhangfei
 * @date 2021年7月21日16:56:34
 */
public class CheckObjectNullUtils {
    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkObjFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        Boolean flag = false;
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));
                if (f.get(object) == null ) {
                    flag =  true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

}
