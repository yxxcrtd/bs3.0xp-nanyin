package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskEventItemRuleKey extends BaseEntity {
    /**
     * 风险事件代码
     */
    private String eventCode;

    /**
     * 特征值代码
     */
    private String itemCode;

    /**
     * 特征值分组ID
     */
    private String groupId;

    /**
     * 取得 风险事件代码
     * @return 风险事件代码
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * 设置 风险事件代码
     * @param eventCode 风险事件代码
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    /**
     * 取得 特征值代码
     * @return 特征值代码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 特征值代码
     * @param itemCode 特征值代码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * 取得 特征值分组ID
     * @return 特征值分组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 特征值分组ID
     * @param groupId 特征值分组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }
}