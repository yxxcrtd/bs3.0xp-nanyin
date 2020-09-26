/*
 * 文件名：TargetPerfRewardCalRule.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.util.Date;
import java.util.List;

/**
 * 业绩报酬计算规则 - 实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月25日
 * @see TargetPerfRewardCalRule
 * @since 1.0
 */
public class TargetPerfRewardCalRule {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流一级类型
     */
    private String cashType1;

    /**
     * 现金流二级类型
     */
    private String cashType2;
    /**
     * 分支序号
     */
    private int branch;
    /**
     * 生效日期
     */
    private Date effDate;
        
    /**
     * 业绩基准规则
     */
    private String baseRule;
    
    /**
     * 资产净值日类型
     */
    private String astVdateType;
    /**
     * 生效规则
     */
    private String actRule;
    /**
     * 业绩报酬规则 对应的 业绩报酬规则明细
     */
    private List<TargetPerfRewardCalRuleDetail> targetPerfRewardCalRuleDetailList;
    
    /**
     * 计息基础
     */
    private String basis;

    /**
     * 基数类型
     */
    private String baseType;
    /**
     * 基数日期类型
     */
    private String baseDateType;
    
    
    public String getBasis() {
		return basis;
	}


	public void setBasis(String basis) {
		this.basis = basis;
	}


	public String getBaseType() {
		return baseType;
	}


	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}


	public String getBaseDateType() {
		return baseDateType;
	}


	public void setBaseDateType(String baseDateType) {
		this.baseDateType = baseDateType;
	}


	/**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    
    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    
    /**
     * 获取 cashType1
     * @return cashType1.
     */
    public String getCashType1() {
        return cashType1;
    }

    
    /**
     * 设置 cashType1
     * @param cashType1 cashType1
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    
    /**
     * 获取 cashType2
     * @return cashType2.
     */
    public String getCashType2() {
        return cashType2;
    }

    
    /**
     * 设置 cashType2
     * @param cashType2 cashType2
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    
    /**
     * 获取 effDate
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }

    
    /**
     * 设置 effDate
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }


    
    
    /**
     * 获取 branch
     * @return branch.
     */
    public int getBranch() {
        return branch;
    }


    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(int branch) {
        this.branch = branch;
    }


    
    /**
     * 获取 baseRule
     * @return baseRule.
     */
    public String getBaseRule() {
        return baseRule;
    }


    
    /**
     * 设置 baseRule
     * @param baseRule baseRule
     */
    public void setBaseRule(String baseRule) {
        this.baseRule = baseRule;
    }


    
    /**
     * 获取 astVdateType
     * @return astVdateType.
     */
    public String getAstVdateType() {
        return astVdateType;
    }


    
    /**
     * 设置 astVdateType
     * @param astVdateType astVdateType
     */
    public void setAstVdateType(String astVdateType) {
        this.astVdateType = astVdateType;
    }


    
    /**
     * 获取 actRule
     * @return actRule.
     */
    public String getActRule() {
        return actRule;
    }


    
    /**
     * 设置 actRule
     * @param actRule actRule
     */
    public void setActRule(String actRule) {
        this.actRule = actRule;
    }


    /**
     * 获取 targetPerfRewardCalRuleDetailList
     * @return targetPerfRewardCalRuleDetailList.
     */
    public List<TargetPerfRewardCalRuleDetail> getTargetPerfRewardCalRuleDetailList() {
        return targetPerfRewardCalRuleDetailList;
    }


    
    /**
     * 设置 targetPerfRewardCalRuleDetailList
     * @param targetPerfRewardCalRuleDetailList targetPerfRewardCalRuleDetailList
     */
    public void setTargetPerfRewardCalRuleDetailList(List<TargetPerfRewardCalRuleDetail> targetPerfRewardCalRuleDetailList) {
        this.targetPerfRewardCalRuleDetailList = targetPerfRewardCalRuleDetailList;
    }

}
