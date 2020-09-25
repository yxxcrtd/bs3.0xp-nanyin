package com.joyintech.tams.common.base.entity;

/**
 * 字段属性实体类
 * @author Administrator
 *
 */
public class ColumnTypeDto {
	/**
	 * 字段名
	 */
	private String columnNname;
	
	/**
	 * 字段说明
	 */
	private String comment;

	/**
	 * 字段类型
	 */
	private String columnType;
	
	/**
	 * 字段数据
	 */
	private String columnData;

	public String getColumnNname() {
		return columnNname;
	}

	public void setColumnNname(String columnNname) {
		this.columnNname = columnNname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnData() {
		return columnData;
	}

	public void setColumnData(String columnData) {
		this.columnData = columnData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnData == null) ? 0 : columnData.hashCode());
		result = prime * result + ((columnNname == null) ? 0 : columnNname.hashCode());
		result = prime * result + ((columnType == null) ? 0 : columnType.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColumnTypeDto other = (ColumnTypeDto) obj;
		if (columnData == null) {
			if (other.columnData != null)
				return false;
		} else if (!columnData.equals(other.columnData))
			return false;
		if (columnNname == null) {
			if (other.columnNname != null)
				return false;
		} else if (!columnNname.equals(other.columnNname))
			return false;
		if (columnType == null) {
			if (other.columnType != null)
				return false;
		} else if (!columnType.equals(other.columnType))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ColumnTypeDto [columnNname=" + columnNname + ", comment=" + comment + ", columnType=" + columnType
				+ ", columnData=" + columnData + "]";
	}
	
	
	
}
