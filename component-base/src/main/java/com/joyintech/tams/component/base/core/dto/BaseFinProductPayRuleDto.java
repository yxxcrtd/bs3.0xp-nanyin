/*
 * 文件名：BaseFinProductPayRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;

/**
 * 现金流支付规则组件
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月3日
 * @see BaseFinProductPayRuleDto
 * @since 1.0
 */
public class BaseFinProductPayRuleDto {

    /**
     * 现金流支付规则表entity
     */
    private List<FinCashPayRule> finCashPayRuleList;
    /**
     * 现金流ID
     */
    private String cashFlowId;
    /**
     * 生效日
     */
    private Date effectivDay;
    
    /**
     * 获取 现金流支付规则表entity
     * @return finCashPayRuleList.
     */
    public List<FinCashPayRule> getFinCashPayRuleList() {
        return finCashPayRuleList;
    }
    
    /**
     * 设置 现金流支付规则表entity
     * @param finCashPayRuleList finCashPayRuleList
     */
    public void setFinCashPayRuleList(List<FinCashPayRule> finCashPayRuleList) {
        this.finCashPayRuleList = finCashPayRuleList;
    }
    
    /**
     * 获取 现金流ID
     * @return cashFlowId.
     */
    public String getCashFlowId() {
        return cashFlowId;
    }
    
    /**
     * 设置 现金流ID
     * @param cashFlowId cashFlowId
     */
    public void setCashFlowId(String cashFlowId) {
        this.cashFlowId = cashFlowId;
    }
    
    /**
     * 获取 生效日
     * @return effectivDay.
     */
    public Date getEffectivDay() {
        return effectivDay;
    }
    
    /**
     * 设置 生效日
     * @param effectivDay effectivDay
     */
    public void setEffectivDay(Date effectivDay) {
        this.effectivDay = effectivDay;
    }
    
    
}
