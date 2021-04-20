/*
 * 文件名：FinTradeProductDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.common.dto;

import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.tams.common.base.entity.FinTradeProduct;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author 刘二通
 * @version 1.0 2018年11月1日
 * @see FinTradeProductDto
 * @since 1.0
 */
public class FinTradeProductDto extends FinTradeProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 取得 计息基础
     *
     * @return 计息基础
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.basis")
    @Override
    public String getBasis() {
        return super.getBasis();
    }

    /**
     * 取得 起息日
     *
     * @return 起息日
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.vdate")
    @Override
    public Date getVdate() {
        return super.getVdate();
    }

    /**
     * 取得 到期日
     *
     * @return 到期日
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.mdate")
    @Override
    public Date getMdate() {
        return super.getMdate();
    }

    /**
     * 取得 期限(天)
     *
     * @return 期限(天)
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.term")
    @Override
    public Long getTerm() {
        return super.getTerm();
    }

    /**
     * 取得 交易日历
     *
     * @return 交易日历
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.calendarId")
    @Override
    public String getCalendarId() {
        return super.getCalendarId();
    }

    /**
     * 取得 交易币种
     *
     * @return 交易币种
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.ccy")
    @Override
    public String getCcy() {
        return super.getCcy();
    }

    /**
     * 取得 名义本金 画面.本金金额
     *
     * @return 名义本金
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.prin")
    @Amount(maxIntBit = 28, fieldName = "TrdLendInvestTrade002.prin")
    @Override
    public BigDecimal getPrin() {
        return super.getPrin();
    }

    /**
     * 取得 固定利率
     *
     * @return 固定利率
     */
    @NotEmpty(fieldName = "TrdLendInvestTrade002.intRate")
    @Rate(fieldName = "TrdLendInvestTrade002.intRate")
    @Override
    public BigDecimal getIntRate() {
        return super.getIntRate();
    }
}
