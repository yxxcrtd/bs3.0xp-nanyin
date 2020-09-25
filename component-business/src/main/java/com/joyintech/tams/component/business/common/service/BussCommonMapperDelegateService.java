package com.joyintech.tams.component.business.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-09-17 15:32
 **/
@Service
public class BussCommonMapperDelegateService extends BaseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T> T selectBySinglePrimaryKey(Class<?> mapper, String key, Class<T> responseType) {
        Object bean = SpringContextUtil.getBean(mapper);
        Object ret;
        try {
            Method selectByPrimaryKey = null;
            Method[] methods = mapper.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("selectByPrimaryKey")) {
                    selectByPrimaryKey = method;
                    break;
                }
            }
            if (selectByPrimaryKey != null) {
                ret = selectByPrimaryKey.invoke(bean, key);
                if (ret != null) {
                    T result = responseType.newInstance();
                    if (result.getClass().isAssignableFrom(ret.getClass())) {
                        return (T) ret;
                    }
                    BeanUtil.copyPropertiesIgnoreNull(result, ret);
                    return result;
                }
            }
        } catch (IllegalAccessException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InvocationTargetException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T selectByMultiPrimaryKey(Class<?> mapper, Object param, Class<T> responseType) {
        Object bean = SpringContextUtil.getBean(mapper);
        Object ret;
        try {
            Method selectByPrimaryKey = null;
            Method[] methods = mapper.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("selectByPrimaryKey")) {
                    selectByPrimaryKey = method;
                    break;
                }
            }
            if (selectByPrimaryKey != null) {
                ret = selectByPrimaryKey.invoke(bean, param);
                if (ret != null) {
                    T result = responseType.newInstance();
                    if (result.getClass().isAssignableFrom(ret.getClass())) {
                        return (T) ret;
                    }
                    BeanUtil.copyPropertiesIgnoreNull(result, ret);
                    return result;
                }
            }
        } catch (IllegalAccessException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InvocationTargetException e) {
            logger.error(mapper.getName()  + "selectByPrimaryKey方法调用失败", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
