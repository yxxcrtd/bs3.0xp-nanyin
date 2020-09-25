package com.jixu.adapter.model;

/**
 * 是否根据这些关键字合并或者删除
 * @author 桃汰猪
 *
 */
public class MergeKeys {
	private String columnName;
	
	private int selectFlag;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(int selectFlag) {
		this.selectFlag = selectFlag;
	}
}
