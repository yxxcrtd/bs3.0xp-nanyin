package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class AccpoolRuleKey extends BaseEntity {
    /**
     * 准入池代码
     */
    private String accpoolId;

    /**
     * 出入项类型包括资产、机构
     */
    private String accpoolId2;

    /**
     * 取得 准入池代码
     * @return 准入池代码
     */
    public String getAccpoolId() {
        return accpoolId;
    }

    /**
     * 设置 准入池代码
     * @param accpoolId 准入池代码
     */
    public void setAccpoolId(String accpoolId) {
        this.accpoolId = accpoolId == null ? null : accpoolId.trim();
    }

    /**
     * 取得 出入项类型包括资产、机构
     * @return 出入项类型包括资产、机构
     */
    public String getAccpoolId2() {
        return accpoolId2;
    }

    /**
     * 设置 出入项类型包括资产、机构
     * @param accpoolId2 出入项类型包括资产、机构
     */
    public void setAccpoolId2(String accpoolId2) {
        this.accpoolId2 = accpoolId2 == null ? null : accpoolId2.trim();
    }
}