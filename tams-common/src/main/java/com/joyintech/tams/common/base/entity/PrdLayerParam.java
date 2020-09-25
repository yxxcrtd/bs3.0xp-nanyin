package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PrdLayerParam extends BaseEntity {
    /**
     * 金融产品代码，子产品对应的代码
     */
    private String finprodId;

    /**
     * 客户等级
     */
    private String customLevel;

    /**
     * 金额下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amtMin;

    /**
     * 金额上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amtMax;

    /**
     * 期限上限
     */
    private Long termMax;

    /**
     * 期限下限
     */
    private Long termMin;

    /**
     * 收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 备注
     */
    private String remark;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 生效日，子产品的起息日
     */
    private Date vdate;

    /**
     * 产品款型
     */
    private String prodStyle;

    /**
     * 销售渠道
     */
    private String saleChannel;

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 目标客户类型
     */
    private String tarCustomerType;

    /**
     * 是否代销
     */
    private String isSale;
    /**
     * 分层产品cashId
     */
    private String cashId;

    /**
     * 取得 金融产品代码，子产品对应的代码
     * @return 金融产品代码，子产品对应的代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码，子产品对应的代码
     * @param finprodId 金融产品代码，子产品对应的代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 客户等级
     * @return 客户等级
     */
    public String getCustomLevel() {
        return customLevel;
    }

    /**
     * 设置 客户等级
     * @param customLevel 客户等级
     */
    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel == null ? null : customLevel.trim();
    }

    /**
     * 取得 金额下限
     * @return 金额下限
     */
    public BigDecimal getAmtMin() {
        return amtMin;
    }

    /**
     * 设置 金额下限
     * @param amtMin 金额下限
     */
    public void setAmtMin(BigDecimal amtMin) {
        this.amtMin = amtMin;
    }

    /**
     * 取得 金额上限
     * @return 金额上限
     */
    public BigDecimal getAmtMax() {
        return amtMax;
    }

    /**
     * 设置 金额上限
     * @param amtMax 金额上限
     */
    public void setAmtMax(BigDecimal amtMax) {
        this.amtMax = amtMax;
    }

    /**
     * 取得 期限上限
     * @return 期限上限
     */
    public Long getTermMax() {
        return termMax;
    }

    /**
     * 设置 期限上限
     * @param termMax 期限上限
     */
    public void setTermMax(Long termMax) {
        this.termMax = termMax;
    }

    /**
     * 取得 期限下限
     * @return 期限下限
     */
    public Long getTermMin() {
        return termMin;
    }

    /**
     * 设置 期限下限
     * @param termMin 期限下限
     */
    public void setTermMin(Long termMin) {
        this.termMin = termMin;
    }

    /**
     * 取得 收益率
     * @return 收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 收益率
     * @param yield 收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 母金融产品代码
     * @return 母金融产品代码
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 母金融产品代码
     * @param pFinprodId 母金融产品代码
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId == null ? null : pFinprodId.trim();
    }

    /**
     * 取得 生效日，子产品的起息日
     * @return 生效日，子产品的起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 生效日，子产品的起息日
     * @param vdate 生效日，子产品的起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 产品款型
     * @return 产品款型
     */
    public String getProdStyle() {
        return prodStyle;
    }

    /**
     * 设置 产品款型
     * @param prodStyle 产品款型
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle == null ? null : prodStyle.trim();
    }

    /**
     * 取得 销售渠道
     * @return 销售渠道
     */
    public String getSaleChannel() {
        return saleChannel;
    }

    /**
     * 设置 销售渠道
     * @param saleChannel 销售渠道
     */
    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel == null ? null : saleChannel.trim();
    }

    /**
     * 取得 销售部门
     * @return 销售部门
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    /**
     * 设置 销售部门
     * @param saleDepartment 销售部门
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment == null ? null : saleDepartment.trim();
    }

    /**
     * 取得 目标客户类型
     * @return 目标客户类型
     */
    public String getTarCustomerType() {
        return tarCustomerType;
    }

    /**
     * 设置 目标客户类型
     * @param tarCustomerType 目标客户类型
     */
    public void setTarCustomerType(String tarCustomerType) {
        this.tarCustomerType = tarCustomerType == null ? null : tarCustomerType.trim();
    }

    /**
     * 取得 是否代销
     * @return 是否代销
     */
    public String getIsSale() {
        return isSale;
    }

    /**
     * 设置 是否代销
     * @param isSale 是否代销
     */
    public void setIsSale(String isSale) {
        this.isSale = isSale == null ? null : isSale.trim();
    }

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
    
}