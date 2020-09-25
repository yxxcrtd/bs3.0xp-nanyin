package com.joyintech.tams.common.base.entity;

public class RskValtblSubject extends RskValtblSubjectKey {
    /**
     * 科目名称
     */
    private String subName;

    /**
     * 取得 科目名称
     * @return 科目名称
     */
    public String getSubName() {
        return subName;
    }

    /**
     * 设置 科目名称
     * @param subName 科目名称
     */
    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }
}