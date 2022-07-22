package com.joyintech.tams.disclosure.information.report.dto;

import java.util.Date;

/**
 * 报表公用产品信息
 * zhengbaosheng
 */
public class ReportCommonProdDto {
    /**
     * 标题名：普通产品：产品名称
     *       分级产品：产品名称+（子产品代码）
     */
    private String titleName;
    /**
     * 产品登记编码
     */
    private String regCode;
    /**
     * 子代码或普通产品代码
     */
    private String prodId;
    /**
     * 父代码或普通产品代码
     */
    private String pprodId;
    /**
     * 成立日
     */
    private Date setDate;
    /**
     * 产品名称：若“是否是分级产品”为“是”，取产品名称+子产品名称；若“是否分级产品”为“否”取产品名称
     */
    private String prodName;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getPprodId() {
        return pprodId;
    }

    public void setPprodId(String pprodId) {
        this.pprodId = pprodId;
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}
