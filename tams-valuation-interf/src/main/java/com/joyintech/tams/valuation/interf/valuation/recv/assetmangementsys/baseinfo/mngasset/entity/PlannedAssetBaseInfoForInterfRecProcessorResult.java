/*
 * 文件名：PlannedAssetBaseInfoForInterfRecProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.common.interf.base.dto.PlannedAssetBaseInfoRecCommonResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ruanhongming
 * @version 1.0 2018年6月25日
 * @see PlannedAssetBaseInfoForInterfRecProcessorResult
 * @since 1.0
 */
public class PlannedAssetBaseInfoForInterfRecProcessorResult extends BaseEntity {

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
     * 金融产品扩展表
     */
    List<FinProductAdd> listValFinProductExt = new ArrayList<>();
    
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
    
    /**
     * 重载构造方法 从valuation-common中返回的result重新赋值
     * 
     * @param rtnResult 共通结果
     */
    public PlannedAssetBaseInfoForInterfRecProcessorResult(PlannedAssetBaseInfoRecCommonResult result) {
        this.setListValCashCalcRule(result.getListValCashCalcRule());
        this.setListValCashInfo(result.getListValCashInfo());
        this.setListValFinProduct(result.getListValFinProduct());
        this.setListValCashPayRule(result.getListValCashPayRule());
        this.setListValCashPlan(result.getListValCashPlan());
        this.setValInterflog(result.getValInterflog());
        this.setListBenchmarkInfo(result.getListBenchmarkInfo());
        this.setListBenchmarkCompo(result.getListBenchmarkCompo());
        /**add by wujie on 20180822 start**/
        this.setListValFinProductType(result.getListValFinProductType());
        /**add by wujie on 20180822 end**/
        /** 标的类金融产品扩展表 add by yanya on 20201222 start**/
        this.setListValFinProductExt(result.getListValFinProductExt());
        /** 标的类金融产品扩展表 add by yanya on 20201222 end**/
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
     * 取得 现金流信息表
     * 
     * @return 现金流信息表
     */
    public List<FinCashInfo> getListValCashInfo() {
        return listValCashInfo;
    }

    /**
     * 设置 现金流信息表
     * 
     * @param listValCashInfo 现金流信息表
     */
    public void setListValCashInfo(List<FinCashInfo> listValCashInfo) {
        this.listValCashInfo = listValCashInfo;
    }

    /**
     * 取得 现金流计算规则表
     * 
     * @return 现金流计算规则表
     */
    public List<FinCashCalcRule> getListValCashCalcRule() {
        return listValCashCalcRule;
    }

    /**
     * 设置 现金流计算规则表
     * 
     * @param listValCashCalcRule 现金流计算规则表
     */
    public void setListValCashCalcRule(List<FinCashCalcRule> listValCashCalcRule) {
        this.listValCashCalcRule = listValCashCalcRule;
    }

    /**
     * 取得 现金流支付规则表
     * 
     * @return 现金流支付规则表
     */
    public List<FinCashPayRule> getListValCashPayRule() {
        return listValCashPayRule;
    }

    /**
     * 设置 现金流支付规则表
     * 
     * @param listValCashPayRule 现金流支付规则表
     */
    public void setListValCashPayRule(List<FinCashPayRule> listValCashPayRule) {
        this.listValCashPayRule = listValCashPayRule;
    }
    
    /**
     * 取得 现金流计划表
     * 
     * @return 现金流计划表
     */
    public List<FinCashPlan> getListValCashPlan() {
        return listValCashPlan;
    }

    /**
     * 设置 现金流计划表
     * 
     * @param listValCashPlan 现金流计划表
     */
    public void setListValCashPlan(List<FinCashPlan> listValCashPlan) {
        this.listValCashPlan = listValCashPlan;
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

	public List<MstBenchmarkInfo> getListBenchmarkInfo() {
		return listBenchmarkInfo;
	}

	public void setListBenchmarkInfo(List<MstBenchmarkInfo> listBenchmarkInfo) {
		this.listBenchmarkInfo = listBenchmarkInfo;
	}

	public List<MstBenchmarkCompo> getListBenchmarkCompo() {
		return listBenchmarkCompo;
	}

	public void setListBenchmarkCompo(List<MstBenchmarkCompo> listBenchmarkCompo) {
		this.listBenchmarkCompo = listBenchmarkCompo;
	}
    
    /**add by wujie on 20180822 start**/
	/**
     * 金融产品分类
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();

	public List<FinProductType> getListValFinProductType() {
		return listValFinProductType;
	}

	public void setListValFinProductType(List<FinProductType> listValFinProductType) {
		this.listValFinProductType = listValFinProductType;
	}
	/**add by wujie on 20180822 end**/

	/** add by yanya on 20201222 金融产品扩展表增加 start**/
    public List<FinProductAdd> getListValFinProductExt() {
        return listValFinProductExt;
    }

    public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
        this.listValFinProductExt = listValFinProductExt;
    }

    /** add by yanya on 20201222 金融产品扩展表增加 end**/
}
