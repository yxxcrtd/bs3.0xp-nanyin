package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class ModRiskPremium extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 证券品种（数据字典.证券品种_模型）
     */
    private String secTypeModel;

    /**
     * 证券细分类（数据字典.证券细分类_模型）
     */
    private String secDetailTypeModel;

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 评级来源
     */
    private String gradeSource;

    /**
     * 评级结果
     */
    private String gradeResult;

    /**
     * 基准日
     */
    private Date basedate;

    /**
     * 风险溢价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal spread;

    /**
     * 风险溢价调整
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal spreadAdj;

    /**
     * 买入全价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal bFullprice;

    /**
     * 买入数量
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal bQty;

    /**
     * 估值模型方法
     */
    private String modMethod;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 证券品种（数据字典.证券品种_模型）
     * @return 证券品种（数据字典.证券品种_模型）
     */
    public String getSecTypeModel() {
        return secTypeModel;
    }

    /**
     * 设置 证券品种（数据字典.证券品种_模型）
     * @param secTypeModel 证券品种（数据字典.证券品种_模型）
     */
    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel == null ? null : secTypeModel.trim();
    }

    /**
     * 取得 证券细分类（数据字典.证券细分类_模型）
     * @return 证券细分类（数据字典.证券细分类_模型）
     */
    public String getSecDetailTypeModel() {
        return secDetailTypeModel;
    }

    /**
     * 设置 证券细分类（数据字典.证券细分类_模型）
     * @param secDetailTypeModel 证券细分类（数据字典.证券细分类_模型）
     */
    public void setSecDetailTypeModel(String secDetailTypeModel) {
        this.secDetailTypeModel = secDetailTypeModel == null ? null : secDetailTypeModel.trim();
    }

    /**
     * 取得 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @return 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @param finprodId 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品名称
     * @return 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 金融产品名称
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 评级来源
     * @return 评级来源
     */
    public String getGradeSource() {
        return gradeSource;
    }

    /**
     * 设置 评级来源
     * @param gradeSource 评级来源
     */
    public void setGradeSource(String gradeSource) {
        this.gradeSource = gradeSource == null ? null : gradeSource.trim();
    }

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 评级结果
     * @param gradeResult 评级结果
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    /**
     * 取得 基准日
     * @return 基准日
     */
    public Date getBasedate() {
        return basedate;
    }

    /**
     * 设置 基准日
     * @param basedate 基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBasedate(Date basedate) {
        this.basedate = basedate;
    }

    /**
     * 取得 风险溢价
     * @return 风险溢价
     */
    public BigDecimal getSpread() {
        return spread;
    }

    /**
     * 设置 风险溢价
     * @param spread 风险溢价
     */
    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    /**
     * 取得 风险溢价调整
     * @return 风险溢价调整
     */
    public BigDecimal getSpreadAdj() {
        return spreadAdj;
    }

    /**
     * 设置 风险溢价调整
     * @param spreadAdj 风险溢价调整
     */
    public void setSpreadAdj(BigDecimal spreadAdj) {
        this.spreadAdj = spreadAdj;
    }

    /**
     * 取得 买入全价
     * @return 买入全价
     */
    public BigDecimal getbFullprice() {
        return bFullprice;
    }

    /**
     * 设置 买入全价
     * @param bFullprice 买入全价
     */
    public void setbFullprice(BigDecimal bFullprice) {
        this.bFullprice = bFullprice;
    }

    /**
     * 取得 买入数量
     * @return 买入数量
     */
    public BigDecimal getbQty() {
        return bQty;
    }

    /**
     * 设置 买入数量
     * @param bQty 买入数量
     */
    public void setbQty(BigDecimal bQty) {
        this.bQty = bQty;
    }

    /**
     * 取得 估值模型方法
     * @return 估值模型方法
     */
    public String getModMethod() {
        return modMethod;
    }

    /**
     * 设置 估值模型方法
     * @param modMethod 估值模型方法
     */
    public void setModMethod(String modMethod) {
        this.modMethod = modMethod == null ? null : modMethod.trim();
    }
}