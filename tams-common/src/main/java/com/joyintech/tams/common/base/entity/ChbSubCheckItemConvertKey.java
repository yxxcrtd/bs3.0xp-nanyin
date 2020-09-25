package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSubCheckItemConvertKey extends BaseEntity {
    /**
     * 分类编号
     */
    private String classId;

    /**
     * 项目编号别名
     */
    private String itemCodeOther;

    /**
     * 取得 分类编号
     * @return 分类编号
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置 分类编号
     * @param classId 分类编号
     */
    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    /**
     * 取得 项目编号别名
     * @return 项目编号别名
     */
    public String getItemCodeOther() {
        return itemCodeOther;
    }

    /**
     * 设置 项目编号别名
     * @param itemCodeOther 项目编号别名
     */
    public void setItemCodeOther(String itemCodeOther) {
        this.itemCodeOther = itemCodeOther == null ? null : itemCodeOther.trim();
    }
}