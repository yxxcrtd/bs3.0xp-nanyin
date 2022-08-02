package com.joyintech.tams.disclosure.busicommon.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtils {
	/**
	 * 生成查询映射
	 * 
	 * @param model
	 * @param fuzzyFields
	 *            模糊查询字段
	 * @return
	 */
	public static Map<String, Object> createQueryMap(Object model,
			String[] fuzzyFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 反射生成model对应的字段查询
		Field[] fields = model.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object value = getFieldValueByName(field.getName(), model);
			if (value != null) {
				// 不是模糊查询的字段
				map.put(field.getName(), value);
				// 生成模糊查询字段与值
				if (fuzzyFields != null) {
					for (String fuzzyField : fuzzyFields) {
						if (fuzzyField.toString().equals(field.getName())) {
							map.put(field.getName() + "_like", value);
							map.remove(field.getName());
							break;
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * 获取javabean对象的属性值
	 * @param fieldName
	 * @param o
	 * @return
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 说明：判断List 是否为空<br>
	 * 备注：<br>
	 * 
	 * @param list
	 * @return null和空的情况返回true, 否则false
	 */
	public static boolean isEmptyList(List<?> list) {
		return list == null || list.size() == 0;
	}
	
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
					Object result = readMethod.invoke(bean, new Object[0]);
					returnMap.put(propertyName, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
}
