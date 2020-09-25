package com.joyintech.tams.common.base.entity;

public class RepCheckRelation extends RepCheckRelationKey {
    /**
     * 报表编码
     */
    private String repId;

    /**
     * 校验编码
     */
    private String checkId;

    /**
     * 校验位置
     */
    private String checkFor;

    /**
     * 取得 报表编码
     * @return 报表编码
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 报表编码
     * @param repId 报表编码
     */
    public void setRepId(String repId) {
        this.repId = repId == null ? null : repId.trim();
    }

    /**
     * 取得 校验编码
     * @return 校验编码
     */
    public String getCheckId() {
        return checkId;
    }

    /**
     * 设置 校验编码
     * @param checkId 校验编码
     */
    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }

    /**
     * 取得 校验位置
     * @return 校验位置
     */
    public String getCheckFor() {
        return checkFor;
    }

    /**
     * 设置 校验位置
     * @param checkFor 校验位置
     */
    public void setCheckFor(String checkFor) {
        this.checkFor = checkFor == null ? null : checkFor.trim();
    }
}