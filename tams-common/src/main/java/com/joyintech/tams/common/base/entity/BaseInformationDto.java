package com.joyintech.tams.common.base.entity;

import java.io.Serializable;
import java.util.List;

import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 业务基础表信息实体类
 * @author gaojian
 *
 */
public class BaseInformationDto implements Serializable, ITableRow{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2834955471362823759L;

	/**
	 * 业务基础表主键
	 */
	private String baseInformationId;
	
	/**
	 * 业务基础表代号
	 */
	private String baseTableId;
	
	/**
	 * 业务基础表名称
	 */
	private String baseTableName;
	
	/**
	 * 业务基础表字段代号
	 */
	private String baseColumnId;
	
	/**
	 * 业务基础表字段名称
	 */
	private String baseColumnName;
	
	/**
	 * 业务基础表字段类型
	 */
	private String baseColumnType;

	/**
	 * 文本类型字段传入参数
	 */
	private String columnText;
	
	/**
	 * 日期类型参数起始时间
	 */
	private String startDate;
	
	/**
	 * 日期类型参数结束时间
	 */
	private String endDate;
	
	/**
	 * 多选类型参数列表
	 */
	private List<String> columnSeq;

	/**
	 * 数据对象
	 */
	private String dataObject;
	
	/**
	 * 业务主键标志
	 */
	private String businesspkFlag;
	
	/**
	 * 数据字典代码
	 */
	private String dictCode;
	

	public String getBaseInformationId() {
		return baseInformationId;
	}

	public void setBaseInformationId(String baseInformationId) {
		this.baseInformationId = baseInformationId;
	}

	public String getBaseTableId() {
		return baseTableId;
	}

	public void setBaseTableId(String baseTableId) {
		this.baseTableId = baseTableId;
	}

	public String getBaseTableName() {
		return baseTableName;
	}

	public void setBaseTableName(String baseTableName) {
		this.baseTableName = baseTableName;
	}

	public String getBaseColumnId() {
		return baseColumnId;
	}

	public void setBaseColumnId(String baseColumnId) {
		this.baseColumnId = baseColumnId;
	}

	public String getBaseColumnName() {
		return baseColumnName;
	}

	public void setBaseColumnName(String baseColumnName) {
		this.baseColumnName = baseColumnName;
	}

	public String getBaseColumnType() {
		return baseColumnType;
	}

	public void setBaseColumnType(String baseColumnType) {
		this.baseColumnType = baseColumnType;
	}

	public String getColumnText() {
		return columnText;
	}

	public void setColumnText(String columnText) {
		this.columnText = columnText;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<String> getColumnSeq() {
		return columnSeq;
	}

	public void setColumnSeq(List<String> columnSeq) {
		this.columnSeq = columnSeq;
	}

	public String getDataObject() {
		return dataObject;
	}

	public void setDataObject(String dataObject) {
		this.dataObject = dataObject;
	}

	public String getBusinesspkFlag() {
		return businesspkFlag;
	}

	public void setBusinesspkFlag(String businesspkFlag) {
		this.businesspkFlag = businesspkFlag;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseColumnId == null) ? 0 : baseColumnId.hashCode());
		result = prime * result + ((baseColumnName == null) ? 0 : baseColumnName.hashCode());
		result = prime * result + ((baseColumnType == null) ? 0 : baseColumnType.hashCode());
		result = prime * result + ((baseInformationId == null) ? 0 : baseInformationId.hashCode());
		result = prime * result + ((baseTableId == null) ? 0 : baseTableId.hashCode());
		result = prime * result + ((baseTableName == null) ? 0 : baseTableName.hashCode());
		result = prime * result + ((businesspkFlag == null) ? 0 : businesspkFlag.hashCode());
		result = prime * result + ((columnSeq == null) ? 0 : columnSeq.hashCode());
		result = prime * result + ((columnText == null) ? 0 : columnText.hashCode());
		result = prime * result + ((dataObject == null) ? 0 : dataObject.hashCode());
		result = prime * result + ((dictCode == null) ? 0 : dictCode.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		BaseInformationDto other = (BaseInformationDto) obj;
		if (baseColumnId == null) {
			if (other.baseColumnId != null)
				return false;
		} else if (!baseColumnId.equals(other.baseColumnId))
			return false;
		if (baseColumnName == null) {
			if (other.baseColumnName != null)
				return false;
		} else if (!baseColumnName.equals(other.baseColumnName))
			return false;
		if (baseColumnType == null) {
			if (other.baseColumnType != null)
				return false;
		} else if (!baseColumnType.equals(other.baseColumnType))
			return false;
		if (baseInformationId == null) {
			if (other.baseInformationId != null)
				return false;
		} else if (!baseInformationId.equals(other.baseInformationId))
			return false;
		if (baseTableId == null) {
			if (other.baseTableId != null)
				return false;
		} else if (!baseTableId.equals(other.baseTableId))
			return false;
		if (baseTableName == null) {
			if (other.baseTableName != null)
				return false;
		} else if (!baseTableName.equals(other.baseTableName))
			return false;
		if (businesspkFlag == null) {
			if (other.businesspkFlag != null)
				return false;
		} else if (!businesspkFlag.equals(other.businesspkFlag))
			return false;
		if (columnSeq == null) {
			if (other.columnSeq != null)
				return false;
		} else if (!columnSeq.equals(other.columnSeq))
			return false;
		if (columnText == null) {
			if (other.columnText != null)
				return false;
		} else if (!columnText.equals(other.columnText))
			return false;
		if (dataObject == null) {
			if (other.dataObject != null)
				return false;
		} else if (!dataObject.equals(other.dataObject))
			return false;
		if (dictCode == null) {
			if (other.dictCode != null)
				return false;
		} else if (!dictCode.equals(other.dictCode))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseInformationDto [baseInformationId=" + baseInformationId + ", baseTableId=" + baseTableId
				+ ", baseTableName=" + baseTableName + ", baseColumnId=" + baseColumnId + ", baseColumnName="
				+ baseColumnName + ", baseColumnType=" + baseColumnType + ", columnText=" + columnText + ", startDate="
				+ startDate + ", endDate=" + endDate + ", columnSeq=" + columnSeq + ", dataObject=" + dataObject
				+ ", businesspkFlag=" + businesspkFlag + ", dictCode=" + dictCode + "]";
	}

	/* (非 Javadoc)
	* 
	* 
	* @return
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#getCreateUser()
	*/
	    
	@Override
	public String getCreateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	* 
	* 
	* @param createUser
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#setCreateUser(java.lang.String)
	*/
	    
	@Override
	public void setCreateUser(String createUser) {
		// TODO Auto-generated method stub
		
	}

	/* (非 Javadoc)
	* 
	* 
	* @return
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#getCreateDept()
	*/
	    
	@Override
	public String getCreateDept() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc)
	* 
	* 
	* @param createDept
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#setCreateDept(java.lang.String)
	*/
	    
	@Override
	public void setCreateDept(String createDept) {
		// TODO Auto-generated method stub
		
	}
	
	
}