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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.MstBenchmarkCompo;
import com.joyintech.tams.common.base.entity.MstBenchmarkInfo;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 基础数据接口--债券
 * 
 * @author HuangJian
 * @version 1.0 2018年6月28日
 * @see SecBaseInfoRecProcessorRecCommonResult
 * @since 1.0
 */
public class SecBaseInfoRecProcessorRecCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标的类金融产品
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();

    /**
     * 金融产品分类
     */
    private List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();

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
     * 行权计划表
     */
    private List<FinExercisePlan>  listValExercisePlan = new ArrayList<FinExercisePlan>();
    
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
     * 获取 listValExercisePlan
     * @return listValExercisePlan
     */
    public List<FinExercisePlan> getListValExercisePlan() {
		return listValExercisePlan;
	}
    /**
     * 设置listValExercisePlan
     * @param listValExercisePlan
     */
	public void setListValExercisePlan(List<FinExercisePlan> listValExercisePlan) {
		this.listValExercisePlan = listValExercisePlan;
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
    
    /**add by wujie on 20180815 end**/
}
