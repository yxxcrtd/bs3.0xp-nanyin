/*
 * 文件名：TrdCommandReceive001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.commandreceive.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.EtrEntrust;

/**
 * 指令接收dto
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年9月26日
 * @see TrdCommandReceive001Dto
 * @since 1.0
 */
public class TrdCommandReceive001Dto extends EtrEntrust{
	
	private static final long serialVersionUID = 1L;
 	/**
 	 * 组合-组合名称
 	 */
 	private String portfolioName;
 	
 	/**
 	 * 标的类金融产品-市场代码
 	 */
 	private String finprodMarketId;
 	
 	/**
 	 * 标的类金融产品-金融产品简称
 	 */
 	private String finprodAbbr;
 	/**
 	 * 创建时间
 	 */
 	private Date createTimeVal;
 	
 	/**
 	 * 委托完成状况表A-完成额度
 	 */
 	@JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finshAmt; 
 	/**
 	 * 交易完成度
 	 */
 	@JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal percent; 
 	
 	/**
 	 * 委托指令分发-指定交易员
 	 */
 	private String trader;
 	
 	/**
 	 * 相隔天数
 	 */
 	private int days;
 	/**
 	 * 交易编号
 	 */
 	private String tradeId;
 	/**
 	 * 交易日
 	 */
 	private Date tradeDate;
 	/**
 	 * 回购/拆借方向
 	 */
 	private String busiPs;
 	
 	private String tradeStatus;

 	/**
 	 * 交易金额
 	 */
 	@JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    /**
     * 投标标位
     */
    private String bidPrice;
    /**
     * 投标编号
     */
    private String bidId;
    /**
     * 投标券面
     */
    private BigDecimal bidPrinAmt;
    /**
     * 中标券面总额
     */
    private BigDecimal winPrinAmt;
    /**
     * 是否中标
     */
    private String isWin;
	/**
	 * 证券账户
	 */
    private String secAcctId;
    
    /**
	 * 期限品种
	 */
    private String termType;

	/**
	 * 合同签署结果
	 */
	public String contractResult;
	/**
	 * 合同号
	 */
	public String contractNo;

    /**
	 * 交易方向名称（页面端使用字段）
	 */
    private String otherPs;

    private String settleSpeed;

    private String repoTerm;

    private BigDecimal matureYield;

	private String priceMode;

	private BigDecimal exerYield;

	private String tradeType;
	private String tradeTypeName;

	private BigDecimal prinAmt;
	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeTypeName() {
		return tradeTypeName;
	}

	public void setTradeTypeName(String tradeTypeName) {
		this.tradeTypeName = tradeTypeName;
	}

	public BigDecimal getExerYield() {
		return exerYield;
	}

	public void setExerYield(BigDecimal exerYield) {
		this.exerYield = exerYield;
	}

	public String getPriceMode() {
		return priceMode;
	}

	public void setPriceMode(String priceMode) {
		this.priceMode = priceMode;
	}

	public BigDecimal getMatureYield() {
		return matureYield;
	}

	public void setMatureYield(BigDecimal matureYield) {
		this.matureYield = matureYield;
	}

	public String getRepoTerm() {
		return repoTerm;
	}

	public void setRepoTerm(String repoTerm) {
		this.repoTerm = repoTerm;
	}

	public String getSettleSpeed() {
		return settleSpeed;
	}

	public void setSettleSpeed(String settleSpeed) {
		this.settleSpeed = settleSpeed;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public String getContractResult() {
		return contractResult;
	}

	public void setContractResult(String contractResult) {
		this.contractResult = contractResult;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * 得到组合名称
	 * @return
	 */
	public String getPortfolioName() {
		return portfolioName;
	}
	/**
	 * 设置组合名称
	 * @param portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	/**
	 * 得到市场代码
	 * @return
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	/**
	 * 设置市场代码
	 * @param finprodMarketId
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	/**
	 * 得到金融产品简称
	 * @return
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}
	/**
	 * 设置金融产品简称
	 * @param finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	/**
	 * 得到交易已完成额度
	 * @return
	 */
	public BigDecimal getFinshAmt() {
		return finshAmt;
	}
	/**
	 * 设置交易已完成额度
	 * @param finshAmt
	 */
	public void setFinshAmt(BigDecimal finshAmt) {
		this.finshAmt = finshAmt;
	}
	/**
	 * 得到交易完成度
	 * @return
	 */
	public BigDecimal getPercent() {
		return percent;
	}
	/**
	 * 设置交易完成度
	 * @param percent
	 */
	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	/**
	 * 得到交易员
	 * @return
	 */
	public String getTrader() {
		return trader;
	}
	/**
	 * 设置交易员
	 * @param trader
	 */
	public void setTrader(String trader) {
		this.trader = trader;
	}
	/**
	 * 得到相隔天数
	 * @return
	 */
	public int getDays() {
		return days;
	}
	/**
	 * 设置相隔天数
	 * @param days
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * 得到交易编号
	 * @return
	 */
	public String getTradeId() {
		return tradeId;
	}
	/**
	 * 设置交易编号
	 * @param tradeId
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * 得到交易日
	 * @return
	 */
	public Date getTradeDate() {
		return tradeDate;
	}
	/**
	 *设置交易日 
	 * @param tradeDate
	 */
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	/**
	 * 得到交易金额
	 * @return
	 */
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	/**
	 * 设置交易金额
	 * @param tradeAmt
	 */
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	/**
     * 得到创建时间
     * @return
     */
    public Date getCreateTimeVal() {
        return createTimeVal;
    }
    /**
     * 设置创建时间
     * @param createTimeVal
     */
    public void setCreateTimeVal(Date createTimeVal) {
        this.createTimeVal = createTimeVal;
    }
    /**
     * 获取 busiPs
     * @return busiPs.
     */
    public String getBusiPs() {
        return busiPs;
    }
    /**
     * 设置 busiPs
     * @param busiPs busiPs
     */
    public void setBusiPs(String busiPs) {
        this.busiPs = busiPs;
    }
    
    /**
     * 获取 tradeStatus
     * @return tradeStatus.
     */
    public String getTradeStatus() {
        return tradeStatus;
    }
    
    /**
     * 设置 tradeStatus
     * @param tradeStatus tradeStatus
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getBidPrinAmt() {
        return bidPrinAmt;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public void setBidPrinAmt(BigDecimal bidPrinAmt) {
        this.bidPrinAmt = bidPrinAmt;
    }

    public BigDecimal getWinPrinAmt() {
        return winPrinAmt;
    }

    public void setWinPrinAmt(BigDecimal winPrinAmt) {
        this.winPrinAmt = winPrinAmt;
    }

    public String getIsWin() {
        return isWin;
    }

    public void setIsWin(String isWin) {
        this.isWin = isWin;
    }

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getOtherPs() {
		return otherPs;
	}

	public void setOtherPs(String otherPs) {
		this.otherPs = otherPs;
	}
}

