/*
 * 文件名：EtrWtFxswapDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrwtfxswap.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.entrust.etrinvestbondinfo.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月18日
 * @see EtrWtFxswapDto
 * @since 1.0
 */
public class EtrWtFxswapDto extends EtrEntrust{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3568128685243375262L;

    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 资产类型
     */
    private String assetType;
    
    /**
     * 交易方向
     */
    private String tradePs;
    
    /**
     * 掉期点
     */
    private BigDecimal swapsPoint;
    
    /**
     * 交易货币
     */
    private String tradeCcy;
    
    /**
     * 交易货币金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    
    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradePercent;
    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal completeAmt;
    
    /**
     * 人员信息业务组件DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 委托关闭方式
     */
    private String closeWay;
    
    /**
     * 期限类型
     */
    private String termType;
    /**
     * 近端交割日
     */
    private String proximalDeliveryDate;
    /**
     * 远端交割日
     */
    private String remoteDeliveryDate;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 货币对
     */
    private String ccyPair;
    /**
     * 交易货币可用持仓
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeCcyAvailablePosition;
    /**
     * 对应货币
     */
    private String contraCcy;

    /**
     * 交易货币账户
     */
    private String tradeCcyAcct;
    /**
     * 对应货币账户
     */
    private String contraCcyAcct;
    
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
     * 获取 tradePs
     * @return tradePs.
     */
    public String getTradePs() {
        return tradePs;
    }

    
    /**
     * 设置 tradePs
     * @param tradePs tradePs
     */
    public void setTradePs(String tradePs) {
        this.tradePs = tradePs;
    }

    
    /**
     * 获取 swapsPoint
     * @return swapsPoint.
     */
    public BigDecimal getSwapsPoint() {
        return swapsPoint;
    }

    
    /**
     * 设置 swapsPoint
     * @param swapsPoint swapsPoint
     */
    public void setSwapsPoint(BigDecimal swapsPoint) {
        this.swapsPoint = swapsPoint;
    }

    
    /**
     * 获取 tradeCcy
     * @return tradeCcy.
     */
    public String getTradeCcy() {
        return tradeCcy;
    }

    
    /**
     * 设置 tradeCcy
     * @param tradeCcy tradeCcy
     */
    public void setTradeCcy(String tradeCcy) {
        this.tradeCcy = tradeCcy;
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
     * 获取 tradePercent
     * @return tradePercent.
     */
    public BigDecimal getTradePercent() {
        return tradePercent;
    }

    
    /**
     * 设置 tradePercent
     * @param tradePercent tradePercent
     */
    
    public void setTradePercent(BigDecimal tradePercent) {
        this.tradePercent = tradePercent;
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
        return closeWay;
    }
    
    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        this.closeWay = closeWay;
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
     * 获取 proximalDeliveryDate
     * @return proximalDeliveryDate.
     */
    public String getProximalDeliveryDate() {
        return proximalDeliveryDate;
    }
    
    /**
     * 设置 proximalDeliveryDate
     * @param proximalDeliveryDate proximalDeliveryDate
     */
    public void setProximalDeliveryDate(String proximalDeliveryDate) {
        this.proximalDeliveryDate = proximalDeliveryDate;
    }
    
    /**
     * 获取 remoteDeliveryDate
     * @return remoteDeliveryDate.
     */
    public String getRemoteDeliveryDate() {
        return remoteDeliveryDate;
    }
    
    /**
     * 设置 remoteDeliveryDate
     * @param remoteDeliveryDate remoteDeliveryDate
     */
    public void setRemoteDeliveryDate(String remoteDeliveryDate) {
        this.remoteDeliveryDate = remoteDeliveryDate;
    }
    
    /**
     * 获取 tradeType
     * @return tradeType.
     */
    public String getTradeType() {
        return tradeType;
    }
    
    /**
     * 设置 tradeType
     * @param tradeType tradeType
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    
    /**
     * 获取 ccyPair
     * @return ccyPair.
     */
    public String getCcyPair() {
        return ccyPair;
    }
    
    /**
     * 设置 ccyPair
     * @param ccyPair ccyPair
     */
    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }
    
    
    /**
     * 获取 tradeCcyAvailablePosition
     * @return tradeCcyAvailablePosition.
     */
    public BigDecimal getTradeCcyAvailablePosition() {
        return tradeCcyAvailablePosition;
    }


    
    /**
     * 设置 tradeCcyAvailablePosition
     * @param tradeCcyAvailablePosition tradeCcyAvailablePosition
     */
    public void setTradeCcyAvailablePosition(BigDecimal tradeCcyAvailablePosition) {
        this.tradeCcyAvailablePosition = tradeCcyAvailablePosition;
    }


    /**
     * 获取 contraCcy
     * @return contraCcy.
     */
    public String getContraCcy() {
        return contraCcy;
    }
    
    /**
     * 设置 contraCcy
     * @param contraCcy contraCcy
     */
    public void setContraCcy(String contraCcy) {
        this.contraCcy = contraCcy;
    }
    
    /**
     * 获取 tradeCcyAcct
     * @return tradeCcyAcct.
     */
    public String getTradeCcyAcct() {
        return tradeCcyAcct;
    }
    
    /**
     * 设置 tradeCcyAcct
     * @param tradeCcyAcct tradeCcyAcct
     */
    public void setTradeCcyAcct(String tradeCcyAcct) {
        this.tradeCcyAcct = tradeCcyAcct;
    }

    /**
     * 获取 contraCcyAcct
     * @return contraCcyAcct.
     */
    public String getContraCcyAcct() {
        return contraCcyAcct;
    }

    
    /**
     * 设置 contraCcyAcct
     * @param contraCcyAcct contraCcyAcct
     */
    public void setContraCcyAcct(String contraCcyAcct) {
        this.contraCcyAcct = contraCcyAcct;
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
     * 获取 completeAmt
     * @return completeAmt.
     */
    public BigDecimal getCompleteAmt() {
        return completeAmt;
    }
    
    /**
     * 设置 completeAmt
     * @param completeAmt completeAmt
     */
    public void setCompleteAmt(BigDecimal completeAmt) {
        this.completeAmt = completeAmt;
    }
}
