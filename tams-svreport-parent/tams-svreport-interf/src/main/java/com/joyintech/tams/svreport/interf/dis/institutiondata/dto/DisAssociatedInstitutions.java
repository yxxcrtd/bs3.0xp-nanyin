package com.joyintech.tams.svreport.interf.dis.institutiondata.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;

public class DisAssociatedInstitutions extends BaseEntity{	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据状态名称
	 */
	private String statusName;
	/**
	 * 数据状态
	 */
	private String status;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

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

	/**
	 * 关联机构名称
	 */
	private String organName;
	/**
	 * 数据来源
	 */
	private String svrDataSources;
	/**
	 * 数据来源名称
	 */
	private String svrDataSourcesName;

	public String getSvrDataSourcesName() {
		return svrDataSourcesName;
	}

	public void setSvrDataSourcesName(String svrDataSourcesName) {
		this.svrDataSourcesName = svrDataSourcesName;
	}

	/**
	 * 导出类型
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * uuid主键
	 */
	private String datauuid;

	public String getDatauuid() {
		return datauuid;
	}

	public void setDatauuid(String datauuid) {
		this.datauuid = datauuid;
	}
	/**
	 * 选择项
	 */
	private List<DisAssociatedInstitutions> selectedItem;

	public List<DisAssociatedInstitutions> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<DisAssociatedInstitutions> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getSvrDataSources() {
		return svrDataSources;
	}

	public void setSvrDataSources(String svrDataSources) {
		this.svrDataSources = svrDataSources;
	}

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