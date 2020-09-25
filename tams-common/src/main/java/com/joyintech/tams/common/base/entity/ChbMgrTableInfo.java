
/**  
* @Title: ChbMgrTableInfo.java
* @Package com.joyintech.tams.common.base.entity
* @Description: 业务数据维护待维护表基本信息
* @author gaojian
* @date 2019年7月2日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
    
package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
* @ClassName: ChbMgrTableInfo
* @Description: 业务数据维护待维护表基本信息
* @author gaojian
* @date 2019年7月2日 
*
*/
    
public class ChbMgrTableInfo implements ITableRow{
	  
	/**
	* @Fields field:field:{待维护表主键}
	*/
	    
	private String mgrTableId;
	
	/**
	* @Fields field:field:{待维护表名称}
	*/
	    
	private String mgrTableName;
	
	/**
	* @Fields field:field:{待维护表中文名称}
	*/
	    
	private String mgrTableChiName;
	
	/**
	* @Fields field:field:{待维护表对应的接口名称，作为报表连接器的参数}
	*/
	    
	private String dataObject;
	
	/**
	* @Fields field:field:{待维护表数据状态，E:激活状态，D:休眠状态}
	*/
	    
	private String status;
	
	/**
	* @Fields field:field:{创建用户}
	*/
	    
	private String createUser;
	
	/**
	* @Fields field:field:{创建部门}
	*/
	    
	private String createDept;
	
	private String headerNum;
	
	public String getMgrTableId() {
		return mgrTableId;
	}

	public void setMgrTableId(String mgrTableId) {
		this.mgrTableId = mgrTableId;
	}

	public String getMgrTableName() {
		return mgrTableName;
	}

	public void setMgrTableName(String mgrTableName) {
		this.mgrTableName = mgrTableName;
	}

	public String getMgrTableChiName() {
		return mgrTableChiName;
	}

	public void setMgrTableChiName(String mgrTableChiName) {
		this.mgrTableChiName = mgrTableChiName;
	}

	public String getDataObject() {
		return dataObject;
	}

	public void setDataObject(String dataObject) {
		this.dataObject = dataObject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataObject == null) ? 0 : dataObject.hashCode());
		result = prime * result + ((mgrTableChiName == null) ? 0 : mgrTableChiName.hashCode());
		result = prime * result + ((mgrTableId == null) ? 0 : mgrTableId.hashCode());
		result = prime * result + ((mgrTableName == null) ? 0 : mgrTableName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ChbMgrTableInfo other = (ChbMgrTableInfo) obj;
		if (dataObject == null) {
			if (other.dataObject != null)
				return false;
		} else if (!dataObject.equals(other.dataObject))
			return false;
		if (mgrTableChiName == null) {
			if (other.mgrTableChiName != null)
				return false;
		} else if (!mgrTableChiName.equals(other.mgrTableChiName))
			return false;
		if (mgrTableId == null) {
			if (other.mgrTableId != null)
				return false;
		} else if (!mgrTableId.equals(other.mgrTableId))
			return false;
		if (mgrTableName == null) {
			if (other.mgrTableName != null)
				return false;
		} else if (!mgrTableName.equals(other.mgrTableName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChbMgrTableInfo [mgrTableId=" + mgrTableId + ", mgrTableName=" + mgrTableName + ", mgrTableChiName="
				+ mgrTableChiName + ", dataObject=" + dataObject + ", status=" + status + "]";
	}

	/* 
	* 
	* 
	* @return
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#getCreateUser()
	*/
	    
	@Override
	public String getCreateUser() {
		return createUser;
	}

	/* 
	* 
	* 
	* @param createUser
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#setCreateUser(java.lang.String)
	*/
	    
	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/* 
	* 
	* 
	* @return
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#getCreateDept()
	*/
	    
	@Override
	public String getCreateDept() {
		return createDept;
	}

	/* 
	* 
	* 
	* @param createDept
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#setCreateDept(java.lang.String)
	*/
	    
	@Override
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
		
	}

	public String getHeaderNum() {
		return headerNum;
	}

	public void setHeaderNum(String headerNum) {
		this.headerNum = headerNum;
	}
}
