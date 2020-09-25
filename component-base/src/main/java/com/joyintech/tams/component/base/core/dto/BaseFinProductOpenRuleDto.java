/*
 * 文件名：BaseFinProductOpenRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenRule;

/**
 * 开放规则组件dto
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年9月4日
 * @see BaseFinProductOpenRuleDto
 * @since 1.0
 */
public class BaseFinProductOpenRuleDto {
    /**
     * 现金流代码
     */
    private String cashId;
    /**
     * 开放类型，申购开放、赎回开放、申赎开放
     */
    private String openType;
    /**
     * 开放规则List
     */
    private List<FinOpenRule> finOpenRuleList;

    
    public String getCashId() {
        return cashId;
    }


    
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }


    
    public String getOpenType() {
        return openType;
    }


    
    public void setOpenType(String openType) {
        this.openType = openType;
    }


    public List<FinOpenRule> getFinOpenRuleList() {
        return finOpenRuleList;
    }
    
    public void setFinOpenRuleList(List<FinOpenRule> finOpenRuleList) {
        this.finOpenRuleList = finOpenRuleList;
    }
}
