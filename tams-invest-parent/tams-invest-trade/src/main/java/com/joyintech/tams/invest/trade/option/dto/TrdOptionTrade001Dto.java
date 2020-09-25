package com.joyintech.tams.invest.trade.option.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 期权交易查询dto
 */
public class TrdOptionTrade001Dto extends BaseEntity {
    /**
     * 委托代码
     */
    private String entrustId;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 合约标的
     */
    private String targetId;
    /**
     * 标的名称
     */
    private String targetName;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 交易方向
     */
    private String psText;
    /**
     * 合约类型
     */
    private String contractType;
    /**
     * 合约类型
     */
    private String contractTypeText;
    /**
     * 行权方式
     */
    private String exerciseType;
    /**
     * 行权方式
     */
    private String exerciseTypeText;
    /**
     * 有效状态
     */
    private String validStatus;
    /**
     * 有效状态
     */
    private String validStatusText;
    /**
     * 交易日
     */
    private Date tradeDate;
    /**
     * 交易日(导出)
     */
    private String tradeDateText;
    /**
     * 交易编号
     */
    private String totleTradeId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 期限
     */
    private String term;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 起息日(导出)
     */
    private String vdateText;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 到期日(导出)
     */
    private String mdateText;
    /**
     * 备注
     */
    private String remark;
    /**
     * 工作流代码
     */
    private String workflowId;
    /**
     * 备注
     */
    private String workflowStatus;


    /**
     * 获取 委托代码
     *
     * @return entrustId 委托代码
     */
    public String getEntrustId() {
        return this.entrustId;
    }

    /**
     * 设置 委托代码
     *
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 合约标的
     *
     * @return targetId 合约标的
     */
    public String getTargetId() {
        return this.targetId;
    }

    /**
     * 设置 合约标的
     *
     * @param targetId 合约标的
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取 标的名称
     *
     * @return targetName 标的名称
     */
    public String getTargetName() {
        return this.targetName;
    }

    /**
     * 设置 标的名称
     *
     * @param targetName 标的名称
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * 获取 交易方向
     *
     * @return ps 交易方向
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 交易方向
     *
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 合约类型
     *
     * @return contractType 合约类型
     */
    public String getContractType() {
        return this.contractType;
    }

    /**
     * 设置 合约类型
     *
     * @param contractType 合约类型
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * 获取 行权方式
     *
     * @return exerciseType 行权方式
     */
    public String getExerciseType() {
        return this.exerciseType;
    }

    /**
     * 设置 行权方式
     *
     * @param exerciseType 行权方式
     */
    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    /**
     * 获取 交易日
     *
     * @return tradeDate 交易日
     */
    public Date getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日
     *
     * @param tradeDate 交易日
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 交易编号
     *
     * @return totleTradeId 交易编号
     */
    public String getTotleTradeId() {
        return this.totleTradeId;
    }

    /**
     * 设置 交易编号
     *
     * @param totleTradeId 交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 组合名称
     *
     * @return portfolioName 组合名称
     */
    public String getPortfolioName() {
        return this.portfolioName;
    }

    /**
     * 设置 组合名称
     *
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 数量
     *
     * @return amount 数量
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置 数量
     *
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取 期限
     *
     * @return term 期限
     */
    public String getTerm() {
        return this.term;
    }

    /**
     * 设置 期限
     *
     * @param term 期限
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 获取 起息日
     *
     * @return vdate 起息日
     */
    public Date getVdate() {
        return this.vdate;
    }

    /**
     * 设置 起息日
     *
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 到期日
     *
     * @return mdate 到期日
     */
    public Date getMdate() {
        return this.mdate;
    }

    /**
     * 设置 到期日
     *
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 备注
     *
     * @return remark 备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 有效状态
     *
     * @return validStatus 有效状态
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * 设置 有效状态
     *
     * @param validStatus 有效状态
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 获取 有效状态
     *
     * @return validStatusText 有效状态
     */
    public String getValidStatusText() {
        validStatusText = SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, validStatus);
        return this.validStatusText;
    }

    /**
     * 设置 有效状态
     *
     * @param validStatusText 有效状态
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    /**
     * 获取 交易方向
     *
     * @return psText 交易方向
     */
    public String getPsText() {
        psText = SysDataUtil.getNameFromDataDict(DictCode.交易方向, ps);
        return this.psText;
    }

    /**
     * 设置 交易方向
     *
     * @param psText 交易方向
     */
    public void setPsText(String psText) {
        this.psText = psText;
    }

    /**
     * 获取 行权方式
     *
     * @return exerciseTypeText 行权方式
     */
    public String getExerciseTypeText() {
        exerciseTypeText = SysDataUtil.getNameFromDataDict(DictCode.行权方式, exerciseType);
        return this.exerciseTypeText;
    }

    /**
     * 设置 行权方式
     *
     * @param exerciseTypeText 行权方式
     */
    public void setExerciseTypeText(String exerciseTypeText) {
        this.exerciseTypeText = exerciseTypeText;
    }

    /**
     * 获取 合约类型
     *
     * @return contractTypeText 合约类型
     */
    public String getContractTypeText() {
        contractTypeText = SysDataUtil.getNameFromDataDict(DictCode.结构类型, contractType);
        return this.contractTypeText;
    }

    /**
     * 设置 合约类型
     *
     * @param contractTypeText 合约类型
     */
    public void setContractTypeText(String contractTypeText) {
        this.contractTypeText = contractTypeText;
    }

    /**
     * 获取 工作流代码
     *
     * @return workflowId 工作流代码
     */
    public String getWorkflowId() {
        return this.workflowId;
    }

    /**
     * 设置 工作流代码
     *
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 备注
     *
     * @return workflowStatus 备注
     */
    public String getWorkflowStatus() {
        return this.workflowStatus;
    }

    /**
     * 设置 备注
     *
     * @param workflowStatus 备注
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * 获取 起息日(导出)
     *
     * @return vdateText 起息日(导出)
     */
    public String getVdateText() {
        vdateText = DateUtil.format(getVdate());
        return vdateText;
    }

    /**
     * 设置 起息日(导出)
     *
     * @param vdateText 起息日(导出)
     */
    public void setVdateText(String vdateText) {
        this.vdateText = vdateText;
    }

    /**
     * 获取 到期日(导出)
     *
     * @return mdateText 到期日(导出)
     */
    public String getMdateText() {
        mdateText = DateUtil.format(getMdate());
        return mdateText;
    }

    /**
     * 设置 到期日(导出)
     *
     * @param mdateText 到期日(导出)
     */
    public void setMdateText(String mdateText) {
        this.mdateText = mdateText;
    }

    /**
     * 获取 交易日(导出)
     *
     * @return tradeDateText 交易日(导出)
     */
    public String getTradeDateText() {
        tradeDateText = DateUtil.format(getTradeDate());
        return tradeDateText;
    }

    /**
     * 设置 交易日(导出)
     *
     * @param tradeDateText 交易日(导出)
     */
    public void setTradeDateText(String tradeDateText) {
        this.tradeDateText = tradeDateText;
    }
}
