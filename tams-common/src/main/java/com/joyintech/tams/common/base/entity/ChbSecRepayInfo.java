package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbSecRepayInfo extends ChbSecRepayInfoKey {
    /**
     * 债券名称
     */
    private String secNm;

    /**
     * 还本比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repayRate;

    /**
     * 还本后面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repayNet;

    /**
     * 上市地点 上交所：SH,深交所:SZ,银行间：IB
     */
    private String tradeMarket;

    /**
     * 还本周期计息截止日
     */
    private Date repayCycleDay;

    /**
     * 还本方式 按面值：1,按数量：2
     */
    private String repayMode;

    /**
     * 取得 债券名称
     * @return 债券名称
     */
    public String getSecNm() {
        return secNm;
    }

    /**
     * 设置 债券名称
     * @param secNm 债券名称
     */
    public void setSecNm(String secNm) {
        this.secNm = secNm == null ? null : secNm.trim();
    }

    /**
     * 取得 还本比例
     * @return 还本比例
     */
    public BigDecimal getRepayRate() {
        return repayRate;
    }

    /**
     * 设置 还本比例
     * @param repayRate 还本比例
     */
    public void setRepayRate(BigDecimal repayRate) {
        this.repayRate = repayRate;
    }

    /**
     * 取得 还本后面值
     * @return 还本后面值
     */
    public BigDecimal getRepayNet() {
        return repayNet;
    }

    /**
     * 设置 还本后面值
     * @param repayNet 还本后面值
     */
    public void setRepayNet(BigDecimal repayNet) {
        this.repayNet = repayNet;
    }

    /**
     * 取得 上市地点 上交所：SH,深交所:SZ,银行间：IB
     * @return 上市地点 上交所：SH,深交所:SZ,银行间：IB
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 上市地点 上交所：SH,深交所:SZ,银行间：IB
     * @param tradeMarket 上市地点 上交所：SH,深交所:SZ,银行间：IB
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 还本周期计息截止日
     * @return 还本周期计息截止日
     */
    public Date getRepayCycleDay() {
        return repayCycleDay;
    }

    /**
     * 设置 还本周期计息截止日
     * @param repayCycleDay 还本周期计息截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepayCycleDay(Date repayCycleDay) {
        this.repayCycleDay = repayCycleDay;
    }

    /**
     * 取得 还本方式 按面值：1,按数量：2
     * @return 还本方式 按面值：1,按数量：2
     */
    public String getRepayMode() {
        return repayMode;
    }

    /**
     * 设置 还本方式 按面值：1,按数量：2
     * @param repayMode 还本方式 按面值：1,按数量：2
     */
    public void setRepayMode(String repayMode) {
        this.repayMode = repayMode == null ? null : repayMode.trim();
    }
}