/*
 * 文件名：TrdAssetLoss001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trdassetlossmanage.trdassetloss.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 资产减值管理dto
 * 〈功能详细描述〉
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLoss001Dto
 * @since 1.0
 */
public class TrdAssetLoss001Dto extends BaseEntity implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3524582114312733039L;
    /**
     * 操作类型
     */
    private String operateType;
    /** 要素id **/
    private String elementId;
    /** 交易代码 **/
    private String tradeId;
    /** 行情日 **/
    private Date valDate;
    /** 失效日 **/
    private Date invalidDate;
    /** 金融产品代码 **/
    private String finprodId;
    /** 金融产品简称 **/
    private String finprodAbbr;
    /** 减值测算方式 **/
    private String lossTestType;
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
    /** 减值录入方式 **/
    private String lossInputPattern;

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

    /**
     * 资产行权日
     */
    private Date assetExerciseDate;
    /**
     * 初始确认起始日
     */
    private Date strDate;
    /**
     * 初始确认截止日
     */
    private Date endDate;
    /**
     * 待偿期（年）
     */
    private BigDecimal peroid;
    /**
     * 减值阶段
     */
    private String stage;
    /**
     * ECL预期信用损失比例%
     */
    private BigDecimal eclVaule;
    /**
     * 含权摊销日
     */
    private String exeDate;

    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

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
        return overDays;
    }

    public void setOverDays(Long overDays) {
        this.overDays = overDays;
    }

    public BigDecimal getResidualMaturity() {
        return residualMaturity;
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
        return vDateStr;
    }

    public void setvDateStr(String vDateStr) {
        this.vDateStr = vDateStr;
    }

    public String getmDateStr() {
        return mDateStr;
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

    public String getExeDate() {
        return exeDate;
    }

    public void setExeDate(String exeDate) {
        this.exeDate = exeDate;
    }
}
