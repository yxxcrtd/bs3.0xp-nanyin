package com.joyintech.tams.component.business.trade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class BussTrdTransfenpeiDto {

	/**
     * 组合代码
     */
    private String portfolioId;
	/**
	 * 证券管理户代码
	 */
	private String secManageAcctIdDuo;
    
	/**
     * 券面总额
     */
    private BigDecimal tradeAmt;
    
    /**
     * 净价总额
     */
    private BigDecimal cpriceAmt;
    
    /**
     * 全价价总额
     */
    private BigDecimal fpriceAmt;
    
    /**
     * 组合T日可用现金
     */
    public BigDecimal bussTrdAvailableCashQueryavalibleCash;
    
    /**
     * 组合T+1日可用现金
     */
    public BigDecimal bussTrdAvailableCashQueryavalibleCash1;
	/**
	 * 本方券券面总额
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal ownTradeAmt;

	/**
	 * 融入券券面总额
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal integrateTradeAmt;

	/**
	 * 本方券应计利息
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal ownIntAmt;

	/**
	 * 融入券应计利息
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal integrateIntAmt;

	/**
	 * 本方券全价总额
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal ownFpriceAmt;

	/**
	 * 融入券全价总额
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal integrateFpriceAmt;

	/**
	 * 融入券净价总额
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal integrateCpriceAmt;


	/**
	 * 融入券卖空可用持仓
	 */
	private BigDecimal assetIntegrateSaleAvailablePosition;

	/**
	 * 本方券可用持仓
	 */
	private BigDecimal assetOwnAvailablePosition;

	/**
	 * 融入券可用持仓
	 */
	private BigDecimal assetIntegrateAvailablePosition;
    /**
     * 托管户剩余现金
     */
    public BigDecimal tgSurpluscash;
    //手续费
    public BigDecimal procedureAmt;
    //结算费
    public  BigDecimal settleAmt;
    //佣金
    public BigDecimal commissionAmt;
	//交易金额
    public BigDecimal tradeAmtAndFee;
    //通道可用现金
    public BigDecimal channelAvailableCash;
	//应计利息总额
    public BigDecimal intAmt;
    //可用持仓
    private BigDecimal Assetsavailablepositions;

    private String entrustId;
    
    /**
     * 本金金额
     */
    private BigDecimal prinAmt;
    
    /**
     * 通道费
     */
    private BigDecimal channelFee;
    
    /**
     * 剩余委托额度
     */
    private BigDecimal doEntrustMoney;

    /**
     * 交易份额
     */
    private BigDecimal shareAmt;
    
    /**
     * 净价
     */
    private BigDecimal unitCprice;
    
    /**
     * 下委托日期
     */
    private Date entrustDate;
    
    /**
     * 投资目的
     */
    private String investaim;
    
    /**
     * 获取 investaim
     * @return investaim.
     */
    public String getInvestaim() {
        return investaim;
    }

    /**
     * 设置 investaim
     * @param investaim investaim
     */
    public void setInvestaim(String investaim) {
        this.investaim = investaim;
    }

    /**
     * 获取 entrustDate
     * @return entrustDate.
     */
    public Date getEntrustDate() {
        return entrustDate;
    }

    /**
     * 设置 entrustDate
     * @param entrustDate entrustDate
     */
    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }


    /**
     * 获取 unitCprice
     * @return unitCprice.
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    
    /**
     * 设置 unitCprice
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public BigDecimal getIntAmt() {
		return intAmt;
	}

	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	public BigDecimal getTgSurpluscash() {
        return tgSurpluscash;
    }

    public void setTgSurpluscash(BigDecimal tgSurpluscash) {
        this.tgSurpluscash = tgSurpluscash;
    }


    public BigDecimal getBussTrdAvailableCashQueryavalibleCash() {
        return bussTrdAvailableCashQueryavalibleCash;
    }

    
    public void setBussTrdAvailableCashQueryavalibleCash(BigDecimal bussTrdAvailableCashQueryavalibleCash) {
        this.bussTrdAvailableCashQueryavalibleCash = bussTrdAvailableCashQueryavalibleCash;
    }

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getSecManageAcctIdDuo() {
		return secManageAcctIdDuo;
	}

	public void setSecManageAcctIdDuo(String secManageAcctIdDuo) {
		this.secManageAcctIdDuo = secManageAcctIdDuo;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public BigDecimal getCpriceAmt() {
		return cpriceAmt;
	}

	public void setCpriceAmt(BigDecimal cpriceAmt) {
		this.cpriceAmt = cpriceAmt;
	}

	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}

	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public BigDecimal getProcedureAmt() {
		return procedureAmt;
	}

	public void setProcedureAmt(BigDecimal procedureAmt) {
		this.procedureAmt = procedureAmt;
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	public BigDecimal getCommissionAmt() {
		return commissionAmt;
	}

	public void setCommissionAmt(BigDecimal commissionAmt) {
		this.commissionAmt = commissionAmt;
	}

	public BigDecimal getTradeAmtAndFee() {
		return tradeAmtAndFee;
	}

	public void setTradeAmtAndFee(BigDecimal tradeAmtAndFee) {
		this.tradeAmtAndFee = tradeAmtAndFee;
	}

	public BigDecimal getAssetsavailablepositions() {
		return Assetsavailablepositions;
	}

	public void setAssetsavailablepositions(BigDecimal assetsavailablepositions) {
		Assetsavailablepositions = assetsavailablepositions;
	}

	/**
	 * 获取 ownTradeAmt
	 *
	 * @return ownTradeAmt
	 */
	public BigDecimal getOwnTradeAmt() {
		return ownTradeAmt;
	}

	/**
	 * 设置 ownTradeAmt
	 *
	 * @param ownTradeAmt ownTradeAmt
	 */
	public void setOwnTradeAmt(BigDecimal ownTradeAmt) {
		this.ownTradeAmt = ownTradeAmt;
	}

	/**
	 * 获取 integrateTradeAmt
	 *
	 * @return integrateTradeAmt
	 */
	public BigDecimal getIntegrateTradeAmt() {
		return integrateTradeAmt;
	}

	/**
	 * 设置 integrateTradeAmt
	 *
	 * @param integrateTradeAmt integrateTradeAmt
	 */
	public void setIntegrateTradeAmt(BigDecimal integrateTradeAmt) {
		this.integrateTradeAmt = integrateTradeAmt;
	}

	/**
	 * 获取 ownIntAmt
	 *
	 * @return ownIntAmt
	 */
	public BigDecimal getOwnIntAmt() {
		return ownIntAmt;
	}

	/**
	 * 设置 ownIntAmt
	 *
	 * @param ownIntAmt ownIntAmt
	 */
	public void setOwnIntAmt(BigDecimal ownIntAmt) {
		this.ownIntAmt = ownIntAmt;
	}

	/**
	 * 获取 integrateIntAmt
	 *
	 * @return integrateIntAmt
	 */
	public BigDecimal getIntegrateIntAmt() {
		return integrateIntAmt;
	}

	/**
	 * 设置 integrateIntAmt
	 *
	 * @param integrateIntAmt integrateIntAmt
	 */
	public void setIntegrateIntAmt(BigDecimal integrateIntAmt) {
		this.integrateIntAmt = integrateIntAmt;
	}

	/**
	 * 获取 ownFpriceAmt
	 *
	 * @return ownFpriceAmt
	 */
	public BigDecimal getOwnFpriceAmt() {
		return ownFpriceAmt;
	}

	/**
	 * 设置 ownFpriceAmt
	 *
	 * @param ownFpriceAmt ownFpriceAmt
	 */
	public void setOwnFpriceAmt(BigDecimal ownFpriceAmt) {
		this.ownFpriceAmt = ownFpriceAmt;
	}

	/**
	 * 获取 integrateFpriceAmt
	 *
	 * @return integrateFpriceAmt
	 */
	public BigDecimal getIntegrateFpriceAmt() {
		return integrateFpriceAmt;
	}

	/**
	 * 设置 integrateFpriceAmt
	 *
	 * @param integrateFpriceAmt integrateFpriceAmt
	 */
	public void setIntegrateFpriceAmt(BigDecimal integrateFpriceAmt) {
		this.integrateFpriceAmt = integrateFpriceAmt;
	}

	/**
	 * 获取 integrateCpriceAmt
	 *
	 * @return integrateCpriceAmt
	 */
	public BigDecimal getIntegrateCpriceAmt() {
		return integrateCpriceAmt;
	}

	/**
	 * 设置 integrateCpriceAmt
	 *
	 * @param integrateCpriceAmt integrateCpriceAmt
	 */
	public void setIntegrateCpriceAmt(BigDecimal integrateCpriceAmt) {
		this.integrateCpriceAmt = integrateCpriceAmt;
	}

	/**
	 * 获取 assetIntegrateSaleAvailablePosition
	 *
	 * @return assetIntegrateSaleAvailablePosition
	 */
	public BigDecimal getAssetIntegrateSaleAvailablePosition() {
		return assetIntegrateSaleAvailablePosition;
	}

	/**
	 * 设置 assetIntegrateSaleAvailablePosition
	 *
	 * @param assetIntegrateSaleAvailablePosition assetIntegrateSaleAvailablePosition
	 */
	public void setAssetIntegrateSaleAvailablePosition(BigDecimal assetIntegrateSaleAvailablePosition) {
		this.assetIntegrateSaleAvailablePosition = assetIntegrateSaleAvailablePosition;
	}

	/**
	 * 获取 assetOwnAvailablePosition
	 *
	 * @return assetOwnAvailablePosition
	 */
	public BigDecimal getAssetOwnAvailablePosition() {
		return assetOwnAvailablePosition;
	}

	/**
	 * 设置 assetOwnAvailablePosition
	 *
	 * @param assetOwnAvailablePosition assetOwnAvailablePosition
	 */
	public void setAssetOwnAvailablePosition(BigDecimal assetOwnAvailablePosition) {
		this.assetOwnAvailablePosition = assetOwnAvailablePosition;
	}

	/**
	 * 获取 assetIntegrateAvailablePosition
	 *
	 * @return assetIntegrateAvailablePosition
	 */
	public BigDecimal getAssetIntegrateAvailablePosition() {
		return assetIntegrateAvailablePosition;
	}

	/**
	 * 设置 assetIntegrateAvailablePosition
	 *
	 * @param assetIntegrateAvailablePosition assetIntegrateAvailablePosition
	 */
	public void setAssetIntegrateAvailablePosition(BigDecimal assetIntegrateAvailablePosition) {
		this.assetIntegrateAvailablePosition = assetIntegrateAvailablePosition;
	}

	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public BigDecimal getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}

	public BigDecimal getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}

	public BigDecimal getBussTrdAvailableCashQueryavalibleCash1() {
		return bussTrdAvailableCashQueryavalibleCash1;
	}

	public void setBussTrdAvailableCashQueryavalibleCash1(BigDecimal bussTrdAvailableCashQueryavalibleCash1) {
		this.bussTrdAvailableCashQueryavalibleCash1 = bussTrdAvailableCashQueryavalibleCash1;
	}

	public BigDecimal getDoEntrustMoney() {
		return doEntrustMoney;
	}

	public void setDoEntrustMoney(BigDecimal doEntrustMoney) {
		this.doEntrustMoney = doEntrustMoney;
	}

	public BigDecimal getChannelAvailableCash() {
		return channelAvailableCash;
	}

	public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
		this.channelAvailableCash = channelAvailableCash;
	}
	
	/**
	 * 交易编号
	 */
	private String tradeId;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	
	/**
	 * 申购费/赎回费
	 */
	private BigDecimal feeAmtPay;

	public BigDecimal getFeeAmtPay() {
		return feeAmtPay;
	}

	public void setFeeAmtPay(BigDecimal feeAmtPay) {
		this.feeAmtPay = feeAmtPay;
	}
	
}
