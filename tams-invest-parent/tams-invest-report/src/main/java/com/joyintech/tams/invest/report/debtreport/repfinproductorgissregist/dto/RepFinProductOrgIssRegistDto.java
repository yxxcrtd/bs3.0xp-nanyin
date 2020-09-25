/*
 * 文件名：RepFinProductOrgIssRegistDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductorgissregist.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月21日
 * @see RepFinProductOrgIssRegistDto
 * @since 1.0
 */
public class RepFinProductOrgIssRegistDto extends RepProdDeclare{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 产品起始日期
     */
    private Date repPrdVdate;

    /**
     * 产品终止日期
     */
    private Date repPrdMdate;
    /**
     * 收益率分档情况说明
     */
    private String repYieldComment;

    /**
     * 开放模式
     */
    private String repOpenMode;

    /**
     * 规律开放周期
     */
    private String repOpenCircle;

    /**
     * 其他规律开放周期（天）
     */
    private Short repOpenCircleDays;

    /**
     * 无规律开放说明
     */
    private String repRuleComment;

    /**
     * 首次开放周期起始日
     */
    private Date repFirstOpenDay;

    /**
     * 节假日是否开放
     */
    private String repHolidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAvgOpenCount;

    /**
     * 开放期业务
     */
    private String repOpenBuss;

    /**
     * 开放期业务说明
     */
    private String repOpenBussRemark;

    /**
     * 是否为结构化（分级）产品
     */
    private String isItStructured;
    /**
     * 层级类别
     */
    private String hierarchicalCategory;
    
    public Date getRepPrdVdate() {
        return repPrdVdate;
    }

    
    public void setRepPrdVdate(Date repPrdVdate) {
        this.repPrdVdate = repPrdVdate;
    }

    
    public Date getRepPrdMdate() {
        return repPrdMdate;
    }

    
    public void setRepPrdMdate(Date repPrdMdate) {
        this.repPrdMdate = repPrdMdate;
    }

    
    public String getRepYieldComment() {
        return repYieldComment;
    }

    
    public void setRepYieldComment(String repYieldComment) {
        this.repYieldComment = repYieldComment;
    }

    
    public String getRepOpenMode() {
        return repOpenMode;
    }

    
    public void setRepOpenMode(String repOpenMode) {
        this.repOpenMode = repOpenMode;
    }

    
    public String getRepOpenCircle() {
        return repOpenCircle;
    }

    
    public void setRepOpenCircle(String repOpenCircle) {
        this.repOpenCircle = repOpenCircle;
    }

    
    public Short getRepOpenCircleDays() {
        return repOpenCircleDays;
    }

    
    public void setRepOpenCircleDays(Short repOpenCircleDays) {
        this.repOpenCircleDays = repOpenCircleDays;
    }

    
    public String getRepRuleComment() {
        return repRuleComment;
    }

    
    public void setRepRuleComment(String repRuleComment) {
        this.repRuleComment = repRuleComment;
    }

    
    public Date getRepFirstOpenDay() {
        return repFirstOpenDay;
    }

    
    public void setRepFirstOpenDay(Date repFirstOpenDay) {
        this.repFirstOpenDay = repFirstOpenDay;
    }

    
    public String getRepHolidayOnFlag() {
        return repHolidayOnFlag;
    }

    
    public void setRepHolidayOnFlag(String repHolidayOnFlag) {
        this.repHolidayOnFlag = repHolidayOnFlag;
    }

    
    public BigDecimal getRepAvgOpenCount() {
        return repAvgOpenCount;
    }

    
    public void setRepAvgOpenCount(BigDecimal repAvgOpenCount) {
        this.repAvgOpenCount = repAvgOpenCount;
    }

    
    public String getRepOpenBuss() {
        return repOpenBuss;
    }

    
    public void setRepOpenBuss(String repOpenBuss) {
        this.repOpenBuss = repOpenBuss;
    }

    
    public String getRepOpenBussRemark() {
        return repOpenBussRemark;
    }

    
    public void setRepOpenBussRemark(String repOpenBussRemark) {
        this.repOpenBussRemark = repOpenBussRemark;
    }
    
    public String getIsItStructured() {
        return isItStructured;
    }


    
    public void setIsItStructured(String isItStructured) {
        this.isItStructured = isItStructured;
    }


    
    public String getHierarchicalCategory() {
        return hierarchicalCategory;
    }


    
    public void setHierarchicalCategory(String hierarchicalCategory) {
        this.hierarchicalCategory = hierarchicalCategory;
    }


    public String getRepInvestorTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, super.getRepInvestorType());
    }
    public String getRepInvestAreaStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.资金投向地区, super.getRepInvestArea());
    }
    public String getRepTermStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品期限, super.getRepTerm());
    }
    public String getRepFinanSerModelStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.理财业务服务模式, super.getRepFinanSerModel());
    }
    public String getRepOperateModeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, super.getRepOperateMode());
    }
    public String getRepBookModeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.会计核算方式, super.getRepBookMode());
    }
    public String getRepCmmodeStr(){
        if(StringUtil.equals(super.getRepCmmode(), DictCodeListExt1.产品资产配置方式_单一资产配置.getValue())){
            return super.getRepCmmode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品资产配置方式, super.getRepCmmode())+"(一对一)";
        }
        if(StringUtil.equals(super.getRepCmmode(), DictCodeListExt1.产品资产配置方式_组合配置.getValue())){
            return super.getRepCmmode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品资产配置方式, super.getRepCmmode())+"(一对多)";
        }
        if(StringUtil.equals(super.getRepCmmode(), DictCodeListExt1.产品资产配置方式_其他.getValue())){
            return super.getRepCmmode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品资产配置方式, super.getRepCmmode())+"(多对多)";
        }
        return SysDataUtil.getNameFromDataDict(DictCode.产品资产配置方式, super.getRepCmmode());
    }
    public String getRepPmmodeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品管理模式, super.getRepPmmode());
    }
    public String getRepPricedModeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品定价方式, super.getRepPricedMode());
    }
    public String getRepInvAssetTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.资金实际投向, super.getRepInvAssetType());
    }
    public String getRepIsYieldStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepIsYield());
    }
    public String getRepIsYieldBasisStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepIsYieldBasis());
    }
    public String getRepRaiseCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getRepRaiseCcy());
    }
    public String getRepCashPrinCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getRepCashPrinCcy());
    }
    public String getRepCashProfCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getRepCashProfCcy());
    }
    public String getRepRiskLevelStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品风险等级, super.getRepRiskLevel());
    }
    public String getRepTermRightFlagStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepTermRightFlag());
    }
    public String getRepCusRedeemFlagStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepCusRedeemFlag());
    }
    public String getRepContModeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.合作模式, super.getRepContMode());
    }
    public String getRepCreditFlagStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, super.getRepCreditFlag());
    }
    public String getRepCreditTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品增信机构类型, super.getRepCreditType());
    }
    public String getRepCreditModeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.产品增信形式, super.getRepCreditMode());
    }
    public String getRepInvestPrinDateStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日, super.getRepInvestPrinDate());
    }
    public String getRepInvestProfDateStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日, super.getRepInvestProfDate());
    }
    public String getRepOpenBussStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.开放期业务, repOpenBuss);
    }
    public String getRepOpenModeStr(){
        if(this.getRepOpenMode() != null 
            && !StringUtil.equals(this.getRepOpenMode(), "")
            && (StringUtil.equals(this.getRepOpenMode(), DictCodeList.开放模式_高频开放.getValue())
                || StringUtil.equals(this.getRepOpenMode(), DictCodeList.开放模式_定期开放.getValue()))){
            return "01 有规律开放";
        }
        return SysDataUtil.getNameFromDataDict(DictCode.开放模式, repOpenMode);
    }
    public String getRepOpenCircleStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.规律开放周期, repOpenCircle);
    }
    public String getIsItStructuredStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否, isItStructured);
    }
    public String getRepStatusDateStr(){
        return DateUtil.format(super.getRepStatusDate());
    }
    public String getRepRaisePstrdateStr(){
        return DateUtil.format(super.getRepRaisePstrdate());
    }
    public String getRepRaisePenddateStr(){
        return DateUtil.format(super.getRepRaisePenddate());
    }
    public String getRepPrdVdateStr(){
        return DateUtil.format(repPrdVdate);
    }
    public String getRepPrdMdateStr(){
        return DateUtil.format(repPrdMdate);
    }
    public String getRepFirstOpenDayStr(){
        return DateUtil.format(repFirstOpenDay);
    }
    public String getRepProfitTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, super.getRepProfitType());
    }
    public String getRepRiskTypeStr(){
        String repRistType = super.getRepRiskType();
        List<String> list = StringUtil.splitToList(",",repRistType);
        List<String> result = new ArrayList<>();
        for (String type: list){
            result.add(SysDataUtil.getNameFromDataDict(DictCode.投资风险偏好, type));
        }
        return StringUtil.splitToList("]",StringUtil.splitToList("[",result.toString()).get(1)).get(0);
    }
}
