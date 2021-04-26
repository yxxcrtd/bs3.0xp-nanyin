package com.joyintech.tams.settle.batch.datagather.dto;

import com.joyintech.tams.common.base.entity.FinTradeProduct;

/**
 * 交易类金融信息DTO
 * @author 何阳阳
 * @date 2020/7/29
 * @see FinTradeProductDto
 * @since 1.0
 */
public class FinTradeProductDto extends FinTradeProduct {
    /**
     * 金融产品分类6（回购类型）
     */
    private String finprodType6;

    /**
     * 金融产品分类7（回购交易方向）
     */
    private String finprodType7;

    /**
     * 金融产品分类8（投管资产业务类型）
     */
    private String finprodType8;

    public String getFinprodType6() {
        return finprodType6;
    }

    public void setFinprodType6(String finprodType6) {
        this.finprodType6 = finprodType6;
    }

    public String getFinprodType7() {
        return finprodType7;
    }

    public void setFinprodType7(String finprodType7) {
        this.finprodType7 = finprodType7;
    }

    public String getFinprodType8() {
        return finprodType8;
    }

    public void setFinprodType8(String finprodType8) {
        this.finprodType8 = finprodType8;
    }
}
