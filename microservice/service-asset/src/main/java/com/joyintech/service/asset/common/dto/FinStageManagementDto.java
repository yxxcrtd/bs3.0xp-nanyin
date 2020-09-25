/*
 * 文件名：FinStageManagementDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.asset.plan.planProfit.dto.FinPlanProfitAssetDto;

/**
 * 期次管理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月12日
 * @see FinStageManagementDto
 * @since 1.0
 */
public class FinStageManagementDto extends FinProduct{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 借款利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal loadRate;

    /**
     * 净投资收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investRate;

    /**
     * 买断利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyoutRate;
    
    /**
     * 资产推荐费率
     */
    private BigDecimal referRate;
    
    /**
     * 销售期次
     */
    private Long period;
    
    /**
     * 行是否编辑标志
     */
    private String updateFlg;
    
    /**
     * 是否已支付
     */
    private String isPayed;
    
    /**
     * 本金现金流代码
     */
    private String priCashId;
    
    /**
     * 利息现金流代码
     */
    private String intCashId;
    
    
    /**
     * 选中的记录
     */
    private transient List<FinStageManagementDto> selectedItem;
    
    /**
     * 计划类资产Dto
     */
    private List<FinPlanProfitAssetDto> finPlanProfitAssetList;

    
    /**
     * 获取 loadRate
     * @return loadRate.
     */
    public BigDecimal getLoadRate() {
        return loadRate;
    }

    
    /**
     * 设置 loadRate
     * @param loadRate loadRate
     */
    public void setLoadRate(BigDecimal loadRate) {
        this.loadRate = loadRate;
    }

    
    /**
     * 获取 investRate
     * @return investRate.
     */
    public BigDecimal getInvestRate() {
        return investRate;
    }

    
    /**
     * 设置 investRate
     * @param investRate investRate
     */
    public void setInvestRate(BigDecimal investRate) {
        this.investRate = investRate;
    }

    
    /**
     * 获取 buyoutRate
     * @return buyoutRate.
     */
    public BigDecimal getBuyoutRate() {
        return buyoutRate;
    }

    
    /**
     * 设置 buyoutRate
     * @param buyoutRate buyoutRate
     */
    public void setBuyoutRate(BigDecimal buyoutRate) {
        this.buyoutRate = buyoutRate;
    }

    
    /**
     * 获取 referRate
     * @return referRate.
     */
    public BigDecimal getReferRate() {
        return referRate;
    }

    
    /**
     * 设置 referRate
     * @param referRate referRate
     */
    public void setReferRate(BigDecimal referRate) {
        this.referRate = referRate;
    }

    
    /**
     * 获取 period
     * @return period.
     */
    public Long getPeriod() {
        return period;
    }

    
    /**
     * 设置 period
     * @param period period
     */
    public void setPeriod(Long period) {
        this.period = period;
    }


    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinStageManagementDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinStageManagementDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 updateFlg
     * @return updateFlg.
     */
    public String getUpdateFlg() {
        return updateFlg;
    }


    
    /**
     * 设置 updateFlg
     * @param updateFlg updateFlg
     */
    public void setUpdateFlg(String updateFlg) {
        this.updateFlg = updateFlg;
    }


    
    /**
     * 获取 finPlanProfitAssetList
     * @return finPlanProfitAssetList.
     */
    public List<FinPlanProfitAssetDto> getFinPlanProfitAssetList() {
        return finPlanProfitAssetList;
    }


    
    /**
     * 设置 finPlanProfitAssetList
     * @param finPlanProfitAssetList finPlanProfitAssetList
     */
    public void setFinPlanProfitAssetList(List<FinPlanProfitAssetDto> finPlanProfitAssetList) {
        this.finPlanProfitAssetList = finPlanProfitAssetList;
    }


    
    /**
     * 获取 isPayed
     * @return isPayed.
     */
    public String getIsPayed() {
        return isPayed;
    }


    
    /**
     * 设置 isPayed
     * @param isPayed isPayed
     */
    public void setIsPayed(String isPayed) {
        this.isPayed = isPayed;
    }


    
    /**
     * 获取 priCashId
     * @return priCashId.
     */
    public String getPriCashId() {
        return priCashId;
    }


    
    /**
     * 设置 priCashId
     * @param priCashId priCashId
     */
    public void setPriCashId(String priCashId) {
        this.priCashId = priCashId;
    }


    
    /**
     * 获取 intCashId
     * @return intCashId.
     */
    public String getIntCashId() {
        return intCashId;
    }


    
    /**
     * 设置 intCashId
     * @param intCashId intCashId
     */
    public void setIntCashId(String intCashId) {
        this.intCashId = intCashId;
    }
    
    
}
