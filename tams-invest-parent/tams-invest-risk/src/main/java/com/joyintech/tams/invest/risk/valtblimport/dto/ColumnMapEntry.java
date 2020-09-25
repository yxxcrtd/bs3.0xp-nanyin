package com.joyintech.tams.invest.risk.valtblimport.dto;


public class ColumnMapEntry {

	private Integer colIndex;
	private ValDataType dataType;
	//估值表
	private ValIndex valIndex;
	
	public void setDataType(String sDataType) {
		dataType = ValDataType.valueOf(sDataType);
	}
	public void setValIndex(String sValIndex) {
		valIndex = ValIndex.valueOf(sValIndex);
	}
	
	public Integer getColIndex() {
		return colIndex;
	}
	public void setColIndex(Integer colIndex) {
		this.colIndex = colIndex;
	}
	public ValDataType getDataType() {
		return dataType;
	}
	public void setDataType(ValDataType dataType) {
		this.dataType = dataType;
	}
	public ValIndex getValIndex() {
		return valIndex;
	}
	public void setValIndex(ValIndex valIndex) {
		this.valIndex = valIndex;
	}
}
