package com.joyintech.tams.invest.product.valuation.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class FinProductValuationDetail001Dto extends FinProductValuation001Dto {

	private static final long serialVersionUID = -6686521013281181635L;

	private Long pvalueid;
	/**
	 * 类型
	 */
	private String vltype;
	/**
	 * 资产一级分类
	 */
	private String firstType;
	/**
	 * 资产类型
	 */
	private String assetType;
	/**
	 * 资产代码
	 */
	private String assetCode;
	/**
	 * 资产名称
	 */
	private String assetName;
	/**
	 * 投资目的
	 */
	private String invtGoals;
	/**
	 * 资产到期日
	 */
	private Date assetMdate;
	/**
	 * 面额
	 */
	private BigDecimal faceAmt;
	/**
	 * 单位价值
	 */
	private BigDecimal cprice;
	/**
	 * 资产价值
	 */
	private BigDecimal assetValue;
	/**
	 * 久期
	 */
	private BigDecimal duration;
	/**
	 * 通道
	 */
	private String channel;
	/**
	 * 管理账户
	 */
	private String mgrAcct;
	/**
	 * 证券托管户
	 */
	private String bondAcct;
	/**
	 * 投资收益率
	 */
	private BigDecimal invtYield;
	/**
	 * 汇率
	 */
	private BigDecimal exchange;
	/**
	 * 费用名称
	 */
	private String feename;
	/**
	 * 计费基础
	 */
	private String basis;
	/**
	 * 应计费用
	 */
	private BigDecimal feeamt;

	public Long getPvalueid() {
		return pvalueid;
	}

	public void setPvalueid(Long pvalueid) {
		this.pvalueid = pvalueid;
	}

	public String getVltype() {
		return vltype;
	}

	public void setVltype(String vltype) {
		this.vltype = vltype;
	}

	public String getFirstType() {
		return firstType;
	}

	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getFirstTypeStr() {
		if ("HQZH".equals(assetType))
			return "托管行活期利息";
		else if ("HOLDCASH".equals(assetType))
			return "剩余现金";
		else
			return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, assetType);
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getInvtGoals() {
		return invtGoals;
	}

	public void setInvtGoals(String invtGoals) {
		this.invtGoals = invtGoals;
	}

	public String getInvtGoalsStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.投资目的, invtGoals);
	}

	public Date getAssetMdate() {
		return assetMdate;
	}

	public void setAssetMdate(Date assetMdate) {
		this.assetMdate = assetMdate;
	}

	public BigDecimal getFaceAmt() {
		return faceAmt;
	}

	public void setFaceAmt(BigDecimal faceAmt) {
		this.faceAmt = faceAmt;
	}

	public BigDecimal getCprice() {
		return cprice;
	}

	public void setCprice(BigDecimal cprice) {
		this.cprice = cprice;
	}

	public BigDecimal getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(BigDecimal assetValue) {
		this.assetValue = assetValue;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMgrAcct() {
		return mgrAcct;
	}

	public void setMgrAcct(String mgrAcct) {
		this.mgrAcct = mgrAcct;
	}

	public String getBondAcct() {
		return bondAcct;
	}

	public void setBondAcct(String bondAcct) {
		this.bondAcct = bondAcct;
	}

	public BigDecimal getInvtYield() {
		return invtYield;
	}

	public void setInvtYield(BigDecimal invtYield) {
		this.invtYield = invtYield;
	}

	public BigDecimal getExchange() {
		return exchange;
	}

	public void setExchange(BigDecimal exchange) {
		this.exchange = exchange;
	}

	public String getFeename() {
		return feename;
	}

	public void setFeename(String feename) {
		this.feename = feename;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public BigDecimal getFeeamt() {
		return feeamt;
	}

	public void setFeeamt(BigDecimal feeamt) {
		this.feeamt = feeamt;
	}

}
