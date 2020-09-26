package com.joyintech.tams.svreport.interf.dis.institutiondata.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class DisAssociatedInstitutions extends BaseEntity{	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * ID
     */
    private String relorgId;   
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建部门
     */
    private String createDept;   
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateteUser;    
    /**
     * 更新时间
     */
    private Date updateTime;

	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public String getUpdateteUser() {
		return updateteUser;
	}

	public void setUpdateteUser(String updateteUser) {
		this.updateteUser = updateteUser;
	}

	public String getRelorgId() {
		return relorgId;
	}

	public void setRelorgId(String relorgId) {
		this.relorgId = relorgId;
	}
}