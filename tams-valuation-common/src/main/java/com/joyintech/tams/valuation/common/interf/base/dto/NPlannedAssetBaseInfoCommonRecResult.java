/*
 * 文件名：AssetMangementRecResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 计划类资产（净值型）基础信息Processor返回共通结果
 * 
 * @author chufengjie
 * @version 1.0 2018年8月3日
 * @see NPlannedAssetBaseInfoCommonRecResult
 * @since 1.0
 */
public class NPlannedAssetBaseInfoCommonRecResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标的类金融产品表
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
    
    /**
     * 标的类金融产品表
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();

    /*
     * 账套信息初始化Result
     */
    private List<BooksetInitResult> booksetInitResultList=new ArrayList<BooksetInitResult>();
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    

    public NPlannedAssetBaseInfoCommonRecResult() {

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
     * 设置 金融产品分类表
     * 
     * @param listValFinProductType 金融产品分类表
     */
    public void setListValFinProductType(List<FinProductType> listValFinProductType) {
        this.listValFinProductType = listValFinProductType;
    }
    
    /**
     * 取得 金融产品分类表
     * 
     * @return 金融产品分类表
     */
    public List<FinProductType> getListValFinProductType() {
        return listValFinProductType;
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

    
    /**
     * 获取 booksetInitResultList
     * @return booksetInitResultList.
     */
    public List<BooksetInitResult> getBooksetInitResultList() {
        return booksetInitResultList;
    }

    
    /**
     * 设置 booksetInitResultList
     * @param booksetInitResultList booksetInitResultList
     */
    public void setBooksetInitResultList(List<BooksetInitResult> booksetInitResultList) {
        this.booksetInitResultList = booksetInitResultList;
    }

}
