package com.jixu.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.json.JsonSanitizer;


public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static void writeJSONToResponse(HttpServletResponse response, String json){
		response.setCharacterEncoding("utf-8");
		PrintWriter pw;  
        try {  
            pw = response.getWriter(); 
            pw.print(JsonSanitizer.sanitize(json));  
            pw.flush(); 
            pw.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
	}
	
	public static void writeJSONToResponseOutput(HttpServletResponse response, String json){
		response.setCharacterEncoding("utf-8");
		ServletOutputStream output;  
        try {  
            output = response.getOutputStream(); 
            output.print(json);  
            output.flush();
            output.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
	}
	
	public static void writeObjToResponse(HttpServletResponse response, Object obj){
		writeJSONToResponse(response, objToString(obj));
	}
	
	public static void writeObjToResponseOutput(HttpServletResponse response, Object obj){
		writeJSONToResponseOutput(response, objToString(obj));
	}
	
	public static String objToString(Object obj){
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static List<Object> stringToList(String json){
		try {
			return objectMapper.readValue(JsonSanitizer.sanitize(json), List.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Object>();
		}
	}
	
	public static Map<String, Object> stringToMap(String json){
		try {
			return objectMapper.readValue(JsonSanitizer.sanitize(json), Map.class);
		} catch (Exception e) {
			return new HashMap<String, Object>();
		}
	}
	
	public static HashMap<String, String> stringToHashMap(String json){
		try {
			return objectMapper.readValue(JsonSanitizer.sanitize(json), HashMap.class);
		} catch (Exception e) {
			return new HashMap<String, String>();
		}
	}
	
	/**
	 * 嵌套两层List
	 * @param json
	 * @return
	 */
	public static List<List<String>> stringToList2(String json){
		try {
			List<List<String>> aaa=new ArrayList<List<String>>();
			return objectMapper.readValue(JsonSanitizer.sanitize(json), aaa.getClass());
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<List<String>>();
		}
	}
	
	public static List<HashMap<String, Object>> stringToListHashmap(String json){
		try {
			List<HashMap<String, Object>> aaa=new ArrayList<HashMap<String, Object>>();
			return objectMapper.readValue(JsonSanitizer.sanitize(json), aaa.getClass());
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<HashMap<String, Object>>();
		}
	}
}
