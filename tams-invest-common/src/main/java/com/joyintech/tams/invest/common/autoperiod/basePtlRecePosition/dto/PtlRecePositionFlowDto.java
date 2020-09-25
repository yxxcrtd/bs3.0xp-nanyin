package com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;

import java.math.BigDecimal;
import java.util.Date;

public class PtlRecePositionFlowDto extends PtlRecePositionFlow {
   
    /**
     * 前置交易编号
     */
    private String bTradeId;
	
	/**
     * 流水号
     */
    private String seqno;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 证券管理账户/通道代码，无通道无证券管理账户的存999999
     */
    private String secAcctId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 持仓类型，可用、实际、在途、质押、融入、融出
     */
    private String hodingType;

    /**
     * 发生日期
     */
    private Date cdate;

    /**
     * 应收款类型，还本结转，收息结转，还本不带息利息结转
     */
    private String recType;

    /**
     * 计划支付日期
     */
    private Date payDate;

    /**
     * 币种，交易或者指令对应的币种
     */
    private String ccy;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 录入方式
     */
    private String inputType;

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
     * 取得 证券管理账户/通道代码，无通道无证券管理账户的存999999
     * @return 证券管理账户/通道代码，无通道无证券管理账户的存999999
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 证券管理账户/通道代码，无通道无证券管理账户的存999999
     * @param secAcctId 证券管理账户/通道代码，无通道无证券管理账户的存999999
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId == null ? null : secAcctId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 持仓类型，可用、实际、在途、质押、融入、融出
     * @return 持仓类型，可用、实际、在途、质押、融入、融出
     */
    public String getHodingType() {
        return hodingType;
    }

    /**
     * 设置 持仓类型，可用、实际、在途、质押、融入、融出
     * @param hodingType 持仓类型，可用、实际、在途、质押、融入、融出
     */
    public void setHodingType(String hodingType) {
        this.hodingType = hodingType == null ? null : hodingType.trim();
    }

    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 发生日期
     * @param cdate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 应收款类型，还本结转，收息结转，还本不带息利息结转
     * @return 应收款类型，还本结转，收息结转，还本不带息利息结转
     */
    public String getRecType() {
        return recType;
    }

    /**
     * 设置 应收款类型，还本结转，收息结转，还本不带息利息结转
     * @param recType 应收款类型，还本结转，收息结转，还本不带息利息结转
     */
    public void setRecType(String recType) {
        this.recType = recType == null ? null : recType.trim();
    }

    /**
     * 取得 计划支付日期
     * @return 计划支付日期
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 计划支付日期
     * @param payDate 计划支付日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 币种，交易或者指令对应的币种
     * @return 币种，交易或者指令对应的币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种，交易或者指令对应的币种
     * @param ccy 币种，交易或者指令对应的币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 数量
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 母金融产品代码
     * @return 母金融产品代码
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 母金融产品代码
     * @param pFinprodId 母金融产品代码
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId == null ? null : pFinprodId.trim();
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

	public String getbTradeId() {
		return bTradeId;
	}

	public void setbTradeId(String bTradeId) {
		this.bTradeId = bTradeId;
	}
}