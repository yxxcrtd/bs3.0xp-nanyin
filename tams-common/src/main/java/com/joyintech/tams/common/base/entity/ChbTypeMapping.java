package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_TYPE_MAPPING表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-02-28
 */
public class ChbTypeMapping  extends BaseEntity {
    /**
     * 流水号
     */
    private String mappingid;

    /**
     * CHB_PROD_DEAL-产品交易表，CHB_PROD_INFO
     */
    private String srcdatatype;

    /**
     * 类别明细1
     */
    private String srcdatatypeid;

    /**
     * 类别2
     */
    private String objdatatype;

    /**
     * 类别明细2
     */
    private String objdatatypeid;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 流水号
     * @return MAPPINGID 流水号
     */
    public String getMappingid() {
        return mappingid;
    }

    /**
     * 流水号
     * @param mappingid 流水号
     */
    public void setMappingid(String mappingid) {
        this.mappingid = mappingid == null ? null : mappingid.trim();
    }

    /**
     * CHB_PROD_DEAL-产品交易表，CHB_PROD_INFO
     * @return SRCDATATYPE CHB_PROD_DEAL-产品交易表，CHB_PROD_INFO
     */
    public String getSrcdatatype() {
        return srcdatatype;
    }

    /**
     * CHB_PROD_DEAL-产品交易表，CHB_PROD_INFO
     * @param srcdatatype CHB_PROD_DEAL-产品交易表，CHB_PROD_INFO
     */
    public void setSrcdatatype(String srcdatatype) {
        this.srcdatatype = srcdatatype == null ? null : srcdatatype.trim();
    }

    /**
     * 类别明细1
     * @return SRCDATATYPEID 类别明细1
     */
    public String getSrcdatatypeid() {
        return srcdatatypeid;
    }

    /**
     * 类别明细1
     * @param srcdatatypeid 类别明细1
     */
    public void setSrcdatatypeid(String srcdatatypeid) {
        this.srcdatatypeid = srcdatatypeid == null ? null : srcdatatypeid.trim();
    }

    /**
     * 类别2
     * @return OBJDATATYPE 类别2
     */
    public String getObjdatatype() {
        return objdatatype;
    }

    /**
     * 类别2
     * @param objdatatype 类别2
     */
    public void setObjdatatype(String objdatatype) {
        this.objdatatype = objdatatype == null ? null : objdatatype.trim();
    }

    /**
     * 类别明细2
     * @return OBJDATATYPEID 类别明细2
     */
    public String getObjdatatypeid() {
        return objdatatypeid;
    }

    /**
     * 类别明细2
     * @param objdatatypeid 类别明细2
     */
    public void setObjdatatypeid(String objdatatypeid) {
        this.objdatatypeid = objdatatypeid == null ? null : objdatatypeid.trim();
    }

    /**
     * 数据状态
     * @return STATUS 数据状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}