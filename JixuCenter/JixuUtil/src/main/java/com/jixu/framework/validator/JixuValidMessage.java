package com.jixu.framework.validator;

import java.io.Serializable;

public class JixuValidMessage implements Serializable{
	
	private static final long serialVersionUID = -9080800526490643961L;

	public JixuValidMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public JixuValidMessage(String message) {
		this.setMessage(message);
	}
	
	//出错的字段
	private String field;
	
	private String message;
	
	//列
	private String column;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}
