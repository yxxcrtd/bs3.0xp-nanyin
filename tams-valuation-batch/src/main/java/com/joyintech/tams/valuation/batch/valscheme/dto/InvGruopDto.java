/*
 * 文件名：InvGruopDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.dto;

import java.math.BigDecimal;

/**
 * 投资组合（计算平均到期日、剩余存续期）
 * 
 * @author weipeng
 * @version 1.0 2018年10月10日
 * @see InvGruopDto
 * @since 1.0
 */
public class InvGruopDto {

    /**
     * 剩余总期限（SUM(A.剩余期限*A.当日成本)）
     */
    private BigDecimal remainTermSum;

    /**
     * 剩余总存续期限（SUM(A.剩余存续期限*A.当日成本)）
     */
    private BigDecimal remainDurationSum;

    /**
     * 分母Sum（Sum(当日成本)）
     */
    private BigDecimal costSum;

    /**
     * 获取 剩余总期限
     * 
     * @return 剩余总期限.
     */
    public BigDecimal getRemainTermSum() {
        return remainTermSum;
    }

    /**
     * 设置 剩余总期限
     * 
     * @param remainTermSum 剩余总期限
     */
    public void setRemainTermSum(BigDecimal remainTermSum) {
        this.remainTermSum = remainTermSum;
    }

    /**
     * 获取 剩余总存续期限
     * 
     * @return 剩余总存续期限.
     */
    public BigDecimal getRemainDurationSum() {
        return remainDurationSum;
    }

    /**
     * 设置 剩余总存续期限
     * 
     * @param remainDurationSum 剩余总存续期限
     */
    public void setRemainDurationSum(BigDecimal remainDurationSum) {
        this.remainDurationSum = remainDurationSum;
    }

    /**
     * 获取 分母Sum
     * 
     * @return 分母Sum.
     */
    public BigDecimal getCostSum() {
        return costSum;
    }

    /**
     * 设置 分母Sum
     * 
     * @param costSum 分母Sum
     */
    public void setCostSum(BigDecimal costSum) {
        this.costSum = costSum;
    }

}
