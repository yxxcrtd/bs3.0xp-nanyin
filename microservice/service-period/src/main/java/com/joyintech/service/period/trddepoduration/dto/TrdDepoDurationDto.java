/*
 * 文件名：TrdDepoDurationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trddepoduration.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.invest.common.dto.period.trddepoduration.TrdDepoDurationCommonDto;

/**
 * 存款类存续期Dto
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年10月11日
 * @see TrdDepoDurationDto
 * @since 1.0
 */
public class TrdDepoDurationDto extends TrdDepoDurationCommonDto{
	/**
	 * mode
	 */
	private String mode;
    /**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 5674442731944624074L;
    /**
     * 金融产品交易汇总业务流程表
     */
    private WflTrdFinProductDealTotal wflTrdFinProductDealTotal;

    /**
     * 划款指令业务流程表
     */
    private List<WflStlPayOrders> wflStlPayOrdersList;

    /**
     * 金融产品交易汇总扩展业务流程表
     */
    private WflPaTrdFinDealTotal wflPaTrdFinDealTotal;

    /**
     * 金融产品交易业务流程表
     */
    private List<WflTrdProductDeal> wflTrdProductDealList;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrades;

    /**
     * 交易报送
     */
    private List<RepTrade> repTrades;
    /**
     * 调整后的计息截止日
     */
    private Date adjustDate;
    /**
     * 编辑类型
     */
    private String opType;
    /**
     * 发行机构代码
     */
    private String repIssuePartyId;
	/**
     * 操作类型
     */
    private String operateType;

    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 资金到账日
     */
    private Date tradeDate;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 交易编号
     */
    private String busiCode;


    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     *资产类型
     */
    private String assetType;
    /**
     * 证券托管户
     */
    private String secManageAcctId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 产品名称
     */
    private String finprodMarketName;

    /**
     * 计息开始日
     */
    private Date vdateProd;

    /**
     * 计息结束日
     */
    private Date mdateProd;
    /**
     * 券面总额
     */
    private BigDecimal qty;

    /**
     * 全价总额 理论本息总额
     */
    private BigDecimal fpriceAmt;

    /**
     * 实收本息总额
     */
    private BigDecimal tradeAmt;
    /**
     * 计息基础
     */
    private String basis;
    /**
     * 借款利率%
     */
    private BigDecimal loadRate;
    /**
     *资产推荐费率%
     */
    private BigDecimal yield;
    /**
     * 委托行
     */
    private String entruster;
    /**
     * 实收利息总额
     */
    private BigDecimal intAmt;

    /**
     * 还本金额
     */
    private BigDecimal prinAmt;
    /**
    *资产推荐费
    */
    private BigDecimal feeAmt;
    /**
     * 理论应计利息
     */
    private BigDecimal theoryInt;

    /**
     * 剩余本金
     */
    private BigDecimal remainPrinAmt;
    /**
     * 提前支取利率
     */
    private BigDecimal earlyWithdrawalRate;
    /**
     * 提前支取金额
     */
    private BigDecimal earlyWithdrawalAmt;
    /**
     * 现金流计划表.计息本金 单位本金,
     */
    private BigDecimal intPrinAmt;

    /**
     * 单位应计利息,
     */
    private BigDecimal unitIntAmt;

//    /**
//     * 支付日
//     */
//    private String payDate;

    /**
     * 现金流代码（多条时，以<code>,</code>分割）
     */
    private String cashId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易备注
     */
    private String remark;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 平安业务类型
     */
    private String paBussType;
	// 通道存续期 qiangchengxi start
	/**
	 * 通道
	 */
	private String chlId;

	/**
	 * 通道名称
	 */
	private String channelName;
	/**
	 * 子组合代码
	 */
	private String actPortfolioId;

	@Override
	public String getActPortfolioId() {
		return actPortfolioId;
	}

	@Override
	public void setActPortfolioId(String actPortfolioId) {
		this.actPortfolioId = actPortfolioId;
	}

	/**
	 * 获取 channelName
	 *
	 * @return channelName
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * 设置 channelName
	 *
	 * @param channelName channelName
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * 获取 chlId
	 *
	 * @return chlId
	 */
	@Override
	public String getChlId() {
		return chlId;
	}

	/**
	 * 设置 chlId
	 *
	 * @param chlId chlId
	 */
	@Override
	public void setChlId(String chlId) {
		this.chlId = chlId;
	}
	// 通道存续期 qiangchengxi end

	/**
	 * 获取 operateType
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * 设置 operateType
	 * @param operateType operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 获取 finprodId
	 * @return finprodId.
	 */
	public String getFinprodId() {
		return finprodId;
	}

	/**
	 * 设置 finprodId
	 * @param finprodId finprodId
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
	 * 获取 statusValid
	 * @return statusValid.
	 */
	public String getStatusValid() {
		return statusValid;
	}

	/**
	 * 设置 statusValid
	 * @param statusValid statusValid
	 */
	public void setStatusValid(String statusValid) {
		this.statusValid = statusValid;
	}

	/**
	 * 获取 busiCode
	 * @return busiCode.
	 */
	public String getBusiCode() {
		return busiCode;
	}

	/**
	 * 设置 busiCode
	 * @param busiCode busiCode
	 */
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}


	/**
	 * 获取 cashType
	 * @return cashType.
	 */
	public String getCashType() {
		return cashType;
	}

	/**
	 * 设置 cashType
	 * @param cashType cashType
	 */
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	/**
	 * 获取 portfolioId
	 * @return portfolioId.
	 */
	public String getPortfolioId() {
		return portfolioId;
	}

	/**
	 * 设置 portfolioId
	 * @param portfolioId portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * 获取 portfolioName
	 * @return portfolioName.
	 */
	public String getPortfolioName() {
		return portfolioName;
	}

	/**
	 * 设置 portfolioName
	 * @param portfolioName portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * 获取 assetType
	 * @return assetType.
	 */
	public String getAssetType() {
		return assetType;
	}

	/**
	 * 设置 assetType
	 * @param assetType assetType
	 */
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	/**
	 * 获取 secManageAcctId
	 * @return secManageAcctId.
	 */
	public String getSecManageAcctId() {
		return secManageAcctId;
	}

	/**
	 * 设置 secManageAcctId
	 * @param secManageAcctId secManageAcctId
	 */
	public void setSecManageAcctId(String secManageAcctId) {
		this.secManageAcctId = secManageAcctId;
	}

	/**
	 * 获取 finprodMarketId
	 * @return finprodMarketId.
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	/**
	 * 设置 finprodMarketId
	 * @param finprodMarketId finprodMarketId
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	/**
	 * 获取 finprodMarketName
	 * @return finprodMarketName.
	 */
	public String getFinprodMarketName() {
		return finprodMarketName;
	}

	/**
	 * 设置 finprodMarketName
	 * @param finprodMarketName finprodMarketName
	 */
	public void setFinprodMarketName(String finprodMarketName) {
		this.finprodMarketName = finprodMarketName;
	}


	/**
	 * 获取 qty
	 * @return qty.
	 */
	public BigDecimal getQty() {
		return qty;
	}

	/**
	 * 设置 qty
	 * @param qty qty
	 */
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * 获取 fpriceAmt
	 * @return fpriceAmt.
	 */
	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}

	/**
	 * 设置 fpriceAmt
	 * @param fpriceAmt fpriceAmt
	 */
	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	/**
	 * 获取 tradeAmt
	 * @return tradeAmt.
	 */
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	/**
	 * 设置 tradeAmt
	 * @param tradeAmt tradeAmt
	 */
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	/**
	 * 获取 basis
	 * @return basis.
	 */
	public String getBasis() {
		return basis;
	}

	/**
	 * 设置 basis
	 * @param basis basis
	 */
	public void setBasis(String basis) {
		this.basis = basis;
	}

	/**
	 * 获取 yield
	 * @return yield.
	 */
	public BigDecimal getYield() {
		return yield;
	}

	/**
	 * 设置 yield
	 * @param yield yield
	 */
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	/**
	 * 获取 entruster
	 * @return entruster.
	 */
	public String getEntruster() {
		return entruster;
	}

	/**
	 * 设置 entruster
	 * @param entruster entruster
	 */
	public void setEntruster(String entruster) {
		this.entruster = entruster;
	}

	/**
	 * 获取 intAmt
	 * @return intAmt.
	 */
	public BigDecimal getIntAmt() {
		return intAmt;
	}

	/**
	 * 设置 intAmt
	 * @param intAmt intAmt
	 */
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	/**
	 * 获取 prinAmt
	 * @return prinAmt.
	 */
	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	/**
	 * 设置 prinAmt
	 * @param prinAmt prinAmt
	 */
	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	/**
	 * 获取 feeAmt
	 * @return feeAmt.
	 */
	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	/**
	 * 设置 feeAmt
	 * @param feeAmt feeAmt
	 */
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	/**
	 * 获取 theoryInt
	 * @return theoryInt.
	 */
	public BigDecimal getTheoryInt() {
		return theoryInt;
	}

	/**
	 * 设置 theoryInt
	 * @param theoryInt theoryInt
	 */
	public void setTheoryInt(BigDecimal theoryInt) {
		this.theoryInt = theoryInt;
	}

	/**
	 * 获取 remainPrinAmt
	 * @return remainPrinAmt.
	 */
	public BigDecimal getRemainPrinAmt() {
		return remainPrinAmt;
	}

	/**
	 * 设置 remainPrinAmt
	 * @param remainPrinAmt remainPrinAmt
	 */
	public void setRemainPrinAmt(BigDecimal remainPrinAmt) {
		this.remainPrinAmt = remainPrinAmt;
	}

	/**
	 * 获取 intPrinAmt
	 * @return intPrinAmt.
	 */
	public BigDecimal getIntPrinAmt() {
		return intPrinAmt;
	}

	/**
	 * 设置 intPrinAmt
	 * @param intPrinAmt intPrinAmt
	 */
	public void setIntPrinAmt(BigDecimal intPrinAmt) {
		this.intPrinAmt = intPrinAmt;
	}

	/**
	 * 获取 unitIntAmt
	 * @return unitIntAmt.
	 */
	public BigDecimal getUnitIntAmt() {
		return unitIntAmt;
	}

	/**
	 * 设置 unitIntAmt
	 * @param unitIntAmt unitIntAmt
	 */
	public void setUnitIntAmt(BigDecimal unitIntAmt) {
		this.unitIntAmt = unitIntAmt;
	}

//	/**
//	 * 获取 payDate
//	 * @return payDate.
//	 */
//	public String getPayDate() {
//		return payDate;
//	}
//
//	/**
//	 * 设置 payDate
//	 * @param payDate payDate
//	 */
//	public void setPayDate(String payDate) {
//		this.payDate = payDate;
//	}

	/**
	 * 获取 cashId
	 * @return cashId.
	 */
	public String getCashId() {
		return cashId;
	}

	/**
	 * 设置 cashId
	 * @param cashId cashId
	 */
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	/**
	 * 获取 ccy
	 * @return ccy.
	 */
	public String getCcy() {
		return ccy;
	}

	/**
	 * 设置 ccy
	 * @param ccy ccy
	 */
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	/**
	 * 获取 remark
	 * @return remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 remark
	 * @param remark remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 tradeMarket
	 * @return tradeMarket.
	 */
	public String getTradeMarket() {
		return tradeMarket;
	}

	/**
	 * 设置 tradeMarket
	 * @param tradeMarket tradeMarket
	 */
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	/**
	 * 获取 vdateProd
	 * @return vdateProd.
	 */
	public Date getVdateProd() {
		return vdateProd;
	}

	/**
	 * 设置 vdateProd
	 * @param vdateProd vdateProd
	 */
	public void setVdateProd(Date vdateProd) {
		this.vdateProd = vdateProd;
	}

	/**
	 * 获取 mdateProd
	 * @return mdateProd.
	 */
	public Date getMdateProd() {
		return mdateProd;
	}

	/**
	 * 设置 mdateProd
	 * @param mdateProd mdateProd
	 */
	public void setMdateProd(Date mdateProd) {
		this.mdateProd = mdateProd;
	}

	/**
	 * 获取 loadRate
	 * @return loadRate.
	 */
	public BigDecimal getLoadRate() {
		return loadRate;
	}

	/**
	 * 设置 loadRate
	 * @param loadRate loadRate
	 */
	public void setLoadRate(BigDecimal loadRate) {
		this.loadRate = loadRate;
	}

	/**
	 * 获取 tradeDate
	 * @return tradeDate.
	 */
	public Date getTradeDate() {
		return tradeDate;
	}

	/**
	 * 设置 tradeDate
	 * @param tradeDate tradeDate
	 */
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * 获取 wflTrdFinProductDealTotal
	 * @return wflTrdFinProductDealTotal.
	 */
	public WflTrdFinProductDealTotal getWflTrdFinProductDealTotal() {
		return wflTrdFinProductDealTotal;
	}

	/**
	 * 设置 wflTrdFinProductDealTotal
	 * @param wflTrdFinProductDealTotal wflTrdFinProductDealTotal
	 */
	public void setWflTrdFinProductDealTotal(WflTrdFinProductDealTotal wflTrdFinProductDealTotal) {
		this.wflTrdFinProductDealTotal = wflTrdFinProductDealTotal;
	}

	/**
	 * 获取 wflStlPayOrdersList
	 * @return wflStlPayOrdersList.
	 */
	public List<WflStlPayOrders> getWflStlPayOrdersList() {
		return wflStlPayOrdersList;
	}

	/**
	 * 设置 wflStlPayOrdersList
	 * @param wflStlPayOrdersList wflStlPayOrdersList
	 */
	public void setWflStlPayOrdersList(List<WflStlPayOrders> wflStlPayOrdersList) {
		this.wflStlPayOrdersList = wflStlPayOrdersList;
	}

	/**
	 * 获取 wflPaTrdFinDealTotal
	 * @return wflPaTrdFinDealTotal.
	 */
	public WflPaTrdFinDealTotal getWflPaTrdFinDealTotal() {
		return wflPaTrdFinDealTotal;
	}

	/**
	 * 设置 wflPaTrdFinDealTotal
	 * @param wflPaTrdFinDealTotal wflPaTrdFinDealTotal
	 */
	public void setWflPaTrdFinDealTotal(WflPaTrdFinDealTotal wflPaTrdFinDealTotal) {
		this.wflPaTrdFinDealTotal = wflPaTrdFinDealTotal;
	}

	/**
	 * 获取 wflTrdProductDealList
	 * @return wflTrdProductDealList.
	 */
	public List<WflTrdProductDeal> getWflTrdProductDealList() {
		return wflTrdProductDealList;
	}

	/**
	 * 设置 wflTrdProductDealList
	 * @param wflTrdProductDealList wflTrdProductDealList
	 */
	public void setWflTrdProductDealList(List<WflTrdProductDeal> wflTrdProductDealList) {
		this.wflTrdProductDealList = wflTrdProductDealList;
	}

	/**
	 * 获取 ptlTrades
	 * @return ptlTrades.
	 */
	public List<PtlTrade> getPtlTrades() {
		return ptlTrades;
	}

	/**
	 * 设置 ptlTrades
	 * @param ptlTrades ptlTrades
	 */
	public void setPtlTrades(List<PtlTrade> ptlTrades) {
		this.ptlTrades = ptlTrades;
	}

	/**
	 * 获取 repTrades
	 * @return repTrades.
	 */
	public List<RepTrade> getRepTrades() {
		return repTrades;
	}

	/**
	 * 设置 repTrades
	 * @param repTrades repTrades
	 */
	public void setRepTrades(List<RepTrade> repTrades) {
		this.repTrades = repTrades;
	}

	/**
	 * 获取 adjustDate
	 * @return adjustDate.
	 */
	public Date getAdjustDate() {
		return adjustDate;
	}

	/**
	 * 设置 adjustDate
	 * @param adjustDate adjustDate
	 */
	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	/**
	 * 获取 opType
	 * @return opType.
	 */
	public String getOpType() {
		return opType;
	}

	/**
	 * 设置 opType
	 * @param opType opType
	 */
	public void setOpType(String opType) {
		this.opType = opType;
	}

	/**
	 * 获取 repIssuePartyId
	 * @return repIssuePartyId.
	 */
	public String getRepIssuePartyId() {
		return repIssuePartyId;
	}

	/**
	 * 设置 repIssuePartyId
	 * @param repIssuePartyId repIssuePartyId
	 */
	public void setRepIssuePartyId(String repIssuePartyId) {
		this.repIssuePartyId = repIssuePartyId;
	}

	/**
	 * 获取 paBussType
	 * @return paBussType.
	 */
	public String getPaBussType() {
		return paBussType;
	}

	/**
	 * 设置 paBussType
	 * @param paBussType paBussType
	 */
	public void setPaBussType(String paBussType) {
		this.paBussType = paBussType;
	}

	/**
	 * 获取 earlyWithdrawalRate
	 * @return earlyWithdrawalRate.
	 */
	public BigDecimal getEarlyWithdrawalRate() {
		return earlyWithdrawalRate;
	}

	/**
	 * 设置 earlyWithdrawalRate
	 * @param earlyWithdrawalRate earlyWithdrawalRate
	 */
	public void setEarlyWithdrawalRate(BigDecimal earlyWithdrawalRate) {
		this.earlyWithdrawalRate = earlyWithdrawalRate;
	}

	/**
	 * 获取 earlyWithdrawalAmt
	 * @return earlyWithdrawalAmt.
	 */
	public BigDecimal getEarlyWithdrawalAmt() {
		return earlyWithdrawalAmt;
	}

	/**
	 * 设置 earlyWithdrawalAmt
	 * @param earlyWithdrawalAmt earlyWithdrawalAmt
	 */
	public void setEarlyWithdrawalAmt(BigDecimal earlyWithdrawalAmt) {
		this.earlyWithdrawalAmt = earlyWithdrawalAmt;
	}

	/**
	 * 获取 mode
	 * @return mode.
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * 设置 mode
	 * @param mode mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
