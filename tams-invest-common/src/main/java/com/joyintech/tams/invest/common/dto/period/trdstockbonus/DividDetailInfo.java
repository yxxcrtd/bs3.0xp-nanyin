/**
 * 
 */
package com.joyintech.tams.invest.common.dto.period.trdstockbonus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.StlPayOrders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**金融产品交易dto
 * @author panbin
 * @version 2019年6月24日
 * @see TrdStockBonus003Dto
 */
public class DividDetailInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3225658084827144465L;

	 /**
     * 业务明细代码，金融产品代码等
     */
    private String busiId;


    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 证券托管户代码
     */
    private String secManageAcctId;

    /**
     * 证券托管户代码
     */
    private String secAcctId;


    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 业务类型，债券交易、产品交易，估值核算专用
     */
    private String busiType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 交割日期
     */
    private Date settleDate;

    /**
     * 清算速度
     */
    private Long settleSpeed;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 净值日期
     */
    private Date cpriceDate;

    /**
     * 单位净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 单位利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 单位全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitFprice;

    /**
     * 单位面值，债券100或者当前面值(还本前)，其他资产1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValue;

    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 交易本金，债券券面等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 随交易支付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmtPay;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 未随交易支付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmtUnpay;

    /**
     * 总交易费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 行权收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exerYield;



    /**
     * 交易状态，初始、撤销
     */
    private String tradeStatus;

    /**
     * 是否已撤销，默认“否”，在交易发生撤销时更新成“是”
     */
    private String isCancel;

    /**
     * 原交易编号，撤销时存原来的交易编号
     */
    private String pTradeId;

    /**
     * 是否结清，收息付费时用
     */
    private String isClean;

    /**
     * 汇总交易编号
     */
    private String totleTradeId;

    /**
     * 反向交易编号
     */
    private String rTradeId;

    /**
     * 前置交易编号，记录该交易的前置交易编号。比如申购确认时存申购申请交易编号
     */
    private String bTradeId;

    /**
     * 渠道/通道代码
     */
    private String chlId;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 是否外部交易，是、否
     */
    private String isOutTrade;

    /**
     * 交易场所，银行间、上交所、深交所、柜面等
     */
    private String tradeMarket;

    /**
     * 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     */
    private String tradePlat;

    /**
     * 交易市场，一级市场、二级市场
     */
    private String tradeMarketMode;

    /**
     * 交易员
     */
    private String trader;

    /**
     * 对手方交易员
     */
    private String counterTrader;

    /**
     * 对手方联系方式
     */
    private String counterContact;


    /**
     * 关联系统交易编号
     */
    private String fTradeId;

    /**
     * 现金流代码，还本、付息、付费等时存对应计划的现金流代码
     */
    private String cashId;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 备注
     */
    private String remark;

    /**
     * 撤销说明
     */
    private String cancelRemark;

    /**
     * null
     */
    private String deliveryType;

    /**
     * 交付日
     */
    private Date payDate;
    
    
    /**
     * 投资组合代码
     */
    private String portfolioId;
    
    /**
     * 投资组合名称
     */
    private String portfolioName;

    /**
     * 自定义现金流类型
     */
    private String customCashType;

    /**
     * 生成方式：接口接入、画面录入、批处理生成
     */
    private String genType;

    /**
     * 预计申购确认日
     */
    private Date purCfmDate;

    /**
     * 预计赎回到账日
     */
    private Date redCfmDate;

    /**
     * 权益登记日
     */
    private Date regDate;

    /**
     * 预计分红日
     */
    private Date bonusCfmDate;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 支付类型，交易方向为分红除权时填写，红利再投、现金分红
     */
    private String payType;

    /**
     * 赎回收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redProfit;

    /**
     * 赎回成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redCost;

    /**
     * 权益登记日份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal regDateAmt;

    /**
     * 主协议金融产品代码
     */
    private String mpFinprodId;

    /**
     * 主协议分支序号
     */
    private Long mpBranch;
    
    /**
     * 权益登记日股本
     */
    private BigDecimal amount;
    
	
	/**
	 * 划款指令
	 */
	private List<StlPayOrders> stlPayOrders;

	

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getInvAim() {
		return invAim;
	}

	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	public String getSecManageAcctId() {
		return secManageAcctId;
	}

	public void setSecManageAcctId(String secManageAcctId) {
		this.secManageAcctId = secManageAcctId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getFinprodType2() {
		return finprodType2;
	}

	public void setFinprodType2(String finprodType2) {
		this.finprodType2 = finprodType2;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
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

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public Long getSettleSpeed() {
		return settleSpeed;
	}

	public void setSettleSpeed(Long settleSpeed) {
		this.settleSpeed = settleSpeed;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Date getCpriceDate() {
		return cpriceDate;
	}

	public void setCpriceDate(Date cpriceDate) {
		this.cpriceDate = cpriceDate;
	}

	public BigDecimal getUnitCprice() {
		return unitCprice;
	}

	public void setUnitCprice(BigDecimal unitCprice) {
		this.unitCprice = unitCprice;
	}

	public BigDecimal getUnitInt() {
		return unitInt;
	}

	public void setUnitInt(BigDecimal unitInt) {
		this.unitInt = unitInt;
	}

	public BigDecimal getUnitFprice() {
		return unitFprice;
	}

	public void setUnitFprice(BigDecimal unitFprice) {
		this.unitFprice = unitFprice;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public BigDecimal getCpriceAmt() {
		return cpriceAmt;
	}

	public void setCpriceAmt(BigDecimal cpriceAmt) {
		this.cpriceAmt = cpriceAmt;
	}

	public BigDecimal getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}

	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public BigDecimal getIntAmt() {
		return intAmt;
	}

	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}

	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public BigDecimal getFeeAmtPay() {
		return feeAmtPay;
	}

	public void setFeeAmtPay(BigDecimal feeAmtPay) {
		this.feeAmtPay = feeAmtPay;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public BigDecimal getFeeAmtUnpay() {
		return feeAmtUnpay;
	}

	public void setFeeAmtUnpay(BigDecimal feeAmtUnpay) {
		this.feeAmtUnpay = feeAmtUnpay;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public BigDecimal getExerYield() {
		return exerYield;
	}

	public void setExerYield(BigDecimal exerYield) {
		this.exerYield = exerYield;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}

	public String getpTradeId() {
		return pTradeId;
	}

	public void setpTradeId(String pTradeId) {
		this.pTradeId = pTradeId;
	}

	public String getIsClean() {
		return isClean;
	}

	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}

	public String getTotleTradeId() {
		return totleTradeId;
	}

	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}

	public String getrTradeId() {
		return rTradeId;
	}

	public void setrTradeId(String rTradeId) {
		this.rTradeId = rTradeId;
	}

	public String getbTradeId() {
		return bTradeId;
	}

	public void setbTradeId(String bTradeId) {
		this.bTradeId = bTradeId;
	}

	public String getChlId() {
		return chlId;
	}

	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	public String getCounterId() {
		return counterId;
	}

	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	public String getIsOutTrade() {
		return isOutTrade;
	}

	public void setIsOutTrade(String isOutTrade) {
		this.isOutTrade = isOutTrade;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getTradePlat() {
		return tradePlat;
	}

	public void setTradePlat(String tradePlat) {
		this.tradePlat = tradePlat;
	}

	public String getTradeMarketMode() {
		return tradeMarketMode;
	}

	public void setTradeMarketMode(String tradeMarketMode) {
		this.tradeMarketMode = tradeMarketMode;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getCounterTrader() {
		return counterTrader;
	}

	public void setCounterTrader(String counterTrader) {
		this.counterTrader = counterTrader;
	}

	public String getCounterContact() {
		return counterContact;
	}

	public void setCounterContact(String counterContact) {
		this.counterContact = counterContact;
	}

	public String getfTradeId() {
		return fTradeId;
	}

	public void setfTradeId(String fTradeId) {
		this.fTradeId = fTradeId;
	}

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public String getRegistOrg() {
		return registOrg;
	}

	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getCustomCashType() {
		return customCashType;
	}

	public void setCustomCashType(String customCashType) {
		this.customCashType = customCashType;
	}

	public String getGenType() {
		return genType;
	}

	public void setGenType(String genType) {
		this.genType = genType;
	}

	public Date getPurCfmDate() {
		return purCfmDate;
	}

	public void setPurCfmDate(Date purCfmDate) {
		this.purCfmDate = purCfmDate;
	}

	public Date getRedCfmDate() {
		return redCfmDate;
	}

	public void setRedCfmDate(Date redCfmDate) {
		this.redCfmDate = redCfmDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getBonusCfmDate() {
		return bonusCfmDate;
	}

	public void setBonusCfmDate(Date bonusCfmDate) {
		this.bonusCfmDate = bonusCfmDate;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getRedProfit() {
		return redProfit;
	}

	public void setRedProfit(BigDecimal redProfit) {
		this.redProfit = redProfit;
	}

	public BigDecimal getRedCost() {
		return redCost;
	}

	public void setRedCost(BigDecimal redCost) {
		this.redCost = redCost;
	}

	public BigDecimal getRegDateAmt() {
		return regDateAmt;
	}

	public void setRegDateAmt(BigDecimal regDateAmt) {
		this.regDateAmt = regDateAmt;
	}

	public String getMpFinprodId() {
		return mpFinprodId;
	}

	public void setMpFinprodId(String mpFinprodId) {
		this.mpFinprodId = mpFinprodId;
	}

	public Long getMpBranch() {
		return mpBranch;
	}

	public void setMpBranch(Long mpBranch) {
		this.mpBranch = mpBranch;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<StlPayOrders> getStlPayOrders() {
		return stlPayOrders;
	}

	public void setStlPayOrders(List<StlPayOrders> stlPayOrders) {
		this.stlPayOrders = stlPayOrders;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}
	
	
}
