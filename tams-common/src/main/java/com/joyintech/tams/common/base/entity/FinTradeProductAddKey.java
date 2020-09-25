package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class FinTradeProductAddKey extends BaseEntity {
    /**
     * ??????
     */
    private String finprodId;

    /**
     * ????
     */
    private Long branch;

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 ??????
     * @param finprodId ??????
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 ????
     * @param branch ????
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
}