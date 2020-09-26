/*
 * 文件名：PenaltyInterestRecResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.penaltyinterest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.common.interf.base.dto.PenaltyInterestCommonResult;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhangquan
 * @version 1.0 2019年12月27日
 * @see PenaltyInterestRecResult
 * @since 1.0
 */
public class PenaltyInterestRecResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 交易类金融产品表
     */
    private List<FinTradeProduct> listValTradeFinProduct = new ArrayList<FinTradeProduct>();

    /**
     * 交易类金融产品附属表
     */
    private List<FinTradeProductAdd> listValTradeFinProductAdd = new ArrayList<FinTradeProductAdd>();

    /**
     * 现金流信息
     */
    private List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();

    /**
     * 现金流计算规则
     */
    private List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();

    /**
     * 现金流计划表
     */
    private List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();

    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 用于存放去重数据筛选条件
     */
    private Map<String, Object> paramMap;

    /**
     * @param result
     */
    public PenaltyInterestRecResult(PenaltyInterestCommonResult result) {
        this.listValTradeFinProduct = result.getListValTradeFinProduct();
        this.listValTradeFinProductAdd = result.getListValTradeFinProductAdd();
        this.valInterflog = result.getValInterflog();
        this.paramMap = result.getParamMap();
        this.listValCashInfo = result.getListValCashInfo();
        this.listValCashCalcRule = result.getListValCashCalcRule();
        this.listValCashPlan = result.getListValCashPlan();
    }

    /**
     * 获取 交易类金融产品表
     * 
     * @return 交易类金融产品表
     */
    public List<FinTradeProduct> getListValTradeFinProduct() {
        return listValTradeFinProduct;
    }

    /**
     * 设置 交易类金融产品表
     * 
     * @param listValTradeFinProduct 交易类金融产品表
     */
    public void setListValTradeFinProduct(List<FinTradeProduct> listValTradeFinProduct) {
        this.listValTradeFinProduct = listValTradeFinProduct;
    }

    /**
     * 获取 现金流信息
     * 
     * @return 现金流信息
     */
    public List<FinCashInfo> getListValCashInfo() {
        return listValCashInfo;
    }

    /**
     * 设置 现金流信息
     * 
     * @param listValCashInfo 现金流信息
     */
    public void setListValCashInfo(List<FinCashInfo> listValCashInfo) {
        this.listValCashInfo = listValCashInfo;
    }

    /**
     * 获取 现金流计算规则
     * 
     * @return 现金流计算规则
     */
    public List<FinCashCalcRule> getListValCashCalcRule() {
        return listValCashCalcRule;
    }

    /**
     * 设置 现金流计算规则
     * 
     * @param listValCashCalcRule 现金流计算规则
     */
    public void setListValCashCalcRule(List<FinCashCalcRule> listValCashCalcRule) {
        this.listValCashCalcRule = listValCashCalcRule;
    }

    /**
     * 获取 现金流计划表
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
     * 获取 交易类金融产品附属表
     * 
     * @return 交易类金融产品附属表
     */
    public List<FinTradeProductAdd> getListValTradeFinProductAdd() {
        return listValTradeFinProductAdd;
    }

    /**
     * 设置 交易类金融产品附属表
     * 
     * @param listValTradeFinProductAdd 交易类金融产品附属表
     */
    public void setListValTradeFinProductAdd(List<FinTradeProductAdd> listValTradeFinProductAdd) {
        this.listValTradeFinProductAdd = listValTradeFinProductAdd;
    }

    /**
     * 设置 日志
     * 
     * @param valInterflog 日志
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    /**
     * 获取 日志
     * 
     * @return 日志
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    /**
     * 获取 用于存放去重数据筛选条件
     * 
     * @return 用于存放去重数据筛选条件
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    /**
     * 设置 用于存放去重数据筛选条件
     * 
     * @param paramMap 用于存放去重数据筛选条件
     */
    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
