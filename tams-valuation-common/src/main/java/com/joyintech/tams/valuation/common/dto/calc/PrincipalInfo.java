/*
 * 文件名：PrincipalInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.dto.calc;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 本金列表
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see PrincipalInfo
 * @since 1.0
 */
public class PrincipalInfo {
    /**
     * 变动日
     */
    private Date changeDate;

    /**
     * 本金金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmount;

    /**
     * 获取 变动日
     * 
     * @return 变动日.
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 设置 变动日
     * 
     * @param changeDate 变动日
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 获取 本金金额
     * 
     * @return 本金金额.
     */
    public BigDecimal getPrinAmount() {
        return prinAmount;
    }

    /**
     * 设置 本金金额
     * 
     * @param prinAmount 本金金额
     */
    public void setPrinAmount(BigDecimal prinAmount) {
        this.prinAmount = prinAmount;
    }
}
