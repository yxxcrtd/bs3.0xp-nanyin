package com.joyintech.tams.valuation.common.interf.base.service.vasinterf.model;

public class ValInertfBuildXml001Dto {
	// 数据类型
	private String Type;
    //组装结果
	private String console;
	//异常信息
	private String errorMessage;
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


 
}
