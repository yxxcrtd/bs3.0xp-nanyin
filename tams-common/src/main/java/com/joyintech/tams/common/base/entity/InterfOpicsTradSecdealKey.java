package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfOpicsTradSecdealKey extends BaseEntity {
    /**
     * 分支代码
     */
    private String br;

    /**
     * 交易号
     */
    private String dealno;

    /**
     * 取得 分支代码
     * @return 分支代码
     */
    public String getBr() {
        return br;
    }

    /**
     * 设置 分支代码
     * @param br 分支代码
     */
    public void setBr(String br) {
        this.br = br == null ? null : br.trim();
    }

    /**
     * 取得 交易号
     * @return 交易号
     */
    public String getDealno() {
        return dealno;
    }

    /**
     * 设置 交易号
     * @param dealno 交易号
     */
    public void setDealno(String dealno) {
        this.dealno = dealno == null ? null : dealno.trim();
    }
}