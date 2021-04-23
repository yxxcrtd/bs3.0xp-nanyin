/*
 * 文件名：EtrRepoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrrepo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrrepo.validator.EtrRepoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 回购投资委托画面dto
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月24日
 * @see EtrRepoDto
 * @since 1.0
 */
@PageId("EtrRepo002")
@ValidLogic(targetClass = EtrRepoValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrRepoDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3186205380954526141L;
    /**
     * 添加质押券信息
     */
    private BussRepoSecDto bussRepoSec;
    /**
     * 证券托管户
     */
    private String securitiesCust;
    /**
     * 资金可用日
     */
    private Date availableDay;
    /**
     * 到期日
     */
    private Date mdateAdd;
    /**
     * 起息日
     */
    private Date vdateAdd;
    /**
     * 委托开始日Text
     */
    private String vdateText;
    /**
     * 委托截止日Text
     */
    private String mdateText;
    /**
     * 清算速度
     */
    private String settleSpreed;
    /**
     * 清算速度（long）
     */
    private Long settleSpeed;
    /**
     * 利率
     */
    private String rate;
    /**
     * 回购期限
     */
    private BigDecimal repoTerm;
    /**
     * 期限品种
     */
    private String termType;
    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
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
    private List<EtrRepoDto> selectedItem;
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
     * 序号
     */
    private String indexId;

    /**
     * 交易日历名称
     */
    private String calendarIdName;

    /**
     * 证券托管户名称
     */
    private String securitiesCustName;

    /**
     * 通道名称
     */
    private String chlIdName;

    /**
     * 最低成交金额
     */
    private BigDecimal minAmt;

    /**
     * 可使用标准券额度,用于APP质押式回购指令显示
     */
    private  String assetAvailablePosition1;

    /**
     * 委托额度，用于APP质押式回购指令显示
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal APPentrustAmtOne;



    /**
     * 券面总额，用于APP买断式式回购指令显示
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal APPentrustAmtTWO;


    public String getAssetAvailablePosition1() {
        return assetAvailablePosition1;
    }

    public void setAssetAvailablePosition1(String assetAvailablePosition1) {
        this.assetAvailablePosition1 = assetAvailablePosition1;
    }


    /**
     * 取得 最低成交金额
     * 
     * @return 最低成交金额
     */
    public BigDecimal getMinAmt() {
        return minAmt;
    }

    /**
     * 设置 最低成交金额
     * 
     * @param minAmt 最低成交金额
     */
    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    /**
     * 获取 indexId
     * 
     * @return indexId.
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 indexId
     * 
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * 获取 settleSpreedName
     * 
     * @return settleSpreedName.
     */
    public String getSettleSpreedName() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpreed());
    }
    
    /**
     * 获取 tradeMarketName
     * 
     * @return tradeMarketName.
     */
    public String getTradeMarketName() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }

    /**
     * 获取 calendarIdName
     * 
     * @return calendarIdName.
     */
    public String getCalendarIdName() {
        return calendarIdName;
    }

    /**
     * 设置 calendarIdName
     * 
     * @param calendarIdName calendarIdName
     */
    public void setCalendarIdName(String calendarIdName) {
        this.calendarIdName = calendarIdName;
    }

    /**
     * 获取 securitiesCustName
     * 
     * @return securitiesCustName.
     */
    public String getSecuritiesCustName() {
        return securitiesCustName;
    }

    /**
     * 设置 securitiesCustName
     * 
     * @param securitiesCustName securitiesCustName
     */
    public void setSecuritiesCustName(String securitiesCustName) {
        this.securitiesCustName = securitiesCustName;
    }

    /**
     * 获取 chlIdName
     * 
     * @return chlIdName.
     */
    public String getChlIdName() {
        return chlIdName;
    }

    /**
     * 设置 chlIdName
     * 
     * @param chlIdName chlIdName
     */
    public void setChlIdName(String chlIdName) {
        this.chlIdName = chlIdName;
    }

    /**
     * 获取 bussRepoSec
     * @return bussRepoSec.
     */
    public BussRepoSecDto getBussRepoSec() {
        return bussRepoSec;
    }


    
    /**
     * 设置 bussRepoSec
     * @param bussRepoSec bussRepoSec
     */
    public void setBussRepoSec(BussRepoSecDto bussRepoSec) {
        this.bussRepoSec = bussRepoSec;
    }


    /**
     * 获取 settleSpeed
     * @return settleSpeed.
     */
    public Long getSettleSpeed() {
        return settleSpeed;
    }

    
    /**
     * 设置 settleSpeed
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(Long settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 securitiesCust
     * @return securitiesCust.
     */
    public String getSecuritiesCust() {
        return securitiesCust;
    }
    
    /**
     * 设置 securitiesCust
     * @param securitiesCust securitiesCust
     */
    public void setSecuritiesCust(String securitiesCust) {
        this.securitiesCust = securitiesCust;
    }
    
    /**
     * 获取 availableDay
     * @return availableDay.
     */
    public Date getAvailableDay() {
        return availableDay;
    }
    
    /**
     * 设置 availableDay
     * @param availableDay availableDay
     */
    public void setAvailableDay(Date availableDay) {
        this.availableDay = availableDay;
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
     * 获取 settleSpreed
     * @return settleSpreed.
     */
    @NotEmpty(fieldName = "EtrRepo002.settleSpreed", groups = {ValidateGroupEdit.class})
    public String getSettleSpreed() {
        return settleSpreed;
    }
    
    /**
     * 设置 settleSpreed
     * @param settleSpreed settleSpreed
     */
    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }
    
    /**
     * 获取 rate
     * @return rate.
     */
    public String getRate() {
        return rate;
    }
    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }
    
    /**
     * 获取 repoTerm
     * @return repoTerm.
     */
//    @NotEmpty(fieldName = "EtrRepo002.repoTerm", groups = {ValidateGroupEdit.class})
    public BigDecimal getRepoTerm() {
        // 点击修改时，这个check忽略，直接返回0
//        if (StringUtil.equals(this.getOperateType(), DictCodeListExt1.委托操作类型_修改.getValue())) {
//            return BigDecimal.ZERO;
//        }
        return repoTerm;
    }
    
    /**
     * 设置 repoTerm
     * @param repoTerm repoTerm
     */
    public void setRepoTerm(BigDecimal repoTerm) {
        this.repoTerm = repoTerm;
    }
    
    /**
     * 获取 termType
     * @return termType.
     */
    public String getTermType() {
        return termType;
    }
    
    /**
     * 设置 termType
     * @param termType termType
     */
    public void setTermType(String termType) {
        this.termType = termType;
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
    public List<EtrRepoDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrRepoDto> selectedItem) {
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
    @NotEmpty(fieldName = "EtrRepo002.assetType1", groups = {ValidateGroupEdit.class})
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
    
    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    
    public String getBusPs(){
        return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getPs());
    }
    
    public String getBusTermType(){
        return SysDataUtil.getNameFromDataDict(DictCode.回购期限品种, this.getTermType());
    }
    
    public String getBusAssetType1(){
        return SysDataUtil.getNameFromDataDict(DictCode.回购类型, this.getAssetType1());
    }
    
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    public String getPriceModelStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.价格模式, super.getPriceModel());
    }
    
    @Override
    @NotEmpty(fieldName = "EtrRepo002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }
    @Override
    @NotEmpty(fieldName = "EtrRepo002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    @Override
    @NotEmpty(fieldName = "EtrRepo002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    @Override
    @NotEmpty(fieldName = "EtrRepo002.entrustAmt", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "EtrRepo002.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt(){
        return super.getEntrustAmt();
    }
    @Override
    @NotEmpty(fieldName = "EtrRepo002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
    @Override
    @NotEmpty(fieldName = "EtrRepo002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    @Override
    @NotEmpty(fieldName = "EtrRepo002.tradeMarket", groups = {ValidateGroupEdit.class})
    public String getTradeMarket(){
        return super.getTradeMarket();
    }
    @Override
    //@NotEmpty(fieldName = "EtrRepo002.price", groups = {ValidateGroupEdit.class})
    @Rate(fieldName = "EtrRepo002.price", groups = {ValidateGroupEdit.class})
    public BigDecimal getPrice(){
        return super.getPrice();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrRepo002.calendarId", groups = {ValidateGroupEdit.class})
    public String getCalendarId(){
        return super.getCalendarId();
    }

    /**
     * 获取 vdateText
     *
     * @rturn vdateText
     */
    public String getVdateText() {
        vdateText = DateUtil.format(this.getVdate());
        return this.vdateText;
    }
    /**
     * 获取 mdateText
     *
     * @rturn mdateText
     */
    public String getMdateText() {
        mdateText = DateUtil.format(this.getMdate());
        return this.mdateText;
    }
    @Override
    @NotEmpty(fieldName = "EtrRepo002.priceModel", groups = {ValidateGroupEdit.class})
    public String getPriceModel() {
        return super.getPriceModel();
    }

    public BigDecimal getAPPentrustAmtOne() {
        return APPentrustAmtOne;
    }

    public void setAPPentrustAmtOne(BigDecimal APPentrustAmtOne) {
        this.APPentrustAmtOne = APPentrustAmtOne;
    }

    public BigDecimal getAPPentrustAmtTWO() {
        return APPentrustAmtTWO;
    }

    public void setAPPentrustAmtTWO(BigDecimal APPentrustAmtTWO) {
        this.APPentrustAmtTWO = APPentrustAmtTWO;
    }
}
