package com.joyintech.tams.invest.common.dto.trade.cstpTrdMatchInfo;

import com.joyintech.tams.common.base.entity.TrdMatchInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CstpTrdMatchInfo
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
public class CstpTrdMatchInfo extends TrdMatchInfo {
    private String invManagerId;//投资经理

    private String portfolioId;
    private String portfolioName;

    private String finprodType;

    private String finprodId;

    private String finprodAbbr;
    private String ps;
    private String invAim;//投资目的

    private BigDecimal prinAmt;//券面总额

    private BigDecimal unitCprice;//回购利率%/净价

    private BigDecimal fpriceAmt;//全价

    private  BigDecimal matureYield;//到期收益率%
    private BigDecimal exerYield;//行权收益率

    private String settleSpreed;//清算速度

    private BigDecimal unitInt;//应计利息

    private BigDecimal intAmt;//应计利息总额

    private BigDecimal tradeAmt;//交易金额

    private String secAcctId;

    private String secAcctName;

    private String tradePlat;
    private String chlId;
    private String termType;
    private String assetType1;
    private String priceMode;
    
    //交易类型
    private String tradeType;
    
    //回购期限
    private String repoTerm;
    
    //汇总交易编号
    private String totleTradeId;
    
    //用于显示的交易方向
    private String otherPs;
    
    //实际占款天数
    private String actCapDays;
    
    //到期结算金额
    private BigDecimal mdateSettleAmt;

    private Integer cashHoldingDays;//实际占款天数

    public Integer getCashHoldingDays() {
        return cashHoldingDays;
    }

    public void setCashHoldingDays(Integer cashHoldingDays) {
        this.cashHoldingDays = cashHoldingDays;
    }

    public BigDecimal getMdateSettleAmt() {
		return mdateSettleAmt;
	}

	public void setMdateSettleAmt(BigDecimal mdateSettleAmt) {
		this.mdateSettleAmt = mdateSettleAmt;
	}

	public String getActCapDays() {
		return actCapDays;
	}

	public void setActCapDays(String actCapDays) {
		this.actCapDays = actCapDays;
	}

	public String getOtherPs() {
		return otherPs;
	}

	public void setOtherPs(String otherPs) {
		this.otherPs = otherPs;
	}

	public String getTotleTradeId() {
		return totleTradeId;
	}

	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}

	public String getRepoTerm() {
		return repoTerm;
	}

	public void setRepoTerm(String repoTerm) {
		this.repoTerm = repoTerm;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getPriceMode() {
        return priceMode;
    }

    public void setPriceMode(String priceMode) {
        this.priceMode = priceMode;
    }

    public String getSecAcctName() {
        return secAcctName;
    }

    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    public String getInvManagerId() {
        return invManagerId;
    }

    public void setInvManagerId(String invManagerId) {
        this.invManagerId = invManagerId;
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

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getMatureYield() {
        return matureYield;
    }

    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    public String getSettleSpreed() {
        return settleSpreed;
    }

    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }

    public BigDecimal getUnitInt() {
        return unitInt;
    }

    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }
}
