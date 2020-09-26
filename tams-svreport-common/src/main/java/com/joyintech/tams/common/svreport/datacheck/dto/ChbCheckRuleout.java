package com.joyintech.tams.common.svreport.datacheck.dto;

import java.util.Date;

/**
 * 描述:CHB_CHECK_RULEOUT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-04-30
 */
public class ChbCheckRuleout {
    /**
     * 主键
     */
    private String ruleoutid;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 检查方法标识
     */
    private String reprotCode;

    /**
     * 检查指标
     */
    private String checkIndex;

    /**
     * 检查类型，B－报送前检查，S－总体核查
     */
    private String checkType;

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

    /**
     * 主键
     * @return RULEOUTID 主键
     */
    public String getRuleoutid() {
        return ruleoutid;
    }

    /**
     * 主键
     * @param ruleoutid 主键
     */
    public void setRuleoutid(String ruleoutid) {
        this.ruleoutid = ruleoutid == null ? null : ruleoutid.trim();
    }

    /**
     * 产品代码
     * @return PROD_ID 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 资产代码
     * @return ASSET_CODE 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 检查方法标识
     * @return REPROT_CODE 检查方法标识
     */
    public String getReprotCode() {
        return reprotCode;
    }

    /**
     * 检查方法标识
     * @param reprotCode 检查方法标识
     */
    public void setReprotCode(String reprotCode) {
        this.reprotCode = reprotCode == null ? null : reprotCode.trim();
    }

    /**
     * 检查指标
     * @return CHECK_INDEX 检查指标
     */
    public String getCheckIndex() {
        return checkIndex;
    }

    /**
     * 检查指标
     * @param checkIndex 检查指标
     */
    public void setCheckIndex(String checkIndex) {
        this.checkIndex = checkIndex == null ? null : checkIndex.trim();
    }

    /**
     * 检查类型，B－报送前检查，S－总体核查
     * @return CHECK_TYPE 检查类型，B－报送前检查，S－总体核查
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * 检查类型，B－报送前检查，S－总体核查
     * @param checkType 检查类型，B－报送前检查，S－总体核查
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
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