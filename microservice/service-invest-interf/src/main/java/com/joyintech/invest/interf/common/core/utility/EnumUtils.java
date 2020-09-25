package com.joyintech.invest.interf.common.core.utility;

import com.joyintech.invest.interf.common.core.contants.InfDictCode;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EnumUtils {
    private static Logger log= LoggerFactory.getLogger(EnumUtils.class);

    /**
     *  根据枚举分类和 值获取对应的名称
     * @param enumT 枚举类
     * @param dictName 枚举字典dict名称
     * @param enumValue 枚举字典dict名称
     * @param <T>
     * @return
     */
    public static <T> String getEnumName(Class<T> enumT, InfDictCode dictName,String enumValue) {
        String enumName="";
        T enums []= enumT.getEnumConstants(); //得到枚举类里的实例
        if (enums == null || enums.length <= 0) {
            return null;
        }
        for (int i = 0;i < enums.length; i++) {
            T tobj = enums[i];
            if(((InfDictCodeList)tobj).getDictCode()==dictName &&( ((InfDictCodeList) tobj).getValue()).equals(enumValue))
            {
                enumName=((InfDictCodeList) tobj).getCodeName();
                break;
            }
        }
        return enumName;
    }

    /**
     *  把枚举类的值都转成Map
     * @param enumT 枚举类
     * @param methodNames 读取的方法
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> EnumToMap(Class<T> enumT, String... methodNames) {
        Map<String, String> enummap = new HashMap<String, String>();
        if (!enumT.isEnum()) { //如果不是enum
            return enummap;
        }
        T enums []= enumT.getEnumConstants(); //得到枚举类里的实例
        if (enums == null || enums.length <= 0) {
            return enummap;
        }
        int count = methodNames.length;
        /**默认接口key方法*/
        String keyMathod = "getReason";
        /**默认接口value方法*/
        String valueMathod = "getIndex";

        if (count >= 1 && !"".equals(methodNames[0])) {
            keyMathod = methodNames[0];
        }
        if (count == 2 && !"".equals(methodNames[1])) {
            valueMathod = methodNames[1];
        }
        for (int i = 0;i < enums.length; i++) {
            T tobj = enums[i];

            try {
                /**获取key值*/
                Object resultkey = getMethodValue(keyMathod, tobj);
                if ("".equals(resultkey)) {
                    continue;
                }
                /**获取value值*/
                Object resultValue = getMethodValue(valueMathod, tobj);
                /**如果描述不存在获取整条enum*/
                if ("".equals(resultValue)) {
                    resultValue = tobj;
                }
                enummap.put(resultkey+"", resultValue+"");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("枚举类转成Map异常",e);
            }
        }
        return enummap;
    }

    private static <T> Object getMethodValue(String methodName, T obj, Object... args) {
        Object resut = "";
        try {
            /********************************* start *****************************************/
            /**获取方法数组，这里只要公有的方法*/
            Method[] methods = obj.getClass().getMethods();
            if (methods.length <= 0) {
                return resut;
            }
            Method method = null;
            for (int i = 0, len = methods.length; i < len; i++) {
                /**忽略大小写取方法*/
                if (methods[i].getName().equalsIgnoreCase(methodName)) {
                    /**如果存在，则取出正确的方法名称*/
                    methodName = methods[i].getName();
                    method = methods[i];
                    break;
                }
            }
            /*************************** end ***********************************************/
            if (method == null) {
                return resut;
            }
            /**方法执行*/
            resut = method.invoke(obj, args);
            if (resut == null) {
                resut = "";
            }
            /**返回结果*/
            return resut;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resut;
    }


}
