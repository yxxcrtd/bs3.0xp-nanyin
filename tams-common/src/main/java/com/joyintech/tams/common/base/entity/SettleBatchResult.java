package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SettleBatchResult extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 执行日期
     */
    private Date dealDate;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 证券品种
     */
    private String secType;

    /**
     * 批处理执行结果（NOR：正常结束、EXP：异常结束、WARN：警告）
     */
    private String taskResult;

    /**
     * null
     */
    private Date startTime;

    /**
     * null
     */
    private Date endTime;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 执行日期
     * @return 执行日期
     */
    public Date getDealDate() {
        return dealDate;
    }

    /**
     * 设置 执行日期
     * @param dealDate 执行日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 证券品种
     * @return 证券品种
     */
    public String getSecType() {
        return secType;
    }

    /**
     * 设置 证券品种
     * @param secType 证券品种
     */
    public void setSecType(String secType) {
        this.secType = secType == null ? null : secType.trim();
    }

    /**
     * 取得 批处理执行结果（NOR：正常结束、EXP：异常结束、WARN：警告）
     * @return 批处理执行结果（NOR：正常结束、EXP：异常结束、WARN：警告）
     */
    public String getTaskResult() {
        return taskResult;
    }

    /**
     * 设置 批处理执行结果（NOR：正常结束、EXP：异常结束、WARN：警告）
     * @param taskResult 批处理执行结果（NOR：正常结束、EXP：异常结束、WARN：警告）
     */
    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult == null ? null : taskResult.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 null
     * @param startTime null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 null
     * @param endTime null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}