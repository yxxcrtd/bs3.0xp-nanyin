package com.joyintech.service.trade.primarybiddingmanage.dto;

import com.joyintech.tams.common.base.entity.EtrEntrust;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class PrimaryBiddingManageDto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 投标编号
     */
    private String bidId;
    
    /**
     * 有效状态
     */
    private String validStatus;
	
    /**
     * 确认状态
     */
    private String tenderconfirmstatus;
	
    /**
     * 金融产品全称
     */
    private String finprodName;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    
    /**
     * 投标日
     */
    private Date biddate;
    
    /**
     * 投标券面总额汇总
     */
    private BigDecimal allBidPrinAmt;
    
    /**
     * 投标标位
     */
    private BigDecimal bidprice;
    
    /**
     * 投标标位
     */
    private String bidpriceStr;

    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 交易对手名称
     */
    private String counterIdText;
    
    /**
     * 投标组合列表
     */
    private List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtolist;
    
    /**
     * 汇总投标列表
     */
    private List<PrimaryBiddingPortfolioDto> primaryBiddingAllDtolist;

	/**
	 * 截标状态
	 */
	private String bidCloseStatus;

	/**
	 * 操作类型
	 */
	private String operateType;
    /**
     * 获取 bidId
     *
     * @return bidId
     */
    public String getBidId() {
		return bidId;
	}
    /**
     * 设置 bidId
     *
     * @return bidId bidId
     */
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	/**
     * 获取 validStatus
     *
     * @return validStatus
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 设置 validStatus
     *
     * @param validStatus validStatus
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }
    
    /**
     * 获取 tenderconfirmstatus
     *
     * @return tenderconfirmstatus
     */
	public String getTenderconfirmstatus() {
		return tenderconfirmstatus;
	}

	/**
     * 设置 tenderconfirmstatus
     *
     * @param tenderconfirmstatus tenderconfirmstatus
     */
	public void setTenderconfirmstatus(String tenderconfirmstatus) {
		this.tenderconfirmstatus = tenderconfirmstatus;
	}
    
	/**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
		return finprodAbbr;
	}
    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	/**
     * 获取 biddate
     * @param biddate
     */
	public Date getBiddate() {
		return biddate;
	}
	
	/**
     * 设置 biddate
     * @param biddate biddate
     */
	public void setBiddate(Date biddate) {
		this.biddate = biddate;
	}
	/**
     * 获取 allBidPrinAmt
     * @param allBidPrinAmt
     */
	public BigDecimal getAllBidPrinAmt() {
		return allBidPrinAmt;
	}
	
	/**
     * 设置 allBidPrinAmt
     * @param allBidPrinAmt allBidPrinAmt
     */
	public void setAllBidPrinAmt(BigDecimal allBidPrinAmt) {
		this.allBidPrinAmt = allBidPrinAmt;
	}
	public BigDecimal getBidprice() {
		return bidprice;
	}
	public void setBidprice(BigDecimal bidprice) {
		this.bidprice = bidprice;
	}
	public String getBidpriceStr() {
		return bidpriceStr;
	}
	public void setBidpriceStr(String bidpriceStr) {
		this.bidpriceStr = bidpriceStr;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public List<PrimaryBiddingPortfolioDto> getPrimaryBiddingPortfolioDtolist() {
		return primaryBiddingPortfolioDtolist;
	}
	public void setPrimaryBiddingPortfolioDtolist(List<PrimaryBiddingPortfolioDto> primaryBiddingPortfolioDtolist) {
		this.primaryBiddingPortfolioDtolist = primaryBiddingPortfolioDtolist;
	}
	public List<PrimaryBiddingPortfolioDto> getPrimaryBiddingAllDtolist() {
		return primaryBiddingAllDtolist;
	}
	public void setPrimaryBiddingAllDtolist(List<PrimaryBiddingPortfolioDto> primaryBiddingAllDtolist) {
		this.primaryBiddingAllDtolist = primaryBiddingAllDtolist;
	}
	public String getCounterIdText() {
		return counterIdText;
	}
	public void setCounterIdText(String counterIdText) {
		this.counterIdText = counterIdText;
	}

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }


	/**
	 * 获取 截标状态
	 *
	 * @return bidCloseStatus 截标状态
	 */
	public String getBidCloseStatus() {
		return this.bidCloseStatus;
	}

	/**
	 * 设置 截标状态
	 *
	 * @param bidCloseStatus 截标状态
	 */
	public void setBidCloseStatus(String bidCloseStatus) {
		this.bidCloseStatus = bidCloseStatus;
	}


	/**
	 * 获取 操作类型
	 *
	 * @return operateType 操作类型
	 */
	public String getOperateType() {
		return this.operateType;
	}

	/**
	 * 设置 操作类型
	 *
	 * @param operateType 操作类型
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
}
