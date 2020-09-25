package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RskIsupLog extends RskIsupLogKey {
    /**
     * 组合(包)代码
     */
    private String portfolioId;

    /**
     * 监督指标编号
     */
    private String indId;

    /**
     * 日志级别
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
     * 取得 组合(包)代码
     * @return 组合(包)代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合(包)代码
     * @param portfolioId 组合(包)代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 监督指标编号
     * @return 监督指标编号
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 监督指标编号
     * @param indId 监督指标编号
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }

    /**
     * 取得 日志级别
     * @return 日志级别
     */
    public String getLogLvl() {
        return logLvl;
    }

    /**
     * 设置 日志级别
     * @param logLvl 日志级别
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