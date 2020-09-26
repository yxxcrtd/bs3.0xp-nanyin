/*
 * 文件名：AssetMangementRecResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.cplannedinfo.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductValuationPara;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.CPlannedAssetBaseInfoValCommonResult;

/**
 * 计划类货币型-基础数据接口Processor返回共通结果
 * @author zhangzhao
 * @version 1.0 2019年1月22日
 * @see CPlannedAssetBaseInfoProcessorResult
 * @since 1.0
 */
public class CPlannedAssetBaseInfoProcessorResult extends BaseEntity {

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
     * 现金流支付规则表
     */
    private List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
    /**
     * 现金流计划表
     */
    private List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();
    
    /**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();
    

    /**
     * 重载构造方法 从valuation-common中返回的result重新赋值
     * 
     * @param rtnResult 共通结果
     */
    public CPlannedAssetBaseInfoProcessorResult(CPlannedAssetBaseInfoValCommonResult result) {
        this.setListValFinProduct(result.getListValFinProduct());
        this.setListValFinProductExt(result.getListValFinProductExt());
        this.setListValFinProductType(result.getListValFinProductType());
        this.setListValFinProductValuationPara(result.getListValFinProductValuationPara());
        this.setListValCashInfo(result.getListValCashInfo());
        this.setListValCashPayRule(result.getListValCashPayRule());
        this.setListValCashPlan(result.getListValCashPlan());
        this.setListValInterflog(result.getListValInterflog());
        this.setParamMap(result.getParamMap());
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
	 * 获取 listValFinProductValuationPara
	 * @return listValFinProductValuationPara.
	 */
	public List<FinProductValuationPara> getListValFinProductValuationPara() {
		return listValFinProductValuationPara;
	}

	/**
	 * 设置 listValFinProductValuationPara
	 * @param listValFinProductValuationPara listValFinProductValuationPara
	 */
	public void setListValFinProductValuationPara(List<FinProductValuationPara> listValFinProductValuationPara) {
		this.listValFinProductValuationPara = listValFinProductValuationPara;
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

	 private Map<String,Object> paramMap = new HashMap<String,Object>();


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
		
   
}
