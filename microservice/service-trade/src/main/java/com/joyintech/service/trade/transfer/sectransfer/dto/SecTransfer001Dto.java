package com.joyintech.service.trade.transfer.sectransfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
*
* 债券交易转仓
* @author zcw
* @version 1.0 2019年11月21日
* @see SecTransfer001Dto
* @since 1.0
*/
public class SecTransfer001Dto extends BaseEntity implements ProcessEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7267040827252594617L;
	
	/**
     * 投资过度组合
     */
    private String investPortfolioId;
    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;
    /**
     * 拆分前交易编号
     */
    private String tradeId;
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 拆分信息列表dtolist
     */
    private List<SecTransferDto> secResoluteDtoList;

    /**
     * 操作方式
     */
    private String operateType;
	/**
	 * 通道名称
	 */
	private String chlName;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 交割日期
     */
    private Date settleDate;
	/**
	 * 债券一级分类
	 */
	private String windTypeOne;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 资产名称
     */
    private String finprodAbbr;
    /**
     * 交易场所
     */
    private String tradeMarket;
    /**
     * 净价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitCprice;
    /**
     * 应计利息
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitInt;
    /**
     * 全价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitFprice;
    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 证券托管户
     */
    private String secManageAcctId;
    /**
     * 通道
     */
    private String chlId;
    /**
     * 券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shareAmt;
    /**
     * 剩余券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal undistributedAmount;
    /**
     * 已拆分金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal distributedAmount;
    /**
     * 币种
     */
    private String ccy;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 交易手续费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal procedureAmtS;
    /**
     * 结算费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal settleAmtS;
    /**
     * 佣金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal commissionAmtS;
    /**
     * 通道费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal channelFeeS;
    /**
     * 交易金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeRepAmtS;

	/**
	 * 单位净价
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal cprice;

	/**
	 * 单位应计利息
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal uint;

	/**
	 * 单位全价
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal fprice;
    
    private String calendarId;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    

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

	public String getInvestPortfolioId() {
		return investPortfolioId;
	}

	public void setInvestPortfolioId(String investPortfolioId) {
		this.investPortfolioId = investPortfolioId;
	}

	public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
		return baseTotalTradeInfoDto;
	}

	public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
		this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public List<SecTransferDto> getSecResoluteDtoList() {
		return secResoluteDtoList;
	}

	public void setSecResoluteDtoList(List<SecTransferDto> secResoluteDtoList) {
		this.secResoluteDtoList = secResoluteDtoList;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
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

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
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

	public String getChlId() {
		return chlId;
	}

	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	public BigDecimal getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}

	public BigDecimal getUndistributedAmount() {
		return undistributedAmount;
	}

	public void setUndistributedAmount(BigDecimal undistributedAmount) {
		this.undistributedAmount = undistributedAmount;
	}

	public BigDecimal getDistributedAmount() {
		return distributedAmount;
	}

	public void setDistributedAmount(BigDecimal distributedAmount) {
		this.distributedAmount = distributedAmount;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public BigDecimal getProcedureAmtS() {
		return procedureAmtS;
	}

	public void setProcedureAmtS(BigDecimal procedureAmtS) {
		this.procedureAmtS = procedureAmtS;
	}

	public BigDecimal getSettleAmtS() {
		return settleAmtS;
	}

	public void setSettleAmtS(BigDecimal settleAmtS) {
		this.settleAmtS = settleAmtS;
	}

	public BigDecimal getCommissionAmtS() {
		return commissionAmtS;
	}

	public void setCommissionAmtS(BigDecimal commissionAmtS) {
		this.commissionAmtS = commissionAmtS;
	}

	public BigDecimal getChannelFeeS() {
		return channelFeeS;
	}

	public void setChannelFeeS(BigDecimal channelFeeS) {
		this.channelFeeS = channelFeeS;
	}

	public BigDecimal getTradeRepAmtS() {
		return tradeRepAmtS;
	}

	public void setTradeRepAmtS(BigDecimal tradeRepAmtS) {
		this.tradeRepAmtS = tradeRepAmtS;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	/**
	 * 获取 通道名称
	 *
	 * @return chlName 通道名称
	 */
	public String getChlName() {
		return this.chlName;
	}

	/**
	 * 设置 通道名称
	 *
	 * @param chlName 通道名称
	 */
	public void setChlName(String chlName) {
		this.chlName = chlName;
	}

	/**
	 * 获取 债券一级分类
	 *
	 * @return windTypeOne 债券一级分类
	 */
	public String getWindTypeOne() {
		return this.windTypeOne;
	}

	/**
	 * 设置 债券一级分类
	 *
	 * @param windTypeOne 债券一级分类
	 */
	public void setWindTypeOne(String windTypeOne) {
		this.windTypeOne = windTypeOne;
	}

	/**
	 * 获取 单位净价
	 *
	 * @return cprice 单位净价
	 */
	public BigDecimal getCprice() {
		return this.cprice;
	}

	/**
	 * 设置 单位净价
	 *
	 * @param cprice 单位净价
	 */
	public void setCprice(BigDecimal cprice) {
		this.cprice = cprice;
	}

	/**
	 * 获取 单位应计利息
	 *
	 * @return uint 单位应计利息
	 */
	public BigDecimal getUint() {
		return this.uint;
	}

	/**
	 * 设置 单位应计利息
	 *
	 * @param uint 单位应计利息
	 */
	public void setUint(BigDecimal uint) {
		this.uint = uint;
	}

	/**
	 * 获取 单位全价
	 *
	 * @return fprice 单位全价
	 */
	public BigDecimal getFprice() {
		return this.fprice;
	}

	/**
	 * 设置 单位全价
	 *
	 * @param fprice 单位全价
	 */
	public void setFprice(BigDecimal fprice) {
		this.fprice = fprice;
	}
}
