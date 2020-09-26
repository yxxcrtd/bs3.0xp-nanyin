/*
 * 文件名：SecLendInfoCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinDealPledge;
import com.joyintech.tams.common.base.entity.FinDealTarget;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年10月30日
 * @see SecLendInfoCommonResult
 * @since 1.0
 */
public class SecLendInfoCommonResult extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 交易类金融产品表
     */
    List<FinTradeProduct> listValFinTradeProduct = new ArrayList<FinTradeProduct>();
    
    /**
     * 金融产品分类表
     */
    List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
    
    /**
     * 交易类金融产品附属表
     */
    List<FinTradeProductAdd> listValFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();
    
    /**
     * 质押品
     */
    List<FinDealPledge> listFinDealPledge = new ArrayList<FinDealPledge>();
    
    /**
     * 金融产品标的信息表
     */
    List<FinDealTarget> listFinDealTarget = new ArrayList<FinDealTarget>();
    
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    
    /**
     * 获取 listValFinTradeProduct
     * @return listValFinTradeProduct.
     */
    public List<FinTradeProduct> getListValFinTradeProduct() {
        return listValFinTradeProduct;
    }

    
    /**
     * 设置 listValFinTradeProduct
     * @param listValFinTradeProduct listValFinTradeProduct
     */
    public void setListValFinTradeProduct(List<FinTradeProduct> listValFinTradeProduct) {
        this.listValFinTradeProduct = listValFinTradeProduct;
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
     * 获取 listValFinTradeProductAdd
     * @return listValFinTradeProductAdd.
     */
    public List<FinTradeProductAdd> getListValFinTradeProductAdd() {
        return listValFinTradeProductAdd;
    }

    
    /**
     * 设置 listValFinTradeProductAdd
     * @param listValFinTradeProductAdd listValFinTradeProductAdd
     */
    public void setListValFinTradeProductAdd(List<FinTradeProductAdd> listValFinTradeProductAdd) {
        this.listValFinTradeProductAdd = listValFinTradeProductAdd;
    }

    
    /**
     * 获取 listFinDealPledge
     * @return listFinDealPledge.
     */
    public List<FinDealPledge> getListFinDealPledge() {
        return listFinDealPledge;
    }

    
    /**
     * 设置 listFinDealPledge
     * @param listFinDealPledge listFinDealPledge
     */
    public void setListFinDealPledge(List<FinDealPledge> listFinDealPledge) {
        this.listFinDealPledge = listFinDealPledge;
    }

    
    /**
     * 获取 listFinDealTarget
     * @return listFinDealTarget.
     */
    public List<FinDealTarget> getListFinDealTarget() {
        return listFinDealTarget;
    }

    
    /**
     * 设置 listFinDealTarget
     * @param listFinDealTarget listFinDealTarget
     */
    public void setListFinDealTarget(List<FinDealTarget> listFinDealTarget) {
        this.listFinDealTarget = listFinDealTarget;
    }

    
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

}
