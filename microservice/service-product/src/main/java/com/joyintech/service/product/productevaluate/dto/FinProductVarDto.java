package com.joyintech.service.product.productevaluate.dto;

import java.util.Date;

public class FinProductVarDto {
    /**
     * 产品名称
     */
    private String finprodName;
    /**
     * 产品代码
     */
    private String finprodId;
    /**
     * 金融产品类型
     */
    private String finprodType2;
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 处理模式
     */
    private String dealMode;
    /**
     * 起息日
     */
    private Date vdate;

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
}
