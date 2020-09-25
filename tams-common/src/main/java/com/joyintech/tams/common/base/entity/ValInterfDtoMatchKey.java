package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValInterfDtoMatchKey extends BaseEntity {
    /**
     * 模版英文名称
     */
    private String tmpEName;

    /**
     * DTO名称
     */
    private String dtoName;

    /**
     * 模版字段英文名
     */
    private String tmpColEName;

    /**
     * 取得 模版英文名称
     * @return 模版英文名称
     */
    public String getTmpEName() {
        return tmpEName;
    }

    /**
     * 设置 模版英文名称
     * @param tmpEName 模版英文名称
     */
    public void setTmpEName(String tmpEName) {
        this.tmpEName = tmpEName == null ? null : tmpEName.trim();
    }

    /**
     * 取得 DTO名称
     * @return DTO名称
     */
    public String getDtoName() {
        return dtoName;
    }

    /**
     * 设置 DTO名称
     * @param dtoName DTO名称
     */
    public void setDtoName(String dtoName) {
        this.dtoName = dtoName == null ? null : dtoName.trim();
    }

    /**
     * 取得 模版字段英文名
     * @return 模版字段英文名
     */
    public String getTmpColEName() {
        return tmpColEName;
    }

    /**
     * 设置 模版字段英文名
     * @param tmpColEName 模版字段英文名
     */
    public void setTmpColEName(String tmpColEName) {
        this.tmpColEName = tmpColEName == null ? null : tmpColEName.trim();
    }
}