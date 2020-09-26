package com.joyintech.tams.report.errormessage.dto;

import java.util.Date;
import com.joyintech.framework.export.paginator.domain.ITableRow;

public class ErrorMessage001 implements ITableRow {
  private String dataid;

  private String interfType;
  
  private String fireName;

  private String status;

  private String updateUser;

  private String createUser;

  private Date updateTime;

  private Date createTime;
  
  private String checkResult;

  private String workflowId;

  private String orgCode;

  private String deptCode;

  private String createDept;

  private String orgData;

  private String errMsg;

  public String getDataid() {
    return dataid;
  }

  public void setDataid(String dataid) {
    this.dataid = dataid == null ? null : dataid.trim();
  }

  public String getInterfType() {
    return interfType;
  }

  public void setInterfType(String interfType) {
    this.interfType = interfType == null ? null : interfType.trim();
  }

  public String getFireName() {
    return fireName;
  }

  public void setFireName(String fireName) {
    this.fireName = fireName == null ? null : fireName.trim();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId == null ? null : workflowId.trim();
  }

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode == null ? null : orgCode.trim();
  }

  public String getDeptCode() {
    return deptCode;
  }

  public void setDeptCode(String deptCode) {
    this.deptCode = deptCode == null ? null : deptCode.trim();
  }

  public String getCreateDept() {
    return createDept;
  }

  public void setCreateDept(String createDept) {
    this.createDept = createDept == null ? null : createDept.trim();
  }

  public String getOrgData() {
    return orgData;
  }

  public void setOrgData(String orgData) {
    this.orgData = orgData == null ? null : orgData.trim();
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg == null ? null : errMsg.trim();
  }
  
  public String getCheckResult() {
    return this.checkResult;
  }

  public void setCheckResult(String checkResult) {
    this.checkResult = checkResult == null ? null : checkResult.trim();
  }
}