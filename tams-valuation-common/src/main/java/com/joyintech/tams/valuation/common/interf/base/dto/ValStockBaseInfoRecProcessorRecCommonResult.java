/*
 * 文件名：SecBaseInfoRecProcessorRecCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
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
 * 基础数据接口--股票
 * 
 * @author yanya
 * @version 1.0 2021-08-27
 * @see ValStockBaseInfoRecProcessorRecCommonResult
 * @since 1.0
 */
public class ValStockBaseInfoRecProcessorRecCommonResult extends BaseEntity {


    private static final long serialVersionUID = 4343862333125897063L;
    /**
     * 标的类金融产品
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();

    /**
     * 金融产品扩展表
     */
    List<FinProductAdd> listValFinProductExt = new ArrayList<>();

    /**
     * 金融产品分类
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();


    /**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();
    
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

    /**add by wujie on 20180815 start remark:含权债初始化现金流信息**/
    private Map<String,Object> paramMap;

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

    public List<FinProductAdd> getListValFinProductExt() {
        return listValFinProductExt;
    }

    public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
        this.listValFinProductExt = listValFinProductExt;
    }


}
