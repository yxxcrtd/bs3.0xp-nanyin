/*
 * 文件名：FinProductEstablishDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.product.productduration.establish.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productduration.establish.validator.FinProductEstablish001Validator;

/**
 * 产品成立信息dto
 * 
 * @author zhangliang
 * @version 1.0 2018年12月19日
 * @see FinProductEstablishDto
 * @since 1.0
 */
@ValidLogic(targetClass = FinProductEstablish001Validator.class)
public class FinProductEstablishDto extends FinProduct {

    /**
     * 
     */
    private static final long serialVersionUID = -5107076629854232712L;

    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 元素Id
     */
    private String elementId;
    
    /**
     * 有效状态
     */
    private String effectStatus;
    
    /**
     * 业务编号
     */
    private String bussId;

    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 子产品号
     */
    private String sonId;

    /**
     * 募集开始日
     */
    private Date firstSaleVdate;

    /**
     * 募集结束日
     */
    private Date firstSaleMdate;

    /**
     * 预期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 销售费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal saleFeeRate;

    /**
     * 托管费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal trustFeeRate;
    
    /**
     * 本期认购
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currentSubscribe;
    
    /**
     * 到账资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal arrivalCapital;
    

    public String getEffectStatus() {
        return effectStatus;
    }
    
    public String getEffectStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态,  effectStatus);
    }

    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    public String getPtlId() {
        return ptlId;
    }

    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    public String getSaleDepartment() {
        return saleDepartment;
    }

    public String getSaleDepartmentStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartment);
    }

    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    public String getSonId() {
        return sonId;
    }

    public void setSonId(String sonId) {
        this.sonId = sonId;
    }

    public Date getFirstSaleVdate() {
        return firstSaleVdate;
    }
    
    public String getFirstSaleVdateStr() {
        return DateUtil.format(firstSaleVdate);
    }

    public void setFirstSaleVdate(Date firstSaleVdate) {
        this.firstSaleVdate = firstSaleVdate;
    }

    public Date getFirstSaleMdate() {
        return firstSaleMdate;
    }

    public String getFirstSaleMdateStr() {
        return DateUtil.format(firstSaleMdate);
    }

    public void setFirstSaleMdate(Date firstSaleMdate) {
        this.firstSaleMdate = firstSaleMdate;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    public BigDecimal getTrustFeeRate() {
        return trustFeeRate;
    }

    public void setTrustFeeRate(BigDecimal trustFeeRate) {
        this.trustFeeRate = trustFeeRate;
    }

    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }
    
    public String getVdateStr(){
        return DateUtil.format(this.getVdate());
    }
    
    public String getMdateStr(){
        return DateUtil.format(this.getMdate());
    }

    
    /**
     * 获取 bussId
     * @return bussId.
     */
    public String getBussId() {
        return bussId;
    }

    
    /**
     * 设置 bussId
     * @param bussId bussId
     */
    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    
    /**
     * 获取 currentSubscribe
     * @return currentSubscribe.
     */
    public BigDecimal getCurrentSubscribe() {
        return currentSubscribe;
    }

    
    /**
     * 设置 currentSubscribe
     * @param currentSubscribe currentSubscribe
     */
    public void setCurrentSubscribe(BigDecimal currentSubscribe) {
        this.currentSubscribe = currentSubscribe;
    }

    
    /**
     * 获取 arrivalCapital
     * @return arrivalCapital.
     */
    public BigDecimal getArrivalCapital() {
        return arrivalCapital;
    }

    
    /**
     * 设置 arrivalCapital
     * @param arrivalCapital arrivalCapital
     */
    public void setArrivalCapital(BigDecimal arrivalCapital) {
        this.arrivalCapital = arrivalCapital;
    }

    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
    /**
     * 交易类型
     */
    private String dealType;
    
    public String getDealTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealType);
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    
}
