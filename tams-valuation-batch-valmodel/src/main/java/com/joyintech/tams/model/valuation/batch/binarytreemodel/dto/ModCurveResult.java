package com.joyintech.tams.model.valuation.batch.binarytreemodel.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class ModCurveResult extends BaseEntity {
    /**
     * 序号
     */
    private String seqno;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 估值全价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal fullPrice;

    /**
     * 估值净价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal netPrice;

    /**
     * 交易点差
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal curveSpread;

    /**
     * 曲线名称
     */
    private String curveName;

    /**
     * 评级结果
     */
    private String curveCode;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 估值模型
     */
    private String valModel;

    /**
     * 取得 序号
     * @return 序号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 序号
     * @param seqno 序号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 估值全价
     * @return 估值全价
     */
    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    /**
     * 设置 估值全价
     * @param fullPrice 估值全价
     */
    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    /**
     * 取得 估值净价
     * @return 估值净价
     */
    public BigDecimal getNetPrice() {
        return netPrice;
    }

    /**
     * 设置 估值净价
     * @param netPrice 估值净价
     */
    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    /**
     * 取得 交易点差
     * @return 交易点差
     */
    public BigDecimal getCurveSpread() {
        return curveSpread;
    }

    /**
     * 设置 交易点差
     * @param curveSpread 交易点差
     */
    public void setCurveSpread(BigDecimal curveSpread) {
        this.curveSpread = curveSpread;
    }

    /**
     * 取得 曲线名称
     * @return 曲线名称
     */
    public String getCurveName() {
        return curveName;
    }

    /**
     * 设置 曲线名称
     * @param curveName 曲线名称
     */
    public void setCurveName(String curveName) {
        this.curveName = curveName == null ? null : curveName.trim();
    }

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getCurveCode() {
        return curveCode;
    }

    /**
     * 设置 评级结果
     * @param curveCode 评级结果
     */
    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode == null ? null : curveCode.trim();
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 估值模型
     * @return 估值模型
     */
    public String getValModel() {
        return valModel;
    }

    /**
     * 设置 估值模型
     * @param valModel 估值模型
     */
    public void setValModel(String valModel) {
        this.valModel = valModel == null ? null : valModel.trim();
    }
}