package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class RskOutValtblImportHis extends RskOutValtblImportHisKey {

    /**文件路径*/
    private String filePath  ;
    /**委外组合代码*/
    private String portfolioId  ;
    /**导入日期*/
    private Date impdate  ;
    /**处理状态：R处理中、T正常结束、F异常结束*/
    private String importType ;
    /**异常代码*/
    private String errorCode ;
    /**开始时间*/
    private Date startTime ;
    /**结束时间*/
    private Date endTime ;
    /**定时任务跑估值穿透状态*/
    private String timedTaskStatus;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Date getImpdate() {
        return impdate;
    }

    public void setImpdate(Date impdate) {
        this.impdate = impdate;
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

    public String getTimedTaskStatus() {
        return timedTaskStatus;
    }

    public void setTimedTaskStatus(String timedTaskStatus) {
        this.timedTaskStatus = timedTaskStatus;
    }
}