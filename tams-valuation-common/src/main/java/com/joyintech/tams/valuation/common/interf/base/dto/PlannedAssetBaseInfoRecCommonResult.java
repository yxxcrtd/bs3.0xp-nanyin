/*
 * 文件名：PlannedAssetBaseInfoForInterfRecProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.*;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ruanhongming
 * @version 1.0 2018年6月25日
 * @see PlannedAssetBaseInfoRecCommonResult
 * @since 1.0
 */
public class PlannedAssetBaseInfoRecCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标的类金融产品表
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();

    /**
     * 现金流信息表
     */
    private List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();

    /**
     * 现金流计算规则表
     */
    private List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();

    /**
     * 现金流支付规则表
     */
    private List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();

    /**
     * 现金流计划表
     */
    private List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
    
    /**
     * 基准信息
     */
    private List<MstBenchmarkInfo> listBenchmarkInfo = new ArrayList<MstBenchmarkInfo>();
    
    /**
     * 基准成分表
     */
    private List<MstBenchmarkCompo> listBenchmarkCompo = new ArrayList<MstBenchmarkCompo>();
    
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**add by wujie on 20210507 start**/
    private Map<String, Object> paramMap = new HashMap<String,Object>();

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**add by wujie on 20210507 end**/
    /**
     * 标的类金融产品扩展表
     */
    private List<FinProductAdd> listValFinProductExt = new ArrayList<FinProductAdd>();

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
     * 获取 listValCashCalcRule
     * @return listValCashCalcRule.
     */
    public List<FinCashCalcRule> getListValCashCalcRule() {
        return listValCashCalcRule;
    }

    
    /**
     * 设置 listValCashCalcRule
     * @param listValCashCalcRule listValCashCalcRule
     */
    public void setListValCashCalcRule(List<FinCashCalcRule> listValCashCalcRule) {
        this.listValCashCalcRule = listValCashCalcRule;
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
     * 获取 listBenchmarkInfo
     * @return listBenchmarkInfo.
     */
    public List<MstBenchmarkInfo> getListBenchmarkInfo() {
        return listBenchmarkInfo;
    }

    
    /**
     * 设置 listBenchmarkInfo
     * @param listBenchmarkInfo listBenchmarkInfo
     */
    public void setListBenchmarkInfo(List<MstBenchmarkInfo> listBenchmarkInfo) {
        this.listBenchmarkInfo = listBenchmarkInfo;
    }

    
    /**
     * 获取 listBenchmarkCompo
     * @return listBenchmarkCompo.
     */
    public List<MstBenchmarkCompo> getListBenchmarkCompo() {
        return listBenchmarkCompo;
    }

    
    /**
     * 设置 listBenchmarkCompo
     * @param listBenchmarkCompo listBenchmarkCompo
     */
    public void setListBenchmarkCompo(List<MstBenchmarkCompo> listBenchmarkCompo) {
        this.listBenchmarkCompo = listBenchmarkCompo;
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

    /**add by wujie on 20180822 start**/
    /**
     * 标的类金融产品表
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();

	public List<FinProductType> getListValFinProductType() {
		return listValFinProductType;
	}


	public void setListValFinProductType(List<FinProductType> listValFinProductType) {
		this.listValFinProductType = listValFinProductType;
	}
    
    /**add by wujie on 20180822 end**/

    public List<FinProductAdd> getListValFinProductExt() {
        return listValFinProductExt;
    }

    public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
        this.listValFinProductExt = listValFinProductExt;
    }
}
