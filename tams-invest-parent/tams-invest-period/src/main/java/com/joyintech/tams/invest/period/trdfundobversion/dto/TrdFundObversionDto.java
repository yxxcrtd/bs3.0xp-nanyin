/*
 * 文件名：TrdFundDuration001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdfundobversion.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdfundduration.TrdFundDurationCommonDto;
import com.joyintech.tams.invest.period.trdfundobversion.validator.TrdFundObversion001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangxuelei
 * @version 1.0 2019年10月15日
 * @see TrdFundObversionDto
 * @since 1.0
 */
@PageId("TrdFundObversion002")
@ValidLogic(targetClass = TrdFundObversion001Validator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdFundObversionDto extends TrdFundDurationCommonDto implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3974078160802737259L;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 发行机构代码
     */
    private String repIssuePartyId;
    /**
     * 发行人
     */
    private String issuer;

    private String assetType;
    /**
     * 业务类型00 新建模式 01 货币再投，02 非货除权 03 非货分红 04 非货再投（用于画面显示及业务操作类型甄别）
     */
    private String type;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 控件Id
     */
    private String elementId;
    /**
     * 模式（辅助标记功能）
     */
    private String mode;
    /**
     * 有效状态
     */
    private String statusValid;
    /**
     * 资产代码
     */
    @NotEmpty(fieldName = "TrdFundDuration001.finprodMarketId",groups= {ValidateGroupEdit.class})
    private String finprodMarketId;
    /**
     * 份额折算类型
     */
    @NotEmpty(fieldName = "TrdFundObversion001.conversionType",groups= {ValidateGroupEdit.class})
    private String conversionType;
    /**
     * 资产名称
     */
    @NotEmpty(fieldName = "TrdFundDuration001.finprodMarketName",groups= {ValidateGroupEdit.class})
    private String finprodMarketName;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 份额折算日
     */
    @NotEmpty(fieldName = "TrdFundObversion001.conversionTime",groups= {ValidateGroupEdit.class})
    private Date conversionTime;

    private BigDecimal totalAmount;
    /**
     * 折算前单位净值
     */
    @Amount(maxDecBit=8,fieldName = "TrdFundObversion001.bconUnitCprice",groups= {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "TrdFundObversion001.bconUnitCprice",groups= {ValidateGroupEdit.class})
    private BigDecimal bconUnitCprice;
    /**
     * 折算后单位净值
     */
    @Amount(maxDecBit=8,fieldName = "TrdFundObversion001.afterUnitCprice",groups= {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "TrdFundObversion001.afterUnitCprice",groups= {ValidateGroupEdit.class})
    private BigDecimal afterUnitCprice;
    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    //折算比例
    private BigDecimal conversionRat;

    private String workFlowId;

    private boolean isBack;

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public BigDecimal getConversionRat() {
        return conversionRat;
    }

    public void setConversionRat(BigDecimal conversionRat) {
        this.conversionRat = conversionRat;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    @Override
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    @Override
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getConversionType() {
        return conversionType;
    }

    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    public String getFinprodMarketName() {
        return finprodMarketName;
    }

    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }

    @Override
    public String getPortfolioId() {
        return portfolioId;
    }

    @Override
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    public BigDecimal getBconUnitCprice() {
        return bconUnitCprice;
    }

    public void setBconUnitCprice(BigDecimal bconUnitCprice) {
        this.bconUnitCprice = bconUnitCprice;
    }

    public BigDecimal getAfterUnitCprice() {
        return afterUnitCprice;
    }

    public void setAfterUnitCprice(BigDecimal afterUnitCprice) {
        this.afterUnitCprice = afterUnitCprice;
    }

    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    /**
     * PK1
     * @return
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId_PK() {
        return StringUtil.isNotEmpty(super.getTotleTradeId())? super.getTotleTradeId(): IDGenerator.generate();
    }
    /**
     * 获取 PK2
     * @return elementId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusinessCode() {
        return DictCodeListExt2.共通业务类型_基金存续期折算.getValue();
    }
    /**
     * 获取 PK3
     * @return getbTradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getbTradeId() {
        return super.getbTradeId();
    }
    /**
     * 获取 PK4
     * @return getpTradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_4)
    public String getpTradeId() {
        return super.getpTradeId();
    }

    //    画面数据状态
    public String getStatusValidStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, this.statusValid);
    }
    //   交易方向
    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getPs());
    }
    //   资产类型
    public String getAssetTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, this.assetType);
    }

    public String getConversionTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.折算类型, this.conversionType);
    }
}
