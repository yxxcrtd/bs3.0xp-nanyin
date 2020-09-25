package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValSubjectTableid extends BaseEntity {
    /**
     * 科目号头部
     */
    private String subjectHead;

    /**
     * 资产对应买入场景代码
     */
    private String tableid;

    /**
     * 备注
     */
    private String assetsName;

    /**
     * 取得 科目号头部
     * @return 科目号头部
     */
    public String getSubjectHead() {
        return subjectHead;
    }

    /**
     * 设置 科目号头部
     * @param subjectHead 科目号头部
     */
    public void setSubjectHead(String subjectHead) {
        this.subjectHead = subjectHead == null ? null : subjectHead.trim();
    }

    /**
     * 取得 资产对应买入场景代码
     * @return 资产对应买入场景代码
     */
    public String getTableid() {
        return tableid;
    }

    /**
     * 设置 资产对应买入场景代码
     * @param tableid 资产对应买入场景代码
     */
    public void setTableid(String tableid) {
        this.tableid = tableid == null ? null : tableid.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getAssetsName() {
        return assetsName;
    }

    /**
     * 设置 备注
     * @param assetsName 备注
     */
    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName == null ? null : assetsName.trim();
    }
}