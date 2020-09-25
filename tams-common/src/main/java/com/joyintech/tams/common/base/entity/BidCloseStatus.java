package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BidCloseStatus extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 截标状态
     */
    private String closeStatus;

    /**
     * 备注
     */
    private String remark;

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
     * 取得 截标状态
     * @return 截标状态
     */
    public String getCloseStatus() {
        return closeStatus;
    }

    /**
     * 设置 截标状态
     * @param closeStatus 截标状态
     */
    public void setCloseStatus(String closeStatus) {
        this.closeStatus = closeStatus == null ? null : closeStatus.trim();
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