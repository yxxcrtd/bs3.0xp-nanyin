package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysPageDictlistKey extends BaseEntity {
    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 数据字典代码
     */
    private String dictId;

    /**
     * 数据字典明细值
     */
    private String dictItemVal;

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
     * 取得 数据字典代码
     * @return 数据字典代码
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * 设置 数据字典代码
     * @param dictId 数据字典代码
     */
    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    /**
     * 取得 数据字典明细值
     * @return 数据字典明细值
     */
    public String getDictItemVal() {
        return dictItemVal;
    }

    /**
     * 设置 数据字典明细值
     * @param dictItemVal 数据字典明细值
     */
    public void setDictItemVal(String dictItemVal) {
        this.dictItemVal = dictItemVal == null ? null : dictItemVal.trim();
    }
}