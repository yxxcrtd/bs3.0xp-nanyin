/*
 * 文件名：BaseFinProductCashCalcRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinFloatCountingRule;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see ValBaseFinProductCashCountingRuleDto
 * @since 1.0
 */
public class ValBaseFinProductCashCountingRuleDto{

    /**
     * 
     */
    private List<FinFloatCountingRule> FinCashCountingRuleList;
    
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 生效日期
     */
    private Date effDate;
    
    
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
     * 获取 FinCashCountingRuleList
     * @return FinCashCountingRuleList.
     */
    public List<FinFloatCountingRule> getFinCashCountingRuleList() {
        return FinCashCountingRuleList;
    }

    
    /**
     * 设置 finCashCountingRuleList
     * @param finCashCountingRuleList finCashCountingRuleList
     */
    public void setFinCashCountingRuleList(List<FinFloatCountingRule> finCashCountingRuleList) {
        FinCashCountingRuleList = finCashCountingRuleList;
    }
    
    
    
    

}
