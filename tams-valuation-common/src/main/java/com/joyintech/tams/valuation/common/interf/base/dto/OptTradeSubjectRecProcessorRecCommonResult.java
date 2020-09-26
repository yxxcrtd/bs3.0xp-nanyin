/*
 * 文件名：OptTradeSubjectRecProcessorRecCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：TanXuan
 * 修改时间：2020年01月08日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 期权交易标的信息
 * 
 * @author TanXuan
 * @version 1.0 2020年01月08日
 * @see OptTradeSubjectRecProcessorRecCommonResult
 * @since 1.0
 */
public class OptTradeSubjectRecProcessorRecCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 交易类金融产品
     */
    private List<FinTradeProduct> listValFinTradeProduct = new ArrayList<FinTradeProduct>();

    /**
     * 金融产品分类
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
    
    /**
     * 交易类金融产品附属表
     */
    private List<FinTradeProductAdd> listValFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();

    /**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();
    
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
     * @return listValFinTradeProductAdd
     */
    public List<FinTradeProductAdd> getListValFinTradeProductAdd() {
        return listValFinTradeProductAdd;
    }
    
    /**
     * 设置 listValFinTradeProductAdd
     * @param listValFinTradeProduct listValFinTradeProductAdd
     */
    public void setListValFinTradeProductAdd(List<FinTradeProductAdd> listValFinTradeProductAdd) {
        this.listValFinTradeProductAdd = listValFinTradeProductAdd;
    }
    
    /**
     * 获取 listValInterflog
     * @return listValInterflog.
     */
    public List<ValInterflog> getListValInterflog() {
        return listValInterflog;
    }
    
    /**
     * 设置 listValInterflog
     * @param listValInterflog listValInterflog
     */
    public void setListValInterflog(List<ValInterflog> listValInterflog) {
        this.listValInterflog = listValInterflog;
    }

}
