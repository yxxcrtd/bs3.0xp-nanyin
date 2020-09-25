package com.joyintech.tams.common.base.entity;

public class BokAcctStandardNature extends BokAcctStandardNatureKey {
    /**
     * 科目性质描述
     */
    private String subjectNatureName;

    /**
     * 取得 科目性质描述
     * @return 科目性质描述
     */
    public String getSubjectNatureName() {
        return subjectNatureName;
    }

    /**
     * 设置 科目性质描述
     * @param subjectNatureName 科目性质描述
     */
    public void setSubjectNatureName(String subjectNatureName) {
        this.subjectNatureName = subjectNatureName == null ? null : subjectNatureName.trim();
    }
}