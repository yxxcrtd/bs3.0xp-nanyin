/*
 * 文件名：FinStageManagementDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.asset.common.validator.FinStageManagementValidator;
import com.joyintech.tams.invest.asset.plan.planProfit.dto.FinPlanProfitAssetDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 期次管理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月12日
 * @see FinStageManagementDto
 * @since 1.0
 */
@PageId("FinStageManagement")
@ValidLogic(targetClass = FinStageManagementValidator.class,groups = {ValidateGroupEdit.class})
public class FinStageManagementDto extends FinProduct implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    private String elementId;
    
    
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
    private String period;
    
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
     * 金融产品代码
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getFinprodId(){
        return StringUtil.isNotEmpty(super.getFinprodId())?super.getFinprodId():StringUtil.EMPTY;
    }

    
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
        referRate =  ArithmeticUtil.doSub(this.getInvestRate(),this.getBuyoutRate());
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
    public String getPeriod() {
        return period;
    }

    
    /**
     * 设置 period
     * @param period period
     */
    public void setPeriod(String period) {
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
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
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

    public String getPeriodCount(){
        if(null!=this.getPeriod()){
            String period = String.valueOf(this.getPeriod());
            return period.substring(period.length()-2,period.length()).replaceAll("^(0+)", "");
        }
        return StringUtil.EMPTY;
    }

    //add by mashukai 20201119 期次管理导出新增 start
    String vdateStr;

    String mdateStr;

    public String getVdateStr() {
        return vdateStr;
    }

    public void setVdateStr(String vdateStr) {
        this.vdateStr = vdateStr;
    }

    public String getMdateStr() {
        return mdateStr;
    }

    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }
    //add by mashukai 20201119 期次管理导出新增 end
}
