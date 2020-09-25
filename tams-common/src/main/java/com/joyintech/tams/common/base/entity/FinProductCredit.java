package com.joyintech.tams.common.base.entity;

public class FinProductCredit extends FinProductCreditKey {
    /**
     * 增信方式
     */
    private String creditType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 增信方式
     * @return 增信方式
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 增信方式
     * @param creditType 增信方式
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
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
}