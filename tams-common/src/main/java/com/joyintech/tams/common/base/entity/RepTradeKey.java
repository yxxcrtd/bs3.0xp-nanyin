package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepTradeKey extends BaseEntity {
    /**
     * 行内交易编码
     */
    private String repBankTradeCode;

    /**
     * 资金流动类型
     */
    private String repMoneyFlowType;

    /**
     * 取得 行内交易编码
     * @return 行内交易编码
     */
    public String getRepBankTradeCode() {
        return repBankTradeCode;
    }

    /**
     * 设置 行内交易编码
     * @param repBankTradeCode 行内交易编码
     */
    public void setRepBankTradeCode(String repBankTradeCode) {
        this.repBankTradeCode = repBankTradeCode == null ? null : repBankTradeCode.trim();
    }

    /**
     * 取得 资金流动类型
     * @return 资金流动类型
     */
    public String getRepMoneyFlowType() {
        return repMoneyFlowType;
    }

    /**
     * 设置 资金流动类型
     * @param repMoneyFlowType 资金流动类型
     */
    public void setRepMoneyFlowType(String repMoneyFlowType) {
        this.repMoneyFlowType = repMoneyFlowType == null ? null : repMoneyFlowType.trim();
    }
}