/*
 * 文件名：EtrInvestInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.component.base.entrust.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 委托完成额度dto 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author huwei
 * @version 1.0 2018年8月31日
 * @see BaseEtrFinishAmtDto
 * @since 1.0
 */
public class BaseEtrFinishAmtDto {

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 总完成额度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal sumFinishAmt;

    /**
     * 总交易完成度%
     */
    private BigDecimal sumFinishPercent;

    /**
     * 取得 委托代码
     *
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     *
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 完成额度
     *
     * @return 完成额度
     */
    public BigDecimal getSumFinishAmt() {
        return sumFinishAmt;
    }

    /**
     * 设置 完成额度
     *
     * @param sumFinishAmt 完成额度
     */
    public void setSumFinishAmt(BigDecimal sumFinishAmt) {
        this.sumFinishAmt = sumFinishAmt;
    }

    /**
     * 获取 sumFinishPercent
     *
     * @return sumFinishPercent.
     */
    public BigDecimal getSumFinishPercent() {
        return sumFinishPercent;
    }

    /**
     * 设置 sumFinishPercent
     *
     * @param sumFinishPercent sumFinishPercent
     */
    public void setSumFinishPercent(BigDecimal sumFinishPercent) {
        this.sumFinishPercent = sumFinishPercent;
    }

}
