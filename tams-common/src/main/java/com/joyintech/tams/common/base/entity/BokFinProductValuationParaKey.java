package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokFinProductValuationParaKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String boksetId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBoksetId() {
        return boksetId;
    }

    /**
     * 设置 账套代码
     * @param boksetId 账套代码
     */
    public void setBoksetId(String boksetId) {
        this.boksetId = boksetId == null ? null : boksetId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
}