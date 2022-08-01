package com.joyintech.tams.disclosure.busicommon.report.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils;

public class ReportCollectionUtils extends CollectionUtils {
    /**
     * 对象转Map
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> bean2Map(Object bean) {
        @SuppressWarnings("rawtypes")
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    //如果是list，需要迭代刷新属性
                    if(readMethod.getReturnType().getName().equals("java.util.List")){
                        List<Object> list = (List<Object>) readMethod.invoke(bean, new Object[0]);
                        List<Map<String,Object>> resultMapList = new ArrayList<>();
                        if(list != null){
                            for(Object o : list){
                                Map<String,Object> map = bean2Map(o);
                                resultMapList.add(map);
                            }
                        }
                        returnMap.put(propertyName, resultMapList);
                    }else{
                        Object result = readMethod.invoke(bean, new Object[0]);
                        if(result == null || "null".equals(result)){
                            returnMap.put(propertyName, "");
                        }else{
                            returnMap.put(propertyName, result);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }
}
