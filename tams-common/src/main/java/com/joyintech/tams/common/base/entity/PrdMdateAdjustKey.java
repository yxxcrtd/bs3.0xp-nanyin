package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdMdateAdjustKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 调整批次，从1开始，每调整一次加1
     */
    private Long adjustNum;

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
     * 取得 调整批次，从1开始，每调整一次加1
     * @return 调整批次，从1开始，每调整一次加1
     */
    public Long getAdjustNum() {
        return adjustNum;
    }

    /**
     * 设置 调整批次，从1开始，每调整一次加1
     * @param adjustNum 调整批次，从1开始，每调整一次加1
     */
    public void setAdjustNum(Long adjustNum) {
        this.adjustNum = adjustNum;
    }
}