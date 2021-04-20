/*
 * 文件名：EtrDealDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.background.component.common.pack.common.validation.annotation.NotEmpty;

/**
 * 指令成交明细Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrDealDto
 * @since 1.0
 */
public class EtrDealDto {

    /**
     * 指令编号
     */
    private String entrustId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 完成日期
     */
    private Date finishDate;

    /**
     * 完成额度
     */
    private BigDecimal entFinishAmt;

    /**
     * 取得 指令编号
     * 
     * @return 指令编号
     */
    @NotEmpty(fieldName = "EtrComponent.entrustId")
    public String getEntrustId() {
        return this.entrustId;
    }

    /**
     * 设置 指令编号
     * 
     * @param entrustId 指令编号
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 取得 交易编号
     * 
     * @return 交易编号
     */
    public String getTradeId() {
        return this.tradeId;
    }

    /**
     * 设置 交易编号
     * 
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 取得 完成日期
     * 
     * @return 完成日期
     */
    public Date getFinishDate() {
        return this.finishDate;
    }

    /**
     * 设置 完成日期
     * 
     * @param finishDate 完成日期
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 取得 完成额度
     * 
     * @return 完成额度
     */
    public BigDecimal getEntFinishAmt() {
        return this.entFinishAmt;
    }

    /**
     * 设置 完成额度
     * 
     * @param entFinishAmt 完成额度
     */
    public void setEntFinishAmt(BigDecimal entFinishAmt) {
        this.entFinishAmt = entFinishAmt;
    }

}
