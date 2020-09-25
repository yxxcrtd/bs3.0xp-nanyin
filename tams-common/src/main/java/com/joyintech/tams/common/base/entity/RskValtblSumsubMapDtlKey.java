package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblSumsubMapDtlKey extends BaseEntity {
    /**
     * 汇总科目映射编号
     */
    private String id;

    /**
     * 目标汇总科目代码
     */
    private String sumCode;

    /**
     * 取得 汇总科目映射编号
     * @return 汇总科目映射编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 汇总科目映射编号
     * @param id 汇总科目映射编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 目标汇总科目代码
     * @return 目标汇总科目代码
     */
    public String getSumCode() {
        return sumCode;
    }

    /**
     * 设置 目标汇总科目代码
     * @param sumCode 目标汇总科目代码
     */
    public void setSumCode(String sumCode) {
        this.sumCode = sumCode == null ? null : sumCode.trim();
    }
}