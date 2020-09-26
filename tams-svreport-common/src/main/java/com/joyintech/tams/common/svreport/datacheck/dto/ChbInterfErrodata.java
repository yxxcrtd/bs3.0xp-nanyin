package com.joyintech.tams.common.svreport.datacheck.dto;

import java.util.Date;

/**
 * 描述:CHB_INTERF_ERRODATA表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-04-28
 */
public class ChbInterfErrodata {
	 /**
     * uuid
     */
    private String datauuid;
    /**
     * 业务主键
     */
    private String dataid;

    /**
     * 接口类型
     */
    private String interfType;

    /**
     * 文件名称
     */
    private String fireName;

    /**
     * 有效状态
     */
    private String status;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建部门
     */
    private String createDept;

    
    public String getDatauuid() {
		return datauuid;
	}

	public void setDatauuid(String datauuid) {
		this.datauuid = datauuid;
	}

	/**
     * 主键
     * @return DATAID 主键
     */
    public String getDataid() {
        return dataid;
    }

    /**
     * 主键
     * @param dataid 主键
     */
    public void setDataid(String dataid) {
        this.dataid = dataid == null ? null : dataid.trim();
    }

    /**
     * 接口类型
     * @return INTERF_TYPE 接口类型
     */
    public String getInterfType() {
        return interfType;
    }

    /**
     * 接口类型
     * @param interfType 接口类型
     */
    public void setInterfType(String interfType) {
        this.interfType = interfType == null ? null : interfType.trim();
    }

    /**
     * 文件名称
     * @return FIRE_NAME 文件名称
     */
    public String getFireName() {
        return fireName;
    }

    /**
     * 文件名称
     * @param fireName 文件名称
     */
    public void setFireName(String fireName) {
        this.fireName = fireName == null ? null : fireName.trim();
    }

    /**
     * 有效状态
     * @return STATUS 有效状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 有效状态
     * @param status 有效状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 工作流代码
     * @return WORKFLOW_ID 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }
}