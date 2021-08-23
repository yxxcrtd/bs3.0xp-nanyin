package com.joyintech.tams.invest.prebatch.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

public class SysPrebatchResultDetail001Dto extends BaseEntity {

    /**
     * 主键id
     */
    private String seqNo;

    /**
     * 批处理结果id
     */
    private String resultSeqNo;

    /**
     * 批处理执行id
     */
    private String batchSeqNo;

    /**
     * taskid即检查项
     */
    private String taskId;

    /**
     * Finprod_id
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 组合id
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 业务代码
     */
    private String tradeId;

    /**
     * 业务totleid
     */
    private String totleTradeId;

    /**
     * 金额
     */
    private BigDecimal tradeAmt;

    /**
     * 启动用户
     */
    private String launchUser;

    /**
     * 当前处理人
     */
    private String nextUser;

    /**
     * 备注
     */
    private String remark;

    private String taskIdStr;


    public String getTaskIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.检查项, this.taskId);
    }

    public void setTaskIdStr(String taskIdStr) {
        this.taskIdStr = taskIdStr;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getResultSeqNo() {
        return resultSeqNo;
    }

    public void setResultSeqNo(String resultSeqNo) {
        this.resultSeqNo = resultSeqNo;
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

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getLaunchUser() {
        return launchUser;
    }

    public void setLaunchUser(String launchUser) {
        this.launchUser = launchUser;
    }

    public String getNextUser() {
        return nextUser;
    }

    public void setNextUser(String nextUser) {
        this.nextUser = nextUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
