/*
 * 文件名：PrdIssueScheduleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.scheduling.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.report.prod.dto.BussRepFinProductInfoDto;
import com.joyintech.tams.invest.product.productelement.scheduling.validator.FinProductScheduling002Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 理财产品排期管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月5日
 * @see PrdIssueScheduleDto
 * @since 1.0
 */
@PageId("FinProductScheduling002")
@ValidLogic(targetClass = FinProductScheduling002Validator.class)
public class PrdIssueScheduleDto extends PaPrdIssueSchedule implements ProcessEntity {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5546351764295855260L;

    /**
     * 一万
     */
    public static final BigDecimal TENTOU = new BigDecimal(10000);

    /**
     * 一百
     */
    public static final BigDecimal HUNDREND = new BigDecimal(100);

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.issueMode")
    public String getIssueMode() {
        return super.getIssueMode();
    }

    @Override
    public String getFinprodId() {
        return super.getFinprodId();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.prodId")
    @Length(fieldName = "FinProductScheduling002.prodId", max = 11)
    @PrimaryKey(Field.PK_2)
    public String getFinprodMarketId() {
        return super.getFinprodMarketId();
    }
    
    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.prodName")
    @Length(fieldName = "FinProductScheduling002.prodName", max = 60)
    public String getFinprodName() {
        return super.getFinprodName();
    }
    
    @PrimaryKey(Field.PK_3)
    public String getFinprodNameMd5() {
        return EncryptUtil.getInstance().md5(super.getFinprodName());
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.prodAbbr")
    @Length(fieldName = "FinProductScheduling002.prodAbbr", max = 60)
    public String getFinprodAbbr() {
        return super.getFinprodAbbr();
    }
    
    @PrimaryKey(Field.PK_4)
    public String getFinprodAbbrMd5() {
        return EncryptUtil.getInstance().md5(super.getFinprodAbbr());
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.vdate")
    public Date getVdate() {
        return super.getVdate();
    }
    
    public String getVdateStr() {
        return DateUtil.format(super.getVdate());
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.mdate")
    public Date getMdate() {
        return super.getMdate();
    }
    
    public String getMdateStr() {
        return DateUtil.format(super.getMdate());
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.raiseStrDate")
    public Date getRaiseStrdate() {
        return super.getRaiseStrdate();
    }
    
    public String getRaiseStrdateStr() {
        return DateUtil.format(super.getRaiseStrdate());
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.raiseEndDate")
    public Date getRaiseEnddate() {
        return super.getRaiseEnddate();
    }
    
    public String getRaiseEnddateStr() {
        return DateUtil.format(super.getRaiseEnddate());
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.subStartAmtBot")
    @Amount(fieldName = "FinProductScheduling002.subStartAmtBot", maxIntBit=24, maxDecBit=6)
    public BigDecimal getToffStartLow() {
        return super.getToffStartLow();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.subStartAmtMid")
    @Amount(fieldName = "FinProductScheduling002.subStartAmtMid", maxIntBit=24, maxDecBit=6)
    public BigDecimal getToffStartMid() {
        return super.getToffStartMid();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.subStartAmtHei")
    @Amount(fieldName = "FinProductScheduling002.subStartAmtHei", maxIntBit=24, maxDecBit=6)
    public BigDecimal getToffStartHig() {
        return super.getToffStartHig();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.customRateBot")
    @Rate(fieldName = "FinProductScheduling002.customRateBot")
    public BigDecimal getYieldLow() {
        return super.getYieldLow();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.customRateMid")
    @Rate(fieldName = "FinProductScheduling002.customRateMid")
    public BigDecimal getYieldMid() {
        return super.getYieldMid();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.customRateHei")
    @Rate(fieldName = "FinProductScheduling002.customRateHei")
    public BigDecimal getYieldHig() {
        return super.getYieldHig();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.addAmt")
    @Amount(fieldName = "FinProductScheduling002.addAmt", maxIntBit=28)
    public BigDecimal getLowestAmt() {
        return super.getLowestAmt();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.raiseTopLimit")
    @Amount(fieldName = "FinProductScheduling002.raiseTopLimit", maxIntBit=28)
    public BigDecimal getRaiseMax() {
        return super.getRaiseMax();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.saleFeeRate")
    @Rate(fieldName = "FinProductScheduling002.saleFeeRate")
    public BigDecimal getSaleFeeRate() {
        return super.getSaleFeeRate();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.trustFeeRate")
    @Rate(fieldName = "FinProductScheduling002.trustFeeRate")
    public BigDecimal getTrustFeeRate() {
        return super.getTrustFeeRate();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.payIntCycle")
    public String getIntpayCyc() {
        return super.getIntpayCyc();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.ccy")
    public String getCcy() {
        return super.getCcy();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.termType")
    public String getTermType() {
        return super.getTermType();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.branch")
    @Length(fieldName = "FinProductScheduling002.branch", max = 2000)
    public String getBranchBank() {
        return super.getBranchBank();
    }

    @Override
    // @NotEmpty(fieldName = "FinProductScheduling002.ruleOpenCycle")
    public String getOpenCycle() {
        return super.getOpenCycle();
    }

    @Override
    // @NotEmpty(fieldName = "FinProductScheduling002.otherRuleOpenExplain")
    public Long getOpenCycleOth() {
        return super.getOpenCycleOth();
    }

    @Override
    // @NotEmpty(fieldName = "FinProductScheduling002.noRuleOpenExplain")
    @Length(fieldName = "FinProductScheduling002.noRuleOpenExplain", max = 300)
    public String getOpenRemark() {
        return super.getOpenRemark();
    }

    @Override
    // @NotEmpty(fieldName = "FinProductScheduling002.fristOpenCycleStrDate")
    public Date getFirstOpenDay() {
        return super.getFirstOpenDay();
    }

    /*
     * @Override
     * @NotEmpty(fieldName = "FinProductScheduling002.holidayIsOpen") public String
     * getHdayOpenFlag() { return super.getHdayOpenFlag(); }
     */
    @Override
    // @NotEmpty(fieldName = "FinProductScheduling002.avgOpenCount")
    public Long getAveOpenTime() {
        return super.getAveOpenTime();
    }

    @Override
    // @NotEmpty(fieldName = "FinProductScheduling002.openBus")
    public String getOpenBuss() {
        return super.getOpenBuss();
    }

    @Override
    // @NotEmpty(fieldName = "FinProductScheduling002.openBusExplain")
    @Length(fieldName = "FinProductScheduling002.openBusExplain", max = 300)
    public String getOpenBussRemark() {
        return super.getOpenBussRemark();
    }

    @Override
    @NotEmpty(fieldName = "FinProductScheduling002.declareFilePlan")
    public String getSchemeId() {
        return super.getSchemeId();
    }

    @Override
    @Length(fieldName = "FinProductScheduling002.remark", max = 300)
    public String getRemark() {
        return super.getRemark();
    }

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 元素Id
     */
    private String elementId;

    /**
     * 对象list
     */
    private transient List<PrdIssueScheduleDto> selectedItem;

    /**
     * {@linkplain}
     */
    @Valid
    private BussRepFinProductInfoDto bussRepFinProductInfoDto;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<PrdIssueScheduleDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<PrdIssueScheduleDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public BussRepFinProductInfoDto getBussRepFinProductInfoDto() {
        return bussRepFinProductInfoDto;
    }

    public void setBussRepFinProductInfoDto(BussRepFinProductInfoDto bussRepFinProductInfoDto) {
        this.bussRepFinProductInfoDto = bussRepFinProductInfoDto;
    }

}
