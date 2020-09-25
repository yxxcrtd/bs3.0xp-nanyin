package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbSysBatchLog extends ChbSysBatchLogKey {
    /**
     * 执行状态
     */
    private String taskStatus;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 业务起始日期
     */
    private Date startDate;

    /**
     * 业务截止日期
     */
    private Date endDate;

    /**
     * 任务参数
     */
    private String taskPara;

    /**
     * 执行结果
     */
    private String taskResult;

    /**
     * 提交任务的操作员
     */
    private String taskAssign;

    /**
     * 启动模式
     */
    private String startModule;

    /**
     * 优先级
     */
    private String gradeType;

    /**
     * 任务创建时间
     */
    private Date taskCreateTime;

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
     * 批处理代码
     */
    private String batchId;

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
     * 取得 结束时间
     * @return 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 取得 业务起始日期
     * @return 业务起始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 业务起始日期
     * @param startDate 业务起始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 业务截止日期
     * @return 业务截止日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 业务截止日期
     * @param endDate 业务截止日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 任务参数
     * @return 任务参数
     */
    public String getTaskPara() {
        return taskPara;
    }

    /**
     * 设置 任务参数
     * @param taskPara 任务参数
     */
    public void setTaskPara(String taskPara) {
        this.taskPara = taskPara == null ? null : taskPara.trim();
    }

    /**
     * 取得 执行结果
     * @return 执行结果
     */
    public String getTaskResult() {
        return taskResult;
    }

    /**
     * 设置 执行结果
     * @param taskResult 执行结果
     */
    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult == null ? null : taskResult.trim();
    }

    /**
     * 取得 提交任务的操作员
     * @return 提交任务的操作员
     */
    public String getTaskAssign() {
        return taskAssign;
    }

    /**
     * 设置 提交任务的操作员
     * @param taskAssign 提交任务的操作员
     */
    public void setTaskAssign(String taskAssign) {
        this.taskAssign = taskAssign == null ? null : taskAssign.trim();
    }

    /**
     * 取得 启动模式
     * @return 启动模式
     */
    public String getStartModule() {
        return startModule;
    }

    /**
     * 设置 启动模式
     * @param startModule 启动模式
     */
    public void setStartModule(String startModule) {
        this.startModule = startModule == null ? null : startModule.trim();
    }

    /**
     * 取得 优先级
     * @return 优先级
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 设置 优先级
     * @param gradeType 优先级
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    /**
     * 取得 任务创建时间
     * @return 任务创建时间
     */
    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    /**
     * 设置 任务创建时间
     * @param taskCreateTime 任务创建时间
     */
    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
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
}