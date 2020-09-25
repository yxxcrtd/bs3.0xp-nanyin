package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DisPoiTab extends BaseEntity {
    /**
     * 报表编号
     */
    private String tabNum;

    /**
     * 报表名称
     */
    private String tabName;

    /**
     * 模板路径
     */
    private String tempPath;

    /**
     * 模板名称
     */
    private String tempName;

    /**
     * 参数条件
     */
    private String tempPrams;

    /**
     * 取得 报表编号
     * @return 报表编号
     */
    public String getTabNum() {
        return tabNum;
    }

    /**
     * 设置 报表编号
     * @param tabNum 报表编号
     */
    public void setTabNum(String tabNum) {
        this.tabNum = tabNum == null ? null : tabNum.trim();
    }

    /**
     * 取得 报表名称
     * @return 报表名称
     */
    public String getTabName() {
        return tabName;
    }

    /**
     * 设置 报表名称
     * @param tabName 报表名称
     */
    public void setTabName(String tabName) {
        this.tabName = tabName == null ? null : tabName.trim();
    }

    /**
     * 取得 模板路径
     * @return 模板路径
     */
    public String getTempPath() {
        return tempPath;
    }

    /**
     * 设置 模板路径
     * @param tempPath 模板路径
     */
    public void setTempPath(String tempPath) {
        this.tempPath = tempPath == null ? null : tempPath.trim();
    }

    /**
     * 取得 模板名称
     * @return 模板名称
     */
    public String getTempName() {
        return tempName;
    }

    /**
     * 设置 模板名称
     * @param tempName 模板名称
     */
    public void setTempName(String tempName) {
        this.tempName = tempName == null ? null : tempName.trim();
    }

    /**
     * 取得 参数条件
     * @return 参数条件
     */
    public String getTempPrams() {
        return tempPrams;
    }

    /**
     * 设置 参数条件
     * @param tempPrams 参数条件
     */
    public void setTempPrams(String tempPrams) {
        this.tempPrams = tempPrams == null ? null : tempPrams.trim();
    }
}