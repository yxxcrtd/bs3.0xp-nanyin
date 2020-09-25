package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DisPraamsSubKey extends BaseEntity {
    /**
     * 关联主表编号
     */
    private String tabNum;

    /**
     * 查询条件表主键
     */
    private String disId;

    /**
     * 取得 关联主表编号
     * @return 关联主表编号
     */
    public String getTabNum() {
        return tabNum;
    }

    /**
     * 设置 关联主表编号
     * @param tabNum 关联主表编号
     */
    public void setTabNum(String tabNum) {
        this.tabNum = tabNum == null ? null : tabNum.trim();
    }

    /**
     * 取得 查询条件表主键
     * @return 查询条件表主键
     */
    public String getDisId() {
        return disId;
    }

    /**
     * 设置 查询条件表主键
     * @param disId 查询条件表主键
     */
    public void setDisId(String disId) {
        this.disId = disId == null ? null : disId.trim();
    }
}