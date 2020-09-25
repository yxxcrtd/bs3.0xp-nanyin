package com.joyintech.interf.bankname.cfetsup.outbound;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * json工具类
 *
 */
public class JSONUtil {
	/**
	 * ObjectMapper
	 */
	private static ObjectMapper om = new ObjectMapper(new JsonFactory());

	/**
	 * json转对象
	 * @param json json
	 * @param c 要转换的类型
	 * @param <T> <T>
	 * @return 返回值
	 */
	public static <T> T json2Object(String json, Class<T> c){
		try {
			return om.readValue(json, c);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对象转json
	 * @param o o
	 * @return 返回json
	 */
	public static String object2Json(Object o){
		try {
			return om.writeValueAsString(o);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json转复杂对象
	 * @param json json
	 * @param c class
	 * @param cs classes
	 * @param <T> <T>
	 * @return T
	 */
	public static <T> T json2Object(String json, Class<T> c, Class<?>... cs){
		JavaType javaType = om.getTypeFactory().constructParametricType(c, cs);
		try {
			return om.readValue(json, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
