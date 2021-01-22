/*
 * 文件名：EtrPlanAssetCDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrplanassetc.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrplanassetc.validator.EtrPlanAssetCValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月3日
 * @see EtrPlanAssetCDto
 * @since 1.0
 */
@PageId("EtrPlanAssetC002")
@ValidLogic(targetClass = EtrPlanAssetCValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrPlanAssetCDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 9222570679406156110L;
    //新增指令多对多导入 张普裕 2019/1/9 start
    private BigDecimal entrustNumShow;

    private BigDecimal entrustAmtShow;
    public BigDecimal getEntrustAmtShow() {
        return entrustAmtShow;
    }

    public void setEntrustAmtShow(BigDecimal entrustAmtShow) {
        this.entrustAmtShow = entrustAmtShow;
    }


    public BigDecimal getEntrustNumShow() {
        return entrustNumShow;
    }


    public void setEntrustNumShow(BigDecimal entrustNumShow) {
        this.entrustNumShow = entrustNumShow;
    }

    /**
     * 交易对手名称
     */
    private String counterIdName;

    public String getCounterIdName() {
        return counterIdName;
    }

    public void setCounterIdName(String counterIdName) {
        this.counterIdName = counterIdName;
    }

    public BigDecimal getEtrAvailableCash() {
        return etrAvailableCash;
    }

    public void setEtrAvailableCash(BigDecimal etrAvailableCash) {
        this.etrAvailableCash = etrAvailableCash;
    }
    public BigDecimal getEtrAvailablePosition() {
        return etrAvailablePosition;
    }

    public void setEtrAvailablePosition(BigDecimal etrAvailablePosition) {
        this.etrAvailablePosition = etrAvailablePosition;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * 指令可用现金
     */
    private BigDecimal etrAvailableCash;

    /**
     * 指令可用持仓
     */
    private BigDecimal etrAvailablePosition;

    /**
     * 序号
     */
    private String indexId;
   //新增指令多对多导入 张普裕 2019/1/9 end
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 买入卖出份额
     */
    private BigDecimal entrustNum2;
    /**
     * 买入卖出金额
     */
    private BigDecimal entrustAmt2;
    /**
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 人员信息业务组件DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 选中的数据
     */
    private transient List<EtrPlanAssetCDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 资产一级分类
     */
    private String assetType1;
    /*添加三-四级分类 lishuping 2019/7/29 start*/
    /**
     * 资产三级分类
     */
    private String assetType10;
    /**
     * 资产四级分类
     */
    private String assetType11;
    /**
     * 获取 assetType10
     * @return assetType10.
     */
    @NotEmpty(fieldName = "EtrPlanAssetC001.type10",groups={ValidateGroupEdit.class})
    public String getAssetType10() {
        return assetType10;
    }

    /**
     * 设置 assetType10
     * @param assetType10 assetType10
     */
    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }
    /**
     * 获取 assetType11
     * @return assetType11.
     */
    public String getAssetType11() {
        return assetType11;
    }
    /**
     * 设置 assetType11
     * @param assetType11 assetType11
     */
    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }

    /**
     * 将数据字典代码转换为数据字典值
     * @return
     */
    public String getBusAssetType10(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getAssetType10());
    }
    /**
     * 将数据字典代码转换为数据字典值
     * @return
     */
    public String getBusAssetType11(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getAssetType11());
    }
    /*添加三-四级分类 lishuping 2019/7/29 end*/

    /**
     * 金融产品全称
     */
    private String finprodName;
    /**
     * 完成额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finishAmt;
    
    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal transactioncom;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;


    /**
     * 交易对手类型
     */
    private String counterType;

    /**
     * 获取 counterType
     *
     * @return counterType
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 counterType
     *
     * @param counterType counterType
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }
    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }


    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }


    /**
     * 获取 entrustNum2
     * @return entrustNum2.
     */
    public BigDecimal getEntrustNum2() {
        return entrustNum2;
    }

    
    /**
     * 设置 entrustNum2
     * @param entrustNum2 entrustNum2
     */
    public void setEntrustNum2(BigDecimal entrustNum2) {
        this.entrustNum2 = entrustNum2;
    }

    
    /**
     * 获取 entrustAmt2
     * @return entrustAmt2.
     */
    public BigDecimal getEntrustAmt2() {
        return entrustAmt2;
    }

    
    /**
     * 设置 entrustAmt2
     * @param entrustAmt2 entrustAmt2
     */
    public void setEntrustAmt2(BigDecimal entrustAmt2) {
        this.entrustAmt2 = entrustAmt2;
    }

    /**
     * 获取 finvdate
     * @return finvdate.
     */
    public Date getFinvdate() {
        return finvdate;
    }
    
    /**
     * 设置 finvdate
     * @param finvdate finvdate
     */
    public void setFinvdate(Date finvdate) {
        this.finvdate = finvdate;
    }
    
    /**
     * 获取 finmdate
     * @return finmdate.
     */
    public Date getFinmdate() {
        return finmdate;
    }
    
    /**
     * 设置 finmdate
     * @param finmdate finmdate
     */
    public void setFinmdate(Date finmdate) {
        this.finmdate = finmdate;
    }
    
    /**
     * 获取 assetAvailablePosition
     * @return assetAvailablePosition.
     */
    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }
    
    /**
     * 设置 assetAvailablePosition
     * @param assetAvailablePosition assetAvailablePosition
     */
    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
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
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "EtrPlanAssetC002.finprodId", groups = {ValidateGroupEdit.class})
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrPlanAssetCDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrPlanAssetCDto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 assetType1
     * @return assetType1.
     */
    @NotEmpty(fieldName = "EtrPlanAssetC001.type9",groups={ValidateGroupEdit.class})
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
    
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId(){
        return super.getEntrustId();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrPlanAssetC002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrPlanAssetC002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrPlanAssetC002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrPlanAssetC002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrPlanAssetC002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
//    @Override
//    @NotEmpty(fieldName = "EtrPlanAssetC002.entrustAmt", groups = {ValidateGroupEdit.class})
//    public BigDecimal getEntrustAmt(){
//        return super.getEntrustAmt();
//    }
    
    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    
    public String getBusPs(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    /*更改一级分类为资产二级分类 lishuping 2019/7/29 start*/
    public String getBusAssetType1(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getAssetType1());
    }
    /*更改一级分类为资产二级分类 lishuping 2019/7/29 end*/
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    
    public BigDecimal getBusEntrustAmt(){
        return this.getEntrustAmt()==null?super.getEntrustNum():getEntrustAmt();
    }
}
