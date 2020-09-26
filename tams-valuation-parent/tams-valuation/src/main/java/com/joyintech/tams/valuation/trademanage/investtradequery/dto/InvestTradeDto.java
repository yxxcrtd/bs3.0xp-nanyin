/*
 * 文件名：InvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 投资交易信息实体类
 * 
 * @author yangjian
 * @version 1.0 2018年7月25日
 * @see InvestTradeDto
 * @since 1.0
 */
public class InvestTradeDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 业务类型Code
     */
    private String busiTypeCode;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 投资主体代码（产品代码）
     */
    private String prodId;

    /**
     * 投资主体名称（产品名称）
     */
    private String prodAbbr;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产简称
     */
    private String assetAbbr;

    /**
     * 交易方向Code
     */
    private String psCode;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易场所Code
     */
    private String tradeMarketCode;
    
    /**
     * 收划款类型
     */
    private String type;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 投资目的Code
     */
    private String invAimCode;

    /**
     * 起息日
     */
    private String vDate;

    /**
     * 到期日
     */
    private String mDate;
    


    /**
     * 发生日 - 取交易日期
     */
    private String tradeDate;

    /**
     * 发生日 - 开始日期
     */
    private String tradeDateBegin;

    /**
     * 发生日 - 结束日期
     */
    private String tradeDateEnd;

    /**
     * 交割日
     */
    private String settleDate;
    
    /**
     * 收划款日期
     */
    private String settleDat;
    
    /**
     * 收划款金额
     */
    private BigDecimal settleAmt;

    /**
     * 到账日
     */
    private String payDate;

    /**
     * 净值日
     */
    private String cpriceDate;

    /**
     * 分红日
     */
    private String branchDate;

    /**
     * 红利再投日
     */
    private String bonusReinvestDate;

    /**
     * 入账日
     */
    private String bookDate;

    /**
     * 申购日
     */
    private String purchaseDate;

    /**
     * 计息开始日
     */
    private String interestBeginDate;

    /**
     * 计息截止日
     */
    private String interestEndDate;

    /**
     * 计划支付日
     */
    private String planPayDate;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 交易币种Code
     */
    private String ccyCode;
    
    /**
     * 期限品种_回购
     */
    private String termType;
    /**
     * 期限品种——拆借
     */
    private String termTypeLend;
    
    /**
     * 期限品种——拆借
     */
    private String termTypeLendCode; 


    /**
     * 确认日
     */
    private String confirmDate;
    
    /**
     * 计费基础
     */
    private String basis;

    /**
     * 交易金额
     */
    private BigDecimal cdAmt;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 登记托管机构名称
     */
    private String registOrgName;

    /**
     * 是否随交易支付
     */
    private String isPayWithTrade;

    /**
     * 是否随交易支付 名称
     */
    private String isPayWithTradeStr;
    
    

    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    
    /**
     * 获取 cdAmt
     * @return cdAmt.
     */
    public BigDecimal getCdAmt() {
        return cdAmt;
    }

    
    /**
     * 设置 cdAmt
     * @param cdAmt cdAmt
     */
    public void setCdAmt(BigDecimal cdAmt) {
        this.cdAmt = cdAmt;
    }

    /**
     * 获取 confirmDate
     * 
     * @return confirmDate.
     */
    public String getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置 confirmDate
     * 
     * @param confirmDate confirmDate
     */
    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }
    
    
    /**
     * 获取 termTypeLendt
     * @return termTypeLend.
     */
    public String getTermTypeLend() {
         return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.期限品种_内部拆借, this.termTypeLend))?
            SysDataUtil.getNameFromDataDict(DictCode.期限品种_内部拆借, this.termTypeLend):termTypeLend;
    }


    /**
     * 设置 termTypeLend
     * @param termTypeLend termTypeLend
     */
    public void setTermTypeLend(String termTypeLend) {
        this.termTypeLend = termTypeLend;
    }

    /**
     * 收款账户户名
     */
    private String myAcctName;

    /**
     * 收款账户账号
     */
    private String myAcctNo;
    
    /**
     * 付款账户户名
     */
    private String counterAcctName;

    /**
     * 付款账户账号
     */
    private String counterAcctNo;
        
    
    /** 派息金额 **/
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal divAmt;
    
    /** 转入/转出利息金额 **/
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tranAmt;
    
    /**
     * 随交易支付费用
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeAmtPay;

    /**
     * 不随交易支付费用
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeAmtUnpay;

    public BigDecimal getFeeAmtPay() {
        return feeAmtPay;
    }

    public void setFeeAmtPay(BigDecimal feeAmtPay) {
        this.feeAmtPay = feeAmtPay;
    }

    public BigDecimal getFeeAmtUnpay() {
        return feeAmtUnpay;
    }

    public void setFeeAmtUnpay(BigDecimal feeAmtUnpay) {
        this.feeAmtUnpay = feeAmtUnpay;
    }
    
    
    
    public BigDecimal getDivAmt() {
        return null!=divAmt?divAmt:BigDecimal.ZERO;
    }


    public void setDivAmt(BigDecimal divAmt) {
        this.divAmt = divAmt;
    }


    public BigDecimal getTranAmt() {
        return null!=tranAmt?tranAmt:BigDecimal.ZERO;
    }


    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }


    /**
     * 获取 termType
     * @return termType.
     */
    public String getTermType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.期限品种_回购, this.termType))?
            SysDataUtil.getNameFromDataDict(DictCode.期限品种_回购, this.termType):termType;
    }


    /**
     * 设置 termType
     * @param termType termType
     */
    public void setTermType(String termType) {
        this.termType = termType;
    }


    /**
     * 券面总额/交易本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 交易份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal shareAmt;
    
    /**
     * 红利再投金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bonusAmt;
    
    /**
     * 应计利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 单位净价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 再投单位净值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal reinvestUnitCprice;

    /**
     * 单位全价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitFprice;

    /**
     * 净价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 应收利息总额 - 取利息总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 单位利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 全价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 总交易费用
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /**
     * 通道费
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tdFeeAmt;

    /**
     * 交易金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    
    /**
     * 现金分红金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cashAmt;

    /**
     * 到期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;
    /**
     * 理论利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal theoryIntAmt;
    
    
    private String yield1;

    /**
     * 行权收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal exerYield;
    private String exerYield1;
    /**
     * 单位分红
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitBranch;

    /**
     * 现金分红金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cashBranchAmt;

    /**
     * 红利再投份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bonusReinvestQty;

    /**
     * 红利再投金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bonusReinvestAmt;

    /**
     * 红利再投金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal returnAmt;
    
    /**
     * 红利再投份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bonusShare;

    /**
     * 应收红利
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAmt;

    /**
     * 实收红利
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal realAmt;

    /**
     * 交易状态Code
     */
    private String tradeStatusCode;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 收益类型Code
     */
    private String profitTypeCode;

    /**
     * 收益类型，净值、预期收益、货币
     */
    private String profitType;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 交易对手名
     */
    private String counterName;

//    /**
//     * 交易对手类型Code
//     */
//    private String counterTypeCode;
//
//    /**
//     * 交易对手类型
//     */
//    private String counterType;

    /**
     * 是否外部交易，是、否
     */
    private String isOutTradeCode;
    
    /**
     * 是否外部交易，是、否
     */
    private String isOutTrade;
    
    /**
     * 通道
     */
    private String chlId;

    /**
     * 交易员
     */
    private String trader;

    /**
     * 交易类型
     */
    private String finprodTypeCode;

    private String finprodTypeName;
    
    /**
     * 实际到账日
     */
    private String realDate;
    
    /**
     * 费用名称
     */
    private String feeTypeName;
    
    private String feeTypeNameCode;
    
    /**费用编号*/
    private String feeTypeCode;

    /**
     * 交易类型
     */
    private String finprodType;

    /**
     * 备注
     */
    private String remark;
    
    
    /**
     * 资金账户名
     */
    private String acctName;
    
    
    /**
     * 实际利息
     */
    private BigDecimal realIntAmt;
    
    /**
     * 新投资目的
     */
    private String newInvAim;
    
    /**
     * 获取 realIntAmt
     * 
     * @return realIntAmt.
     */
    public BigDecimal getRealIntAmt() {
        return null!=realIntAmt?realIntAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 realIntAmt
     * 
     * @param realIntAmt realIntAmt
     */
    public void setRealIntAmt(BigDecimal realIntAmt) {
        this.realIntAmt = realIntAmt;
    }
    
    /**
     * 到期结算金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal expSetAmt;
    
    /**
     * 获取 expSetAmt
     * @return expSetAmt.
     */
    public BigDecimal getExpSetAmt() {
        return null!=expSetAmt?expSetAmt:BigDecimal.ZERO;
    }

    
    /**
     * 设置 expSetAmt
     * @param expSetAmt expSetAmt
     */
    public void setExpSetAmt(BigDecimal expSetAmt) {
        this.expSetAmt = expSetAmt;
    }

    
    /**
     * 获取 firSetAmt
     * @return firSetAmt.
     */
    public BigDecimal getFirSetAmt() {
        return null!=firSetAmt?firSetAmt:BigDecimal.ZERO;
    }

    
    /**
     * 设置 firSetAmt
     * @param firSetAmt firSetAmt
     */
    public void setFirSetAmt(BigDecimal firSetAmt) {
        this.firSetAmt = firSetAmt;
    }

    /**
     * 到期结算金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal firSetAmt;
    /**
     * 获取 interestBeginDate
     * 
     * @return interestBeginDate.
     */
    public String getInterestBeginDate() {
        return interestBeginDate;
    }

    /**
     * 设置 interestBeginDate
     * 
     * @param interestBeginDate interestBeginDate
     */
    public void setInterestBeginDate(String interestBeginDate) {
        this.interestBeginDate = interestBeginDate;
    }

    /**
     * 获取 interestEndDate
     * 
     * @return interestEndDate.
     */
    public String getInterestEndDate() {
        return interestEndDate;
    }

    /**
     * 设置 interestEndDate
     * 
     * @param interestEndDate interestEndDate
     */
    public void setInterestEndDate(String interestEndDate) {
        this.interestEndDate = interestEndDate;
    }

    /**
     * 获取 planPayDate
     * 
     * @return planPayDate.
     */
    public String getPlanPayDate() {
        return planPayDate;
    }

    /**
     * 设置 planPayDate
     * 
     * @param planPayDate planPayDate
     */
    public void setPlanPayDate(String planPayDate) {
        this.planPayDate = planPayDate;
    }

    /**
     * 获取 reinvestUnitCprice
     * 
     * @return reinvestUnitCprice.
     */
    public BigDecimal getReinvestUnitCprice() {
        return null!=reinvestUnitCprice?reinvestUnitCprice:BigDecimal.ZERO;
    }

    /**
     * 设置 reinvestUnitCprice
     * 
     * @param reinvestUnitCprice reinvestUnitCprice
     */
    public void setReinvestUnitCprice(BigDecimal reinvestUnitCprice) {
        this.reinvestUnitCprice = reinvestUnitCprice;
    }

    /**
     * 获取 purchaseDate
     * 
     * @return purchaseDate.
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 设置 purchaseDate
     * 
     * @param purchaseDate purchaseDate
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdAbbr() {
        return prodAbbr;
    }

    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetAbbr() {
        return assetAbbr;
    }

    public void setAssetAbbr(String assetAbbr) {
        this.assetAbbr = assetAbbr;
    }

    public String getPs() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.ps))?
                SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.ps):this.ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPsCode() {
        return psCode;
    }

    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }

    public String getTradeMarket() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.tradeMarket))?
                SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.tradeMarket):this.tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradeMarketCode() {
        return tradeMarketCode;
    }

    public void setTradeMarketCode(String tradeMarketCode) {
        this.tradeMarketCode = tradeMarketCode;
    }

    public String getInvAim() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.投资目的, this.invAim))?
                SysDataUtil.getNameFromDataDict(DictCode.投资目的, this.invAim):this.invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getInvAimCode() {
        return invAimCode;
    }

    public void setInvAimCode(String invAimCode) {
        this.invAimCode = invAimCode;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCcy() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccy))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccy):this.ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public BigDecimal getPrinAmt() {
        return null!=prinAmt?prinAmt:BigDecimal.ZERO;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getUnitCprice() {
        return null!=unitCprice?unitCprice:BigDecimal.ZERO;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public BigDecimal getUnitFprice() {
        return null!=unitFprice?unitFprice:BigDecimal.ZERO;
    }

    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    public BigDecimal getCpriceAmt() {
        return null!=cpriceAmt?cpriceAmt:BigDecimal.ZERO;
    }

    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    public BigDecimal getIntAmt() {
        return null!=intAmt?intAmt:BigDecimal.ZERO;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getFpriceAmt() {
        return null!=fpriceAmt?fpriceAmt:BigDecimal.ZERO;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getFeeAmt() {
        return null!=feeAmt?feeAmt:BigDecimal.ZERO;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public BigDecimal getTdFeeAmt() {
        return null!=tdFeeAmt?tdFeeAmt:BigDecimal.ZERO;
    }

    public void setTdFeeAmt(BigDecimal tdFeeAmt) {
        this.tdFeeAmt = tdFeeAmt;
    }

    public BigDecimal getTradeAmt() {
        return null!=tradeAmt?tradeAmt:BigDecimal.ZERO;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getYield() {
        return null!=yield?yield:BigDecimal.ZERO;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public BigDecimal getExerYield() {
        return null!=exerYield?exerYield:BigDecimal.ZERO;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    
    /**
     * 获取 yield1
     * @return yield1.
     */
    public String getYield1() {
        return yield1;
    }

    
    /**
     * 设置 yield1
     * @param yield1 yield1
     */
    public void setYield1(String yield1) {
        this.yield1 = yield1;
    }

    
    /**
     * 获取 exerYield1
     * @return exerYield1.
     */
    public String getExerYield1() {
        return exerYield1;
    }

    
    /**
     * 设置 exerYield1
     * @param exerYield1 exerYield1
     */
    public void setExerYield1(String exerYield1) {
        this.exerYield1 = exerYield1;
    }

    public String getTradeStatus() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易状态, this.tradeStatus))?
                SysDataUtil.getNameFromDataDict(DictCode.交易状态, this.tradeStatus):this.tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeStatusCode() {
        return tradeStatusCode;
    }

    public void setTradeStatusCode(String tradeStatusCode) {
        this.tradeStatusCode = tradeStatusCode;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType))?
                SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType):this.profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getProfitTypeCode() {
        return profitTypeCode;
    }

    public void setProfitTypeCode(String profitTypeCode) {
        this.profitTypeCode = profitTypeCode;
    }

    /**
     * 获取 shareAmt
     * 
     * @return shareAmt.
     */
    public BigDecimal getShareAmt() {
        return null!=shareAmt?shareAmt:BigDecimal.ZERO;
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
     * 获取 settleDate
     * 
     * @return settleDate.
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     * 
     * @param settleDate settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 payDate
     * 
     * @return payDate.
     */
    public String getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     * 
     * @param payDate payDate
     */
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 cpriceDate
     * 
     * @return cpriceDate.
     */
    public String getCpriceDate() {
        return cpriceDate;
    }

    /**
     * 设置 cpriceDate
     * 
     * @param cpriceDate cpriceDate
     */
    public void setCpriceDate(String cpriceDate) {
        this.cpriceDate = cpriceDate;
    }

    /**
     * 获取 branchDate
     * 
     * @return branchDate.
     */
    public String getBranchDate() {
        return branchDate;
    }

    /**
     * 设置 branchDate
     * 
     * @param branchDate branchDate
     */
    public void setBranchDate(String branchDate) {
        this.branchDate = branchDate;
    }

    /**
     * 获取 bonusReinvestDate
     * 
     * @return bonusReinvestDate.
     */
    public String getBonusReinvestDate() {
        return bonusReinvestDate;
    }

    /**
     * 设置 bonusReinvestDate
     * 
     * @param bonusReinvestDate bonusReinvestDate
     */
    public void setBonusReinvestDate(String bonusReinvestDate) {
        this.bonusReinvestDate = bonusReinvestDate;
    }

    /**
    /**
     * 获取 unitBranch
     * 
     * @return unitBranch.
     */
    public BigDecimal getUnitBranch() {
        return null!=unitBranch?unitBranch:BigDecimal.ZERO;
    }

    /**
     * 设置 unitBranch
     * 
     * @param unitBranch unitBranch
     */
    public void setUnitBranch(BigDecimal unitBranch) {
        this.unitBranch = unitBranch;
    }

    /**
     * 获取 cashBranchAmt
     * 
     * @return cashBranchAmt.
     */
    public BigDecimal getCashBranchAmt() {
        return null!=cashBranchAmt?cashBranchAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 cashBranchAmt
     * 
     * @param cashBranchAmt cashBranchAmt
     */
    public void setCashBranchAmt(BigDecimal cashBranchAmt) {
        this.cashBranchAmt = cashBranchAmt;
    }

    /**
     * 获取 bonusReinvestQty
     * 
     * @return bonusReinvestQty.
     */
    public BigDecimal getBonusReinvestQty() {
        return null!=bonusReinvestQty?bonusReinvestQty:BigDecimal.ZERO;
    }

    /**
     * 设置 bonusReinvestQty
     * 
     * @param bonusReinvestQty bonusReinvestQty
     */
    public void setBonusReinvestQty(BigDecimal bonusReinvestQty) {
        this.bonusReinvestQty = bonusReinvestQty;
    }

    /**
     * 获取 bonusReinvestAmt
     * 
     * @return bonusReinvestAmt.
     */
    public BigDecimal getBonusReinvestAmt() {
        return null!=bonusReinvestAmt?bonusReinvestAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 bonusReinvestAmt
     * 
     * @param bonusReinvestAmt bonusReinvestAmt
     */
    public void setBonusReinvestAmt(BigDecimal bonusReinvestAmt) {
        this.bonusReinvestAmt = bonusReinvestAmt;
    }

    /**
     * 获取 tradeDateBegin
     * 
     * @return tradeDateBegin.
     */
    public String getTradeDateBegin() {
        return tradeDateBegin;
    }

    /**
     * 设置 tradeDateBegin
     * 
     * @param tradeDateBegin tradeDateBegin
     */
    public void setTradeDateBegin(String tradeDateBegin) {
        this.tradeDateBegin = tradeDateBegin;
    }

    /**
     * 获取 tradeDateEnd
     * 
     * @return tradeDateEnd.
     */
    public String getTradeDateEnd() {
        return tradeDateEnd;
    }

    /**
     * 设置 tradeDateEnd
     * 
     * @param tradeDateEnd tradeDateEnd
     */
    public void setTradeDateEnd(String tradeDateEnd) {
        this.tradeDateEnd = tradeDateEnd;
    }

    /**
     * 获取 counterId
     * 
     * @return counterId.
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 counterId
     * 
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

//    /**
//     * 获取 counterType
//     * 
//     * @return counterType.
//     */
//    public String getCounterType() {
//        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易对手类型, this.counterType))?
//                SysDataUtil.getNameFromDataDict(DictCode.交易对手类型, this.counterType):this.counterType;
//    }
//
//    /**
//     * 设置 counterType
//     * 
//     * @param counterType counterType
//     */
//    public void setCounterType(String counterType) {
//        this.counterType = counterType;
//    }
//
//    public String getCounterTypeCode() {
//        return counterTypeCode;
//    }
//
//    public void setCounterTypeCode(String counterTypeCode) {
//        this.counterTypeCode = counterTypeCode;
//    }
    
    public String getIsOutTradeCode() {
		return isOutTradeCode;
	}


	public void setIsOutTradeCode(String isOutTradeCode) {
		this.isOutTradeCode = isOutTradeCode;
	}


	public String getIsOutTrade() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否, isOutTrade);
	}


	public void setIsOutTrade(String isOutTrade) {
		this.isOutTrade = isOutTrade;
	}
	
	

    /**
     * 获取 chlId
     * 
     * @return chlId.
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     * 
     * @param chlId chlId
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    /**
     * 获取 trader
     * 
     * @return trader.
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 trader
     * 
     * @param trader trader
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取 vDate
     * 
     * @return vDate.
     */
    public String getvDate() {
        return vDate;
    }

    /**
     * 设置 vDate
     * 
     * @param vDate vDate
     */
    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 mDate
     * 
     * @return mDate.
     */
    public String getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     * 
     * @param mDate mDate
     */
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 unitInt
     * 
     * @return unitInt.
     */
    public BigDecimal getUnitInt() {
        return null!=unitInt?unitInt:BigDecimal.ZERO;
    }

    /**
     * 设置 unitInt
     * 
     * @param unitInt unitInt
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    public String getBusiType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.业务类型, this.busiType))?
                SysDataUtil.getNameFromDataDict(DictCode.业务类型, this.busiType):this.busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getBusiTypeCode() {
        return busiTypeCode;
    }

    public void setBusiTypeCode(String busiTypeCode) {
        this.busiTypeCode = busiTypeCode;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getFinprodType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType))?
                SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType):finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 finprodTypeName
     * 
     * @return finprodTypeName.
     */
    public String getFinprodTypeName() {
        // GZHSXT-1422
        String rStr = StringUtils.EMPTY;
        /** modify by dongjun on 20190325 投资端费用C11 时，显示交易费用 start**/
        if(StringUtil.equals(finprodTypeName, DictCodeListExt1.金融产品类型_交易费用.getValue())){
        	return DictCodeListExt1.金融产品类型_交易费用.getCodeName();
        }
        /** modify by dongjun on 20190325 投资端费用C11 时，显示交易费用 end**/
        if(StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, finprodTypeName))) {
            rStr = SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, finprodTypeName);
        }else if (StringUtils.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodTypeName))) {
            rStr = SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodTypeName);
        }else {
            rStr = finprodTypeName;
        }
        return rStr;
    }

    /**
     * 设置 finprodTypeName
     * 
     * @param finprodTypeName finprodTypeName
     */
    public void setFinprodTypeName(String finprodTypeName) {
        this.finprodTypeName = finprodTypeName;
    }

    public String getFinprodTypeCode() {
        return finprodTypeCode;
    }

    public void setFinprodTypeCode(String finprodTypeCode) {
        this.finprodTypeCode = finprodTypeCode;
    }

    /**
     * 获取 remark
     * 
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * 
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 returnAmt
     * 
     * @return returnAmt.
     */
    public BigDecimal getReturnAmt() {
        return null!=returnAmt?returnAmt:BigDecimal.ZERO;
    }

    /**
     * 设置 returnAmt
     * 
     * @param returnAmt returnAmt
     */
    public void setReturnAmt(BigDecimal returnAmt) {
        this.returnAmt = returnAmt;
    }

    
    public BigDecimal getIntRate() {
        return null!=intRate?intRate:BigDecimal.ZERO;
    }

    
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    
    public BigDecimal getCashAmt() {
        return null!=cashAmt?cashAmt:BigDecimal.ZERO;
    }
    
    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    
    public BigDecimal getBonusAmt() {
        return null!=bonusAmt?bonusAmt:BigDecimal.ZERO;
    }

    
    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    
    public BigDecimal getBonusShare() {
        return null!=bonusShare?bonusShare:BigDecimal.ZERO;
    }

    
    public void setBonusShare(BigDecimal bonusShare) {
        this.bonusShare = bonusShare;
    }

    
    public BigDecimal getbAmt() {
        return null!=bAmt?bAmt:BigDecimal.ZERO;
    }

    
    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }

    
    public BigDecimal getRealAmt() {
        return null!=realAmt?realAmt:BigDecimal.ZERO;
    }

    
    public void setRealAmt(BigDecimal realAmt) {
        this.realAmt = realAmt;
    }

    public String getFeeTypeName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.税类型, feeTypeName))
                ?SysDataUtil.getNameFromDataDict(DictCode.税类型, feeTypeName):feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    public String getFeeTypeCode() {
        return feeTypeCode;
    }

    public void setFeeTypeCode(String feeTypeCode) {
        this.feeTypeCode = feeTypeCode;
    }

    public BigDecimal getTheoryIntAmt() {
        return null!=theoryIntAmt?theoryIntAmt:BigDecimal.ZERO;
    }

    public void setTheoryIntAmt(BigDecimal theoryIntAmt) {
        this.theoryIntAmt = theoryIntAmt;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }


    /**
     * 获取 type
     * @return type.
     */
    public String getType() {
        return type;
    }


    /**
     * 设置 type
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * 获取 settleDat
     * @return settleDat.
     */
    public String getSettleDat() {
        return settleDat;
    }


    /**
     * 设置 settleDat
     * @param settleDat settleDat
     */
    public void setSettleDat(String settleDat) {
        this.settleDat = settleDat;
    }


    /**
     * 获取 settleAmt
     * @return settleAmt.
     */
    public BigDecimal getSettleAmt() {
        return null!=settleAmt?settleAmt:BigDecimal.ZERO;
    }


    /**
     * 设置 settleAmt
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }


    /**
     * 获取 myAcctName
     * @return myAcctName.
     */
    public String getMyAcctName() {
        return myAcctName;
    }


    /**
     * 设置 myAcctName
     * @param myAcctName myAcctName
     */
    public void setMyAcctName(String myAcctName) {
        this.myAcctName = myAcctName;
    }


    /**
     * 获取 myAcctNo
     * @return myAcctNo.
     */
    public String getMyAcctNo() {
        return myAcctNo;
    }


    /**
     * 设置 myAcctNo
     * @param myAcctNo myAcctNo
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }


    /**
     * 获取 counterAcctName
     * @return counterAcctName.
     */
    public String getCounterAcctName() {
        return counterAcctName;
    }


    /**
     * 设置 counterAcctName
     * @param counterAcctName counterAcctName
     */
    public void setCounterAcctName(String counterAcctName) {
        this.counterAcctName = counterAcctName;
    }


    /**
     * 获取 counterAcctNo
     * @return counterAcctNo.
     */
    public String getCounterAcctNo() {
        return counterAcctNo;
    }


    /**
     * 设置 counterAcctNo
     * @param counterAcctNo counterAcctNo
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }


    /**
     * 获取 bookDate
     * @return bookDate.
     */
    public String getBookDate() {
        return bookDate;
    }


    /**
     * 设置 bookDate
     * @param bookDate bookDate
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }


    /**
     * 获取 realDate
     * @return realDate.
     */
    public String getRealDate() {
        return realDate;
    }


    /**
     * 设置 realDate
     * @param realDate realDate
     */
    public void setRealDate(String realDate) {
        this.realDate = realDate;
    }

    
    public String getNewInvAimFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.投资目的, this.newInvAim))?
            SysDataUtil.getNameFromDataDict(DictCode.投资目的, this.newInvAim):this.newInvAim;
    }
    
    public String getNewInvAim() {
        return newInvAim;
    }

    
    public void setNewInvAim(String newInvAim) {
        this.newInvAim = newInvAim;
    }


    
    /**
     * 获取 feeTypeNameCode
     * @return feeTypeNameCode.
     */
    public String getFeeTypeNameCode() {
        return feeTypeNameCode;
    }


    
    /**
     * 设置 feeTypeNameCode
     * @param feeTypeNameCode feeTypeNameCode
     */
    public void setFeeTypeNameCode(String feeTypeNameCode) {
        this.feeTypeNameCode = feeTypeNameCode;
    }


	public String getTermTypeLendCode() {
		return termTypeLendCode;
	}


	public void setTermTypeLendCode(String termTypeLendCode) {
		this.termTypeLendCode = termTypeLendCode;
	}


    public String getRegistOrg() {
        return registOrg;
    }

    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }

    public String getIsPayWithTradeStr() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsPayWithTrade()))?
                SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsPayWithTrade()):this.getIsPayWithTrade();
    }

    public void setIsPayWithTradeStr(String isPayWithTradeStr) {
        this.isPayWithTradeStr = isPayWithTradeStr;
    }

    public String getRegistOrgName() {
        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, this.getRegistOrg());
    }

    public void setRegistOrgName(String registOrgName) {
        this.registOrgName = registOrgName;
    }
}
