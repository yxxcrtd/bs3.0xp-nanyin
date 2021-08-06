/*
 * 文件名：ValBondValuationInfo003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @Description:资产定价dto
 * @auther: yuzj
 * @date: 2021/7/5
 * @param:
 * @return:
 */
public class AssetsMakePriceResultDto extends  BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;


    /**
     * 证券品种
     */
    private String secType;

    /**
     * 证券细分类
     */
    private String secDetailType;

    /**
     * 金融产品代码
     */
    private String finprodId;


    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 基准日
     */
    private Date benchDate;

    /**
     * 买入全价
     */
    private BigDecimal bFullprice;

    /**
     * 估值模型方法
     */
    private String valueModelMethod;
    /**
     * 评级来源
     */
    private String gradeSource;
    /**
     * 评级结果
     */
    private String gradeResult;
    /**
     * 风险溢价
     */
    private BigDecimal spread;
    /**
     * 风险溢价调整
     */
    private BigDecimal spreadAdj;
    /**
     * 买入数量
     */
    private BigDecimal bQty;


    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getSecDetailType() {
        return secDetailType;
    }

    public void setSecDetailType(String secDetailType) {
        this.secDetailType = secDetailType;
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

    public Date getBenchDate() {
        return benchDate;
    }

    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    public BigDecimal getbFullprice() {
        return bFullprice;
    }

    public void setbFullprice(BigDecimal bFullprice) {
        this.bFullprice = bFullprice;
    }

    public String getValueModelMethod() {
        return valueModelMethod;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public void setValueModelMethod(String valueModelMethod) {
        this.valueModelMethod = valueModelMethod;
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

    public BigDecimal getbQty() {
        return bQty;
    }

    public void setbQty(BigDecimal bQty) {
        this.bQty = bQty;
    }
}
