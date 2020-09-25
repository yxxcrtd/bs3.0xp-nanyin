package com.joyintech.service.product.productplanning.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class HoldPositionDto {
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 币种
     */
    private String bCcy;
    /**
     * 持仓数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shartAmt;
    /**
     * 分支序号
     */
    private String branch;
    /**
     * 行内二级分类
     */
    private String type9;
    /**
     * 行内三级分类
     */
    private String type10;
    /**
     * 行内四级分类
     */
    private String type11;
    /**
     * 行内五级分类
     */
    private String type12;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getbCcy() {
        return bCcy;
    }

    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }

    public BigDecimal getShartAmt() {
        return shartAmt;
    }

    public void setShartAmt(BigDecimal shartAmt) {
        this.shartAmt = shartAmt;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public String getType10() {
        return type10;
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }

    public String getType11() {
        return type11;
    }

    public void setType11(String type11) {
        this.type11 = type11;
    }

    public String getType12() {
        return type12;
    }

    public void setType12(String type12) {
        this.type12 = type12;
    }
}
