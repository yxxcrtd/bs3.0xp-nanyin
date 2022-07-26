
package com.joyintech.tams.disclosure.information.report.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品要素调整公告费用优惠明细
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2022年07月14日
 * @see ReportProdadjDto
 * @since 1.0
 */
public class ReportProdadjDto extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 调整要素
     */
    private String adjField;
    /**
     * 调整前
     */
    private String preAdjust;
    /**
     * 调整后
     */
    private String afterAdjust;
    /**
     * 生效时间
     */
    private Date effDate;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getAdjField() {
        return adjField;
    }

    public void setAdjField(String adjField) {
        this.adjField = adjField;
    }

    public String getPreAdjust() {
        return preAdjust;
    }

    public void setPreAdjust(String preAdjust) {
        this.preAdjust = preAdjust;
    }

    public String getAfterAdjust() {
        return afterAdjust;
    }

    public void setAfterAdjust(String afterAdjust) {
        this.afterAdjust = afterAdjust;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
}
