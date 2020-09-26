/*
 * 文件名：ValRepurchaseAssetQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.repurchase.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;

/**
 * 回购类资产查询DTO
 * 
 * @author wangqidong
 * @version 1.0 2018年10月19日
 * @see ValRepurchaseAssetQuery001Dto
 * @since 1.0
 */
public class ValRepurchaseAssetQuery001Dto extends FinProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 起息日
     */
    private String vdate1;

    /**
     * 到期日
     */
    private String mdate1;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 名义本金
     */
    private BigDecimal prin;

    /**
     * 本金币种
     */
    private String ccy;

    /**
     * 期限(天)
     */
    private String term;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 固定利率
     */
    private BigDecimal intRate;

    /**
     * 利率编号
     */
    private String intRateId;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 计息基础
     */
    private String basisCode;

    /**
     * 到期本金
     */
    private BigDecimal mPrinAmt;

    /**
     * 到期利息
     */
    private BigDecimal mIntAmt;

    /**
     * 到期结算金额
     */
    private BigDecimal mTradeAmt;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 交易日历名称
     */
    private String calendarName;

    /**
     * 抵押品代码
     */
    private String pledgeFinprodId;

    /**
     * 质押面值
     */
    private String pledgeFaceValue;

    /**
     * 数量
     */
    private String pledgeNumber;

    /**
     * 质押率%
     */
    private String pledgeRatio;

    /**
     * 质押金额
     */
    private String pledgeAmt;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 金融产品类型Code
     */
    private String finprodTypeCode;

    /**
     * 首期交付日
     */
    private String  vpayDate;
    /**
     * 到期交付日
     */
    private String  mpayDate;
    /**
     * 实际占款天数
     */
    private String  actCapDays;
    /**
     * 交付日
     */
    private String  payDate;

    /**
     * 备注
     */
    private String  remark;
    /**
     * 交易所回购天数方式
     */
    private String  seRepDayType;
    /**
     * 交易所回购天数方式
     */
    private String  seRepDayTypeCode;
    /**
     * 合同编号
     */
    private String contractNo;


    /**
     * 获取 tradeMarket
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }
    public String getTradeMarketFmt() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, tradeMarket);
    }


    /**
     * 设置 tradeMarket
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 pledgeFinprodId
     *
     * @return pledgeFinprodId.
     */
    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    /**
     * 设置 pledgeFinprodId
     *
     * @param pledgeFinprodId pledgeFinprodId
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
    }

    /**
     * 获取 pledgeFaceValue
     *
     * @return pledgeFaceValue.
     */
    public String getPledgeFaceValue() {
        return pledgeFaceValue;
    }

    /**
     * 设置 pledgeFaceValue
     *
     * @param pledgeFaceValue pledgeFaceValue
     */
    public void setPledgeFaceValue(String pledgeFaceValue) {
        this.pledgeFaceValue = pledgeFaceValue;
    }

    /**
     * 获取 pledgeNumber
     *
     * @return pledgeNumber.
     */
    public String getPledgeNumber() {
        return pledgeNumber;
    }

    /**
     * 设置 pledgeNumber
     *
     * @param pledgeNumber pledgeNumber
     */
    public void setPledgeNumber(String pledgeNumber) {
        this.pledgeNumber = pledgeNumber;
    }

    /**
     * 获取 pledgeRatio
     *
     * @return pledgeRatio.
     */
    public String getPledgeRatio() {
        return pledgeRatio;
    }

    /**
     * 设置 pledgeRatio
     *
     * @param pledgeRatio pledgeRatio
     */
    public void setPledgeRatio(String pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    /**
     * 获取 pledgeAmt
     *
     * @return pledgeAmt.
     */
    public String getPledgeAmt() {
        return pledgeAmt;
    }

    /**
     * 设置 pledgeAmt
     *
     * @param pledgeAmt pledgeAmt
     */
    public void setPledgeAmt(String pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    /**
     * 获取 交易日历
     *
     * @return 交易日历.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     *
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 交易日历名称
     *
     * @return 交易日历名称
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 交易日历名称
     *
     * @param calendarName 交易日历名称
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    /**
     * 取得 名义本金
     *
     * @return 名义本金
     */
    public BigDecimal getPrin() {
        return prin;
    }

    /**
     * 设置 名义本金
     *
     * @param prin 名义本金
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
    }

    /**
     * 取得 本金币种
     *
     * @return 本金币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 本金币种
     *
     * @param ccy 本金币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 期限(天)
     *
     * @return 期限(天)
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 期限(天)
     *
     * @param term 期限(天)
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 取得 期限品种
     *
     * @return 期限品种
     */
    public String getTermType() {
        return termType;
    }

    public String getTermTypeFmt() {
        return SysDataUtil.getNameFromDataDict(DictCode.期限品种_回购, termType);
    }

    /**
     * 设置 期限品种
     *
     * @param termType 期限品种
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    /**
     * 取得 利率类型
     *
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     *
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * 取得 固定利率
     *
     * @return 固定利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 固定利率
     *
     * @param intRate 固定利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 利率编号
     *
     * @return 利率编号
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 利率编号
     *
     * @param intRateId 利率编号
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 计息基础
     *
     * @return 计息基础
     */
    public String getBasis() {
        return SysDataUtil.getNameFromDataDict(DictCode.计息基础, basis);
    }

    /**
     * 设置 计息基础
     *
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }



    /**
     * 获取 basisCode
     * @return basisCode.
     */
    public String getBasisCode() {
        return basisCode;
    }

    /**
     * 设置 basisCode
     * @param basisCode basisCode
     */
    public void setBasisCode(String basisCode) {
        this.basisCode = basisCode;
    }
    /**
     * 取得 到期本金
     *
     * @return 到期本金
     */
    public BigDecimal getmPrinAmt() {
        return mPrinAmt;
    }

    /**
     * 设置 到期本金
     *
     * @param mPrinAmt 到期本金
     */
    public void setmPrinAmt(BigDecimal mPrinAmt) {
        this.mPrinAmt = mPrinAmt;
    }

    /**
     * 取得 到期利息
     *
     * @return 到期利息
     */
    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    /**
     * 设置 到期利息
     *
     * @param mIntAmt 到期利息
     */
    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    /**
     * 取得 到期结算金额
     *
     * @return 到期结算金额
     */
    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    /**
     * 设置 到期结算金额
     *
     * @param mTradeAmt 到期结算金额
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    /**
     * 取得 本金收益特征
     *
     * @return 本金收益特征
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 本金收益特征
     *
     * @param capiIncomeFeature 本金收益特征
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature == null ? null : capiIncomeFeature.trim();
    }

    /**
     * 获取 vdate1
     *
     * @return vdate1.
     */
    public String getVdate1() {
        return vdate1;
    }

    /**
     * 设置 vdate1
     *
     * @param vdate1 vdate1
     */
    public void setVdate1(String vdate1) {
        this.vdate1 = vdate1;
    }

    /**
     * 获取 mdate1
     *
     * @return mdate1.
     */
    public String getMdate1() {
        return mdate1;
    }

    /**
     * 设置 mdate1
     *
     * @param mdate1 mdate1
     */
    public void setMdate1(String mdate1) {
        this.mdate1 = mdate1;
    }

    /**
     * 获取 finprodType
     *
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     *
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 finprodTypeCode
     *
     * @return finprodTypeCode.
     */
    public String getFinprodTypeCode() {
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType);
    }

    /**
     * 设置 finprodTypeCode
     *
     * @param finprodTypeCode finprodTypeCode
     */
    public void setFinprodTypeCode(String finprodTypeCode) {
        this.finprodTypeCode = finprodTypeCode;
    }

    public String getIntRateFt(){
        if(StringUtil.isNotEmpty(intRate)){
            return intRate.toString()+"%";
        }else{
            return "";
        }

    }
    public String getVpayDate() {
        return vpayDate;
    }
    public void setVpayDate(String vpayDate) {
        this.vpayDate = vpayDate;
    }
    public String getMpayDate() {
        return mpayDate;
    }
    public void setMpayDate(String mpayDate) {
        this.mpayDate = mpayDate;
    }
    public String getActCapDays() {
        return actCapDays;
    }
    public void setActCapDays(String actCapDays) {
        this.actCapDays = actCapDays;
    }
    public String getPayDate() {
        return payDate;
    }
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSeRepDayType() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易所回购计提天数方式, seRepDayType);
    }
    public void setSeRepDayType(String seRepDayType) {
        this.seRepDayType = seRepDayType;
    }
    public String getSeRepDayTypeCode() {
        return seRepDayTypeCode;
    }
    public void setSeRepDayTypeCode(String seRepDayTypeCode) {
        this.seRepDayTypeCode = seRepDayTypeCode;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getContractNo() {
        return contractNo;
    }
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

}