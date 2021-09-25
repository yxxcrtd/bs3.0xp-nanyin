/*
 * 文件名：ChbEndSubDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年9月24日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.component.svrreport.chb.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.component.svrreport.chb.contant.annotate.RateFormat;


/**
 * 产品终止登记
 * @description 
 * @author 王波
 * @version 1.0 2020年9月24日
 * @see ChbPrdEndSubDto
 * @since 1.0
 */
public class ChbPrdEndSubDto {
	 /**
     * 理财产品代码
     */
    private String prodId;
    /**
     * 报送流水号
     */
    private String sumitSeq;
    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 实际终止日期
     */
    private Date mdate;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 银行实际实现收入
     */
    private BigDecimal profit;
    /**
     * 反馈状态
     */

    private String submitFeedbackStatus;
    /**
     * 兑付客户收益
     */
    private BigDecimal customProfit;

    /**
     * 兑付客户总金额
     */
    private BigDecimal customAmt;

    /**
     * 兑付总份额
     */
    private BigDecimal customQty;

    /**
     * 客户实际年化收益率
     */
    @RateFormat
    private BigDecimal cusYearYield;

    /**
     * 产品实际年化收益率
     */
    @RateFormat
    private BigDecimal prdYearYield;

    /**
     * 本机构托管费
     */
    private BigDecimal custFeeIn;

    /**
     * 本机构管理费
     */
    private BigDecimal manFeeIn;

    /**
     * 本机构销售手续费
     */
    private BigDecimal couFeeIn;

    /**
     * 本机构其他产品费用
     */
    private BigDecimal otherFeeIn;

    /**
     * 其他机构托管费
     */
    private BigDecimal custFeeOut;

    /**
     * 其他机构管理费
     */
    private BigDecimal manFeeOut;

    /**
     * 其他机构销售手续费
     */
    private BigDecimal couFeeOut;

    /**
     * 投资顾问费用
     */
    private BigDecimal advFeeOut;

    /**
     * 其他机构其他产品费用
     */
    private BigDecimal otherFeeOut;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;
    
    /**
     * 登记序列
     */
    private String registSeqno;

    /**
     * 取得 登记序列
     * @return 登记序列
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 登记序列
     * @param registSeqno 登记序列
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 理财产品代码
     * @return 理财产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品代码
     * @param prodId 理财产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
    }

    /**
     * 取得 实际终止日期
     * @return 实际终止日期
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 实际终止日期
     * @param mdate 实际终止日期
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 银行实际实现收入
     * @return 银行实际实现收入
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 设置 银行实际实现收入
     * @param profit 银行实际实现收入
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 取得 兑付客户收益
     * @return 兑付客户收益
     */
    public BigDecimal getCustomProfit() {
        return customProfit;
    }

    /**
     * 设置 兑付客户收益
     * @param customProfit 兑付客户收益
     */
    public void setCustomProfit(BigDecimal customProfit) {
        this.customProfit = customProfit;
    }

    /**
     * 取得 兑付客户总金额
     * @return 兑付客户总金额
     */
    public BigDecimal getCustomAmt() {
        return customAmt;
    }

    /**
     * 设置 兑付客户总金额
     * @param customAmt 兑付客户总金额
     */
    public void setCustomAmt(BigDecimal customAmt) {
        this.customAmt = customAmt;
    }

    /**
     * 取得 兑付总份额
     * @return 兑付总份额
     */
    public BigDecimal getCustomQty() {
        return customQty;
    }

    /**
     * 设置 兑付总份额
     * @param customQty 兑付总份额
     */
    public void setCustomQty(BigDecimal customQty) {
        this.customQty = customQty;
    }

    /**
     * 取得 客户实际年化收益率
     * @return 客户实际年化收益率
     */
    public BigDecimal getCusYearYield() {
        return cusYearYield;
    }

    /**
     * 设置 客户实际年化收益率
     * @param cusYearYield 客户实际年化收益率
     */
    public void setCusYearYield(BigDecimal cusYearYield) {
        this.cusYearYield = cusYearYield;
    }

    /**
     * 取得 产品实际年化收益率
     * @return 产品实际年化收益率
     */
    public BigDecimal getPrdYearYield() {
        return prdYearYield;
    }

    /**
     * 设置 产品实际年化收益率
     * @param prdYearYield 产品实际年化收益率
     */
    public void setPrdYearYield(BigDecimal prdYearYield) {
        this.prdYearYield = prdYearYield;
    }

    /**
     * 取得 本机构托管费
     * @return 本机构托管费
     */
    public BigDecimal getCustFeeIn() {
        return custFeeIn;
    }

    /**
     * 设置 本机构托管费
     * @param custFeeIn 本机构托管费
     */
    public void setCustFeeIn(BigDecimal custFeeIn) {
        this.custFeeIn = custFeeIn;
    }

    /**
     * 取得 本机构管理费
     * @return 本机构管理费
     */
    public BigDecimal getManFeeIn() {
        return manFeeIn;
    }

    /**
     * 设置 本机构管理费
     * @param manFeeIn 本机构管理费
     */
    public void setManFeeIn(BigDecimal manFeeIn) {
        this.manFeeIn = manFeeIn;
    }

    /**
     * 取得 本机构销售手续费
     * @return 本机构销售手续费
     */
    public BigDecimal getCouFeeIn() {
        return couFeeIn;
    }

    /**
     * 设置 本机构销售手续费
     * @param couFeeIn 本机构销售手续费
     */
    public void setCouFeeIn(BigDecimal couFeeIn) {
        this.couFeeIn = couFeeIn;
    }

    /**
     * 取得 本机构其他产品费用
     * @return 本机构其他产品费用
     */
    public BigDecimal getOtherFeeIn() {
        return otherFeeIn;
    }

    /**
     * 设置 本机构其他产品费用
     * @param otherFeeIn 本机构其他产品费用
     */
    public void setOtherFeeIn(BigDecimal otherFeeIn) {
        this.otherFeeIn = otherFeeIn;
    }

    /**
     * 取得 其他机构托管费
     * @return 其他机构托管费
     */
    public BigDecimal getCustFeeOut() {
        return custFeeOut;
    }

    /**
     * 设置 其他机构托管费
     * @param custFeeOut 其他机构托管费
     */
    public void setCustFeeOut(BigDecimal custFeeOut) {
        this.custFeeOut = custFeeOut;
    }

    /**
     * 取得 其他机构管理费
     * @return 其他机构管理费
     */
    public BigDecimal getManFeeOut() {
        return manFeeOut;
    }

    /**
     * 设置 其他机构管理费
     * @param manFeeOut 其他机构管理费
     */
    public void setManFeeOut(BigDecimal manFeeOut) {
        this.manFeeOut = manFeeOut;
    }

    /**
     * 取得 其他机构销售手续费
     * @return 其他机构销售手续费
     */
    public BigDecimal getCouFeeOut() {
        return couFeeOut;
    }

    /**
     * 设置 其他机构销售手续费
     * @param couFeeOut 其他机构销售手续费
     */
    public void setCouFeeOut(BigDecimal couFeeOut) {
        this.couFeeOut = couFeeOut;
    }

    /**
     * 取得 投资顾问费用
     * @return 投资顾问费用
     */
    public BigDecimal getAdvFeeOut() {
        return advFeeOut;
    }

    /**
     * 设置 投资顾问费用
     * @param advFeeOut 投资顾问费用
     */
    public void setAdvFeeOut(BigDecimal advFeeOut) {
        this.advFeeOut = advFeeOut;
    }

    /**
     * 取得 其他机构其他产品费用
     * @return 其他机构其他产品费用
     */
    public BigDecimal getOtherFeeOut() {
        return otherFeeOut;
    }

    /**
     * 设置 其他机构其他产品费用
     * @param otherFeeOut 其他机构其他产品费用
     */
    public void setOtherFeeOut(BigDecimal otherFeeOut) {
        this.otherFeeOut = otherFeeOut;
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

	public String getSubmitFeedbackStatus() {
		return submitFeedbackStatus;
	}

	public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
		this.submitFeedbackStatus = submitFeedbackStatus;
	}

	public String getSumitSeq() {
		return sumitSeq;
	}

	public void setSumitSeq(String sumitSeq) {
		this.sumitSeq = sumitSeq;
	}
}
