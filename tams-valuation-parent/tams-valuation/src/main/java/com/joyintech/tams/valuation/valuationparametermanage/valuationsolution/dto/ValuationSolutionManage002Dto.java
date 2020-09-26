/*
 * 文件名：ValuationSolutionManage001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokValSchemeDetail;
import com.joyintech.tams.constant.DictCode;


public class ValuationSolutionManage002Dto extends BokValSchemeDetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3599295403442172889L;

    /**
     * 交易市场
     */
    @SuppressWarnings("unused")
    private String tradeMarketDesc;
    
    /**
     * 证券品种
     */
    @SuppressWarnings("unused")
    private String secTypeDesc;

    /**
     * 证券细分类
     */
    @SuppressWarnings("unused")
    private String secDetailTypeDesc;

    /**
     * 投资目的
     */
    @SuppressWarnings("unused")
    private String invAimDesc;

    /**
     * 估值方法
     */
    @SuppressWarnings("unused")
    private String valuationMethodDesc;

    /**
     * 估值日期
     */
    @SuppressWarnings("unused")
    private String valuationDateDesc;
    /**
     * 估值市价
     */
    @SuppressWarnings("unused")
    private String valuationPriceDesc;
    
    public String getValuationPriceDesc() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值市价, this.getValuationPrice());
    }


    
    public void setValuationPriceDesc(String valuationPriceDesc) {
        this.valuationPriceDesc = valuationPriceDesc;
    }


    public String getTradeMarketDesc() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值交易市场, this.getTradeMarket());
    }

    
    public void setTradeMarketDesc(String tradeMarketDesc) {
        this.tradeMarketDesc = tradeMarketDesc;
    }

    
    public String getSecTypeDesc() {
        return SysDataUtil.getNameFromDataDict(DictCode.证券品种, this.getSecType());
    }

    
    public void setSecTypeDesc(String secTypeDesc) {
        this.secTypeDesc = secTypeDesc;
    }

    
    public String getSecDetailTypeDesc() {
        return SysDataUtil.getNameFromDataDict(DictCode.证券细分类, this.getSecDetailType());
    }

    
    public void setSecDetailTypeDesc(String secDetailTypeDesc) {
        this.secDetailTypeDesc = secDetailTypeDesc;
    }

    
    public String getInvAimDesc() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, this.getInvAim());
    }

    
    public void setInvAimDesc(String invAimDesc) {
        this.invAimDesc = invAimDesc;
    }

    
    public String getValuationMethodDesc() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值方法, this.getValuationMethod());
    }

    
    public void setValuationMethodDesc(String valuationMethodDesc) {
        this.valuationMethodDesc = valuationMethodDesc;
    }


    public String getValuationDateDesc() {
        return SysDataUtil.getNameFromDataDict(DictCode.估值日期, this.getValuationDate());
    }


    public void setValuationDateDesc(String valuationDateDesc) {
        this.valuationDateDesc = valuationDateDesc;
    }


}