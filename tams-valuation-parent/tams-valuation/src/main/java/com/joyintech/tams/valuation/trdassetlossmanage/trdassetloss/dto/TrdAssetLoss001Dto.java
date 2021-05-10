/*
 * 文件名：TrdAssetLoss001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 资产减值管理dto
 * 〈功能详细描述〉
 * @author lby
 * @version 1.0 2020年10月19日
 * @see TrdAssetLoss001Dto
 * @since 1.0
 */
/*@PageId("TrdAssetLoss001")
@ValidLogic(targetClass = EtrInvestBondInfoValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})*/
public class TrdAssetLoss001Dto extends BaseEntity implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2487999892984574505L;
    /**
     * 操作类型
     */
    private String operateType;
    /** 要素id **/
    private String elementId;
    /** 交易代码 **/
    @PrimaryKey(value = PrimaryKey.Field.PK_2)
    private String tradeId;
    /** 减值行情日 **/
    private Date valDate;
    private String valDateStr;
    /** 失效日 **/
    private Date invalidDate;
    /** 金融产品代码 **/
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String finprodId;
    /** 金融产品简称 **/
    private String finprodAbbr;
    /** 减值测算方式 **/
    private String lossTestType;
    private String lossTestTypeStr;

    /** 资产起息日 **/
    private Date vDate;
    private String vDateStr;

    /** 资产到期日 **/
    private Date mDate;
    private String mDateStr;

    /** 减值比例% **/
    private BigDecimal lossRatio;
    /** PD违约概率% **/
    private BigDecimal pdValue;
    /** LGD违约损失率% **/
    private BigDecimal lgdValue;
    /** 逾期天数 **/
    private Long overDays;
    /** 剩余期限 **/
    private BigDecimal residualMaturity;

    /** 资产类型 **/
    private String assetType1;
    private String assetType1Str;

    /** 减值录入方式 **/
    private String lossInputPattern;
    private String lossInputPatternStr;

    /** 账套代码 **/
    private String bookSetId;
    /** 账套名称 **/
    private String bookSetName;
    /** 投资目的 **/
    private String invAim;
    /** 账面价值 **/
    private BigDecimal bookValue;
    /** 应收利息 **/
    private BigDecimal interestReceivable;
    /** EAD违约风险暴露 **/
    private BigDecimal eadDefaultExposure;
    /** 本次减值损失 **/
    private BigDecimal thisLossValue;
    /** 业务明细代码 **/
    private String busiId;
    /** 入账日期 **/
    private Date bookSetDate;
    /**
     * 选中的数据
     */
    private transient List<TrdAssetLoss001Dto> selectedItem;
    /**
     * 序号
     */
    private String indexId;
    /** 检查结果 **/
    private String checkStatusStr;

    private String statusStr;

    /**
     * 资产行权日
     */
    private Date assetExerciseDate;
    private String assetExerciseDateStr;
    /**
     * 初始确认起始日
     */
    private Date strDate;
    private String strDateStr;
    /**
     * 初始确认截止日
     */
    private Date endDate;
    private String endDateStr;
    /**
     * 待偿期（年）
     */
    private BigDecimal peroid;
    /**
     * 减值阶段
     */
    private String stage;
    private String stageStr;
    /**
     * ECL预期信用损失比例%
     */
    private BigDecimal eclVaule;
    /**
     * 含权摊销日
     */
    private String exeDate;
    /**
     * 备注
     */
    private StringBuffer remark;
    /**
     * 撤销原因
     */
    private String revokeReason;
    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getLossTestType() {
        return lossTestType;
    }

    public void setLossTestType(String lossTestType) {
        this.lossTestType = lossTestType;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public BigDecimal getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(BigDecimal lossRatio) {
        this.lossRatio = lossRatio;
    }

    public BigDecimal getPdValue() {
        return pdValue;
    }

    public void setPdValue(BigDecimal pdValue) {
        this.pdValue = pdValue;
    }

    public BigDecimal getLgdValue() {
        return lgdValue;
    }

    public void setLgdValue(BigDecimal lgdValue) {
        this.lgdValue = lgdValue;
    }

    public Long getOverDays() {
        if (StringUtil.isNotEmpty(this.getmDate()) && StringUtil.isNotEmpty(this.getValDate()) && (DateUtil.compareDate(this.getValDate(), this.getmDate()) > 0)) {
            //DateUtil.compareDate(this.减值行情日,this.资产到期日)>0?:DateUtil.getBetweenDayNumber(this.减值行情日,this.资产到期日):0;
            long betweenDays = DateUtil.getBetweenDayNumber(this.getmDate(), this.getValDate());
            return Long.valueOf(betweenDays);
        }
        return 0L;
    }

    public void setOverDays(Long overDays) {
        this.overDays = overDays;
    }

    public BigDecimal getResidualMaturity() {
        if (StringUtil.isNotEmpty(this.getmDate()) && StringUtil.isNotEmpty(this.getValDate()) && (DateUtil.compareDate( this.getmDate(),this.getValDate()) > 0)){
            //DateUtil.compareDate(this.资产到期日,this.减值行情日)>0?DateUtil.getBetweenDayNumber(this.减值行情日,this.资产到期日)/365(取四位小数):0;
            //剩于期限 = 资产到期日 > 减值生效日?资产到期日 - 减值生效日/365(取四位小数):0;
            return BigDecimal.valueOf(DateUtil.getBetweenDayNumber(this.getmDate(),this.getValDate())).divide(new BigDecimal("365"),4, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public void setResidualMaturity(BigDecimal residualMaturity) {
        this.residualMaturity = residualMaturity;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getvDateStr() {
        return DateUtil.format(this.getvDate());
    }

    public void setvDateStr(String vDateStr) {
        this.vDateStr = vDateStr;
    }

    public String getmDateStr() {
        return DateUtil.format(this.getmDate());
    }

    public void setmDateStr(String mDateStr) {
        this.mDateStr = mDateStr;
    }

    public String getLossInputPattern() {
        return lossInputPattern;
    }

    public void setLossInputPattern(String lossInputPattern) {
        this.lossInputPattern = lossInputPattern;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getBookSetId() {
        return bookSetId;
    }

    public void setBookSetId(String bookSetId) {
        this.bookSetId = bookSetId;
    }

    public String getBookSetName() {
        return bookSetName;
    }

    public void setBookSetName(String bookSetName) {
        this.bookSetName = bookSetName;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public BigDecimal getBookValue() {
        return bookValue;
    }

    public void setBookValue(BigDecimal bookValue) {
        this.bookValue = bookValue;
    }

    public BigDecimal getInterestReceivable() {
        return interestReceivable;
    }

    public void setInterestReceivable(BigDecimal interestReceivable) {
        this.interestReceivable = interestReceivable;
    }

    public BigDecimal getEadDefaultExposure() {
        return eadDefaultExposure;
    }

    public void setEadDefaultExposure(BigDecimal eadDefaultExposure) {
        this.eadDefaultExposure = eadDefaultExposure;
    }

    public BigDecimal getThisLossValue() {
        return thisLossValue;
    }

    public void setThisLossValue(BigDecimal thisLossValue) {
        this.thisLossValue = thisLossValue;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public Date getBookSetDate() {
        return bookSetDate;
    }

    public void setBookSetDate(Date bookSetDate) {
        this.bookSetDate = bookSetDate;
    }

    public List<TrdAssetLoss001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<TrdAssetLoss001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getCheckStatusStr() {
        return checkStatusStr;
    }

    public void setCheckStatusStr(String checkStatusStr) {
        this.checkStatusStr = checkStatusStr;
    }

    public String getAssetType1Str() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.金融产品类型,this.getAssetType1());
    }

    public void setAssetType1Str(String assetType1Str) {
        this.assetType1Str = assetType1Str;
    }

    public String getLossTestTypeStr() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.减值测算方式,this.getLossTestType());
    }

    public void setLossTestTypeStr(String lossTestTypeStr) {
        this.lossTestTypeStr = lossTestTypeStr;
    }

    public String getLossInputPatternStr() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.减值录入方式,this.getLossInputPattern());
    }

    public void setLossInputPatternStr(String lossInputPatternStr) {
        this.lossInputPatternStr = lossInputPatternStr;
    }

    public String getValDateStr() {
        return DateUtil.format(this.getValDate());
    }

    public void setValDateStr(String valDateStr) {
        this.valDateStr = valDateStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Date getAssetExerciseDate() {
        return assetExerciseDate;
    }

    public void setAssetExerciseDate(Date assetExerciseDate) {
        this.assetExerciseDate = assetExerciseDate;
    }

    public Date getStrDate() {
        return strDate;
    }

    public void setStrDate(Date strDate) {
        this.strDate = strDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPeroid() {
        return peroid;
    }

    public void setPeroid(BigDecimal peroid) {
        this.peroid = peroid;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public BigDecimal getEclVaule() {
        return eclVaule;
    }

    public void setEclVaule(BigDecimal eclVaule) {
        this.eclVaule = eclVaule;
    }

    public String getStageStr() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.减值阶段,this.getStage());
    }

    public void setStageStr(String stageStr) {
        this.stageStr = stageStr;
    }

    public String getAssetExerciseDateStr() {
        return DateUtil.format(this.assetExerciseDate);
    }

    public void setAssetExerciseDateStr(String assetExerciseDateStr) {
        this.assetExerciseDateStr = assetExerciseDateStr;
    }

    public String getExeDate() {
        return exeDate;
    }

    public void setExeDate(String exeDate) {
        this.exeDate = exeDate;
    }

    public String getStrDateStr() {
        return DateUtil.format(this.getStrDate());
    }

    public void setStrDateStr(String strDateStr) {
        this.strDateStr = strDateStr;
    }

    public String getEndDateStr() {
        return DateUtil.format(this.endDate);
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public StringBuffer getRemark() {
        return remark;
    }

    public void setRemark(StringBuffer remark) {
        this.remark = remark;
    }

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }
}
