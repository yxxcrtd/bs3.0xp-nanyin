/*
 * 文件名：AddTaxRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 附加税税则Dto
 * @author weipeng
 * @version 1.0 2018年8月7日
 * @see AddTaxRuleDto
 * @since 1.0
 */
public class AddTaxRuleDto {

    /**
     * 附加税类型
     */
    private String addTaxType;

    /**
     * 应税税率
     */
    private BigDecimal taxRate;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 获取 附加税类型
     * 
     * @return 附加税类型
     */
    public String getAddTaxType() {
        return addTaxType;
    }

    /**
     * 设置 附加税类型
     * 
     * @param addTaxType 附加税类型
     */
    public void setAddTaxType(String addTaxType) {
        this.addTaxType = addTaxType;
    }

    /**
     * 获取 应税税率
     * 
     * @return 应税税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 设置 应税税率
     * 
     * @param 应税税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 获取 生效日
     * 
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * 
     * @param effDate 生效日
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

}
