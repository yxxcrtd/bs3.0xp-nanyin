/*
 * 文件名：BaseFinProductCashCalcRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see BaseFinProductCashCalcRuleDto
 * @since 1.0
 */
public class BaseFinProductCashCalcRuleDto{

    /**
     * 
     */
    private List<FinCashCalcRule> FinCashCalcRuleList;
    
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 生效日期
     */
    private Date effDate;
    
    /**
     * 是否初始利率
     */
    private String isInitial;
    
    
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
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }


    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }


    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 分支序号
     */
    private Long branch;

    
    /**
     * 获取 finCashCalcRuleList
     * @return finCashCalcRuleList.
     */
    public List<FinCashCalcRule> getFinCashCalcRuleList() {
        return FinCashCalcRuleList;
    }

    
    /**
     * 设置 finCashCalcRuleList
     * @param finCashCalcRuleList finCashCalcRuleList
     */
    public void setFinCashCalcRuleList(List<FinCashCalcRule> finCashCalcRuleList) {
        FinCashCalcRuleList = finCashCalcRuleList;
    }



    
    /**
     * 获取 isInitial
     * @return isInitial.
     */
    public String getIsInitial() {
        return isInitial;
    }



    
    /**
     * 设置 isInitial
     * @param isInitial isInitial
     */
    public void setIsInitial(String isInitial) {
        this.isInitial = isInitial;
    }
    
    
    
    

}
