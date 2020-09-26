/*
 * 文件名：DepositeAssetValCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 存款类资产信息接口 返回共通结果
 * 
 * @author weipeng
 * @version 1.0 2018年6月27日
 * @see DepositeAssetBaseInfoValCommonResult
 * @since 1.0
 */
public class DepositeAssetBaseInfoValCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 交易类金融产品
     */
    /*List<FinTradeProduct> listValTradeFinProduct = new ArrayList<FinTradeProduct>();*/

    /**
     * 标的类金融产品
     */
    List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
    /**
     * 金融产品分类
     */
    List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();

    /**
     * 现金流信息
     */
    List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();

    /**
     * 现金流计算规则
     */
    List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();

    /**
     * 现金流支付规则
     */
    List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();

    /**
     * 现金流计划
     */
    List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();

    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 浮动利率初始化
     */
    List<FloatingInterestRateCommonResult> listFloatingInterestRateCommonResult = new ArrayList<FloatingInterestRateCommonResult>();

    /**
     * 获取 listValTradeFinProduct
     * 
     * @return listValTradeFinProduct.
     */
    /*public List<FinTradeProduct> getListValTradeFinProduct() {
        return listValTradeFinProduct;
    }*/

    /**
     * 设置 listValTradeFinProduct
     * 
     * @param listValTradeFinProduct listValTradeFinProduct
     */
    /*public void setListValTradeFinProduct(List<FinTradeProduct> listValTradeFinProduct) {
        this.listValTradeFinProduct = listValTradeFinProduct;
    }*/

    /**
     * 获取 listValFinProductType
     * 
     * @return listValFinProductType.
     */
    public List<FinProductType> getListValFinProductType() {
        return listValFinProductType;
    }

    /**
     * 设置 listValFinProductType
     * 
     * @param listValFinProductType listValFinProductType
     */
    public void setListValFinProductType(List<FinProductType> listValFinProductType) {
        this.listValFinProductType = listValFinProductType;
    }

    /**
     * 获取 listValCashInfo
     * 
     * @return listValCashInfo.
     */
    public List<FinCashInfo> getListValCashInfo() {
        return listValCashInfo;
    }

    /**
     * 设置 listValCashInfo
     * 
     * @param listValCashInfo listValCashInfo
     */
    public void setListValCashInfo(List<FinCashInfo> listValCashInfo) {
        this.listValCashInfo = listValCashInfo;
    }

    /**
     * 获取 listValCashCalcRule
     * 
     * @return listValCashCalcRule.
     */
    public List<FinCashCalcRule> getListValCashCalcRule() {
        return listValCashCalcRule;
    }

    /**
     * 设置 listValCashCalcRule
     * 
     * @param listValCashCalcRule listValCashCalcRule
     */
    public void setListValCashCalcRule(List<FinCashCalcRule> listValCashCalcRule) {
        this.listValCashCalcRule = listValCashCalcRule;
    }

    /**
     * 获取 listValCashPayRule
     * 
     * @return listValCashPayRule.
     */
    public List<FinCashPayRule> getListValCashPayRule() {
        return listValCashPayRule;
    }

    /**
     * 设置 listValCashPayRule
     * 
     * @param listValCashPayRule listValCashPayRule
     */
    public void setListValCashPayRule(List<FinCashPayRule> listValCashPayRule) {
        this.listValCashPayRule = listValCashPayRule;
    }

    /**
     * 获取 listValCashPlan
     * 
     * @return listValCashPlan.
     */
    public List<FinCashPlan> getListValCashPlan() {
        return listValCashPlan;
    }

    /**
     * 设置 listValCashPlan
     * 
     * @param listValCashPlan listValCashPlan
     */
    public void setListValCashPlan(List<FinCashPlan> listValCashPlan) {
        this.listValCashPlan = listValCashPlan;
    }

    /**
     * 获取 valInterflog
     * 
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    /**
     * 设置 valInterflog
     * 
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    /**
     * 获取 listFloatingInterestRateCommonResult
     * 
     * @return listFloatingInterestRateCommonResult.
     */
    public List<FloatingInterestRateCommonResult> getListFloatingInterestRateCommonResult() {
        return listFloatingInterestRateCommonResult;
    }

    /**
     * 设置 listFloatingInterestRateCommonResult
     * 
     * @param listFloatingInterestRateCommonResult listFloatingInterestRateCommonResult
     */
    public void setListFloatingInterestRateCommonResult(List<FloatingInterestRateCommonResult> listFloatingInterestRateCommonResult) {
        this.listFloatingInterestRateCommonResult = listFloatingInterestRateCommonResult;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    /**
     * 获取 listValFinProduct
     * @return listValFinProduct.
     */
    public List<FinProduct> getListValFinProduct() {
        return listValFinProduct;
    }

    
    /**
     * 设置 listValFinProduct
     * @param listValFinProduct listValFinProduct
     */
    public void setListValFinProduct(List<FinProduct> listValFinProduct) {
        this.listValFinProduct = listValFinProduct;
    }

}
