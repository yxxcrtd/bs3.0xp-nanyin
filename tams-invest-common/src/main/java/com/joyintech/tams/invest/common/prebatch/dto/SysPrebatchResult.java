package com.joyintech.tams.invest.common.prebatch.dto;

import java.util.Date;

/**
 * 描述:SYS_PREBATCH_RESULT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-04-16
 */
public class SysPrebatchResult {
    /**
     * null
     */
    private String seqNo;

    /**
     * 批处理执行id
     */
    private String batchSeqNo;

    /**
     * taskid即检查项
     */
    private String taskId;

    /**
     * Service别名
     */
    private String beanName;

    /**
     * 检查日
     */
    private Date executeDate;

    /**
     * 检查状态
     */
    private String taskStatus;

    /**
     * 启动模式
     */
    private String startMode;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 机构代码
     */
    private String institutionCode;

    /**
     * 是否卡批00:不卡  01:卡批
     */
    private String stopFlag;

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
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * null
     * @return SEQ_NO null
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * null
     * @param seqNo null
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 批处理执行id
     * @return BATCH_SEQ_NO 批处理执行id
     */
    public String getBatchSeqNo() {
        return batchSeqNo;
    }

    /**
     * 批处理执行id
     * @param batchSeqNo 批处理执行id
     */
    public void setBatchSeqNo(String batchSeqNo) {
        this.batchSeqNo = batchSeqNo == null ? null : batchSeqNo.trim();
    }

    /**
     * taskid即检查项
     * @return TASK_ID taskid即检查项
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * taskid即检查项
     * @param taskId taskid即检查项
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * Service别名
     * @return BEAN_NAME Service别名
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * Service别名
     * @param beanName Service别名
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    /**
     * 检查日
     * @return EXECUTE_DATE 检查日
     */
    public Date getExecuteDate() {
        return executeDate;
    }

    /**
     * 检查日
     * @param executeDate 检查日
     */
    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    /**
     * 检查状态
     * @return TASK_STATUS 检查状态
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 检查状态
     * @param taskStatus 检查状态
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * 启动模式
     * @return START_MODE 启动模式
     */
    public String getStartMode() {
        return startMode;
    }

    /**
     * 启动模式
     * @param startMode 启动模式
     */
    public void setStartMode(String startMode) {
        this.startMode = startMode == null ? null : startMode.trim();
    }

    /**
     * 开始时间
     * @return START_TIME 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return END_TIME 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 机构代码
     * @return INSTITUTION_CODE 机构代码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 机构代码
     * @param institutionCode 机构代码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
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


    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }
}