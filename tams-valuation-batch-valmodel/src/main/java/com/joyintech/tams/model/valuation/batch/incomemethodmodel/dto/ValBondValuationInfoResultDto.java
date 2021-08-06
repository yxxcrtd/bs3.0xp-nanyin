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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @Description:债券估值行情dto
 * @auther: yuzj
 * @date: 2021/7/5
 * @param:
 * @return:
 */
public class ValBondValuationInfoResultDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 估价日
     */
    private Date valueDate;

    /**
     * 估值来源
     */
    private String valueSource;

    /**
     * 估价全价
     */
    private BigDecimal bAnalDirtyCnbd;

    /**
     * 估价净价
     */
    private BigDecimal bAnalNetCnbd;

    /**
     * 交易差点
     */
    private BigDecimal curveSpread;
    /**
     * 曲线名称
     */
    private String  curveName;
    /**
     * 评级结果
     */
    private String  curveCode;
    /**
     * 计息基础
     */
    private String  basis;



    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    public BigDecimal getbAnalDirtyCnbd() {
        return bAnalDirtyCnbd;
    }

    public void setbAnalDirtyCnbd(BigDecimal bAnalDirtyCnbd) {
        this.bAnalDirtyCnbd = bAnalDirtyCnbd;
    }

    public BigDecimal getbAnalNetCnbd() {
        return bAnalNetCnbd;
    }

    public void setbAnalNetCnbd(BigDecimal bAnalNetCnbd) {
        this.bAnalNetCnbd = bAnalNetCnbd;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public void setCurveSpread(BigDecimal curveSpread) {
        this.curveSpread = curveSpread;
    }

    public void setCurveName(String curveName) {
        this.curveName = curveName;
    }

    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }
}
