package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokCheckLog extends BaseEntity {
    /**
     * 日志信息ID
     */
    private String seqNo;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日
     */
    private Date happenDate;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 检查日期
     */
    private Date checkDate;

    /**
     * 批检类型
     */
    private String checkType;

    /**
     * 日志信息
     */
    private String logInfo;

    /**
     * 取得 日志信息ID
     * @return 日志信息ID
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 日志信息ID
     * @param seqNo 日志信息ID
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
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
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 发生日
     * @return 发生日
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日
     * @param happenDate 发生日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 入账日
     * @return 入账日
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日
     * @param bookDate 入账日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 检查日期
     * @return 检查日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 设置 检查日期
     * @param checkDate 检查日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 取得 批检类型
     * @return 批检类型
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * 设置 批检类型
     * @param checkType 批检类型
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    /**
     * 取得 日志信息
     * @return 日志信息
     */
    public String getLogInfo() {
        return logInfo;
    }

    /**
     * 设置 日志信息
     * @param logInfo 日志信息
     */
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo == null ? null : logInfo.trim();
    }
}