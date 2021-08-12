package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto;

import com.joyintech.framework.base.BaseEntity;

public class FinProductDto extends BaseEntity {

    private static final long serialVersionUID = 2370080076630966437L;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
}
