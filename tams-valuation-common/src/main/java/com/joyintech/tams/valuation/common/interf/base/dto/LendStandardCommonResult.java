/*
 * 文件名：LendStandardCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 拆借类资产-基础数据接口模板处理返回共通结果
 * 
 * @author HuangJian
 * @version 1.0 2018年7月23日
 * @see LendStandardCommonResult
 * @since 1.0
 */
public class LendStandardCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
	 * 标的类金融产品
	 */
	private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();

	/**
	 * 金融产品扩展表
	 */
	List<FinProductAdd> listValFinProductExt = new ArrayList<>();

	/**
	 * 现金流利率信息表
	 */
	private List<FinRateInfo> listValCashIntInfo = new ArrayList<FinRateInfo>();

	/**
     * 获取 listValTradeFinProduct
     * @return listValTradeFinProduct.
     */
    public List<FinTradeProduct> getListValTradeFinProduct() {
        return listValTradeFinProduct;
    }

    
    /**
     * 设置 listValTradeFinProduct
     * @param listValTradeFinProduct listValTradeFinProduct
     */
    public void setListValTradeFinProduct(List<FinTradeProduct> listValTradeFinProduct) {
        this.listValTradeFinProduct = listValTradeFinProduct;
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
     * 用于存放去重数据筛选条件
     */
    private Map<String,Object> paramMap;

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**add by wujie on 20180823 start**/

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
	 * 获取 serialversionuid
	 * @return serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<FinProduct> getListValFinProduct() {
		return listValFinProduct;
	}


	public void setListValFinProduct(List<FinProduct> listValFinProduct) {
		this.listValFinProduct = listValFinProduct;
	}
    
    /**add by wujie on 20180823 end**/

	/**  add by sunshuaishuai on 20210322 start**/
	public List<FinProductAdd> getListValFinProductExt() {
		return listValFinProductExt;
	}

	public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
		this.listValFinProductExt = listValFinProductExt;
	}

	// 现金流利率信息表
	public List<FinRateInfo> getListValCashIntInfo() {
		return listValCashIntInfo;
	}

	public void setListValCashIntInfo(List<FinRateInfo> listValCashIntInfo) {
		this.listValCashIntInfo = listValCashIntInfo;
	}
	/**  add by sunshuaishuai on 20210322 end**/
}
