package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.math.BigDecimal;

public class PrdBalance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 真实产品编码
     */
    private String realPrdId;

    /**
     * 指标项编号
     */
    private String subjectId;

    /**
     * 资产指标名称
     */
    private String subjectName;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 期末余额
     */
    private BigDecimal endBal;

    /**
     * 年初余额
     */
    private BigDecimal beginBal;

    /**
     * 业务日期
     */
    private String busiDate;

    /**
     * 负债指标名称
     */
    private String liaSubjectName;

    /**
     * 负债期末余额
     */
    private BigDecimal liaEndBal;

    /**
     * 负债年初余额负债年初余额
     */
    private BigDecimal liaBeginBal;

    public String getRealPrdId() {
        return realPrdId;
    }

    public void setRealPrdId(String realPrdId) {
        this.realPrdId = realPrdId == null ? null : realPrdId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    public BigDecimal getEndBal() {
        return endBal;
    }

    public void setEndBal(BigDecimal endBal) {
        this.endBal = endBal;
    }

    public BigDecimal getBeginBal() {
        return beginBal;
    }

    public void setBeginBal(BigDecimal beginBal) {
        this.beginBal = beginBal;
    }

    public String getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate == null ? null : busiDate.trim();
    }

    public String getLiaSubjectName() {
        return liaSubjectName;
    }

    public void setLiaSubjectName(String liaSubjectName) {
        this.liaSubjectName = liaSubjectName == null ? null : liaSubjectName.trim();
    }

    public BigDecimal getLiaEndBal() {
        return liaEndBal;
    }

    public void setLiaEndBal(BigDecimal liaEndBal) {
        this.liaEndBal = liaEndBal;
    }

    public BigDecimal getLiaBeginBal() {
        return liaBeginBal;
    }

    public void setLiaBeginBal(BigDecimal liaBeginBal) {
        this.liaBeginBal = liaBeginBal;
    }

}