/**
 * 
 */
package com.joyintech.tams.disclosure.information.report.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhengbaosheng
 *
 */
public class ReportBonusReportDto {
    /**资产净值**/
	private BigDecimal assetsValue;
    /**期末余额**/
    private BigDecimal capital;
    /**单位净值**/
    private BigDecimal unitValue;
    /**收益分配基准日**/
    private Date settDate;
    /*红利发放日*/
    private Date paymentDate;
    /**业绩基准**/
    private String achievementBasis;
    /**业绩基准比例**/
    private BigDecimal achievementBasisProp;
    /**超额收益收取比例**/
    private BigDecimal excessAchievementProp;
    /*运营起始日*/
    private Date operationStartDate;
    /*自动赎回日*/
    private Date autoRedeemDate;

    public BigDecimal getAssetsValue() {
        return assetsValue;
    }

    public void setAssetsValue(BigDecimal assetsValue) {
        this.assetsValue = assetsValue;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public Date getSettDate() {
        return settDate;
    }

    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAchievementBasis() {
        return achievementBasis;
    }

    public void setAchievementBasis(String achievementBasis) {
        this.achievementBasis = achievementBasis;
    }

    public BigDecimal getAchievementBasisProp() {
        return achievementBasisProp;
    }

    public void setAchievementBasisProp(BigDecimal achievementBasisProp) {
        this.achievementBasisProp = achievementBasisProp;
    }

    public BigDecimal getExcessAchievementProp() {
        return excessAchievementProp;
    }

    public void setExcessAchievementProp(BigDecimal excessAchievementProp) {
        this.excessAchievementProp = excessAchievementProp;
    }

    public Date getOperationStartDate() {
        return operationStartDate;
    }

    public void setOperationStartDate(Date operationStartDate) {
        this.operationStartDate = operationStartDate;
    }

    public Date getAutoRedeemDate() {
        return autoRedeemDate;
    }

    public void setAutoRedeemDate(Date autoRedeemDate) {
        this.autoRedeemDate = autoRedeemDate;
    }
}
