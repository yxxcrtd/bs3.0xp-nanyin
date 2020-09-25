package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblStructMapDtlKey extends BaseEntity {
    /**
     * 结构映射编号
     */
    private String id;

    /**
     * 列索引
     */
    private String srcColIndex;

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
     * 取得 列索引
     * @return 列索引
     */
    public String getSrcColIndex() {
        return srcColIndex;
    }

    /**
     * 设置 列索引
     * @param srcColIndex 列索引
     */
    public void setSrcColIndex(String srcColIndex) {
        this.srcColIndex = srcColIndex == null ? null : srcColIndex.trim();
    }
}