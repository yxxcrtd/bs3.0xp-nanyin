/*
 * 文件名：InterfMapValUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.invest.interf.common.core.util;

import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.convert.DateConverter;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.common.core.annotation.DictLink;
import com.joyintech.invest.interf.common.core.annotation.Index;
import com.joyintech.invest.interf.common.core.cache.InvestInterfCacheUtil;
import com.joyintech.invest.interf.common.core.contants.InterfColumns;
import com.joyintech.invest.interf.common.exception.InterfException;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 解析接口字段工具
 * 
 * @author zhangrui1
 * @version 1.0 2019年7月26日
 * @see InfMapValUtil
 * @since 1.0
 */
public class InfMapValUtil {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(InfMapValUtil.class);

    private InfMapValUtil() {
        // private the constructor
    }

    /**
     * 从Map中取出指定的共通的
     * 
     * @param dataMap hash map
     * @param key 接口共同字段
     * @return 字段对应的值
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static final <V> V get(Map<String, Object> dataMap, InterfColumns key) {
        if (dataMap == null || dataMap.isEmpty()) {
            return null;
        }
        return (V)dataMap.get(key.fieldName());
    }

    public static <V> V mapDataToObject(Map<String, Object> mapData, Class<V> beanClass) {
        if (mapData == null || mapData.keySet() == null) {
            return null;
        }
        Set<String> delSet = new HashSet<>();
        mapData.entrySet().forEach(en->{
            if (en.getValue() == null) {
                delSet.add(en.getKey());
            }
        });
        delSet.forEach(key -> mapData.remove(key));
        DateConverter converter = new DateConverter();
        converter.setPattern(DateUtil.DEFAULT_DATE_FORMAT_EN);
        ConvertUtils.register(converter, Date.class);
        return BeanUtil.mapToObject(mapData, beanClass);
    }

    /**
     * 文件行数据转化成Entity
     * 
     * @param data 文件行数据
     * @param clz Entity类型
     * @param <T> 返回类型
     * @return Entity实例
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static <T> T parseArrToEntity(String[] data, Class<T> clz)
        throws InterfException {
        // 数据为空或者受体类不存在时返回空
        if (data == null || data.length == 0 || clz == null) {
            return null;
        }
        // 实例化受体类
        try {
            T entity = clz.newInstance();
            // 受体类的方法列表
            Method[] fList = clz.getDeclaredMethods();
            for (Method md : fList) {
                // 受体类的方法注解
                Index idx = md.getAnnotation(Index.class);
                // 受体类的方法不是set方法或者没有注解时跳过
                if (!StringUtil.startsWith(md.getName(), "set") || idx == null) {
                    continue;
                }
                int i = idx.value();
                // 参数类型
                String paramType = md.getGenericParameterTypes()[0].getTypeName();
                String val = data[i - 1];
                if (StringUtil.isEmpty(val)) {
                    continue;
                }
                // String类型时
                if (isClassType(paramType, String.class)) {
                    // 关联数据字典
                    DictLink dict = md.getAnnotation(DictLink.class);
                    // 数据字典转换
                    if (dict != null) {
                        val = InvestInterfCacheUtil.changeDictCode(dict.link(), val);
                    }
                    md.invoke(entity, val);
                }
                // Date类型时
                else if (isClassType(paramType, Date.class)) {
                    // 设置值
                    md.invoke(entity, DateUtil.parseDate(val, DateUtil.DEFAULT_OTHER_FORMAT));
                }
                // Date类型时
                else if (isClassType(paramType, BigDecimal.class)) {
                    // 设置值
                    md.invoke(entity, new BigDecimal(val));
                }
                // int类型时
                else if (isClassType(paramType, Integer.class)) {
                    // 设置值
                    md.invoke(entity, new Integer(val));
                }
                // long类型时
                else if (isClassType(paramType, Long.class)) {
                    // 设置值
                    md.invoke(entity, new Long(val));
                }
                else {
                    throw new InterfException("受体类属性(" + md.getName() + ")类型(" + paramType
                                              + ")定义不符合规范:【String, Date, BigDecimal, int, long】");
                }
            }
            return entity;
        }
        catch (InstantiationException | IllegalAccessException | SecurityException
            | IllegalArgumentException | InvocationTargetException | ParseException e) {
            LOGGER.error("文件解析异常", e);
            throw new InterfException("文件解析异常");
        }
    }

    /**
     * 判断Class类型
     * 
     * @param type 对象
     * @param clz 目标
     * @return true:符合预期；false:不符合
     * @see
     * @since 1.0
     */
    private static <T> boolean isClassType(String type, Class<T> clz) {
        String clzName = clz.getName();
        return StringUtil.equals(clzName, type);
    }
    
    /**
	 * 
	 * <多层Map的值的获取>
	 * 
	 * @param key
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static Object getValueFromDeepMap(String key, Map<String, Object> map) {
		Set<String> keys = map.keySet();
		if (keys != null && !keys.isEmpty()) {
			for (String k : keys) {
				if (k.equalsIgnoreCase(key)) {
					return map.get(k);
				}
			}
		}		
		Collection<Object> values = map.values();
		Object obj = null;
		for (Object value: values) {
			if (value instanceof HashMap) {
				obj = getValueFromDeepMap(key, (HashMap)value);
			}
			if (obj != null) {
				break;
			}
		}
		return obj;
	}

    // 将map值全部转换为大写
    public static Map<String, Object> transformUpperCase(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();
        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }
        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = key.toUpperCase();
            resultMap.put(newKey, orgMap.get(key));
        }
        return resultMap;
    }
}
