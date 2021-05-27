package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @ClassName ChbPeriodicPosition
 * @Description: 定期报告持仓情况
 * @Author WanShiWei
 * @Date 2021-05-07
 * @Version V1.0
 **/
public class ChbPeriodicAssetPosition extends BaseEntity {

    private static final long serialVersionUID = 109399923067388230L;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 期末余额
     */
    private BigDecimal balance;

    /**
     * 期末余额（穿透后）
     */
    private BigDecimal bottombalance;

    /**
     * 穿透前占总资产比例
     */
    private BigDecimal percentAssetvalueA;

    /**
     * 穿透后占总资产比例
     */
    private BigDecimal percentAssetvalueB;

    /**
     * 穿透前占总资产比例显示名称
     */
    private String percentAssetvalueAFmt;

    /**
     * 穿透后占总资产比例显示名称
     */
    private String percentAssetvalueBFmt;

    public String getPropertycode() {
        return propertycode;
    }

    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBottombalance() {
        return bottombalance;
    }

    public void setBottombalance(BigDecimal bottombalance) {
        this.bottombalance = bottombalance;
    }

    public BigDecimal getPercentAssetvalueA() {
        return percentAssetvalueA;
    }

    public void setPercentAssetvalueA(BigDecimal percentAssetvalueA) {
        this.percentAssetvalueA = percentAssetvalueA;
    }

    public BigDecimal getPercentAssetvalueB() {
        return percentAssetvalueB;
    }

    public void setPercentAssetvalueB(BigDecimal percentAssetvalueB) {
        this.percentAssetvalueB = percentAssetvalueB;
    }

    public String getPercentAssetvalueAFmt() {
        return percentAssetvalueAFmt;
    }

    public void setPercentAssetvalueAFmt(String percentAssetvalueAFmt) {
        this.percentAssetvalueAFmt = percentAssetvalueAFmt;
    }

    public String getPercentAssetvalueBFmt() {
        return percentAssetvalueBFmt;
    }

    public void setPercentAssetvalueBFmt(String percentAssetvalueBFmt) {
        this.percentAssetvalueBFmt = percentAssetvalueBFmt;
    }
}
