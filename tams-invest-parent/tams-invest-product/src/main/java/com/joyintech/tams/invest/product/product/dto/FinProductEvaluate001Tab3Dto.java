package com.joyintech.tams.invest.product.product.dto;

import com.joyintech.tams.common.base.entity.BokPositionPrd;

/**
 * 产品净值变现 table
 */
public class FinProductEvaluate001Tab3Dto extends BokPositionPrd {

    private static final long serialVersionUID = 8540383540861924759L;

    /**
     * 产品名称
     */
    private String finprodName;

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
}
