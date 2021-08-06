/*
 * 文件名：RiskPremiumAdjConf001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-30
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-06-30
 * @see RiskPremiumAdjConf001Dto
 * @since 1.0
 */
public class RiskPremiumAdjConf001Dto extends BaseEntity {
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
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadAdj;

    /**
     * 买入全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bFullprice;

    /**
     * 买入数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bQty;

    /**
     * 估值模型方法
     */
    private String modMethod;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getSecTypeModel() {
        return secTypeModel;
    }

    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel;
    }

    public String getSecDetailTypeModel() {
        return secDetailTypeModel;
    }

    public void setSecDetailTypeModel(String secDetailTypeModel) {
        this.secDetailTypeModel = secDetailTypeModel;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getGradeSource() {
        return gradeSource;
    }

    public void setGradeSource(String gradeSource) {
        this.gradeSource = gradeSource;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public Date getBasedate() {
        return basedate;
    }

    public String getBasedateFmt(){
        if(StringUtil.isNotEmpty(basedate)){
            return DateUtil.format(basedate,"yyyy-MM-dd");
        }
        return "";
    }

    public void setBasedate(Date basedate) {
        this.basedate = basedate;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal getSpreadAdj() {
        return spreadAdj;
    }

    public void setSpreadAdj(BigDecimal spreadAdj) {
        this.spreadAdj = spreadAdj;
    }

    public BigDecimal getbFullprice() {
        return bFullprice;
    }

    public void setbFullprice(BigDecimal bFullprice) {
        this.bFullprice = bFullprice;
    }

    public BigDecimal getbQty() {
        return bQty;
    }

    public void setbQty(BigDecimal bQty) {
        this.bQty = bQty;
    }

    public String getModMethod() {
        return modMethod;
    }

    public void setModMethod(String modMethod) {
        this.modMethod = modMethod;
    }
}
