package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysPrdGrpHisKey extends BaseEntity {
    /**
     * �����
     */
    private String prdGrpId;

    /**
     * ����
     */
    private Integer hisNo;

    /**
     * 取得 �����
     * @return �����
     */
    public String getPrdGrpId() {
        return prdGrpId;
    }

    /**
     * 设置 �����
     * @param prdGrpId �����
     */
    public void setPrdGrpId(String prdGrpId) {
        this.prdGrpId = prdGrpId == null ? null : prdGrpId.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 ����
     * @param hisNo ����
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }
}