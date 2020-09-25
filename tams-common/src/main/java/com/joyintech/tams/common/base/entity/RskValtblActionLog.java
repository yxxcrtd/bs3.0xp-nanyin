package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RskValtblActionLog extends BaseEntity {
    /**
     * 日志编号
     */
    private String id;

    /**
     * 估值日期

     */
    private Date valDate;

    /**
     * 组合代码

     */
    private String ptlId;

    /**
     * 操作方式(MANUAL:手工,AUTO:自动)
     */
    private String opMode;

    /**
     * 日志级别(INFO:提示,ERROR:错误)

     */
    private String logLvl;

    /**
     * 日志时间

     */
    private Date logTime;

    /**
     * 日志消息

     */
    private String message;

    /**
     * 取得 日志编号
     * @return 日志编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 日志编号
     * @param id 日志编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 估值日期

     * @return 估值日期

     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日期

     * @param valDate 估值日期

     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 组合代码

     * @return 组合代码

     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 组合代码

     * @param ptlId 组合代码

     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId == null ? null : ptlId.trim();
    }

    /**
     * 取得 操作方式(MANUAL:手工,AUTO:自动)
     * @return 操作方式(MANUAL:手工,AUTO:自动)
     */
    public String getOpMode() {
        return opMode;
    }

    /**
     * 设置 操作方式(MANUAL:手工,AUTO:自动)
     * @param opMode 操作方式(MANUAL:手工,AUTO:自动)
     */
    public void setOpMode(String opMode) {
        this.opMode = opMode == null ? null : opMode.trim();
    }

    /**
     * 取得 日志级别(INFO:提示,ERROR:错误)

     * @return 日志级别(INFO:提示,ERROR:错误)

     */
    public String getLogLvl() {
        return logLvl;
    }

    /**
     * 设置 日志级别(INFO:提示,ERROR:错误)

     * @param logLvl 日志级别(INFO:提示,ERROR:错误)

     */
    public void setLogLvl(String logLvl) {
        this.logLvl = logLvl == null ? null : logLvl.trim();
    }

    /**
     * 取得 日志时间

     * @return 日志时间

     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * 设置 日志时间

     * @param logTime 日志时间

     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * 取得 日志消息

     * @return 日志消息

     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置 日志消息

     * @param message 日志消息

     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}