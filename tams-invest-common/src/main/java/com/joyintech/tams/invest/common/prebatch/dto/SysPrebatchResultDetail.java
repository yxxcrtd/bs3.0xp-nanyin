package com.joyintech.tams.invest.common.prebatch.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:SYS_PREBATCH_RESULT_DETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-04-16
 */
public class SysPrebatchResultDetail {
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

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 主键id
     * @return SEQ_NO 主键id
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 主键id
     * @param seqNo 主键id
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 批处理结果id
     * @return RESULT_SEQ_NO 批处理结果id
     */
    public String getResultSeqNo() {
        return resultSeqNo;
    }

    /**
     * 批处理结果id
     * @param resultSeqNo 批处理结果id
     */
    public void setResultSeqNo(String resultSeqNo) {
        this.resultSeqNo = resultSeqNo == null ? null : resultSeqNo.trim();
    }

    /**
     * 批处理执行id
     * @return BATCH_SEQ_NO 批处理执行id
     */
    public String getBatchSeqNo() {
        return batchSeqNo;
    }

    /**
     * 批处理执行id
     * @param batchSeqNo 批处理执行id
     */
    public void setBatchSeqNo(String batchSeqNo) {
        this.batchSeqNo = batchSeqNo == null ? null : batchSeqNo.trim();
    }

    /**
     * taskid即检查项
     * @return TASK_ID taskid即检查项
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * taskid即检查项
     * @param taskId taskid即检查项
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * Finprod_id
     * @return FINPROD_ID Finprod_id
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * Finprod_id
     * @param finprodId Finprod_id
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 资产名称
     * @return FINPROD_NAME 资产名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 资产名称
     * @param finprodName 资产名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 组合id
     * @return PORTFOLIO_ID 组合id
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 组合id
     * @param portfolioId 组合id
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 组合名称
     * @return PORTFOLIO_NAME 组合名称
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 组合名称
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName == null ? null : portfolioName.trim();
    }

    /**
     * 业务代码
     * @return TRADE_ID 业务代码
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 业务代码
     * @param tradeId 业务代码
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 业务totleid
     * @return TOTLE_TRADE_ID 业务totleid
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 业务totleid
     * @param totleTradeId 业务totleid
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId == null ? null : totleTradeId.trim();
    }

    /**
     * 金额
     * @return TRADE_AMT 金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 金额
     * @param tradeAmt 金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 启动用户
     * @return LAUNCH_USER 启动用户
     */
    public String getLaunchUser() {
        return launchUser;
    }

    /**
     * 启动用户
     * @param launchUser 启动用户
     */
    public void setLaunchUser(String launchUser) {
        this.launchUser = launchUser == null ? null : launchUser.trim();
    }

    /**
     * 当前处理人
     * @return NEXT_USER 当前处理人
     */
    public String getNextUser() {
        return nextUser;
    }

    /**
     * 当前处理人
     * @param nextUser 当前处理人
     */
    public void setNextUser(String nextUser) {
        this.nextUser = nextUser == null ? null : nextUser.trim();
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}