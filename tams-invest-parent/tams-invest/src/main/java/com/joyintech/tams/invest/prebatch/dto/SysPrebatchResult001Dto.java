package com.joyintech.tams.invest.prebatch.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.BatchDictCode;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

public class SysPrebatchResult001Dto  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String seqNo;
    private String batchSeqNo;
    private String taskId;
    private String beanName;
    private Date checkDate;
    private Date executeDate;
    private String taskStatus;
    private int detailCount;
    private String stopFlag;
    private String startMode;
    private Date startTime;
    private Date endTime;
    private String institutionCode;

    private Long processTime;
    private String startTimeStr;

    private String endTimeStr;

    private String taskStatusStr;

    private String taskIdStr;

    private String checkDateStr;

    private String executeDateStr;



    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getBatchSeqNo() {
        return batchSeqNo;
    }

    public void setBatchSeqNo(String batchSeqNo) {
        this.batchSeqNo = batchSeqNo;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public String getStartMode() {
        return startMode;
    }

    public void setStartMode(String startMode) {
        this.startMode = startMode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Long processTime) {
        this.processTime = processTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getTaskStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.批前检查_执行状态, this.taskStatus);
    }

    public void setTaskStatusStr(String taskStatusStr) {
        this.taskStatusStr = taskStatusStr;
    }

    public String getTaskIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.检查项, this.taskId);
    }

    public void setTaskIdStr(String taskIdStr) {
        this.taskIdStr = taskIdStr;
    }

    public String getCheckDateStr() {
        return checkDateStr;
    }

    public void setCheckDateStr(String checkDateStr) {
        this.checkDateStr = checkDateStr;
    }

    public String getExecuteDateStr() {
        return executeDateStr;
    }

    public void setExecuteDateStr(String executeDateStr) {
        this.executeDateStr = executeDateStr;
    }

    public int getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(int detailCount) {
        this.detailCount = detailCount;
    }
}
