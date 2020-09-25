package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepMonthCountKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 统计日期
     */
    private Date reCountDate;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 统计日期
     * @return 统计日期
     */
    public Date getReCountDate() {
        return reCountDate;
    }

    /**
     * 设置 统计日期
     * @param reCountDate 统计日期
     */
    public void setReCountDate(Date reCountDate) {
        this.reCountDate = reCountDate;
    }
}