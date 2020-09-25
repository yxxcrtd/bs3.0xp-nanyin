package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepDurationKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 存续登记日
     */
    private Date repDurationRegDate;

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
     * 取得 存续登记日
     * @return 存续登记日
     */
    public Date getRepDurationRegDate() {
        return repDurationRegDate;
    }

    /**
     * 设置 存续登记日
     * @param repDurationRegDate 存续登记日
     */
    public void setRepDurationRegDate(Date repDurationRegDate) {
        this.repDurationRegDate = repDurationRegDate;
    }
}