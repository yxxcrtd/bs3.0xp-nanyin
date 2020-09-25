package com.joyintech.service.product.productevaluate.dto;

import com.joyintech.tams.common.base.entity.BokPositionPrd;

public class FinBokPositionPrdDto extends BokPositionPrd {

    private static final long serialVersionUID = -4278456708126143926L;

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
