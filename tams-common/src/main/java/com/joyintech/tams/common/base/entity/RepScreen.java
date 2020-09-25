package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepScreen extends BaseEntity {
    /**
     * 主键ID
     */
    private String screenId;

    /**
     * 报表ID
     */
    private String repId;

    /**
     * 查询条件类型
     */
    private String screenType;

    /**
     * 查询条件key
     */
    private String screenKey;

    /**
     * 查询条件名称
     */
    private String screenName;

    /**
     * 查询条件值
     */
    private String screenValue;

    /**
     * 查询条件状态
     */
    private String screenStatus;

    /**
     * 查询条件顺序
     */
    private Long screenIndex;

    /**
     * 取得 主键ID
     * @return 主键ID
     */
    public String getScreenId() {
        return screenId;
    }

    /**
     * 设置 主键ID
     * @param screenId 主键ID
     */
    public void setScreenId(String screenId) {
        this.screenId = screenId == null ? null : screenId.trim();
    }

    /**
     * 取得 报表ID
     * @return 报表ID
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 报表ID
     * @param repId 报表ID
     */
    public void setRepId(String repId) {
        this.repId = repId == null ? null : repId.trim();
    }

    /**
     * 取得 查询条件类型
     * @return 查询条件类型
     */
    public String getScreenType() {
        return screenType;
    }

    /**
     * 设置 查询条件类型
     * @param screenType 查询条件类型
     */
    public void setScreenType(String screenType) {
        this.screenType = screenType == null ? null : screenType.trim();
    }

    /**
     * 取得 查询条件key
     * @return 查询条件key
     */
    public String getScreenKey() {
        return screenKey;
    }

    /**
     * 设置 查询条件key
     * @param screenKey 查询条件key
     */
    public void setScreenKey(String screenKey) {
        this.screenKey = screenKey == null ? null : screenKey.trim();
    }

    /**
     * 取得 查询条件名称
     * @return 查询条件名称
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * 设置 查询条件名称
     * @param screenName 查询条件名称
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName == null ? null : screenName.trim();
    }

    /**
     * 取得 查询条件值
     * @return 查询条件值
     */
    public String getScreenValue() {
        return screenValue;
    }

    /**
     * 设置 查询条件值
     * @param screenValue 查询条件值
     */
    public void setScreenValue(String screenValue) {
        this.screenValue = screenValue == null ? null : screenValue.trim();
    }

    /**
     * 取得 查询条件状态
     * @return 查询条件状态
     */
    public String getScreenStatus() {
        return screenStatus;
    }

    /**
     * 设置 查询条件状态
     * @param screenStatus 查询条件状态
     */
    public void setScreenStatus(String screenStatus) {
        this.screenStatus = screenStatus == null ? null : screenStatus.trim();
    }

    /**
     * 取得 查询条件顺序
     * @return 查询条件顺序
     */
    public Long getScreenIndex() {
        return screenIndex;
    }

    /**
     * 设置 查询条件顺序
     * @param screenIndex 查询条件顺序
     */
    public void setScreenIndex(Long screenIndex) {
        this.screenIndex = screenIndex;
    }
}