package com.joyintech.service.common.mapper.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * @program: tams-invest-parent
 * @description: service
 * @author: 强成西
 * @create: 2018-10-26 17:25
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommonMapperDelegateService {

    public Integer modify(@PathVariable("mapperClassName") String mapperClassName,
                          @PathVariable("methodName") String methodName,
                          @PathVariable("key") String key, @RequestBody Map<String, Object> param)
        throws ClassNotFoundException,
        IllegalAccessException,
        InvocationTargetException {
        Class<?> mapper = Class.forName(mapperClassName);
        Object bean = SpringContextUtil.getBean(mapper);
        for (Method method : mapper.getDeclaredMethods()) {
            if (StringUtil.equals(method.getName(), methodName)) {
                Class<?> type = method.getParameterTypes()[0];
                Object object = BeanUtil.mapToObject(param, type);
                if (StringUtil.equals(type.getName(), "java.lang.String")) {
                    object = key;
                }
                Object ret = method.invoke(bean, object);
                if (ret != null && ret instanceof Integer) {
                    return (int)ret;
                }
                break;
            }
        }
        return null;
    }

    public Object selectByPrimaryKey(String mapperClassName, String methodName, String key,
                                     Map<String, Object> param)
        throws ClassNotFoundException,
        IllegalAccessException,
        InvocationTargetException {
        Object ret = null;
        Class<?> mapper = Class.forName(mapperClassName);
        Object bean = SpringContextUtil.getBean(mapper);
        for (Method method : mapper.getDeclaredMethods()) {
            if (StringUtil.equals(method.getName(), methodName)) {
                Class<?> type = method.getParameterTypes()[0];
                Object object = BeanUtil.mapToObject(param, type);
                if (StringUtil.equals(type.getName(), "java.lang.String")) {
                    object = key;
                }
                ret = method.invoke(bean, object);
                break;
            }
        }
        return ret;
    }
}
