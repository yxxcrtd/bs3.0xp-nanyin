/*
 * 文件名：AssetMangementRecResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngassetn.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.NPlannedAssetBaseInfoCommonRecResult;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 计划类资产（净值型）基础信息Processor返回共通结果
 * 
 * @author chufengjie
 * @version 1.0 2018年8月3日
 * @see NPlannedAssetBaseInfoRecResult
 * @since 1.0
 */
public class NPlannedAssetBaseInfoRecResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标的类金融产品表
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
    /**
     * 金融产品分类
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
    
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 重载构造方法 从valuation-common中返回的result重新赋值
     * 
     * @param rtnResult 共通结果
     */
    public NPlannedAssetBaseInfoRecResult(NPlannedAssetBaseInfoCommonRecResult rtnResult) {
        this.setListValFinProduct(rtnResult.getListValFinProduct());
        this.setListValFinProductType(rtnResult.getListValFinProductType());
        this.setValInterflog(rtnResult.getValInterflog());
    }

    /**
     * 取得 标的类金融产品表
     * 
     * @return 标的类金融产品表
     */
    public List<FinProduct> getListValFinProduct() {
        return listValFinProduct;
    }

    /**
     * 设置 标的类金融产品表
     * 
     * @param listValFinProduct 标的类金融产品表
     */
    public void setListValFinProduct(List<FinProduct> listValFinProduct) {
        this.listValFinProduct = listValFinProduct;
    }
    
    /**
     * 取得 标的类金融产品表
     * 
     * @return 标的类金融产品表
     */
    public List<FinProductType> getListValFinProductType() {
        return listValFinProductType;
    }

    /**
     * 设置 标的类金融产品表
     * 
     * @param listValFinProduct 标的类金融产品表
     */
    public void setListValFinProductType(List<FinProductType> listValFinProductType) {
        this.listValFinProductType = listValFinProductType;
    }

    /**
     * 获取 日志
     * 
     * @return 日志.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    /**
     * 设置 日志
     * 
     * @param valInterflog 日志
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

}
