package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblAssetSubMapDtlKey extends BaseEntity {
    /**
     * 组合资产科目映射编号
     */
    private String id;

    /**
     * 源科目代码

     */
    private String srcCode;

    /**
     * 取得 组合资产科目映射编号
     * @return 组合资产科目映射编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 组合资产科目映射编号
     * @param id 组合资产科目映射编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 源科目代码

     * @return 源科目代码

     */
    public String getSrcCode() {
        return srcCode;
    }

    /**
     * 设置 源科目代码

     * @param srcCode 源科目代码

     */
    public void setSrcCode(String srcCode) {
        this.srcCode = srcCode == null ? null : srcCode.trim();
    }
}