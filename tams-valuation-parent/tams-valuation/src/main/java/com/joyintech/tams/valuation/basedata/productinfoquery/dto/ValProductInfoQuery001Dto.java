/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productinfoquery.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品资产查询Dto
 * 
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see ValProductInfoQuery001Dto
 * @since 1.0
 */
public class ValProductInfoQuery001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 生效利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;
  
    /**
     * 登记托管机构
     */
    private String registOrg;
    
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodType;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 投资性质
     */
    private String investmentType;

    /**
     * 收益标识
     */
    private String prdIncomeType;
    /**
     * 收益标识
     */
    private String prdIncomeTypeCode;

    /**
     * 募集金额
     */
    private BigDecimal planraiseAmt;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 产品期限
     */
    private String term;

    /**
     * 利率处理方式
     */
    private String valuationRoundRule;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 收益类型，净值、预期收益、货币
     */
    private String profitType;

    /**
     * 息票品种，贴现、附息
     */
    private String couponSpecies;

    /**
     * 通道代码
     */
    private String chlFinprodId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 发行价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal issuePrice;

    /**
     * 发行规模
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 发行日
     */
    private String idate;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 实际到期日
     */
    private String actmdate;

    /**
     * 是否通道产品
     */
    private String isChl;

    /**
     * 是否永续
     */
    private String isSus;

    /**
     * 是否含权
     */
    private String isRight;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 挂钩母产品 名称
     */
    private String pFinprodName;
    
    /**
     * 投资组合 名称
     */
    private String acctSubjectName;
    
    /**
     * 原金融产品代码，发生标的转换时用
     */
    private String oFinprodId;

    /**
     * 原金融产品类型，发生标的转换时用
     */
    private String oFinprodType;

    /**
     * 投资周期
     */
    private String investCycle;

    /**
     * 投资性质
     */
    private String investType;

    /**
     * 份额小数位
     */
    private String shareDecDigits;

    /**
     * 万份收益小数位
     */
    private String pyeildDecDigits;

    /**
     * 收益率小数位
     */
    private String yeildDecDigits;

    /**
     * 估值小数位位数
     */
    private String valDecDigits;
    
    /**
     * 是否对账
     */
    private String isReconciliation;
    
    private ValProductInfoFeeDto ValProductInfoFeeDto;

    private List<ValProductInfoFeeDto> listFeeDto;

    
    /**
     * 获取 isReconciliation
     * 
     * @return isReconciliation.
     */
    public String getIsReconciliation() {
		return isReconciliation;
	}

    /**
     * 设置 isReconciliation
     * 
     * @param isReconciliation isReconciliation
     */
	public void setIsReconciliation(String isReconciliation) {
		this.isReconciliation = isReconciliation;
	}
    
    
    /**
     * 获取 term
     * 
     * @return term.
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 term
     * 
     * @param term term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 获取 raiseType
     * 
     * @return raiseType.
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 raiseType
     * 
     * @param raiseType raiseType
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    /**
     * 获取 investmentType
     * 
     * @return investmentType.
     */
    public String getInvestmentType() {
        return investmentType;
    }

    /**
     * 设置 investmentType
     * 
     * @param investmentType investmentType
     */
    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    /**
     * 获取 prdIncomeType
     * 
     * @return prdIncomeType.
     */
    public String getPrdIncomeType() {
//        return SysDataUtil.getNameFromDataDict(DictCode.收益标识, this.prdIncomeType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益标识, this.prdIncomeType))?
    			SysDataUtil.getNameFromDataDict(DictCode.收益标识, this.prdIncomeType):this.prdIncomeType;
    }

//    /**
//     * 设置 profitFlag
//     * 
//     * @param profitFlag profitFlag
//     */
//    public void setProfitFlag(String prdIncomeType) {
//        this.prdIncomeType = prdIncomeType;
//    }

    /**
     * 获取 planraiseAmt
     * 
     * @return planraiseAmt.
     */
    public BigDecimal getPlanraiseAmt() {
        if (planraiseAmt != null) {
            return planraiseAmt;
        }
        else {
            return BigDecimal.ZERO;
        }

    }

    /**
     * 设置 planraiseAmt
     * 
     * @param planraiseAmt planraiseAmt
     */
    public void setPlanraiseAmt(BigDecimal planraiseAmt) {
        this.planraiseAmt = planraiseAmt;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
//    一体化问题12：表名变更（标的类金融产品扩展表->标的类金融产品附属表）新表没有SALE_CHANNEL（销售渠道字段）该字段也没被用到
//    /**
//     * 销售渠道
//     */
//    private String saleChannel;

    /**
     * 销售分层
     */
    private String saleLayer;

    /**
     * 销售期次
     */
    private Long salePeriod;

    /**
     * 开放类型
     */
    private String openType;

    /**
     * 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    private String carryoverFreq;

    /**
     * 申购确认速度
     */
    private Long purSpeed;

    /**
     * 赎回确认速度
     */
    private Long redSpeed;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 交易日历名称
     */
    private String calendarName;

//    /**
//     * 取得 销售渠道
//     * 
//     * @return 销售渠道
//     */
//    public String getSaleChannel() {
//        return saleChannel;
//    }
//
//    /**
//     * 设置 销售渠道
//     * 
//     * @param saleChannel 销售渠道
//     */
//    public void setSaleChannel(String saleChannel) {
//        this.saleChannel = saleChannel == null ? null : saleChannel.trim();
//    }

    /**
     * 取得 销售分层
     * 
     * @return 销售分层
     */
    public String getSaleLayer() {
        return saleLayer;
    }

    /**
     * 设置 销售分层
     * 
     * @param saleLayer 销售分层
     */
    public void setSaleLayer(String saleLayer) {
        this.saleLayer = saleLayer == null ? null : saleLayer.trim();
    }

    /**
     * 取得 销售期次
     * 
     * @return 销售期次
     */
    public Long getSalePeriod() {
        return salePeriod;
    }

    /**
     * 设置 销售期次
     * 
     * @param salePeriod 销售期次
     */
    public void setSalePeriod(Long salePeriod) {
        this.salePeriod = salePeriod;
    }

    /**
     * 取得 开放类型
     * 
     * @return 开放类型
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * 设置 开放类型
     * 
     * @param openType 开放类型
     */
    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    /**
     * 取得 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     * 
     * @return 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    public String getCarryoverFreq() {
        return carryoverFreq;
    }

    /**
     * 设置 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     * 
     * @param carryoverFreq 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    public void setCarryoverFreq(String carryoverFreq) {
        this.carryoverFreq = carryoverFreq == null ? null : carryoverFreq.trim();
    }

    /**
     * 取得 申购确认速度
     * 
     * @return 申购确认速度
     */
    public Long getPurSpeed() {
        return purSpeed;
    }

    /**
     * 设置 申购确认速度
     * 
     * @param purSpeed 申购确认速度
     */
    public void setPurSpeed(Long purSpeed) {
        this.purSpeed = purSpeed;
    }

    /**
     * 取得 赎回确认速度
     * 
     * @return 赎回确认速度
     */
    public Long getRedSpeed() {
        return redSpeed;
    }

    /**
     * 设置 赎回确认速度
     * 
     * @param redSpeed 赎回确认速度
     */
    public void setRedSpeed(Long redSpeed) {
        this.redSpeed = redSpeed;
    }

    /**
     * 是否挂钩结构标的
     */
    private String isStructure;

    /**
     * 是否挂钩结构标的
     */
    public boolean isStructureYorN() {
        return StringUtil.equals(this.isStructure, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否支持自动投续
     */
    private String isAutoContinuation;

    /**
     * 是否支持自动投续
     */
    public boolean isAutoContinuationYorN() {
        return StringUtil.equals(this.isAutoContinuation, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否周期型
     */
    private String isPeriodicType;

    /**
     * 是否周期型
     */
    public boolean isPeriodicTypeYorN() {
        return StringUtil.equals(this.isPeriodicType, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否允许提前终止
     */
    private String isEndflag;

    /**
     * 是否允许提前终止
     */
    public boolean isEndflagYorN() {
        return StringUtil.equals(this.isEndflag, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否允许赎回
     */
    private String isRedflag;

    /**
     * 是否允许赎回
     */
    public boolean isRedflagYorN() {
        return StringUtil.equals(this.isRedflag, DictCodeList.是否_是.getValue());
    };

    /**
     * 节假日是否开放
     */
    private String isOpenHoliday;

    /**
     * 节假日是否开放
     */
    public boolean isOpenHolidayYorN() {
        return StringUtil.equals(this.isOpenHoliday, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否申赎同频
     */
    private String isRede;

    /**
     * 是否申赎同频
     */
    public boolean isRedeYorN() {
        ;
        return StringUtil.equals(this.isRede, DictCodeList.是否_是.getValue());
    }

    /**
     * 是否专属产品
     */
    private String isExclusive;

    /**
     * 是否专属产品
     */
    public boolean isExclusiveYorN() {
        return StringUtil.equals(this.isExclusive, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否托管活期利率计提
     */
    private String isTrustCurrent;

    /**
     * 是否托管活期利率计提
     */
    public boolean isTrustCurrentYorN() {
        return StringUtil.equals(this.isTrustCurrent, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否分层，对值做Y和N处理
     * 
     * @return isSaleLayer.
     */
    private String isSaleLayer;

    /**
     * 是否分层，对值做Y和N处理
     * 
     * @return isSaleLayer.
     */
    public boolean isSaleLayerYorN() {
        return StringUtil.equals(this.isSaleLayer, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否通道产品，对值做Y和N处理
     */
    public boolean isChlYorN() {
        return StringUtil.equals(this.isChl, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否永续，对值做Y和N处理
     */
    public boolean isSusYorN() {
        return StringUtil.equals(this.isSus, DictCodeList.是否_是.getValue());
    };

    /**
     * 是否含权，对值做Y和N处理
     */
    public boolean isRightYorN() {
        return StringUtil.equals(this.isRight, DictCodeList.是否_是.getValue());
    };

    /**
     * 获取 isStructure
     * 
     * @return isStructure.
     */
    public String getIsStructure() {
        return isStructure;
    }

    /**
     * 设置 isStructure
     * 
     * @param isStructure isStructure
     */
    public void setIsStructure(String isStructure) {
        this.isStructure = isStructure;
    }

    /**
     * 获取 isAutoContinuation
     * 
     * @return isAutoContinuation.
     */
    public String getIsAutoContinuation() {
        return isAutoContinuation;
    }

    /**
     * 设置 isAutoContinuation
     * 
     * @param isAutoContinuation isAutoContinuation
     */
    public void setIsAutoContinuation(String isAutoContinuation) {
        this.isAutoContinuation = isAutoContinuation;
    }

    /**
     * 获取 isPeriodicType
     * 
     * @return isPeriodicType.
     */
    public String getIsPeriodicType() {
        return isPeriodicType;
    }

    /**
     * 设置 isPeriodicType
     * 
     * @param isPeriodicType isPeriodicType
     */
    public void setIsPeriodicType(String isPeriodicType) {
        this.isPeriodicType = isPeriodicType;
    }

    /**
     * 获取 isEndflag
     * 
     * @return isEndflag.
     */
    public String getIsEndflag() {
        return isEndflag;
    }

    /**
     * 设置 isEndflag
     * 
     * @param isEndflag isEndflag
     */
    public void setIsEndflag(String isEndflag) {
        this.isEndflag = isEndflag;
    }

    /**
     * 获取 isRedflag
     * 
     * @return isRedflag.
     */
    public String getIsRedflag() {
        return isRedflag;
    }

    /**
     * 设置 isRedflag
     * 
     * @param isRedflag isRedflag
     */
    public void setIsRedflag(String isRedflag) {
        this.isRedflag = isRedflag;
    }

    /**
     * 获取 isOpenHoliday
     * 
     * @return isOpenHoliday.
     */
    public String getIsOpenHoliday() {
        return isOpenHoliday;
    }

    /**
     * 获取 isRede
     * 
     * @return isRede.
     */
    public String getIsRede() {
        return isRede;
    }

    /**
     * 设置 isRede
     * 
     * @param isRede isRede
     */
    public void setIsRede(String isRede) {
        this.isRede = isRede;
    }

    /**
     * 获取 isExclusive
     * 
     * @return isExclusive.
     */
    public String getIsExclusive() {
        return isExclusive;
    }

    /**
     * 设置 isExclusive
     * 
     * @param isExclusive isExclusive
     */
    public void setIsExclusive(String isExclusive) {
        this.isExclusive = isExclusive;
    }

    /**
     * 获取 isTrustCurrent
     * 
     * @return isTrustCurrent.
     */
    public String getIsTrustCurrent() {
        return isTrustCurrent;
    }

    /**
     * 设置 isTrustCurrent
     * 
     * @param isTrustCurrent isTrustCurrent
     */
    public void setIsTrustCurrent(String isTrustCurrent) {
        this.isTrustCurrent = isTrustCurrent;
    }

    /**
     * 获取 isSaleLayer
     * 
     * @return isSaleLayer.
     */
    public String getIsSaleLayer() {
        return isSaleLayer;
    }

    /**
     * 设置 isSaleLayer
     * 
     * @param isSaleLayer isSaleLayer
     */
    public void setIsSaleLayer(String isSaleLayer) {
        this.isSaleLayer = isSaleLayer;
    }

    /**
     * 获取 valuationRoundRule
     * 
     * @return valuationRoundRule.
     */
    public String getValuationRoundRule() {
        return valuationRoundRule;
    }

    /**
     * 设置 valuationRoundRule
     * 
     * @param valuationRoundRule valuationRoundRule
     */
    public void setValuationRoundRule(String valuationRoundRule) {
        this.valuationRoundRule = valuationRoundRule;
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
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 couponSpecies
     * 
     * @return couponSpecies.
     */
    public String getCouponSpecies() {
        return couponSpecies;
    }

    /**
     * 设置 couponSpecies
     * 
     * @param couponSpecies couponSpecies
     */
    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies;
    }

    /**
     * 获取 chlFinprodId
     * 
     * @return chlFinprodId.
     */
    public String getChlFinprodId() {
        return chlFinprodId;
    }

    /**
     * 设置 chlFinprodId
     * 
     * @param chlFinprodId chlFinprodId
     */
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 issuePrice
     * 
     * @return issuePrice.
     */
    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    /**
     * 设置 issuePrice
     * 
     * @param issuePrice issuePrice
     */
    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    /**
     * 获取 issueAmt
     * 
     * @return issueAmt.
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 issueAmt
     * 
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 blnArea
     * 
     * @return blnArea.
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 blnArea
     * 
     * @param blnArea blnArea
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea;
    }

    /**
     * 获取 tradeMarket
     * 
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     * 
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 issueType
     * 
     * @return issueType.
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 issueType
     * 
     * @param issueType issueType
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    /**
     * 获取 issuer
     * 
     * @return issuer.
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 issuer
     * 
     * @param issuer issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * 获取 isChl
     * 
     * @return isChl.
     */
    public String getIsChl() {
        return isChl;
    }

    /**
     * 设置 isChl
     * 
     * @param isChl isChl
     */
    public void setIsChl(String isChl) {
        this.isChl = isChl;
    }

    /**
     * 获取 isSus
     * 
     * @return isSus.
     */
    public String getIsSus() {
        return isSus;
    }

    /**
     * 设置 isSus
     * 
     * @param isSus isSus
     */
    public void setIsSus(String isSus) {
        this.isSus = isSus;
    }

    /**
     * 获取 isRight
     * 
     * @return isRight.
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 设置 isRight
     * 
     * @param isRight isRight
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    /**
     * 获取 pFinprodId
     * 
     * @return pFinprodId.
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 pFinprodId
     * 
     * @param pFinprodId pFinprodId
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

    /**
     * 获取 oFinprodId
     * 
     * @return oFinprodId.
     */
    public String getoFinprodId() {
        return oFinprodId;
    }

    /**
     * 设置 oFinprodId
     * 
     * @param oFinprodId oFinprodId
     */
    public void setoFinprodId(String oFinprodId) {
        this.oFinprodId = oFinprodId;
    }

    /**
     * 获取 oFinprodType
     * 
     * @return oFinprodType.
     */
    public String getoFinprodType() {
        return oFinprodType;
    }

    /**
     * 设置 oFinprodType
     * 
     * @param oFinprodType oFinprodType
     */
    public void setoFinprodType(String oFinprodType) {
        this.oFinprodType = oFinprodType;
    }


    /**
     * 获取 investCycle
     * 
     * @return investCycle.
     */
    public String getInvestCycle() {
        return investCycle;
    }

    /**
     * 设置 investCycle
     * 
     * @param investCycle investCycle
     */
    public void setInvestCycle(String investCycle) {
        this.investCycle = investCycle;
    }

    /**
     * 获取 idate
     * 
     * @return idate.
     */
    public String getIdate() {
        return idate;
    }

    /**
     * 设置 idate
     * 
     * @param idate idate
     */
    public void setIdate(String idate) {
        this.idate = idate;
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 actmdate
     * 
     * @return actmdate.
     */
    public String getActmdate() {
        return actmdate;
    }

    /**
     * 设置 actmdate
     * 
     * @param actmdate actmdate
     */
    public void setActmdate(String actmdate) {
        this.actmdate = actmdate;
    }

    /**
     * 获取 investType
     * 
     * @return investType.
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 设置 investType
     * 
     * @param investType investType
     */
    public void setInvestType(String investType) {
        this.investType = investType;
    }

    /**
     * 获取 valProductInfoFeeDto
     * 
     * @return valProductInfoFeeDto.
     */
    public ValProductInfoFeeDto getValProductInfoFeeDto() {
        return ValProductInfoFeeDto;
    }

    /**
     * 设置 valProductInfoFeeDto
     * 
     * @param valProductInfoFeeDto valProductInfoFeeDto
     */
    public void setValProductInfoFeeDto(ValProductInfoFeeDto valProductInfoFeeDto) {
        ValProductInfoFeeDto = valProductInfoFeeDto;
    }

    /**
     * 获取 listFeeDto
     * 
     * @return listFeeDto.
     */
    public List<ValProductInfoFeeDto> getListFeeDto() {
        return listFeeDto;
    }

    /**
     * 设置 listFeeDto
     * 
     * @param listFeeDto listFeeDto
     */
    public void setListFeeDto(List<ValProductInfoFeeDto> listFeeDto) {
        this.listFeeDto = listFeeDto;
    }

    /**
     * 获取 shareDecDigits
     * 
     * @return shareDecDigits.
     */
    public String getShareDecDigits() {
        return shareDecDigits;
    }

    /**
     * 设置 shareDecDigits
     * 
     * @param shareDecDigits shareDecDigits
     */
    public void setShareDecDigits(String shareDecDigits) {
        this.shareDecDigits = shareDecDigits;
    }

    /**
     * 获取 pyeildDecDigits
     * 
     * @return pyeildDecDigits.
     */
    public String getPyeildDecDigits() {
        return pyeildDecDigits;
    }

    /**
     * 设置 pyeildDecDigits
     * 
     * @param pyeildDecDigits pyeildDecDigits
     */
    public void setPyeildDecDigits(String pyeildDecDigits) {
        this.pyeildDecDigits = pyeildDecDigits;
    }

    /**
     * 获取 yeildDecDigits
     * 
     * @return yeildDecDigits.
     */
    public String getYeildDecDigits() {
        return yeildDecDigits;
    }

    /**
     * 设置 yeildDecDigits
     * 
     * @param yeildDecDigits yeildDecDigits
     */
    public void setYeildDecDigits(String yeildDecDigits) {
        this.yeildDecDigits = yeildDecDigits;
    }

    /**
     * 设置 isOpenHoliday
     * 
     * @param isOpenHoliday isOpenHoliday
     */
    public void setIsOpenHoliday(String isOpenHoliday) {
        this.isOpenHoliday = isOpenHoliday;
    }

    /**
     * 获取 valDecDigits
     * 
     * @return valDecDigits.
     */
    public String getValDecDigits() {
        return valDecDigits;
    }

    /**
     * 设置 valDecDigits
     * 
     * @param valDecDigits valDecDigits
     */
    public void setValDecDigits(String valDecDigits) {
        this.valDecDigits = valDecDigits;
    }

    /**
     * 获取 calendarId
     * 
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * 
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 calendarName
     * 
     * @return calendarName.
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 calendarName
     * 
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getProfitTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType))?
    			SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType):this.profitType;
    }

    /**
     * 获取 金融产品类型 数据字典
     * 
     * @return 金融产品类型 数据字典
     */
    public String getFinprodTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType))?
    			SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType):this.finprodType;
    }

    public String getRaiseTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.募集方式, raiseType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.募集方式, this.raiseType))?
    			SysDataUtil.getNameFromDataDict(DictCode.募集方式, this.raiseType):this.raiseType;
    }

    public String getIssueTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.发行方式, issueType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发行方式, issueType))?
    			SysDataUtil.getNameFromDataDict(DictCode.发行方式, issueType):issueType;
    }

    public String getInvestTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.投资性质, investType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.投资性质, investType))?
    			SysDataUtil.getNameFromDataDict(DictCode.投资性质, investType):investType;
    }

    public String getCcyFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
    			SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
    }

    /**
     * 获取 BlnArea数据字典
     * 
     * @return BlnArea数据字典
     */
    public String getBlnAreaFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea))?
    			SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea):this.blnArea;
    }

    public String getCouponSpeciesFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.息票品种, couponSpecies);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.息票品种, couponSpecies))?
    			SysDataUtil.getNameFromDataDict(DictCode.息票品种, couponSpecies):this.couponSpecies;
    }
    /**
     * 取得 生效利率
     * @return 生效利率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 生效利率
     * @param yield 生效利率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }
    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    
    public String getpFinprodName() {
        return pFinprodName;
    }

    
    public void setpFinprodName(String pFinprodName) {
        this.pFinprodName = pFinprodName;
    }

    
    public String getAcctSubjectName() {
        return acctSubjectName;
    }

    
    public void setAcctSubjectName(String acctSubjectName) {
        this.acctSubjectName = acctSubjectName;
    }

//	public String getPrdIncomeType() {
//		return prdIncomeType;
//	}

	public void setPrdIncomeType(String prdIncomeType) {
		this.prdIncomeType = prdIncomeType;
	}

	public String getPrdIncomeTypeCode() {
		return prdIncomeTypeCode;
	}

	public void setPrdIncomeTypeCode(String prdIncomeTypeCode) {
		this.prdIncomeTypeCode = prdIncomeTypeCode;
	}


}
