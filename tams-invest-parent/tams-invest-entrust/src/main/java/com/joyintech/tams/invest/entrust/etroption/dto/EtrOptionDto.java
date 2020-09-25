/*
 * 文件名：EtrOptionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：likai
 * 修改时间：2019年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etroption.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.invest.entrust.etroption.validator.EtrOptionValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
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
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.validator.EtrLendSpecInfoValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 期权委托dto
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月13日
 * @see
 * @since 1.0
 */
@PageId("EtrOption002")
@ValidLogic(targetClass = EtrOptionValidator.class , groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class EtrOptionDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3314619126979170188L;

    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 投资委托人员信息dto
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;

    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 接收状态
     */
    private String recStatus;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产一级分类，委托页面的债券一级分类
     */
    private String assetType1;
    /**
     * 期限(天)
     */
    private Long termDays;
    /**
     * 合约标的
     */
    private String assetType10;
    private String assetType10Str;
    /**
     * 合约类型
     */
    private String assetType11;
    /**
     * 行权方式
     */
    private String  exerciseType;
    /**
     * 行权日基准日
     */
    private Date execBaseDate;
    /**
     * 期权费类型
     */
    private String optFeePayType;
    /**
     * 期权费率
     */
    private String optFeePayType2;
    /**
     * 执行价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exePrice;
    /**
     * 完成额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finishAmt;
    /**
     * 敲出价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal knockPrice;
    /**
     * 低障碍价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowHindPrice;
    /**
     * 低行权价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowExercPrice;
    @NotEmpty(fieldName = "EtrOption002.contractSubMatter", groups = {ValidateGroupEdit.class})
    public String getAssetType10() {
        return assetType10;
    }

    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }
    @NotEmpty(fieldName = "EtrOption002.contractType", groups = {ValidateGroupEdit.class})
    public String getAssetType11() {
        return assetType11;
    }

    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }
    @NotEmpty(fieldName = "EtrOption002.exerciseType", groups = {ValidateGroupEdit.class})
    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    @NotEmpty(fieldName = "EtrOption002.execBaseDate", groups = {ValidateGroupEdit.class})
    public Date getExecBaseDate() {
        return execBaseDate;
    }

    public void setExecBaseDate(Date execBaseDate) {
        this.execBaseDate = execBaseDate;
    }

    public String getOptFeePayType() {
        return optFeePayType;
    }

    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType;
    }

    public String getOptFeePayType2() {
        return optFeePayType2;
    }

    public void setOptFeePayType2(String optFeePayType2) {
        this.optFeePayType2 = optFeePayType2;
    }

    public BigDecimal getExePrice() {
        return exePrice;
    }

    public void setExePrice(BigDecimal exePrice) {
        this.exePrice = exePrice;
    }

    public BigDecimal getKnockPrice() {
        return knockPrice;
    }

    public void setKnockPrice(BigDecimal knockPrice) {
        this.knockPrice = knockPrice;
    }

    public BigDecimal getLowHindPrice() {
        return lowHindPrice;
    }

    public void setLowHindPrice(BigDecimal lowHindPrice) {
        this.lowHindPrice = lowHindPrice;
    }

    public BigDecimal getLowExercPrice() {
        return lowExercPrice;
    }

    public void setLowExercPrice(BigDecimal lowExercPrice) {
        this.lowExercPrice = lowExercPrice;
    }

    public BigDecimal getHighHindPrice() {
        return highHindPrice;
    }

    public void setHighHindPrice(BigDecimal highHindPrice) {
        this.highHindPrice = highHindPrice;
    }

    public BigDecimal getHighExercPrice() {
        return highExercPrice;
    }

    public void setHighExercPrice(BigDecimal highExercPrice) {
        this.highExercPrice = highExercPrice;
    }

    /**
     * 高障碍价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highHindPrice;
    /**
     * 高行权价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highExercPrice;

    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal transactioncom;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 表格所选数据
     */
    private List<EtrOptionDto> selectedItem;

    /**
     * 委托关闭方式
     */
    private String CloseWay;
    /**
     * 起息日
     */
    private Date vdateAdd;
    /**
     * 到期日
     */
    private Date mdateAdd;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;



    /**
     * 获取 riskTrialRetDto
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }


    /**
     * 设置 riskTrialRetDto
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 termDays
     * @return termDays.
     */
    @NotEmpty(fieldName = "EtrOption002.term", groups = {ValidateGroupEdit.class})
    public Long getTermDays() {
        return termDays;
    }
    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(Long termDays) {
        this.termDays = termDays;
    }

    public String getTermDaysStr(){
        return StringUtil.numberToString(termDays);
    }

    /**
     * 获取 vdateAdd
     * @return vdateAdd.
     */
    public Date getVdateAdd() {
        return vdateAdd;
    }


    /**
     * 设置 vdateAdd
     * @param vdateAdd vdateAdd
     */
    public void setVdateAdd(Date vdateAdd) {
        this.vdateAdd = vdateAdd;
    }


    /**
     * 获取 mdateAdd
     * @return mdateAdd.
     */
    public Date getMdateAdd() {
        return mdateAdd;
    }



    /**
     * 设置 mdateAdd
     * @param mdateAdd mdateAdd
     */
    public void setMdateAdd(Date mdateAdd) {
        this.mdateAdd = mdateAdd;
    }



    /**
     * 获取 assetType1
     * @return assetType1.
     */
    public String getAssetType1() {
        return assetType1;
    }
    /**
     * 设置 assetType1
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }


    /**
     * 获取 recStatus
     * @return recStatus.
     */
    public String getRecStatus() {
        return recStatus;
    }



    /**
     * 设置 recStatus
     * @param recStatus recStatus
     */
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }


    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * 获取 etrInvestPersonInfo
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }
    /**
     * 设置 etrInvestPersonInfo
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }




    /**
     * 获取 closeWay
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }




    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }



    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }



    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }



    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrOptionDto> getSelectedItem() {
        return selectedItem;
    }



    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrOptionDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }


    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }


    /**
     * 获取 finishAmt
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }


    /**
     * 设置 finishAmt
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }


    /**
     * 获取 transactioncom
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }


    /**
     * 设置 transactioncom
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getVdateStr(){
        return DateUtil.format(super.getVdate());
    }
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getMdateStr(){
        return DateUtil.format(super.getMdate());
    }
    /**
     *
     * 交易方向数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */

    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    /**
     *
     * 合约标的数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */

    public String getAssetType11Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.结构类型, this.getAssetType11());
    }
    /**
     *
     * 币种数据字典装换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     *
     * 委托状态数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getEntrustStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    /**
     * 取得 委托代码
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId() {
        return super.getEntrustId();
    }
    /**
     * 组合代码判空
     *
     */
    @Override
    @NotEmpty(fieldName = "EtrOption002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }
    /**
     *
     * 委托开始日期判空
     */
    @Override
   @NotEmpty(fieldName = "EtrOption002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    /**
     *
     * 委托终止日期判空
     */
    @Override
    @NotEmpty(fieldName = "EtrOption002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    /**
     *
     * 币种判空
     */
    @Override
  @NotEmpty(fieldName = "EtrOption002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }

    public String getAssetType10Str() {
        return assetType10Str;
    }

    public void setAssetType10Str(String assetType10Str) {
        this.assetType10Str = assetType10Str;
    }
    public String getExerciseTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.行权方式, this.getExerciseType());
    }

    /**
     *
     * 交易方向判空
     */
    @Override
    @NotEmpty(fieldName = "EtrOption002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
//  /**
//  *
//  * 交易场所判空
//  */
// @NotEmpty(fieldName = "EtrOption002.tradeMarket", groups = {ValidateGroupEdit.class})
// public String getTradeMarket() {
//     return super.getTradeMarket();
// }

    /**
     *
     * 数量判空
     */
    @NotEmpty(fieldName = "EtrOption002.entrustNum", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustNum() {
        return super.getEntrustNum();
    }
}
