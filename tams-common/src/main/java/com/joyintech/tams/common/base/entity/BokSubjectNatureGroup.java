package com.joyintech.tams.common.base.entity;

public class BokSubjectNatureGroup extends BokSubjectNatureGroupKey {
    /**
     * 使用说明
     */
    private String useRemark;

    /**
     * 取得 使用说明
     * @return 使用说明
     */
    public String getUseRemark() {
        return useRemark;
    }

    /**
     * 设置 使用说明
     * @param useRemark 使用说明
     */
    public void setUseRemark(String useRemark) {
        this.useRemark = useRemark == null ? null : useRemark.trim();
    }
}