/*
 * 文件名：ProductLedger001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年9月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.riskmonitorindicators.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 产品台账model
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年9月24日
 * @see RiskMonitorIndicatorsDto
 * @since 1.0
 */
public class RiskMonitorIndicatorsDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    private String prodId;//组合代码
    private String combinationname;//组合名称
    private String manager;//产品经理
    private BigDecimal prodduration;//产品久期
    private BigDecimal assetduration;//资产久期
    private BigDecimal secduration;//债券久期
    private BigDecimal durationmismatch;//久期错配
    private BigDecimal termmismatch;//期限错配
    private BigDecimal leverageratio;//杠杆率%
    private BigDecimal cleancashflownextday;//未来隔夜净现金流
    private BigDecimal cleancashflowsevenday;//未来7日净现金流
    private BigDecimal cleancashflowonemonth;//未来1个月净现金流
    private BigDecimal openhighliquidityratio;//开放式高流动性资产占比%
    private BigDecimal highliquidityratio;//高流动性资产占比%
    private BigDecimal singlecustomerconcentration;//单一最大认购客户集中度
    private BigDecimal singleassetconcentration;//单一最大资产集中度
    private BigDecimal singlefinancierconcentration;//单一最大融资人集中度
    private BigDecimal noopensecconcentration;//非公开债券集中度
    private BigDecimal singlejoinratio;//单一关联方单笔交易占资本净额比例%
    private BigDecimal singlenonstandardratio;//单一机构非标占资本净额比例%
    private BigDecimal nonstandardratioclean;//非标资产占产品净资产比例%
    private BigDecimal nonstandardratiowhole;//非标资产占全行总资产比例%
    private BigDecimal floatingpl;//浮动盈亏金额
    private String prodManager;
     
    /**
     * 获取 prodManager
     * @return prodManager.
     */
    public String getProdManager() {
        return prodManager;
    }

    
    /**
     * 设置 prodManager
     * @param prodManager prodManager
     */
    public void setProdManager(String prodManager) {
        this.prodManager = prodManager;
    }

    /**
     * 获取 prodId
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }
 
    /**
     * 设置 prodId
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 combinationname
     * @return combinationname.
     */
    public String getCombinationname() {
        return combinationname;
    }
    
    /**
     * 设置 combinationname
     * @param combinationname combinationname
     */
    public void setCombinationname(String combinationname) {
        this.combinationname = combinationname;
    }
    
    /**
     * 获取 manager
     * @return manager.
     */
    public String getManager() {
        return manager;
    }
    
    /**
     * 设置 manager
     * @param manager manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    /**
     * 获取 prodduration
     * @return prodduration.
     */
    public BigDecimal getProdduration() {
        return prodduration;
    }
    
    /**
     * 设置 prodduration
     * @param prodduration prodduration
     */
    public void setProdduration(BigDecimal prodduration) {
        this.prodduration = prodduration;
    }
    
    /**
     * 获取 assetduration
     * @return assetduration.
     */
    public BigDecimal getAssetduration() {
        return assetduration;
    }
    
    /**
     * 设置 assetduration
     * @param assetduration assetduration
     */
    public void setAssetduration(BigDecimal assetduration) {
        this.assetduration = assetduration;
    }
    
    /**
     * 获取 secduration
     * @return secduration.
     */
    public BigDecimal getSecduration() {
        return secduration;
    }
    
    /**
     * 设置 secduration
     * @param secduration secduration
     */
    public void setSecduration(BigDecimal secduration) {
        this.secduration = secduration;
    }
    
    /**
     * 获取 durationmismatch
     * @return durationmismatch.
     */
    public BigDecimal getDurationmismatch() {
        return durationmismatch;
    }
    
    /**
     * 设置 durationmismatch
     * @param durationmismatch durationmismatch
     */
    public void setDurationmismatch(BigDecimal durationmismatch) {
        this.durationmismatch = durationmismatch;
    }
    
    /**
     * 获取 termmismatch
     * @return termmismatch.
     */
    public BigDecimal getTermmismatch() {
        return termmismatch;
    }
    
    /**
     * 设置 termmismatch
     * @param termmismatch termmismatch
     */
    public void setTermmismatch(BigDecimal termmismatch) {
        this.termmismatch = termmismatch;
    }
    
    /**
     * 获取 leverageratio
     * @return leverageratio.
     */
    public BigDecimal getLeverageratio() {
        return leverageratio;
    }
    
    /**
     * 设置 leverageratio
     * @param leverageratio leverageratio
     */
    public void setLeverageratio(BigDecimal leverageratio) {
        this.leverageratio = leverageratio;
    }
    
    /**
     * 获取 cleancashflownextday
     * @return cleancashflownextday.
     */
    public BigDecimal getCleancashflownextday() {
        return cleancashflownextday;
    }
    
    /**
     * 设置 cleancashflownextday
     * @param cleancashflownextday cleancashflownextday
     */
    public void setCleancashflownextday(BigDecimal cleancashflownextday) {
        this.cleancashflownextday = cleancashflownextday;
    }
    
    /**
     * 获取 cleancashflowsevenday
     * @return cleancashflowsevenday.
     */
    public BigDecimal getCleancashflowsevenday() {
        return cleancashflowsevenday;
    }
    
    /**
     * 设置 cleancashflowsevenday
     * @param cleancashflowsevenday cleancashflowsevenday
     */
    public void setCleancashflowsevenday(BigDecimal cleancashflowsevenday) {
        this.cleancashflowsevenday = cleancashflowsevenday;
    }
    
    /**
     * 获取 cleancashflowonemonth
     * @return cleancashflowonemonth.
     */
    public BigDecimal getCleancashflowonemonth() {
        return cleancashflowonemonth;
    }
    
    /**
     * 设置 cleancashflowonemonth
     * @param cleancashflowonemonth cleancashflowonemonth
     */
    public void setCleancashflowonemonth(BigDecimal cleancashflowonemonth) {
        this.cleancashflowonemonth = cleancashflowonemonth;
    }
    
    /**
     * 获取 openhighliquidityratio
     * @return openhighliquidityratio.
     */
    public BigDecimal getOpenhighliquidityratio() {
        return openhighliquidityratio;
    }
    
    /**
     * 设置 openhighliquidityratio
     * @param openhighliquidityratio openhighliquidityratio
     */
    public void setOpenhighliquidityratio(BigDecimal openhighliquidityratio) {
        this.openhighliquidityratio = openhighliquidityratio;
    }
    
    /**
     * 获取 highliquidityratio
     * @return highliquidityratio.
     */
    public BigDecimal getHighliquidityratio() {
        return highliquidityratio;
    }
    
    /**
     * 设置 highliquidityratio
     * @param highliquidityratio highliquidityratio
     */
    public void setHighliquidityratio(BigDecimal highliquidityratio) {
        this.highliquidityratio = highliquidityratio;
    }
    
    /**
     * 获取 singlecustomerconcentration
     * @return singlecustomerconcentration.
     */
    public BigDecimal getSinglecustomerconcentration() {
        return singlecustomerconcentration;
    }
    
    /**
     * 设置 singlecustomerconcentration
     * @param singlecustomerconcentration singlecustomerconcentration
     */
    public void setSinglecustomerconcentration(BigDecimal singlecustomerconcentration) {
        this.singlecustomerconcentration = singlecustomerconcentration;
    }
    
    /**
     * 获取 singleassetconcentration
     * @return singleassetconcentration.
     */
    public BigDecimal getSingleassetconcentration() {
        return singleassetconcentration;
    }
    
    /**
     * 设置 singleassetconcentration
     * @param singleassetconcentration singleassetconcentration
     */
    public void setSingleassetconcentration(BigDecimal singleassetconcentration) {
        this.singleassetconcentration = singleassetconcentration;
    }
    
    /**
     * 获取 singlefinancierconcentration
     * @return singlefinancierconcentration.
     */
    public BigDecimal getSinglefinancierconcentration() {
        return singlefinancierconcentration;
    }
    
    /**
     * 设置 singlefinancierconcentration
     * @param singlefinancierconcentration singlefinancierconcentration
     */
    public void setSinglefinancierconcentration(BigDecimal singlefinancierconcentration) {
        this.singlefinancierconcentration = singlefinancierconcentration;
    }
    
    /**
     * 获取 noopensecconcentration
     * @return noopensecconcentration.
     */
    public BigDecimal getNoopensecconcentration() {
        return noopensecconcentration;
    }
    
    /**
     * 设置 noopensecconcentration
     * @param noopensecconcentration noopensecconcentration
     */
    public void setNoopensecconcentration(BigDecimal noopensecconcentration) {
        this.noopensecconcentration = noopensecconcentration;
    }
    
    /**
     * 获取 singlejoinratio
     * @return singlejoinratio.
     */
    public BigDecimal getSinglejoinratio() {
        return singlejoinratio;
    }
    
    /**
     * 设置 singlejoinratio
     * @param singlejoinratio singlejoinratio
     */
    public void setSinglejoinratio(BigDecimal singlejoinratio) {
        this.singlejoinratio = singlejoinratio;
    }
    
    /**
     * 获取 singlenonstandardratio
     * @return singlenonstandardratio.
     */
    public BigDecimal getSinglenonstandardratio() {
        return singlenonstandardratio;
    }
    
    /**
     * 设置 singlenonstandardratio
     * @param singlenonstandardratio singlenonstandardratio
     */
    public void setSinglenonstandardratio(BigDecimal singlenonstandardratio) {
        this.singlenonstandardratio = singlenonstandardratio;
    }
    
    /**
     * 获取 nonstandardratioclean
     * @return nonstandardratioclean.
     */
    public BigDecimal getNonstandardratioclean() {
        return nonstandardratioclean;
    }
    
    /**
     * 设置 nonstandardratioclean
     * @param nonstandardratioclean nonstandardratioclean
     */
    public void setNonstandardratioclean(BigDecimal nonstandardratioclean) {
        this.nonstandardratioclean = nonstandardratioclean;
    }
    
    /**
     * 获取 nonstandardratiowhole
     * @return nonstandardratiowhole.
     */
    public BigDecimal getNonstandardratiowhole() {
        return nonstandardratiowhole;
    }
    
    /**
     * 设置 nonstandardratiowhole
     * @param nonstandardratiowhole nonstandardratiowhole
     */
    public void setNonstandardratiowhole(BigDecimal nonstandardratiowhole) {
        this.nonstandardratiowhole = nonstandardratiowhole;
    }
    
    /**
     * 获取 floatingpl
     * @return floatingpl.
     */
    public BigDecimal getFloatingpl() {
        return floatingpl;
    }
    
    /**
     * 设置 floatingpl
     * @param floatingpl floatingpl
     */
    public void setFloatingpl(BigDecimal floatingpl) {
        this.floatingpl = floatingpl;
    }
}
