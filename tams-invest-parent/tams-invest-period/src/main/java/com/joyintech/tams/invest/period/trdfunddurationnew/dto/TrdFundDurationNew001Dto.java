/*
 * 文件名：TrdFundDurationNew001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdfunddurationnew.dto;

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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdfundduration.TrdFundDurationCommonDto;
import com.joyintech.tams.invest.period.trdfunddurationnew.validator.TrdFundDurationNew001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年10月15日
 * @see TrdFundDurationNew001Dto
 * @since 1.0
 */
@PageId("TrdFundDurationNew002")
@ValidLogic(targetClass = TrdFundDurationNew001Validator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdFundDurationNew001Dto extends TrdFundDurationCommonDto implements ProcessEntity {

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
     * 基金类型
     */
    @NotEmpty(fieldName = "TrdFundDuration001.assetType",groups= {ValidateGroupEdit.class})
    private String assetType;
    /**
     * 资产名称
     */
    @NotEmpty(fieldName = "TrdFundDuration001.finprodMarketName",groups= {ValidateGroupEdit.class})
    private String finprodMarketName;
    /**
     * 组合代码
     */
    private String portfolioId;
//    /**
//     * 分红类型
//     */
//    @NotEmpty(fieldName = "TrdFundDuration001.bonusMode",groups= {ValidateGroupEdit.class})
//    private String bonusMode;

    private String bonusModeStr;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 权益登记日
     */
    @NotEmpty(fieldName = "TrdFundDuration001.registDate",groups= {ValidateGroupEdit.class})
    private Date registDate;
    /**
     * 除权日
     */
    private Date exRightsDate;
    /**
     * 分红到账日
     */
    private Date arriveDate;
    /**
     * 红利再投日
     */
    private Date reInvesDate;
    /**
     * 权益登记日份额
     */
    private BigDecimal registShare;
    /**
     * 单位分红净值
     */
    @Amount(maxDecBit=4,fieldName = "TrdFundDuration001.unitCprice",groups= {ValidateGroupEdit.class})
    private BigDecimal unitCprice;
    /**
     * 分红金额
     */
    private BigDecimal tradeAmt;
    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;
    /**
     * 分红份额
     */
    private BigDecimal qty;

    /**
     * 通道名称
     */
    private String chlName;

    /**
     * 获取 chlName
     * 
     * @return chlName.
     */
    public String getChlName() {
        return chlName;
    }

    /**
     * 设置 chlName
     * 
     * @param chlName chlName
     */
    public void setChlName(String chlName) {
        this.chlName = chlName;
    }

    //add by lishuping 20191030 start
    /**
     * 资产简称
     */
    private String finprodAbbr;

//    /**
//     * 是否来源来账分拣
//     */
//    private String isFromCheck;
//
//    /**
//     * 是否来源待办事项
//     */
//    private String isFromTodo;

    /**
     * 红利再投
     */
    private String pstype;

    public String getPstype() {
        return pstype;
    }

    public void setPstype(String pstype) {
        this.pstype = pstype;
    }

//    public String getIsFromCheck() {
//        return isFromCheck;
//    }
//
//    public void setIsFromCheck(String isFromCheck) {
//        this.isFromCheck = isFromCheck;
//    }
//
//    public String getIsFromTodo() {
//        return isFromTodo;
//    }
//
//    public void setIsFromTodo(String isFromTodo) {
//        this.isFromTodo = isFromTodo;
//    }

    /**
     * finprodAbbr
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    //add by lishuping 20191030 end

    public String getUpdateFlag(){
        if(StringUtil.equals(DictCodeList.交易方向_除权登记.getValue(),getPs())
                && StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())
                && StringUtil.equals(assetType, DictCodeList.基金类资产类型_非货币基金.getValue())
                && StringUtil.isEmpty(getHasFlow())){
            return "01";
        }else{
            return "02";
        }
//        if(!StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())||StringUtil.isNotEmpty(getHasFlow())){
//            return "02";
//        }
//        else if(StringUtil.equals(type,"04")||StringUtil.equals(type,"01")||StringUtil.equals(type,"03")||StringUtil.equals(type,"06")){
//            return "02";
//        }
//        else{
//            return "01";
//        }
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
//    //   分红类型
//    public String getBonusModeStr(){
//        return SysDataUtil.getNameFromDataDict(DictCode.分红方式, this.bonusMode);
//    }


    public String getBonusModeStr() {
        return bonusModeStr;
    }

    public void setBonusModeStr(String bonusModeStr) {
        this.bonusModeStr = bonusModeStr;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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
        return DictCodeList.共通业务类型_基金类资产存续期.getValue();
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
        /**
         * 获取 statusValid
         * @return statusValid.
         */
    public String getStatusValid() {
        return statusValid;
    }
    
    /**
     * 设置 statusValid
     * @param statusValid statusValid
     */
    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }
    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
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
     * 获取 assetType
     * @return assetType.
     */
    public String getAssetType() {
        return assetType;
    }
    
    /**
     * 设置 assetType
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    
    /**
     * 获取 finprodMarketName
     * @return finprodMarketName.
     */
    public String getFinprodMarketName() {
        return finprodMarketName;
    }
    
    /**
     * 设置 finprodMarketName
     * @param finprodMarketName finprodMarketName
     */
    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }
    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }
    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

//    /**
//     * 获取 bonusMode
//     * @return bonusMode.
//     */
//    public String getBonusMode() {
//        return bonusMode;
//    }
//
//    /**
//     * 设置 bonusMode
//     * @param bonusMode bonusMode
//     */
//    public void setBonusMode(String bonusMode) {
//        this.bonusMode = bonusMode;
//    }
    
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
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * 获取 registDate
     * @return registDate.
     */
    public Date getRegistDate() {
        return registDate;
    }
    
    /**
     * 设置 registDate
     * @param registDate registDate
     */
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
    
    /**
     * 获取 exRightsDate
     * @return exRightsDate.
     */
    public Date getExRightsDate() {
        return exRightsDate;
    }
    
    /**
     * 设置 exRightsDate
     * @param exRightsDate exRightsDate
     */
    public void setExRightsDate(Date exRightsDate) {
        this.exRightsDate = exRightsDate;
    }
    
    /**
     * 获取 arriveDate
     * @return arriveDate.
     */
    public Date getArriveDate() {
        return arriveDate;
    }
    
    /**
     * 设置 arriveDate
     * @param arriveDate arriveDate
     */
    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }
    
    /**
     * 获取 reInvesDate
     * @return reInvesDate.
     */
    public Date getReInvesDate() {
        return reInvesDate;
    }
    
    /**
     * 设置 reInvesDate
     * @param reInvesDate reInvesDate
     */
    public void setReInvesDate(Date reInvesDate) {
        this.reInvesDate = reInvesDate;
    }
    
    /**
     * 获取 registShare
     * @return registShare.
     */
    public BigDecimal getRegistShare() {
        return registShare;
    }
    
    /**
     * 设置 registShare
     * @param registShare registShare
     */
    public void setRegistShare(BigDecimal registShare) {
        this.registShare = registShare;
    }
    
    /**
     * 获取 unitCprice
     * @return unitCprice.
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }
    
    /**
     * 设置 unitCprice
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }
    
    /**
     * 获取 tradeAmt
     * @return tradeAmt.
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }
    
    /**
     * 设置 tradeAmt
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }
    
    /**
     * 获取 qty
     * @return qty.
     */
    public BigDecimal getQty() {
        return qty;
    }
    
    /**
     * 设置 qty
     * @param qty qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
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

    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * 获取 uploadFileList
     *
     * @return uploadFileList
     */
    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }

    /**
     * 设置 uploadFileList
     *
     * @param uploadFileList uploadFileList
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }
}
