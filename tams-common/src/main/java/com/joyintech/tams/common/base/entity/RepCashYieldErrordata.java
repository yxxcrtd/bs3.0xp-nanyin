package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepCashYieldErrordata extends BaseEntity {
    /**
     * uuid主键
     */
    private String datauuid;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 信披费用类型
     */
    private String cashType2;

    /**
     * 取得 uuid主键
     * @return uuid主键
     */
    public String getDatauuid() {
        return datauuid;
    }

    /**
     * 设置 uuid主键
     * @param datauuid uuid主键
     */
    public void setDatauuid(String datauuid) {
        this.datauuid = datauuid == null ? null : datauuid.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 信披费用类型
     * @return 信披费用类型
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置 信披费用类型
     * @param cashType2 信披费用类型
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2 == null ? null : cashType2.trim();
    }
}