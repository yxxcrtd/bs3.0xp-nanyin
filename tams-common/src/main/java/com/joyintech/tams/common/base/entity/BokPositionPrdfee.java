package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokPositionPrdfee extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 当日计费本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPosition;

    /**
     * 当日费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFeeRate;

    /**
     * 当日新增费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAdd;

    /**
     * 当日区间应付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntervalUnpaidInc;

    /**
     * 当日累计应付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnpaidIntincexp;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;
    
    /**
     * 子金融产品代码
     */
    private String sFinprodId;
    
    
    

    public String getsFinprodId() {
		return sFinprodId;
	}

	public void setsFinprodId(String sFinprodId) {
		this.sFinprodId = sFinprodId;
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
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
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
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 取得 费用类型
     * @return 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 取得 当日计费本金
     * @return 当日计费本金
     */
    public BigDecimal getTdyPosition() {
        return tdyPosition;
    }

    /**
     * 设置 当日计费本金
     * @param tdyPosition 当日计费本金
     */
    public void setTdyPosition(BigDecimal tdyPosition) {
        this.tdyPosition = tdyPosition;
    }

    /**
     * 取得 当日费率
     * @return 当日费率
     */
    public BigDecimal getTdyFeeRate() {
        return tdyFeeRate;
    }

    /**
     * 设置 当日费率
     * @param tdyFeeRate 当日费率
     */
    public void setTdyFeeRate(BigDecimal tdyFeeRate) {
        this.tdyFeeRate = tdyFeeRate;
    }

    /**
     * 取得 当日新增费用
     * @return 当日新增费用
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 设置 当日新增费用
     * @param tdyIntincexpAdd 当日新增费用
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
    }

    /**
     * 取得 当日区间应付费用
     * @return 当日区间应付费用
     */
    public BigDecimal getTdyIntervalUnpaidInc() {
        return tdyIntervalUnpaidInc;
    }

    /**
     * 设置 当日区间应付费用
     * @param tdyIntervalUnpaidInc 当日区间应付费用
     */
    public void setTdyIntervalUnpaidInc(BigDecimal tdyIntervalUnpaidInc) {
        this.tdyIntervalUnpaidInc = tdyIntervalUnpaidInc;
    }

    /**
     * 取得 当日累计应付费用
     * @return 当日累计应付费用
     */
    public BigDecimal getTdyUnpaidIntincexp() {
        return tdyUnpaidIntincexp;
    }

    /**
     * 设置 当日累计应付费用
     * @param tdyUnpaidIntincexp 当日累计应付费用
     */
    public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
        this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
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
}