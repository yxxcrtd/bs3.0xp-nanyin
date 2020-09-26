/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.productdata.dto;

import java.math.BigDecimal;
import java.util.Date;

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
 * 
 * 产品资产查询Dto
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see ValProductAssetsQuery001Dto
 * @since 1.0
 */
public class ValProductAssetsQuery001Dto extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
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
    private String profitFlag;
    /**
     * 募集金额
     */
    private String planraiseAmt;
    
    /**
     * 计息基础
     */
    private String basis;
    /**
     * 起息日
     */
    private String vdateStr;
    /**
     * 到期日
     */
    private String mdateStr;
    /**
     * 发行日
     */
    private String idateStr;
    /**
     * 实际到期日
     */
    private String actmdateStr;
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
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issuePrice;

    /**
     * 发行规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 境内外
     */
    private String blnArea;
    private String blnAreaCode;
    /**
     * 交易场所
     */
    private String tradeMarket;
    private String tradeMarketCode;

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
    private Date idate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 实际到期日
     */
    private Date actmdate;

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
     * 原金融产品代码，发生标的转换时用
     */
    private String oFinprodId;

    /**
     * 原金融产品类型，发生标的转换时用
     */
    private String oFinprodType;
    
    /**
     * 获取 term
     * @return term.
     */
    public String getTerm() {
        return term;
    }


    
    /**
     * 设置 term
     * @param term term
     */
    public void setTerm(String term) {
        this.term = term;
    }


    /**
     * 获取 raiseType
     * @return raiseType.
     */
    public String getRaiseType() {
//        return SysDataUtil.getNameFromDataDict(DictCode.募集方式, raiseType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.募集方式, raiseType))?
    			SysDataUtil.getNameFromDataDict(DictCode.募集方式, raiseType):raiseType;
    }

    
    /**
     * 设置 raiseType
     * @param raiseType raiseType
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }
    /**
     * 获取 investmentType
     * @return investmentType.
     */
    public String getInvestmentType() {
        return investmentType;
    }

    
    /**
     * 设置 investmentType
     * @param investmentType investmentType
     */
    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    
    /**
     * 获取 profitFlag
     * @return profitFlag.
     */
    public String getProfitFlag() {
        return profitFlag;
    }

    
    /**
     * 设置 profitFlag
     * @param profitFlag profitFlag
     */
    public void setProfitFlag(String profitFlag) {
        this.profitFlag = profitFlag;
    }

    
    /**
     * 获取 planraiseAmt
     * @return planraiseAmt.
     */
    public String getPlanraiseAmt() {
        return planraiseAmt;
    }

    
    /**
     * 设置 planraiseAmt
     * @param planraiseAmt planraiseAmt
     */
    public void setPlanraiseAmt(String planraiseAmt) {
        this.planraiseAmt = planraiseAmt;
    }

    
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
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    
    /**
     * 获取 vdateStr
     * @return vdateStr.
     */
    public String getVdateStr() {
        return vdateStr;
    }


    
    /**
     * 设置 vdateStr
     * @param vdateStr vdateStr
     */
    public void setVdateStr(String vdateStr) {
        this.vdateStr = vdateStr;
    }


    
    /**
     * 获取 mdateStr
     * @return mdateStr.
     */
    public String getMdateStr() {
        return mdateStr;
    }


    
    /**
     * 设置 mdateStr
     * @param mdateStr mdateStr
     */
    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }
    
    /**
     * 获取 idateStr
     * @return idateStr.
     */
    public String getIdateStr() {
        return idateStr;
    }


    
    /**
     * 设置 idateStr
     * @param idateStr idateStr
     */
    public void setIdateStr(String idateStr) {
        this.idateStr = idateStr;
    }


    
    /**
     * 获取 actmdateStr
     * @return actmdateStr.
     */
    public String getActmdateStr() {
        return actmdateStr;
    }


    
    /**
     * 设置 actmdateStr
     * @param actmdateStr actmdateStr
     */
    public void setActmdateStr(String actmdateStr) {
        this.actmdateStr = actmdateStr;
    }

    /**
     * 销售渠道
     */
    private String saleChannel;

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
     * 取得 销售渠道
     * @return 销售渠道
     */
    public String getSaleChannel() {
        return saleChannel;
    }

    /**
     * 设置 销售渠道
     * @param saleChannel 销售渠道
     */
    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel == null ? null : saleChannel.trim();
    }

    /**
     * 取得 销售分层
     * @return 销售分层
     */
    public String getSaleLayer() {
        return saleLayer;
    }
    /**
     * 设置 销售分层
     * @param saleLayer 销售分层
     */
    public void setSaleLayer(String saleLayer) {
        this.saleLayer = saleLayer == null ? null : saleLayer.trim();
    }
    /**
     * 取得 销售期次
     * @return 销售期次
     */
    public Long getSalePeriod() {
        return salePeriod;
    }

    /**
     * 设置 销售期次
     * @param salePeriod 销售期次
     */
    public void setSalePeriod(Long salePeriod) {
        this.salePeriod = salePeriod;
    }

    /**
     * 取得 开放类型
     * @return 开放类型
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * 设置 开放类型
     * @param openType 开放类型
     */
    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    /**
     * 取得 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     * @return 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    public String getCarryoverFreq() {
        return carryoverFreq;
    }

    /**
     * 设置 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     * @param carryoverFreq 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    public void setCarryoverFreq(String carryoverFreq) {
        this.carryoverFreq = carryoverFreq == null ? null : carryoverFreq.trim();
    }

    /**
     * 取得 申购确认速度
     * @return 申购确认速度
     */
    public Long getPurSpeed() {
        return purSpeed;
    }

    /**
     * 设置 申购确认速度
     * @param purSpeed 申购确认速度
     */
    public void setPurSpeed(Long purSpeed) {
        this.purSpeed = purSpeed;
    }

    /**
     * 取得 赎回确认速度
     * @return 赎回确认速度
     */
    public Long getRedSpeed() {
        return redSpeed;
    }

    /**
     * 设置 赎回确认速度
     * @param redSpeed 赎回确认速度
     */
    public void setRedSpeed(Long redSpeed) {
        this.redSpeed = redSpeed;
    }

    /**
     * 是否挂钩结构标的
     * 
     */
    private String isStructure;
    /**
     * 是否挂钩结构标的
     * 
     */
    @SuppressWarnings("unused")
	private boolean isStructureYorN;
    /**
     * 是否支持自动投续
     * 
     */
    private String isAutoContinuation;
    /**
     * 是否支持自动投续
     * 
     */
    @SuppressWarnings("unused")
	private boolean isAutoContinuationYorN;
    /**
     * 是否周期型
     * 
     */
    private String isPeriodicType;
    /**
     * 是否周期型
     * 
     */
    @SuppressWarnings("unused")
	private boolean isPeriodicTypeYorN;
    /**
     * 是否允许提前终止
     * 
     */
    private String isEndflag;
    /**
     * 是否允许提前终止
     * 
     */
    @SuppressWarnings("unused")
	private boolean isEndflagYorN;
    /**
     * 是否允许赎回
     * 
     */
    private String isRedflag;
    /**
     * 是否允许赎回
     * 
     */
    @SuppressWarnings("unused")
	private boolean isRedflagYorN;
    /**
     * 节假日是否开放
     * 
     */
    private String isOpenHoliday;
    /**
     * 节假日是否开放
     * 
     */
    @SuppressWarnings("unused")
	private boolean isOpenHolidayYorN;
    
    /**
     * 是否申赎同频
     * 
     */
    private String isRede;
    /**
     * 是否申赎同频
     * 
     */
    @SuppressWarnings("unused")
	private boolean isRedeYorN;
    /**
     * 是否专属产品
     * 
     */
    private String isExclusive;
    /**
     * 是否专属产品
     * 
     */
    @SuppressWarnings("unused")
	private boolean isExclusiveYorN;
    /**
     * 是否托管活期利率计提
     * 
     */
    private String isTrustCurrent;
    /**
     * 是否托管活期利率计提
     * 
     */
    @SuppressWarnings("unused")
	private boolean isTrustCurrentYorN;
    
    /**
     * 是否分层，对值做Y和N处理
     * @return isSaleLayer.
     */
    private String isSaleLayer;

    /**
     * 是否分层，对值做Y和N处理
     * @return isSaleLayer.
     */
    @SuppressWarnings("unused")
	private boolean isSaleLayerYorN;
    /**
     * 是否通道产品，对值做Y和N处理
     */
    @SuppressWarnings("unused")
	private boolean isChlYorN;

    /**
     * 是否永续，对值做Y和N处理
     */
    @SuppressWarnings("unused")
	private boolean isSusYorN;

    /**
     * 是否含权，对值做Y和N处理
     */
    private boolean isRightYorN;

    
    /**
     * 获取 isStructure
     * @return isStructure.
     */
    public String getIsStructure() {
        return isStructure;
    }



    
    /**
     * 设置 isStructure
     * @param isStructure isStructure
     */
    public void setIsStructure(String isStructure) {
        this.isStructure = isStructure;
    }



    
    /**
     * 获取 isStructureYorN
     * @return isStructureYorN.
     */
    public boolean isStructureYorN() {
        return StringUtil.equals(isStructure,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isStructureYorN
     * @param isStructureYorN isStructureYorN
     */
    public void setStructureYorN(boolean isStructureYorN) {
        this.isStructureYorN = isStructureYorN;
    }



    
    /**
     * 获取 isAutoContinuation
     * @return isAutoContinuation.
     */
    public String getIsAutoContinuation() {
        return isAutoContinuation;
    }



    
    /**
     * 设置 isAutoContinuation
     * @param isAutoContinuation isAutoContinuation
     */
    public void setIsAutoContinuation(String isAutoContinuation) {
        this.isAutoContinuation = isAutoContinuation;
    }



    
    /**
     * 获取 isAutoContinuationYorN
     * @return isAutoContinuationYorN.
     */
    public boolean isAutoContinuationYorN() {
        return StringUtil.equals(isAutoContinuation,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isAutoContinuationYorN
     * @param isAutoContinuationYorN isAutoContinuationYorN
     */
    public void setAutoContinuationYorN(boolean isAutoContinuationYorN) {
        this.isAutoContinuationYorN = isAutoContinuationYorN;
    }



    
    /**
     * 获取 isPeriodicType
     * @return isPeriodicType.
     */
    public String getIsPeriodicType() {
        return isPeriodicType;
    }



    
    /**
     * 设置 isPeriodicType
     * @param isPeriodicType isPeriodicType
     */
    public void setIsPeriodicType(String isPeriodicType) {
        this.isPeriodicType = isPeriodicType;
    }



    
    /**
     * 获取 isPeriodicTypeYorN
     * @return isPeriodicTypeYorN.
     */
    public boolean isPeriodicTypeYorN() {
        return StringUtil.equals(isPeriodicType,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isPeriodicTypeYorN
     * @param isPeriodicTypeYorN isPeriodicTypeYorN
     */
    public void setPeriodicTypeYorN(boolean isPeriodicTypeYorN) {
        this.isPeriodicTypeYorN = isPeriodicTypeYorN;
    }



    
    /**
     * 获取 isEndflag
     * @return isEndflag.
     */
    public String getIsEndflag() {
        return isEndflag;
    }



    
    /**
     * 设置 isEndflag
     * @param isEndflag isEndflag
     */
    public void setIsEndflag(String isEndflag) {
        this.isEndflag = isEndflag;
    }



    
    /**
     * 获取 isEndflagYorN
     * @return isEndflagYorN.
     */
    public boolean isEndflagYorN() {
        return StringUtil.equals(isEndflag,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isEndflagYorN
     * @param isEndflagYorN isEndflagYorN
     */
    public void setEndflagYorN(boolean isEndflagYorN) {
        this.isEndflagYorN = isEndflagYorN;
    }



    
    /**
     * 获取 isRedflag
     * @return isRedflag.
     */
    public String getIsRedflag() {
        return isRedflag;
    }



    
    /**
     * 设置 isRedflag
     * @param isRedflag isRedflag
     */
    public void setIsRedflag(String isRedflag) {
        this.isRedflag = isRedflag;
    }



    
    /**
     * 获取 isRedflagYorN
     * @return isRedflagYorN.
     */
    public boolean isRedflagYorN() {
        return StringUtil.equals(isRedflag,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isRedflagYorN
     * @param isRedflagYorN isRedflagYorN
     */
    public void setRedflagYorN(boolean isRedflagYorN) {
        this.isRedflagYorN = isRedflagYorN;
    }



    
    /**
     * 获取 isOpenHoliday
     * @return isOpenHoliday.
     */
    public String getIsOpenHoliday() {
        return isOpenHoliday;
    }



    
    /**
     * 设置 isOpenHoliday
     * @param isOpenHoliday isOpenHoliday
     */
    public void setIsOpenHoliday(String isOpenHoliday) {
        this.isOpenHoliday = isOpenHoliday;
    }



    
    /**
     * 获取 isOpenHolidayYorN
     * @return isOpenHolidayYorN.
     */
    public boolean isOpenHolidayYorN() {
        return StringUtil.equals(isOpenHoliday,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isOpenHolidayYorN
     * @param isOpenHolidayYorN isOpenHolidayYorN
     */
    public void setOpenHolidayYorN(boolean isOpenHolidayYorN) {
        this.isOpenHolidayYorN = isOpenHolidayYorN;
    }



    
    /**
     * 获取 isRede
     * @return isRede.
     */
    public String getIsRede() {
        return isRede;
    }



    
    /**
     * 设置 isRede
     * @param isRede isRede
     */
    public void setIsRede(String isRede) {
        this.isRede = isRede;
    }



    
    /**
     * 获取 isRedeYorN
     * @return isRedeYorN.
     */
    public boolean isRedeYorN() {
        return StringUtil.equals(isRede,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isRedeYorN
     * @param isRedeYorN isRedeYorN
     */
    public void setRedeYorN(boolean isRedeYorN) {
        this.isRedeYorN = isRedeYorN;
    }



    
    /**
     * 获取 isExclusive
     * @return isExclusive.
     */
    public String getIsExclusive() {
        return isExclusive;
    }



    
    /**
     * 设置 isExclusive
     * @param isExclusive isExclusive
     */
    public void setIsExclusive(String isExclusive) {
        this.isExclusive = isExclusive;
    }



    
    /**
     * 获取 isExclusiveYorN
     * @return isExclusiveYorN.
     */
    public boolean isExclusiveYorN() {
        return StringUtil.equals(isExclusive,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isExclusiveYorN
     * @param isExclusiveYorN isExclusiveYorN
     */
    public void setExclusiveYorN(boolean isExclusiveYorN) {
        this.isExclusiveYorN = isExclusiveYorN;
    }



    
    /**
     * 获取 isTrustCurrent
     * @return isTrustCurrent.
     */
    public String getIsTrustCurrent() {
        return isTrustCurrent;
    }



    
    /**
     * 设置 isTrustCurrent
     * @param isTrustCurrent isTrustCurrent
     */
    public void setIsTrustCurrent(String isTrustCurrent) {
        this.isTrustCurrent = isTrustCurrent;
    }



    
    /**
     * 获取 isTrustCurrentYorN
     * @return isTrustCurrentYorN.
     */
    public boolean isTrustCurrentYorN() {
        return StringUtil.equals(isTrustCurrent,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isTrustCurrentYorN
     * @param isTrustCurrentYorN isTrustCurrentYorN
     */
    public void setTrustCurrentYorN(boolean isTrustCurrentYorN) {
        this.isTrustCurrentYorN = isTrustCurrentYorN;
    }



    
    /**
     * 获取 isSaleLayer
     * @return isSaleLayer.
     */
    public String getIsSaleLayer() {
        return isSaleLayer;
    }



    
    /**
     * 设置 isSaleLayer
     * @param isSaleLayer isSaleLayer
     */
    public void setIsSaleLayer(String isSaleLayer) {
        this.isSaleLayer = isSaleLayer;
    }



    
    /**
     * 获取 isSaleLayerYorN
     * @return isSaleLayerYorN.
     */
    public boolean isSaleLayerYorN() {
        return StringUtil.equals(isSaleLayer,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isSaleLayerYorN
     * @param isSaleLayerYorN isSaleLayerYorN
     */
    public void setSaleLayerYorN(boolean isSaleLayerYorN) {
        this.isSaleLayerYorN = isSaleLayerYorN;
    }



    
    /**
     * 获取 isChlYorN
     * @return isChlYorN.
     */
    public boolean isChlYorN() {
        return StringUtil.equals(isChl,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isChlYorN
     * @param isChlYorN isChlYorN
     */
    public void setChlYorN(boolean isChlYorN) {
        this.isChlYorN = isChlYorN;
    }



    
    /**
     * 获取 isSusYorN
     * @return isSusYorN.
     */
    public boolean isSusYorN() {
        return StringUtil.equals(isSus,DictCodeList.是否_是.getValue());
    }



    
    /**
     * 设置 isSusYorN
     * @param isSusYorN isSusYorN
     */
    public void setSusYorN(boolean isSusYorN) {
        this.isSusYorN = isSusYorN;
    }



    
    /**
     * 获取 isRightYorN
     * @return isRightYorN.
     */
    public boolean isRightYorN() {
        return isRightYorN;
    }



    
    /**
     * 设置 isRightYorN
     * @param isRightYorN isRightYorN
     */
    public void setRightYorN(boolean isRightYorN) {
        this.isRightYorN = isRightYorN;
    }
    
    /**
     * 获取 valuationRoundRule
     * @return valuationRoundRule.
     */
    public String getValuationRoundRule() {
        return valuationRoundRule;
    }



    
    /**
     * 设置 valuationRoundRule
     * @param valuationRoundRule valuationRoundRule
     */
    public void setValuationRoundRule(String valuationRoundRule) {
        this.valuationRoundRule = valuationRoundRule;
    }
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }



    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }



    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType; 
    }
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType); 
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType))?
    			SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType):finprodType;
    }

    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }



    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }



    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }



    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }



    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }



    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }
    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, profitType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益类型, profitType))?
    			SysDataUtil.getNameFromDataDict(DictCode.收益类型, profitType):profitType;
    }



    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }



    /**
     * 获取 couponSpecies
     * @return couponSpecies.
     */
    public String getCouponSpecies() {
        return couponSpecies;
    }
    
    /**
     * 获取 couponSpecies
     * @return couponSpecies.
     */
    public String getCouponSpeciesFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.息票品种, couponSpecies);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.息票品种, couponSpecies))?
    			SysDataUtil.getNameFromDataDict(DictCode.息票品种, couponSpecies):couponSpecies;
    }



    
    /**
     * 设置 couponSpecies
     * @param couponSpecies couponSpecies
     */
    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies;
    }



    
    /**
     * 获取 chlFinprodId
     * @return chlFinprodId.
     */
    public String getChlFinprodId() {
        return chlFinprodId;
    }



    
    /**
     * 设置 chlFinprodId
     * @param chlFinprodId chlFinprodId
     */
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }



    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }



    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }



    
    /**
     * 获取 issuePrice
     * @return issuePrice.
     */
    public BigDecimal getIssuePrice() {
        return null!=issuePrice?issuePrice:BigDecimal.ZERO;
    }



    
    /**
     * 设置 issuePrice
     * @param issuePrice issuePrice
     */
    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }



    
    /**
     * 获取 issueAmt
     * @return issueAmt.
     */
    public BigDecimal getIssueAmt() {
        return null!=issueAmt?issueAmt:BigDecimal.ZERO;
    }



    
    /**
     * 设置 issueAmt
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }



    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy; 
    }
    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcyFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
    			SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
    }



    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }



    /**
     * 获取 blnArea
     * @return blnArea.
     */
    public String getBlnArea() {
        return  blnArea;
    }
    
    /**
     * 获取 blnArea
     * @return blnArea.
     */
    public String getBlnAreaFmt() {
//        return  SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea))?
    			SysDataUtil.getNameFromDataDict(DictCode.境内外, this.blnArea):this.blnArea;
    }



    
    /**
     * 设置 blnArea
     * @param blnArea blnArea
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea;
    }



    
    /**
     * 获取 tradeMarket
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    public String getTradeMarketFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.tradeMarket);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.tradeMarket))?
    			SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, this.tradeMarket):this.tradeMarket;
    }

    
    
    /**
     * 获取 blnAreaCode
     * @return blnAreaCode.
     */
    public String getBlnAreaCode() {
        return blnAreaCode;
    }



    
    /**
     * 设置 blnAreaCode
     * @param blnAreaCode blnAreaCode
     */
    public void setBlnAreaCode(String blnAreaCode) {
        this.blnAreaCode = blnAreaCode;
    }



    
    /**
     * 获取 tradeMarketCode
     * @return tradeMarketCode.
     */
    public String getTradeMarketCode() {
        return tradeMarketCode;
    }



    
    /**
     * 设置 tradeMarketCode
     * @param tradeMarketCode tradeMarketCode
     */
    public void setTradeMarketCode(String tradeMarketCode) {
        this.tradeMarketCode = tradeMarketCode;
    }



    /**
     * 设置 tradeMarket
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }



    
   



    
    /**
     * 设置 issueType
     * @param issueType issueType
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }



    
    /**
     * 获取 issuer
     * @return issuer.
     */
    public String getIssuer() {
        return issuer;
    }



    
    /**
     * 设置 issuer
     * @param issuer issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }



    
    /**
     * 获取 idate
     * @return idate.
     */
    public Date getIdate() {
        return idate;
    }



    
    /**
     * 设置 idate
     * @param idate idate
     */
    public void setIdate(Date idate) {
        this.idate = idate;
    }



    
    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }



    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }



    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }



    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }



    
    /**
     * 获取 actmdate
     * @return actmdate.
     */
    public Date getActmdate() {
        return actmdate;
    }



    
    /**
     * 设置 actmdate
     * @param actmdate actmdate
     */
    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }



    
    /**
     * 获取 isChl
     * @return isChl.
     */
    public String getIsChlFt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.是否, isChl);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, isChl))?
    			SysDataUtil.getNameFromDataDict(DictCode.是否, isChl):isChl;
    }

    

    
    
    /**
     * 获取 isChl
     * @return isChl.
     */
    public String getIsChl() {
        return isChl;
    }



    /**
     * 设置 isChl
     * @param isChl isChl
     */
    public void setIsChl(String isChl) {
        this.isChl = isChl;
    }



    
    /**
     * 获取 isSus
     * @return isSus.
     */
    public String getIsSusFt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.是否, isSus);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, isSus))?
    			SysDataUtil.getNameFromDataDict(DictCode.是否, isSus):isSus;
    }


    
    
    
    /**
     * 获取 isSus
     * @return isSus.
     */
    public String getIsSus() {
        return isSus;
    }



    /**
     * 设置 isSus
     * @param isSus isSus
     */
    public void setIsSus(String isSus) {
        this.isSus = isSus;
    }



    /**
     * 获取 isRight
     * @return isRight.
     */
    public String getIsRight() {
        return isRight;
    }
    
    /**
     * 获取 isRight
     * @return isRight.
     */
    public String getIsRightFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.是否, isRight);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否, isRight))?
    			SysDataUtil.getNameFromDataDict(DictCode.是否, isRight):isRight;
    }



    
    /**
     * 设置 isRight
     * @param isRight isRight
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }



    
    /**
     * 获取 pFinprodId
     * @return pFinprodId.
     */
    public String getpFinprodId() {
        return pFinprodId;
    }



    
    /**
     * 设置 pFinprodId
     * @param pFinprodId pFinprodId
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }



    
    /**
     * 获取 oFinprodId
     * @return oFinprodId.
     */
    public String getoFinprodId() {
        return oFinprodId;
    }



    
    /**
     * 设置 oFinprodId
     * @param oFinprodId oFinprodId
     */
    public void setoFinprodId(String oFinprodId) {
        this.oFinprodId = oFinprodId;
    }



    
    /**
     * 获取 oFinprodType
     * @return oFinprodType.
     */
    public String getoFinprodType() {
        return oFinprodType;
    }



    
    /**
     * 设置 oFinprodType
     * @param oFinprodType oFinprodType
     */
    public void setoFinprodType(String oFinprodType) {
        this.oFinprodType = oFinprodType;
    }
    /**
     * 获取 issueType
     * @return issueType.
     */
    public String getIssueType() {
        return  issueType;
    }
    /**
     * 获取  issueType
     * @return  issueType.
     */
    public String getIssueTypeFmt() {
//        return SysDataUtil.getNameFromDataDict(DictCode.发行方式,issueType); 
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发行方式,issueType))?
    			SysDataUtil.getNameFromDataDict(DictCode.发行方式,issueType):issueType;
    }
    
    
    

}     				
      			
      