package com.joyintech.tams.invest.batch.valbatchimport.dto;

public enum ValType {

	ASSET("ASSET"),
	SUM("SUM");
	
	private String desc;
	
	private ValType(String desc){
		this.desc=desc;
	}
	public String getDesc() {
		return this.desc;
	}
}
