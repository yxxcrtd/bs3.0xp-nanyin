package com.joyintech.tams.common.base.entity;

public class PtlSecStock extends PtlSecStockKey {
    /**
     * 质押库名称
     */
    private String pledgeStockName;

    /**
     * 取得 质押库名称
     * @return 质押库名称
     */
    public String getPledgeStockName() {
        return pledgeStockName;
    }

    /**
     * 设置 质押库名称
     * @param pledgeStockName 质押库名称
     */
    public void setPledgeStockName(String pledgeStockName) {
        this.pledgeStockName = pledgeStockName == null ? null : pledgeStockName.trim();
    }
}