/*
 * 文件名：ChannelBaseInfoProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.channel.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductValuationPara;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelBaseInfoCommonResult;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2019年3月12日
 * @see ChannelBaseInfoProcessorResult
 * @since 1.0
 */
public class ChannelBaseInfoProcessorResult extends BaseEntity {

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
     * 金融产品估值参数表
     */
    private List<FinProductValuationPara> listValFinProductValuationPara = new ArrayList<FinProductValuationPara>();

    /**
     * 现金流信息表
     */
    private List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();

    /**
     * 现金流计算规则表
     */
    private List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();

    /**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();
    /**
     * 通道代码
     */
    private List<String> tdlist;

    /**
     * 获取 list
     * 
     * @return list.
     */
    public List<String> getTdlist() {
        return tdlist;
    }

    /**
     * 设置 list
     * 
     * @param list list
     */
    public void setTdlist(List<String> tdlist) {
        this.tdlist = tdlist;
    }

    /**
     * 获取 listValFinProduct
     * 
     * @return listValFinProduct.
     */
    public List<FinProduct> getListValFinProduct() {
        return listValFinProduct;
    }

    /**
     * 设置 listValFinProduct
     * 
     * @param listValFinProduct listValFinProduct
     */
    public void setListValFinProduct(List<FinProduct> listValFinProduct) {
        this.listValFinProduct = listValFinProduct;
    }

    /**
     * 获取 listValFinProductExt
     * 
     * @return listValFinProductExt.
     */
    public List<FinProductAdd> getListValFinProductExt() {
        return listValFinProductExt;
    }

    /**
     * 设置 listValFinProductExt
     * 
     * @param listValFinProductExt listValFinProductExt
     */
    public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
        this.listValFinProductExt = listValFinProductExt;
    }

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
     * 获取 listValFinProductValuationPara
     * 
     * @return listValFinProductValuationPara.
     */
    public List<FinProductValuationPara> getListValFinProductValuationPara() {
        return listValFinProductValuationPara;
    }

    /**
     * 设置 listValFinProductValuationPara
     * 
     * @param listValFinProductValuationPara listValFinProductValuationPara
     */
    public void setListValFinProductValuationPara(List<FinProductValuationPara> listValFinProductValuationPara) {
        this.listValFinProductValuationPara = listValFinProductValuationPara;
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
     * 获取 listValInterflog
     * 
     * @return listValInterflog.
     */
    public List<ValInterflog> getListValInterflog() {
        return listValInterflog;
    }

    /**
     * 设置 listValInterflog
     * 
     * @param listValInterflog listValInterflog
     */
    public void setListValInterflog(List<ValInterflog> listValInterflog) {
        this.listValInterflog = listValInterflog;
    }

    private Map<String, Object> paramMap = new HashMap<String, Object>();

    /**
     * @return the paramMap
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    /**
     * @param paramMap the paramMap to set
     */
    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 
     */
    public ChannelBaseInfoProcessorResult(ChannelBaseInfoCommonResult result) {
        this.setListValCashCalcRule(result.getListValCashCalcRule());
        this.setListValCashInfo(result.getListValCashInfo());
        this.setListValFinProduct(result.getListValFinProduct());
        this.setListValFinProductExt(result.getListValFinProductExt());
        this.setListValFinProductType(result.getListValFinProductType());
        this.setListValFinProductValuationPara(result.getListValFinProductValuationPara());
        this.setListValInterflog(result.getListValInterflog());
        this.setTdlist(result.getTdlist());
        this.setParamMap(result.getParamMap());
    }

}
