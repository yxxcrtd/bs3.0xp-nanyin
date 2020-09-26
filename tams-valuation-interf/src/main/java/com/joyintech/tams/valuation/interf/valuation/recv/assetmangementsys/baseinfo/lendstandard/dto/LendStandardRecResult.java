/*
 * 文件名：LendStandardRecResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.lendstandard.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.common.interf.base.dto.LendStandardCommonResult;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年7月23日
 * @see LendStandardRecResult
 * @since 1.0
 */
public class LendStandardRecResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * @param result
     */
    public LendStandardRecResult(LendStandardCommonResult result) {
        this.listValFinProductType = result.getListValFinProductType();
        this.listValTradeFinProduct = result.getListValTradeFinProduct();
        this.valInterflog = result.getValInterflog();
        this.paramMap = result.getParamMap();
        /**add by wujie on 20180823 start**/
        this.setListValCashInfo(result.getListValCashInfo());
        this.setListValCashCalcRule(result.getListValCashCalcRule());
        this.setListValCashPayRule(result.getListValCashPayRule());
        this.setListValCashPlan(result.getListValCashPlan());
        /**add by wujie on 20180823 end**/
        /**add by chendijun on 20190429 start**/
        this.setListValFinProduct(result.getListValFinProduct());
        /**add by chendijun on 20190429 end**/
    }
    /**
     * 标的类金融产品
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();

    /**新增 标的类金融产品表 add by chendijun on 20190429 end**/

    /**
     * 交易类金融产品
     */
    private List<FinTradeProduct> listValTradeFinProduct = new ArrayList<FinTradeProduct>();

    /**
     * 金融产品分类表
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
    
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
     * 现金流计划表
     */
    List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();

    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 获取 listValTradeFinProduct
     * 
     * @return listValTradeFinProduct.
     */
    public List<FinTradeProduct> getListValTradeFinProduct() {
        return listValTradeFinProduct;
    }

    /**
     * 设置 listValTradeFinProduct
     * 
     * @param listValTradeFinProduct listValTradeFinProduct
     */
    public void setListValTradeFinProduct(List<FinTradeProduct> listValTradeFinProduct) {
        this.listValTradeFinProduct = listValTradeFinProduct;
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

    private Map<String,Object> paramMap;

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}	

	public List<FinCashInfo> getListValCashInfo() {
		return listValCashInfo;
	}

	public void setListValCashInfo(List<FinCashInfo> listValCashInfo) {
		this.listValCashInfo = listValCashInfo;
	}

	public List<FinCashCalcRule> getListValCashCalcRule() {
		return listValCashCalcRule;
	}

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

    /**add by wujie on 20180823 end**/
}
