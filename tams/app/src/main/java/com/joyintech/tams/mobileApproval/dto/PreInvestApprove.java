package com.joyintech.tams.mobileApproval.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class PreInvestApprove extends BaseEntity {
	private Date	approveDate	;//	申报时间
	private String	quotaCode	;//	额度申请编号
	private String	quotaType	;//	额度类型
	private String	approveType	;//	审批类型
	private String	assetName	;//	资产名称
	private String	assetType	;//	资产种类
	private String	assetCode	;//	资产代码
	private String	finanUser	;//	融资人名称
	private String	ztRating	;//	主体评级
	private String	zxRating	;//	债项评级
	private String	dbRating	;//	担保评级
	private String	assetTerm	;//	资产剩余期限
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal	declareAmt	;//	申报金额
	private String 	declareRate	;//	申报投资收益率
	private Date	effstartdate	;//	有效开始日
	private Date	effenddate	;//	有效结束日
	private String	cleanprice	;//	百元交易净价
	private String	approveGress	;//	申报进度
	private String	approveRate	;//	审批利率
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal	approveAmt	;//	审批规模
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal	alrAmt	;//	已经投放规模
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal	surAmt	;//	剩余投放规模
	private String	remark	;//	备注
	private String	wflLevel	;//	流程审批级
	private String seqno;//授信额度代码
	private String  surplusCreditAmt;//剩余授信额度
	private String  creditAmt;//融资人总授信额度
	private String  finanUserName;//融资人名称
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public String getQuotaCode() {
		return quotaCode;
	}
	public void setQuotaCode(String quotaCode) {
		this.quotaCode = quotaCode;
	}
	public String getQuotaType() {
		return quotaType;
	}
	public void setQuotaType(String quotaType) {
		this.quotaType = quotaType;
	}
	public String getApproveType() {
		return approveType;
	}
	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getFinanUser() {
		return finanUser;
	}
	public void setFinanUser(String finanUser) {
		this.finanUser = finanUser;
	}
	public String getZtRating() {
		return ztRating;
	}
	public void setZtRating(String ztRating) {
		this.ztRating = ztRating;
	}
	public String getZxRating() {
		return zxRating;
	}
	public void setZxRating(String zxRating) {
		this.zxRating = zxRating;
	}
	public String getDbRating() {
		return dbRating;
	}
	public void setDbRating(String dbRating) {
		this.dbRating = dbRating;
	}
	public String getAssetTerm() {
		return assetTerm;
	}
	public void setAssetTerm(String assetTerm) {
		this.assetTerm = assetTerm;
	}
	public BigDecimal getDeclareAmt() {
		return declareAmt;
	}
	public void setDeclareAmt(BigDecimal declareAmt) {
		this.declareAmt = declareAmt;
	}

	public String getDeclareRate() {
		return declareRate;
	}

	public void setDeclareRate(String declareRate) {
		this.declareRate = declareRate;
	}

	public Date getEffstartdate() {
		return effstartdate;
	}
	public void setEffstartdate(Date effstartdate) {
		this.effstartdate = effstartdate;
	}
	public Date getEffenddate() {
		return effenddate;
	}
	public void setEffenddate(Date effenddate) {
		this.effenddate = effenddate;
	}
	public String getApproveGress() {
		return approveGress;
	}
	public void setApproveGress(String approveGress) {
		this.approveGress = approveGress;
	}
	public BigDecimal getApproveAmt() {
		return approveAmt;
	}
	public void setApproveAmt(BigDecimal approveAmt) {
		this.approveAmt = approveAmt;
	}
	public BigDecimal getAlrAmt() {
		return alrAmt;
	}
	public void setAlrAmt(BigDecimal alrAmt) {
		this.alrAmt = alrAmt;
	}
	public BigDecimal getSurAmt() {
		return surAmt;
	}
	public void setSurAmt(BigDecimal surAmt) {
		this.surAmt = surAmt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWflLevel() {
		return wflLevel;
	}
	public void setWflLevel(String wflLevel) {
		this.wflLevel = wflLevel;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getSurplusCreditAmt() {
		return surplusCreditAmt;
	}

	public void setSurplusCreditAmt(String surplusCreditAmt) {
		this.surplusCreditAmt = surplusCreditAmt;
	}

	public String getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

	public String getCleanprice() {
		return cleanprice;
	}

	public void setCleanprice(String cleanprice) {
		this.cleanprice = cleanprice;
	}

	public String getApproveRate() {
		return approveRate;
	}

	public void setApproveRate(String approveRate) {
		this.approveRate = approveRate;
	}

	public String getFinanUserName() {

		return finanUserName;
	}

	public void setFinanUserName(String finanUserName) {
		this.finanUserName = finanUserName;
	}
}