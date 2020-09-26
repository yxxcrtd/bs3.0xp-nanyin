/*
 * 文件名：FundBaseInfoForInterfRecProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;

/**
 *  基金类资产
 * FundBaseInfoForInterfRecProcessorResult
 * @author HuangJian
 * @version 1.0 2018年6月26日
 * @see FundBaseInfoForInterfRecProcessorResult
 * @since 1.0
 */
public class FundBaseInfoForInterfRecCommonResult extends BaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 标的类金融产品表
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();

    /**
     * 标的类金融产品扩展表
     */
    private List<FinProductAdd> listValFinProductExt = new ArrayList<FinProductAdd>();
    
    /**
     * 金融产品分类表
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
    
    /**
     * 现金流信息表
     */
    private List<FinCashInfo> listValCashInfo =  new ArrayList<FinCashInfo>();
    
    /**
     * 现金流支付规则表
     */
    private List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
    /**
     * 现金流计划表
     */
    private List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
    
    private List<ValInterflog> valInterflog = new ArrayList<>();
    
    

    
    /**
     * 获取 valInterflog
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    
    /**
     * 设置 valInterflog
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
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
    
    /**
     * 获取 listValFinProductExt
     * @return listValFinProductExt.
     */
    public List<FinProductAdd> getListValFinProductExt() {
        return listValFinProductExt;
    }
    
    /**
     * 设置 listValFinProductExt
     * @param listValFinProductExt listValFinProductExt
     */
    public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
        this.listValFinProductExt = listValFinProductExt;
    }
    
    /**
     * 获取 listValFinProductType
     * @return listValFinProductType.
     */
    public List<FinProductType> getListValFinProductType() {
        return listValFinProductType;
    }
    
    /**
     * 设置 listValFinProductType
     * @param listValFinProductType listValFinProductType
     */
    public void setListValFinProductType(List<FinProductType> listValFinProductType) {
        this.listValFinProductType = listValFinProductType;
    }
    
    /**
     * 获取 listValCashInfo
     * @return listValCashInfo.
     */
    public List<FinCashInfo> getListValCashInfo() {
        return listValCashInfo;
    }
    
    /**
     * 设置 listValCashInfo
     * @param listValCashInfo listValCashInfo
     */
    public void setListValCashInfo(List<FinCashInfo> listValCashInfo) {
        this.listValCashInfo = listValCashInfo;
    }
    
    /**
     * 获取 listValCashPayRule
     * @return listValCashPayRule.
     */
    public List<FinCashPayRule> getListValCashPayRule() {
        return listValCashPayRule;
    }
    
    /**
     * 设置 listValCashPayRule
     * @param listValCashPayRule listValCashPayRule
     */
    public void setListValCashPayRule(List<FinCashPayRule> listValCashPayRule) {
        this.listValCashPayRule = listValCashPayRule;
    }
    
    /**
     * 获取 listValCashPlan
     * @return listValCashPlan.
     */
    public List<FinCashPlan> getListValCashPlan() {
        return listValCashPlan;
    }
    
    /**
     * 设置 listValCashPlan
     * @param listValCashPlan listValCashPlan
     */
    public void setListValCashPlan(List<FinCashPlan> listValCashPlan) {
        this.listValCashPlan = listValCashPlan;
    }
    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
