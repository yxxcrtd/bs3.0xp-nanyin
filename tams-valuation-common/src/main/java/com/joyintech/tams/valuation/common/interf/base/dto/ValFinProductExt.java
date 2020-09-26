package com.joyintech.tams.valuation.common.interf.base.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 估值：标的类金融产品扩展信息表
 * FinProductExt -> FinProductAdd
 * @author zhangwei
 * @version 1.0 2019年1月14日
 * @see ValFinProductExt
 * @since 1.0
 */
public class ValFinProductExt extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 销售渠道
     */
/*    private String saleChannel;*/

    /**
     * 销售分层
     */
    private String saleLayer;

    /**
     * 销售期次
     */
    private Long salePeriod;

    /**
     * 开放类型
     */
    private String openType;

    /**
     * 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    private String carryoverFreq;

    /**
     * 申购确认速度
     */
    private Long purSpeed;

    /**
     * 赎回确认速度
     */
    private Long redSpeed;

    /**
     * 亏损处理方式
     */
    private String lossHandleMode;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 是否按日复利
     */
    private String isCompoundInt;

    /**
     * 收益标识，非保本浮动收益型、保本浮动、保证收益型
     */
    private String profitFlag;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 是否核算
     */
    private String isAccount;

    /**
     * 是否分层核算
     */
    private String isLayerAccount;

    /**
     * 面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceValue;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 销售渠道
     * @return 销售渠道
     */
/*    public String getSaleChannel() {
        return saleChannel;
    }
*/
    /**
     * 设置 销售渠道
     * @param saleChannel 销售渠道
     */
/*    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel == null ? null : saleChannel.trim();
    }*/

    /**
     * 取得 销售分层
     * @return 销售分层
     */
    public String getSaleLayer() {
        return saleLayer;
    }

    /**
     * 设置 销售分层
     * @param saleLayer 销售分层
     */
    public void setSaleLayer(String saleLayer) {
        this.saleLayer = saleLayer == null ? null : saleLayer.trim();
    }

    /**
     * 取得 销售期次
     * @return 销售期次
     */
    public Long getSalePeriod() {
        return salePeriod;
    }

    /**
     * 设置 销售期次
     * @param salePeriod 销售期次
     */
    public void setSalePeriod(Long salePeriod) {
        this.salePeriod = salePeriod;
    }

    /**
     * 取得 开放类型
     * @return 开放类型
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * 设置 开放类型
     * @param openType 开放类型
     */
    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    /**
     * 取得 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     * @return 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    public String getCarryoverFreq() {
        return carryoverFreq;
    }

    /**
     * 设置 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     * @param carryoverFreq 收益结转频率，“每日分配，按月支付”，“每日分配，按日支付”
     */
    public void setCarryoverFreq(String carryoverFreq) {
        this.carryoverFreq = carryoverFreq == null ? null : carryoverFreq.trim();
    }

    /**
     * 取得 申购确认速度
     * @return 申购确认速度
     */
    public Long getPurSpeed() {
        return purSpeed;
    }

    /**
     * 设置 申购确认速度
     * @param purSpeed 申购确认速度
     */
    public void setPurSpeed(Long purSpeed) {
        this.purSpeed = purSpeed;
    }

    /**
     * 取得 赎回确认速度
     * @return 赎回确认速度
     */
    public Long getRedSpeed() {
        return redSpeed;
    }

    /**
     * 设置 赎回确认速度
     * @param redSpeed 赎回确认速度
     */
    public void setRedSpeed(Long redSpeed) {
        this.redSpeed = redSpeed;
    }

    /**
     * 取得 亏损处理方式
     * @return 亏损处理方式
     */
    public String getLossHandleMode() {
        return lossHandleMode;
    }

    /**
     * 设置 亏损处理方式
     * @param lossHandleMode 亏损处理方式
     */
    public void setLossHandleMode(String lossHandleMode) {
        this.lossHandleMode = lossHandleMode == null ? null : lossHandleMode.trim();
    }

    /**
     * 取得 托管活期利息计提
     * @return 托管活期利息计提
     */
    public String getIsCalc() {
        return isCalc;
    }

    /**
     * 设置 托管活期利息计提
     * @param isCalc 托管活期利息计提
     */
    public void setIsCalc(String isCalc) {
        this.isCalc = isCalc == null ? null : isCalc.trim();
    }

    /**
     * 取得 是否按日复利
     * @return 是否按日复利
     */
    public String getIsCompoundInt() {
        return isCompoundInt;
    }

    /**
     * 设置 是否按日复利
     * @param isCompoundInt 是否按日复利
     */
    public void setIsCompoundInt(String isCompoundInt) {
        this.isCompoundInt = isCompoundInt == null ? null : isCompoundInt.trim();
    }

    /**
     * 取得 收益标识，非保本浮动收益型、保本浮动、保证收益型
     * @return 收益标识，非保本浮动收益型、保本浮动、保证收益型
     */
    public String getProfitFlag() {
        return profitFlag;
    }

    /**
     * 设置 收益标识，非保本浮动收益型、保本浮动、保证收益型
     * @param profitFlag 收益标识，非保本浮动收益型、保本浮动、保证收益型
     */
    public void setProfitFlag(String profitFlag) {
        this.profitFlag = profitFlag == null ? null : profitFlag.trim();
    }

    /**
     * 取得 投资组合代码
     * @return 投资组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 投资组合代码
     * @param portfolioId 投资组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 是否核算
     * @return 是否核算
     */
    public String getIsAccount() {
        return isAccount;
    }

    /**
     * 设置 是否核算
     * @param isAccount 是否核算
     */
    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount == null ? null : isAccount.trim();
    }

    /**
     * 取得 是否分层核算
     * @return 是否分层核算
     */
    public String getIsLayerAccount() {
        return isLayerAccount;
    }

    /**
     * 设置 是否分层核算
     * @param isLayerAccount 是否分层核算
     */
    public void setIsLayerAccount(String isLayerAccount) {
        this.isLayerAccount = isLayerAccount == null ? null : isLayerAccount.trim();
    }

    /**
     * 取得 面值
     * @return 面值
     */
    public BigDecimal getFaceValue() {
        return faceValue;
    }

    /**
     * 设置 面值
     * @param faceValue 面值
     */
    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }
}