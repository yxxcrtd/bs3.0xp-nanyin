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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.*;

/**
 * 债券回购标的
 * 
 * @author HuangJian
 * @version 1.0 2018年7月23日
 * @see RepoNStandardCommonResult
 * @since 1.0
 */
public class RepoNStandardCommonResult extends BaseEntity {

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
     * 质押品信息
     */
    private List<FinDealPledge> listFinDealPledge = new ArrayList<FinDealPledge>();
    
    /**
     * 交易类金融产品附属表
     */
    private List<FinTradeProductAdd> listFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();

    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 金融产品估值参数表
     * add by wujie on 20190420
     */
    private List<FinProductValuationPara> listFinProductValuationPara = new ArrayList<FinProductValuationPara>();





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
    
    

    public List<FinDealPledge> getListFinDealPledge() {
		return listFinDealPledge;
	}


	public void setListFinDealPledge(List<FinDealPledge> listFinDealPledge) {
		this.listFinDealPledge = listFinDealPledge;
	}



	/**
	 * @return the listFinTradeProductAdd
	 */
	public List<FinTradeProductAdd> getListFinTradeProductAdd() {
		return listFinTradeProductAdd;
	}


	/**
	 * @param listFinTradeProductAdd the listFinTradeProductAdd to set
	 */
	public void setListFinTradeProductAdd(List<FinTradeProductAdd> listFinTradeProductAdd) {
		this.listFinTradeProductAdd = listFinTradeProductAdd;
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



    public List<FinProductValuationPara> getListFinProductValuationPara() {
        return listFinProductValuationPara;
    }


    public void setListFinProductValuationPara(List<FinProductValuationPara> listFinProductValuationPara) {
        this.listFinProductValuationPara = listFinProductValuationPara;
    }


}
