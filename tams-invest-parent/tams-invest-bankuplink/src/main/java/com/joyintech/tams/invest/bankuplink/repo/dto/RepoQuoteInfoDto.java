/*
 * 文件名：BondQuoteInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.repo.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * 质押券报价基本信息Dto
 * 
 * @author yufei
 * @version 1.0 2020年5月15日
 * @see RepoQuoteInfoDto
 * @since 1.0
 */
public class RepoQuoteInfoDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8967384621506273L;

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 本方角色
     */
    private String quoteRoleType;

    /**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 报价类型，对话报价/请求报价/质押式回购报价
     */
    private String quoteType;

    /**
     * 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    private String tradeStatus;

    /**
     * 发送状态，待发送/成功/失败/发送中
     */
    private String sendStatus;

    /**
     * 本方银行间机构，6位编号
     */
    private String ouPartyId;

    /**
     * 本方银行间交易员
     */
    private String ouTraderId;

    /**
     * 对手银行间机构，6位编号
     */
    private String cpPartyId;

    /**
     * 对手方银行间交易员
     */
    private String cpTrader;

    /**
     * 请求有效时间
     */
    private String reqEffTime;

    /**
     * 回复有效时间
     */
    private String resEffTime;

    /**
     * 回复量(份额)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 清算速度
     */
    private Long settleSpeed;

    /**
     * 单位净价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 到期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 回购利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 券面（万）(份额 * 100)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 资产一级分类
     */
    private String assetTypeOne;

    /**
     * CFETS报价编号
     */
    private String cfetsQuoteId;

    /**
     * 成交编号
     */
    private String fTradeId;
    
    /**
     * 
     */
    private String validStatus;
    /**
     * 全价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 回购期限(天)
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.term")
    private long term;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 首期结算日
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.vSettleDate")
    private Date vSettleDate;

    /**
     * 到期结算日
     */
    @NotEmpty(fieldName = "CfetsBondDealQuotedPrice002.mSettleDate")
    private Date mSettleDate;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 是否多组合
     */
    private String isDzh;

    /**
     * 获取 entrustId
     * 
     * @return entrustId.
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 entrustId
     * 
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 term
     * 
     * @return term.
     */
    public long getTerm() {
        return term;
    }

    /**
     * 设置 term
     * 
     * @param term term
     */
    public void setTerm(long term) {
        this.term = term;
    }

    /**
     * 获取 vSettleDate
     * 
     * @return vSettleDate.
     */
    public Date getvSettleDate() {
        return vSettleDate;
    }

    /**
     * 获取 vSettleDate
     * 
     * @return vSettleDate.
     */
    public String getvSettleDateStr() {
        return DateUtil.format(vSettleDate);
    }

    /**
     * 设置 vSettleDate
     * 
     * @param vSettleDate vSettleDate
     */
    public void setvSettleDate(Date vSettleDate) {
        this.vSettleDate = vSettleDate;
    }

    /**
     * 获取 mSettleDate
     * 
     * @return mSettleDate.
     */
    public Date getmSettleDate() {
        return mSettleDate;
    }

    /**
     * 获取 mSettleDate
     * 
     * @return mSettleDate.
     */
    public String getmSettleDateStr() {
        return DateUtil.format(mSettleDate);
    }

    /**
     * 设置 mSettleDate
     * 
     * @param mSettleDate mSettleDate
     */
    public void setmSettleDate(Date mSettleDate) {
        this.mSettleDate = mSettleDate;
    }

    /**
     * 获取 fpriceAmt
     * 
     * @return fpriceAmt.
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 fpriceAmt
     * 
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 获取 quoteId
     * 
     * @return quoteId.
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 quoteId
     * 
     * @param quoteId quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * 获取 quoteRoleType
     * 
     * @return quoteRoleType.
     */
    public String getQuoteRoleType() {
        return quoteRoleType;
    }

    /**
     * 设置 quoteRoleType
     * 
     * @param quoteRoleType quoteRoleType
     */
    public void setQuoteRoleType(String quoteRoleType) {
        this.quoteRoleType = quoteRoleType;
    }

    /**
     * 获取 ps
     * 
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     * 
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodAbbr
     * 
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 quoteType
     * 
     * @return quoteType.
     */
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * 设置 quoteType
     * 
     * @param quoteType quoteType
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    /**
     * 获取 tradeStatus
     * 
     * @return tradeStatus.
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 tradeStatus
     * 
     * @param tradeStatus tradeStatus
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 获取 sendStatus
     * 
     * @return sendStatus.
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 sendStatus
     * 
     * @param sendStatus sendStatus
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取 ouPartyId
     * 
     * @return ouPartyId.
     */
    public String getOuPartyId() {
        return ouPartyId;
    }

    /**
     * 设置 ouPartyId
     * 
     * @param ouPartyId ouPartyId
     */
    public void setOuPartyId(String ouPartyId) {
        this.ouPartyId = ouPartyId;
    }

    /**
     * 获取 ouTraderId
     * 
     * @return ouTraderId.
     */
    public String getOuTraderId() {
        return ouTraderId;
    }

    /**
     * 设置 ouTraderId
     * 
     * @param ouTraderId ouTraderId
     */
    public void setOuTraderId(String ouTraderId) {
        this.ouTraderId = ouTraderId;
    }

    /**
     * 获取 cpPartyId
     * 
     * @return cpPartyId.
     */
    public String getCpPartyId() {
        return cpPartyId;
    }

    /**
     * 设置 cpPartyId
     * 
     * @param cpPartyId cpPartyId
     */
    public void setCpPartyId(String cpPartyId) {
        this.cpPartyId = cpPartyId;
    }

    /**
     * 获取 cpTrader
     * 
     * @return cpTrader.
     */
    public String getCpTrader() {
        return cpTrader;
    }

    /**
     * 设置 cpTrader
     * 
     * @param cpTrader cpTrader
     */
    public void setCpTrader(String cpTrader) {
        this.cpTrader = cpTrader;
    }

    /**
     * 获取 reqEffTime
     * 
     * @return reqEffTime.
     */
    public String getReqEffTime() {
        return reqEffTime;
    }

    /**
     * 设置 reqEffTime
     * 
     * @param reqEffTime reqEffTime
     */
    public void setReqEffTime(String reqEffTime) {
        this.reqEffTime = reqEffTime;
    }

    /**
     * 获取 resEffTime
     * 
     * @return resEffTime.
     */
    public String getResEffTime() {
        return resEffTime;
    }

    /**
     * 设置 resEffTime
     * 
     * @param resEffTime resEffTime
     */
    public void setResEffTime(String resEffTime) {
        this.resEffTime = resEffTime;
    }

    /**
     * 获取 shareAmt
     * 
     * @return shareAmt.
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 shareAmt
     * 
     * @param shareAmt shareAmt
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 获取 settleSpeed
     * 
     * @return settleSpeed.
     */
    public Long getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 settleSpeed
     * 
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(Long settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 unitCprice
     * 
     * @return unitCprice.
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 unitCprice
     * 
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 获取 yield
     * 
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     * 
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 prinAmt
     * 
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     * 
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 assetTypeOne
     * 
     * @return assetTypeOne.
     */
    public String getAssetTypeOne() {
        return assetTypeOne;
    }

    /**
     * 设置 assetTypeOne
     * 
     * @param assetTypeOne assetTypeOne
     */
    public void setAssetTypeOne(String assetTypeOne) {
        this.assetTypeOne = assetTypeOne;
    }

    /**
     * 获取 cfetsQuoteId
     * 
     * @return cfetsQuoteId.
     */
    public String getCfetsQuoteId() {
        return cfetsQuoteId;
    }

    /**
     * 设置 cfetsQuoteId
     * 
     * @param cfetsQuoteId cfetsQuoteId
     */
    public void setCfetsQuoteId(String cfetsQuoteId) {
        this.cfetsQuoteId = cfetsQuoteId;
    }

    /**
     * 获取 fTradeId
     * 
     * @return fTradeId.
     */
    public String getfTradeId() {
        return fTradeId;
    }

    /**
     * 设置 fTradeId
     * 
     * @param fTradeId fTradeId
     */
    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId;
    }

    public String getQuoteRoleTypeStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.银行间上行本方角色,
            this.getQuoteRoleType());
    }

    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.回购拆借方向, this.getPs());
    }

    public String getQuoteTypeStr() {
        return "对话";
    }

    public String getTradeStatusStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.银行间上行报价状态, this.getTradeStatus());
    }

    public String getSendStatusStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.银行间上行发送状态, this.getSendStatus());
    }

    public BigDecimal getShareAmtHun() {
        if (this.getShareAmt() != null) {
            return ArithmeticUtil.divide(this.getShareAmt(), new BigDecimal(10000), 6);
        }
        return null;
    }

    public String getSettleSpeedStr() {
        if (this.getSettleSpeed() != null && this.getSettleSpeed() == 0L) {
            return BussinessDictCodeList.清算速度_T加0.getCodeName();
        }
        else if (this.getSettleSpeed() != null && this.getSettleSpeed() == 1L) {
            return BussinessDictCodeList.清算速度_T加1.getCodeName();
        }
        return null;
    }

    public BigDecimal getPrinAmtHun() {
        if (this.getPrinAmt() != null) {
            return ArithmeticUtil.divide(this.getPrinAmt(), new BigDecimal(10000), 6);
        }
        return null;
    }

    public String getAssetTypeOneStr() {
    	return this.getAssetTypeOne();
        //return SysDataUtil.getNameFromDataDict(BussinessDictCode.平安资产一级分类, this.getAssetTypeOne());
    }

    /**
     * 获取 rate
     * 
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 rate
     * 
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    // add by qufangxu at 2020-05-25 start

    /**
     * 报价日期（交易日期）
     */
    private Date quoteDate;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 取得 报价日期（交易日期）
     * 
     * @return
     */
    public Date getQuoteDate() {
        return quoteDate;
    }

    /**
     * 设置 报价日期（交易日期）
     * 
     * @param quoteDate
     */
    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    /**
     * 取得 组合代码
     * 
     * @return
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * 
     * @param portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    // add by qufangxu at 2020-05-25 start

    private String cpTraderId;

    private String execid;

    private String repoPs;

    public String getCpTraderId() {
        return cpTraderId;
    }

    public void setCpTraderId(String cpTraderId) {
        this.cpTraderId = cpTraderId;
    }

    public String getExecid() {
        return execid;
    }

    public void setExecid(String execid) {
        this.execid = execid;
    }

    public String getRepoPs() {
        return repoPs;
    }

    public void setRepoPs(String repoPs) {
        this.repoPs = repoPs;
    }

    public String getRepoPsStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.回购拆借方向, this.getRepoPs());
    }

    private String ouTraderName;

    private String partyAbbr;

    private String partyName;

    private String partyCustomerId;

    private String partyAbbrEn;

    private String partyNameEn;

    public String getOuTraderName() {
        return ouTraderName;
    }

    public void setOuTraderName(String ouTraderName) {
        this.ouTraderName = ouTraderName;
    }

    public String getPartyAbbr() {
        return partyAbbr;
    }

    public void setPartyAbbr(String partyAbbr) {
        this.partyAbbr = partyAbbr;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyCustomerId() {
        return partyCustomerId;
    }

    public void setPartyCustomerId(String partyCustomerId) {
        this.partyCustomerId = partyCustomerId;
    }

    public String getPartyAbbrEn() {
        return partyAbbrEn;
    }

    public void setPartyAbbrEn(String partyAbbrEn) {
        this.partyAbbrEn = partyAbbrEn;
    }

    public String getPartyNameEn() {
        return partyNameEn;
    }

    public void setPartyNameEn(String partyNameEn) {
        this.partyNameEn = partyNameEn;
    }

    /**
     * 开始日
     */
    private String startQuoteDate;

    /**
     * 结束日
     */
    private String endQuoteDate;

    public String getStartQuoteDate() {
        return startQuoteDate;
    }

    public void setStartQuoteDate(String startQuoteDate) {
        this.startQuoteDate = startQuoteDate;
    }

    public String getEndQuoteDate() {
        return endQuoteDate;
    }

    public void setEndQuoteDate(String endQuoteDate) {
        this.endQuoteDate = endQuoteDate;
    }

    private String cpTraderName;

    private String ouPartyName;

    private String cpPartyName;

    public String getCpTraderName() {
        return cpTraderName;
    }

    public void setCpTraderName(String cpTraderName) {
        this.cpTraderName = cpTraderName;
    }

    public String getOuPartyName() {
        return ouPartyName;
    }

    public void setOuPartyName(String ouPartyName) {
        this.ouPartyName = ouPartyName;
    }

    public String getCpPartyName() {
        return cpPartyName;
    }

    public void setCpPartyName(String cpPartyName) {
        this.cpPartyName = cpPartyName;
    }

    /**
     * 获取 termType
     * 
     * @return termType.
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 termType
     * 
     * @param termType termType
     */
    public void setTermType(String termType) {
        this.termType = termType;
    }

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	
	public String getValidStatusStr() {
		if (StringUtils.equals(this.getValidStatus(), BussinessDictCodeList.有效状态_流程中.getValue())) {
			return BussinessDictCodeList.有效状态_流程中.getCodeName();
		} else {
			return BussinessDictCodeList.有效状态_有效.getCodeName();
		}
	}

    public String getIsDzh() {
        return isDzh;
    }

    public void setIsDzh(String isDzh) {
        this.isDzh = isDzh;
    }
}
