/*
 * 文件名：Inter448001InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448001.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口448001输入
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月18日
 * @see Interf448001InEntity
 * @since 1.0
 */
public class Interf448001InEntity extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6646527736272801053L;

    /**
     * 产品简称
     */
    private String shortName;

    /**
     * 中文全称
     */
    private String productName;

    /**
     * 年度
     */
    private String yearId;

    /**
     * 币种
     */
    private String currencyId;

    /**
     * (滚动模式)
     */
    private String cycleMode;

    /**
     * (本期数量)
     */
    private String cycleDays;

    /**
     * (期限说明)
     */
    private String cycleRemark;

    /**
     * (是否周期性产品)
     */
    private String isCycle;

    /**
     * (是否允许提前终止)
     */
    private String isPreAllot;

    /**
     * (提前终止类别)
     */
    private String preallotType;

    /**
     * (默认分红模式)
     */
    private String interestMode;

    /**
     * (分红模式是否可改)
     */
    private String interestModeChg;

    /**
     * (默认本金到期模式)
     */
    private String endInstruct;

    /**
     * (本金到期模式是否可改)
     */
    private String endInstructChg;

    /**
     * (是否已经截止)
     */
    private String isExpire;

    /**
     * (本期预留发售天数)
     */
    private String saleDayNum;

    /**
     * (开始销售日)
     */
    private String saleStartDate;

    /**
     * (截止销售日)
     */
    private String saleEndDate;

    /**
     * (登记清算日期)
     */
    private String bailDate;

    /**
     * (起息日)
     */
    private String startDate;

    /**
     * (到期日)
     */
    private String endDate;

    /**
     * (还款截止日)
     */
    private String allotDate;

    /**
     * (发售开始时间)
     */
    private String saleStartTime;

    /**
     * (发售结束时间)
     */
    private String saleEndTime;

    /**
     * (发售末日结束时间)
     */
    private String bailTime;

    /**
     * (工作日开始时间)
     */
    private String rtStartTime;

    /**
     * (工作日结束时间)
     */
    private String rtEndTime;

    /**
     * (定投类别)
     */
    private String fixInvestType;

    /**
     * (是否允许过户)
     */
    private String isChgCust;

    /**
     * (风险等级)
     */
    private String riskLevel;

    /**
     * (风险说明)
     */
    private String riskRemark;

    /**
     * (总规模限制)
     */
    private String allAmounttLmt;

    /**
     * (额度类别)
     */
    private String quoteType;

    /**
     * (关联类别)
     */
    private String relateType;

    /**
     * (管理模式)
     */
    private String navMode;

    /**
     * (是否具有待结转收益)
     */
    private String isCarry;

    /**
     * (净值)
     */
    private String netValue;

    /**
     * (上一交易日净值)
     */
    private String lastNetValue;

    /**
     * (净值日期)
     */
    private String navDate;

    /**
     * (按收益类别分类)
     */
    private String rateType;

    /**
     * (分配收益周期类别)
     */
    private String rateCycleType;

    /**
     * (预定收益率，100元的年收益)
     */
    private String planRate;

    /**
     * (预期收益附加说明)
     */
    private String planRateRemark;

    /**
     * (最近月收益率)
     */
    private String lastMonthRate;

    /**
     * (最近年化收益率)
     */
    private String lastYearRate;

    /**
     * (累计净值)
     */
    private String totalNetValue;

    /**
     * (计息天数)
     */
    private String interestBaseDays;

    /**
     * (收费模式)
     */
    private String feeMode;

    /**
     * (前端收费参考比例)
     */
    private String buyFeeRate;

    /**
     * (后端收费参考比例)
     */
    private String drawFeeRate;

    /**
     * (预约柜员)
     */
    private String agencyClientName;

    /**
     * (复核柜员)
     */
    private String apprTellerName;

    /**
     * (授权柜员1)
     */
    private String ownerName;

    /**
     * (建立日期时间)
     */
    private String buildDateTime;

    /**
     * (建立部门)
     */
    private String buildDept;

    /**
     * 销售费率
     */
    private String saleFeeRate;

    /**
     * 最低购买金额
     */
    private String minAmount;

    /**
     * 最高购买金额
     */
    private String maxAmount;

    /**
     * 最低持有份额
     */
    private String minLeaveAmount;

    /**
     * 单位购买金额
     */
    private String unitAmount;

    /**
     * 分红方式
     */
    private String partType;

    /**
     * 分红频率
     */
    private String partCycle;

    /**
     * 最高收益率
     */
    private String highestYield;

    /**
     * 最低收益率
     */
    private String lowestYield;

    /**
     * 理财产品登记编码
     */
    private String productNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发行方式
     */
    private String issuetype;

    /**
     * 分行ID
     */
    private String branchId;

    /**
     * 产品中文简称
     */
    private String prodName;

    /**
     * 取得 产品简称
     * 
     * @return 产品简称
     */
    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    public String getShortName() {
        return shortName;
    }

    /**
     * 取得 中文全称
     * 
     * @return 中文全称
     */
    @EsbName(value = "ProductName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Required
    public String getProductName() {
        return productName;
    }

    /**
     * 取得 年度
     * 
     * @return 年度
     */
    @EsbName(value = "YearId")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getYearId() {
        return yearId;
    }

    /**
     * 取得 币种
     * 
     * @return 币种
     */
    @EsbName(value = "CurrencyId")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    public String getCurrencyId() {
        return currencyId;
    }

    /**
     * 取得 (滚动模式)
     * 
     * @return (滚动模式)
     */
    @EsbName(value = "CycleMode")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getCycleMode() {
        return cycleMode;
    }

    /**
     * 取得 (本期数量)
     * 
     * @return (本期数量)
     */
    @EsbName(value = "CycleDays")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getCycleDays() {
        return cycleDays;
    }

    /**
     * 取得 (期限说明)
     * 
     * @return (期限说明)
     */
    @EsbName(value = "CycleRemark")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getCycleRemark() {
        return cycleRemark;
    }

    /**
     * 取得 (是否周期性产品)
     * 
     * @return (是否周期性产品)
     */
    @EsbName(value = "IsCycle")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getIsCycle() {
        return isCycle;
    }

    /**
     * 取得 (是否允许提前终止)
     * 
     * @return (是否允许提前终止)
     */
    @EsbName(value = "IsPreAllot")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getIsPreAllot() {
        return isPreAllot;
    }

    /**
     * 取得 (提前终止类别)
     * 
     * @return (提前终止类别)
     */
    @EsbName(value = "PreallotType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    public String getPreallotType() {
        return preallotType;
    }

    /**
     * 取得 (默认分红模式)
     * 
     * @return (默认分红模式)
     */
    @EsbName(value = "InterestMode")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getInterestMode() {
        return interestMode;
    }

    /**
     * 取得 (分红模式是否可改)
     * 
     * @return (分红模式是否可改)
     */
    @EsbName(value = "InterestModeChg")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getInterestModeChg() {
        return interestModeChg;
    }

    /**
     * 取得 (默认本金到期模式)
     * 
     * @return (默认本金到期模式)
     */
    @EsbName(value = "EndInstruct")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getEndInstruct() {
        return endInstruct;
    }

    /**
     * 取得 (本金到期模式是否可改)
     * 
     * @return (本金到期模式是否可改)
     */
    @EsbName(value = "EndInstructChg")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getEndInstructChg() {
        return endInstructChg;
    }

    /**
     * 取得 (是否已经截止)
     * 
     * @return (是否已经截止)
     */
    @EsbName(value = "IsExpire")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getIsExpire() {
        return isExpire;
    }

    /**
     * 取得 (本期预留发售天数)
     * 
     * @return (本期预留发售天数)
     */
    @EsbName(value = "SaleDayNum")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getSaleDayNum() {
        return saleDayNum;
    }

    /**
     * 取得 (开始销售日)
     * 
     * @return (开始销售日)
     */
    @EsbName(value = "SaleStartDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getSaleStartDate() {
        return saleStartDate;
    }

    /**
     * 取得 (截止销售日)
     * 
     * @return (截止销售日)
     */
    @EsbName(value = "SaleEndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getSaleEndDate() {
        return saleEndDate;
    }

    /**
     * 取得 (登记清算日期)
     * 
     * @return (登记清算日期)
     */
    @EsbName(value = "BailDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getBailDate() {
        return bailDate;
    }

    /**
     * 取得 (起息日)
     * 
     * @return (起息日)
     */
    @EsbName(value = "StartDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得 (到期日)
     * 
     * @return (到期日)
     */
    @EsbName(value = "EndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getEndDate() {
        return endDate;
    }

    /**
     * 取得 (还款截止日)
     * 
     * @return (还款截止日)
     */
    @EsbName(value = "AllotDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getAllotDate() {
        return allotDate;
    }

    /**
     * 取得 (发售开始时间)
     * 
     * @return (发售开始时间)
     */
    @EsbName(value = "SaleStartTime")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getSaleStartTime() {
        return saleStartTime;
    }

    /**
     * 取得 (发售结束时间)
     * 
     * @return (发售结束时间)
     */
    @EsbName(value = "SaleEndTime")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getSaleEndTime() {
        return saleEndTime;
    }

    /**
     * 取得 (发售末日结束时间)
     * 
     * @return (发售末日结束时间)
     */
    @EsbName(value = "BailTime")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getBailTime() {
        return bailTime;
    }

    /**
     * 取得 (工作日开始时间)
     * 
     * @return (工作日开始时间)
     */
    @EsbName(value = "RtStartTime")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getRtStartTime() {
        return rtStartTime;
    }

    /**
     * 取得 (工作日结束时间)
     * 
     * @return (工作日结束时间)
     */
    @EsbName(value = "RtEndTime")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getRtEndTime() {
        return rtEndTime;
    }

    /**
     * 取得 (定投类别)
     * 
     * @return (定投类别)
     */
    @EsbName(value = "FixInvestType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getFixInvestType() {
        return fixInvestType;
    }

    /**
     * 取得 (是否允许过户)
     * 
     * @return (是否允许过户)
     */
    @EsbName(value = "IsChgCust")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getIsChgCust() {
        return isChgCust;
    }

    /**
     * 取得 (风险等级)
     * 
     * @return (风险等级)
     */
    @EsbName(value = "RiskLevel")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 取得 (风险说明)
     * 
     * @return (风险说明)
     */
    @EsbName(value = "RiskRemark")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getRiskRemark() {
        return riskRemark;
    }

    /**
     * 取得 (总规模限制)
     * 
     * @return (总规模限制)
     */
    @EsbName(value = "AllAmounttLmt")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 13)
    @Required
    public String getAllAmounttLmt() {
        return allAmounttLmt;
    }

    /**
     * 取得 (额度类别)
     * 
     * @return (额度类别)
     */
    @EsbName(value = "QuoteType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * 取得 (关联类别)
     * 
     * @return (关联类别)
     */
    @EsbName(value = "RelateType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getRelateType() {
        return relateType;
    }

    /**
     * 取得 (管理模式)
     * 
     * @return (管理模式)
     */
    @EsbName(value = "NavMode")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getNavMode() {
        return navMode;
    }

    /**
     * 取得 (是否具有待结转收益)
     * 
     * @return (是否具有待结转收益)
     */
    @EsbName(value = "IsCarry")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getIsCarry() {
        return isCarry;
    }

    /**
     * 取得 (净值)
     * 
     * @return (净值)
     */
    @EsbName(value = "NetValue")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getNetValue() {
        return netValue;
    }

    /**
     * 取得 (上一交易日净值)
     * 
     * @return (上一交易日净值)
     */
    @EsbName(value = "LastNetValue")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getLastNetValue() {
        return lastNetValue;
    }

    /**
     * 取得 (净值日期)
     * 
     * @return (净值日期)
     */
    @EsbName(value = "NavDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getNavDate() {
        return navDate;
    }

    /**
     * 取得 (按收益类别分类)
     * 
     * @return (按收益类别分类)
     */
    @EsbName(value = "RateType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 6)
    public String getRateType() {
        return rateType;
    }

    /**
     * 取得 (分配收益周期类别)
     * 
     * @return (分配收益周期类别)
     */
    @EsbName(value = "RateCycleType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    public String getRateCycleType() {
        return rateCycleType;
    }

    /**
     * 取得 (预定收益率，100元的年收益)
     * 
     * @return (预定收益率，100元的年收益)
     */
    @EsbName(value = "PlanRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getPlanRate() {
        return planRate;
    }

    /**
     * 取得 (预期收益附加说明)
     * 
     * @return (预期收益附加说明)
     */
    @EsbName(value = "PlanRateRemark")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getPlanRateRemark() {
        return planRateRemark;
    }

    /**
     * 取得 (最近月收益率)
     * 
     * @return (最近月收益率)
     */
    @EsbName(value = "LastMonthRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getLastMonthRate() {
        return lastMonthRate;
    }

    /**
     * 取得 (最近年化收益率)
     * 
     * @return (最近年化收益率)
     */
    @EsbName(value = "LastYearRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getLastYearRate() {
        return lastYearRate;
    }

    /**
     * 取得 (累计净值)
     * 
     * @return (累计净值)
     */
    @EsbName(value = "TotalNetValue")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getTotalNetValue() {
        return totalNetValue;
    }

    /**
     * 取得 (计息天数)
     * 
     * @return (计息天数)
     */
    @EsbName(value = "InterestBaseDays")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getInterestBaseDays() {
        return interestBaseDays;
    }

    /**
     * 取得 (收费模式)
     * 
     * @return (收费模式)
     */
    @EsbName(value = "FeeMode")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    public String getFeeMode() {
        return feeMode;
    }

    /**
     * 取得 (前端收费参考比例)
     * 
     * @return (前端收费参考比例)
     */
    @EsbName(value = "BuyFeeRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getBuyFeeRate() {
        return buyFeeRate;
    }

    /**
     * 取得 (后端收费参考比例)
     * 
     * @return (后端收费参考比例)
     */
    @EsbName(value = "DrawFeeRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getDrawFeeRate() {
        return drawFeeRate;
    }

    /**
     * 取得 (预约柜员)
     * 
     * @return (预约柜员)
     */
    @EsbName(value = "BuildUserId")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getAgencyClientName() {
        return agencyClientName;
    }

    /**
     * 取得 (复核柜员)
     * 
     * @return (复核柜员)
     */
    @EsbName(value = "CheckUserId")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getApprTellerName() {
        return apprTellerName;
    }

    /**
     * 取得 (授权柜员1)
     * 
     * @return (授权柜员1)
     */
    @EsbName(value = "AuthorUserId1")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 取得 (建立日期时间)
     * 
     * @return (建立日期时间)
     */
    @EsbName(value = "BuildDateTime")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 14)
    @Required
    public String getBuildDateTime() {
        return buildDateTime;
    }

    /**
     * 取得 (建立部门)
     * 
     * @return (建立部门)
     */
    @EsbName(value = "BuildDept")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getBuildDept() {
        return buildDept;
    }

    /**
     * 取得 销售费率
     * 
     * @return 销售费率
     */
    @EsbName(value = "SaleFeeRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 取得 最低购买金额
     * 
     * @return 最低购买金额
     */
    @EsbName(value = "MinAmount")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 15)
    public String getMinAmount() {
        return minAmount;
    }

    /**
     * 取得 最高购买金额
     * 
     * @return 最高购买金额
     */
    @EsbName(value = "MaxAmount")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 15)
    public String getMaxAmount() {
        return maxAmount;
    }

    /**
     * 取得 最低持有份额
     * 
     * @return 最低持有份额
     */
    @EsbName(value = "MinLeaveAmount")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 15)
    public String getMinLeaveAmount() {
        return minLeaveAmount;
    }

    /**
     * 取得 单位购买金额
     * 
     * @return 单位购买金额
     */
    @EsbName(value = "UnitAmount")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 9)
    public String getUnitAmount() {
        return unitAmount;
    }

    /**
     * 设置 产品简称
     * 
     * @param shortName 产品简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 设置 中文全称
     * 
     * @param productName 中文全称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 设置 年度
     * 
     * @param yearId 年度
     */
    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    /**
     * 设置 币种
     * 
     * @param currencyId 币种
     */
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * 设置 (滚动模式)
     * 
     * @param cycleMode (滚动模式)
     */
    public void setCycleMode(String cycleMode) {
        this.cycleMode = cycleMode;
    }

    /**
     * 设置 (本期数量)
     * 
     * @param cycleDays (本期数量)
     */
    public void setCycleDays(String cycleDays) {
        this.cycleDays = cycleDays;
    }

    /**
     * 设置 (期限说明)
     * 
     * @param cycleRemark (期限说明)
     */
    public void setCycleRemark(String cycleRemark) {
        this.cycleRemark = cycleRemark;
    }

    /**
     * 设置 (是否周期性产品)
     * 
     * @param isCycle (是否周期性产品)
     */
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
    }

    /**
     * 设置 (是否允许提前终止)
     * 
     * @param isPreAllot (是否允许提前终止)
     */
    public void setIsPreAllot(String isPreAllot) {
        this.isPreAllot = isPreAllot;
    }

    /**
     * 设置 (提前终止类别)
     * 
     * @param preallotType (提前终止类别)
     */
    public void setPreallotType(String preallotType) {
        this.preallotType = preallotType;
    }

    /**
     * 设置 (默认分红模式)
     * 
     * @param interestMode (默认分红模式)
     */
    public void setInterestMode(String interestMode) {
        this.interestMode = interestMode;
    }

    /**
     * 设置 (分红模式是否可改)
     * 
     * @param interestModeChg (分红模式是否可改)
     */
    public void setInterestModeChg(String interestModeChg) {
        this.interestModeChg = interestModeChg;
    }

    /**
     * 设置 (默认本金到期模式)
     * 
     * @param endInstruct (默认本金到期模式)
     */
    public void setEndInstruct(String endInstruct) {
        this.endInstruct = endInstruct;
    }

    /**
     * 设置 (本金到期模式是否可改)
     * 
     * @param endInstructChg (本金到期模式是否可改)
     */
    public void setEndInstructChg(String endInstructChg) {
        this.endInstructChg = endInstructChg;
    }

    /**
     * 设置 (是否已经截止)
     * 
     * @param isExpire (是否已经截止)
     */
    public void setIsExpire(String isExpire) {
        this.isExpire = isExpire;
    }

    /**
     * 设置 (本期预留发售天数)
     * 
     * @param saleDayNum (本期预留发售天数)
     */
    public void setSaleDayNum(String saleDayNum) {
        this.saleDayNum = saleDayNum;
    }

    /**
     * 设置 (开始销售日)
     * 
     * @param saleStartDate (开始销售日)
     */
    public void setSaleStartDate(String saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    /**
     * 设置 (截止销售日)
     * 
     * @param saleEndDate (截止销售日)
     */
    public void setSaleEndDate(String saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    /**
     * 设置 (登记清算日期)
     * 
     * @param bailDate (登记清算日期)
     */
    public void setBailDate(String bailDate) {
        this.bailDate = bailDate;
    }

    /**
     * 设置 (起息日)
     * 
     * @param startDate (起息日)
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 设置 (到期日)
     * 
     * @param endDate (到期日)
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 设置 (还款截止日)
     * 
     * @param allotDate (还款截止日)
     */
    public void setAllotDate(String allotDate) {
        this.allotDate = allotDate;
    }

    /**
     * 设置 (发售开始时间)
     * 
     * @param saleStartTime (发售开始时间)
     */
    public void setSaleStartTime(String saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    /**
     * 设置 (发售结束时间)
     * 
     * @param saleEndTime (发售结束时间)
     */
    public void setSaleEndTime(String saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    /**
     * 设置 (发售末日结束时间)
     * 
     * @param bailTime (发售末日结束时间)
     */
    public void setBailTime(String bailTime) {
        this.bailTime = bailTime;
    }

    /**
     * 设置 (工作日开始时间)
     * 
     * @param rtStartTime (工作日开始时间)
     */
    public void setRtStartTime(String rtStartTime) {
        this.rtStartTime = rtStartTime;
    }

    /**
     * 设置 (工作日结束时间)
     * 
     * @param rtEndTime (工作日结束时间)
     */
    public void setRtEndTime(String rtEndTime) {
        this.rtEndTime = rtEndTime;
    }

    /**
     * 设置 (定投类别)
     * 
     * @param fixInvestType (定投类别)
     */
    public void setFixInvestType(String fixInvestType) {
        this.fixInvestType = fixInvestType;
    }

    /**
     * 设置 (是否允许过户)
     * 
     * @param isChgCust (是否允许过户)
     */
    public void setIsChgCust(String isChgCust) {
        this.isChgCust = isChgCust;
    }

    /**
     * 设置 (风险等级)
     * 
     * @param riskLevel (风险等级)
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * 设置 (风险说明)
     * 
     * @param riskRemark (风险说明)
     */
    public void setRiskRemark(String riskRemark) {
        this.riskRemark = riskRemark;
    }

    /**
     * 设置 (总规模限制)
     * 
     * @param allAmounttLmt (总规模限制)
     */
    public void setAllAmounttLmt(String allAmounttLmt) {
        this.allAmounttLmt = allAmounttLmt;
    }

    /**
     * 设置 (额度类别)
     * 
     * @param quoteType (额度类别)
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    /**
     * 设置 (关联类别)
     * 
     * @param relateType (关联类别)
     */
    public void setRelateType(String relateType) {
        this.relateType = relateType;
    }

    /**
     * 设置 (管理模式)
     * 
     * @param navMode (管理模式)
     */
    public void setNavMode(String navMode) {
        this.navMode = navMode;
    }

    /**
     * 设置 (是否具有待结转收益)
     * 
     * @param isCarry (是否具有待结转收益)
     */
    public void setIsCarry(String isCarry) {
        this.isCarry = isCarry;
    }

    /**
     * 设置 (净值)
     * 
     * @param netValue (净值)
     */
    public void setNetValue(String netValue) {
        this.netValue = netValue;
    }

    /**
     * 设置 (上一交易日净值)
     * 
     * @param lastNetValue (上一交易日净值)
     */
    public void setLastNetValue(String lastNetValue) {
        this.lastNetValue = lastNetValue;
    }

    /**
     * 设置 (净值日期)
     * 
     * @param navDate (净值日期)
     */
    public void setNavDate(String navDate) {
        this.navDate = navDate;
    }

    /**
     * 设置 (按收益类别分类)
     * 
     * @param rateType (按收益类别分类)
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * 设置 (分配收益周期类别)
     * 
     * @param rateCycleType (分配收益周期类别)
     */
    public void setRateCycleType(String rateCycleType) {
        this.rateCycleType = rateCycleType;
    }

    /**
     * 设置 (预定收益率，100元的年收益)
     * 
     * @param planRate (预定收益率，100元的年收益)
     */
    public void setPlanRate(String planRate) {
        this.planRate = planRate;
    }

    /**
     * 设置 (预期收益附加说明)
     * 
     * @param planRateRemark (预期收益附加说明)
     */
    public void setPlanRateRemark(String planRateRemark) {
        this.planRateRemark = planRateRemark;
    }

    /**
     * 设置 (最近月收益率)
     * 
     * @param lastMonthRate (最近月收益率)
     */
    public void setLastMonthRate(String lastMonthRate) {
        this.lastMonthRate = lastMonthRate;
    }

    /**
     * 设置 (最近年化收益率)
     * 
     * @param lastYearRate (最近年化收益率)
     */
    public void setLastYearRate(String lastYearRate) {
        this.lastYearRate = lastYearRate;
    }

    /**
     * 设置 (累计净值)
     * 
     * @param totalNetValue (累计净值)
     */
    public void setTotalNetValue(String totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    /**
     * 设置 (计息天数)
     * 
     * @param interestBaseDays (计息天数)
     */
    public void setInterestBaseDays(String interestBaseDays) {
        this.interestBaseDays = interestBaseDays;
    }

    /**
     * 设置 (收费模式)
     * 
     * @param feeMode (收费模式)
     */
    public void setFeeMode(String feeMode) {
        this.feeMode = feeMode;
    }

    /**
     * 设置 (前端收费参考比例)
     * 
     * @param buyFeeRate (前端收费参考比例)
     */
    public void setBuyFeeRate(String buyFeeRate) {
        this.buyFeeRate = buyFeeRate;
    }

    /**
     * 设置 (后端收费参考比例)
     * 
     * @param drawFeeRate (后端收费参考比例)
     */
    public void setDrawFeeRate(String drawFeeRate) {
        this.drawFeeRate = drawFeeRate;
    }

    /**
     * 设置 (建立日期时间)
     * 
     * @param buildDateTime (建立日期时间)
     */
    public void setBuildDateTime(String buildDateTime) {
        this.buildDateTime = buildDateTime;
    }

    /**
     * 设置 (建立部门)
     * 
     * @param buildDept (建立部门)
     */
    public void setBuildDept(String buildDept) {
        this.buildDept = buildDept;
    }

    /**
     * 设置 销售费率
     * 
     * @param saleFeeRate 销售费率
     */
    public void setSaleFeeRate(String saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 设置 最低购买金额
     * 
     * @param minAmount 最低购买金额
     */
    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 设置 最高购买金额
     * 
     * @param maxAmount 最高购买金额
     */
    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * 设置 最低持有份额
     * 
     * @param minLeaveAmount 最低持有份额
     */
    public void setMinLeaveAmount(String minLeaveAmount) {
        this.minLeaveAmount = minLeaveAmount;
    }

    /**
     * 设置 单位购买金额
     * 
     * @param unitAmount 单位购买金额
     */
    public void setUnitAmount(String unitAmount) {
        this.unitAmount = unitAmount;
    }

    /**
     * 取得 分红方式
     * 
     * @return 分红方式
     */
    @EsbName(value = "PartType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getPartType() {
        return partType;
    }

    /**
     * 取得 分红频率
     * 
     * @return 分红频率
     */
    @EsbName(value = "PartCycle")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    public String getPartCycle() {
        return partCycle;
    }

    /**
     * 取得 最高收益率
     * 
     * @return 最高收益率
     */
    @EsbName(value = "HighestYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getHighestYield() {
        return highestYield;
    }

    /**
     * 取得 最低收益率
     * 
     * @return 最低收益率
     */
    @EsbName(value = "LowestYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getLowestYield() {
        return lowestYield;
    }

    /**
     * 取得 理财产品登记编码
     * 
     * @return 理财产品登记编码
     */
    @EsbName(value = "ProductNo")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置 分红方式
     * 
     * @param partType 分红方式
     */
    public void setPartType(String partType) {
        this.partType = partType;
    }

    /**
     * 设置 分红频率
     * 
     * @param partCycle 分红频率
     */
    public void setPartCycle(String partCycle) {
        this.partCycle = partCycle;
    }

    /**
     * 设置 最高收益率
     * 
     * @param highestYield 最高收益率
     */
    public void setHighestYield(String highestYield) {
        this.highestYield = highestYield;
    }

    /**
     * 设置 最低收益率
     * 
     * @param lowestYield 最低收益率
     */
    public void setLowestYield(String lowestYield) {
        this.lowestYield = lowestYield;
    }

    /**
     * 设置 理财产品登记编码
     * 
     * @param productNo 理财产品登记编码
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 取得 发行方式
     * 
     * @return 发行方式
     */
    public String getIssuetype() {
        return issuetype;
    }

    /**
     * 取得 分行ID
     * 
     * @return 分行ID
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 设置 发行方式
     * 
     * @param issuetype 发行方式
     */
    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    /**
     * 设置 分行ID
     * 
     * @param branchId 分行ID
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * 设置 (预约柜员)
     * 
     * @param agencyClientName (预约柜员)
     */
    public void setAgencyClientName(String agencyClientName) {
        this.agencyClientName = agencyClientName;
    }

    /**
     * 设置 (复核柜员)
     * 
     * @param apprTellerName (复核柜员)
     */
    public void setApprTellerName(String apprTellerName) {
        this.apprTellerName = apprTellerName;
    }

    /**
     * 设置 (授权柜员1)
     * 
     * @param ownerName (授权柜员1)
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * 获取 中文简称
     * 
     * @return 中文简称.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 中文简称
     * 
     * @param prodName 中文简称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}
