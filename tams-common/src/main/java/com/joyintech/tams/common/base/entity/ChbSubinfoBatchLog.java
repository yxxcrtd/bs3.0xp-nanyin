package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSubinfoBatchLog extends BaseEntity {
    /**
     * 批处理批次
     */
    private String batchExeId;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 业务确认日期
     */
    private String busiConfirmDate;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 文件类别
     */
    private String fileType;

    /**
     * 压缩文件编号
     */
    private String compresFileId;

    /**
     * 压缩文件名
     */
    private String compresFileName;

    /**
     * 压缩文件路径
     */
    private String compresFilePath;

    /**
     * 文件个数
     */
    private Long fileCount;

    /**
     * 总记录数
     */
    private Long recordCount;

    /**
     * 处理状态
     */
    private String dealStatus;

    /**
     * 处理信息
     */
    private String dealMessage;

    /**
     * 耗时（s）
     */
    private Long consumeTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 生成条件
     */
    private String genCondition;

    /**
     * 取得 批处理批次
     * @return 批处理批次
     */
    public String getBatchExeId() {
        return batchExeId;
    }

    /**
     * 设置 批处理批次
     * @param batchExeId 批处理批次
     */
    public void setBatchExeId(String batchExeId) {
        this.batchExeId = batchExeId == null ? null : batchExeId.trim();
    }

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 业务确认日期
     * @return 业务确认日期
     */
    public String getBusiConfirmDate() {
        return busiConfirmDate;
    }

    /**
     * 设置 业务确认日期
     * @param busiConfirmDate 业务确认日期
     */
    public void setBusiConfirmDate(String busiConfirmDate) {
        this.busiConfirmDate = busiConfirmDate == null ? null : busiConfirmDate.trim();
    }

    /**
     * 取得 操作人
     * @return 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置 操作人
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * 取得 文件类别
     * @return 文件类别
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 文件类别
     * @param fileType 文件类别
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * 取得 压缩文件编号
     * @return 压缩文件编号
     */
    public String getCompresFileId() {
        return compresFileId;
    }

    /**
     * 设置 压缩文件编号
     * @param compresFileId 压缩文件编号
     */
    public void setCompresFileId(String compresFileId) {
        this.compresFileId = compresFileId == null ? null : compresFileId.trim();
    }

    /**
     * 取得 压缩文件名
     * @return 压缩文件名
     */
    public String getCompresFileName() {
        return compresFileName;
    }

    /**
     * 设置 压缩文件名
     * @param compresFileName 压缩文件名
     */
    public void setCompresFileName(String compresFileName) {
        this.compresFileName = compresFileName == null ? null : compresFileName.trim();
    }

    /**
     * 取得 压缩文件路径
     * @return 压缩文件路径
     */
    public String getCompresFilePath() {
        return compresFilePath;
    }

    /**
     * 设置 压缩文件路径
     * @param compresFilePath 压缩文件路径
     */
    public void setCompresFilePath(String compresFilePath) {
        this.compresFilePath = compresFilePath == null ? null : compresFilePath.trim();
    }

    /**
     * 取得 文件个数
     * @return 文件个数
     */
    public Long getFileCount() {
        return fileCount;
    }

    /**
     * 设置 文件个数
     * @param fileCount 文件个数
     */
    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }

    /**
     * 取得 总记录数
     * @return 总记录数
     */
    public Long getRecordCount() {
        return recordCount;
    }

    /**
     * 设置 总记录数
     * @param recordCount 总记录数
     */
    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * 取得 处理状态
     * @return 处理状态
     */
    public String getDealStatus() {
        return dealStatus;
    }

    /**
     * 设置 处理状态
     * @param dealStatus 处理状态
     */
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus == null ? null : dealStatus.trim();
    }

    /**
     * 取得 处理信息
     * @return 处理信息
     */
    public String getDealMessage() {
        return dealMessage;
    }

    /**
     * 设置 处理信息
     * @param dealMessage 处理信息
     */
    public void setDealMessage(String dealMessage) {
        this.dealMessage = dealMessage == null ? null : dealMessage.trim();
    }

    /**
     * 取得 耗时（s）
     * @return 耗时（s）
     */
    public Long getConsumeTime() {
        return consumeTime;
    }

    /**
     * 设置 耗时（s）
     * @param consumeTime 耗时（s）
     */
    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }

    /**
     * 取得 开始时间
     * @return 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 开始时间
     * @param startTime 开始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 取得 生成条件
     * @return 生成条件
     */
    public String getGenCondition() {
        return genCondition;
    }

    /**
     * 设置 生成条件
     * @param genCondition 生成条件
     */
    public void setGenCondition(String genCondition) {
        this.genCondition = genCondition == null ? null : genCondition.trim();
    }
}