/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.finprodtradequery.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 金融产品交易查询Dto
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Dto
 * @since 1.0
 */
@PageId("ValFinprodTradeQuery002")
public class ValFinprodTradeQuery001Dto extends BaseEntity implements ProcessEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 交易编号
     */
    @PrimaryKey(value=Field.PK_1)
    private String tradeId;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 业务类型Code
     */
    private String busiTypeCode;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal branch;

    /**
     * 交易日
     */
    private String tradeDate;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 交割日
     */
    private String settleDate;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 净值日期
     */
    private String cpriceDate;

    /**
     * 单位净价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 单位利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 单位全价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitFprice;

    /**
     * 单位面值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal parValue;

    /**
     * 净价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 交易份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 交易本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 利息总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 随交易支付费用
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeAmtPay;

    /**
     * 交易金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 未随交易支付费用
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeAmtUnpay;

    /**
     * 总交易费用
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 到期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 行权收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal exerYield;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 原交易编号
     */
    private String pTradeId;

    /**
     * 是否结清
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
     * 通道代码
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
    
//    /**
//     * 交易对手类型
//     */
//    private String counterType;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易员
     */
    private String trader;

    /**
     * 证券管理户代码
     */
    private String secManageAcctId;

    /**
     * 关联系统交易编号
     */
    private String fTradeId;

    /**
     * 理财产品代码
     */
    private String prodId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 录入日
     */
    private String booksetDate;

    /**
     * 入账日
     */
    private String bookDate;

    /**
     * 业务表
     */
    private String busiTable;

    /**
     * 账务明细代码
     */
    private String bokDetailId;
    
    /**
     * 取得 交易编号
     * 
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * 
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 取得 业务类型
     * 
     * @return 业务类型
     */
    public String getBusiType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.业务类型,
            busiType)) ? SysDataUtil.getNameFromDataDict(DictCode.业务类型, busiType) : this.busiType;
    }

    /**
     * 设置 业务类型
     * 
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取 busiTypeCode
     * 
     * @return busiTypeCode.
     */
    public String getBusiTypeCode() {
        return busiTypeCode;
    }

    /**
     * 设置 busiTypeCode
     * 
     * @param busiTypeCode busiTypeCode
     */
    public void setBusiTypeCode(String busiTypeCode) {
        this.busiTypeCode = busiTypeCode;
    }

    /**
     * 取得 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 取得 金融产品类型
     * 
     * @return 金融产品类型
     */
    public String getFinprodTypeFt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型,
            finprodType)) ? SysDataUtil.getNameFromDataDict(DictCode.金融产品类型,
                finprodType) : this.finprodType;
    }
    
    
    
    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * 
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 取得 分支序号
     * 
     * @return 分支序号
     */
    public BigDecimal getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * 
     * @param branch 分支序号
     */
    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    /**
     * 取得 交易日
     * 
     * @return 交易日
     */
    public String getTradeDate() {
        return DateUtil.formatDateAddSplit(tradeDate);
    }

    /**
     * 设置 交易日
     * 
     * @param tradeDate 交易日
     */

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 起息日
     * 
     * @return 起息日
     */
    public String getVdate() {
        return DateUtil.formatDateAddSplit(vdate);
    }

    /**
     * 设置 起息日
     * 
     * @param vdate 起息日
     */

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * 
     * @return 到期日
     */
    public String getMdate() {
        return DateUtil.formatDateAddSplit(mdate);
    }

    /**
     * 设置 到期日
     * 
     * @param mdate 到期日
     */

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 交割日
     * 
     * @return 交割日
     */
    public String getSettleDate() {
        return DateUtil.formatDateAddSplit(settleDate);
    }

    /**
     * 设置 交割日
     * 
     * @param settleDate 交割日
     */

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 取得 交易币种
     * 
     * @return 交易币种
     */
    public String getCcyFt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种,
            ccy)) ? SysDataUtil.getNameFromDataDict(DictCode.币种, ccy) : this.ccy;
    }

    
    
    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 交易币种
     * 
     * @param ccy 交易币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 取得 净值日期
     * 
     * @return 净值日期
     */
    public String getCpriceDate() {
        return DateUtil.formatDateAddSplit(cpriceDate);
    }

    /**
     * 设置 净值日期
     * 
     * @param cpriceDate 净值日期
     */

    public void setCpriceDate(String cpriceDate) {
        this.cpriceDate = cpriceDate;
    }

    /**
     * 取得 单位净价
     * 
     * @return 单位净价
     */
    public BigDecimal getUnitCprice() {
        if (unitCprice == null) {
            return BigDecimal.ZERO;
        }
        return unitCprice;
    }

    /**
     * 设置 单位净价
     * 
     * @param unitCprice 单位净价
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 取得 单位利息
     * 
     * @return 单位利息
     */
    public BigDecimal getUnitInt() {
        if (unitInt == null) {
            return BigDecimal.ZERO;
        }
        return unitInt;
    }

    /**
     * 设置 单位利息
     * 
     * @param unitInt 单位利息
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 取得 单位全价
     * 
     * @return 单位全价
     */
    public BigDecimal getUnitFprice() {
        if (unitFprice == null) {
            return BigDecimal.ZERO;
        }
        return unitFprice;
    }

    /**
     * 设置 单位全价
     * 
     * @param unitFprice 单位全价
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    /**
     * 取得 单位面值
     * 
     * @return 单位面值
     */
    public BigDecimal getParValue() {
        if (parValue == null) {
            return BigDecimal.ZERO;
        }
        return parValue;
    }

    /**
     * 设置 单位面值
     * 
     * @param parValue 单位面值
     */
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    /**
     * 取得 净价总额
     * 
     * @return 净价总额
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 净价总额
     * 
     * @param cpriceAmt 净价总额
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 取得 交易份额
     * 
     * @return 交易份额
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额
     * 
     * @param shareAmt 交易份额
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 交易本金
     * 
     * @return 交易本金
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 交易本金
     * 
     * @param prinAmt 交易本金
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 取得 利息总额
     * 
     * @return 利息总额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 利息总额
     * 
     * @param intAmt 利息总额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 全价总额
     * 
     * @return 全价总额
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 全价总额
     * 
     * @param fpriceAmt 全价总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 取得 随交易支付费用
     * 
     * @return 随交易支付费用
     */
    public BigDecimal getFeeAmtPay() {
        if (feeAmtPay == null) {
            return BigDecimal.ZERO;
        }
        return feeAmtPay;
    }

    /**
     * 设置 随交易支付费用
     * 
     * @param feeAmtPay 随交易支付费用
     */
    public void setFeeAmtPay(BigDecimal feeAmtPay) {
        this.feeAmtPay = feeAmtPay;
    }

    /**
     * 取得 交易金额
     * 
     * @return 交易金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 交易金额
     * 
     * @param tradeAmt 交易金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 未随交易支付费用
     * 
     * @return 未随交易支付费用
     */
    public BigDecimal getFeeAmtUnpay() {
        if (feeAmtUnpay == null) {
            return BigDecimal.ZERO;
        }
        return feeAmtUnpay;
    }

    /**
     * 设置 未随交易支付费用
     * 
     * @param feeAmtUnpay 未随交易支付费用
     */
    public void setFeeAmtUnpay(BigDecimal feeAmtUnpay) {
        this.feeAmtUnpay = feeAmtUnpay;
    }

    /**
     * 取得 总交易费用
     * 
     * @return 总交易费用
     */
    public BigDecimal getFeeAmt() {
        if (feeAmt == null) {
            return BigDecimal.ZERO;
        }
        return feeAmt;
    }

    /**
     * 设置 总交易费用
     * 
     * @param feeAmt 总交易费用
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 取得 交易方向
     * 
     * @return 交易方向
     */
    public String getPsFt() {
        if(DictCodeList.业务类型_指令交易.getValue().equals(busiType)){
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收付方向, ps))?
                SysDataUtil.getNameFromDataDict(DictCode.收付方向, ps):this.ps;
        }
        else{
            return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易方向, ps))?
                SysDataUtil.getNameFromDataDict(DictCode.交易方向, ps):this.ps;
        }
    }

    
    
    
    /**
     * 获取 ps
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * 
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 取得 到期收益率
     * 
     * @return 到期收益率
     */
    public BigDecimal getYield() {
        if (yield == null) {
            return BigDecimal.ZERO;
        }
        return yield;
    }

    /**
     * 设置 到期收益率
     * 
     * @param yield 到期收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 行权收益率
     * 
     * @return 行权收益率
     */
    public BigDecimal getExerYield() {
        if (exerYield == null) {
            return BigDecimal.ZERO;
        }
        return exerYield;
    }

    /**
     * 设置 行权收益率
     * 
     * @param exerYield 行权收益率
     */
    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    /**
     * 取得 投资目的
     * 
     * @return 投资目的
     */
    public String getInvAimFt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.投资目的,
            invAim)) ? SysDataUtil.getNameFromDataDict(DictCode.投资目的, invAim) : this.invAim;
    }

    
    
    /**
     * 获取 invAim
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * 
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 取得 交易状态
     * 
     * @return 交易状态
     */
    public String getTradeStatusFt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易状态,
            tradeStatus)) ? SysDataUtil.getNameFromDataDict(DictCode.交易状态,
                tradeStatus) : this.tradeStatus;
    }

    
    
    /**
     * 获取 tradeStatus
     * @return tradeStatus.
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 交易状态
     * 
     * @param tradeStatus 交易状态
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 取得 原交易编号
     * 
     * @return 原交易编号
     */
    public String getPTradeId() {
        return pTradeId;
    }

    /**
     * 设置 原交易编号
     * 
     * @param pTradeId 原交易编号
     */
    public void setPTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    /**
     * 取得 是否结清
     * 
     * @return 是否结清
     */
    public String getIsCleanFt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否,
            isClean)) ? SysDataUtil.getNameFromDataDict(DictCode.是否, isClean) : this.isClean;
    }

    
    
    
    /**
     * 获取 isClean
     * @return isClean.
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 是否结清
     * 
     * @param isClean 是否结清
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

    /**
     * 取得 汇总交易编号
     * 
     * @return 汇总交易编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总交易编号
     * 
     * @param totleTradeId 汇总交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 取得 反向交易编号
     * 
     * @return 反向交易编号
     */
    public String getRTradeId() {
        return rTradeId;
    }

    /**
     * 设置 反向交易编号
     * 
     * @param rTradeId 反向交易编号
     */
    public void setRTradeId(String rTradeId) {
        this.rTradeId = rTradeId;
    }

    /**
     * 取得 通道代码
     * 
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * 
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    /**
     * 取得 交易对手
     * 
     * @return 交易对手
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手
     * 
     * @param counterId 交易对手
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

//    /**
//     * 取得 交易对手类型
//     * 
//     * @return 交易对手类型
//     */
//    public String getCounterTypeFt() {
//        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易对手类型,
//            counterType)) ? SysDataUtil.getNameFromDataDict(DictCode.交易对手类型,
//                counterType) : this.counterType;
//    }
//
//    
//    
//    
//    /**
//     * 获取 counterType
//     * @return counterType.
//     */
//    public String getCounterType() {
//        return counterType;
//    }
//
//    /**
//     * 设置 交易对手类型
//     * 
//     * @param counterType 交易对手类型
//     */
//    public void setCounterType(String counterType) {
//        this.counterType = counterType;
//    }
    
    public String getIsOutTradeCode() {
		return isOutTrade;
	}
    
	public String getIsOutTrade() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否, isOutTrade);
	}

	public void setIsOutTrade(String isOutTrade) {
		this.isOutTrade = isOutTrade;
	}

    /**
     * 取得 交易场所
     * 
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 取得 交易场所
     * 
     * @return 交易场所
     */
    public String getTradeMarketFmt() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, tradeMarket);
    }

    /**
     * 设置 交易场所
     * 
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 取得 交易员
     * 
     * @return 交易员
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 交易员
     * 
     * @param trader 交易员
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 取得 证券管理户代码
     * 
     * @return 证券管理户代码
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 证券管理户代码
     * 
     * @param secManageAcctId 证券管理户代码
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    /**
     * 取得 关联系统交易编号
     * 
     * @return 关联系统交易编号
     */
    public String getFTradeId() {
        return fTradeId;
    }

    /**
     * 设置 关联系统交易编号
     * 
     * @param fTradeId 关联系统交易编号
     */
    public void setFTradeId(String fTradeId) {
        this.fTradeId = fTradeId;
    }

    /**
     * 取得 理财产品代码
     * 
     * @return 理财产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品代码
     * 
     * @param prodId 理财产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 获取 账套代码
     * 
     * @return booksetId 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 录入日
     * 
     * @return booksetDate 录入日
     */
    public String getBooksetDate() {
        return DateUtil.formatDateAddSplit(booksetDate);
    }

    /**
     * 设置 录入日
     * 
     * @param booksetDate 录入日
     */
    public void setBooksetDate(String booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 获取 入账日
     * 
     * @return bookDate 入账日
     */
    public String getBookDate() {
        return DateUtil.formatDateAddSplit(bookDate);
    }

    /**
     * 设置 入账日
     * 
     * @param bookDate 入账日
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 获取 busiTable
     * 
     * @return busiTable.
     */
    public String getBusiTable() {
        return busiTable;
    }

    /**
     * 设置 busiTable
     * 
     * @param busiTable busiTable
     */
    public void setBusiTable(String busiTable) {
        this.busiTable = busiTable;
    }

    /**
     * 获取 bokDetailId
     * 
     * @return bokDetailId.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 bokDetailId
     * 
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    

    /**
     * 操作类型
     */
    private String operate;

    /**
     * 选中的数据
     */
    private transient List<ValFinprodTradeQuery001Dto> listValFinprodTradeQuery001Dto;

    
    public String getOperate() {
        return operate;
    }

    
    public void setOperate(String operate) {
        this.operate = operate;
    }

    
    public List<ValFinprodTradeQuery001Dto> getListValFinprodTradeQuery001Dto() {
        return listValFinprodTradeQuery001Dto;
    }

    
    public void setListValFinprodTradeQuery001Dto(List<ValFinprodTradeQuery001Dto> listValFinprodTradeQuery001Dto) {
        this.listValFinprodTradeQuery001Dto = listValFinprodTradeQuery001Dto;
    }
    
    
}
