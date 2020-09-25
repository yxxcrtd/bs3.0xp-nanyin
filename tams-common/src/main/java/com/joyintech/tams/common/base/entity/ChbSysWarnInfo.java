package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysWarnInfo extends BaseEntity {
    /**
     * 警告代码
     */
    private String warnId;

    /**
     * 业务代码
     */
    private String bussId;

    /**
     * 业务类型 参照数据字典
     */
    private String bussType;

    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 要素代码
     */
    private String elementId;

    /**
     * 警告类型 参照数据字典
     */
    private String warnType;

    /**
     * 警告信息
     */
    private String warnInfo;

    /**
     * 取得 警告代码
     * @return 警告代码
     */
    public String getWarnId() {
        return warnId;
    }

    /**
     * 设置 警告代码
     * @param warnId 警告代码
     */
    public void setWarnId(String warnId) {
        this.warnId = warnId == null ? null : warnId.trim();
    }

    /**
     * 取得 业务代码
     * @return 业务代码
     */
    public String getBussId() {
        return bussId;
    }

    /**
     * 设置 业务代码
     * @param bussId 业务代码
     */
    public void setBussId(String bussId) {
        this.bussId = bussId == null ? null : bussId.trim();
    }

    /**
     * 取得 业务类型 参照数据字典
     * @return 业务类型 参照数据字典
     */
    public String getBussType() {
        return bussType;
    }

    /**
     * 设置 业务类型 参照数据字典
     * @param bussType 业务类型 参照数据字典
     */
    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
    }

    /**
     * 取得 画面代码
     * @return 画面代码
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 画面代码
     * @param pageId 画面代码
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 要素代码
     * @return 要素代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 要素代码
     * @param elementId 要素代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * 取得 警告类型 参照数据字典
     * @return 警告类型 参照数据字典
     */
    public String getWarnType() {
        return warnType;
    }

    /**
     * 设置 警告类型 参照数据字典
     * @param warnType 警告类型 参照数据字典
     */
    public void setWarnType(String warnType) {
        this.warnType = warnType == null ? null : warnType.trim();
    }

    /**
     * 取得 警告信息
     * @return 警告信息
     */
    public String getWarnInfo() {
        return warnInfo;
    }

    /**
     * 设置 警告信息
     * @param warnInfo 警告信息
     */
    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo == null ? null : warnInfo.trim();
    }
}