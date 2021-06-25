package com.joyintech.service.entrust.etribbondinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbBondinfo001Dto
 * @since 1.0
 */
@PageId("EtrIbBondinfo001")
public class EtrIbBondinfo001Dto extends BaseEntity {

    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 持仓数量
     */
    private BigDecimal amount;
    
    /**
     * 单位成本
     */
    private BigDecimal unitCost;
    
    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 债券一级分类
     */
    private String secType1;

    /**
     * 持仓券面总额
     */
    private BigDecimal shareAmt;

    /**
     * 质押券面总额
     */
    private BigDecimal avaPledgePrin;

    /**
     * 质押券面总额
     */
    private BigDecimal avaShareAmt;

    /**
     * 持仓成本
     */
    private BigDecimal positionsAmt;

    /**
     * 最新价
     */
    private BigDecimal price;

    /**
     * 持仓市值
     */
    private BigDecimal positionsPrice;

    /**
     * 浮动盈亏
     */
    private BigDecimal floatingPl;

    /**
     * 指令编号
     */
    private String entrustId;

    /**
     * 有效状态
     */
    private String entrustStatus;

    /**
     * 投资经理
     */
    private String invManagerId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 券面总额
     */
    private BigDecimal shareNum;

    /**
     * 价格模式
     */
    private String priceModel;

    /**
     * 净价
     */
    private BigDecimal unitPrice;

    /**
     * 全价
     */
    private BigDecimal allPrice;

    /**
     * 到期收益率
     */
    private BigDecimal matureYield;

    /**
     * 行权收益率
     */
    private BigDecimal exerYield;

    /**
     * 清算速度
     */
    private String settleSpreed;

    /**
     * 指令开始日
     */
    private Date vdate;

    /**
     * 指令结束日
     */
    private Date mdate;

    /**
     * 已完成额度
     */
    private BigDecimal finishAmt;

    /**
     * 交易完成度
     */
    private BigDecimal transactioncom;

    /**
     * 备注
     */
    private String remark;

    /**
     * 持仓单位成本(综合成本法)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal costPosition;

    /**
     * 持仓数量(综合成本法)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costPositionNum;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 交易对手
     */
    private String counterId;


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

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getSecType1() {
        return secType1;
    }

    public void setSecType1(String secType1) {
        this.secType1 = secType1;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getAvaPledgePrin() {
        return avaPledgePrin;
    }

    public void setAvaPledgePrin(BigDecimal avaPledgePrin) {
        this.avaPledgePrin = avaPledgePrin;
    }

    public BigDecimal getAvaShareAmt() {
        return avaShareAmt;
    }

    public void setAvaShareAmt(BigDecimal avaShareAmt) {
        this.avaShareAmt = avaShareAmt;
    }

    public BigDecimal getPositionsAmt() {
        return positionsAmt;
    }

    public void setPositionsAmt(BigDecimal positionsAmt) {
        this.positionsAmt = positionsAmt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPositionsPrice() {
        return positionsPrice;
    }

    public void setPositionsPrice(BigDecimal positionsPrice) {
        this.positionsPrice = positionsPrice;
    }

    public BigDecimal getFloatingPl() {
        return floatingPl;
    }

    public void setFloatingPl(BigDecimal floatingPl) {
        this.floatingPl = floatingPl;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getInvManagerId() {
        return invManagerId;
    }

    public void setInvManagerId(String invManagerId) {
        this.invManagerId = invManagerId;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public BigDecimal getShareNum() {
        return shareNum;
    }

    public void setShareNum(BigDecimal shareNum) {
        this.shareNum = shareNum;
    }

    public String getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
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

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    public BigDecimal getTransactioncom() {
        return transactioncom;
    }

    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 取得 持仓单位成本(综合成本法)
     * @return 持仓单位成本(综合成本法)
     */
    public BigDecimal getCostPosition() {
        return costPosition;
    }

    /**
     * 设置 持仓单位成本(综合成本法)
     * @param costPosition 持仓单位成本(综合成本法)
     */
    public void setCostPosition(BigDecimal costPosition) {
        this.costPosition = costPosition;
    }

    /**
     * 取得 持仓数量(综合成本法)
     * @return 持仓数量(综合成本法)
     */
    public BigDecimal getCostPositionNum() {
        return costPositionNum;
    }

    /**
     * 设置 持仓数量(综合成本法)
     * @param costPositionNum 持仓数量(综合成本法)
     */
    public void setCostPositionNum(BigDecimal costPositionNum) {
        this.costPositionNum = costPositionNum;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    private String parentPortfolioId;


    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}

	public String getInvAim() {
		return invAim;
	}

	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}
    
    
}
