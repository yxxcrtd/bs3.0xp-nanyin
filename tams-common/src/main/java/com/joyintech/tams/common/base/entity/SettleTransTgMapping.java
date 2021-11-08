package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SettleTransTgMapping extends SettleTransTgMappingKey {
    /**
     * 投管交易数据状态（01：待处理、02：处理结束）
     */
    private String tgDataStatus;

    /**
     * 执行日期
     */
    private Date dealDate;

    /**
     * 取得 投管交易数据状态（01：待处理、02：处理结束）
     * @return 投管交易数据状态（01：待处理、02：处理结束）
     */
    public String getTgDataStatus() {
        return tgDataStatus;
    }

    /**
     * 设置 投管交易数据状态（01：待处理、02：处理结束）
     * @param tgDataStatus 投管交易数据状态（01：待处理、02：处理结束）
     */
    public void setTgDataStatus(String tgDataStatus) {
        this.tgDataStatus = tgDataStatus == null ? null : tgDataStatus.trim();
    }

    /**
     * 取得 执行日期
     * @return 执行日期
     */
    public Date getDealDate() {
        return dealDate;
    }

    /**
     * 设置 执行日期
     * @param dealDate 执行日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
}