package com.joyintech.service.trade.buyoutrepo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

/**
 * 买断式回购交易新建
 * @author zhangliang
 *
 */
public class TrdOutRightRepoTradeDetailDto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1806018338501643163L;
	
	/**
	 * 元素Id
	 */
	private String elementId;
	
	/**
	 * 操作类型
	 */
	private String operateType;
	
	/**
	 * 是否保本
	 */
	private String isGuarantee;
	
	/**
	 * 交易编号
	 */
	private String tradeId;

	/**
	 * 委托代码
	 */
	private String entrustId;

	/**
	 * 组合代码
	 */
	private String portfolioId;

	/**
	 * 成交编号
	 */
	private String dealBussId;

	/**
	 * 交易日期
	 */
	private Date tradeDate;

	/**
	 * 交易市场
	 */
	private String tradeMarket;

	/**
	 * 交易方向
	 */
	private String ps;

	/**
	 * 标的资产代码
	 */
	private String finprodId;

	/**
	 * 清算速度
	 */
	private String settleSpeed;

	/**
	 * 期限品种
	 */
	private String termType;
	/**
	 * 回购期限
	 */
	private Long repoterm;
	/**
	 * 计息基础
	 */
	private String basis;
	/**
	 * 起息日
	 */
	private Date vdate;
	/**
	 * 到期日
	 */
	private Date mdate;
	/**
	 * 实际占款天数
	 */
	private String actAmtDays;
	/**
	 * 资金可用日
	 */
	private Date amtAvaDate;
	/**
	 * 交易日历
	 */
	private String dealCalId;
	/**
	 * 证券账户
	 */
	private String secAcctId;
	/**
	 * 通道
	 */
	private String chlId;

	/**
	 * 通道费
	 */
	private BigDecimal channelFee;

	/**
	 * 通道费
	 */
	private BigDecimal tradeAmtAndFee;

	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 券面总额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal secTotalPrin;

	/**
	 * 回购利率
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal repoRate;

	/**
	 * 首次净价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fCprice;

	/**
	 * 首次应计利息
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fIntAmt;

	/**
	 * 首次全价
	 */
	private BigDecimal fFprice;

	/**
	 * 首次收益率
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fYield;

	/**
	 * 首次结算金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fSettleAmt;

	/**
	 * 首次结算方式
	 */
	private String fSettleMethod;

	/**
	 * 到期净价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mCprice;

	/**
	 * 到期应计利息
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mIntAmt;

	/**
	 * 到期全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mFprice;

	/**
	 * 到期收益率%
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mYield;

	/**
	 * 到期结算金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mSettleAmt;

	/**
	 * 到期结算方式
	 */
	private String mSettleMethod;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件上传
	 */
	private transient List<UploadFile> uploadFileList;

    /**
     * 风险试算实体类
     */
    private transient List<RiskTrialRetDto> riskTrialRetDto;

	/**
	 * 结算费
	 */
	private BigDecimal settleAmt;

	/**
	 * 交易手续费
	 */
	private BigDecimal procedureAmt;

	/**
	 * 佣金
	 */
	private BigDecimal commissionAmt;
    

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getDealBussId() {
		return dealBussId;
	}

	public void setDealBussId(String dealBussId) {
		this.dealBussId = dealBussId;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getSettleSpeed() {
		return settleSpeed;
	}

	public void setSettleSpeed(String settleSpeed) {
		this.settleSpeed = settleSpeed;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public Long getRepoterm() {
		return repoterm;
	}

	public void setRepoterm(Long repoterm) {
		this.repoterm = repoterm;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
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

	public String getActAmtDays() {
		return actAmtDays;
	}

	public void setActAmtDays(String actAmtDays) {
		this.actAmtDays = actAmtDays;
	}

	public Date getAmtAvaDate() {
		return amtAvaDate;
	}

	public void setAmtAvaDate(Date amtAvaDate) {
		this.amtAvaDate = amtAvaDate;
	}

	public String getDealCalId() {
		return dealCalId;
	}

	public void setDealCalId(String dealCalId) {
		this.dealCalId = dealCalId;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	/**
	 * 获取 chlId
	 *
	 * @return chlId
	 */
	public String getChlId() {
		return chlId;
	}

	/**
	 * 获取 channelFee
	 *
	 * @return channelFee
	 */
	public BigDecimal getChannelFee() {
		return channelFee;
	}

	/**
	 * 设置 channelFee
	 *
	 * @param channelFee channelFee
	 */
	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}

	/**
	 * 设置 chlId
	 *
	 * @param chlId chlId
	 */
	public void setChlId(String chlId) {
		this.chlId = chlId;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getSecTotalPrin() {
		return secTotalPrin;
	}

	public void setSecTotalPrin(BigDecimal secTotalPrin) {
		this.secTotalPrin = secTotalPrin;
	}

	public BigDecimal getRepoRate() {
		return repoRate;
	}

	public void setRepoRate(BigDecimal repoRate) {
		this.repoRate = repoRate;
	}

	public BigDecimal getfCprice() {
		return fCprice;
	}

	public void setfCprice(BigDecimal fCprice) {
		this.fCprice = fCprice;
	}

	public BigDecimal getfIntAmt() {
		return fIntAmt;
	}

	public void setfIntAmt(BigDecimal fIntAmt) {
		this.fIntAmt = fIntAmt;
	}

	public BigDecimal getfFprice() {
		return fFprice;
	}

	public void setfFprice(BigDecimal fFprice) {
		this.fFprice = fFprice;
	}

	public BigDecimal getfYield() {
		return fYield;
	}

	public void setfYield(BigDecimal fYield) {
		this.fYield = fYield;
	}

	public BigDecimal getfSettleAmt() {
		return fSettleAmt;
	}

	public void setfSettleAmt(BigDecimal fSettleAmt) {
		this.fSettleAmt = fSettleAmt;
	}

	public String getfSettleMethod() {
		return fSettleMethod;
	}

	public void setfSettleMethod(String fSettleMethod) {
		this.fSettleMethod = fSettleMethod;
	}

	public BigDecimal getmCprice() {
		return mCprice;
	}

	public void setmCprice(BigDecimal mCprice) {
		this.mCprice = mCprice;
	}

	public BigDecimal getmIntAmt() {
		return mIntAmt;
	}

	public void setmIntAmt(BigDecimal mIntAmt) {
		this.mIntAmt = mIntAmt;
	}

	public BigDecimal getmFprice() {
		return mFprice;
	}

	public void setmFprice(BigDecimal mFprice) {
		this.mFprice = mFprice;
	}

	public BigDecimal getmYield() {
		return mYield;
	}

	public void setmYield(BigDecimal mYield) {
		this.mYield = mYield;
	}

	public BigDecimal getmSettleAmt() {
		return mSettleAmt;
	}

	public void setmSettleAmt(BigDecimal mSettleAmt) {
		this.mSettleAmt = mSettleAmt;
	}

	public String getmSettleMethod() {
		return mSettleMethod;
	}

	public void setmSettleMethod(String mSettleMethod) {
		this.mSettleMethod = mSettleMethod;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	public List<RiskTrialRetDto> getRiskTrialRetDto() {
		return riskTrialRetDto;
	}

	public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
		this.riskTrialRetDto = riskTrialRetDto;
	}
	
	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getBusiType(){
		return DictCodeList.共通业务类型_质押式回购交易.getValue();
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getIsGuarantee() {
		return isGuarantee;
	}

	public void setIsGuarantee(String isGuarantee) {
		this.isGuarantee = isGuarantee;
	}

	/**
	 * 获取 tradeAmtAndFee
	 *
	 * @return tradeAmtAndFee
	 */
	public BigDecimal getTradeAmtAndFee() {
		return tradeAmtAndFee;
	}

	/**
	 * 设置 tradeAmtAndFee
	 *
	 * @param tradeAmtAndFee tradeAmtAndFee
	 */
	public void setTradeAmtAndFee(BigDecimal tradeAmtAndFee) {
		this.tradeAmtAndFee = tradeAmtAndFee;
	}

	/**
	 * 获取 settleAmt
	 *
	 * @return settleAmt.
	 */
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	/**
	 * 设置 settleAmt
	 *
	 * @param settleAmt settleAmt
	 */
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	/**
	 * 获取 procedureAmt
	 *
	 * @return procedureAmt.
	 */
	public BigDecimal getProcedureAmt() {
		return procedureAmt;
	}

	/**
	 * 设置 procedureAmt
	 *
	 * @param procedureAmt procedureAmt
	 */
	public void setProcedureAmt(BigDecimal procedureAmt) {
		this.procedureAmt = procedureAmt;
	}

	/**
	 * 获取 commissionAmt
	 *
	 * @return commissionAmt.
	 */
	public BigDecimal getCommissionAmt() {
		return commissionAmt;
	}

	/**
	 * 设置 commissionAmt
	 *
	 * @param commissionAmt commissionAmt
	 */
	public void setCommissionAmt(BigDecimal commissionAmt) {
		this.commissionAmt = commissionAmt;
	}

}
