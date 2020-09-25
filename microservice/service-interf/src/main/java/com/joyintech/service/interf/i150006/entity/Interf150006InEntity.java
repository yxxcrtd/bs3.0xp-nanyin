/*
 * 文件名：Interf150006InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150006.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.i448001.entity.Interf448001InEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 15006接口输入
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月9日
 * @see Interf150006InEntity
 * @since 1.0
 */
public class Interf150006InEntity extends Interf448001InEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3047335018192719188L;

    /**
     * 产品系列代码
     */
    private String portfolioId;

    /**
     * 产品系列名称
     */
    private String portfolioName;

    /**
     * 品牌代码
     */
    private String brandCode;

    /**
     * 子产品代码
     */
    private String subProductCode;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 产品中文简称
     */
    private String proChShortName;

    /**
     * 产品监管登记编码
     */
    private String prodCtrlregcode;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 募集方式
     */
    private String collectType;

    /**
     * 销售商
     */
    private String vendor;

    /**
     * 销售模式
     */
    private String saleMode;

    /**
     * 按投资地区分类
     */
    private String investAreatype;

    /**
     * 按发行对象分类
     */
    private String issueObjecttype;

    /**
     * 信息公布日期
     */
    private String msgPublishdate;

    /**
     * 滚动周期
     */
    private String rollCycle;

    /**
     * 产品发行最小规模
     */
    private String prodIssueminscale;

    /**
     * 首次结转日
     */
    private String firstConvertdate;

    /**
     * 结转周期
     */
    private String convertTycle;

    /**
     * 结转推算方法
     */
    private String convertReckonMode;

    /**
     * 首次开放日
     */
    private String firstOpendate;

    /**
     * 开发持续天数
     */
    private String develOpcontdays;

    /**
     * 开放周期
     */
    private String openPeriod;

    /**
     * 开放周期单位
     */
    private String openPeriodUnit;

    /**
     * 开放推算方法
     */
    private String openReckonmode;

    /**
     * 管理费率
     */
    private String managementFeerate;

    /**
     * 托管费率
     */
    private String consignFeerate;

    /**
     * 其他费率
     */
    private String otherFeerate;

    /**
     * 中文全称
     */
    private String chnFullName;

    /**
     * 产品清盘日
     */
    private String prodWindingDt;

    /**
     * 产品观察日数组
     */
    private List<Interf150006InArrEntity> observeDtList;

    @EsbName(value = "PRODUCT_SHORTNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    @Override
    public String getShortName() {
        return super.getShortName();
    }

    @EsbName(value = "PRODUCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    @Override
    public String getProductName() {
        return super.getProductName();
    }

    @EsbName(value = "YEAR")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    @Override
    public String getYearId() {
        return super.getYearId();
    }

    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    @Override
    public String getCurrencyId() {
        return super.getCurrencyId();
    }

    @EsbName(value = "ROLL_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getCycleMode() {
        return super.getCycleMode();
    }

    @EsbName(value = "THIS_COUNT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getCycleDays() {
        return super.getCycleDays();
    }

    @EsbName(value = "TERM_DESC")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    @Override
    public String getCycleRemark() {
        return super.getCycleRemark();
    }

    @EsbName(value = "CYCLE_PRO_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    @Override
    public String getIsCycle() {
        return super.getIsCycle();
    }

    @EsbName(value = "ALLOW_AHEAD_END_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    @Override
    public String getIsPreAllot() {
        return super.getIsPreAllot();
    }

    @EsbName(value = "PRE_END_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getPreallotType() {
        return super.getPreallotType();
    }

    @EsbName(value = "PREFERRED_BONUS_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getInterestMode() {
        return super.getInterestMode();
    }

    @EsbName(value = "UPDATE_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    @Override
    public String getInterestModeChg() {
        return super.getInterestModeChg();
    }

    @EsbName(value = "PREFERRED_CORPUS_EXPIRY_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getEndInstruct() {
        return super.getEndInstruct();
    }

    @EsbName(value = "CORPUS_EXPIRY_MODE_UPDATE_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    @Override
    public String getEndInstructChg() {
        return super.getEndInstructChg();
    }

    @EsbName(value = "END_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    @Override
    public String getIsExpire() {
        return super.getIsExpire();
    }

    @EsbName(value = "THIS_RESERVER_SALE_DAYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getSaleDayNum() {
        return super.getSaleDayNum();
    }

    @EsbName(value = "SALE_START_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getSaleStartDate() {
        return super.getSaleStartDate();
    }

    @EsbName(value = "SALE_END_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getSaleEndDate() {
        return super.getSaleEndDate();
    }

    @EsbName(value = "REGISTER_SETTLE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getBailDate() {
        return super.getBailDate();
    }

    @EsbName(value = "VALUE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getStartDate() {
        return super.getStartDate();
    }

    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getEndDate() {
        return super.getEndDate();
    }

    @EsbName(value = "REPAY_END_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getAllotDate() {
        return super.getAllotDate();
    }

    @EsbName(value = "SALE_BEGIN_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getSaleStartTime() {
        return super.getSaleStartTime();
    }

    @EsbName(value = "SALE_END_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getSaleEndTime() {
        return super.getSaleEndTime();
    }

    @EsbName(value = "SALE_LAST_DAY_END_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getBailTime() {
        return super.getBailTime();
    }

    @EsbName(value = "WORK_START_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getRtStartTime() {
        return super.getRtStartTime();
    }

    @EsbName(value = "WORK_END_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getRtEndTime() {
        return super.getRtEndTime();
    }

    @EsbName(value = "FIX_INVEST_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getFixInvestType() {
        return super.getFixInvestType();
    }

    @EsbName(value = "ALLOW_TRANSFER_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    @Override
    public String getIsChgCust() {
        return super.getIsChgCust();
    }

    @EsbName(value = "PROD_RISK_LEVEL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getRiskLevel() {
        return super.getRiskLevel();
    }

    @EsbName(value = "RISK_DESC")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 500)
    @Override
    public String getRiskRemark() {
        return super.getRiskRemark();
    }

    @EsbName(value = "TOTAL_SCALE_LIMIT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getAllAmounttLmt() {
        return super.getAllAmounttLmt();
    }

    @EsbName(value = "CREDIT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getQuoteType() {
        return super.getQuoteType();
    }

    @EsbName(value = "RELATIVE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    @Override
    public String getRelateType() {
        return super.getRelateType();
    }

    @EsbName(value = "MANAGEMENT_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getNavMode() {
        return super.getNavMode();
    }

    @EsbName(value = "WAIT_CONVERT_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    @Override
    public String getIsCarry() {
        return super.getIsCarry();
    }

    @EsbName(value = "NET_VALUE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getNetValue() {
        return super.getNetValue();
    }

    @EsbName(value = "LAST_NET_VALUE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getLastNetValue() {
        return super.getLastNetValue();
    }

    @EsbName(value = "NET_VALUE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getNavDate() {
        return super.getNavDate();
    }

    @EsbName(value = "INCOME_TYPE_KIND")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getRateType() {
        return super.getRateType();
    }

    @EsbName(value = "ASSIGN_INCOME_PERIOD_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getRateCycleType() {
        return super.getRateCycleType();
    }

    @EsbName(value = "RESERVE_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getPlanRate() {
        return super.getPlanRate();
    }

    @EsbName(value = "EXPECT_INCOME_ADD_DESC")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 600)
    @Override
    public String getPlanRateRemark() {
        return super.getPlanRateRemark();
    }

    @EsbName(value = "LAST_MONTH_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getLastMonthRate() {
        return super.getLastMonthRate();
    }

    @EsbName(value = "LAST_YEAR_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getLastYearRate() {
        return super.getLastYearRate();
    }

    @EsbName(value = "TOTAL_NET_VALUE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getTotalNetValue() {
        return super.getTotalNetValue();
    }

    @EsbName(value = "INTEREST_DAYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getInterestBaseDays() {
        return super.getInterestBaseDays();
    }

    @EsbName(value = "CHARGE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getFeeMode() {
        return super.getFeeMode();
    }

    @EsbName(value = "FRONT_CHARGE_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getBuyFeeRate() {
        return super.getBuyFeeRate();
    }

    @EsbName(value = "BACK_CHARGE_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getDrawFeeRate() {
        return super.getDrawFeeRate();
    }

    @EsbName(value = "BESPEAK_TELLER_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    @Override
    public String getAgencyClientName() {
        return super.getAgencyClientName();
    }

    @EsbName(value = "CHECK_TELLER_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    @Override
    public String getApprTellerName() {
        return super.getApprTellerName();
    }

    @EsbName(value = "AUTHOR_TELLER_ID_ONE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Override
    public String getOwnerName() {
        return super.getOwnerName();
    }

    @EsbName(value = "BUILD_DATE_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getBuildDateTime() {
        return super.getBuildDateTime();
    }

    @EsbName(value = "BUILD_DEPT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getBuildDept() {
        return super.getBuildDept();
    }

    @EsbName(value = "SALE_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getSaleFeeRate() {
        return super.getSaleFeeRate();
    }

    @EsbName(value = "MIN_BUY_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getMinAmount() {
        return super.getMinAmount();
    }

    @EsbName(value = "MAX_BUY_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getMaxAmount() {
        return super.getMaxAmount();
    }

    @EsbName(value = "MIN_HOLD_LOT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getMinLeaveAmount() {
        return super.getMinLeaveAmount();
    }

    @EsbName(value = "UNIT_BUY_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getUnitAmount() {
        return super.getUnitAmount();
    }

    @EsbName(value = "BONUS_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Override
    public String getPartType() {
        return super.getPartType();
    }

    @EsbName(value = "BONUS_FREQUENCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Override
    public String getPartCycle() {
        return super.getPartCycle();
    }

    @EsbName(value = "MAX_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getHighestYield() {
        return super.getHighestYield();
    }

    @EsbName(value = "MIN_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getLowestYield() {
        return super.getLowestYield();
    }

    @EsbName(value = "FIN_PROD_REG_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    @Override
    public String getProductNo() {
        return super.getProductNo();
    }

    @EsbName(value = "ISSUE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 5)
    @Override
    public String getIssuetype() {
        if (StringUtil.equals(DictCodeList.排期发行方式_单一.getValue(), super.getIssuetype())) {
            return "1";
        }
        else if (StringUtil.equals(DictCodeList.排期发行方式_集合.getValue(),
            super.getIssuetype())) {
            return "2";
        }
        return super.getIssuetype();
    }

    /**
     * 取得 产品系列代码
     * 
     * @return 产品系列代码
     * @see
     * @since 1.0
     */
    @EsbName(value = "PROD_SERIES_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置产品系列代码
     * 
     * @param portfolioId 产品系列代码
     * @see
     * @since 1.0
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 取得 产品系列名称
     * 
     * @return 产品系列名称
     * @see
     * @since 1.0
     */
    @EsbName(value = "PROD_SERIES_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Required
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置产品系列名称
     * 
     * @param portfolioName 产品系列名称
     * @see
     * @since 1.0
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 取得 品牌代码
     * 
     * @return 品牌代码
     * @see
     * @since 1.0
     */
    @EsbName(value = "BRAND_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * 设置品牌代码
     * 
     * @param brandCode 品牌代码
     * @see
     * @since 1.0
     */
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    /**
     * 取得子产品代码
     * 
     * @return 子产品代码
     * @see
     * @since 1.0
     */
    @EsbName(value = "SUB_PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    public String getSubProductCode() {
        return subProductCode;
    }

    /**
     * 设置子产品代码
     * 
     * @param subProductCode 子产品代码
     * @see
     * @since 1.0
     */
    public void setSubProductCode(String subProductCode) {
        this.subProductCode = subProductCode;
    }

    /**
     * 取得中文全称
     * 
     * @return 中文全称
     * @see
     * @since 1.0
     */
    @EsbName(value = "CHN_FULL_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    public String getChnFullName() {
        return chnFullName;
    }

    /**
     * 设置 中文全称
     * 
     * @param chnFullName 中文全称
     * @see
     * @since 1.0
     */
    public void setChnFullName(String chnFullName) {
        this.chnFullName = chnFullName;
    }

    /**
     * 获取 品牌名称
     * 
     * @return 品牌名称
     */
    @EsbName(value = "BRAND_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置 品牌名称
     * 
     * @param brandName 品牌名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取 产品中文简称
     * 
     * @return proChShortName
     */
    @EsbName(value = "PRO_CH_SHORTNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 256)
    @Required
    public String getProChShortName() {
        return proChShortName;
    }

    /**
     * 设置 产品中文简称
     * 
     * @param proChShortName 产品中文简称
     */
    public void setProChShortName(String proChShortName) {
        this.proChShortName = proChShortName;
    }

    /**
     * 获取产品监管登记编码
     * 
     * @return 产品监管登记编码
     */
    @EsbName(value = "PROD_CTRL_REG_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    public String getProdCtrlregcode() {
        return prodCtrlregcode;
    }

    /**
     * 设置产品监管登记编码
     * 
     * @param prodCtrlregcode 产品监管登记编码
     */
    public void setProdCtrlregcode(String prodCtrlregcode) {
        this.prodCtrlregcode = prodCtrlregcode;
    }

    /**
     * 获取产品类型
     * 
     * @return 产品类型
     */
    @EsbName(value = "PRODUCT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    public String getProductType() {
        return productType;
    }

    /**
     * 设置产品类型
     * 
     * @param productType 产品类型
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * 获取募集方式
     * 
     * @return 募集方式
     */
    @EsbName(value = "COLLECT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getCollectType() {
        return collectType;
    }

    /**
     * 设置募集方式
     * 
     * @param collectType 募集方式
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    /**
     * 获取销售商
     * 
     * @return 销售商
     */
    @EsbName(value = "VENDOR")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    public String getVendor() {
        return vendor;
    }

    /**
     * 设置销售商
     * 
     * @param vendor 销售商
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * 获取销售模式
     * 
     * @return 销售模式
     */
    @EsbName(value = "SALE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getSaleMode() {
        return saleMode;
    }

    /**
     * 设置销售模式
     * 
     * @param saleMode 销售模式
     */
    public void setSaleMode(String saleMode) {
        this.saleMode = saleMode;
    }

    /**
     * 获取按投资地区分类
     * 
     * @return 按投资地区分类
     */
    @EsbName(value = "INVEST_AREA_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getInvestAreatype() {
        return investAreatype;
    }

    /**
     * 设置按投资地区分类
     * 
     * @param investAreatype 按投资地区分类
     */
    public void setInvestAreatype(String investAreatype) {
        this.investAreatype = investAreatype;
    }

    /**
     * 获取 发行对象分类
     * 
     * @return 发行对象分类
     */
    @EsbName(value = "ISSUE_OBJECT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getIssueObjecttype() {
        return issueObjecttype;
    }

    /**
     * 设置发行对象分类
     * 
     * @param issueObjecttype 发行对象分类
     */
    public void setIssueObjecttype(String issueObjecttype) {
        this.issueObjecttype = issueObjecttype;
    }

    /**
     * 获取信息公布日期
     * 
     * @return 信息公布日期
     */
    @EsbName(value = "MSG_PUBLISH_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getMsgPublishdate() {
        return msgPublishdate;
    }

    /**
     * 设置信息公布日期
     * 
     * @param msgPublishdate 信息公布日期
     */
    public void setMsgPublishdate(String msgPublishdate) {
        this.msgPublishdate = msgPublishdate;
    }

    /**
     * 获取滚动周期
     * 
     * @return 滚动周期
     */
    @EsbName(value = "ROLL_CYCLE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getRollCycle() {
        return rollCycle;
    }

    /**
     * 设置滚动周期
     * 
     * @param rollCycle 滚动周期
     */
    public void setRollCycle(String rollCycle) {
        this.rollCycle = rollCycle;
    }

    /**
     * 获取产品发行最小规模
     * 
     * @return 产品发行最小规模
     */
    @EsbName(value = "PROD_ISSUE_MIN_SCALE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getProdIssueminscale() {
        return prodIssueminscale;
    }

    /**
     * 设置产品发行最小规模
     * 
     * @param prodIssueminscale 产品发行最小规模
     */
    public void setProdIssueminscale(String prodIssueminscale) {
        this.prodIssueminscale = prodIssueminscale;
    }

    /**
     * 获取首次结转日
     * 
     * @return 首次结转日
     */
    @EsbName(value = "FIRST_CONVERT_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getFirstConvertdate() {
        return firstConvertdate;
    }

    /**
     * 设置首次结转日
     * 
     * @param firstConvertdate 首次结转日
     */
    public void setFirstConvertdate(String firstConvertdate) {
        this.firstConvertdate = firstConvertdate;
    }

    /**
     * 获取结转周期
     * 
     * @return 结转周期
     */
    @EsbName(value = "CONVERT_CYCLE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getConvertTycle() {
        return convertTycle;
    }

    /**
     * 设置结转周期
     * 
     * @param convertTycle 结转周期
     */
    public void setConvertTycle(String convertTycle) {
        this.convertTycle = convertTycle;
    }

    /**
     * 获取结转推算方法
     * 
     * @return 结转推算方法
     */
    @EsbName(value = "CONVERT_RECKON_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getConvertReckonMode() {
        return convertReckonMode;
    }

    /**
     * 设置结转推算方法
     * 
     * @param convertReckonMode 结转推算方法
     */
    public void setConvertReckonMode(String convertReckonMode) {
        this.convertReckonMode = convertReckonMode;
    }

    /**
     * 获取首次开放日
     * 
     * @return 首次开放日
     */
    @EsbName(value = "FIRST_OPEN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getFirstOpendate() {
        return firstOpendate;
    }

    /**
     * 设置首次开放日
     * 
     * @param firstOpendate 首次开放日
     */
    public void setFirstOpendate(String firstOpendate) {
        this.firstOpendate = firstOpendate;
    }

    /**
     * 获取开放持续天数
     * 
     * @return 开放持续天数
     */
    @EsbName(value = "DEVELOP_CONT_DAYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getDevelOpcontdays() {
        return develOpcontdays;
    }

    /**
     * 设置 开放持续天数
     * 
     * @param develOpcontdays 开放持续天数
     */
    public void setDevelOpcontdays(String develOpcontdays) {
        this.develOpcontdays = develOpcontdays;
    }

    /**
     * 获取开放周期
     * 
     * @return 开放周期
     */
    @EsbName(value = "OPEN_PERIOD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOpenPeriod() {
        return openPeriod;
    }

    /**
     * 设置开放周期
     * 
     * @param openPeriod 开放周期
     */
    public void setOpenPeriod(String openPeriod) {
        this.openPeriod = openPeriod;
    }

    /**
     * 获取开放推算方法
     * 
     * @return 开放推算方法
     */
    @EsbName(value = "OPEN_RECKON_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOpenReckonmode() {
        return openReckonmode;
    }

    /**
     * 设置开放推算方法
     * 
     * @param openReckonmode 开放推算方法
     */
    public void setOpenReckonmode(String openReckonmode) {
        this.openReckonmode = openReckonmode;
    }

    /**
     * 获取管理费率
     * 
     * @return 管理费率
     */
    @EsbName(value = "MANAGEMENT_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getManagementFeerate() {
        return managementFeerate;
    }

    /**
     * 设置管理费率
     * 
     * @param managementFeerate 管理费率
     */
    public void setManagementFeerate(String managementFeerate) {
        this.managementFeerate = managementFeerate;
    }

    /**
     * 获取托管费率
     * 
     * @return 托管费率
     */
    @EsbName(value = "CONSIGN_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getConsignFeerate() {
        return consignFeerate;
    }

    /**
     * 设置托管费率
     * 
     * @param consignFeerate 托管费率
     */
    public void setConsignFeerate(String consignFeerate) {
        this.consignFeerate = consignFeerate;
    }

    /**
     * 获取其他费率
     * 
     * @return 其他费率
     */
    @EsbName(value = "OTHER_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOtherFeerate() {
        return otherFeerate;
    }

    /**
     * 设置其他费率
     * 
     * @param otherFeerate 其他费率
     */
    public void setOtherFeerate(String otherFeerate) {
        this.otherFeerate = otherFeerate;
    }

    /**
     * 设置 产品清盘日
     * 
     * @param prodWindingDt 产品清盘日
     */
    public void setProdWindingDt(String prodWindingDt) {
        this.prodWindingDt = prodWindingDt;
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    @Override
    public String getRemark() {
        return super.getRemark();
    }

    /**
     * 取得 分行ID
     * 
     * @return 分行ID
     */
    @EsbName(value = "BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public String getBranchId() {
        return StringUtil.equals(DictCodeList.排期发行方式_单一.getValue(),
            super.getIssuetype()) ? super.getBranchId() : null;
    }

    /**
     * 取得 产品清盘日
     * 
     * @return 产品清盘日
     */
    @EsbName(value = "PROD_WINDING_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getProdWindingDt() {
        return prodWindingDt;
    }

    /**
     * 获取 产品观察日数组
     * 
     * @return 产品观察日数组.
     */
    @EsbName(value = "PROD_OBSERVE_DATE_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public List<Interf150006InArrEntity> getObserveDtList() {
        return observeDtList;
    }

    /**
     * 设置 产品观察日数组
     * 
     * @param observeDtList 产品观察日数组
     */
    public void setObserveDtList(List<Interf150006InArrEntity> observeDtList) {
        this.observeDtList = observeDtList;
    }

    /**
     * 获取 开放周期单位
     * 
     * @return 开放周期单位.
     */
    @EsbName(value = "OPEN_PERIOD_UNIT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOpenPeriodUnit() {
        return openPeriodUnit;
    }

    /**
     * 设置 开放周期单位
     * 
     * @param openPeriodUnit 开放周期单位
     */
    public void setOpenPeriodUnit(String openPeriodUnit) {
        this.openPeriodUnit = openPeriodUnit;
    }
}
