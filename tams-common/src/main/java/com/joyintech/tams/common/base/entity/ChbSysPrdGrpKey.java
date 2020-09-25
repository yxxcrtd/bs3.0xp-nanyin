package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysPrdGrpKey extends BaseEntity {
    /**
     * 产品组代码
     */
    private String prdGrpId;

    /**
     * 取得 产品组代码
     * @return 产品组代码
     */
    public String getPrdGrpId() {
        return prdGrpId;
    }

    /**
     * 设置 产品组代码
     * @param prdGrpId 产品组代码
     */
    public void setPrdGrpId(String prdGrpId) {
        this.prdGrpId = prdGrpId == null ? null : prdGrpId.trim();
    }
}