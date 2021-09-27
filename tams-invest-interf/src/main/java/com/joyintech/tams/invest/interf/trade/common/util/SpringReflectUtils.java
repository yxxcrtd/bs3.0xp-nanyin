package com.joyintech.tams.invest.interf.trade.common.util;

import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

import com.joyintech.framework.util.SpringContextUtil;
/**
 * spring反射工具类
 * 
 * @author yflv
 * @version 1.0 2021年09月26日
 * @see SpringReflectUtils
 * @since 1.0
 */
public class SpringReflectUtils {

    /**
     * 根据 服务名称 ，方法名 反射调用  spring bean 中的 方法
     * @param serviceName 服务名
     * @param methodName 方法名
     * @param params 参数
     * @return
     * @throws Exception
     */
    public static Object springInvokeMethod(String serviceName, String methodName, Object[] params) throws Exception {
        Object service = SpringContextUtil.getBean(serviceName);
        Class<? extends Object>[] paramClass = null;
        if (params != null) {
            int paramsLength = params.length;
            paramClass = new Class[paramsLength];
            for (int i = 0; i < paramsLength; i++) {
                paramClass[i] = params[i].getClass();
            }
        }
        // 找到方法
        Method method = ReflectionUtils.findMethod(service.getClass(), methodName, paramClass);
        // 执行方法
        return ReflectionUtils.invokeMethod(method, service, params);
    }
}
