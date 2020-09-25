/*
 * 文件名：FinProductPayIntIntDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.payint.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;

/**
 * 利息明细信息
 *
 * @author etliu
 * @version 1.0 2019年1月8日
 * @see FinProductPayIntIntDetailDto
 * @since 1.0
 */
public class FinProductPayIntIntDetailDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6084743519738556854L;

    /**
     * 子产品号
     */
    private String sonFinprodId;

    /**
     * 客户等级
     */
    private String customLevel;

    /**
     * 金额下限
     */
    private BigDecimal amtMin;

    /**
     * 金额上限
     */
    private BigDecimal amtMax;

    /**
     * 期限上限
     */
    private String termMax;

    /**
     * 期限上限
     */
    private String termMaxText;

    /**
     * 期限下限
     */
    private String termMin;

    /**
     * 期限下限
     */
    private String termMinText;

    /**
     * 计息本金
     */
    private BigDecimal intAccrPrinAmt;

    /**
     * 应付收益率%
     */
    private BigDecimal theoryYield;

    /**
     * 应付利息
     */
    private BigDecimal theoryInt;

    /**
     * 实付收益率%
     */
    private BigDecimal actYield;

    /**
     * 实付利息
     */
    private BigDecimal actInt;

    /**
     * 分层金融产品代码
     */
    private String layFinprodId;

    /**
     * 子产品起息日
     */
    private Date sonProdVdate;

    /**
     * 子产品到期日
     */
    private Date sonProdMdate;

    /**
     * 计息基础
     */
    private String basis;
    /**
     * 款型
     */
    private String prodType;

    public String getProdType() {
		return prodType;
	}

	public void setProType(String prodType) {
		this.prodType = prodType;
	}

 /**
     * 计息区间
     */
    private long calcIntPeriod;

    /**
     * 计息基础
     */
    private int calcIntBasic;
	/**
     * 获取 sonFinprodId
     *
     * @return sonFinprodId.
     */
    public String getSonFinprodId() {
        return sonFinprodId;
    }

    /**
     * 设置 sonFinprodId
     *
     * @param sonFinprodId sonFinprodId
     */
    public void setSonFinprodId(String sonFinprodId) {
        this.sonFinprodId = sonFinprodId;
    }

    /**
     * 获取 customLevel
     *
     * @return customLevel.
     */
    public String getCustomLevel() {
        return customLevel;
    }

    /**
     * 设置 customLevel
     *
     * @param customLevel customLevel
     */
    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel;
    }

    /**
     * 获取 amtMin
     *
     * @return amtMin.
     */
    public BigDecimal getAmtMin() {
        return amtMin;
    }

    /**
     * 设置 amtMin
     *
     * @param amtMin amtMin
     */
    public void setAmtMin(BigDecimal amtMin) {
        this.amtMin = amtMin;
    }

    /**
     * 获取 amtMax
     *
     * @return amtMax.
     */
    public BigDecimal getAmtMax() {
        return amtMax;
    }

    /**
     * 设置 amtMax
     *
     * @param amtMax amtMax
     */
    public void setAmtMax(BigDecimal amtMax) {
        this.amtMax = amtMax;
    }

    /**
     * 获取 termMax
     *
     * @return termMax.
     */
    public String getTermMax() {
        return termMax;
    }

    /**
     * 设置 termMax
     *
     * @param termMax termMax
     */
    public void setTermMax(String termMax) {
        this.termMax = termMax;
    }

    /**
     * 获取 termMaxText
     *
     * @return termMaxText.
     */
    public String getTermMaxText() {
        if (StringUtil.isNotBlank(termMax)) {
            termMaxText = termMax + "天";
        }
        return termMaxText;
    }

    /**
     * 设置 termMaxText
     *
     * @param termMaxText termMaxText
     */
    public void setTermMaxText(String termMaxText) {
        this.termMaxText = termMaxText;
    }

    /**
     * 获取 termMin
     *
     * @return termMin.
     */
    public String getTermMin() {
        return termMin;
    }

    /**
     * 设置 termMin
     *
     * @param termMin termMin
     */
    public void setTermMin(String termMin) {
        this.termMin = termMin;
    }

    /**
     * 获取 termMinText
     *
     * @return termMinText.
     */
    public String getTermMinText() {
        if (StringUtil.isNotBlank(termMin)) {
            termMinText = termMin + "天";
        }
        return termMinText;
    }

    /**
     * 设置 termMinText
     *
     * @param termMinText termMinText
     */
    public void setTermMinText(String termMinText) {
        this.termMinText = termMinText;
    }

    /**
     * 获取 intAccrPrinAmt
     *
     * @return intAccrPrinAmt.
     */
    public BigDecimal getIntAccrPrinAmt() {
        return intAccrPrinAmt;
    }

    /**
     * 设置 intAccrPrinAmt
     *
     * @param intAccrPrinAmt intAccrPrinAmt
     */
    public void setIntAccrPrinAmt(BigDecimal intAccrPrinAmt) {
        this.intAccrPrinAmt = intAccrPrinAmt;
    }

    /**
     * 获取 theoryYield
     *
     * @return theoryYield.
     */
    public BigDecimal getTheoryYield() {
        return theoryYield;
    }

    /**
     * 设置 theoryYield
     *
     * @param theoryYield theoryYield
     */
    public void setTheoryYield(BigDecimal theoryYield) {
        this.theoryYield = theoryYield;
    }

    /**
     * 获取 theoryInt
     *
     * @return theoryInt.
     */
    public BigDecimal getTheoryInt() {
        return theoryInt;
    }

    /**
     * 设置 theoryInt
     *
     * @param theoryInt theoryInt
     */
    public void setTheoryInt(BigDecimal theoryInt) {
        this.theoryInt = theoryInt;
    }

    /**
     * 获取 actYield
     *
     * @return actYield.
     */
    public BigDecimal getActYield() {
        return actYield;
    }

    /**
     * 设置 actYield
     *
     * @param actYield actYield
     */
    public void setActYield(BigDecimal actYield) {
        this.actYield = actYield;
    }

    /**
     * 获取 actInt
     *
     * @return actInt.
     */
    public BigDecimal getActInt() {
        return actInt;
    }

    /**
     * 设置 actInt
     *
     * @param actInt actInt
     */
    public void setActInt(BigDecimal actInt) {
        this.actInt = actInt;
    }

    /**
     * 获取 layFinprodId
     *
     * @return layFinprodId.
     */
    public String getLayFinprodId() {
        return layFinprodId;
    }

    /**
     * 设置 layFinprodId
     *
     * @param layFinprodId layFinprodId
     */
    public void setLayFinprodId(String layFinprodId) {
        this.layFinprodId = layFinprodId;
    }

    /**
     * 获取 sonProdVdate
     *
     * @return sonProdVdate.
     */
    public Date getSonProdVdate() {
        return sonProdVdate;
    }

    /**
     * 设置 sonProdVdate
     *
     * @param sonProdVdate sonProdVdate
     */
    public void setSonProdVdate(Date sonProdVdate) {
        this.sonProdVdate = sonProdVdate;
    }

    /**
     * 获取 sonProdMdate
     *
     * @return sonProdMdate.
     */
    public Date getSonProdMdate() {
        return sonProdMdate;
    }

    /**
     * 设置 sonProdMdate
     *
     * @param sonProdMdate sonProdMdate
     */
    public void setSonProdMdate(Date sonProdMdate) {
        this.sonProdMdate = sonProdMdate;
    }

    /**
     * 获取 basis
     *
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 calcIntPeriod
     * 
     * @return calcIntPeriod.
     */
    public long getCalcIntPeriod() {
        return calcIntPeriod;
    }

    /**
     * 设置 calcIntPeriod
     * 
     * @param calcIntPeriod calcIntPeriod
     */
    public void setCalcIntPeriod(long calcIntPeriod) {
        this.calcIntPeriod = calcIntPeriod;
    }

    /**
     * 获取 calcIntBasic
     * 
     * @return calcIntBasic.
     */
    public int getCalcIntBasic() {
        return calcIntBasic;
    }

    /**
     * 设置 calcIntBasic
     * 
     * @param calcIntBasic calcIntBasic
     */
    public void setCalcIntBasic(int calcIntBasic) {
        this.calcIntBasic = calcIntBasic;
    }

}
