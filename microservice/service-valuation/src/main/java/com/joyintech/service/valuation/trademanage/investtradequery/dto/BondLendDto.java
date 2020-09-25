/*
 * 文件名：BondLendDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年12月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.dto;

import java.math.BigDecimal;

/**
 * 债券借贷dto
 * 
 * @author niuqianyu
 * @version 1.0 2018年12月10日
 * @see BondLendDto
 * @since 1.0
 */
public class BondLendDto extends InvestTradeDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 计费基础
     */
    private String basis;

    /**
     * 交易金额
     */
    private BigDecimal cdAmt;

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 cdAmt
     * 
     * @return cdAmt.
     */
    public BigDecimal getCdAmt() {
        return cdAmt;
    }

    /**
     * 设置 cdAmt
     * 
     * @param cdAmt cdAmt
     */
    public void setCdAmt(BigDecimal cdAmt) {
        this.cdAmt = cdAmt;
    }

}
