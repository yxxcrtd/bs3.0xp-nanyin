package com.joyintech.service.valuation.tradecostmanagement.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 标的类金融产品 Dto
 * @Author wangXiaoYong
 * @create 2019/11/19 0019
 */
public class TradeCostFinProductDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // 金融产品代码
    private String finProdId;

    // 金融产品类型(估值核算)
    private String finProdType;

    // 投资组合代码
    private String protfolioId;

    public String getFinProdId() {
        return finProdId;
    }

    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    public String getFinProdType() {
        return finProdType;
    }

    public void setFinProdType(String finProdType) {
        this.finProdType = finProdType;
    }

    public String getProtfolioId() {
        return protfolioId;
    }

    public void setProtfolioId(String protfolioId) {
        this.protfolioId = protfolioId;
    }

    public TradeCostFinProductDto() {
    }

    public TradeCostFinProductDto(String finProdId, String finProdType, String protfolioId) {
        this.finProdId = finProdId;
        this.finProdType = finProdType;
        this.protfolioId = protfolioId;
    }

}
