package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ShareFileConfg extends BaseEntity {
    /**
     * 模板编号
     */
    private String templateid;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 表名
     */
    private String tableid;

    /**
     * 取得 模板编号
     * @return 模板编号
     */
    public String getTemplateid() {
        return templateid;
    }

    /**
     * 设置 模板编号
     * @param templateid 模板编号
     */
    public void setTemplateid(String templateid) {
        this.templateid = templateid == null ? null : templateid.trim();
    }

    /**
     * 取得 文件名
     * @return 文件名
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设置 文件名
     * @param filename 文件名
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    /**
     * 取得 表名
     * @return 表名
     */
    public String getTableid() {
        return tableid;
    }

    /**
     * 设置 表名
     * @param tableid 表名
     */
    public void setTableid(String tableid) {
        this.tableid = tableid == null ? null : tableid.trim();
    }
}