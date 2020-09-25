package com.jixu.common.type;

/** 
 * @ClassName 		IDGenerateType
 * @author  		Pan Wei 
 * @Date 			2018年3月15日 下午2:42:12 	
 * @Description		[]  
 * @return  
 */

public class IDGenerateType {
	
	//上一个计数ID
	private int lastID;

	//ID的初始值
	private int idInitValue;

	//ID的步长,每次增加多少
	private int idLength;

	//表名
	private String tableName;
	
	private String className;
	
	//该表id的名字
	private String idName;

	public int getLastID() {
		return lastID;
	}

	public void setLastID(int lastID) {
		this.lastID = lastID;
	}

	public int getIdInitValue() {
		return idInitValue;
	}

	public void setIdInitValue(int idInitValue) {
		this.idInitValue = idInitValue;
	}

	public int getIdLength() {
		return idLength;
	}

	public void setIdLength(int idLength) {
		this.idLength = idLength;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
