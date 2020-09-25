package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepPower extends BaseEntity {
    /**
     * ID
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 报表ID
     */
    private String reportId;

    /**
     * null
     */
    private String roleName;

    /**
     * 取得 ID
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 类型
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 取得 报表ID
     * @return 报表ID
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * 设置 报表ID
     * @param reportId 报表ID
     */
    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置 null
     * @param roleName null
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}