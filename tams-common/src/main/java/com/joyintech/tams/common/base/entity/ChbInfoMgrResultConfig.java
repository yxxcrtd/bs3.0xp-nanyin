
/**  
* @Title: ChbInfoMgrResultConfig.java
* @Package com.joyintech.tams.common.base.entity
* @Description: 业务数据维护查询结果列
* @author gaojian
* @date 2019年7月2日
* @since V1.0  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
    
package com.joyintech.tams.common.base.entity;

import java.io.Serializable;

import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
* @ClassName: ChbInfoMgrResultConfig
* @Description: 业务数据维护查询结果列
* @author gaojian
* @date 2019年7月2日 
*
*/

public class ChbInfoMgrResultConfig implements ITableRow {
	
	/**
	* @Fields field:field:{查询结果ID}
	*/
	    
	private String resultId;
	
	/**
	* @Fields field:field:{查询结果对应的待维护表}
	*/
	    
	private String mgrTableName;
	
	/**
	* @Fields field:field:{查询结果字段名称}
	*/
	    
	private String columnId;
	
	/**
	* @Fields field:field:{查询结果字段中文名称}
	*/
	    
	private String columnName;
	
	/**
	* @Fields field:field:{字段对应数据字典}
	*/
	    
	private String resultdictCode;
	
	/**
	* @Fields field:field:{查询结果排列顺序}
	*/
	    
	private Integer orderNo;
	
	/**
	* @Fields field:field:{是否前台显示标志}
	*/
	private String showFlag;
	
	/**
	 * 实际字段ID（主要针对资产要素表操作情况）
	 */
	private String realColumnId;


	/* (非 Javadoc)
	* 
	* 
	* @return
	* @see com.joyintech.framework.export.paginator.domain.ITableRow#getCreateUser()
	*/
	    
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((mgrTableName == null) ? 0 : mgrTableName.hashCode());
		result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
		result = prime * result + ((realColumnId == null) ? 0 : realColumnId.hashCode());
		result = prime * result + ((resultId == null) ? 0 : resultId.hashCode());
		result = prime * result + ((resultdictCode == null) ? 0 : resultdictCode.hashCode());
		result = prime * result + ((showFlag == null) ? 0 : showFlag.hashCode());
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
		ChbInfoMgrResultConfig other = (ChbInfoMgrResultConfig) obj;
		if (columnId == null) {
			if (other.columnId != null)
				return false;
		} else if (!columnId.equals(other.columnId))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (mgrTableName == null) {
			if (other.mgrTableName != null)
				return false;
		} else if (!mgrTableName.equals(other.mgrTableName))
			return false;
		if (orderNo == null) {
			if (other.orderNo != null)
				return false;
		} else if (!orderNo.equals(other.orderNo))
			return false;
		if (realColumnId == null) {
			if (other.realColumnId != null)
				return false;
		} else if (!realColumnId.equals(other.realColumnId))
			return false;
		if (resultId == null) {
			if (other.resultId != null)
				return false;
		} else if (!resultId.equals(other.resultId))
			return false;
		if (resultdictCode == null) {
			if (other.resultdictCode != null)
				return false;
		} else if (!resultdictCode.equals(other.resultdictCode))
			return false;
		if (showFlag == null) {
			if (other.showFlag != null)
				return false;
		} else if (!showFlag.equals(other.showFlag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChbInfoMgrResultConfig [resultId=" + resultId + ", mgrTableName=" + mgrTableName + ", columnId="
				+ columnId + ", columnName=" + columnName + ", resultdictCode=" + resultdictCode + ", orderNo="
				+ orderNo + ", showFlag=" + showFlag + ", realColumnId=" + realColumnId + "]";
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getMgrTableName() {
		return mgrTableName;
	}

	public void setMgrTableName(String mgrTableName) {
		this.mgrTableName = mgrTableName;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getResultdictCode() {
		return resultdictCode;
	}

	public void setResultdictCode(String resultdictCode) {
		this.resultdictCode = resultdictCode;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

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

	/**
	 * @Description TODO
	 * @Author      高健
	 * @Date        2019年12月7日 下午3:48:50
	 * @return realColumnId
	 */

	public String getRealColumnId() {
		return realColumnId;
	}

	/**
	 * @Description TODO
	 * @Author      高健
	 * @Date        2019年12月7日 下午3:48:50
	 * @param realColumnId realColumnId
	 */

	public void setRealColumnId(String realColumnId) {
		this.realColumnId = realColumnId;
	}


}
