package com.joyintech.tams.common.base.entity;

public class RskIndividUnite extends RskIndividUniteKey implements IColBaseCond {
    /**
     * 备注
     */
    private String remark;

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