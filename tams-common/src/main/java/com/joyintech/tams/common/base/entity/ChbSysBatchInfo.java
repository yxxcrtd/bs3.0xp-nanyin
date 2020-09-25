package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbSysBatchInfo extends BaseEntity {
    /**
     * 批处理代码
     */
    private String batchId;

    /**
     * Batch Service Bean的别名
     */
    private String beanName;

    /**
     * 任务代码
     */
    private String jobId;

    /**
     * 批处理开始日
     */
    private Date batchStartDate;

    /**
     * 批处理结束日
     */
    private Date batchEndDate;

    /**
     * 日志文件代码
     */
    private String logFileCode;

    /**
     * 执行状态
     */
    private String taskStatus;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 启动模式：手动，自动
     */
    private String startMode;

    /**
     * 其他参数1
     */
    private String otherParam1;

    /**
     * 其他参数1
     */
    private String otherParam2;

    /**
     * 其他参数1
     */
    private String otherParam3;

    /**
     * 其他参数1
     */
    private String otherParam4;

    /**
     * 其他参数1
     */
    private String otherParam5;

    /**
     * 其他参数1
     */
    private String otherParam6;

    /**
     * 其他参数1
     */
    private String otherParam7;

    /**
     * 其他参数1
     */
    private String otherParam8;

    /**
     * 其他参数1
     */
    private String otherParam9;

    /**
     * 其他参数1
     */
    private String otherParam10;

    /**
     * 产品代码列表
     */
    private String prdIdList;

    /**
     * 取得 批处理代码
     * @return 批处理代码
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 批处理代码
     * @param batchId 批处理代码
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * 取得 Batch Service Bean的别名
     * @return Batch Service Bean的别名
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置 Batch Service Bean的别名
     * @param beanName Batch Service Bean的别名
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * 设置 任务代码
     * @param jobId 任务代码
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * 取得 批处理开始日
     * @return 批处理开始日
     */
    public Date getBatchStartDate() {
        return batchStartDate;
    }

    /**
     * 设置 批处理开始日
     * @param batchStartDate 批处理开始日
     */
    public void setBatchStartDate(Date batchStartDate) {
        this.batchStartDate = batchStartDate;
    }

    /**
     * 取得 批处理结束日
     * @return 批处理结束日
     */
    public Date getBatchEndDate() {
        return batchEndDate;
    }

    /**
     * 设置 批处理结束日
     * @param batchEndDate 批处理结束日
     */
    public void setBatchEndDate(Date batchEndDate) {
        this.batchEndDate = batchEndDate;
    }

    /**
     * 取得 日志文件代码
     * @return 日志文件代码
     */
    public String getLogFileCode() {
        return logFileCode;
    }

    /**
     * 设置 日志文件代码
     * @param logFileCode 日志文件代码
     */
    public void setLogFileCode(String logFileCode) {
        this.logFileCode = logFileCode == null ? null : logFileCode.trim();
    }

    /**
     * 取得 执行状态
     * @return 执行状态
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置 执行状态
     * @param taskStatus 执行状态
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * 取得 操作员
     * @return 操作员
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置 操作员
     * @param operator 操作员
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * 取得 启动模式：手动，自动
     * @return 启动模式：手动，自动
     */
    public String getStartMode() {
        return startMode;
    }

    /**
     * 设置 启动模式：手动，自动
     * @param startMode 启动模式：手动，自动
     */
    public void setStartMode(String startMode) {
        this.startMode = startMode == null ? null : startMode.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam1() {
        return otherParam1;
    }

    /**
     * 设置 其他参数1
     * @param otherParam1 其他参数1
     */
    public void setOtherParam1(String otherParam1) {
        this.otherParam1 = otherParam1 == null ? null : otherParam1.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam2() {
        return otherParam2;
    }

    /**
     * 设置 其他参数1
     * @param otherParam2 其他参数1
     */
    public void setOtherParam2(String otherParam2) {
        this.otherParam2 = otherParam2 == null ? null : otherParam2.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam3() {
        return otherParam3;
    }

    /**
     * 设置 其他参数1
     * @param otherParam3 其他参数1
     */
    public void setOtherParam3(String otherParam3) {
        this.otherParam3 = otherParam3 == null ? null : otherParam3.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam4() {
        return otherParam4;
    }

    /**
     * 设置 其他参数1
     * @param otherParam4 其他参数1
     */
    public void setOtherParam4(String otherParam4) {
        this.otherParam4 = otherParam4 == null ? null : otherParam4.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam5() {
        return otherParam5;
    }

    /**
     * 设置 其他参数1
     * @param otherParam5 其他参数1
     */
    public void setOtherParam5(String otherParam5) {
        this.otherParam5 = otherParam5 == null ? null : otherParam5.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam6() {
        return otherParam6;
    }

    /**
     * 设置 其他参数1
     * @param otherParam6 其他参数1
     */
    public void setOtherParam6(String otherParam6) {
        this.otherParam6 = otherParam6 == null ? null : otherParam6.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam7() {
        return otherParam7;
    }

    /**
     * 设置 其他参数1
     * @param otherParam7 其他参数1
     */
    public void setOtherParam7(String otherParam7) {
        this.otherParam7 = otherParam7 == null ? null : otherParam7.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam8() {
        return otherParam8;
    }

    /**
     * 设置 其他参数1
     * @param otherParam8 其他参数1
     */
    public void setOtherParam8(String otherParam8) {
        this.otherParam8 = otherParam8 == null ? null : otherParam8.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam9() {
        return otherParam9;
    }

    /**
     * 设置 其他参数1
     * @param otherParam9 其他参数1
     */
    public void setOtherParam9(String otherParam9) {
        this.otherParam9 = otherParam9 == null ? null : otherParam9.trim();
    }

    /**
     * 取得 其他参数1
     * @return 其他参数1
     */
    public String getOtherParam10() {
        return otherParam10;
    }

    /**
     * 设置 其他参数1
     * @param otherParam10 其他参数1
     */
    public void setOtherParam10(String otherParam10) {
        this.otherParam10 = otherParam10 == null ? null : otherParam10.trim();
    }

    /**
     * 取得 产品代码列表
     * @return 产品代码列表
     */
    public String getPrdIdList() {
        return prdIdList;
    }

    /**
     * 设置 产品代码列表
     * @param prdIdList 产品代码列表
     */
    public void setPrdIdList(String prdIdList) {
        this.prdIdList = prdIdList == null ? null : prdIdList.trim();
    }
}