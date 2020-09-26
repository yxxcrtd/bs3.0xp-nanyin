package com.joyintech.tams.valuation.interf.valuation.send.xmlCommon.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class Bean2Map {
	/**
	   * 转换bean为map
	   *
	   * @param source 要转换的bean
	   * @param <T>    bean类型
	   * @return 转换结果
	   */
	  public static <T> Map<String, Object> obj2Map(T source) throws IllegalAccessException {
	    Map<String, Object> result = new HashMap<>();

	    Class<?> sourceClass = source.getClass();
	    //拿到所有的字段,不包括继承的字段
	    Field[] sourceFiled = sourceClass.getDeclaredFields();
	    for (Field field : sourceFiled) {
	      field.setAccessible(true);//设置可访问,不然拿不到private
	      result.put(field.getName(), field.get(source));
	    }
	    return result;
	  }
}
