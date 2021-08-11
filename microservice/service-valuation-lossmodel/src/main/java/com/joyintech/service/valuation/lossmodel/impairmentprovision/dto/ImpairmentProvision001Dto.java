/*
 * 文件名：ImpairmentProvision001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-07-05
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.lossmodel.impairmentprovision.dto;


import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 减值情况计提表
 * @author xutong
 * @createTime 2021-07-05
 * @see ImpairmentProvision001Dto
 * @since 1.0
 */
public class ImpairmentProvision001Dto extends BaseEntity {
    private Date decDate;
    private String prodId;
    private String prodName;
    private String type9;
    private BigDecimal eclP1;
    private BigDecimal eclP2;
    private BigDecimal eclP3;
    private BigDecimal eclSum;
    private BigDecimal decRatioP1;
    private BigDecimal decRatioP2;
    private BigDecimal decRatioP3;
    private BigDecimal decRatioSum;
    private BigDecimal decBalanceP1;
    private BigDecimal decBalanceP2;
    private BigDecimal decBalanceP3;
    private BigDecimal decBalanceSum;
    private BigDecimal perEclP1;
    private BigDecimal perEclP2;
    private BigDecimal perEclP3;
    private BigDecimal perEclSum;
    private BigDecimal perDecRatioP1;
    private BigDecimal perDecRatioP2;
    private BigDecimal perDecRatioP3;
    private BigDecimal perDecRatioSum;
    private BigDecimal bondCountP1;
    private BigDecimal bondCountP2;
    private BigDecimal bondCountP3;
    private BigDecimal bondCountSum;

    public Date getDecDate() {
        return decDate;
    }

    public String getDecDateFmt(){
        if(StringUtil.isNotEmpty(decDate)){
            return DateUtil.format(decDate,"yyyy-MM-dd");
        }
        return "";
    }

    public void setDecDate(Date decDate) {
        this.decDate = decDate;
    }

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

    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public BigDecimal getEclP1() {
        return eclP1;
    }

    public void setEclP1(BigDecimal eclP1) {
        this.eclP1 = eclP1;
    }

    public BigDecimal getEclP2() {
        return eclP2;
    }

    public void setEclP2(BigDecimal eclP2) {
        this.eclP2 = eclP2;
    }

    public BigDecimal getEclP3() {
        return eclP3;
    }

    public void setEclP3(BigDecimal eclP3) {
        this.eclP3 = eclP3;
    }

    public BigDecimal getEclSum() {
        return eclSum;
    }

    public void setEclSum(BigDecimal eclSum) {
        this.eclSum = eclSum;
    }

    public BigDecimal getDecRatioP1() {
        return decRatioP1;
    }

    public void setDecRatioP1(BigDecimal decRatioP1) {
        this.decRatioP1 = decRatioP1;
    }

    public BigDecimal getDecRatioP2() {
        return decRatioP2;
    }

    public void setDecRatioP2(BigDecimal decRatioP2) {
        this.decRatioP2 = decRatioP2;
    }

    public BigDecimal getDecRatioP3() {
        return decRatioP3;
    }

    public void setDecRatioP3(BigDecimal decRatioP3) {
        this.decRatioP3 = decRatioP3;
    }

    public BigDecimal getDecRatioSum() {
        return decRatioSum;
    }

    public void setDecRatioSum(BigDecimal decRatioSum) {
        this.decRatioSum = decRatioSum;
    }

    public BigDecimal getDecBalanceP1() {
        return decBalanceP1;
    }

    public void setDecBalanceP1(BigDecimal decBalanceP1) {
        this.decBalanceP1 = decBalanceP1;
    }

    public BigDecimal getDecBalanceP2() {
        return decBalanceP2;
    }

    public void setDecBalanceP2(BigDecimal decBalanceP2) {
        this.decBalanceP2 = decBalanceP2;
    }

    public BigDecimal getDecBalanceP3() {
        return decBalanceP3;
    }

    public void setDecBalanceP3(BigDecimal decBalanceP3) {
        this.decBalanceP3 = decBalanceP3;
    }

    public BigDecimal getDecBalanceSum() {
        return decBalanceSum;
    }

    public void setDecBalanceSum(BigDecimal decBalanceSum) {
        this.decBalanceSum = decBalanceSum;
    }

    public BigDecimal getPerEclP1() {
        return perEclP1;
    }

    public void setPerEclP1(BigDecimal perEclP1) {
        this.perEclP1 = perEclP1;
    }

    public BigDecimal getPerEclP2() {
        return perEclP2;
    }

    public void setPerEclP2(BigDecimal perEclP2) {
        this.perEclP2 = perEclP2;
    }

    public BigDecimal getPerEclP3() {
        return perEclP3;
    }

    public void setPerEclP3(BigDecimal perEclP3) {
        this.perEclP3 = perEclP3;
    }

    public BigDecimal getPerEclSum() {
        return perEclSum;
    }

    public void setPerEclSum(BigDecimal perEclSum) {
        this.perEclSum = perEclSum;
    }

    public BigDecimal getPerDecRatioP1() {
        return perDecRatioP1;
    }

    public void setPerDecRatioP1(BigDecimal perDecRatioP1) {
        this.perDecRatioP1 = perDecRatioP1;
    }

    public BigDecimal getPerDecRatioP2() {
        return perDecRatioP2;
    }

    public void setPerDecRatioP2(BigDecimal perDecRatioP2) {
        this.perDecRatioP2 = perDecRatioP2;
    }

    public BigDecimal getPerDecRatioP3() {
        return perDecRatioP3;
    }

    public void setPerDecRatioP3(BigDecimal perDecRatioP3) {
        this.perDecRatioP3 = perDecRatioP3;
    }

    public BigDecimal getPerDecRatioSum() {
        return perDecRatioSum;
    }

    public void setPerDecRatioSum(BigDecimal perDecRatioSum) {
        this.perDecRatioSum = perDecRatioSum;
    }

    public BigDecimal getBondCountP1() {
        return bondCountP1;
    }

    public void setBondCountP1(BigDecimal bondCountP1) {
        this.bondCountP1 = bondCountP1;
    }

    public BigDecimal getBondCountP2() {
        return bondCountP2;
    }

    public void setBondCountP2(BigDecimal bondCountP2) {
        this.bondCountP2 = bondCountP2;
    }

    public BigDecimal getBondCountP3() {
        return bondCountP3;
    }

    public void setBondCountP3(BigDecimal bondCountP3) {
        this.bondCountP3 = bondCountP3;
    }

    public BigDecimal getBondCountSum() {
        return bondCountSum;
    }

    public void setBondCountSum(BigDecimal bondCountSum) {
        this.bondCountSum = bondCountSum;
    }
}
