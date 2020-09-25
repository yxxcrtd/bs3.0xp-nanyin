package com.joyintech.service.valuation.assets.posit.dto;

import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 利率调整
 * 
 * @author 王婷婷
 * @version 1.0 2018年10月27日
 * @see ValIntRateAdjustmentDto
 * @since 1.0
 */
public class ValIntRateAdjustmentDto extends FinProduct {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private String branch;

    /**
     * 生效日
     */
    private String effDate;

    /**
     * 录入方式
     */
    private String entryMode;

    /**
     * 利率
     */
    private String intRate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取 branch
     * 
     * @return branch.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     * 
     * @param branch branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 获取 effDate
     * 
     * @return effDate.
     */
    public String getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * 
     * @param effDate effDate
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 entryMode
     * 
     * @return entryMode.
     */
    public String getEntryMode() {
        return entryMode;
    }

    /**
     * 设置 entryMode
     * 
     * @param entryMode entryMode
     */
    public void setEntryMode(String entryMode) {
        this.entryMode = entryMode;
    }

    /**
     * 获取 intRate
     * 
     * @return intRate.
     */
    public String getIntRate() {
        return intRate;
    }

    /**
     * 设置 intRate
     * 
     * @param intRate intRate
     */
    public void setIntRate(String intRate) {
        this.intRate = intRate;
    }

    /**
     * 获取 remark
     * 
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * 
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
