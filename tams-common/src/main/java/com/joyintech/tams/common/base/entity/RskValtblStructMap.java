package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblStructMap extends BaseEntity {
    /**
     * 结构映射编号
     */
    private String id;

    /**
     * 映射名称
     */
    private String name;

    /**
     * 取得 结构映射编号
     * @return 结构映射编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 结构映射编号
     * @param id 结构映射编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 映射名称
     * @return 映射名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 映射名称
     * @param name 映射名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}